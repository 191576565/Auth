package com.tianjian.auth.mvc.sysMgmt;

import java.sql.Timestamp;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.auth.mvc.constant.ConstantLog;

public class OrgMgmtController extends Controller {
	
	private static final Log log = Log.getLog(OrgMgmtController.class);
	private OrgMgmtService orgMgmtService =  new OrgMgmtService();
	private SysMgmtService sysMgmtService = new SysMgmtService();
	//保存接收参数("uuid")
	public static String g_uuid = "";
	
	public void index() {
		log.info("jump to orgMgmt");
		render("orgMgmt.jsp");

	}
	
	/*
	 * orgMgmt/orgData
	 * 查询机构信息
	 */
	public void orgData(){
		String userId = ((Record)getSessionAttr("userinfo")).getStr("user_id");
		String userRole = sysMgmtService.userRole(userId).get(0).getStr("domain_id");
		if("root".equals(userRole)){
			renderJson(orgMgmtService.getData(g_uuid));
			return;
		}
		renderJson(orgMgmtService.notRootData(userId));
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
		String userId = ((Record)getSessionAttr("userinfo")).getStr("user_id");
		String userRole = sysMgmtService.userRole(userId).get(0).getStr("domain_id");
		if("root".equals(userRole)){
			renderJson(orgMgmtService.getScopeInfo(g_uuid));
			return;
		}
		renderJson(orgMgmtService.notRootScopeInfo(userId));
	}
	
	/*
	 * orgMgmt/getOrg
	 * 发送机构信息查询结果
	 */
	public void getOrg(){
		String domainUuid = ((Record)getSessionAttr("userinfo")).getStr("domain_uuid");
		String orgUuid = ((Record)getSessionAttr("userinfo")).getStr("org_uuid");
		String userId = ((Record)getSessionAttr("userinfo")).getStr("user_id");
		String userRole = sysMgmtService.userRole(userId).get(0).getStr("domain_id");
		if("root".equals(userRole)){
			renderJson(orgMgmtService.getOrgInfo(g_uuid));
			return;
		}
		renderJson(orgMgmtService.notRootOrgInfo(orgUuid,domainUuid));
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
		orgMgmt.set("ORG_UP_UUID", getPara("org_up_uuid"));
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
		setAttr(ConstantLog.log_optype, ConstantLog.org_add);
		String msg = "新增机构-" + "　机构编码:" + getPara("orgCode") + " 机构名称:"
				+ getPara("orgName");
		setAttr(ConstantLog.log_opcontent, msg);
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
		orgMgmt.set("ORG_UP_UUID", getPara("org_up_uuid"));
		orgMgmt.set("MEMO", getPara("memo"));
		orgMgmt.set("MODIFIER", ((Record)getSessionAttr("userinfo")).getStr("user_id"));
		orgMgmt.set("MODIFIED_DATE", new Timestamp(System.currentTimeMillis()));
		if(orgMgmtService.uptReapeated(getPara("domainUUID"), getPara("orgCode"), getPara("UUID"))){
			renderJson("{\"message\":\"repeat\"}");
			return;
		}
		if(orgMgmtService.update(orgMgmt)){
			setAttr(ConstantLog.log_optype, ConstantLog.org_chg);
			String msg = "编辑机构-" + " UUID:" + getPara("UUID")+" 机构编码:" + getPara("orgCode") + " 机构名称:"
					+ getPara("orgName");
			setAttr(ConstantLog.log_opcontent, msg);
			renderJson(true);
		}
	}
	
	/*
	 * orgMgmt/delete
	 * 删除机构信息
	 */
	public void delete(){
		String uuid = getPara("UUID");
		//判断是否关联用户
		if(orgMgmtService.usrSelect(uuid)){
			renderJson(false);
			return;
		}
		orgMgmtService.delete(uuid);
		setAttr(ConstantLog.log_optype, ConstantLog.org_del);
		String msg = "删除机构-" + " UUID:" + getPara("UUID") + " 机构编码:" + getPara("orgCode") + " 机构名称:"
				+ getPara("orgName");
		setAttr(ConstantLog.log_opcontent, msg);
		renderJson(true);
	}
}
