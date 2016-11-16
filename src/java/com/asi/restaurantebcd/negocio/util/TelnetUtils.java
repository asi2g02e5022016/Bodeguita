/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import org.apache.commons.net.telnet.TelnetClient;

/**
 *
 * @author siman
 */
public class TelnetUtils
{
  private TelnetClient telnet = new TelnetClient();
  private InputStream in;
  private PrintStream out;
  private String prompt = ">";
BufferedReader br;
        
  public TelnetUtils(String pIp, String pUser, String pPass ) {
   try {
     // Conectar al servidor:
     telnet.connect( pIp, 23 );
     
     // Crea streams de entrada y salida
     in = telnet.getInputStream();
     out = new PrintStream( telnet.getOutputStream() );

     br = new BufferedReader (new InputStreamReader (in));
     // Realizar login
     readUntil("login:" );
     write( pUser );
     readUntil("password:" );
     write( pPass );     
    
     // Avanza al prompt
     readUntil(prompt);
   }
   catch( Exception e ) {
     e.printStackTrace();
   }
  }


  public String readUntil( String pattern ) {
   try {
     char lastChar = pattern.charAt( pattern.length() - 1 );
     StringBuffer sb = new StringBuffer();
     boolean found = false;
     int i=0;
     char ch = ( char )in.read();
     while( i<=100000 ) {
      
      sb.append( ch );
      if( ch == lastChar ) {
        if( sb.toString().endsWith(pattern ) ) {
         i = 100000;
         System.out.print( sb.toString() );
         return sb.toString();
        }
      }
      ch = ( char )in.read();
      i=i+1;
     }
   }
   catch( Exception e ) {
     e.printStackTrace();
   }
   return null;
  }

  public void write( String value ) {
   try {
     out.println( value );
     out.flush();
     System.out.println( value );
   }
   catch( Exception e ) {
     e.printStackTrace();
   }
  }

  public String sendCommand( String command ) {
   try {
     write( command );
     return readUntil( prompt );
   }
   catch( Exception e ) {
     e.printStackTrace();
   }
   return null;
  }

  public void disconnect() {
   try {
     telnet.disconnect();
   }
   catch( Exception e ) {
     e.printStackTrace();
   }
  }

  public String readprueba( String pattern ) {
   try {
     char lastChar = pattern.charAt( pattern.length() - 1 );
     String cadena;
     StringBuffer sb = new StringBuffer();
     boolean found = false;
     char ch = ( char )in.read();
     while( true ) {
      System.out.print( ch );
      sb.append( ch );
      if( ch == lastChar ) {
        if( sb.toString().endsWith( pattern ) ) {
          cadena = sb.toString();
         return cadena;
        }
      }
      ch = ( char )in.read();
     }
   }
   catch( Exception e ) {
     e.printStackTrace();
   }
   return null;
  }  
     
}