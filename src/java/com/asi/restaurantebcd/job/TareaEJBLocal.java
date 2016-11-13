/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.job;

import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author siman
 */
@Local
public interface TareaEJBLocal {
    
        public void process(Map<String,String> args);
    
}
