package com.asi.restaurantebcd.controller.seguridad;

import com.asi.restaurantbcd.modelo.Opcionmenu;
import com.asi.restaurantebcd.negocio.util.QueryUtil;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


@Named
@RequestScoped
public class MenuParentList extends QueryUtil<Opcionmenu> {

	public MenuParentList() {
		setJpql("select m from Opcionmenu m where m.menuPadre is null order by m.orden");
	}
}
