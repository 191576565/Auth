package com.tianjian.auth.mvc.rolMgmt;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.tianjian.auth.mvc.rolMgmt.RolMgmtController;

/**
 * 角色管理
 * --控制器，控制业务流转
 * @author caoguopeng
 * 
 * **/
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
	
	//新增角色
	public void save() {
		
		
		RoleMgmt mgmt = new RoleMgmt();
		mgmt.set("DOMAIN_UUID", getPara("domain_uuid"));
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
	
	//修改角色
	
	public void update() {
		
		System.out.println("update 方法被调用");
		
		System.out.println(getPara("uuid"));
		RoleMgmt mgmt = new RoleMgmt();
		mgmt.set("UUID", getPara("UUID"));
		mgmt.set("DOMAIN_UUID", getPara("domain_uuid"));
		mgmt.set("ROLE_ID", getPara("role_id"));
		mgmt.set("ROLE_NAME", getPara("role_name"));
		mgmt.set("CREATOR", "caoguopeng");
		
		if(!mgmtService.update(mgmt)) {
			renderJson(false);
			return;
		}
		renderJson(true);
	}
	
	//删除角色
	
	public void delete() {
		RoleMgmt mgmt = new RoleMgmt();
		mgmt.set("UUID", getPara("UUID"));
		if(!mgmtService.delete(mgmt)) {
			renderJson(false);
			return;
		}
		renderJson(true);
		
	}
}
