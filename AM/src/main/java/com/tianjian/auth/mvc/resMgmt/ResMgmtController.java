package com.tianjian.auth.mvc.resMgmt;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.tianjian.auth.mvc.resMgmt.ResMgmtController;

public class ResMgmtController extends Controller {
	
	private static final Log log = Log.getLog(ResMgmtController.class);
	
	public void index() {
		log.info("jump to resMgmt");
		render("resMgmt.jsp");

	}
}
