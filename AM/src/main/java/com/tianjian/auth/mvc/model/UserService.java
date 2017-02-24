package com.tianjian.auth.mvc.model;




import java.util.HashMap;
import java.util.LinkedList;

import java.util.Map;




import com.jfinal.aop.Before;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;

import com.jfinal.plugin.activerecord.tx.Tx;


import com.tianjian.platform.constant.ConstantRender;
import com.tianjian.platform.plugin.ParamInitPlugin;
import com.tianjian.platform.tools.ToolCache;
import com.tianjian.platform.tools.ToolSqlXml;

public class UserService   {

	private static final Log log = Log.getLog(UserService.class);

	public static final String serviceName = "userService";

	public static String getSqlByBeetl(String sqlId, Map<String, Object> param){
    	return ToolSqlXml.getSql(sqlId, param, ConstantRender.sql_renderType_beetl);
    }
	
	public static String getSqlByBeetl(String sqlId, Map<String, Object> param, LinkedList<Object> list){
    	return ToolSqlXml.getSql(sqlId, param, ConstantRender.sql_renderType_beetl, list);
    }
	
	/**
	 * 获取缓存
	 * @param userName
	 * @return
	 */
	public static User cacheGetByUserName(String userName){
		User user = ToolCache.get(ParamInitPlugin.cacheStart_user + userName);
		if(user == null){
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("column", User.user_id);
			param.put("table", User.table_name);
			String sql = getSqlByBeetl("model.user.column", param);
			user = User.dao.findFirst(sql, userName);
		}
		return user;
	}
	
	/**
	 * @exception 用户登录成功后更新数据库状态
	 * @author      谢 涛
	 * */
	@Before(Tx.class)
	public static  int  login(String username,String Sessionid,String type){
		User user = ToolCache.get(ParamInitPlugin.cacheStart_user + username);
		int updates='0';
		if(user == null){
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("column", User.user_id);
			param.put("table", User.table_name);
			String sql = getSqlByBeetl("model.user.ulogino", param);
	        updates=Db.update(sql,type,Sessionid,username) ;
	       
		}
		return updates;
	}
	
	/**
	 * @exception 用户登录成功后更新数据库状态
	 * @author      谢 涛
	 * */
	@Before(Tx.class)
	public static  int  ulogin(String username,String Sessionid,String type){
		User user = ToolCache.get(ParamInitPlugin.cacheStart_user + username);
		int updates='0';
		if(user == null){
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("column", User.user_id);
			param.put("table", User.table_name);
			String sql = getSqlByBeetl("model.user.ulogino", param);
	        updates=Db.update(sql,type,Sessionid,username) ;
		}
		return updates;
	}
	
	/**
	 * @exception 全局监听器检查用户是登录
	 * @author      谢 涛
	 * */
	public static boolean  login_status(String username){
		User	user=User.dao.findFirst("select loggin_status from sys_user_info where user_id = ? and loggin_status='1' ",username) ;
		if (user==null){
			return false;
		}
		return true;
	}
	
}
