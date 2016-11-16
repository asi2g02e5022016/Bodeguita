/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantebcd.job.TareaEJBLocal;
import java.util.Map;
import javax.ejb.Stateless;

/**
 *
 * @author siman
 */
@Stateless(name = "/TestEJB")
public class TestEJB  implements TareaEJBLocal{

    @Override
    public void process(Map<String, String> args) {
      System.out.println("Imprimiendo TestEJB");
      
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
