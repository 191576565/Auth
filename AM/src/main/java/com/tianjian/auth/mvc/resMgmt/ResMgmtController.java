package com.tianjian.auth.mvc.resMgmt;

import java.sql.Timestamp;
import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.auth.mvc.constant.ConstantLog;

public class ResMgmtController extends Controller {

	private static final Log log = Log.getLog(ResMgmtController.class);
	private ResMgmtService reservice = new ResMgmtService();

	public void index() {
		render("resMgmt.jsp");
	}
	/*
	 * 资源的获取
	 */
	public void get() {

		List<ResMgmt> all = reservice.get(ResMgmt.sqlId_res_select);
		renderJson(all);
	}

	public void save() {
		ResMgmt res = getModel(ResMgmt.class, "res");	
		//获取session中数据
		Object userinfo = getSessionAttr("userinfo");
		//完善res数据
		reservice.setResParam(res,userinfo);
		boolean savesucess = res.save();
		if (savesucess) {
			//写日志
			setAttr(ConstantLog.log_optype,ConstantLog.res_add);
			String msg="新增资源"+"res_id:"+res.getStr(ResMgmt.column_res_id)+"res_name:"+res.getStr(ResMgmt.column_res_name);
			setAttr(ConstantLog.log_opcontent,msg);
			renderJson(true);
		} else {
			renderJson(false);
		}

	}

	public void put() {
		ResMgmt res = getModel(ResMgmt.class, "res");

		//获取session中数据
		Object userinfo = getSessionAttr("userinfo");
		//
		reservice.updateResParam(res,userinfo);
		boolean savesucess = res.update();
		if (savesucess) {
			//写日志
			setAttr(ConstantLog.log_optype,ConstantLog.res_add);
			String msg="编辑资源"+"res_id:"+res.getStr(ResMgmt.column_res_id)+"res_name:"+res.getStr(ResMgmt.column_res_name);
			setAttr(ConstantLog.log_opcontent,msg);
			renderJson(true);
		} else {
			renderJson(false);
		}

	}

	public void delete() {
//		ResMgmt res = new ResMgmt();
//
//		res.set("uuid", getPara("uuid"));
		String uuid=getPara("uuid");
		try{
			Db.update(ResMgmt.sqlId_resmenu_delete,uuid);
			setAttr(ConstantLog.log_optype,ConstantLog.res_add);
			String msg="删除资源"+"uuid:";
			setAttr(ConstantLog.log_opcontent,msg);
			renderJson(true);
		}catch(Exception e){
			renderJson(false);
		}
	}

	/*
	 * 根据res_type，res_up_uuid，user_id获取菜单 author： hujian
	 * 
	 */
	public void menuPage() {
		String restype = getPara("res_type");
		String resupuuid = getPara("res_up_uuid");
		Object userinfo = getSessionAttr("userinfo");
		String userid = ((Record) userinfo).getStr("user_id");
		System.out.println(restype + "-" + resupuuid + "-" + userid);
		List<Record> menupage = reservice.getMenuPage(ResMgmt.sqlId_resmenu_select, userid, resupuuid, restype);
		renderJson(menupage);
	}
}
