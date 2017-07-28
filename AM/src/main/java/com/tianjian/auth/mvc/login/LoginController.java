package com.tianjian.auth.mvc.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.kit.HttpKit;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.auth.mvc.base.BaseSecurityMD5;
import com.tianjian.auth.mvc.base.BaseSessionController;
import com.tianjian.auth.mvc.handler.GlobalInterceptor;
import com.tianjian.auth.mvc.menu.MenuService;
import com.tianjian.auth.mvc.model.UserService;

/**
 * @Company 重庆天健金管科技股份有限公司
 * @Function 用户登录控制器
 * @Declare 1.用户登录进行控制 2.对外部系统进行交互
 * @Author 谢涛
 */

public class LoginController extends Controller {
	private LoginService loginservice = new LoginService();
	private MenuService menuService = new MenuService();
	private static final Log log = Log.getLog(LoginController.class);
	private static String g_userName;
	private static String g_passWord;

	public void index() {
		log.info("welcome to login");
		// hujian modify
		getResponse().setHeader("Cache-Control", "no-cache");
		getResponse().setHeader("Pragma", "no-cache");
		getResponse().setDateHeader("Expires", 0);

		render("login_v2.html");
	}

	public void getloginUser() {
		List<String> list = new ArrayList<String>();
		list.add(0, g_userName);
		list.add(1, g_passWord);
		/*
		 * 判断是否是FR内置系统管理员,是则加入明文,否则加入密文
		 */
		// String domain = loginservice.domainOfUser(g_userName);
		// if("root".equals(domain)){
		// list.add(1, g_passWord);
		// }else{
		// String md5Pwd = BaseSecurityMD5.encodeMD5Hex(g_passWord);
		// list.add(1, md5Pwd);
		// }
		renderJson(list);
	}

	public void init_login() {

		// String callback = getPara("callback");
		// System.err.println("--------------------------callback is:
		// "+callback);
		log.info("welcome to init_login");
		getResponse().setHeader("Cache-Control", "no-cache");
		getResponse().setHeader("Pragma", "no-cache");
		getResponse().setDateHeader("Expires", 0);

		render("login_v2.html");

	}

	/**
	 * hujian 登录后处理
	 */
	public void loginafter() {
		// System.err.println("---------"+getSession().getId()+"------");
		String sessionId = (String) getRequest().getSession().getAttribute("usersessionid");
		Object userinfo = getSessionAttr("userinfo");
		String userName = ((Record) userinfo).getStr("user_id");
		// 针对CA增加org_unit_id
		String domainid = ((Record) userinfo).getStr("domain_id");
		String orgunitid = ((Record) userinfo).getStr("org_unit_id");
		setAttr("userid", userName);
		setAttr("sid", sessionId);
		setAttr("domainid", domainid);
		setAttr("orgunitid", orgunitid);
		render("login_after.jsp");
	}

	/**
	 * @Function 用户登录
	 * @Declare 实现用户登录功能：用户密码认证，用户Session绑定，用户权限交互-登录
	 * @Author 谢涛
	 * @Return String sessionId
	 */
	@Clear(GlobalInterceptor.class)
	public void validLogin() {

		//
		BaseSessionController SessionController = new BaseSessionController();
		String username = getPara("username");
		String password = getPara("password");

		/*
		 * yeqc 17/06/13
		 */
		g_userName = username;
		g_passWord = password;

		// ----------
		String callback = getPara("callback");
		// ---------

		// 用户登录认证
		int b = loginservice.validUser(username, password);
		if (b == 3) {
			String sessionId = getSession().getId();
			// 用户是否非配角色校验
			if (!loginservice.uhasrole(Login.sqlId_uhasrole, username)) {
				renderJson("{\"code\":\"0\",\"message\":\"请向管理员分配角色后再登录\"}");
				return;
			}

			// 用户Session绑定
			try {
				SessionController.bindUSessionId(getRequest(), getResponse(), username);
				SessionController.bindUserInfo(getRequest(), getResponse(), username);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
			// hujian modify
			Object userinfo = getSessionAttr("userinfo");
			String doid = ((Record) userinfo).getStr("domain_id");
			// session 为新的才发请求

			if (loginservice.sidIsNew(Login.sqlId_checksid, sessionId, username)) {
				loginservice.sendPost(username, doid);
			}

			UserService.login(username, sessionId, "0");
			if (!"".equals(callback) && null != callback) {
				renderJson("{\"code\":\"9\"},\"message\":" + callback);
				return;
			}
			renderJson("{\"code\":\"1\",\"message\":\"登陆成功\"}");
		} else {
			renderJson("{\"code\":\"0\",\"message\":\"用户名或密码错误\"}");
		}
	}

	// 获取用户拥有的菜单项
	public void getMenu() {
		renderJson(menuService.getScopeInfo(((Record) getSessionAttr("userinfo")).getStr("user_id")));
	}

}
