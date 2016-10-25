/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.util;
//////


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;





public class JasperRpt {
    public String ImprimeReporteXLS(String datSource, String url,
            Map parameters) throws IOException, NamingException,
            SQLException, Exception {
        Connection connection = null;
        String archJasper;
        URI uriObj = null;
        try {
            InitialContext initialContext = new InitialContext();
            DataSource ds = (DataSource) initialContext.lookup(datSource);
            connection = ds.getConnection();
            uriObj = getClass().getResource(url).toURI();
            File reporte = new File(uriObj);
            archJasper = reporte.getAbsolutePath();
            JasperPrint jasperPrint = null;

            jasperPrint = JasperFillManager
                    .fillReport(archJasper, parameters, connection);
            String xlsPath = archJasper;
            String xlsFileName = "";
            xlsFileName = "rpt.xls";

            JRXlsExporter exporter = new JRXlsExporter();
         exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
         exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
                 xlsPath + xlsFileName);
         exporter.setParameter(JRExporterParameter.IGNORE_PAGE_MARGINS, true);
         //exporter.setParameter(JRXlsAbstractExporterParameter
         //.IS_WHITE_PAGE_BACKGROUND, false);
         exporter.setParameter(JRXlsAbstractExporterParameter
                 .IS_IGNORE_CELL_BORDER, false);
         exporter.setParameter(JRXlsAbstractExporterParameter
                 .IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,true);
         exporter.setParameter(JRXlsAbstractExporterParameter
                 .IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,true);
         exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,true);
         exporter.exportReport();
         connection.close();
         return xlsPath + xlsFileName;
        } catch (NamingException ne) {
            Logger.getLogger(JasperRpt.class.getName()).log(Level.SEVERE,
                    null, ne);
            ne.printStackTrace();
            throw new Exception("Error al accededer al DataSource "
                    + ne.getMessage());
        } catch (SQLException sqle) {
            Logger.getLogger(JasperRpt.class.getName()).log(Level.SEVERE,
                    null, sqle);
            sqle.printStackTrace();
            throw new Exception("SQL Exception " + sqle.getMessage());
        } catch (JRException ex) {
            Logger.getLogger(JasperRpt.class.getName())
                    .log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            throw new Exception("JRException " + ex.getMessage());
        } catch (Exception e) {
            Logger.getLogger(JasperRpt.class.getName())
                    .log(Level.SEVERE, null, e);
            e.printStackTrace();
            throw new Exception("Exception " + e.getMessage());
        } finally {
            connection.close();
        }
    }

     /**
     * Metodo que ejecuta un reporte y lo exporta en pdf.
     * @param datSource String nombre del DataSource.
     * @param url Direccion uri donde esta el jsaper.
     * @param parameters map de los parametros del reporte.
     * @return 
     * @throws java.io.IOException 
     * @throws javax.naming.NamingException 
     * @throws java.sql.SQLException 
     * @throws java.lang.Exception error general.
     */
    
    public byte[] ImprimeReportePDF(String datSource, String url,
            Map parameters) throws IOException, NamingException,
            SQLException, Exception {
        Connection connection = null;
        String archJasper;
        URI uriObj;
        byte[] reporteByte = null;
        try {
            InitialContext initialContext = new InitialContext();
            DataSource ds = (DataSource) initialContext.lookup(datSource);
            connection = ds.getConnection();
            uriObj = getClass().getResource(url).toURI();
            File reporte = new File(uriObj);
            archJasper = reporte.getAbsolutePath();
            JasperPrint impresion;
            JRExporter exporter = new JRPdfExporter();
            ByteArrayOutputStream reportePDF = new ByteArrayOutputStream();
            reporteByte = JasperRunManager.runReportToPdf(reporte.getPath(),
                    parameters, connection);
            impresion =
               JasperFillManager.fillReport(archJasper, parameters, connection);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, impresion);
           exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, reportePDF);
           connection.close();
        } catch (NamingException ne) {
            Logger.getLogger(JasperRpt.class.getName()).log(Level.SEVERE,
                    null, ne);
            throw new Exception("Error al accededer al DataSource "
                    + ne.getMessage());
        } catch (SQLException sqle) {
            Logger.getLogger(JasperRpt.class.getName()).log(Level.SEVERE,
                    null, sqle);
            throw new Exception("SQL Exception " + sqle.getMessage());
        } catch (JRException ex) {
            Logger.getLogger(JasperRpt.class.getName())
                    .log(Level.SEVERE, null, ex);
            throw new Exception("JRException " + ex.getMessage());
        } catch (URISyntaxException e) {
            Logger.getLogger(JasperRpt.class.getName())
                    .log(Level.SEVERE, null, e);
          
            throw new Exception("Exception " + e.getMessage());
        } finally {
            if (connection != null) {
            connection.close();
            }
        }
        return reporteByte;
    }


        /**
     * MÃ©todo que permite imprimir un reporte en formato para excel.
     * @param dSource nombre del DataSource.
     * @param url direcciÃ³n donde se encuentra el reporte.
     * @param params parÃ¡metros para el reporte.
     * @param pJREP parÃ¡metros de tipo JRExporterParameter.
     * @param pJRXLSEP parÃ¡metros de tipo JRXlsExporterParameter.
     * @param pJRXLSAEP parÃ¡metros de tipo JRXlsAbstractExporterParameter.
     * @return String. Ruta de archivo donde se genera el reporte.
     * @throws java.lang.Exception Error genÃ©rico.
     */
    public final String imprimeReporteXLS(final String dSource,
           final String url, final Map params,
           final Map < JRExporterParameter, Object > pJREP,
           final Map < JRXlsExporterParameter, Object > pJRXLSEP,
           final Map < JRXlsAbstractExporterParameter, Object > pJRXLSAEP)
           throws Exception {
        Connection connection = null;
        String archJasper;
        URI uriObj;
        try {
          InitialContext initialContext = new InitialContext();
          DataSource ds = (DataSource) initialContext.lookup(dSource);
          connection = ds.getConnection();
          uriObj = getClass().getResource(url).toURI();
          File reporte = new File(uriObj);
          archJasper = reporte.getAbsolutePath();
          JasperPrint jasperPrint = JasperFillManager.fillReport(
                                    archJasper, params, connection);
          String xlsPath = archJasper;
          String xlsFileName = "reporte.xls";
          JRXlsExporter exporter = new JRXlsExporter();
          exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
          exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
                                xlsPath + xlsFileName);
          if (pJREP != null && !pJREP.isEmpty()) {
              pJREP.entrySet().stream().forEach((data) -> {
                  exporter.setParameter(data.getKey(), data.getValue());
              });
          }
          if (pJRXLSEP != null && !pJRXLSEP.isEmpty()) {
              pJRXLSEP.entrySet().stream().forEach((data) -> {
                  exporter.setParameter(data.getKey(), data.getValue());
              });
          }
          if (pJRXLSAEP != null && !pJRXLSAEP.isEmpty()) {
              pJRXLSAEP.entrySet().stream().forEach((data) -> {
                  exporter.setParameter(data.getKey(), data.getValue());
              });
          }
          exporter.exportReport();
          connection.close();
          return xlsPath + xlsFileName;
        } catch (NamingException | SQLException | URISyntaxException | JRException e) {
          throw e;
        } finally {
          if (connection != null) {
              connection.close();
          }
        }
    }
    
}
