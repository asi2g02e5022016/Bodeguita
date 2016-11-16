/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.job;

import com.asi.restaurantbcd.modelo.Programaciontareas;
import com.asi.restaurantebcd.negocio.util.TelnetUtils;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.quartz.CronScheduleBuilder;
import static org.quartz.JobBuilder.newJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import static org.quartz.TriggerBuilder.newTrigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author siman
 */
@ManagedBean(name = "jobController")
@ViewScoped
public class JobControllerMB implements Serializable{

       Scheduler scheduler = null;    
   
       @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em;
    
       
       
    TareaEJBLocal procesaFacturasEJB = lookupprocesaFacturasEJBLocal();
     private TareaEJBLocal lookupprocesaFacturasEJBLocal() {
        try {
            Context c = new InitialContext();
            return (TareaEJBLocal) c.lookup("java:global/RestaurantBDC//procesaFacturasEJB!com.asi.restaurantebcd.job.TareaEJBLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    public void procesarFacturas(){

    }

 
}
