package com.tianjian.platform.routes;

import com.jfinal.config.Routes;
import com.tianjian.auth.mvc.resMgmt.ResMgmtController;

public class ResMgmtRoutes extends Routes {

	@Override
	public void config() {
		// TODO Auto-generated method stub
		//设置以下路由的视图路径
		setBaseViewPath("/WEB-INF/views");

		add("resMgmt", ResMgmtController.class,"resMgmt");
	}

}
