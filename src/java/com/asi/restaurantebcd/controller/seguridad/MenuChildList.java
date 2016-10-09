package com.asi.restaurantebcd.controller.seguridad;

import com.asi.restaurantbcd.modelo.Opcionmenu;
import com.asi.restaurantebcd.negocio.util.QueryUtil;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class MenuChildList extends QueryUtil<Opcionmenu> {

	public MenuChildList() {
		setJpql(" select m from Opcionmenu m" +
				" where " +
				" m.url is not null and length(m.url)>5 and" +
				" (Select Count(n) from Opcionmenu n where n.menuPadre=m)=0");
	}
}
