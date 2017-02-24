package com.tianjian.platform.routes;

import com.jfinal.config.Routes;
import com.tianjian.auth.mvc.api.ApiJsonController;

public class ApiJsonRoutes extends Routes {

	@Override
	public void config() {
		add("Api", ApiJsonController.class,"Api");
	}

}
