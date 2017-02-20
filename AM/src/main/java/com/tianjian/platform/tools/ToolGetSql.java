package com.tianjian.platform.tools;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.jfinal.kit.PropKit;
import com.tianjian.platform.constant.ConstantRender;

public class ToolGetSql {
	public static String getSql(String sqlId) {
		
		Map<String, Object> mpara = new HashMap<String, Object>();
		mpara.put("db_type", PropKit.get("db.type"));
		String sql = ToolSqlXml.getSql(sqlId, mpara, ConstantRender.sql_renderType_beetl);
		return sql;
	}
	public static String getSql(String sqlId,Map<String, Object> mpara) {
		LinkedList<Object> paramValue = new LinkedList<Object>();
		mpara.put("db_type", PropKit.get("db.type"));
		String sql = ToolSqlXml.getSql(sqlId, mpara, ConstantRender.sql_renderType_beetl, paramValue);
		return sql;
	}
}
