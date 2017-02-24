package com.tianjian.auth.mvc.sysMgmt;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;

public class SysMgmtController extends Controller {
	
	private static final Log log = Log.getLog(SysMgmtController.class);
	private SysMgmtService sysMgmtService = new SysMgmtService();
	
	public void index() {
		log.info("jump to sysMgmt");
		render("sysMgmt.jsp");

	}
	
	/*
	 * sysMgmt/sysData
	 * 查询系统信息
	 */
	public void sysData(){
		renderJson(sysMgmtService.getData());
	}
	
	/*
	 * sysMgmt/save
	 * 新增系统信息
	 */
	public void save(){
		SysMgmt sysMgmt = new SysMgmt();
		sysMgmt.set("DOMAIN_ID", getPara("scopeCode"));
		sysMgmt.set("DOMAIN_NAME", getPara("scopeName"));
		sysMgmt.set("CREATOR", "Yeqc");
		sysMgmt.set("SORT_ID", getPara("scopeSort"));
		sysMgmt.set("MODIFIER", "Yeqc");
		if(!sysMgmtService.notRepeated(getPara("scopeCode"), getPara("scopeName"))){
			renderJson(false);
			return;
		}
		sysMgmtService.save(sysMgmt);
		renderJson(true);
	}
	
	/*
	 * sysMgmt/update
	 * 修改系统信息
	 */
	public void update(){
		SysMgmt sysMgmt = new SysMgmt();
		sysMgmt.set("UUID", getPara("UUID"));
		sysMgmt.set("DOMAIN_ID", getPara("scopeCode"));
		sysMgmt.set("DOMAIN_NAME", getPara("scopeName"));
		sysMgmt.set("SORT_ID", getPara("scopeSort"));
		if(sysMgmtService.update(sysMgmt)){
			renderJson(true);
			return;
		}
		renderJson(false);
	}
	
	/*
	 * sysMgmt/delete
	 * 删除系统信息
	 */
	public void delete(){
		SysMgmt sysMgmt = new SysMgmt();
		sysMgmt.set("UUID", getPara("UUID"));
		if(sysMgmtService.delete(sysMgmt)){
			renderJson(true);
			return;
		}
		renderJson(false);
	}
	
}
