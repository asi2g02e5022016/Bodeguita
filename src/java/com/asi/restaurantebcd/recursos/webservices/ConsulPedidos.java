/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.recursos.webservices;

import static com.asi.restaurantbcd.modelo.GuardarPedido.getParemetro;
import com.asi.restaurantbcd.modelo.Ordenpedido;
import com.asi.restaurantbcd.modelo.OrdenpedidoDTO;
import com.asi.restaurantbcd.modelo.Ordenpedidodetalle;
import com.asi.restaurantbcd.modelo.OrdenpedidodetalleDTO;
import com.asi.restaurantbcd.modelo.Sucursal;
import com.asi.restaurantebcd.negocio.base.BusquedaPedidoLocal;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import com.asi.restaurantebcd.negocio.base.PedidoOnlineLocal;
import com.asi.restaurantebcd.negocio.util.ReponseWs;
import com.asi.restaurantebcd.negocio.util.Utilidades;
import com.asi.restaurantebcd.negocio.util.WsException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    BusquedaPedidoLocal busquedaPedido = lookupBusquedaPedidoLocal();
    
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
             Utilidades uti = new Utilidades();
            Map hast =  new Gson().fromJson(auth, HashMap.class);
            String json = getParemetro("json", hast);
             Map params =  new Gson().fromJson(json, HashMap.class);
             String idSuc  = getParemetro("idsucursal", params);
            Date fechaInicial  = getParemetro("fechaInicial", params);
             Date fechaFinal  = getParemetro("fechaFinal", params);
            Sucursal suc = new Sucursal();

            suc.setIdsucursal(Integer.parseInt(idSuc));
            fechaFinal = uti.getFiltroDeFecha(fechaFinal, 1);
            List <Ordenpedido> lstOrde = busquedaPedido.buscarOrdenPedido(suc, fechaInicial, fechaFinal);
            System.out.println("lstOrdenes..." +lstOrde);
            if (lstOrde == null || lstOrde.isEmpty()) {
                throw new Exception("No se encontraron resultados.");
            }
            List <OrdenpedidoDTO> lstDto  = new ArrayList<>();
            for (Ordenpedido ordenpedido : lstOrde) {
                OrdenpedidoDTO odT  = new OrdenpedidoDTO();
                odT.setFechapedido(ordenpedido.getFechapedido());
//                odT.setIdcliente(ordenpedido.getIdcliente());
//                odT.setIdestado(ordenpedido.getIdestado());
//                odT.setIdusuario(ordenpedido.getIdusuario());
                odT.setMesa(ordenpedido.getMesa());
                odT.setOrdenpedidoPK(ordenpedido.getOrdenpedidoPK());
//                odT.setSucursal(ordenpedido.getSucursal());
                List<OrdenpedidodetalleDTO> lstDetaDTO = new ArrayList<>();
                if (ordenpedido.getOrdenpedidodetalleList() != null 
                        && !ordenpedido.getOrdenpedidodetalleList().isEmpty()) {
                for (Ordenpedidodetalle  cor :
                        ordenpedido.getOrdenpedidodetalleList()) {
                    OrdenpedidodetalleDTO detOt =  new OrdenpedidodetalleDTO();
                    detOt.setCantidadconfirmada(cor.getCantidadconfirmada());
                    detOt.setCantidadsolicitada(cor.getCantidadsolicitada());
                    detOt.setCosto(cor.getCosto());
//                    detOt.setIdproducto(cor.getIdproducto());
                    detOt.setIva(cor.getIva());
                    detOt.setOrdenpedidodetallePK(cor.getOrdenpedidodetallePK());
                    detOt.setPrecio(cor.getPrecio());
                    lstDetaDTO.add(detOt);
                 }
                }
                odT.setOrdenpedidodetalleList(lstDetaDTO);
                
                lstDto.add(odT);
            }
            System.out.println("pasooo" +lstDto);
            String jsonReturn = new Gson().toJson(lstDto,
                            new TypeToken<ArrayList<OrdenpedidoDTO>>() {}.getType());
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

    private BusquedaPedidoLocal lookupBusquedaPedidoLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (BusquedaPedidoLocal) c.lookup("java:global/RestaurantBDC/BusquedaPedido!com.asi.restaurantebcd.negocio.base.BusquedaPedidoLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
            public static < T> T getParemetro(Object object, Map filtros) {
        if (filtros == null || object == null) {
            return null;
        }
        return (T) filtros.get(object);
    } 
    
}
