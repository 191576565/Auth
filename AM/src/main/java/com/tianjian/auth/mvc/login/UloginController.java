package com.tianjian.auth.mvc.login;


import java.io.IOException;

import javax.servlet.ServletException;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.auth.mvc.base.BaseSessionController;

/** 
 *@Company  重庆天健金管科技股份有限公司
 *@Function   用户登录控制器            
 *@Declare     1.用户登录进行控制 2.对外部系统进行交互 
 *@Author      谢涛
 */

public class UloginController extends Controller {
	private static final Log log = Log.getLog(UloginController.class);
	public void index() {
		log.info("welcome to login");
		render("login_v2.html");
	}
	
	 /** 
		 *@Function 用户注销            
		 *@Declare   实现用户注销功能：用户Session解绑，用户权限交互-退出
		 *@Author    谢涛
		 *@Return    String  sessionId
		 */
	public  void userexit() {
		//int i=1;
		LoginService loginservice=new LoginService();
		Object userinfo = getSessionAttr("userinfo");
		String username=((Record) userinfo).getStr("user_id");
		String doid=((Record) userinfo).getStr("domain_id");
		System.out.println("user_id:"+username);
		System.out.println("doid:"+doid);
		loginservice.sendPost(username,doid);
		BaseSessionController SessionController=new BaseSessionController();
		// TODO Auto-generated method stub
		try {
			SessionController.ubindUSessionId(getRequest(), getResponse());
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		redirect("/");
	}
}
