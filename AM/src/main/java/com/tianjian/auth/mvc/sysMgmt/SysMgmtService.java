package com.tianjian.auth.mvc.sysMgmt;

import java.util.List;
import com.tianjian.platform.tools.ToolGetSql;

public class SysMgmtService {
	
	public List<SysMgmt> getData(){
		
		String sql = ToolGetSql.getSql(SysMgmt.sqlId_sys_select);
		return SysMgmt.dao.find(sql);
		
	}

}
