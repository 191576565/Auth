package com.tianjian.auth.mvc.sysMgmt;

import java.util.List;
import com.tianjian.platform.tools.ToolGetSql;

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

}
