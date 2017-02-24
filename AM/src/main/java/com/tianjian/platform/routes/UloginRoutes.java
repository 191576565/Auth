package com.tianjian.platform.routes;

import com.jfinal.config.Routes;
import com.tianjian.auth.mvc.login.LoginController;
import com.tianjian.auth.mvc.login.UloginController;
import com.tianjian.auth.mvc.oplog.OpLogController;

public class UloginRoutes extends Routes {

	@Override
	public void config() {
		// TODO Auto-generated method stub
		//设置以下路由的视图路径
		setBaseViewPath("/WEB-INF/views");
		add("/ulogin", UloginController.class,"ulogin");
	}

}
