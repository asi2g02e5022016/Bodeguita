/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantebcd.job.TareaEJBLocal;
import java.util.Date;
import java.util.Map;
import javax.ejb.Stateless;


/**
 *
 * @author siman
 */
@Stateless(name = "/JobEmail")
public class JobEmail implements TareaEJBLocal {


    @Override
    public void process(Map<String, String> args) {
             Date dt  = new Date();
                  System.out.println(dt.toString()+ "Email Service Trigger name");
                  System.out.println(dt.toString()+ "Email Service Trigger num: " + args.get("trigger_num"));
    }
}
