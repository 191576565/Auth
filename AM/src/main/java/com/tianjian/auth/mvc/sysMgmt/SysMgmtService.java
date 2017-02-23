package com.tianjian.auth.mvc.sysMgmt;

import java.util.List;
import com.tianjian.platform.tools.ToolGetSql;
import com.jfinal.plugin.activerecord.Db;

public class SysMgmtService {
	
	//查询系统信息
	public List<SysMgmt> getData(){
		String sql = ToolGetSql.getSql(SysMgmt.sqlId_sys_select);
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

}
