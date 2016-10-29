/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.util;

import java.io.InputStream;
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
  private String prompt = "$";

  public TelnetUtils(String pIp, String pUser, String pPass ) {
   try {
     // Conectar al servidor:
     telnet.connect( pIp, 23 );
     
     // Crea streams de entrada y salida
     in = telnet.getInputStream();
     out = new PrintStream( telnet.getOutputStream() );

     // Realizar login
     readUntil("login:" );
     write( pUser );
     readUntil(pUser+"'s Password:" );
     write( pPass );     
    
     // Avanza al prompt
     readUntil(prompt);
   }
   catch( Exception e ) {
     e.printStackTrace();
   }
  }

  public void su( String password ) {
    try {
      write( "su" );
      readUntil( "Password: " );
      write( password );
      prompt = "$";
      readUntil( prompt );
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
      System.out.print( ch );
      sb.append( ch );
      if( ch == lastChar ) {
        if( sb.toString().endsWith(pattern ) ) {
         i = 100000;
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
   public static void comando_zip(String pfile, String pstore, String ptype, String p_Ip, String p_User, String p_Pass) {
        try {

    //Comando para menejo de archvios de ventas
             TelnetUtils telnet = new TelnetUtils(p_Ip, p_User, p_Pass);            
             telnet.sendCommand( "cd $FRANQ_TOP" );
             telnet.sendCommand( "unzip -L "+pfile);

             telnet.disconnect();        
             
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
   public static void comando_zipvta(String pfile, String pstore, String ptype, String p_Ip, String p_User, String p_Pass) {  
        try {

    //Comando para menejo de archvios de ventas   
             TelnetUtils telnet = new TelnetUtils(p_Ip, p_User, p_Pass); 
             telnet.sendCommand( "cd $FRANQ_TOP" );
             telnet.sendCommand( "unzip -L "+pfile+" "+ptype+pstore+".ter");
             Thread.sleep(6000);
             telnet.disconnect();        
             
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
  
  public static void comando_remane( String pstore, String ptype, String p_Ip, String p_User, String p_Pass) {
        try {

    //Comando para menejo de archvios de ventas 
             TelnetUtils telnet = new TelnetUtils(p_Ip, p_User, p_Pass);
             telnet.sendCommand( "cd $FRANQ_TOP" );
             telnet.sendCommand( "mv "+ptype+pstore+".ter "+ptype.toUpperCase()+"XXXX.TER");
             Thread.sleep(5000);
             telnet.disconnect();        
             
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
  public static void comando_borrafile( String pfile, String p_Ip, String p_User, String p_Pass) {
        try {

    //Comando para menejo de archvios de ventas
             TelnetUtils telnet = new TelnetUtils(p_Ip, p_User, p_Pass);            
             telnet.sendCommand( "cd $FRANQ_TOP" );
             telnet.sendCommand( "rm -rf "+pfile);
             Thread.sleep(5000);
             telnet.disconnect();        
             
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
  
    public static void comando_borra( String pfile, String p_Ip, String p_User, String p_Pass) {
          try {

    //Comando para menejo de archvios de ventas
             TelnetUtils telnet = new TelnetUtils(p_Ip, p_User, p_Pass);            
             telnet.sendCommand( "cd $FRANQ_TOP" );
             telnet.sendCommand( "rm -rf *");
             Thread.sleep(5000);
             telnet.disconnect();        
             
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    public static void comando_lineas(String p_Ip, String p_User, String p_Pass)
  {
   // Guarda la informacion de lineas por arhcivos TER en archivo LONGFILE.VER 
    TelnetUtils telnet = new TelnetUtils(p_Ip, p_User, p_Pass);            
    telnet.sendCommand( "cd $FRANQ_TOP" );
    telnet.sendCommand( "wc -lw *TER > LONGFILE.VER");
    telnet.sendCommand("chmod 777 *");
    telnet.disconnect();   
   
  }
   public static void comando_zipcom(String pfile, String p_Ip, String p_User, String p_Pass) {
         try {

    //Comando para menejo de archvios de  compra
             TelnetUtils telnet = new TelnetUtils(p_Ip, p_User, p_Pass);
             telnet.sendCommand( "cd $FRANQ_TOP" );
             telnet.sendCommand( "unzip -L "+pfile);
             Thread.sleep(6000);
             telnet.disconnect();        
             
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

  public static void comando_remane_compra( String pexten, String pfile, String p_Ip, String p_User, String p_Pass) {  
        try {

    //Comando para menejo de archvios de ventas   
             TelnetUtils telnet = new TelnetUtils(p_Ip, p_User, p_Pass);
             telnet.sendCommand( "cd $FRANQ_TOP" );
             telnet.sendCommand( "mv *"+pexten+" "+pfile);
             Thread.sleep(5000);
             telnet.disconnect();        
             
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
     public static void comando_wc_compra(String p_prefi, String p_Ip, String p_User, String p_Pass)
  {
   // Guarda la informacion de lineas por arhcivos TER en archivo LONGFILE.VER 
    TelnetUtils telnet = new TelnetUtils(p_Ip, p_User, p_Pass);            
    telnet.sendCommand( "cd $FRANQ_TOP" );
    telnet.sendCommand( "wc -lw "+p_prefi+"* > LONGFILEC.VER");
    telnet.sendCommand("chmod 777 *");
    telnet.disconnect();   
  }   
   public static void comando_zipArt(String pfile, String p_Ip, String p_User, String p_Pass) {
        try {

    //Comando para menejo de archvios de  compra
             TelnetUtils telnet = new TelnetUtils(p_Ip, p_User, p_Pass);            
             telnet.sendCommand( "cd $FRANQ_TOP" );
             telnet.sendCommand( "unzip "+pfile);
             Thread.sleep(5000);
             telnet.disconnect();        
             
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
     public static void comando_lineasArt(String p_Ip, String p_User, String p_Pass)
  {
   // Guarda la informacion de lineas por arhcivos TER en archivo LONGFILE.VER 
    TelnetUtils telnet = new TelnetUtils(p_Ip, p_User, p_Pass);            
    telnet.sendCommand( "cd $FRANQ_TOP" );
    telnet.sendCommand( "wc -lw ART*TER > LONGFILEA.VER");
    telnet.sendCommand("chmod 777 *");

    telnet.disconnect();   
   
  }
       public static void comando_movfile(String p_Ip, String p_User, String p_Pass, String pArchivo , String pArchivoName )
  {
   // Guarda la informacion de lineas por arhcivos TER en archivo LONGFILE.VER 
    TelnetUtils telnet = new TelnetUtils(p_Ip, p_User, p_Pass);            
    telnet.sendCommand("cd $FRANQ_TOP" );
    telnet.sendCommand("cd ../tempfranq" );
    telnet.sendCommand("cp "+pArchivo+" ../franquicias");
    telnet.sendCommand("cd ../franquicias" );
    telnet.sendCommand("mv "+pArchivo+" "+pArchivoName);
    telnet.disconnect();   
   
  }
  
   public static void comando_ftp( String p_Ip, String p_User, String p_Pass, String p_IpFtp, String p_UserFtp, String p_PassFtp,String pArchivo, String p_DirLoc, String p_DirRemo) {
        try {

    //Comando para menejo de ftp
             TelnetUtils telnet = new TelnetUtils(p_Ip, p_User, p_Pass);  
                       
             telnet.write("ftp "+ p_IpFtp);
             telnet.readUntil("Name ("+p_IpFtp+":franquse):"); 
             telnet.write( p_UserFtp );
             telnet.readUntil("Password:");
             telnet.write( p_PassFtp );
             telnet.readUntil("ftp>");
             telnet.write("cd "+p_DirRemo );
             telnet.readUntil("ftp>");
             telnet.write("lcd "+p_DirLoc );
             telnet.readUntil("ftp>");
             telnet.write("bin" );
             telnet.readUntil("ftp>");
             telnet.write("get "+pArchivo );
             telnet.readUntil("ftp>");
             telnet.write("quit" );
             telnet.readUntil("$");
             telnet.sendCommand("cd "+p_DirLoc);
             telnet.sendCommand("chmod 777 *");
             
             telnet.disconnect();        
             
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    } 
      
  public static void comando_log_vta(String p_prefi, String p_Ip, String p_User, String p_Pass)
  {
   // Guarda la informacion de lineas por arhcivos TER en archivo LONGFILE.VER 
    TelnetUtils telnet = new TelnetUtils(p_Ip, p_User, p_Pass);            
    telnet.sendCommand( "cd $FRANQ_TOP" );
    telnet.sendCommand( "cd ../tempfranq/log" );
    telnet.sendCommand( "echo "+p_prefi+" > ArchivosVta.log");
    telnet.disconnect();   
  }  
  
  public static void comando_log_art(String p_prefi, String p_Ip, String p_User, String p_Pass)
  {
   // Guarda la informacion de lineas por arhcivos TER en archivo LONGFILE.VER 
    TelnetUtils telnet = new TelnetUtils(p_Ip, p_User, p_Pass);            
    telnet.sendCommand( "cd $FRANQ_TOP" );
    telnet.sendCommand( "cd ../tempfranq/log" );
    telnet.sendCommand( "echo "+p_prefi+" > ArchivosArt.log");
    telnet.disconnect();   
  }   
  
  public static void comando_log_cmp(String p_prefi, String p_Ip, String p_User, String p_Pass)
  {
   // Guarda la informacion de lineas por arhcivos TER en archivo LONGFILE.VER 
    TelnetUtils telnet = new TelnetUtils(p_Ip, p_User, p_Pass);            
    telnet.sendCommand( "cd $FRANQ_TOP" );
    telnet.sendCommand( "cd ../tempfranq/log" );
    telnet.sendCommand( "echo "+p_prefi+" > ArchivosCmp.log");
    telnet.disconnect();   
  }  
}