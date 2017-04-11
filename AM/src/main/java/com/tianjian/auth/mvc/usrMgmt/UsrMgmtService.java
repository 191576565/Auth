package com.tianjian.auth.mvc.usrMgmt;

import java.util.List;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.auth.mvc.base.BaseSecurityMD5;
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
	//查询该用户所能看到的机构
	public List<Record> selectOrganization(String userId){
		String sql = ToolGetSql.getSql("tianjian.usrMgmt.selectOrganization");
		List<Record> list = Db.find(sql,userId);
		return list;
	}
	//查询该用户所能看到的角色
	public List<Record> selectRole(String userId){
		String sql = ToolGetSql.getSql("tianjian.usrMgmt.selectRole");
		List<Record> list = Db.find(sql,userId);
		return list;
	}
	//批量删除
	public boolean batchDeleteUUID(String[] uuids){
		boolean feedback = false;
		String sql = ToolGetSql.getSql("tianjian.usrMgmt.batchDelete");
		Object[][] obj = new Object[uuids.length][1];
		for (int i = 0; i < uuids.length; i++) {
			obj[i][0] = uuids[i];
		}
		int[] size = Db.batch(sql, obj, 10);
		if(!size.equals(null)&&size.length>0){
			feedback = true;
		}
		return feedback;
	}
	
	//重置密码
	public boolean batchResetUUID(String[] uuids){
		boolean feedback = false;
		String sql = ToolGetSql.getSql("tianjian.usrMgmt.batchReset");
		String pwd=BaseSecurityMD5.encodeMD5Hex("123456");
		Object[][] obj = new Object[uuids.length][2];
		for (int i = 0; i < uuids.length; i++) {
			obj[i][0] = pwd;
			obj[i][1] = uuids[i];
		}
		int[] size = Db.batch(sql, obj, 10);
		if(!size.equals(null)&&size.length>0){
			feedback = true;
		}
		return feedback;
	}
	
	//条件搜索
	public List<Record> paramSelect(String orgUuid, String param){
		String sql = ToolGetSql.getSql("tianjian.usrMgmt.paramSelect");
		String findParam = "%"+param+"%";
		List<Record> list = Db.find(sql,orgUuid,findParam,findParam);
		return list;
	}
}
