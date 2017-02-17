package com.tianjian.auth.mvc.login;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;
public class LoginController extends Controller {
	private LoginService loginservice=new LoginService();
	private static final Log log = Log.getLog(LoginController.class);
	public void index() {
		log.info("welcome to login");
		
		//renderJsp("auth/login.html");
		render("login_v2.html");
	}
	public void validLogin() {
		
		String username=getPara("username");
		String password=getPara("password");
		System.out.println("login000"+username+password);
		boolean b=loginservice.validUser(username,password);
		if (b){
			render("index.html");
		}else{
			render("login_v2.html");
		}
	}
}
