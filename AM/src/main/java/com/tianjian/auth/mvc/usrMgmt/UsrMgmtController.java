package com.tianjian.auth.mvc.usrMgmt;

import java.sql.Timestamp;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.tianjian.auth.mvc.base.BaseSecurityMD5;
import com.tianjian.auth.mvc.constant.ConstantLog;

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
		String param = getPara("sendParam");
		//条件查询
		if(!"".equals(param)){
			renderJson(ums.paramSelect(orgUUID,param));
			return;
		}
		//查询该用户所属机构下所能展示的用户
		renderJson(ums.initSelect(orgUUID));
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
		String roles = StringUtils.join(getParaValues("role"),",");
		String userPwd = BaseSecurityMD5.encodeMD5Hex("123456");
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
		usrMgmt.set("roles", roles);
		usrMgmt.set("user_pwd", userPwd);
		usrMgmt.set("user_name", userName);
		usrMgmt.set("user_email", userEmail);
		usrMgmt.set("user_phone", userPhone);
		usrMgmt.set("creator", sessionUserId);
		if(""==roles || null==roles){
			renderJson("{\"message\":\"roleIsNull\"}");
			return;
		}
		
		usrMgmt.save();

		setAttr(ConstantLog.log_optype, ConstantLog.user_add);
		String msg = "新增用户（用户编码）："+userId+" 用户名:"+userName;
		setAttr(ConstantLog.log_opcontent, msg);
		renderJson(true);
	}
	//编辑用户
	public void updtUsr(){
		//获取form中的信息
		String uuid = getPara("uuid");
		String userName = getPara("usrName");
		String roles = StringUtils.join(getParaValues("role"),",");
		//获取密码并用MD5加密
//		String userPwd = BaseSecurityMD5.encodeMD5Hex(getPara("pwd"));
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
		usrMgmt.set("roles", roles);
//		usrMgmt.set("user_pwd", userPwd);
		usrMgmt.set("user_name", userName);
		usrMgmt.set("user_email", userEmail);
		usrMgmt.set("user_phone", userPhone);
		usrMgmt.set("modifier", sessionUserId);
		usrMgmt.set("modified_date", new Timestamp(System.currentTimeMillis()));
		if(""==roles || null==roles){
			renderJson("{\"message\":\"roleIsNull\"}");
			return;
		}
		usrMgmt.update();

		setAttr(ConstantLog.log_optype, ConstantLog.user_chg);
		String msg = "编辑用户-UUID:" + uuid +" 用户名:"+userName;
		setAttr(ConstantLog.log_opcontent, msg);
		renderJson(true);
	}
	//删除用户
	public void delUsr(){
		//获取form中的信息
		String uuid = getPara("uuid");
		//将获得的字段与表字段对应
		usrMgmt.set("uuid", uuid);
		
		usrMgmt.delete();
		
		setAttr(ConstantLog.log_optype, ConstantLog.user_del);
		String msg = "删除用户-UUID："+uuid+" 用户编码:"+getPara("user_id")+" 用户名:"+getPara("user_name");
		setAttr(ConstantLog.log_opcontent, msg);
		renderJson(true);
	}
	//批量删除用户
	public void batchDel(){
		String[] uuids = getParaValues("uuid[]");
		String[] users = getParaValues("arrUser[]");
		renderJson(ums.batchDeleteUUID(uuids));
		
		setAttr(ConstantLog.log_optype, ConstantLog.user_del);
		String msg = "删除用户-UUID："+Arrays.toString(uuids)+"　用户名:"+Arrays.toString(users);
		setAttr(ConstantLog.log_opcontent, msg);
		renderJson(true);
	}
	
	//重置密码
	public void batchReset(){
		String[] uuids = getParaValues("uuid[]");
		renderJson(ums.batchResetUUID(uuids));
		
		setAttr(ConstantLog.log_optype, ConstantLog.user_chg);
		String msg = "重置用户（uuid）："+uuids+" 的密码";
		setAttr(ConstantLog.log_opcontent, msg);
	}
}
