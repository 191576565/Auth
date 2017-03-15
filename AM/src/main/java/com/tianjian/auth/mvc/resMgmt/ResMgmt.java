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
	 * 资源select语句
	 */
	public static final String sqlId_res_select = "tianjian.res.allresdata";
	/*
	 * 资源select语句
	 */
	public static final String sqlId_res_tree = "tianjian.res.restree";
	/*
	 * 资源select语句
	 */
	public static final String sqlId_resmenu_select = "tianjian.res.menuSelect";
	/*
	 *资源delete语句
	 */
	public static final String sqlId_res_delete = "tianjian.res.resdatadelete";
	/*
	 *资源id查重复语句
	 */
	public static final String sqlId_res_residrepeated = "tianjian.res.residrepeated";
	/*
	 *资源nAME查重复语句
	 */
	public static final String sqlId_res_resnamerepeated = "tianjian.res.resnamerepeated";

}
