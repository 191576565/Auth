package com.tianjian.auth.mvc.model;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import com.jfinal.aop.Before;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.tianjian.auth.mvc.base.BaseModel;
import com.tianjian.auth.mvc.base.BaseSecurityMD5;
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
	
}
