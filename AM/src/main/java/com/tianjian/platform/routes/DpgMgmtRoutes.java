package com.tianjian.platform.routes;

import com.jfinal.config.Routes;
import com.tianjian.auth.mvc.dpgMgmt.DpgMgmtController;

public class DpgMgmtRoutes extends Routes {

	@Override
	public void config() {
		// TODO Auto-generated method stub
		//设置以下路由的视图路径
		setBaseViewPath("/WEB-INF/views");

		add("dpgMgmt", DpgMgmtController.class,"dpgMgmt");
	}

}
