package com.tianjian.auth.mvc.usrMgmt;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.tianjian.auth.mvc.usrMgmt.UsrMgmtController;

public class UsrMgmtController extends Controller {
	
	private static final Log log = Log.getLog(UsrMgmtController.class);
	
	public void index() {
		log.info("jump to usrMgmt");
		render("usrMgmt.html");

	}
}
