package com.tianjian.auth.mvc.resMgmt;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.auth.mvc.login.LoginController;
import com.tianjian.auth.mvc.oplog.OpLogService;
import com.tianjian.auth.mvc.resMgmt.ResMgmtController;

public class ResMgmtController extends Controller {
	
	private static final Log log = Log.getLog(ResMgmtController.class);
	private ResMgmtService reservice = new ResMgmtService();
	public void index() {
		log.info("jump to resMgmt");
		render("resMgmt.jsp");
	}
	/*
	 * 根据res_type，res_up_uuid，user_id获取菜单
	 * author： hujian
	 * 
	 */
	public void menuPage() {
		String restype = getPara("res_type");
		String resupuuid = getPara("res_up_uuid");
		Object userinfo = getSessionAttr("userinfo");	
		String userid=((Record) userinfo).getStr("user_id");
		System.out.println(restype+"-"+resupuuid+"-"+userid);
		List<Record> menupage=reservice.getMenuPage(ResMgmt.sqlId_resmenu_select,userid,resupuuid,restype);
		renderJson(menupage);
	}
}
