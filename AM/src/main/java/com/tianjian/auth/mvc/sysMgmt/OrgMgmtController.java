package com.tianjian.auth.mvc.sysMgmt;

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
	 * 发送查询结果
	 */
	public void getId(){
		renderJson(orgMgmtService.getScopeInfo(g_uuid));
	}
}
