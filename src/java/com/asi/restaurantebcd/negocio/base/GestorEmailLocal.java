package com.asi.restaurantebcd.negocio.base;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author samaelopez
 */
@Local
public interface GestorEmailLocal {
      /**
     * Crea y envia email con recursos y plantillas del web.
     *
     * @param contenido Cuerpo de mail.
     * @param asunto asunto del correo.
     * @param nombre nombre del usurio de correo.
     * @param lstMails lista de email de destinatarios.
     * @param pheader encabezado.
     * @throws Exception error generico.
     */

    public void enviarEmail(String contenido, String asunto,
            String nombre, List lstMails, String pheader) throws Exception ;
    
}
