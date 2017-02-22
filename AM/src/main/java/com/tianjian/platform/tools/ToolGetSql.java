package com.tianjian.platform.tools;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.jfinal.kit.PropKit;
import com.tianjian.platform.constant.ConstantRender;
/*
 *根据.sql.xml获取sql语句
 *全为静态方法
 *@author  hujian
 *@version 1.0
 *@see   jfinaluib 3.0
 *@
 */
public class ToolGetSql {
	/*
	 *根据.sql.xml获取静态sql语句，里面封装了db_type参数
	 *@param [sqlId][xml文件中sql的id号]
	 *@return 字符串的sql
	 *@author  hujian
	 *@see   jfinaluib 3.0
	 *@
	 */
	public static String getSql(String sqlId) {	
		Map<String, Object> mpara = new HashMap<String, Object>();
		mpara.put("db_type", PropKit.get("db.type"));
		String sql = ToolSqlXml.getSql(sqlId, mpara, ConstantRender.sql_renderType_beetl);
		return sql;
	}
	/*
	 *根据.sql.xml获取静态sql语句，里面封装了db_type参数
	 *@param [sqlId][xml文件中sql的id号]
	 *@param [mpara][sql语句中带动态参数值]
	 *@notice sql参数格式#'$param$'#字符型  #$startdate_start$#数值型
	 *@return 字符串的sql
	 *@author  hujian
	 *@see   jfinaluib 3.0
	 *@
	 */
	public static String getSql(String sqlId,Map<String, Object> mpara) {
		LinkedList<Object> paramValue = new LinkedList<Object>();
		mpara.put("db_type", PropKit.get("db.type"));
		String sql = ToolSqlXml.getSql(sqlId, mpara, ConstantRender.sql_renderType_beetl, paramValue);
		return sql;
	}
}
