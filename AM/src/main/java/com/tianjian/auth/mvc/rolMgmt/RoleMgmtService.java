package com.tianjian.auth.mvc.rolMgmt;

import java.util.List;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.auth.mvc.sysMgmt.SysMgmt;
import com.tianjian.platform.tools.ToolGetSql;


 /**
 * 角色管理
 * --业务实现类
 * @author caoguopeng
 */

public class RoleMgmtService {
	//查询角色信息表，用于前端页面展示
	public List<Record> defaultSelect() {
		//通过配置文件获取查询sql语句
		String sql = ToolGetSql.getSql("tianjian.roleMgmt.defaultSelect");
		//将查询结果存入集合，返回给客户端
		List<Record> list = Db.find(sql);
		return list;
	}
	
	//新增角色
	public void save(RoleMgmt roleMgmt) {
		roleMgmt.save();
	}
	
	//新增角色重复性验证
	public boolean notRepeated(String scopeCode, String scopeName){
		String sql = ToolGetSql.getSql("tianjian.roleMgmt.repeatSelect");
		return RoleMgmt.dao.find(sql, scopeCode, scopeName).isEmpty();
	}
	
	//修改角色
	public boolean update(RoleMgmt roleMgmt) {
		return roleMgmt.update();
	}
	
	//删除角色信息
	public boolean delete(RoleMgmt roleMgmt) {
		return roleMgmt.delete();
	}

}
