package com.tianjian.auth.mvc.oplog;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.plugin.activerecord.Page;
import com.tianjian.platform.pjson.PageJson;
import com.tianjian.platform.tools.ToolGetSql;

public class OpLogService {
	/*
	 * 组装sql参数，注意字段名和.sql.xml相同
	 * @return
	 * @author hujian
	 * @see 
	 * @
	 */
	public Map<String, Object> getMparam(String user_uuid,String op_type,String startdate_start,String startdate_end){
		Map<String, Object> mpara = new HashMap<String, Object>();
		mpara.put("domain_name", "FTP");
		mpara.put("user_uuid", user_uuid);
		mpara.put("op_type", op_type);
		mpara.put("startdate_start", startdate_start);
		mpara.put("startdate_end", startdate_end);
		return mpara;
	}
	/*
	 * 获取.sql.xml中sql
	 * @return
	 * @author hujian
	 * @see 
	 * @
	 */
	public String getSelectSql(String fromsql){
		return  ToolGetSql.getSql(fromsql);
	}
	/*
	 * 获取.sql.xml中sql，带参数
	 * @return
	 * @author hujian
	 * @see getMparam
	 * @
	 */
	public String getFromSql(String fromsql,Map<String, Object> mpara){
		return  ToolGetSql.getSql(fromsql, mpara);
	}
	/*
	 * 查询数据库，并返回
	 * @return
	 * @author hujian
	 * @see getMparam
	 * @
	 */
	public PageJson<OpLog> getPageData(Integer pageSize,Integer pageNumber,String selectsql,String fromsql){
		Page<OpLog> logall = OpLog.dao.paginate(pageNumber, pageSize, selectsql, fromsql);
		PageJson<OpLog> myjson = new PageJson<OpLog>();
		myjson.buildJson(logall);
		return myjson;
	}
}
