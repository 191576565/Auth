package com.tianjian.platform.tools;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.jfinal.kit.PropKit;
import com.tianjian.platform.constant.ConstantRender;

public class ToolGetSql {
	public static String getSqlNoP(String sqlId) {
		return ToolSqlXml.getSql(sqlId);
	}
	public static String getSqlByP(String sqlId,Map<String, Object> mpara) {
		LinkedList<Object> paramValue = new LinkedList<Object>();
		String sql = ToolSqlXml.getSql(sqlId, mpara, ConstantRender.sql_renderType_beetl, paramValue);
		System.out.println("paramValue:"+paramValue);
		return sql;
	}
	public static String getSqlByDbtype(String sqlId) {
		LinkedList<Object> paramValue = new LinkedList<Object>();
		
		// // 调用生成from sql，并构造paramValue
		Map<String, Object> mpara = new HashMap<String, Object>();

		mpara.put("db_type", PropKit.get("db.type"));

		String sql = ToolSqlXml.getSql(sqlId, mpara, ConstantRender.sql_renderType_beetl, paramValue);
		return sql;
	}
}
