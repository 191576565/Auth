package com.tianjian.auth.mvc.menu;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.platform.tools.ToolGetSql;

public class MenuService {
	
	//获取
	public List<Record> getScopeInfo(String userId){
		String sql = ToolGetSql.getSql("tianjian.menu.sysSelect");
		return Db.find(sql,userId);
	}
	
}
