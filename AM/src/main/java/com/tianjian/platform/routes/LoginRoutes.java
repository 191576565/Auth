package com.tianjian.platform.routes;

import com.jfinal.config.Routes;
import com.tianjian.auth.mvc.login.LoginController;
import com.tianjian.auth.mvc.loginAfter.LoginAfterController;
import com.tianjian.auth.mvc.oplog.OpLogController;

public class LoginRoutes extends Routes {

	@Override
	public void config() {
		// TODO Auto-generated method stub
		//设置以下路由的视图路径
		setBaseViewPath("/WEB-INF/views");

		add("/", LoginController.class,"login");
		//第三个参数为视图所在路径的文件夹，如果不设置，默认为ControllerKey，本例不设置为oplog
		add("syslog", OpLogController.class,"oplog");
		
		add("auth", LoginAfterController.class,"login");


	}

}
