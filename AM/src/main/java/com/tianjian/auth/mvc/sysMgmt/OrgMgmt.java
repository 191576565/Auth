package com.tianjian.auth.mvc.sysMgmt;

import com.jfinal.plugin.activerecord.Model;

public class OrgMgmt extends Model<OrgMgmt>{

	private static final long serialVersionUID = 1L;
	
	public static final OrgMgmt dao = new OrgMgmt();
	
	public static final String sqlId_org_select = "tianjian.org.pageSelect";
	public static final String sqlId_sub_select = "tianjian.org.subSelect";
	public static final String sqlId_sub2_select = "tianjian.org.subSelect2";
	public static final String sqlId_getOrgInfo = "tianjian.org.getOrgInfo";
	public static final String sqlId_getSubOrg = "tianjian.org.getSubOrg";
}
