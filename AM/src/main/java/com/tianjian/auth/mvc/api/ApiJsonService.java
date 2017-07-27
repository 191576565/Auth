package com.tianjian.auth.mvc.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
	 * 
	 * 
	 * 
	 * 
	 * 修改    yeqc      17/07/27
	 * @return  Map<String,Object>
	 * */
	public static  Map<String,Object> getSelectlist(String username, String sessionid, String type){
		List<Record> user = ToolCache.get(ParamInitPlugin.cacheStart_user + username);
		if(user == null){
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("column", User.user_id);
			param.put("session", User.user_sid);
			String sql = getSqlByBeetl("model.user.rpmselect"+type, param);
			user = (List<Record>) Db.find(sql, username,sessionid,sessionid);
		}
		
		//把冗余的八项提出来
		Map<String,Object> common = new LinkedHashMap<String,Object>();
		String[] colNames = user.get(0).getColumnNames();
		for(int i=0; i<colNames.length; i++){
			switch (colNames[i]) {
			case "domain_id":
				common.put("domain_id", user.get(0).getStr("domain_id"));
				break;
			case "domain_name":
				common.put("domain_name", user.get(0).getStr("domain_name"));
				break;
			case "org_unit_desc":
				common.put("org_unit_desc", user.get(0).getStr("org_unit_desc"));
				break;
			case "org_unit_id":
				common.put("org_unit_id", user.get(0).getStr("org_unit_id"));
				break;
			case "role_id":
				common.put("role_id", user.get(0).getStr("role_id"));
				break;
			case "role_name":
				common.put("role_name", user.get(0).getStr("role_name"));
				break;
			case "user_id":
				common.put("user_id", user.get(0).getStr("user_id"));
				break;
			case "user_name":
				common.put("user_name", user.get(0).getStr("user_name"));
				break;
			default:
				break;
			}
		}
		
		//删掉List中的八项
//		System.err.println(common);
		for(int i=0; i<user.size(); i++){
			user.get(i).remove("domain_id","domain_name","org_unit_desc","org_unit_id","role_id","role_name","user_id","user_name");
//			System.err.println(user.get(i));
		}
		
		//重新组合并返回
		common.put("rest_items", user);
		return common;
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
