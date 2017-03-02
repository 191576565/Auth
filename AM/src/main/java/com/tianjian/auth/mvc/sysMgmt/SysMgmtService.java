package com.tianjian.auth.mvc.sysMgmt;

import java.util.List;
import com.tianjian.platform.tools.ToolGetSql;

public class SysMgmtService {
	
	//查询系统信息
	public List<SysMgmt> getData(){
		String sql = ToolGetSql.getSql(SysMgmt.sqlId_sys_select);
		return SysMgmt.dao.find(sql);
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
	
	//重复性验证
	public boolean notRepeated(String scopeCode, String scopeName){
		String sql = ToolGetSql.getSql(SysMgmt.sqlId_sys_repeat);
		return SysMgmt.dao.find(sql, scopeCode, scopeName).isEmpty();
	}
	
	//修改系统信息
	public boolean update(SysMgmt sysMgmt){
		return sysMgmt.update();
	}
	
	//删除系统信息
	public boolean delete(SysMgmt sysMgmt){
		return sysMgmt.delete();
	}
	
	//批量删除系统信息
	public boolean deleteMore(String idValues){
		String[] idValue = idValues.split(",");
		boolean[] flags = new boolean[idValue.length];
		for (int i=0; i<idValue.length; i++){
			flags[i] = SysMgmt.dao.deleteById(idValue[i]);
		}
		for (int i=0; i<flags.length; i++){
			if(flags[i] == false){
				return false;
			}
		}
		return true;
	}

}
