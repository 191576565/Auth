package com.tianjian.auth.mvc.sysMgmt;

import java.sql.Timestamp;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;

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
	 * orgMgmt/subData
	 * 查询第二级机构
	 */
	public void subData(){
		renderJson(orgMgmtService.subData(g_uuid));
	}
	
	/*
	 * orgMgmt/subData2
	 * 无线循环
	 */
	public void subData2(){
		String up_uuid = getPara("up_uuid");
		renderJson(orgMgmtService.subData2(g_uuid, up_uuid));
	}
	
	/*
	 * orgMgmt/orgData
	 * 查询机构信息
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
		orgMgmt.set("DOMAIN_UUID", getPara("UUID"));
		orgMgmt.set("ORG_UNIT_ID", getPara("orgCode"));
		orgMgmt.set("ORG_UNIT_DESC", getPara("orgName"));
		orgMgmt.set("ORG_UP_UUID", getPara("upOrg"));
		orgMgmt.set("IS_ENABLE", "Y");
		orgMgmt.set("MEMO", getPara("memo"));
		orgMgmt.set("CREATOR", "yeqc");
		orgMgmt.set("MODIFIER", "yeqc");
		orgMgmt.set("CREATED_DATE", new Timestamp(System.currentTimeMillis()));
		orgMgmt.set("MODIFIED_DATE", new Timestamp(System.currentTimeMillis()));
		if(orgMgmtService.isRepeated(getPara("UUID"), getPara("orgCode"))){
			renderJson(false);
			return;
		}
		orgMgmtService.save(orgMgmt);
		renderJson(true);
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
