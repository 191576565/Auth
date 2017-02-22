package com.tianjian.auth.mvc.rolMgmt;

import java.util.List;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.platform.tools.ToolGetSql;


 /**
 * 
 * 
 * 角色管理
 * --业务实现类
 * @author caoguopeng
 * 
 * **/

public class RoleMgmtService {
	
	
	
	//查询角色信息表，用于前端页面展示
	public List<Record> defaultSelect() {
		
		//通过配置文件获取查询sql语句
		String sql = ToolGetSql.getSql("tianjian.roleMgmt.defaultSelect");
		
		//将查询结果存入集合，返回给客户端
		List<Record> list = Db.find(sql);
		return list;
	}

}
