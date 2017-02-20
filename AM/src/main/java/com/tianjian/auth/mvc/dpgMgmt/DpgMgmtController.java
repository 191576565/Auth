package com.tianjian.auth.mvc.dpgMgmt;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.tianjian.auth.mvc.dpgMgmt.DpgMgmtController;

public class DpgMgmtController extends Controller {
	
	private static final Log log = Log.getLog(DpgMgmtController.class);
	
	public void index() {
		log.info("jump to dpgMgmt");
		render("dpgMgmt.html");

	}
}
