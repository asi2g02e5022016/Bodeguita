/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.recursos.webservices;

import com.asi.restaurantbcd.modelo.Ordenpedido;
import com.asi.restaurantbcd.modelo.Sucursal;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import com.asi.restaurantebcd.negocio.base.PedidoOnlineLocal;
import com.asi.restaurantebcd.negocio.util.ReponseWs;
import com.asi.restaurantebcd.negocio.util.WsException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author samaelopez
 */
@Path("/ConsultaPedidosWS")
public class ConsulPedidos {
    
    PedidoOnlineLocal pedidoOnline = lookupPedidoOnlineLocal();

    CrudBDCLocal crudBDC = lookupCrudBDCLocal();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UsuarioWS
     */
    public ConsulPedidos() {
    }

    /**
     * Retrieves representation of an instance of com.asi.restaurantebcd.recursos.webservices.UsuarioWS
     * @param auth
     * @return an instance of java.lang.String
     * @throws com.asi.restaurantebcd.negocio.util.WsException
     */
     @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@HeaderParam("autorizacion")
                                    String auth) throws WsException {
 ReponseWs resp;
        try {
    
            List <Ordenpedido> lstOrdenes =  lookupPedidoOnlineLocal().lstPedido();
            System.out.println("lstOrdenes..." +lstOrdenes);
            if (lstOrdenes == null || lstOrdenes.isEmpty()) {
                throw new Exception("No se encontraron resultados.");
            }
            System.out.println("pasooo");
            System.out.println("lstOrdenes.get(0).. " +lstOrdenes.get(0));
             String jsonReturn = new Gson().toJson(lstOrdenes.get(0));
             System.out.println("jsonReturn.. " +jsonReturn);
               System.out.println("pasoo8888888888888888888888888o");
            resp = new ReponseWs();
            resp.setContent(jsonReturn);
            resp.setDescripcion("-");
            resp.setError("0");
            resp.setStatus(Response.Status.OK.getStatusCode());
            return new Gson().toJson(resp);
            
        } catch (Exception e) {
            Logger.getLogger(VentasWS.class.getName())
                    .log(Level.SEVERE, null, e);
            throw new WsException(e.getMessage());
        }
    }


    /**
     * PUT method for updating or creating an instance of UsuarioWS
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    private CrudBDCLocal lookupCrudBDCLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (CrudBDCLocal) c.lookup("java:global/RestaurantBDC/CrudBDC!com.asi.restaurantebcd.negocio.base.CrudBDCLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private PedidoOnlineLocal lookupPedidoOnlineLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (PedidoOnlineLocal) c.lookup("java:global/RestaurantBDC/PedidoOnline!com.asi.restaurantebcd.negocio.base.PedidoOnlineLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    
    
}
