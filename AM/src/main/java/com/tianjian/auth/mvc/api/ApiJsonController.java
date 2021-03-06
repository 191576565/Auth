package com.tianjian.auth.mvc.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.auth.mvc.base.BaseSessionController;
import com.tianjian.auth.mvc.handler.GlobalInterceptor;
import com.tianjian.auth.mvc.login.LoginService;
import com.tianjian.auth.mvc.model.User;
import com.tianjian.auth.mvc.model.UserService;

/**
 * @Function Api 交互控制器
 * @Declare 实现系统和外部系统的户数交互功能
 * @Author 谢涛
 * @Return
 */

public class ApiJsonController extends Controller {
	private static final Log log = Log.getLog(ApiJsonController.class);
	private LoginService loginservice = new LoginService();
	private ApiJsonService apiservice=new ApiJsonService();
	/**
	 * rpm 系统
	 */
	private static final String rpm = "rpm";

	/**
	 * @Function rpm系统
	 * @Declare Api传入字符串类
	 * @Author 谢涛
	 * @Return object json
	 * @Eg http://127.0.0.1:8080/AM/Api/rpmString/jfinal-77E30E1DFD28BA533821DE626ED7DC78-0-1
	 */
	public void rpmString() {
		/*
		 * username 用户名，用于权限系统用户查询 usersession 用户session，用于认证当前用户在权限系统的登录状态 flag
		 * 用户状态，用户Rpm系统对当前用户注销操作 type 内容类型，区分Rpm系统数据交互的内容对象 tourl 重定向界面
		 * 
		 **/
		String username = getPara(0);
		String usersession = getPara(1);
		String flag = getPara(2);
		String type = getPara(3);
		String tourl = getPara(4);
		/* 判断命令类型：0 正常请求类型 1 用户注销 */
		if (flag.equals("0")) {
			// 获取json返回对象
			// Record user1 =
			// ApiJsonService.getSelect(username,usersession,type);
			Map<String,Object> user = ApiJsonService.getSelectlist(username, usersession, type);//yeqc 17/07/27  返回嵌套格式的json,减少数据冗余
			
			if (user == null) {
				log.info("rpm api请求数据异常，请检查接入格式或用户Session状态！");
				renderJson("failed", "api请求数据异常，请检查接入格式或用户Session状态！");
			} else {
				renderJson("code:200;msg:api请求数据异常", "data:" + user);
			}
		} else {
			int ulogin = ApiJsonService.ulogin(username);
			log.info("api请求" + ulogin + "个用户退出成功，用户[" + username + "],[" + usersession + "]！");
			redirect("/ulogin");
		}
		System.out.print(username + "-" + usersession + "-" + flag + "-" + type);
	}

	/**
	 * @Function rpm系统
	 * @Declare Api传入参数类
	 * @Author 谢涛
	 * @Return object json
	 * @Eg http://127.0.0.1:8080/AM/Api/rpmParam?userid=jfinal&sid=77E30E1DFD28BA533821DE626ED7DC78&login=0&apitype=1
	 * @Param apitype=1:获取前台权限
	 */
	public void rpmParam() {
		// 允许跨域操作
		// getResponse().addHeader("Access-Control-Allow-Origin", "*");
		String username = getPara("userid");
		String usersession = getPara("sid");
		String flag = getPara("login");
		String type = getPara("apitype");
		String tourl = getPara("tourl");
		/* 判断命令类型：0 正常请求类型 1 用户注销 */
		// Object userinfo=new Object();
		Map<String, Object> userinfo = new HashMap<String, Object>();
		String code = "200";
		String msg = username + "用户数据请求成功！";
		Object data = null;
		if (flag.equals("0")) {
			// 获取json返回对象
			// Record user1 =
			// ApiJsonService.getSelect(username,usersession,type);
			try {
				Map<String,Object> user = ApiJsonService.getSelectlist(username, usersession, type);
				if (user == null || user.size() == 0) {
					log.info("rpm api请求数据异常，请检查接入格式或用户Session状态！");
					
					code = "200";
					msg =username+ "-数据权限组无该用户或该用户登录状态已改变";
					//modify 2017.4.26 hujian
					//msg = "用户在其他终端登录,该终端已下线,请确保您的密码是否已泄露。";
				} else {
					data = user;
				}
			} catch (Exception e) {
				code = "400";
				data = null;
				msg = "sid验证失败";
				userinfo.put("code", code);
				userinfo.put("msg", msg);
				userinfo.put("data", data);
				renderJson(userinfo);
			}			
			
		} else {
			//the two just add for once
			String sessionId="111111";//getRequest().getSession().getId();
			int ulogin=UserService.ulogin(username, sessionId, "1");
			//
			//int ulogin = ApiJsonService.ulogin(username);
			
			log.info("api请求" + ulogin + "个用户退出成功，用户[" + username + "],[" + usersession + "]！");
			code = "101";
			msg = "api请求" + ulogin + "个用户退出成功，用户[" + username + "],[" + usersession + "]！";
			System.out.println("redirect");
			//redirect("/ulogin/userexit");
			//return;
		}
		userinfo.put("code", code);
		userinfo.put("msg", msg);
		userinfo.put("data", data);
		renderJson(userinfo);
	}
	@Clear(GlobalInterceptor.class)
	public void ftpRequest(){
		String username = getPara("userid");
		String usersession = getPara("sid");
		//String flag = getPara("login");
		String type = getPara("apitype");
		Map<String,Object> user = ApiJsonService.getSelectlist(username, usersession, type);//yeqc 17/07/27  返回嵌套格式的json,减少数据冗余
		if (user == null) {
			log.info("ftp api请求菜单数据异常，请检查接入格式或用户Session状态！");
			renderJson("failed", "api请求数据异常，请检查接入格式或用户Session状态！");
			return;
		}
		List<Record> orginfo = ApiJsonService.getOrginfo(username);
		if (orginfo==null){
			log.info("ftp api请求机构数据异常，请检查接入格式或用户Session状态！");
			renderJson("failed", "api请求数据异常，请检查接入格式或用户Session状态！");
			return;
		}
		String code = "200";
		String msg = username + "用户数据请求成功！";
		Map<String, Object> ftpdata = new HashMap<String, Object>();
		ftpdata.put("code", code);
		ftpdata.put("msg", msg);
		ftpdata.put("data", user);
		ftpdata.put("org", orginfo);
		renderJson(ftpdata);
//		} else {
//			
//		}
	}
}
