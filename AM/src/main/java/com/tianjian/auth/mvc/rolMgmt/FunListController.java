package com.tianjian.auth.mvc.rolMgmt;

import org.apache.commons.lang.StringUtils;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.tianjian.auth.mvc.rolMgmt.FunListController;


/***
 * 角色管理
 * --角色（业务功能选择）
 *   --跳转流程控制类
 * 
 * 
 * 
 * 
 * 
 * **/

public class FunListController extends Controller {
	
	private static final Log log = Log.getLog(FunListController.class);
	
	FunListService funListService = new FunListService();
	RoleMgmtService mgmtService = new RoleMgmtService();
	
	public void index() {
		log.info("jump to sysMgmt");
		render("funList.jsp");

	}
	
	//角色对应功能列表查询
	
	public void showFunList() {
		
		System.out.println("showfunlist 方法被调用");
		System.out.println(getPara("uuid"));
		setAttr("funuuid", getPara("uuid"));
		setAttr("funlist", funListService.selectFunList());
		render("funList.jsp");
	}
	
	//角色对应功能保存
	
	public void funSave() {
		System.out.println("funsave 方法被调用");
		
	//	String[] flist = getParaValues("fun_list");
	//	System.out.println(StringUtils.join(getParaValues("fun_list"),","));
		
		RoleMgmt mgmt = new RoleMgmt();
		mgmt.set("uuid", getPara("fun_uuid"));
		
		mgmt.set("resources", StringUtils.join(getParaValues("fun_list"),","));
		if(!mgmtService.update(mgmt)){
			return ;
		}
		redirect("/rolMgmt");
	}
}
