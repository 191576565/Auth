package com.tianjian.auth.mvc.sysMgmt;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;

public class SysMgmtController extends Controller {
	
	private static final Log log = Log.getLog(SysMgmtController.class);
	private SysMgmtService sysMgmtService = new SysMgmtService();
	
	public void index() {
		log.info("jump to sysMgmt");
		render("sysMgmt.jsp");

	}
	
	/*
	 * sysMgmt/sysData
	 */
	public void sysData(){
		renderJson(sysMgmtService.getData());
	}
}
