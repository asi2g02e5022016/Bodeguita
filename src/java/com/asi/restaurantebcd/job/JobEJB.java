/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.job;

import com.asi.restaurantbcd.modelo.Programaciondetalle;
import com.asi.restaurantbcd.modelo.Programaciontareas;
import com.asi.restaurantebcd.negocio.util.LineaComandoUtils;
import com.asi.restaurantebcd.negocio.util.TelnetUtils;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.quartz.CronScheduleBuilder;
import static org.quartz.JobBuilder.newJob;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import static org.quartz.TriggerBuilder.newTrigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author siman
 */
@Stateless(name = "/JobEJB")
public class JobEJB implements JobEJBLocal {

    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private String ejbName;

    Scheduler scheduler = null;

    private TareaEJBLocal lookupprocesaFacturasEJBLocal() {
        try {
            Context c = new InitialContext();
            return (TareaEJBLocal) c.lookup("java:global/RestaurantBDC/" + this.getEjbName() + "!com.asi.restaurantebcd.job.TareaEJBLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public void execute(JobExecutionContext jec) {
        try {
            System.out.println("Fexecute JOB..");
            Programaciontareas prg;
            if (em == null) {
                System.out.println("Entitymanager nulo");
            }

            String id = jec.getTrigger().getKey().getName();
            System.out.println("Ejecutando tarea: " + id);
            prg = (Programaciontareas) em.createQuery("select p from Programaciontareas p where p.idprogramacion =:id")
                    .setParameter("id", Integer.valueOf(id))
                    .getSingleResult();

            Date p = new Date();

            if (prg.getInicio().before(p) && (prg.getFin() == null || prg.getFin().after(p)) || id.equals("19")) {

                for (Programaciondetalle pdet : prg.getProgramaciondetalleList()) {
                    System.out.println("Tipo tarea: " + pdet.getIdtarea().getIdtipotarea().getIdtipotarea());
                    if (pdet.getIdtarea().getIdtipotarea().getIdtipotarea().equals("EJB")) {
                        this.setEjbName(pdet.getIdtarea().getEjecutable());
                        TareaEJBLocal jobEJB = lookupprocesaFacturasEJBLocal();
                        int i = 0;
                        String[] args = {""};
                        if (pdet.getParametros() != null) {
                            args = pdet.getParametros().split(" ");
                        }
                        Map<String, String> m_args = new HashMap<String, String>();

                        for (i = 0; i < args.length; i++) {
                            if (args[i].toLowerCase().startsWith("-par:")) {
                                m_args.put(args[i].replace("-par:", ""), args[i + 1]);
                                i++;
                            }
                        }
                        jobEJB.process(m_args);

                    }
                    
                     if (pdet.getIdtarea().getIdtipotarea().getIdtipotarea().equals("ETL")) {
                          if(pdet.getHost().getValor().equals("localhost") || pdet.getHost().getValor().equals("127.0.0.0") 
                                                                        || pdet.getHost().getValor().equals("") || true){
                                    String salida = LineaComandoUtils.Run("C:\\Instaladores\\spoon\\data-integration\\Kitchen.bat -file=C:\\Instaladores\\JOBS\\"+pdet.getIdtarea().getEjecutable()+" "+pdet.getParametros()+" --level=Minimal");
                                    System.out.println(salida);
                                } else {
                                TelnetUtils telnet = new TelnetUtils("192.168.0.111", "siman", "Siman2016");
                                telnet.sendCommand("C:\\Instaladores\\spoon\\data-integration\\KitchenGF.bat -file=C:\\Instaladores\\JOBS\\SUBE_FACTURAS.kjb "+pdet.getIdtarea().getEjecutable()+" "+pdet.getParametros()+" --level=Minimal");
                                telnet.disconnect();
                                    }
                          
                          
                        
                     }

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the ejbName
     */
    public String getEjbName() {
        return ejbName;
    }

    /**
     * @param ejbName the ejbName to set
     */
    public void setEjbName(String ejbName) {
        this.ejbName = ejbName;
    }

    @Override
    public void startSchedulle() {
        
        //startProgramacionTareas(19);
        
        Date p = new Date();

        System.out.println("Fecha query: " + p.toString());
        List<Programaciontareas> lprg = em.createQuery("select p from Programaciontareas p")
                .getResultList();
        System.out.println("Programs found: " + lprg.size());
        for (Programaciontareas pdet : lprg) {

            if (pdet.getInicio().before(p) && (pdet.getFin() == null || pdet.getFin().after(p))) {
                startProgramacionTareas(pdet.getIdprogramacion());
            }

        }
    }

    @Override
    public void stopSchedulle() {
        System.out.println("Shedulle End");
        try {
            scheduler = new StdSchedulerFactory().getScheduler();
        } catch (SchedulerException ex) {
            Logger.getLogger(JobControllerMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            scheduler.shutdown();
            System.out.println("Shedulle Ended");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startProgramacionTareas(Integer idProgramacion) {
        System.out.println("startProgramacionTareas");
        Programaciontareas ptar;
        ptar = (Programaciontareas) em.createQuery("SELECT p FROM Programaciontareas p where p.idprogramacion =:id")
                .setParameter("id", idProgramacion)
                .getSingleResult();

        try {
            scheduler = new StdSchedulerFactory().getScheduler();
        } catch (SchedulerException ex) {
            Logger.getLogger(JobControllerMB.class.getName()).log(Level.SEVERE, null, ex);
        }

        JobDetail job = newJob(JobController.class).withIdentity(
                "CronQuartzJob-" + ptar.getIdprogramacion(), "Group-" + ptar.getIdprogramacion()).build();
        StringBuilder cron = new StringBuilder();

        //segundos siempre es cero:
        cron.append("0");
        Trigger trigger = null;
        //minutos
        if (ptar.getFrecuencia() != null && ptar.getFrecuencia() > 0) {
            Date dt = new Date();
            Calendar cal = GregorianCalendar.getInstance(); // creates a new calendar instance
            cal.setTime(dt);   // assigns calendar to given date 
            cal.add(Calendar.MINUTE, 1);

            if (ptar.getFrecuencia() <= 59) {
                int min = cal.get(Calendar.MINUTE);
                int frq = (new Double(ptar.getFrecuencia())).intValue();
                cron.append(" ").append(min);
                cron.append("/").append(frq).append(" *");
            }

            if (ptar.getFrecuencia() >= 60) {
                int min = cal.get(Calendar.MINUTE);
                cron.append(" ").append(min);
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int frq = (new Double((ptar.getFrecuencia() / 60))).intValue();
                cron.append(" " + hour);
                cron.append("/" + frq);
            }

            cron.append(" * * ?");

            System.out.println("Cron: " + cron.toString());
            // Create a Trigger that fires every 5 minutes.
            trigger = newTrigger()
                    .withIdentity(ptar.getIdprogramacion().toString(), "Group-" + ptar.getIdprogramacion())
                    .withSchedule(CronScheduleBuilder.cronSchedule(cron.toString()))
                    .build();

        } else {
            Date dt = new Date();
            Calendar cal = GregorianCalendar.getInstance(); // creates a new calendar instance
            if (ptar.getInicio().after(dt)) {
                cal.setTime(ptar.getInicio());   // assigns calendar to given date   
            } else {
                cal.setTime(dt);   // assigns calendar to given date   
            }

            cal.add(Calendar.MINUTE, 1);

            cron.append("0");
            cron.append(" ").append(cal.get(Calendar.MINUTE));
            cron.append(" ").append(cal.get(Calendar.HOUR_OF_DAY));
            cron.append(" *").append(cal.get(Calendar.DAY_OF_MONTH));
            cron.append(" *").append(cal.get(Calendar.MONTH));
            cron.append(" ?");
            cron.append(" ").append(Calendar.YEAR);

            System.out.println("Cron: " + cron.toString());
            // Create a Trigger that fires every 5 minutes.
            trigger = newTrigger()
                    .withIdentity(ptar.getIdprogramacion().toString(), "Group-" + ptar.getIdprogramacion())
                    .withSchedule(CronScheduleBuilder.cronSchedule(cron.toString()))
                    .build();
        }

        try {
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException ex) {
            Logger.getLogger(JobEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
