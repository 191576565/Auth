package com.tianjian.auth.mvc.sysMgmt;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;

public class SysMgmtController extends Controller {
	
	private static final Log log = Log.getLog(SysMgmtController.class);
	
	public void index() {
		log.info("jump to sysMgmt");
		render("sysMgmt.jsp");

	}
}
