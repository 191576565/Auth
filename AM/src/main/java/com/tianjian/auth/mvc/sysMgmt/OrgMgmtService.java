package com.tianjian.auth.mvc.sysMgmt;

import java.util.List;

import com.tianjian.platform.tools.ToolGetSql;

public class OrgMgmtService {
	
	public List<OrgMgmt> getData(String uuid){
		String sql = ToolGetSql.getSql(OrgMgmt.sqlId_org_select);
		return OrgMgmt.dao.find(sql,uuid);
	}

}
