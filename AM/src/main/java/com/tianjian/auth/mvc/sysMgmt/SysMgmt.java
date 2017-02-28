package com.tianjian.auth.mvc.sysMgmt;

import com.jfinal.plugin.activerecord.Model;

public class SysMgmt extends Model<SysMgmt> {

	private static final long serialVersionUID = 1L;

	public static final SysMgmt dao=new SysMgmt();	
	
	public static final String sqlId_sys_select = "tianjian.sys.pageSelect";
	public static final String sqlId_sys_repeat = "tianjian.sys.repeatSelect";
	public static final String sqlId_sys_root = "tianjian.sys.rootSelect";
	public static final String sqlId_sys_delete = "tianjian.sys.deleteMore";
	
}
