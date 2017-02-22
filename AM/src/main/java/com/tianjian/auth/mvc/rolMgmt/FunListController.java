package com.tianjian.auth.mvc.rolMgmt;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.tianjian.auth.mvc.rolMgmt.FunListController;

public class FunListController extends Controller {
	
	private static final Log log = Log.getLog(FunListController.class);
	
	public void index() {
		log.info("jump to sysMgmt");
		render("funList.jsp");

	}
}
