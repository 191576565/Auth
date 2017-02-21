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
		PageJson myjson=new PageJson();
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

	public void logdata1() {
		// Page<OpLog> oplog = OpLog.dao.paginate(2, 15, "select * ", "from
		// SYS_LOG order by domian_uuid");
		String sql = ToolGetSql.getSql("tianjian.oplog.pageAllSelect");
		List<OpLog> logall = OpLog.dao.find(sql);

		System.out.println(logall);
		renderJson(logall);
	}

	public void logdata() {
		// Page<OpLog> oplog = OpLog.dao.paginate(2, 15, "select * ", "from
		// SYS_LOG order by domian_uuid");
		String sqlselect = ToolGetSql.getSql("tianjian.oplog.pageAllSelect");
		String sqlfrom = ToolGetSql.getSql("tianjian.oplog.pageAllFrom");

		// List<OpLog> logall = OpLog.dao.find(sql);
		Page<OpLog> logall = OpLog.dao.paginate(1, 2, sqlselect, sqlfrom);
		// Page<Record> logall = Db.paginate(1, 2, sqlselect, sqlfrom);
		// logall.getTotalRow();
		List<OpLog> rows = logall.getList();
		System.out.println(logall);
		renderJson(logall);
	}

	public void search1() {
		String user_uuid = getPara("user_uuid");
		String op_type = getPara("op_type");
		String startdate_start = getPara("startdate_start");
		String startdate_end = getPara("startdate_end");
		// 调用生成from sql，并构造paramValue
		Map<String, Object> mpara = new HashMap<String, Object>();

		mpara.put("user_uuid", user_uuid);
		mpara.put("op_type", op_type);
		mpara.put("startdate_start", startdate_start);
		mpara.put("startdate_end", startdate_end);
		String sql = ToolGetSql.getSql("tianjian.oplog.search", mpara);
		List<OpLog> logsearch = OpLog.dao.find(sql);
		System.out.println("logsearch:" + logsearch);
		renderJson(logsearch);
	}

	public void search() {
		String user_uuid = getPara("user_uuid");
		String op_type = getPara("op_type");
		String startdate_start = getPara("startdate_start");
		String startdate_end = getPara("startdate_end");
		// 调用生成from sql，并构造paramValue
		Map<String, Object> mpara = new HashMap<String, Object>();

		mpara.put("user_uuid", user_uuid);
		mpara.put("op_type", op_type);
		mpara.put("startdate_start", startdate_start);
		mpara.put("startdate_end", startdate_end);
		System.out.println("getpara:" + mpara);

		String sqlselect = ToolGetSql.getSql("tianjian.oplog.pageSearchSelect");
		String sqlfrom = ToolGetSql.getSql("tianjian.oplog.pageSearchFrom", mpara);

		// Page<OpLog> logsearch = OpLog.dao.paginate(1, 2, sqlselect, sqlfrom);
		Page<Record> logsearch = Db.paginate(1, 2, sqlselect, sqlfrom);
		System.out.println("logsearch:" + logsearch);
		renderJson(logsearch);
	}
}
