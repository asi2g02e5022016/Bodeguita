/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.util;

import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PageRanges;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;

/**
 *
 * @author samaelopez
 */
public class ImpFactura {
        
    public void ImprimirFactura2(String datSource, String url, Map parameters)
            throws IOException, NamingException,
            SQLException, Exception {
        Connection connection = null;
        String archJasper;
        URI uriObj = null;
        byte[] reporteByte = null;
        try {
            InitialContext initialContext = new InitialContext();
            DataSource ds = (DataSource) initialContext.lookup(datSource);
            connection = ds.getConnection();
            uriObj = getClass().getResource(url).toURI();
            File reporte = new File(uriObj);
            archJasper = reporte.getAbsolutePath();
            JasperPrint impresion = null;
            
            impresion = JasperFillManager
                    .fillReport(archJasper, parameters, connection);
            
//            float w = impresion.getPageWidth() / 72f;
//            float h = impresion.getPageHeight() / 72f;
            
            // Configuro el area de impresiÃ³n
//            int unidad = MediaPrintableArea.INCH;
        
//            System.out.println("ww/" + impresion.getPageWidth());
//            System.out.println("hh/" + impresion.getPageHeight());
//            
//            System.out.println("-----------------------------");
//            System.out.println("Width" + w);
//            System.out.println("Height " + h);
//            System.out.println(MediaSize.findMedia(Float.parseFloat("3.139"), 
//                    Float.parseFloat("10.972"), MediaSize.INCH));
//            System.out.println("-----------------------------");
//            
            
                PrinterJob job  = PrinterJob.getPrinterJob();
                PrintService services =
                        PrintServiceLookup.lookupDefaultPrintService();
                job.setPrintService(services);

//                float x = (float) 8.45;

               PrintRequestAttributeSet printRequestAttributeSet =
                       new HashPrintRequestAttributeSet();
//               
//               printRequestAttributeSet.add(MediaSize.findMedia(Float.parseFloat("3.139"), 
//                    Float.parseFloat("10.972"), MediaSize.INCH));

               
//               MediaSizeName mediaSizeName =
//                       MediaSize.findMedia(x,x,MediaPrintableArea.INCH);
//               printRequestAttributeSet.add(mediaSizeName);
               
               
               printRequestAttributeSet.add(new Copies(1));
               JRPrintServiceExporter exporter;
               exporter = new JRPrintServiceExporter();
               exporter.setParameter(JRExporterParameter.
                       JASPER_PRINT, impresion);
               /* We set the selected service and pass it as a paramenter */
               exporter.setParameter(JRPrintServiceExporterParameter.
                       PRINT_SERVICE, services);
               exporter.setParameter(JRPrintServiceExporterParameter.
                       PRINT_SERVICE_ATTRIBUTE_SET, services.getAttributes());
               exporter.setParameter(JRPrintServiceExporterParameter.
                       PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
               exporter.setParameter(JRPrintServiceExporterParameter.
                       DISPLAY_PAGE_DIALOG, Boolean.FALSE);
               exporter.setParameter(JRPrintServiceExporterParameter.
                       DISPLAY_PRINT_DIALOG, Boolean.FALSE);
               exporter.exportReport();
        } catch (NamingException ne) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
                    null, ne);
            throw new Exception("Error al accededer al DataSource "
                    + ne.getMessage());
        } catch (SQLException sqle) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
                    null, sqle);
            throw new Exception("SQL Exception " + sqle.getMessage());
        } catch (JRException ex) {
            Logger.getLogger(this.getClass().getName())
                    .log(Level.SEVERE, null, ex);
            throw new Exception("JRException " + ex.getMessage());
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName())
                    .log(Level.SEVERE, null, e);
            throw new Exception("Exception " + e.getMessage());
            
        } finally {
            connection.close();
        }
    }
    
    
    public void ImprimirFactura(String datSource, String url, String printer,
            Map parameters, BigInteger nocopias)
            throws IOException, NamingException,
            SQLException, Exception {
        Connection connection = null;
        String archJasper;
        URI uriObj = null;
        byte[] reporteByte = null;
        try {
            InitialContext initialContext = new InitialContext();
            DataSource ds = (DataSource) initialContext.lookup(datSource);
            connection = ds.getConnection();
            
            uriObj = getClass().getResource(url).toURI();
            File reporte = new File(uriObj);
            archJasper = reporte.getAbsolutePath();
            JasperPrint impresion = null;
            impresion = JasperFillManager
                    .fillReport(archJasper, parameters, connection);
            /* Create an array of PrintServices */
            PrinterJob job = PrinterJob.getPrinterJob();
            PrintService[] services = PrintServiceLookup
                    .lookupPrintServices(null, null);
            int selectedService = -1;
            
            

            for(int i = 0; i < services.length;i++) {
                if(services[i].getName()
                        .toUpperCase().equals(printer.toUpperCase())) {
                    /*If the service is named as what we are querying 
                      we select it */
                    selectedService = i;
                }
            }
            if (selectedService == -1) {
                throw new Exception("No existe la impresora configurada."
                        + " Nombre de la impresora " + printer);
            }
            
            // Opciones que funcionan
            job.setPrintService(services[selectedService]);
            
            PrintRequestAttributeSet printRequestAttributeSet
                    = new HashPrintRequestAttributeSet();
            
            printRequestAttributeSet.add(new Copies(nocopias.intValue()));
            JRPrintServiceExporter exporter;
            exporter = new JRPrintServiceExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT,
                    impresion);
            /* We set the selected service and pass it as a paramenter */
            exporter.setParameter(JRPrintServiceExporterParameter
                    .PRINT_SERVICE, services[selectedService]);
            exporter.setParameter(JRPrintServiceExporterParameter
                    .PRINT_SERVICE_ATTRIBUTE_SET,
                    services[selectedService].getAttributes());
            exporter.setParameter(JRPrintServiceExporterParameter.
                    PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
            exporter.setParameter(JRPrintServiceExporterParameter
                    .DISPLAY_PAGE_DIALOG, Boolean.FALSE);
            exporter.setParameter(JRPrintServiceExporterParameter
                    .DISPLAY_PRINT_DIALOG, Boolean.FALSE);
            exporter.exportReport();
             //JasperPrintManager.printReport(impresion, false);    
        } catch (NamingException ne) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
                    null, ne);
            throw new Exception("Error al accededer al DataSource "
                    + ne.getMessage());
        } catch (SQLException sqle) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
                    null, sqle);
            throw new Exception("SQL Exception " + sqle.getMessage());
        } catch (JRException ex) {
            Logger.getLogger(this.getClass().getName())
                    .log(Level.SEVERE, null, ex);
            throw new Exception("JRException " + ex.getMessage());
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName())
                    .log(Level.SEVERE, null, e);
            throw new Exception("Exception " + e.getMessage());
        } finally {
            connection.close();
        }
    }
    
    public void crearReporte(String datSource, String url, Map parameters,
            String rutaarchivo)
            throws IOException, NamingException,
            SQLException, Exception {
        Connection connection = null;
        String archJasper;
        URI uriObj = null;
        FileWriter file = null;
        try {
            InitialContext initialContext = new InitialContext();
            DataSource ds = (DataSource) initialContext.lookup(datSource);
            connection = ds.getConnection();
            uriObj = getClass().getResource(url).toURI();
            File reporte = new File(uriObj);
            archJasper = reporte.getAbsolutePath();
            
            file = new FileWriter(rutaarchivo, true);

//                    JasperReport reporte = (JasperReport) JRLoader.loadObject(
//                        report);
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    archJasper, parameters, connection);
            JRExporter exporter = new JRTextExporter();
            // Para los reportes en texto debemos expresar el tamano de
            // la pagina
            exporter.setParameter(JRTextExporterParameter.PAGE_HEIGHT,
                    new Integer(120).floatValue());
            exporter.setParameter(JRTextExporterParameter.PAGE_WIDTH,
                    new Integer(140).floatValue());
            exporter.setParameter(JRTextExporterParameter.CHARACTER_WIDTH,
                    new Integer(5).floatValue());
            exporter.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT,
                    new Integer(6).floatValue());
            exporter.setParameter(JRExporterParameter.JASPER_PRINT,
                    jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_WRITER,
                    file);
            exporter.exportReport();
             //JasperPrintManager.printReport(impresion, false);    
        } catch (NamingException ne) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
                    null, ne);
            throw new Exception("Error al accededer al DataSource "
                    + ne.getMessage());
        } catch (SQLException sqle) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
                    null, sqle);
            throw new Exception("SQL Exception " + sqle.getMessage());
        } catch (JRException ex) {
            Logger.getLogger(this.getClass().getName())
                    .log(Level.SEVERE, null, ex);
            throw new Exception("JRException " + ex.getMessage());
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName())
                    .log(Level.SEVERE, null, e);
            throw new Exception("Exception " + e.getMessage());
        } finally {
            connection.close();
        }
    }
    
    
    
      public static void imprimir(JasperPrint jasperPrint) throws JRException {
        PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
        PrintService impresoraPredeterminada = PrintServiceLookup.lookupDefaultPrintService();

        // Los mÃ©todos getPageWidth y getPageHeight devuelven
        // en pixeles, de forma que hay que transformar
        // dividiendo x 72
        float w = jasperPrint.getPageWidth() / 72f;
        float h = jasperPrint.getPageHeight() / 72f;
        
        // Busco el tamaÃ±o de papel de la impresora mÃ¡s parecido
        printRequestAttributeSet.add(MediaSize.findMedia(80, 197, MediaSize.MM));

        // Configuro el area de impresiÃ³n
        int unidad = MediaPrintableArea.MM;
        printRequestAttributeSet.add(new MediaPrintableArea(0, 0, 80, 197, unidad));

        // OrientaciÃ³n
        OrientationRequested orientation = OrientationRequested.PORTRAIT;
        //if (jasperPrint.getOrientationValue() == jasperPrint. .OrientationRequested.LANDSCAPE) {
          //  orientation = OrientationRequested.LANDSCAPE;
        //}
        printRequestAttributeSet.add(orientation);
        PrintService service = impresoraPredeterminada;

        JRExporter exporter = new JRPrintServiceExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        
        exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
        exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, service);
        exporter.exportReport();
    }

    
}
