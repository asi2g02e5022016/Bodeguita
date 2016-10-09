/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.seguridad;

import com.asi.restaurantebcd.negocio.util.Utilidades;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author siman
 */
@WebFilter
public class LoginFilter implements Filter {
    
    @Inject
    SessionUsr sessionUsr;
    
     @Override
     public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Get the loginBean from session attribute
         HttpServletRequest req = (HttpServletRequest) request;
        String requestURI;
         requestURI = req.getRequestURI();

         System.out.println("sessionUsr: " + sessionUsr);
         System.out.println("Token: " + sessionUsr.getToken());
         System.out.println("Empleado: " + sessionUsr.getEmpleSucursal());
        // For the first application request there is no loginBean in the session so user needs to log in
        // For other requests loginBean is present but we need to check if user has logged in successfully
        if (sessionUsr == null || sessionUsr.getToken()==null || sessionUsr.getToken().equals("")) {
            if(!requestURI.endsWith("Loggin.xhtml") && !requestURI.endsWith("home.xhtml")){
                String contextPath = ((HttpServletRequest)request).getContextPath();
                ((HttpServletResponse)response).sendRedirect(contextPath + "/Loggin.xhtml");
            }
        }
         
        chain.doFilter(request, response);
             
    }
 
     @Override
    public void init(FilterConfig config) throws ServletException {
        // Nothing to do here!
    }
 
     @Override
    public void destroy() {
        // Nothing to do here!
    }   

}
