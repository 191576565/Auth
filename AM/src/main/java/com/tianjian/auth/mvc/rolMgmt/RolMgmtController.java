package com.tianjian.auth.mvc.rolMgmt;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.tianjian.auth.mvc.rolMgmt.RolMgmtController;

public class RolMgmtController extends Controller {
	
	private static final Log log = Log.getLog(RolMgmtController.class);
	
	RoleMgmtService mgmtService = new RoleMgmtService();
	
	public void index() {
		log.info("jump to sysMgmt");
		render("rolMgmt.jsp");

	}
	
	public void showRol() {
		renderJson(mgmtService.defaultSelect());
	}
	
	public void save() {
		
		System.out.println("addRole方法被调用");
		RoleMgmt mgmt = new RoleMgmt();
		mgmt.set("DOMIAN_UUID", getPara("domain_uuid"));
		mgmt.set("ROLE_ID", getPara("role_id"));
		mgmt.set("ROLE_NAME", getPara("role_name"));
		mgmt.set("CREATOR", "caoguopeng");
		
		if(!mgmtService.notRepeated(getPara("role_id"), getPara("role_name"))) {
			renderJson(false);
			return;
		}
		mgmtService.save(mgmt);
		renderJson(true);
		
	}
}
