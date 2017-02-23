package com.tianjian.auth.mvc.usrMgmt;

import java.util.ArrayList;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.tianjian.auth.mvc.usrMgmt.UsrMgmtController;

public class UsrMgmtController extends Controller {
	
	private UsrMgmtService ums = new UsrMgmtService();
	private static final Log log = Log.getLog(UsrMgmtController.class);
	
	public void index() {
		log.info("jump to usrMgmt");
		render("usrMgmt.jsp");
	}
	//初始化查询
	public void initSel(){
		//获取session中的user_id
		String userId = getSessionAttr("username");
		//将查询到的domain_uuid和org_uuid放到list中
		ArrayList<String> list = new ArrayList<String>();
		list.addAll(ums.selectDomainOrg(userId));
		//查询该用户所属域和机构所能展示的用户
		renderJson(ums.initSelect(list));
	}
	//用户id ajax校验
	public void chkUserId(){
		//获取前台userid
		String userId = getPara("scopeCode");
		//将结果赋值给chkUserId
		setAttr("chkUserId",ums.checkUserId(userId));
		renderJson();
	}
}
