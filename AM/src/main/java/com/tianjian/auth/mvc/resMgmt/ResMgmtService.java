package com.tianjian.auth.mvc.resMgmt;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.platform.tools.ToolGetSql;

public class ResMgmtService {
	
	public List<ResMgmt> get(String sqlId) {
		String sql = ToolGetSql.getSql(sqlId);
		List<ResMgmt> allres = ResMgmt.dao.find(sql);
		return allres;
	}
	/*
	 * 根据res_type，res_up_uuid，user_id获取菜单
	 * author： hujian
	 */
	public List<Record> getMenuPage(String sqlId, String userid, String resupuuid, String restype) {		
		String sql=ToolGetSql.getSql(sqlId);
		List<Record> menupage = Db.find(sql, userid, resupuuid, restype);
		return menupage;
	}
}
