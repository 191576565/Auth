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
	public void index() {
		render("sysLog.jsp");
	}

	public void test() {

		String sqlselect = ToolGetSql.getSql("tianjian.oplog.pageAllSelect");
		String sqlfrom = ToolGetSql.getSql("tianjian.oplog.pageAllFrom");
		Page<OpLog> logall = OpLog.dao.paginate(1, 2, sqlselect, sqlfrom);
		PageJson myjson = new PageJson();
		myjson.buildJson(logall);
		renderJson(myjson);
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

		// 调用生成from sql，并构造paramValue
		Map<String, Object> mpara = new HashMap<String, Object>();
		
		String domain_name = getPara("domain_name");
		mpara.put("domain_name",domain_name);
		String sqlselect = ToolGetSql.getSql("tianjian.oplog.pageAllSelect");
		String sqlfrom = ToolGetSql.getSql("tianjian.oplog.pageAllFrom",mpara);

		Page<OpLog> logall = OpLog.dao.paginate(1, 15, sqlselect, sqlfrom);
		PageJson myjson = new PageJson();
		myjson.buildJson(logall);
		renderJson(myjson);
	}
	/*
	 * 搜索日志数据
	 * @return json
	 * @author hujian
	 * @see ToolGetSql
	 * @
	 */
	public void search() {
		String domain_name = getPara("domain_name");
		String user_uuid = getPara("user_uuid");
		String op_type = getPara("op_type");
		String startdate_start = getPara("startdate_start");
		String startdate_end = getPara("startdate_end");
		// 调用生成from sql，并构造paramValue
		Map<String, Object> mpara = new HashMap<String, Object>();
		mpara.put("domain_name",domain_name);
		mpara.put("user_uuid", user_uuid);
		mpara.put("op_type", op_type);
		mpara.put("startdate_start", startdate_start);
		mpara.put("startdate_end", startdate_end);
		System.out.println("getpara:" + mpara);

		String sqlselect = ToolGetSql.getSql("tianjian.oplog.pageSearchSelect");
		String sqlfrom = ToolGetSql.getSql("tianjian.oplog.pageSearchFrom", mpara);

		// Page<OpLog> logsearch = OpLog.dao.paginate(1, 2, sqlselect, sqlfrom);
		Page<Record> logsearch = Db.paginate(1, 15, sqlselect, sqlfrom);
		PageJson myjson = new PageJson();
		myjson.buildJson(logsearch);
		renderJson(myjson);
	}
}
