package com.tianjian.auth.mvc.rolMgmt;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.tianjian.auth.mvc.rolMgmt.RolMgmtController;

public class RolMgmtController extends Controller {
	
	private static final Log log = Log.getLog(RolMgmtController.class);
	
	public void index() {
		log.info("jump to sysMgmt");
		render("rolMgmt.jsp");

	}
	
	public void showRol() {
		renderJson(new RoleMgmtService().defaultSelect());
	}
}
