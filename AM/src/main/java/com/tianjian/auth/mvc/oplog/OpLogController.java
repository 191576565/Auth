package com.tianjian.auth.mvc.oplog;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

public class OpLogController extends Controller {
	public void index() {
		Page<OpLog> oplog = OpLog.dao.paginate(2, 15, "select * ", "from sys_op_logs order by op_user_id");
		renderJson(oplog);
	}
}
