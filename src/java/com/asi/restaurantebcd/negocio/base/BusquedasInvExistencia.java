package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Existencia;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author SANCHEZ
 */
@Stateless
public class BusquedasInvExistencia implements BusquedasInvExistenciaLocal {

    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em; 
    
       
/**
 * Obtener el ultimo correlativo de existencias.
 * @return LCorrelativo Siguinte.
 * @throws Exception Error generico.
*/       
    @Override
    public Integer obtenerCorrelativoInvExistencia() throws Exception {
        Integer valor;
        StringBuilder slq = new StringBuilder();
        slq.append("SELECT MAX(idEstado) FROM Existencia ");
        Query query = em.createNativeQuery(slq.toString());
        valor = (Integer) query.getSingleResult();
        if (valor == null) {
            valor = Integer.parseInt("0");
        }
        valor   =  valor + 1;
        return valor;
    }
    
/**
 * Obtener la lista de existencias.
 * @return LCorrelativo Siguinte.
 * @throws Exception Error generico.
*/
    @Override
    public List <Existencia> buscarInvExistencia() throws Exception {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT a FROM Existencia a ");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }
}
