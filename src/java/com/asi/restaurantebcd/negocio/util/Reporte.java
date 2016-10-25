/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.util;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Reporte extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> 
     * and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        JasperRpt imprpt = new JasperRpt();
        byte[] reporteByte;

        String ds = (String) request.getSession().getAttribute("datasourse");
        String url = (String) request.getSession().getAttribute("url");
        String format = (String) request.getSession().getAttribute("format");
        Map parameters = (Map) request.getSession().getAttribute("parameters");
        Map pJREP = (Map) request.getSession()
                .getAttribute("JRExporterParameters");
        Map pJRXLSEP = (Map) request.getSession()
                .getAttribute("JRXlsExporterParameters");
        Map pJRXLSAEP = (Map) request.getSession()
                .getAttribute("JRXlsAbstractExporterParameters");
         if (ds == null || ds.equals("")){
             PrintWriter out = response.getWriter();
             out.println("<h2>El parametro del Data Source no es valida</h2>");
             out.close();
             return;
         }

        if (url == null || url.equals("")){
            PrintWriter out = response.getWriter();
            out.println("<h2>El parametro URL del reporte no es valida </h2>");
            out.close();
            return;
        }

        if (format == null || format.equals("")){
            PrintWriter out = response.getWriter();
            out.println("<h2>El formato del reporte no fue especificado</h2>");
            out.close();
            return;
        }

        if (parameters == null){
            PrintWriter out = response.getWriter();
            out.println("<h2>Los parametro del reporte no son validos </h2>");
            out.close();
            return;
        }
        try {
            if (format.equals("PDF")) {
                reporteByte = imprpt.ImprimeReportePDF(ds, url, parameters);
                response.setContentType("application/pdf");
                response.setContentLength(reporteByte.length);
                response.getOutputStream().write(reporteByte);
                response.getOutputStream().flush();
                response.getOutputStream().close();
            } else {
                String rptxls = "";

                if ((pJREP != null && !pJREP.isEmpty())
                    || (pJRXLSEP != null && !pJRXLSEP.isEmpty())
                    || (pJRXLSAEP != null && !pJRXLSAEP.isEmpty())) {
                  rptxls = imprpt.imprimeReporteXLS(
                           ds, url, parameters, pJREP, pJRXLSEP, pJRXLSAEP);
                } else {
                  rptxls = imprpt.ImprimeReporteXLS(ds, url, parameters);
                }
                response.reset();
                response.setHeader("Content-Disposition",
                        "attachment; filename=\"" + "rpt.xls" + "\"");
                InputStream in = null;
                ServletOutputStream bufferSalida = null;
                in = new FileInputStream(rptxls);
                bufferSalida = response.getOutputStream();
                //se transfieren los bytes
                byte[] buf = new byte[256];//256
                int len;
                while ((len = in.read(buf)) > 0) {
                    bufferSalida.write(buf, 0, len);
                }
                //vaciamos el buffer de salida y se envia el resultado
                in.close();
                bufferSalida.flush();
                bufferSalida.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(Reporte.class.getName())
                    .log(Level.SEVERE, null, ex);
            PrintWriter out = response.getWriter();
            out.println("<H2> Error: " + ex.getMessage() + "</H2>");
            out.close();
        }
    }
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }


}









