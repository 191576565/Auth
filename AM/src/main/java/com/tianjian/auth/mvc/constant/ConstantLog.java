package com.tianjian.auth.mvc.constant;

public interface ConstantLog {
	/**
	 * AUTH 三层标题
	 */
	public static final String basemgr  =" BASEMGR";  // 基础配置
	public static final String userauth = "USERAUTH" ; //用户权限
	public static final String syslog   = "SYSLOG" ;   //系统日志
	
	/**
	 * 域操作信息
	 */	
	public static final String dmn     = basemgr+"-DMN";
	public static final String dmn_add = dmn+"-ADD";
	public static final String dmn_chg = dmn+"-CHG";
	public static final String dmn_del = dmn+"-DEL";	
	/**
	 * 机构操作信息
	 */	
	public static final String org     = basemgr+"-ORG";
	public static final String org_add = org+"-ADD";
	public static final String org_chg = org+"-CHG";
	public static final String org_del = org+"-DEL";	
	/**
	 * 用户操作信息
	 */
	public static final String user     = userauth+"-USER" ;
	public static final String user_add = user+"-ADD";
	public static final String user_chg = user+"-CHG";
	public static final String user_del = user+"-DEL";	
	/**
	 * 角色操作信息
	 */	
	public static final String role     = userauth+"-ROLE" ;
	public static final String role_add = role+"-ADD";
	public static final String role_chg = role+"-CHG";
	public static final String role_del = role+"-DEL";	
	/**
	 * 资源操作信息
	 */	
	public static final String res     = userauth+"RES"; 
	public static final String res_add = res+"-ADD";
	public static final String res_chg = res+"-CHG";
	public static final String res_del = res+"-DEL";	
	/**
	 * 数据权限组操作信息
	 */
	public static final String grp      =userauth+"-GRP";	 
	public static final String grp_add = grp+"-ADD";
	public static final String grp_chg = grp+"-CHG";
	public static final String grp_del = grp+"-DEL";	
	/**
	 * 日志  操作信息
	 */
	public static final String log_optype = syslog+"OPTYPE";
	public static final String log_opcontent = syslog+"-OPCONTENT";	
}
