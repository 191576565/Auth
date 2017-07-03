package com.tianjian.auth.mvc.api;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.auth.mvc.base.BaseService;
import com.tianjian.auth.mvc.model.User;
import com.tianjian.platform.constant.ConstantRender;
import com.tianjian.platform.plugin.ParamInitPlugin;
import com.tianjian.platform.tools.ToolCache;
import com.tianjian.platform.tools.ToolGetSql;
import com.tianjian.platform.tools.ToolSqlXml;

/** 
 *@Function Api 方法           
 *@Declare   实现外部系统数据请求查询方法的实现
 *@Author    谢涛
 *@Return    
 */

public class ApiJsonService extends BaseService{
	
	public static String getSqlByBeetl(String sqlId, Map<String, Object> param){
    	return ToolSqlXml.getSql(sqlId, param, ConstantRender.sql_renderType_beetl);
    }
	
	public static String getSqlByBeetl(String sqlId, Map<String, Object> param, LinkedList<Object> list){
    	return ToolSqlXml.getSql(sqlId, param, ConstantRender.sql_renderType_beetl, list);
    }
	/**
	 * @exception 用户权限查询方法
	 * @author      谢涛
	 * @return       Record  Record
	 * */
	public static  Record  getSelect(String username, String sessionid, String type){
		Record user = ToolCache.get(ParamInitPlugin.cacheStart_user + username);
		if(user == null){
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("column", User.user_id);
			param.put("session", User.user_sid);
			String sql = getSqlByBeetl("model.user.rpmselect"+type, param);
			user = Db.findFirst(sql, username,sessionid);
		}
		return user;
	}
	
	/**
	 * @exception 用户权限查询方法
	 * @author      谢涛
	 * @return       list集合  List<Record>
	 * */
	public static  List<Record> getSelectlist(String username, String sessionid, String type){
		List<Record> user = ToolCache.get(ParamInitPlugin.cacheStart_user + username);
		if(user == null){
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("column", User.user_id);
			param.put("session", User.user_sid);
			String sql = getSqlByBeetl("model.user.rpmselect"+type, param);
			user = (List<Record>) Db.find(sql, username,sessionid,sessionid);
		}
		return user;
	}
	
	/**
	 * @exception 用户注销-外部系统请求
	 * @author      谢 涛
	 * */
	public static int  ulogin(String username){
		User user = ToolCache.get(ParamInitPlugin.cacheStart_user + username);
		int updates='0';
		if(user == null){
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("column", User.user_id);
			param.put("table", User.table_name);
			String sql = getSqlByBeetl("model.user.uloginw", param);
            updates=Db.update(sql, username);
		}
		return updates;
	}
	/**
	 * @ftp 请求数据
	 * */
	public static List<Record> getOrginfo(String username){
		String sql = ToolGetSql.getSql("model.user.ftpSelect");
		List<Record> orglist = Db.find(sql, username);
		return orglist;
	}
}
