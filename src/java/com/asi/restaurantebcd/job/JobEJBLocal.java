/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.job;

import javax.ejb.Local;
import org.quartz.JobExecutionContext;

/**
 *
 * @author siman
 */
@Local
public interface JobEJBLocal {
    public void execute(JobExecutionContext jec);
    
    public void startSchedulle();

    public void stopSchedulle();
        
    public void startProgramacionTareas(Integer idProgramacion);
    
}
