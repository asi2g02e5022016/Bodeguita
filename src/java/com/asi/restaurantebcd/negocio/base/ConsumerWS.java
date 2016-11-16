 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Cliente;
import com.asi.restaurantebcd.negocio.util.ReponseWs;
import static com.asi.restaurantebcd.negocio.util.Utilidades.getParemetro;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.net.ConnectException;

import java.net.NoRouteToHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author samaelopez
 */
@Stateless
public class ConsumerWS implements ConsumerWSLocal {

    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em;

public String consumirWebservices(String usr, String jsonDatos,String URLBase) throws Exception {
      String respuesta = null;
        if (URLBase == null) {
            throw new Exception("La url base es obligatorio.");
        }   
              SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String fechaAhora    = format.format(new Date());
        Date forFechaAhora   = format.parse(fechaAhora);
        Calendar calFechaAhora = Calendar.getInstance();
        calFechaAhora.setTime(forFechaAhora);
        Calendar calFechaAntes = Calendar.getInstance();
        calFechaAntes.setTime(forFechaAhora);
        calFechaAntes.add(Calendar.HOUR, -1);
        fechaAhora    = format.format(calFechaAhora.getTime());
        String token = crearTokenWebservice(usr,fechaAhora );
        Map mapHeader = new HashMap();
        mapHeader.put("User", usr);
        mapHeader.put("fecha", new Date().toString());
        mapHeader.put("Token", token);
        mapHeader.put("json", jsonDatos);
        try {
            String jsonHeader = new Gson().toJson(mapHeader);
            System.out.println("jsonHeader.. " +jsonHeader);
            Client client = Client.create();
            WebResource webResource = client.resource(URLBase);
            WebResource.Builder buildws;
            buildws = webResource
                    .header("autorizacion", jsonHeader);
            System.out.println("buildws..." +buildws);
            ClientResponse response;
            response = buildws.get(ClientResponse.class);
            System.out.println("response.." +response);
            String rs = response.getEntity(String.class);
               Gson json = new Gson();
               System.out.println("rs,,,,." +rs);
        ReponseWs ws = json.fromJson(rs, ReponseWs.class);
            if (ws != null && ws.getError().equals("1")) {
                throw new Exception("Peidos Ws: " +ws.getDescripcion());
            }
            if (ws != null && ws.getContent() != null) {
                respuesta = ws.getContent();
            }
            return respuesta;
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof ConnectException
                    || e instanceof ClientHandlerException
                    || e instanceof NoRouteToHostException) {
                throw new Exception("No se pudo establecer comunicacion con servicios web,"
                        + " comuniquese con el administrador");
            }
            throw new Exception(e.getMessage());
        }
    }

 /**
  * 
  * @param auth
  * @return
  * @throws Exception 
  */
 public ReponseWs validarAccesoSinTokenIntWS(String auth) throws Exception {
        StringBuilder str = new StringBuilder();
        if (auth == null || auth.equals("")) {
            str.append("No se puede acceder al recurso solicitado.");
            str.append("El usuario no cuenta con las credenciales.");
            throw new Exception(str.toString());
        }
        Gson json = new Gson();
        HashMap autorizmap;
        try {
            autorizmap = json.fromJson(auth, HashMap.class);
        } catch (Exception e) {
            str.append("Error al convertir los parametros");
            str.append("de Authorization.");
            str.append(e.getMessage());
            throw new Exception(str.toString());
        }
        String fecha = getParemetro("fecha", autorizmap );
        String user =  getParemetro("User", autorizmap );
        String token = getParemetro("Token", autorizmap );
        if (user == null || user.equals("")) {
            str.append("No existe usuario de autenticacion.");
            throw new Exception(str.toString());
        }
        if (token == null || token.equals("")) {
            str.append("No existe token en la peticion.");
            throw new Exception(str.toString());
        }
        String tokenPOS = token;
        Cliente usrVali = em.find(Cliente.class, null);
        if (usrVali == null) {
            throw new Exception("El cliente no es valdo  " );
        }
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String fechaAhora    = format.format(new Date());
        Date forFechaAhora   = format.parse(fechaAhora);
        Calendar calFechaAhora = Calendar.getInstance();
        calFechaAhora.setTime(forFechaAhora);
        Calendar calFechaAntes = Calendar.getInstance();
        calFechaAntes.setTime(forFechaAhora);
        calFechaAntes.add(Calendar.HOUR, -1);
        fechaAhora    = format.format(calFechaAhora.getTime());
        String fechaAnterior = format.format(calFechaAntes.getTime());
        String TokenAhora = crearTokenWebservice(user, fechaAhora);
        String TokenAntes = crearTokenWebservice(user, fechaAnterior);
        List < String > lstHash = new ArrayList();
        lstHash.add(TokenAhora);
        lstHash.add(TokenAntes);
        if (!lstHash.contains(tokenPOS)) {
           throw new Exception("El token no es invalido.");
        }
        ReponseWs resp = new ReponseWs();
        resp.setStatus(200);
        return resp;
    }

 private String crearTokenWebservice(String usr, String fecha) {
    String valor  = usr +  fecha;
     String token = String.valueOf(valor.hashCode());
     return token;
     
 }
}
