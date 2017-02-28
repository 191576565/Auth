package com.tianjian.auth.mvc.usrMgmt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.platform.tools.ToolGetSql;

public class UsrMgmtService {
	//初始化时查询，查询session用户机构
	public String selectInitOrganization(String userId){
		String sql = ToolGetSql.getSql("tianjian.usrMgmt.selectInitOrganization");
		List<Record> list = Db.find(sql,userId);
		String org = list.get(0).getStr("org_uuid");
		return org;
	}
	//初始化查询，查询session用户所能看到的用户
	public List<Record> initSelect(String orgUUID){
		String sql = ToolGetSql.getSql("tianjian.usrMgmt.initSelect");
		List<Record> list = Db.find(sql,orgUUID);
		return list;
	}
	//校验用户是否存在
	public boolean checkUserId(String userId){
		String sql = ToolGetSql.getSql("tianjian.usrMgmt.checkUserId");
		boolean chkRst = Db.find(sql, userId).isEmpty();
		return chkRst;
	}
	//查询该用户所能看到的域
	public List<Record> selectDomain(String userId){
		String sql = ToolGetSql.getSql("tianjian.usrMgmt.selectDomain");
		List<Record> list = Db.find(sql,userId);
		return list;
	}
	public List<Record> selectOrganization(String userId){
		String sql = ToolGetSql.getSql("tianjian.usrMgmt.selectOrganization");
		List<Record> list = Db.find(sql,userId);
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
