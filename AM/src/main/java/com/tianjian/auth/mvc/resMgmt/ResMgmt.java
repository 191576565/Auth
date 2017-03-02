package com.tianjian.auth.mvc.resMgmt;

import com.jfinal.plugin.activerecord.Model;
import com.tianjian.auth.mvc.oplog.OpLog;

public class ResMgmt extends Model<ResMgmt> {
	
	public static final ResMgmt dao=new ResMgmt();	
	/*
	 * 日志select语句
	 */
	public static final String sqlId_res_select = "tianjian.res.allresdata";
	/*
	 * 日志select语句
	 */
	public static final String sqlId_resmenu_select = "tianjian.res.menuSelect";
	
}
