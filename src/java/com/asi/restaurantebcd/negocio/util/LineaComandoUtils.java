/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author siman
 */
public class LineaComandoUtils {
    
     public static String Run(String Command){
      try{
           String salida="";
           Process p=Runtime.getRuntime().exec(Command);
               
           InputStream ist = p.getInputStream();
           BufferedReader br = new BufferedReader (new InputStreamReader (ist));
           
           String aux = br.readLine(); 
           
           while (aux!=null)
            {
                salida=salida+aux+"\n";                            
                aux = br.readLine();
            }  
           
           InputStream ise = p.getErrorStream();
           BufferedReader bre = new BufferedReader (new InputStreamReader (ise));
           String error="";
           for (int i = 0; i < ise.available(); i++) {
            error = error+ bre.readLine();
            }
           
            
           return salida+error;
           
      }
      catch (Exception e){
           e.printStackTrace();
           System.out.println("Error al ejecutar getRunTime para: " + Command + "\n" + e.getMessage());
           return(e.getMessage());
      }
    
   }
}
