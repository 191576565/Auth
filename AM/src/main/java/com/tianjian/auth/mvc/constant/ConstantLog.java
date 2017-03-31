package com.tianjian.auth.mvc.constant;

public interface ConstantLog {
	/**
	 * AUTH 三层标题
	 */
	public static final String basemgr  =" 基础配置";  // 基础配置
	public static final String userauth = "用户权限" ; //用户权限
	public static final String syslog   = "系统日志" ;   //系统日志
	
	/**
	 * 域操作信息
	 */	
	public static final String dmn     = basemgr+"-域管理";
	public static final String dmn_add = dmn+"-新增";
	public static final String dmn_chg = dmn+"-编辑";
	public static final String dmn_del = dmn+"-删除";	
	/**
	 * 机构操作信息
	 */	
	public static final String org     = basemgr+"-机构管理";
	public static final String org_add = org+"-新增";
	public static final String org_chg = org+"-编辑";
	public static final String org_del = org+"-删除";	
	/**
	 * 用户操作信息
	 */
	public static final String user     = userauth+"-用户管理" ;
	public static final String user_add = user+"-新增";
	public static final String user_chg = user+"-编辑";
	public static final String user_del = user+"-删除";	
	/**
	 * 角色操作信息
	 */	
	public static final String role     = userauth+"-角色管理" ;
	public static final String role_add = role+"-新增";
	public static final String role_chg = role+"-编辑";
	public static final String role_del = role+"-删除";	
	/**
	 * 资源操作信息
	 */	
	public static final String res     = userauth+"资源管理"; 
	public static final String res_add = res+"-新增";
	public static final String res_chg = res+"-编辑";
	public static final String res_del = res+"-删除";	
	/**
	 * 数据权限组操作信息
	 */
	public static final String grp      =userauth+"-数据权限组管理";	 
	public static final String grp_add = grp+"-新增";
	public static final String grp_chg = grp+"-编辑";
	public static final String grp_del = grp+"-删除";	
	/**
	 * 日志  操作信息
	 */
	public static final String log_optype = "OPTYPE";
	public static final String log_opcontent = "OPCONTENT";	
}
