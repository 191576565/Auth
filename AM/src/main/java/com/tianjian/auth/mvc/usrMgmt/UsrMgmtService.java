package com.tianjian.auth.mvc.usrMgmt;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.platform.tools.ToolGetSql;

public class UsrMgmtService {
	public List<Record> defaultSelect(){
		String sql = ToolGetSql.getSql("tianjian.usrMgmt.defaultSelect");
		List<Record> list = Db.find(sql);
		return list;
	} 
}
