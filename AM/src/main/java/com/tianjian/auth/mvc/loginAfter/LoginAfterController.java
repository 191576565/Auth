package com.tianjian.auth.mvc.loginAfter;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.tianjian.auth.mvc.loginAfter.LoginAfterController;

public class LoginAfterController extends Controller {
	
	private static final Log log = Log.getLog(LoginAfterController.class);
	
	public void index() {
		log.info("jump to orgMgmt");
		render("index.jsp");

	}
}
