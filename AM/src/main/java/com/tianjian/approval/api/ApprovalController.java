package com.tianjian.approval.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.auth.mvc.handler.GlobalInterceptor;

public class ApprovalController extends Controller{
	
	private ApprovalService approvalService = new ApprovalService();
	
	public void index() {
		renderJson(true);
	}
	
	/*
	 * activiti/getUserTree
	 * 根据机构id加载其下机构和用户
	 */
	public void getUserTree() {
		//对"approval/orgUsrTree"不进行拦截
		GlobalInterceptor.addExcludedActionKey("activiti/getUserTree");
		
		String callback=getRequest().getParameter("callback");
		String jsonText="";
		
		String id = getPara("id");
		List<Record> list = new ArrayList<Record>();
		if(null==id || "".equals(id)){
			//加载顶层
			list = approvalService.initOrgUser();
		}else{
			list = approvalService.orgUser(id);
		}
		List<Record> newList = approvalService.typeCast(list);
		jsonText=JsonKit.toJson(newList);
		String rs = approvalService.caseCast(jsonText);
		renderJson(callback+"("+rs+")");
	}
	
	/*
	 * activiti/getUserName
	 * 根据userId得到userName
	 */
	public void getUserName() {
		GlobalInterceptor.addExcludedActionKey("activiti/getUserName");
		
		String callback=getRequest().getParameter("callback");
		String jsonText="";
		String id = getPara("userId");
		String userName = approvalService.getUserName(id);
		jsonText = JsonKit.toJson(userName);
		renderJson(callback+"("+jsonText+")");
	}
	
	/*
	 * activiti/ssoLogin
	 * 返回用户信息
	 */
	public void ssoLogin() throws UnsupportedEncodingException {
		GlobalInterceptor.addExcludedActionKey("activiti/ssoLogin");
//		System.err.println("---------"+getSession().getId()+"------");
		
		Record rd = ((Record)getSessionAttr("userinfo"));
//		System.err.println("------------rd is:"+rd);
		String callback=getRequest().getParameter("callback");
		if(null == rd || "".equals(rd.get("user_id")) || null==rd.get("user_id")){
			redirect("/init_login?callback="+callback);
		}else{
			List<Record> list = approvalService.getOrg(rd.get("user_id"));
			Record rd1 = list.get(0);
//			System.err.println("------------user_name is:"+rd.getStr("user_name"));
			String userName = URLEncoder.encode(rd.getStr("user_name"),"utf-8");
			
			redirect(callback+"?userId="+rd.get("user_id")+"&userName="+userName+"&groupsId="+
		rd.getStr("role_ids")+"&department="+rd.get("org_unit_id"));
		}
		
	}
	
	/*
	 * activiti/getUserInfo
	 * 返回用户信息(带参)
	 */
	public void getUserInfo() {
		
		GlobalInterceptor.addExcludedActionKey("activiti/getUserInfo");
		
//		String callback=getRequest().getParameter("callback");
		String jsonText="";
		String userId = getPara("userId");
		List<Record> list = approvalService.getUserInfo(userId);
		Record rd = list.get(0);
		
		jsonText = JsonKit.toJson(rd);
		String rs = approvalService.caseCast(jsonText);
		renderJson(rs);
		
	}
}
