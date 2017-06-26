package com.tianjian.auth.mvc.resMgmt;

import java.sql.Timestamp;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.auth.mvc.sysMgmt.OrgMgmt;
import com.tianjian.platform.tools.ToolGetSql;

public class ResMgmtService {
	
	/*
	 * 资源-懒加载
	 */
	public List<Record> getResData(String resUpUuid){
		String sql = ToolGetSql.getSql("tianjian.res.getResData");
		List<Record> list = Db.find(sql, resUpUuid);
		return list;
	}
	
	//重复性验证
	public boolean notRepeated(String resId, String resName){
		String sql = ToolGetSql.getSql("tianjian.res.repeatSelect");
		boolean bool = Db.find(sql,resId,resName).isEmpty();
		return bool;
	}
	
	//编辑资源,yeqc
	public void resUpdate(ResMgmt resMgmt){
		resMgmt.update();
	}
	
	//新增资源,yeqc
	public void resSave(ResMgmt resMgmt){
		resMgmt.save();
	}
	
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
	
	/**
	 * 功能:资源id，name是否重复
	 * 输入:
	 * 输出:boolean
	 */
	public boolean resIdisRepeated(String sqlId,String resId){
		String sql = ToolGetSql.getSql(sqlId);
		return ResMgmt.dao.find(sql,resId).isEmpty();			
	}
	public boolean resNameisRepeated(String sqlId,String resName){
		String sql = ToolGetSql.getSql(sqlId);
		return ResMgmt.dao.find(sql,resName).isEmpty();			
	}
	/*
	 * 根据res_type，res_up_uuid，user_id获取菜单 author： hujian
	 */
	public List<Record> getMenuPage(String sqlId, String userid, String resupuuid, String restype) {
		String sql = ToolGetSql.getSql(sqlId);
		List<Record> menupage = Db.find(sql, userid, resupuuid);//, restype);
		return menupage;
	}
}
