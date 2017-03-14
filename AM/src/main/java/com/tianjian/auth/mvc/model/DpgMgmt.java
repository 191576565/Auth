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
	 * 权限数据子页面select语句
	 */
	public static final String sqlId_DG_select = "tianjian.dpgmgmt.gouAllSelect";
	/*
	 * 权限数据子页面from语句
	 */
	public static final String sqlId_DG_selectfrom = "tianjian.dpgmgmt.gouAllFrom";
	
	/*
	 * 域信息获取语句
	 */
	public static final String sqlId_domaininfo = "tianjian.dpgmgmt.domaininfo";
	
	/*
	 * 条件dictcode获取语句
	 */
	public static final String sqlId_dictcode = "tianjian.dpgmgmt.dictcode";
	
	/*
	 * 保存权限组数据 GROUP_INFO
	 */
	public static final String sqlId_ingroupinfo = "tianjian.dpgmgmt.ingroupinfo";
	
	/*
	 * 保存权限组数据 GROUP_URL
	 */
	public static final String sqlId_ingroup_url = "tianjian.dpgmgmt.ingroupurl";
	
	/*
	 * 更新权限组数据 GROUP_INFO
	 */
	public static final String sqlId_upgroupinfo = "tianjian.dpgmgmt.upgroupinfo";
	
	/*
	 * 更新权限组数据 GROUP_URL
	 */
	public static final String sqlId_upgroupurl = "tianjian.dpgmgmt.upgroupurl";
	
	
	/*
	 * 验证权限组id是否存在 GROUP_INFO
	 */
	public static final String sqlid_verifygid = "tianjian.dpgmgmt.verifygid";
	
	/*
	 * 验证权限组URL是否存在 
	 */
	public static final String sqlid_verifyurl = "tianjian.dpgmgmt.verifyurl";
	
	
	/*
	 * 删除权限组  DELETE GROUP_INFO
	 */
	public static final String sqlid_deletegid = "tianjian.dpgmgmt.deletegid";
	

	/*
	 * 删除权限组  DELETE GROUP_URL
	 */
	public static final String sqlid_deleteurl = "tianjian.dpgmgmt.deleteurl";
}
