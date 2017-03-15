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
	 * 资源的--查询
	 */
	public void get() {

		List<ResMgmt> all = reservice.get(ResMgmt.sqlId_res_select);
		renderJson(all);
	}
	/*
	 * 资源的--id校验
	 */
	public void resIdcheck() {	
		log.info("resid ");
		if (reservice.resIdisRepeated(ResMgmt.sqlId_res_residrepeated,getPara("resId"))){
			renderJson(true);
		}else{
			renderJson(false);
		}
	}
	/*
	 * 资源的--name校验
	 */
	public void resNamecheck() {	
		if (reservice.resNameisRepeated(ResMgmt.sqlId_res_resnamerepeated,getPara("resName"))){
			renderJson(true);
		}else{
			renderJson(false);
		}
	}
	/*
	 * 资源的--新增
	 */
	public void save() {
		ResMgmt res = getModel(ResMgmt.class, "res");
		System.out.println(res);
		// 获取session中数据
		Object userinfo = getSessionAttr("userinfo");
		// 完善res数据
		reservice.setResParam(res, userinfo);
		// uuid自动生成
		res.remove(ResMgmt.column_uuid);
		boolean savesucess = res.save();
		if (savesucess) {
			// 写日志
			setAttr(ConstantLog.log_optype, ConstantLog.res_add);
			String msg = "新增资源" + "资源id:" + res.getStr(ResMgmt.column_res_id) + "  资源名称:"
					+ res.getStr(ResMgmt.column_res_name);
			setAttr(ConstantLog.log_opcontent, msg);
			renderJson(true);
		} else {
			renderJson(false);
		}

	}

	/*
	 * 资源的--更改
	 */
	public void put() {
		ResMgmt res = getModel(ResMgmt.class, "res");

		// 获取session中数据
		Object userinfo = getSessionAttr("userinfo");
		//
		reservice.updateResParam(res, userinfo);

		boolean savesucess = res.update();
		if (savesucess) {
			// 写日志
			setAttr(ConstantLog.log_optype, ConstantLog.res_chg);
			String msg = "编辑资源" + "资源id:" + res.getStr(ResMgmt.column_res_id) + "  资源名称:"
					+ res.getStr(ResMgmt.column_res_name);
			setAttr(ConstantLog.log_opcontent, msg);
			renderJson(true);
		} else {
			renderJson(false);
		}

	}

	/*
	 * 资源的--删除
	 */
	public void delete() {

		String uuid = getPara("uuid");
		System.out.println("uuid=====" + uuid);

		int i = reservice.delmore(ResMgmt.sqlId_res_delete, uuid);
		if (i >= 1) {
			// 写日志
			setAttr(ConstantLog.log_optype, ConstantLog.res_del);
			String msg = "删除资源,资源头结点UUID为：" +uuid ;
			setAttr(ConstantLog.log_opcontent, msg);
			renderJson(true);
		} else {
			renderJson(false);
		}
	}

	/*
	 * 资源的--资源树
	 */
	public void restree() {
		List<Record> restree = reservice.gettree(ResMgmt.sqlId_res_tree);
		renderJson(restree);
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
