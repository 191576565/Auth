package com.tianjian.auth.mvc.resMgmt;

import java.sql.Timestamp;
import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Record;

public class ResMgmtController extends Controller {
	
	private static final Log log = Log.getLog(ResMgmtController.class);
	private ResMgmtService reservice = new ResMgmtService();
	
	public void index() {
		render("resMgmt.jsp");
	}
	public void get() {
	//	sqlId_res_select
		List<ResMgmt> all=reservice.get(ResMgmt.sqlId_res_select);
		renderJson(all);
	}
	public void save() {
		ResMgmt res=getModel(ResMgmt.class,"res");
		
		res.set("domain_uuid", "hujian");
		res.set("created_date", new Timestamp(System.currentTimeMillis()));
		res.set("creator", "hujian");
		res.set("modified_date", new Timestamp(System.currentTimeMillis()));
		res.set("modifier", "hujian");
		System.out.println("res---"+res);
		boolean savesucess=res.save();
		if (savesucess){
			renderJson(true);
		}else{
			renderJson(false);
		}
		
	}
	public void put() {
		ResMgmt res=getModel(ResMgmt.class,"res");
		
		res.set("domain_uuid", "hujian");
		res.set("modified_date", new Timestamp(System.currentTimeMillis()));
		res.set("modifier", "hujian");
		System.out.println("res---"+res);
		boolean savesucess=res.update();
		if (savesucess){
			renderJson(true);
		}else{
			renderJson(false);
		}
		
	}
	public void delete() {
		ResMgmt res=new ResMgmt();
		
		res.set("uuid", getPara("uuid"));
		boolean savesucess=res.delete();
		if (savesucess){
			renderJson(true);
		}else{
			renderJson(false);
		}
		
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
