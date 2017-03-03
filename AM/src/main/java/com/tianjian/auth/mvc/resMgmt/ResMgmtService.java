package com.tianjian.auth.mvc.resMgmt;

import java.sql.Timestamp;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.platform.tools.ToolGetSql;

public class ResMgmtService {
	/*
	 * 资源的--查询
	 */
	public List<ResMgmt> get(String sqlId) {
		String sql = ToolGetSql.getSql(sqlId);
		List<ResMgmt> allres = ResMgmt.dao.find(sql);
		return allres;
	}
	public List<Record> gettree(String sqlId) {
		String sql = ToolGetSql.getSql(sqlId);
		List<Record> tree = Db.find(sql);
		return tree;
	}
	public void setResParam(ResMgmt res, Object userinfo) {
		// 获取session中数据
		String userid = ((Record) userinfo).getStr("user_id");
		
		res.set(ResMgmt.column_created_date, new Timestamp(System.currentTimeMillis()));
		res.set(ResMgmt.column_creator, userid);
	}

	public void updateResParam(ResMgmt res, Object userinfo) {
		String userid = ((Record) userinfo).getStr("user_id");
		
		res.set(ResMgmt.column_modified_date, new Timestamp(System.currentTimeMillis()));
		res.set(ResMgmt.column_modifier, userid);
	}
	public int delmore(String sqlId,String uuid) {
		String sql = ToolGetSql.getSql(sqlId);
		int i=Db.update(sql,uuid);
		return i;
	}
	/*
	 * 根据res_type，res_up_uuid，user_id获取菜单 author： hujian
	 */
	public List<Record> getMenuPage(String sqlId, String userid, String resupuuid, String restype) {
		String sql = ToolGetSql.getSql(sqlId);
		List<Record> menupage = Db.find(sql, userid, resupuuid, restype);
		return menupage;
	}
}
