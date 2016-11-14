/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.recursos.webservices;

import com.asi.restaurantbcd.modelo.Vwproductos;
import com.asi.restaurantebcd.negocio.base.BusquedasProductosLocal;
import com.asi.restaurantebcd.negocio.util.ReponseWs;
import com.asi.restaurantebcd.negocio.util.WsException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author samaelopez
 */
@Path("/VentasWS")
public class VentasWS {

    BusquedasProductosLocal busquedasProductos = lookupBusquedasProductosLocal();
 


    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Ventas
     */
    public VentasWS() {
    }

    /**
     * Retrieves representation of an instance of com.asi.restaurantebcd.recursos.webservices.VentasWS
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        System.out.println("jhoads");
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of VentasWS
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
         System.out.println("jhoads");
         System.out.println(content);
    }
    
    
    
        
    @POST
    @Path("/obtenerProductos")
    public String obtenerProductos(@HeaderParam("autorizacion")
                                    String auth, String json) throws Exception {
        ReponseWs resp;
        try {
            System.out.println("entro.......");
//            if (auth == null || auth.equals("")) {
//                throw new Exception("No vienen datos para autorizar acceso");
//            }
//            resp = lookupProcesosSeg().validarAccesoSinTokenIntWS(auth);
//            if (resp != null && resp.getError().equals("1")) {
//                throw new Exception(resp.getDescripcion());
//            }
            Map filtros =  new Gson().fromJson(json, HashMap.class);
            List <Vwproductos > lstProduc = 
                    busquedasProductos.buscarProducto(filtros);
            if (lstProduc == null || lstProduc.isEmpty()) {
                throw new Exception("No se encontraron resultados.");
            }
            String jsonRetu = 
                    new Gson().toJson(lstProduc,
                            new TypeToken<ArrayList<Vwproductos>>() {}.getType());
            resp = new ReponseWs();
            resp.setContent(jsonRetu);
            resp.setDescripcion("-");
            resp.setError("0");
            resp.setStatus(Response.Status.OK.getStatusCode());
            
            return new Gson().toJson(resp);
            
        } catch (Exception e) {
            Logger.getLogger(VentasWS.class.getName()).log(Level.SEVERE, null, e);
            throw new WsException(e.getMessage());
        }
    }

    private BusquedasProductosLocal lookupBusquedasProductosLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (BusquedasProductosLocal) c.lookup("java:global/RestaurantBDC/BusquedasProductos!com.asi.restaurantebcd.negocio.base.BusquedasProductosLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }


    }
    