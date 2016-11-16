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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author siman
 */
@Stateless
public class JobController implements Job {
    
    JobEJBLocal jobEJBLocal = lookupprocesaFacturasEJBLocal();
    private JobEJBLocal lookupprocesaFacturasEJBLocal() {
        try {
            Context c = new InitialContext();
            return (JobEJBLocal) c.lookup("java:global/RestaurantBDC//JobEJB!com.asi.restaurantebcd.job.JobEJBLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        try{
        jobEJBLocal.execute(jec);}
        catch(Exception e){e.printStackTrace();};
    }
    
    
    
}
