package com.asi.restaurantebcd.controller.seguridad;

import com.asi.restaurantbcd.modelo.OpcionMenu;
import com.asi.restaurantebcd.negocio.util.QueryUtil;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


@Named
@RequestScoped
public class MenuParentList extends QueryUtil<OpcionMenu> {

	public MenuParentList() {
		setJpql("select m from OpcionMenu m where m.menuPadre is null order by m.orden");
	}
}
