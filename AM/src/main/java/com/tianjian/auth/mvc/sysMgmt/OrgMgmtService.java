package com.tianjian.auth.mvc.sysMgmt;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.platform.tools.ToolGetSql;

public class OrgMgmtService {
	//root用户机构查询
	public List<OrgMgmt> getData(String uuid){
		String sql = ToolGetSql.getSql(OrgMgmt.sqlId_root_select);
		return OrgMgmt.dao.find(sql,uuid);
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
	
	//修改域信息
	public boolean update(OrgMgmt orgMgmt){
		return orgMgmt.update();
	}
	
	//重复性验证(修改)
	public boolean uptReapeated(String domainId, String orgId, String uuid){
		String sql = ToolGetSql.getSql(OrgMgmt.sqlId_uptRepeatSelect);
		if(OrgMgmt.dao.find(sql,domainId,orgId,uuid).size()>0){
			return true;
		}
		return false;
	}
	
	//删除机构信息
	public boolean delete(String uuid){
		String[] subIds = getSubOrg(uuid);
		boolean[] flags = new boolean[subIds.length];
		for(int i=0; i<subIds.length; i++){
			flags[i] = OrgMgmt.dao.deleteById(subIds[i]);
		}
		for(int i=0; i<flags.length; i++){
			if(flags[i] == false){
				return false;
			}
		}
		return OrgMgmt.dao.deleteById(uuid);
	}
	
	/**
	 * 功能:查询该机构下的所有机构
	 * 输入:String uuid
	 * 输出:String[] subIds
	 */
	public String[] getSubOrg(String uuid){
		String sql = ToolGetSql.getSql(OrgMgmt.sqlId_getSubOrg);
		List<OrgMgmt> list = OrgMgmt.dao.find(sql,uuid);
		String[] subIds = new String[list.size()];
		for(int i=0; i<list.size(); i++){
			System.err.println(list.get(i).getStr("uuid"));
			subIds[i] = list.get(i).getStr("uuid");
		}
		return subIds;
	}
	
	/**
	 * 功能:该域下的机构编码是否重复
	 * 输入:String orgId,String domainId
	 * 输出:boolean
	 */
	public boolean isRepeated(String domainId, String orgId){
		String sql = ToolGetSql.getSql(OrgMgmt.sqlId_repeatSelect);
		if(OrgMgmt.dao.find(sql,domainId,orgId).size()>0){
			return true;
		}
		return false;
	}
}
