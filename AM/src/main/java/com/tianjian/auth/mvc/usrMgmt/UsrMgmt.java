package com.tianjian.auth.mvc.usrMgmt;

import com.jfinal.plugin.activerecord.Model;


public class UsrMgmt extends Model<UsrMgmt> {
	public static final UsrMgmt dao = new UsrMgmt();
	//hujian add
	public static final String 	column_uuid          =  "uuid"         ;
	public static final String  column_user_id    =  "user_id"    ;
	public static final String  column_user_pwd   =  "user_pwd"  ;
}
