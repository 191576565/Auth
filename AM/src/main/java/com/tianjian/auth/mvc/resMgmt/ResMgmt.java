package com.tianjian.auth.mvc.resMgmt;

import com.jfinal.plugin.activerecord.Model;
import com.tianjian.auth.mvc.oplog.OpLog;

public class ResMgmt extends Model<ResMgmt> {

	public static final ResMgmt dao = new ResMgmt();

	// ==============================================
	public static final String column_uuid = "uuid";
	public static final String column_domain_uuid = "domain_uuid";
	public static final String column_res_id = "res_id";
	public static final String column_res_name = "res_name";
	public static final String column_res_url = "res_url";
	public static final String column_res_up_uuid = "res_up_uuid";
	public static final String column_res_type = "res_type";
	public static final String column_res_class = "res_class";
	public static final String column_res_color = "res_color";
	public static final String column_res_icon = "res_icon";
	public static final String column_sort_id = "sort_id";
	public static final String column_created_date = "created_date";
	public static final String column_creator = "creator";
	public static final String column_modified_date = "modified_date";
	public static final String column_modifier = "modifier";
	public static final String column_memo = "memo";

	// ==============================================

	/*
	 * 日志select语句
	 */
	public static final String sqlId_res_select = "tianjian.res.allresdata";
	/*
	 * 日志select语句
	 */
	public static final String sqlId_resmenu_select = "tianjian.res.menuSelect";
	/*
	 * 日志delete语句
	 */
	public static final String sqlId_resmenu_delete = "tianjian.res.resdatadelete";

}
