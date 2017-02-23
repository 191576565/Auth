package com.tianjian.auth.mvc.sysMgmt;

import com.jfinal.plugin.activerecord.Model;

public class SysMgmt extends Model<SysMgmt> {
	public static final SysMgmt dao=new SysMgmt();	
	
	public static final String sqlId_sys_select = "tianjian.sys.pageSelect";
}
