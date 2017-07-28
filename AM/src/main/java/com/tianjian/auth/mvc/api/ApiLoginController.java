package com.tianjian.auth.mvc.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.auth.mvc.handler.GlobalInterceptor;
import com.tianjian.auth.mvc.login.LoginService;
import com.tianjian.auth.mvc.model.User;
import com.tianjian.platform.tools.ToolHttpClient;


/**
 *rpm app 登录api
 *
 * @author  [hujian]
 * @version [1.0]
 * @date    [2017年3月30日]
 */

public class ApiLoginController extends Controller {

	private static final Log log = Log.getLog(ApiLoginController.class);
	private LoginService loginservice = new LoginService();
	private ApiLoginService apiservice = new ApiLoginService();

	/**
	 * 登录  传递session id  修改登录状态字段名
	 */
	@Clear(GlobalInterceptor.class)
	public void appValidLogin() {
		String username = getPara("username");
		String password = getPara("password");
		//同一浏览器已登录
		
		// 用户登录认证
		int b = loginservice.validUser(username, password);
		if (b == 3) {
			// app用户Session
			// setp 1: get session sid
			String sid = getSession().getId();
			// setp 2: update db userinfo applogin
			apiservice.appLogin(User.model_user_applogin, username, sid, "0");
			// step 3: get userinfo
			System.out.println("sid :" + sid);
			Map<String, Object> userinfo = new HashMap<String, Object>();
			String code = "200";
			String msg = username + "用户数据请求成功！";
			Object data = null;
			//
			List<Record> user = ApiLoginService.getSelectlist(username, sid, "1");
			if (user == null || user.size() == 0) {
				log.info("rpm api请求数据异常，请检查接入格式或用户Session状态！");
				code = "400";
				msg = username + "数据api请求异常，请检查接入格式或用户Session状态！";
			} else {
				data = user;
			}
			// step 4: format message
			//207.7.24 增加app报表登录
			String url="http://localhost:8080/WebReport/ReportServer?op=fs_load&cmd=sso&fr_username="
					+ username
					+"&fr_password="
					+password;
			String rep=ToolHttpClient.SendHttpPost(url, null, null);
			if ( rep.contains("success")){
				msg+=" 报表登录成功";
			}else{
				msg+=" 报表登录失败";
			}
			//
			userinfo.put("sid", sid);
			userinfo.put("code", code);
			userinfo.put("msg", msg);
			userinfo.put("data", data);
			renderJson(userinfo);

		} else {
			Map<String, Object> userinfo = new HashMap<String, Object>();
			String code = "400";
			String msg = username + "用户或密码错误";
			Object data = null;
			userinfo.put("code", code);
			userinfo.put("msg", msg);
			userinfo.put("data", data);
			renderJson(userinfo);
		}
	}

	/**
	 * 退出   修改登录状态字段
	 */
	@Clear(GlobalInterceptor.class)
	public void appLogout() {
		String sid = getSession().getId();
		String username = getPara("username");
		Map<String, Object> userinfo = new HashMap<String, Object>();
		String code = "200";
		String msg = username + "用户登出成功！";
		Object data = null;
		int n=apiservice.appLogout(User.model_user_applogin, username, sid, "1");
		if (n>0){
			
		}else{
			 code = "400";
			 msg = username + "用户登出失败！";
		}
		userinfo.put("code", code);
		userinfo.put("msg", msg);
		userinfo.put("data", data);
		renderJson(userinfo);
	}
	/**
	 * 接口请求数据   和原pc请求一样
	 */
	@Clear(GlobalInterceptor.class)
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
			List<Record> user = ApiLoginService.getSelectlist(username, usersession, type);
			if (user == null || user.size() == 0) {
				log.info("rpm api请求数据异常，请检查接入格式或用户Session状态！");
				code = "400";
				//msg = username + "数据api请求异常，请检查接入格式或用户Session状态！";
				//modify 2017.4.26 hujian
				msg = "用户在其他终端登录,该终端已下线,请确保您的密码是否已泄露。";
			} else {
				data = user;
			}
		} else {
			apiservice.appLogout(User.model_user_applogin, username, usersession, "1");
			log.info("api请求用户退出成功"+username);
			code = "101";
			msg = "api请求用户退出成功"+username;
			
		}
		userinfo.put("code", code);
		userinfo.put("msg", msg);
		userinfo.put("data", data);
		renderJson(userinfo);
	}
}
