package com.tianjian.platform.routes;

import com.jfinal.config.Routes;
import com.tianjian.auth.mvc.rolMgmt.FunListController;
import com.tianjian.auth.mvc.rolMgmt.RolMgmtController;

public class RolMgmtRoutes extends Routes {

	@Override
	public void config() {
		// TODO Auto-generated method stub
		//设置以下路由的视图路径
		setBaseViewPath("/WEB-INF/views");

		add("rolMgmt", RolMgmtController.class,"rolMgmt");
		add("funList", FunListController.class,"rolMgmt");
	}

}
