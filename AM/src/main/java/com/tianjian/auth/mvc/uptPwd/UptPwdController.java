package com.tianjian.auth.mvc.uptPwd;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.auth.mvc.base.BaseSecurityMD5;
import com.tianjian.auth.mvc.usrMgmt.UsrMgmt;

public class UptPwdController extends Controller {

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
