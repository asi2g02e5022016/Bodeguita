/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.util;

import java.io.Serializable;

/**
 *
 * @author samaelopez
 */
public class WsException  extends Exception implements Serializable {
 
       private static final long serialVersionUID = 1L;

    public WsException() {
        super();
    }

    public WsException(String msg)   {
        super(msg);
    }
    
    public WsException(String msg, Exception e)  {
        super(msg, e);
    }

}
