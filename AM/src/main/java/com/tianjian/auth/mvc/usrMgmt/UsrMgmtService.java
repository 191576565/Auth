package com.tianjian.auth.mvc.usrMgmt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.platform.tools.ToolGetSql;

public class UsrMgmtService {
	
	public ArrayList<String> selectDomainOrg(String userId){
		String sql = ToolGetSql.getSql("tianjian.usrMgmt.checkUserId");
		List<Record> list = Db.find(sql,userId);
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add(list.get(0).getStr("domain_uuid"));
		arrayList.add(list.get(0).getStr("org_uuid"));
		return arrayList;
	}
	public List<Record> initSelect(ArrayList<?> param){
		String sql = ToolGetSql.getSql("tianjian.usrMgmt.initSelect");
		//0-->DOMAIN_UUID;1-->ORG_UUID
		List<Record> list = Db.find(sql,param.get(0).toString(),param.get(1).toString());
		return list;
	}
	public boolean checkUserId(String userId){
		String sql = ToolGetSql.getSql("tianjian.usrMgmt.checkUserId");
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
