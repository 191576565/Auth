package com.tianjian.auth.mvc.model;

import com.jfinal.plugin.activerecord.Model;

public class DpgMgmt extends Model<DpgMgmt> {
	public static final DpgMgmt dao=new DpgMgmt();	
	/*
	 * 权限数据select语句
	 */
	public static final String sqlId_DM_select = "tianjian.dpgmgmt.pageAllSelect";
	/*
	 * 权限数据from语句
	 */
	public static final String sqlId_DM_selectfrom = "tianjian.dpgmgmt.pageAllFrom";
	
	/*
	 * 域信息获取语句
	 */
	public static final String sqlId_domaininfo = "tianjian.dpgmgmt.domaininfo";
}
