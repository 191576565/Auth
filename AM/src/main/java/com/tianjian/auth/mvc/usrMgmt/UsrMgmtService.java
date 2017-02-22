package com.tianjian.auth.mvc.usrMgmt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.platform.tools.ToolGetSql;

public class UsrMgmtService {
	public List<Record> defaultSelect(){
		String sql = ToolGetSql.getSql("tianjian.usrMgmt.defaultSelect");
		List<Record> list = Db.find(sql);
		return list;
	} 
	public boolean checkUserId(String userId){
		String sql = ToolGetSql.getSql("tianjian.usrMgmt.checkUserId");
		System.out.println(sql);
		boolean chkRst = Db.find(sql, userId).isEmpty();
		return chkRst;
	}
	public List<Record> selectDomain(){
		String sql = ToolGetSql.getSql("tianjian.usrMgmt.selectDomain");
		List<Record> list = Db.find(sql);
		return list;
	}
	public List<Record> selectOrg(String domainUUID){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("domain_uuid", domainUUID);
		String sql = ToolGetSql.getSql("tianjian.usrMgmt.selectOrg", param);
		List<Record> list = Db.find(sql);
		return list;
	}
	public List<Record> selectRole(String domainUUID){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("domain_uuid", domainUUID);
		String sql = ToolGetSql.getSql("tianjian.usrMgmt.selectRole", param);
		List<Record> list = Db.find(sql);
		return list;
	}
}
