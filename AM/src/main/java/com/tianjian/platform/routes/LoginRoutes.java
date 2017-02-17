package com.tianjian.platform.routes;

import com.jfinal.config.Routes;
import com.tianjian.auth.mvc.login.LoginController;
import com.tianjian.auth.mvc.oplog.OpLogController;

public class LoginRoutes extends Routes {

	@Override
	public void config() {
		// TODO Auto-generated method stub
		setBaseViewPath("/WEB-INF/views");

		add("/", LoginController.class);
		add("syslog", OpLogController.class,"oplog");
	}

}
