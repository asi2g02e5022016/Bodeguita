/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.util;

/**
 *
 * @author siman
 */
import com.asi.restaurantbcd.modelo.Vwfisico;
import com.asi.restaurantbcd.modelo.Vwventas;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
 
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.ChartSeries;
 
@ManagedBean(name = "chartView")
@ViewScoped
public class ChartView implements Serializable {
 
    private BarChartModel barModel;
    
    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em;
    
    private HorizontalBarChartModel horizontalBarModel;
 
    @PostConstruct
    public void init() {
        createBarModels();
    }
 
    public BarChartModel getBarModel() {
        return barModel;
    }
     
    public HorizontalBarChartModel getHorizontalBarModel() {
        return horizontalBarModel;
    }
 
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
        List<Vwventas> sucList = em.createQuery("select p from Vwventas p").getResultList();
        ChartSeries ventasu = new ChartSeries();
        ventasu.setLabel("Unidades");
        ChartSeries ventasm = new ChartSeries();
        ventasm.setLabel("Monto");
        for(Vwventas vta:sucList){
          ventasu.set(vta.getSucursal(), vta.getUnidades());
          ventasm.set(vta.getSucursal(), vta.getTotal());
        }
                
        model.addSeries(ventasu);
        model.addSeries(ventasm);
         
        return model;
    }
     
    private void createBarModels() {
        createBarModel();
        createHorizontalBarModel();
    }
     
    private void createBarModel() {
        barModel = initBarModel();
         
        barModel.setTitle("Grafico Ventas");
        barModel.setLegendPosition("ne");
         
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Sucursal");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Venta");
        yAxis.setMin(0);
        yAxis.setMax(200);
    }
     
    private void createHorizontalBarModel() {
        horizontalBarModel = new HorizontalBarChartModel();
 
        ChartSeries exisu = new ChartSeries();
        ChartSeries exism = new ChartSeries();
 
        List<Vwfisico> sucList = em.createQuery("select p from Vwfisico p").getResultList();
        exisu.setLabel("Unidades");
        exism.setLabel("Monto");
        for(Vwfisico vta:sucList){
          exisu.set(vta.getSucursal(), vta.getUnidades());
          exism.set(vta.getSucursal(), vta.getTotal());
        }

 
        horizontalBarModel.addSeries(exisu);
        horizontalBarModel.addSeries(exism);
         
        horizontalBarModel.setTitle("Existencias x Sucursal");
        horizontalBarModel.setLegendPosition("e");
        horizontalBarModel.setStacked(true);
         
        Axis xAxis = horizontalBarModel.getAxis(AxisType.X);
        xAxis.setLabel("Unidades/Monto");
        xAxis.setMin(0);
        xAxis.setMax(200);
         
        Axis yAxis = horizontalBarModel.getAxis(AxisType.Y);
        yAxis.setLabel("Sucursal");        
    }


 
}
