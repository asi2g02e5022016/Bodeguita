package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Sucursal;
import com.asi.restaurantbcd.modelo.Vwexistencias;
import com.asi.restaurantebcd.negocio.util.Utilidades;
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
    public List<Vwexistencias> buscarExistencias(Map filtro)
            throws Exception {
        if (filtro == null) {
            return null;
        }
        StringBuilder jpql = new StringBuilder();
        try {
            /*Integer codsuc = (Integer) filtro.get("codsuc");
            Integer codprod = (Integer) filtro.get("codprod");*/
            
            Integer codsuc = Utilidades.getParemetro("codsuc", filtro);
            /*Integer codprod = Utilidades.getParemetro("codprod", filtro); */
            
            String sucsal = Utilidades.getParemetro("sucsal", filtro);
            String prod = Utilidades.getParemetro("prod", filtro);
            
                        
            jpql.append("SELECT a FROM Vwexistencias a where 1 = 1");
            
            if (codsuc != 0){
                jpql.append(" AND a.idsucursal = :codsuc");
            }            
            if (sucsal != null) {
                jpql.append(" AND a.sucursal like CONCAT('%', :sucsal, '%')");
            }
            if (prod != null) {
                jpql.append(" AND a.producto like CONCAT('%', :prod, '%')");
            }
            /*if (tipo != null) {
                jpql.append(" AND a.idtipoproducto = :tipo");
            }*/
            Query query = em.createQuery(jpql.toString());
            if (codsuc != 0) {
                query.setParameter("codsuc", codsuc);
            }
            if (sucsal != null) {
                query.setParameter("sucsal", sucsal);
            }
            if (prod != null) {
                query.setParameter("prod", prod);
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

    @Override
    public List<Sucursal> buscarSucursal()  throws Exception{
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT a FROM Sucursal a ");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }
}
