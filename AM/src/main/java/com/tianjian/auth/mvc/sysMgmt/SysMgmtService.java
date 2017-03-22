package com.tianjian.auth.mvc.sysMgmt;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.platform.tools.ToolGetSql;
import com.tianjian.platform.tools.ToolYeqc;

public class SysMgmtService {
	
	//root用户查询系统信息
	public List<Record> getData(){
		String sql = ToolGetSql.getSql(SysMgmt.sqlId_sys_select);
		List<Record> list = Db.find(sql);
//		for(int i=0; i<list.size(); i++){
//			String memo = list.get(i).getStr("memo");
//			list.get(i).set("memo", ToolYeqc.HtmlToText(memo));
//		}
		return list;
	}
	
	//非root用户查询系统信息
	public List<Record> notRootData(String userId){
		String sql = ToolGetSql.getSql("tianjian.sys.notRootpageSelect");
		return Db.find(sql,userId);
	}
	
	//查询用户角色
	public List<Record> userRole(String userId){
		String sql = ToolGetSql.getSql("tianjian.sys.roleSelect");
		return Db.find(sql,userId);
	}
	
	//查询root域uuid
	public List<SysMgmt> getRoot(){
		String sql = ToolGetSql.getSql(SysMgmt.sqlId_sys_root);
		return SysMgmt.dao.find(sql);
	}
	
	//新增系统信息
	public void save(SysMgmt sysMgmt){
		sysMgmt.save();
	}
	
	//重复性验证(新增)
	public boolean notRepeated(String scopeCode, String scopeName){
		String sql = ToolGetSql.getSql(SysMgmt.sqlId_sys_repeat);
		return SysMgmt.dao.find(sql, scopeCode, scopeName).isEmpty();
	}
	
	//重复性验证(修改)
	public boolean uptRepeated(String scopeCode, String scopeName, String uuid){
		String sql = ToolGetSql.getSql(SysMgmt.sqlId_uptReapeatSelect);
		if(SysMgmt.dao.find(sql, scopeCode, scopeName, uuid).size()>0){
			return true;
		}
		return false;
	}
	
	//修改系统信息
	public boolean update(SysMgmt sysMgmt){
		return sysMgmt.update();
	}
	
	//删除系统信息
	public boolean delete(SysMgmt sysMgmt){
		return sysMgmt.delete();
	}
	
	//判断是否关联机构
	public boolean orgSelect(String domainUuid){
		String sql = ToolGetSql.getSql("tianjian.sys.orgSelect");
		List<Record> list = Db.find(sql,domainUuid);
		if(list.size()>0){
			return true;
		}
		return false;
	}
	
	//批量删除系统信息
	public boolean deleteMore(String idValues){
		String[] idValue = idValues.split(",");
		boolean[] flags = new boolean[idValue.length];
		//若没有关联机构则允许删除
		for (int i=0; i<idValue.length; i++){
			if(orgSelect(idValue[i])){
				return false;
			}
		}
		for(int i=0; i<idValue.length; i++){
			SysMgmt.dao.deleteById(idValue[i]);
		}
		return true;
	}

}
