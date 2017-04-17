package com.tianjian.auth.mvc.sysMgmt;

import java.sql.Timestamp;
import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.auth.mvc.constant.ConstantLog;
import com.tianjian.platform.tools.ToolYeqc;

public class SysMgmtController extends Controller {
	
	private static final Log log = Log.getLog(SysMgmtController.class);
	private SysMgmtService sysMgmtService = new SysMgmtService();
	
	public void index() {
		log.info("jump to sysMgmt");
		String userId = ((Record)getSessionAttr("userinfo")).getStr("user_id");
		String userRole = sysMgmtService.userRole(userId).get(0).getStr("domain_id");
		if("root".equals(userRole)){
			render("sysMgmt.jsp");
			return;
		}
		render("sysMgmt2.jsp");
	}
	
	/*
	 * sysMgmt/sysData
	 * 查询系统信息
	 */
	public void sysData(){
		String userId = ((Record)getSessionAttr("userinfo")).getStr("user_id");
		String userRole = sysMgmtService.userRole(userId).get(0).getStr("domain_id");
		String param = getPara("sendParam");
		//条件查询
		if(!"".equals(param)){
			renderJson(sysMgmtService.paramSelect(param));
			return;
		}
		//Root用户
		if("root".equals(userRole)){
			renderJson(sysMgmtService.getData());
			return;
		}
		//非root用户
		renderJson(sysMgmtService.notRootData(userId));
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
		sysMgmt.set("MEMO", ToolYeqc.Text2Html(getPara("memo")));
		if(!sysMgmtService.notRepeated(getPara("scopeCode"), getPara("scopeName"))){
			renderJson(false);
			return;
		}
		sysMgmtService.save(sysMgmt);
		setAttr(ConstantLog.log_optype, ConstantLog.dmn_add);
		String msg = "新增域" + "域id:" + getPara("scopeCode") + "  域名称:"
				+ getPara("scopeName");
		setAttr(ConstantLog.log_opcontent, msg);
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
		sysMgmt.set("MEMO", ToolYeqc.Text2Html(getPara("memo")));
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
		setAttr(ConstantLog.log_optype, ConstantLog.dmn_chg);
		String msg = "编辑域" + "域id:" + getPara("scopeCode") + "  域名称:"
				+ getPara("scopeName");
		setAttr(ConstantLog.log_opcontent, msg);
		renderJson(true);
	}
	
	/*
	 * sysMgmt/delete
	 * 删除系统信息
	 */
	public void delete(){
		SysMgmt sysMgmt = new SysMgmt();
		sysMgmt.set("UUID", getPara("UUID"));
		//判断是否关联机构
		if(sysMgmtService.orgSelect(getPara("UUID"))){
			renderJson(false);
			return;
		}
		//是否删除成功
		if(!sysMgmtService.delete(sysMgmt)){
			renderJson(false);
			return;
		}
		//日志
		setAttr(ConstantLog.log_optype, ConstantLog.dmn_del);
		String msg = "删除域,头结点UUID为："+getPara("UUID") ;
		setAttr(ConstantLog.log_opcontent, msg);
		//删除成功
		renderJson(true);
	}
	
	/*
	 * sysMgmt/deleteMore
	 * 删除多个
	 */
	public void deleteMore(){	
		String uuids = getPara("uuid");
		if(sysMgmtService.deleteMore(uuids)){
			setAttr(ConstantLog.log_optype, ConstantLog.dmn_del);
			String msg = "批量删除域,头结点UUID为："+getPara("uuid") ;
			setAttr(ConstantLog.log_opcontent, msg);
			renderJson(true);
			return;
		}
		renderJson(false);
	}
	
	/*
	 * sysMgmt/paramSelect
	 * 条件搜索
	 */
	public void paramSelect(){
		String param = getPara("param");
		renderJson(sysMgmtService.paramSelect(param));
	}
	
}
