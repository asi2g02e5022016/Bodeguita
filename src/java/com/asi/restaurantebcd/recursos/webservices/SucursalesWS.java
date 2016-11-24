/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.recursos.webservices;

import com.asi.restaurantbcd.modelo.Impuesto;
import com.asi.restaurantbcd.modelo.Sucursal;
import com.asi.restaurantebcd.negocio.base.BusquedasSucursalLocal;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
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
@Path("/SucursalWS")
public class SucursalesWS {

    CrudBDCLocal crudBDC = lookupCrudBDCLocal();

    BusquedasSucursalLocal busquedasSucursal = lookupBusquedasSucursalLocal();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SucursalesWS
     */
    public SucursalesWS() {
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


            List <Sucursal > lstProduc = 
                    busquedasSucursal.buscarSucursal();
            if (lstProduc == null || lstProduc.isEmpty()) {
                throw new Exception("No se encontraron resultados.");
            }
            for (Sucursal sucursal : lstProduc) {
                Impuesto imp = lookupCrudBDCLocal()
                        .buscarEntidad(Impuesto.class, Integer.parseInt("1"));
                if (imp == null) {
                    throw new Exception("No exist impuesto de iva");
                }
                sucursal.setIva(imp.getPorcentaje());
            }
            String jsonRetu = 
                    new Gson().toJson(lstProduc,
                            new TypeToken<ArrayList<Sucursal>>() {}.getType());
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
    
    /**
     * PUT method for updating or creating an instance of SucursalesWS
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    private BusquedasSucursalLocal lookupBusquedasSucursalLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (BusquedasSucursalLocal) c.lookup("java:global/RestaurantBDC/BusquedasSucursal!com.asi.restaurantebcd.negocio.base.BusquedasSucursalLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
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
    
    
    
    
}
