package com.tianjian.auth.mvc.oplog;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.tianjian.platform.tools.ToolGetSql;
import java.util.List;
public class OpLogController extends Controller {
	public void index() {
		render("sysLog.html");
	}
	public void logdata() {
		//Page<OpLog> oplog = OpLog.dao.paginate(2, 15, "select * ", "from SYS_LOG order by domian_uuid");
		String sql = ToolGetSql.getSqlByDbtype("tianjian.oplog.all");
		List<OpLog> logall=OpLog.dao.find(sql);
		System.out.println(logall);
		renderJson(logall);
	}
	public void search() {
		//Page<OpLog> oplog = OpLog.dao.paginate(2, 15, "select * ", "from SYS_LOG order by domian_uuid");
		String sql = ToolGetSql.getSqlByDbtype("tianjian.oplog.all");
		List<OpLog> logall=OpLog.dao.find(sql);
		renderJson(logall);
	}
}
