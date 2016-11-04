/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Empleado;
import com.asi.restaurantbcd.modelo.Perfil;
import com.asi.restaurantbcd.modelo.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author "Joaquin Sanchez SA101110"
 */
@Local
public interface BusquedasUsuariosLocal {
    public List <Usuario> buscarUsuario() throws Exception;
    public List <Perfil> buscarPerfil() throws Exception;
    public List <Empleado> buscarEmpleado() throws Exception;
}
