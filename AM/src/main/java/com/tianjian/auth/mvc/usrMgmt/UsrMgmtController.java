package com.tianjian.auth.mvc.usrMgmt;

import java.awt.List;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.auth.mvc.base.BaseSecurityMD5;
import com.tianjian.auth.mvc.usrMgmt.UsrMgmtController;

public class UsrMgmtController extends Controller {
	
	private UsrMgmt usrMgmt = new UsrMgmt();
	private UsrMgmtService ums = new UsrMgmtService();
	private static final Log log = Log.getLog(UsrMgmtController.class);
	
	public void index() {
		log.info("jump to usrMgmt");
		render("usrMgmt.jsp");
	}
	//初始化查询
	public void initSel(){
		//获取session中的user_id
		String sessionUserId = getSessionAttr("user_id");
		System.out.println("sessionUserId == "+sessionUserId);
		//查询到的org_uuid
		String orgUUID = ums.selectInitOrganization(sessionUserId);
		//查询该用户所属机构下所能展示的用户
		renderJson(ums.initSelect(orgUUID,sessionUserId));
	}
	//初始化域
	public void selDmn(){
		//获取session中的user_id
		String sessionUserId = getSessionAttr("user_id");
		renderJson(ums.selectDomain(sessionUserId));
	}
	//初始化机构
	public void selOrg(){
		//获取session中的user_id
		String sessionUserId = getSessionAttr("user_id");
		renderJson(ums.selectOrganization(sessionUserId));
	}
	//初始化角色
	public void selRol(){
		//获取session中的user_id
		String sessionUserId = getSessionAttr("user_id");
		renderJson(ums.selectRole(sessionUserId));
	}
	//用户id ajax校验
	public void chkUserId(){
		//获取前台userid
		String userId = getPara("scopeCode");
		//将结果赋值给chkUserId
		setAttr("chkUserId",ums.checkUserId(userId));
		renderJson();
	}
	//新增用户
	public void insUsr(){
		//获取form中的信息
		String userId = getPara("scopeCode");
		String userName = getPara("usrName");
		//获取密码并用MD5加密
		String userPwd = BaseSecurityMD5.encodeMD5Hex(getPara("pwd"));
		String userPhone = getPara("phone");
		String userEmail = getPara("email");
		String domainUUID = getPara("domain");
		String orgUUID = getPara("organization");
		//将session中的userid作为创建者
		String sessionUserId = getSessionAttr("user_id");
		
		//将获得的字段与表字段对应
		usrMgmt.set("domain_uuid", domainUUID);
		usrMgmt.set("user_id", userId);
		usrMgmt.set("org_uuid", orgUUID);
		usrMgmt.set("roles", null);
		usrMgmt.set("user_pwd", userPwd);
		usrMgmt.set("user_name", userName);
		usrMgmt.set("user_email", userEmail);
		usrMgmt.set("user_phone", userPhone);
		usrMgmt.set("creator", sessionUserId);
		
		usrMgmt.save();
	}
	//编辑用户
	public void updtUsr(){
		//获取form中的信息
		String uuid = getPara("uuid");
		String userName = getPara("usrName");
		//获取密码并用MD5加密
		String userPwd = BaseSecurityMD5.encodeMD5Hex(getPara("pwd"));
		String userPhone = getPara("phone");
		String userEmail = getPara("email");
		//前台没做domainUUID和orgUUID，先暂用常量代替
		String domainUUID = getPara("domain");
		String orgUUID = getPara("organization");
		//将session中的userid作为创建者
		String sessionUserId = getSessionAttr("user_id");
		
		//将获得的字段与表字段对应
		usrMgmt.set("uuid", uuid);
		usrMgmt.set("domain_uuid", domainUUID);
		usrMgmt.set("org_uuid", orgUUID);
		usrMgmt.set("roles", null);
		usrMgmt.set("user_pwd", userPwd);
		usrMgmt.set("user_name", userName);
		usrMgmt.set("user_email", userEmail);
		usrMgmt.set("user_phone", userPhone);
		usrMgmt.set("modifier", sessionUserId);
		usrMgmt.set("modified_date", new Timestamp(System.currentTimeMillis()));
		usrMgmt.update();
	}
	//删除用户
	public void delUsr(){
		//获取form中的信息
		String uuid = getPara("uuid");
		//将获得的字段与表字段对应
		usrMgmt.set("uuid", uuid);
		
		usrMgmt.delete();
	}
	//批量删除用户
	public void batchDel(){
		String[] uuids = getParaValues("uuid[]");
		renderJson(ums.batchDeleteUUID(uuids));
	}
}
