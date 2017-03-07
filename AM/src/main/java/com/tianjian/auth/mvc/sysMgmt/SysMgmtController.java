package com.tianjian.auth.mvc.sysMgmt;

import java.sql.Timestamp;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Record;

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
		sysMgmt.set("CREATOR", ((Record)getSessionAttr("userinfo")).getStr("user_id"));
		sysMgmt.set("SORT_ID", getPara("scopeSort"));
		sysMgmt.set("MODIFIER", ((Record)getSessionAttr("userinfo")).getStr("user_id"));
		sysMgmt.set("DOMAIN_UP_UUID", sysMgmtService.getRoot().get(0).get("uuid"));
		sysMgmt.set("CREATED_DATE", new Timestamp(System.currentTimeMillis()));
		sysMgmt.set("MODIFIED_DATE", new Timestamp(System.currentTimeMillis()));
		sysMgmt.set("MEMO", getPara("memo"));
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
		sysMgmt.set("MEMO", getPara("memo"));
		sysMgmt.set("MODIFIER", ((Record)getSessionAttr("userinfo")).getStr("user_id"));
		sysMgmt.set("MODIFIED_DATE", new Timestamp(System.currentTimeMillis()));
		if(sysMgmtService.uptRepeated(getPara("scopeCode"), getPara("scopeName"), getPara("UUID"))==true){
			renderJson("{\"message\":\"repeat\"}");
			return;
		}
		if(!sysMgmtService.update(sysMgmt)){
			renderJson(false);
			return;
		}
		renderJson(true);
	}
	
	/*
	 * sysMgmt/delete
	 * 删除系统信息
	 */
	public void delete(){
		SysMgmt sysMgmt = new SysMgmt();
		sysMgmt.set("UUID", getPara("UUID"));
		if(!sysMgmtService.delete(sysMgmt)){
			renderJson(false);
			return;
		}
		renderJson(true);
	}
	
	/*
	 * sysMgmt/deleteMore
	 * 删除多个
	 */
	public void deleteMore(){	
		String uuids = getPara("uuid");
		if(sysMgmtService.deleteMore(uuids)){
			renderJson(true);
		}
	}
	
}
