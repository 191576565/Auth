package com.tianjian.auth.mvc.oplog;

import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.platform.pjson.PageJson;
import com.tianjian.platform.tools.ToolGetSql;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpLogController extends Controller {

	private OpLogService oplogservice = new OpLogService();

	public void index() {
		render("sysLog.jsp");
	}

	public static void datasave(String doName) {

		OpLog l = new OpLog();
		l.set("DOMAIN_NAME", doName);
		l.set("ORG_UNIT_DESC", doName);
		l.set("USER_NAME", doName);
		l.set("ROLE_NAME", doName);
		l.set("OP_TYPE", doName);
		l.set("OP_CONTENT", doName);
		l.set("OP_DATE", new Timestamp(System.currentTimeMillis()));

		l.save();

		// OpLog.dao.set(attr, value);
	}

	/*
	 * 获取日志数据 
	 * @return json 
	 * @author hujian
	 * @see ToolGetSql
	 * @
	 */
	public void logdata() {
		// 获取表单参数
		Integer pageSize = getParaToInt("pageSize");
		Integer pageNumber = getParaToInt("pageNumber");
		String user_uuid = getPara("user_uuid");
		String op_type = getPara("op_type");
		String startdate_start = getPara("startdate_start");
		String startdate_end = getPara("startdate_end");
		// 组装sql参数
		Map<String, Object> mpara = oplogservice.getMparam(user_uuid, op_type, startdate_start, startdate_end);
		// 获取select语句
		String selectsql = oplogservice.getSelectSql(OpLog.sqlId_log_select);
		// 获取from语句
		String fromsql = oplogservice.getFromSql(OpLog.sqlId_log_selectfrom, mpara);
		// 获取数据
		PageJson<OpLog> myjson = oplogservice.getPageData(pageSize, pageNumber, selectsql, fromsql);

		renderJson(myjson);
	}

}
