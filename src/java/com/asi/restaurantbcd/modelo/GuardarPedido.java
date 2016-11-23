/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantbcd.modelo;

import com.asi.restaurantebcd.negocio.base.PedidoOnlineLocal;
import com.asi.restaurantebcd.negocio.util.ReponseWs;
import com.asi.restaurantebcd.negocio.util.WsException;
import com.asi.restaurantebcd.recursos.webservices.VentasWS;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author samaelopez
 */
@Path("/GuardarPedido")
public class GuardarPedido {

    PedidoOnlineLocal pedidoOnline = lookupPedidoOnlineLocal();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GuardarPedido
     */
    public GuardarPedido() {
    }

 @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@HeaderParam("autorizacion")
                                    String auth) throws WsException {
 ReponseWs resp;
        try {
            System.out.println("entro......."+auth);
//            if (auth == null || auth.equals("")) {
//                throw new Exception("No vienen datos para autorizar acceso");
//            }
//            resp = lookupProcesosSeg().validarAccesoSinTokenIntWS(auth);
//            if (resp != null && resp.getError().equals("1")) {
//                throw new Exception(resp.getDescripcion());
//            }
        
            Map hast =  new Gson().fromJson(auth, HashMap.class);
            String json = getParemetro("json", hast);
            OrdenpedidoDTO pedi = new Gson().fromJson(json, OrdenpedidoDTO.class);
            System.out.println("pedi" +pedi);
            if (pedi ==  null) {
                throw new Exception("El pedido es obligatorio.");
            }
            if (pedi.getOrdenpedidodetalleList() == null) {
             throw new Exception(" La lista de pedido es obligatorio. ");
            }
            
            pedidoOnline.guardarPedidoOnline(
                        pedi.getIdcliente(), "lportillo", 
                        pedi.getSucursal(),
                        pedi.getOrdenpedidodetalleList());
             
            resp = new ReponseWs();
            resp.setContent("se guardo exitosamente.");
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
     * PUT method for updating or creating an instance of GuardarPedido
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
        public static < T> T getParemetro(Object object, Map filtros) {
        if (filtros == null || object == null) {
            return null;
        }
        return (T) filtros.get(object);
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
