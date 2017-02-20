package com.tianjian.auth.mvc.login;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.tianjian.auth.mvc.base.BaseSessionController;

/** 
 *@Company  重庆天健金管科技股份有限公司
 *@Function   用户登录控制器            
 *@Declare     1.用户登录进行控制 2.对外部系统进行交互 
 *@Author      谢涛
 */

public class LoginController extends Controller {
	private LoginService loginservice=new LoginService();
	private static final Log log = Log.getLog(LoginController.class);
	public void index() {
		log.info("welcome to login");
		
		//renderJsp("auth/login.html");
		///render("WEB-INF/auth/login_v2.html");
		//render("AAA.jsp");
		render("login_v2.html");

	}
	
	 /** 
		 *@Function 用户登录            
		 *@Declare   实现用户登录功能：用户密码认证，用户Session绑定，用户权限交互-登录
		 *@Author    谢涛
		 *@Return    String  sessionId
		 */
	public void validLogin() {
		BaseSessionController SessionController=new BaseSessionController();
		String username=getPara("username");
		String password=getPara("password");
		//用户登录认证
		int b=loginservice.validUser(username,password);
		if(b==3){
			//用户Session绑定
			try {
				SessionController.bindUSessionId(getRequest(), getResponse(), username);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
			//用户交互数据命令生成
				render("index.html");
		}else{
				render("login_v2.html");
		}
	}
	
	 /** 
		 *@Function 用户注销            
		 *@Declare   实现用户注销功能：用户Session解绑，用户权限交互-退出
		 *@Author    谢涛
		 *@Return    String  sessionId
		 */
	public void ulogin() {
		BaseSessionController SessionController=new BaseSessionController();
		// TODO Auto-generated method stub
		try {
			SessionController.ubindUSessionId(getRequest(), getResponse());
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//    	List<User> users = User.dao.find("select * from user");
// 		setAttr("users", users);
        render("list.jsp");
	}
}
