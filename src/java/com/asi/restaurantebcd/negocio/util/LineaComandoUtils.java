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
                salida=salida+aux;                            
                aux = br.readLine();
            }  
           
            
           return salida;
           
      }
      catch (Exception e){
           e.printStackTrace();
           System.out.println("Error al ejecutar getRunTime para: " + Command + "\n" + e.getMessage());
           return(e.getMessage());
      }
    
   }
}
