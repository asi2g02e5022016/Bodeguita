/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Tipoproducto;
import java.util.List;
import javax.ejb.Local;


/**
 *
 * @author hp
 * aqui dice interfaz
 * solo definis el nombre , parametros, que retornara el metodo
 * suimplementacion de tod su logica esta en la clase
 * okk
 * entonces esta es laque tenes que llamar
 * pero te enselñare un metodo mas faceil
 * 
 */
@Local
public interface BusquedasTipoProductoLocal {
   /**
     * Obtener el ultimo correlativo de estado.
     * @return LCorrelativo Siguinte.
     * @throws Exception Error generico.
     */   
     Integer obtenerCorreltivoTipoProducto() throws Exception;
       /**
     * Obtiene la lista de estado documentos.
     * @return List Estado documento.
     * @throws Exception Error generico.
     */
     List <Tipoproducto> buscarTipoProducto() throws Exception;   
    
    
}

