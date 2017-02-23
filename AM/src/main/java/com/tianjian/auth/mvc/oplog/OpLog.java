package com.tianjian.auth.mvc.oplog;

import com.jfinal.plugin.activerecord.Model;
import com.tianjian.auth.mvc.login.Login;

public class OpLog extends Model<OpLog> {
	public static final OpLog dao=new OpLog();	
	
	public static final String sqlId_log_select = "tianjian.oplog.pageAllSelect";
	public static final String sqlId_log_selectfrom = "tianjian.oplog.pageAllFrom";
}
