package com.tianjian.auth.mvc.usrMgmt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.platform.tools.ToolGetSql;

public class UsrMgmtService {
	public List<Record> defaultSelect(){
		String sql = ToolGetSql.getSql("tianjian.usrMgmt.defSel");
		List<Record> list = Db.find(sql);
		return list;
	} 
	public List<Record> getDomain(){
		String sql = ToolGetSql.getSql("tianjian.usrMgmt.selDomain");
		List<Record> list = Db.find(sql);
		return list;
	}
	public List<Record> getOrg(String domainUUID){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("domain_uuid", domainUUID);
		String sql = ToolGetSql.getSql("tianjian.usrMgmt.selDomain", param);
		List<Record> list = Db.find(sql);
		return list;
	}
	public List<Record> getRole(String domainUUID){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("domain_uuid", domainUUID);
		String sql = ToolGetSql.getSql("tianjian.usrMgmt.selRole", param);
		List<Record> list = Db.find(sql);
		return list;
	}
}
