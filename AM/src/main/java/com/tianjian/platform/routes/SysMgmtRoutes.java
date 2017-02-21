package com.tianjian.platform.routes;

import com.jfinal.config.Routes;
import com.tianjian.auth.mvc.sysMgmt.OrgMgmtController;
import com.tianjian.auth.mvc.sysMgmt.SysMgmtController;

public class SysMgmtRoutes extends Routes {

	@Override
	public void config() {
		// TODO Auto-generated method stub
		//设置以下路由的视图路径
		setBaseViewPath("/WEB-INF/views");

		add("sysMgmt", SysMgmtController.class,"sysMgmt");
		add("orgMgmt", OrgMgmtController.class,"sysMgmt");
	}

}
