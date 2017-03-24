package com.tianjian.auth.mvc.rolMgmt;

import java.sql.Timestamp;
import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.auth.mvc.rolMgmt.RolMgmtController;
import com.tianjian.auth.mvc.sysMgmt.SysMgmt;
import com.tianjian.auth.mvc.sysMgmt.SysMgmtService;

/**
 * 角色管理
 * --控制器，控制业务流转
 * @author caoguopeng
*/
public class RolMgmtController extends Controller {
	
	private static final Log log = Log.getLog(RolMgmtController.class);
	
	RoleMgmtService mgmtService = new RoleMgmtService();
	SysMgmtService sysMgmtService = new SysMgmtService();
	
	public void index() {
		log.info("jump to sysMgmt");
		render("rolMgmt.jsp");
	}
	
	/*
	 * rolMgmt/showRol
	 * 查询角色表数据
	 */
	public void showRol() {
		String userId = ((Record)getSessionAttr("userinfo")).getStr("user_id");
		List<Record> list = mgmtService.paramSelect(userId);
		String domainUuid = list.get(0).getStr("uuid");
		String domainId = list.get(0).getStr("domain_id");
		renderJson(mgmtService.defaultSelect(domainUuid,domainId));
	}
	
	//新增角色
	public void save() {
		RoleMgmt mgmt = new RoleMgmt();
		mgmt.set("DOMAIN_UUID", getPara("domain_uuid"));
		mgmt.set("ROLE_ID", getPara("role_id"));
		mgmt.set("ROLE_NAME", getPara("role_name"));
		mgmt.set("CREATED_DATE", new Timestamp(System.currentTimeMillis()));
		mgmt.set("CREATOR", ((Record)getSessionAttr("userinfo")).getStr("user_id"));
		mgmt.set("MODIFIED_DATE", new Timestamp(System.currentTimeMillis()));
		mgmt.set("MODIFIER", ((Record)getSessionAttr("userinfo")).getStr("user_id"));
		mgmt.set("MEMO", getPara("memo"));
		if(!mgmtService.notRepeated(getPara("role_id"), getPara("role_name"))) {
			renderJson(false);
			return;
		}
		mgmtService.save(mgmt);
		renderJson(true);
	}
	
	//修改角色
	public void update() {
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
