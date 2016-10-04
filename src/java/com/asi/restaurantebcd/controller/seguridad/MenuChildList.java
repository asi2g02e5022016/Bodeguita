package com.asi.restaurantebcd.controller.seguridad;

import com.asi.restaurantbcd.modelo.OpcionMenu;
import com.asi.restaurantbcd.negocio.util.QueryUtil;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class MenuChildList extends QueryUtil<OpcionMenu> {

	public MenuChildList() {
		setJpql(" select m from OpcionMenu m" +
				" where " +
				" m.url is not null and length(m.url)>5 and" +
				" (Select Count(n) from OpcionMenu n where n.menuPadre=m)=0");
	}
}
