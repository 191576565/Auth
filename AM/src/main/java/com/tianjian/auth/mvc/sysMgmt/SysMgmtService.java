package com.tianjian.auth.mvc.sysMgmt;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.tianjian.auth.mvc.oplog.OpLog;
import com.tianjian.platform.pjson.PageJson;
import com.tianjian.platform.tools.ToolGetSql;

public class SysMgmtService {
	
	public String getSelectSql(String fromsql){
		return  ToolGetSql.getSql(fromsql);
	}
	
	public List<SysMgmt> getData(String selectsql){
		List<SysMgmt> sysAll = SysMgmt.dao.find(selectsql);
		return sysAll;
		
	}

}
