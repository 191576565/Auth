package com.tianjian.auth.mvc.sysMgmt;

import com.jfinal.plugin.activerecord.Model;

public class OrgMgmt extends Model<OrgMgmt>{

	private static final long serialVersionUID = 1L;
	
	public static final OrgMgmt dao = new OrgMgmt();
	
	public static final String sqlId_root_select = "tianjian.org.rootSelect";
	public static final String sqlId_getOrgInfo = "tianjian.org.getOrgInfo";
	public static final String sqlId_getSubOrg = "tianjian.org.getSubOrg";
	public static final String sqlId_repeatSelect = "tianjian.org.repeatSelect";
}
