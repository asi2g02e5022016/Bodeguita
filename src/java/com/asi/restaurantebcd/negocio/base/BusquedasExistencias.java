package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Vwexistencias;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author PROGRAMADOR
 */
@Stateless
public class BusquedasExistencias implements BusquedasExistenciasLocal {

    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em;

    /**
     *
     * @param filtro
     * @return
     * @throws Exception
     */
    @Override
    public List<Vwexistencias> buscarExistenciaFiltros(Map filtro)
            throws Exception {
        if (filtro == null) {
            return null;
        }
        StringBuilder jpql = new StringBuilder();
        try {
            Integer codsuc = (Integer) filtro.get("codsuc");
            Integer codprod = (Integer) filtro.get("codprod");
            
            System.out.println(codsuc);
            System.out.println(codprod);
            
            //String tipo = (String) filtro.get("tipo");
            jpql.append("SELECT a FROM Vexistxsucsal a where 1 = 1");
            if (codsuc != null) {
                jpql.append(" AND a.idsucursal = :codsuc");
            }
            if (codprod != null) {
                jpql.append(" AND a.idproducto = :codprod");
            }
            /*if (tipo != null) {
                jpql.append(" AND a.idtipoproducto = :tipo");
            }*/
            Query query = em.createQuery(jpql.toString());
            if (codsuc != null) {
                query.setParameter("codsuc", codsuc);
            }
            if (codprod != null) {
                query.setParameter("codprod", codprod);
            }
            /*if (tipo != null) {
                query.setParameter("tipo", tipo);
            }*/
            return query.getResultList();
        } catch (NoResultException nre) {
            return null;
        } catch (Exception e) {
            throw e;
        }
    }
}
