package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Vexistxsucsal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author PROGRAMADOR
 */
@Local
public interface VBusquedasExistenciasLocal {       
     List <Vexistxsucsal> buscarExistencia() throws Exception;
     List <Vexistxsucsal> buscarExistenciaxSucsal(String _sucsal) throws Exception;
     List <Vexistxsucsal> buscarExistenciaxProducto(String _producto) throws Exception;
}
