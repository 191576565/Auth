package com.tianjian.auth.mvc.oplog;

import com.jfinal.plugin.activerecord.Model;

public class OpLog extends Model<OpLog> {
	public static final OpLog dao=new OpLog();	
	/*
	 * 日志select语句
	 */
	public static final String sqlId_log_select = "tianjian.oplog.pageAllSelect";
	/*
	 * 日志from语句
	 */
	public static final String sqlId_log_selectfrom = "tianjian.oplog.pageAllFrom";
}
