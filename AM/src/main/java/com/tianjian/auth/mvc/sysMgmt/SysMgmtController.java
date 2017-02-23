package com.tianjian.auth.mvc.sysMgmt;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;

public class SysMgmtController extends Controller {
	
	private static final Log log = Log.getLog(SysMgmtController.class);
	private SysMgmtService sysMgmtService = new SysMgmtService();
	
	public void index() {
		log.info("jump to sysMgmt");
		render("sysMgmt.jsp");

	}
	
	public void sysData(){
//		String selectSql = sysMgmtService.getSelectSql(SysMgmt.sqlId_sys_select);
//		System.out.println(selectSql);
		List<SysMgmt> myjson = sysMgmtService.getData("select * from sys_domain_info");
		System.out.println(myjson);
		renderJson(myjson);
	}
}
