package com.tianjian.auth.mvc.oplog;

import com.jfinal.plugin.activerecord.Model;

public class OpLog extends Model<OpLog> {
	public static final OpLog dao=new OpLog();	
	//=============================================
	public static final String 	column_uuid          =  "uuid"         ;
	public static final String  column_domain_id     =  "domain_id"    ;
	public static final String  column_domain_name   =  "domain_name"  ;
	public static final String  column_org_unit_desc =  "org_unit_desc";
	public static final String  column_user_name     =  "user_name"    ;
	public static final String  column_role_name     =  "role_name"    ;
	public static final String  column_op_ipadr      =  "op_ipadr"     ;
	public static final String  column_op_type       =  "op_type"      ;
	public static final String  column_op_content    =  "op_content"   ;
	public static final String  column_op_date       =  "op_date"      ;          
	//=============================================
	/*
	 * 日志select语句
	 */
	public static final String sqlId_log_select = "tianjian.oplog.pageAllSelect";
	/*
	 * 日志from语句
	 */
	public static final String sqlId_log_selectfrom = "tianjian.oplog.pageAllFrom";
	
}
