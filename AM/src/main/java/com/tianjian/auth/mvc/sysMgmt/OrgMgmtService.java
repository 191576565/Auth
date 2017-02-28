package com.tianjian.auth.mvc.sysMgmt;

import java.util.List;

import com.tianjian.platform.tools.ToolGetSql;

public class OrgMgmtService {
	
	public List<OrgMgmt> getData(String uuid){
		String sql = ToolGetSql.getSql(OrgMgmt.sqlId_org_select);
		return OrgMgmt.dao.find(sql,uuid);
	}
	
	public List<OrgMgmt> subData(String uuid){
		String sql = ToolGetSql.getSql(OrgMgmt.sqlId_sub_select);
		return OrgMgmt.dao.find(sql,uuid,uuid);
	}
	
	public List<OrgMgmt> subData2(String uuid, String up_uuid){
		String sql = ToolGetSql.getSql(OrgMgmt.sqlId_sub2_select);
		return OrgMgmt.dao.find(sql,uuid,up_uuid);
	}

}
