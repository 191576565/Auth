package com.tianjian.auth.mvc.sysMgmt;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.platform.tools.ToolGetSql;

public class OrgMgmtService {
	//查询根机构
	public List<OrgMgmt> getData(String uuid){
		String sql = ToolGetSql.getSql(OrgMgmt.sqlId_org_select);
		return OrgMgmt.dao.find(sql,uuid);
	}
	
	//查询二级机构
	public List<OrgMgmt> subData(String uuid){
		String sql = ToolGetSql.getSql(OrgMgmt.sqlId_sub_select);
		return OrgMgmt.dao.find(sql,uuid,uuid);
	}
	
	//查询下层机构(无限循环)
	public List<OrgMgmt> subData2(String uuid, String up_uuid){
		String sql = ToolGetSql.getSql(OrgMgmt.sqlId_sub2_select);
		return OrgMgmt.dao.find(sql,uuid,up_uuid);
	}
	
	//获取域信息
	public List<Record> getScopeInfo(String uuid){
		return Db.find("select t.* from SYS_DOMAIN_INFO t where t.UUID = ?",uuid);
	}
	
	//获取机构信息
	public List<OrgMgmt> getOrgInfo(String uuid){
		String sql = ToolGetSql.getSql(OrgMgmt.sqlId_getOrgInfo);
		return OrgMgmt.dao.find(sql,uuid);
	}
	
	//新增机构信息
	public void save(OrgMgmt orgMgmt){
		orgMgmt.save();
	}
}
