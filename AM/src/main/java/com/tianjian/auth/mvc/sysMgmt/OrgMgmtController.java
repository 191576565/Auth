package com.tianjian.auth.mvc.sysMgmt;

import java.sql.Timestamp;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Record;

public class OrgMgmtController extends Controller {
	
	private static final Log log = Log.getLog(OrgMgmtController.class);
	public OrgMgmtService orgMgmtService =  new OrgMgmtService();
	//保存接收参数("uuid")
	public static String g_uuid = "";
	
	public void index() {
		log.info("jump to orgMgmt");
		render("orgMgmt.jsp");

	}
	
	/*
	 * orgMgmt/orgData
	 * root用户查询机构信息
	 */
	public void orgData(){
		renderJson(orgMgmtService.getData(g_uuid));
	}
	
	/*
	 * orgMgmt/accept
	 * 接收查询条件
	 */
	public void accept(){
		g_uuid = getPara("uuid");
		renderJson(true);
	}
	
	/*
	 * orgMgmt/getId
	 * 发送域信息查询结果
	 */
	public void getId(){
		renderJson(orgMgmtService.getScopeInfo(g_uuid));
	}
	
	/*
	 * orgMgmt/getOrg
	 * 发送机构信息查询结果
	 */
	public void getOrg(){
		renderJson(orgMgmtService.getOrgInfo(g_uuid));
	}
	
	/*
	 * orgMgmt/save
	 * 新增机构信息
	 */
	public void save(){
		OrgMgmt orgMgmt = new OrgMgmt();
		orgMgmt.set("DOMAIN_UUID", getPara("domainUUID"));
		orgMgmt.set("ORG_UNIT_ID", getPara("orgCode"));
		orgMgmt.set("ORG_UNIT_DESC", getPara("orgName"));
		orgMgmt.set("ORG_UP_UUID", getPara("upOrg"));
		orgMgmt.set("IS_ENABLE", "Y");
		orgMgmt.set("MEMO", getPara("memo"));
		orgMgmt.set("CREATOR", ((Record)getSessionAttr("userinfo")).getStr("user_id"));
		orgMgmt.set("MODIFIER", ((Record)getSessionAttr("userinfo")).getStr("user_id"));
		orgMgmt.set("CREATED_DATE", new Timestamp(System.currentTimeMillis()));
		orgMgmt.set("MODIFIED_DATE", new Timestamp(System.currentTimeMillis()));
		if(orgMgmtService.isRepeated(getPara("domainUUID"), getPara("orgCode"))){
			renderJson(false);
			return;
		}
		orgMgmtService.save(orgMgmt);
		renderJson(true);
	}
	
	/*
	 * orgMgmt/update
	 * 编辑机构信息
	 */
	public void update(){
		OrgMgmt orgMgmt = new OrgMgmt();
		orgMgmt.set("UUID", getPara("UUID"));
		orgMgmt.set("ORG_UNIT_ID", getPara("orgCode"));
		orgMgmt.set("ORG_UNIT_DESC", getPara("orgName"));
		orgMgmt.set("ORG_UP_UUID", getPara("upOrg"));
		orgMgmt.set("MEMO", getPara("memo"));
		orgMgmt.set("MODIFIER", ((Record)getSessionAttr("userinfo")).getStr("user_id"));
		orgMgmt.set("MODIFIED_DATE", new Timestamp(System.currentTimeMillis()));
		if(orgMgmtService.uptReapeated(getPara("domainUUID"), getPara("orgCode"), getPara("UUID"))){
			renderJson("{\"message\":\"repeat\"}");
			return;
		}
		if(orgMgmtService.update(orgMgmt)){
			renderJson(true);
		}
	}
	
	/*
	 * orgMgmt/delete
	 * 删除机构信息
	 */
	public void delete(){
		if(orgMgmtService.delete(getPara("UUID"))){
			renderJson(true);
			return;
		}
		renderJson(false);
	}
}
