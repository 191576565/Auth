package com.tianjian.auth.mvc.uptPwd;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;

public class UptPwdController extends Controller {
	
	private static final Log log = Log.getLog(UptPwdController.class);
	
	public void index() {
		log.info("jump to uptPwd");
		render("uptPwd.jsp");

	}
}
