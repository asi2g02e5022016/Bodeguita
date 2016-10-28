package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Vwexistencias;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author PROGRAMADOR
 */
@Local
public interface BusquedasExistenciasLocal {       
     
         /**
     * 
     * @param filtro
     * @return
     * @throws Exception 
     */
    public List <Vwexistencias> buscarExistencias(Map filtro) 
                throws Exception;
}
