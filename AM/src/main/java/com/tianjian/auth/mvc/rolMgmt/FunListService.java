package com.tianjian.auth.mvc.rolMgmt;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.platform.tools.ToolGetSql;

/**
 * 
 * 角色管理
 * --角色功能菜单选择-业务实现类
 * @author caoguopeng
 * 
 * 
 * ***/
public class FunListService {
	
	
	public List<Record> selectFunList() {
		String sql = ToolGetSql.getSql("tianjian.roleMgmt.selectFunList");
		List<Record> list = Db.find(sql);
		return list;
	}

}
