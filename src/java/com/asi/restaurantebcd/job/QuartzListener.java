package com.asi.restaurantebcd.job;



import com.asi.restaurantebcd.job.JobEmail;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.quartz.CronExpression;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzListener implements ServletContextListener {

       Scheduler scheduler = null;

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
        public void contextInitialized(ServletContextEvent servletContext) {
                System.out.println("Context Initialized");
                    
                   jobEJBLocal.startSchedulle();
                   
/*                    try {
                    
                    
                        // Setup the Job class and the Job group
                        JobDetail job = newJob(JobEmail.class).withIdentity(
                                        "CronQuartzJob", "Group").build();

                       
                        // Create a Trigger that fires every 5 minutes.
                        Trigger trigger = newTrigger()
                        .withIdentity("1", "Group")
                        .withSchedule(CronScheduleBuilder.cronSchedule("30 * * * * ?"))
                        .build();
                        
                        


                        // Setup the Job and Trigger with Scheduler & schedule jobs
                        scheduler = new StdSchedulerFactory().getScheduler();
                        scheduler.start();
                        scheduler.scheduleJob(job, trigger);
                }
                catch (SchedulerException e) {
                        e.printStackTrace();
                } */

        }

        @Override
        public void contextDestroyed(ServletContextEvent servletContext) {
                System.out.println("Context Destroyed");
                jobEJBLocal.stopSchedulle();
        }
     

    
            
}