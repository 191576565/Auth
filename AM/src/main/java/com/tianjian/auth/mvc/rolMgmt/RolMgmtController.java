package com.tianjian.auth.mvc.rolMgmt;

import java.sql.Timestamp;
import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.auth.mvc.constant.ConstantLog;
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
		String userId = ((Record)getSessionAttr("userinfo")).getStr("user_id");
		String userRole = sysMgmtService.userRole(userId).get(0).getStr("domain_id");
		//Root用户
		if("root".equals(userRole)){
			setAttr("domainlist",sysMgmtService.getData());
		}else{
			setAttr("domainlist",sysMgmtService.notRootData(userId));
		}
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
		String param = getPara("sendParam");
		//条件查询
		if(!"".equals(param)){
			renderJson(mgmtService.likeSelect(domainId,domainUuid,param));
			return;
		}
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
		setAttr(ConstantLog.log_optype, ConstantLog.role_add);
		String msg = "新增角色-" + " 角色编码:" + getPara("role_id") + " 角色名称:"
				+ getPara("role_name");
		setAttr(ConstantLog.log_opcontent, msg);
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
		setAttr(ConstantLog.log_optype, ConstantLog.role_chg);
		String msg = "修改角色-" +" UUID:" + getPara("UUID") + " 角色编码:" + getPara("role_id") + " 角色名称:"
				+ getPara("role_name");
		setAttr(ConstantLog.log_opcontent, msg);
	}
	
	//删除角色
	public void delete() {
		RoleMgmt mgmt = new RoleMgmt();
		String uuid = getPara("UUID");
		mgmt.set("UUID", uuid);
		//判断是否关联用户
		if(mgmtService.usrSelect(uuid)){
			renderJson(false);
			return;
		}
		mgmtService.delete(mgmt);
		renderJson(true);
		setAttr(ConstantLog.log_optype, ConstantLog.role_del);
		String msg = "删除角色-" +" UUID:" + getPara("UUID") + " 角色编码:" + getPara("role_id") + " 角色名称:"
				+ getPara("role_name");
		setAttr(ConstantLog.log_opcontent, msg);
	}
	
	//批量删除
	public void deleteMore(){
		String uuids = getPara("UUID");
		if(mgmtService.deleteMore(uuids)){
			renderJson(true);
			setAttr(ConstantLog.log_optype, ConstantLog.role_del);
			String msg = "删除角色-" +" UUID:" + uuids + " 角色编码:" + getPara("role_id") + " 角色名称:"
					+ getPara("role_name");
			setAttr(ConstantLog.log_opcontent, msg);
			return;
		}
		renderJson(false);
	}
	
}
