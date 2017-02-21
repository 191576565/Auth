package com.tianjian.auth.mvc.sysMgmt;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;

public class OrgMgmtController extends Controller {
	
	private static final Log log = Log.getLog(OrgMgmtController.class);
	
	public void index() {
		log.info("jump to orgMgmt");
		render("orgMgmt.jsp");

	}
}
