package com.tianjian.auth.mvc.uptPwd;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.auth.mvc.base.BaseSecurityMD5;
import com.tianjian.auth.mvc.constant.ConstantLog;
import com.tianjian.auth.mvc.resMgmt.ResMgmt;
import com.tianjian.auth.mvc.usrMgmt.UsrMgmt;
import com.tianjian.platform.tools.ToolGetSql;

public class UptPwdController extends Controller {
	private UsrMgmt usrMgmt = new UsrMgmt();
	private static final Log log = Log.getLog(UptPwdController.class);

	public void index() {
		render("uptPwd.jsp");
	}

	public void modifpwd() {
		UsrMgmt u = new UsrMgmt();
		Object userinfo = getSessionAttr("userinfo");
		String oldp = getPara("oldpassword");
		String newp = getPara("newpassword");
		String renewp = getPara("renewpassword");
		String pass = BaseSecurityMD5.encodeMD5Hex(newp);
		u.set(UsrMgmt.column_uuid, ((Record) userinfo).getStr("user_uuid"));
		u.set(UsrMgmt.column_user_pwd, pass);

		if (newp.equals(renewp)) {
			setAttr(ConstantLog.log_optype, ConstantLog.user_passmodify);
			String msg = "用户密码修改";
			setAttr(ConstantLog.log_opcontent, msg);
			u.update();
			redirect("/");
		}else{
			render("uptPwd.jsp");
		}
	}

	public void oldpwdCheck() {
		String oldp = getPara("oldpassword");
		Object userinfo = getSessionAttr("userinfo");
		String userpwd = ((Record) userinfo).getStr("user_pwd");
		String pstr = BaseSecurityMD5.encodeMD5Hex(oldp);
		if (pstr.equals(userpwd)) {
			renderJson(true);
		} else {
			renderJson(false);
		}
	}
//界面修改用户信息
	public void userinfoMod(){
		Object userinfo = getSessionAttr("userinfo");
		String useruuid = ((Record) userinfo).getStr("user_uuid");
		System.out.println("user_uuid:"+useruuid);
		String sql=ToolGetSql.getSql("tianjian.usrMgmt.findsingle");
		Record su =Db.findFirst(sql, useruuid);
		renderJson(su);
	}
	public void userinfoupt(){
		String uuid = getPara("uuid");
		String userName = getPara("username");
		String userPhone = getPara("userphone");
		String userEmail = getPara("useremail");
		usrMgmt.set("uuid", uuid);
		usrMgmt.set("user_name", userName);
		usrMgmt.set("user_email", userEmail);
		usrMgmt.set("user_phone", userPhone);
		usrMgmt.update();
		setAttr(ConstantLog.log_optype, ConstantLog.user_chg);
		String msg = "编辑用户（uuid）："+uuid;
		setAttr(ConstantLog.log_opcontent, msg);
		//forwardAction("/validLogin");
		//render("/login/login_after.jsp");
		renderJson(true);	
	}
	
	public void newplenCheck() {
		String newp = getPara("newpassword");

		// 要验证的字符串

		// 邮箱验证规则
		String regEx = "/^(\\w){6,20}$/";
		// 编译正则表达式
		Pattern pattern = Pattern.compile(regEx);
		// 忽略大小写的写法
		// Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(newp);
		// 字符串是否与正则表达式相匹配
		boolean rs = matcher.matches();
		if (rs) {
			renderJson(true);
		} else {
			renderJson(false);
		}
	}

	public void newpCheck() {
		String newp = getPara("newpassword");
		String renewp = getPara("renewpassword");

		if (newp.equals(renewp)) {
			renderJson(true);
		} else {
			renderJson(false);
		}
	}
}
