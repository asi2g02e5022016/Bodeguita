package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Vexistxsucsal;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author PROGRAMADOR
 */
@Local
public interface VBusquedasExistenciasLocal {       
     
         /**
     * 
     * @param filtro
     * @return
     * @throws Exception 
     */
    public List <Vexistxsucsal> buscarExistenciaFiltros(Map filtro) 
                throws Exception;
}
