package com.tianjian.platform.routes;

import com.jfinal.config.Routes;
import com.tianjian.auth.mvc.login.LoginController;

public class LoginRoutes extends Routes {

	@Override
	public void config() {
		// TODO Auto-generated method stub
		setBaseViewPath("/auth");
		add("/", LoginController.class);
	}

}
