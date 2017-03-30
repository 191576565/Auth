package com.tianjian.auth.mvc.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.auth.mvc.base.BaseService;
import com.tianjian.auth.mvc.model.User;
import com.tianjian.platform.plugin.ParamInitPlugin;
import com.tianjian.platform.tools.ToolCache;
import com.tianjian.platform.tools.ToolGetSql;



/**
 * rpm app 登录api的service层
 *
 * @author  [hujian]
 * @version [1.0]
 * @date    [2017年3月30日]
 */
public class ApiLoginService extends BaseService {
	public void appLogin(String sqlId,String userid,String sid,String loginstatus){
		String sql = ToolGetSql.getSql(sqlId);
		Db.update(sql, loginstatus,sid,userid);
	}
	
	public int appLogout(String sqlId,String userid,String sid,String loginstatus){
		String sql = ToolGetSql.getSql(sqlId);
		return Db.update(sql, loginstatus,sid,userid);
	}
	public static  List<Record> getSelectlist(String username, String sessionid, String type){
		List<Record> user = ToolCache.get(ParamInitPlugin.cacheStart_user + username);
		if(user == null){
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("column", User.user_id);
			param.put("session", User.user_sid_m);
			String sql = getSqlByBeetl("tianjian.app.rpmselect"+type, param);
			user = (List<Record>) Db.find(sql, username,sessionid);
		}
		return user;
	}
	
}
