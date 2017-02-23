package com.tianjian.auth.mvc.oplog;

import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.platform.pjson.PageJson;
import com.tianjian.platform.tools.ToolGetSql;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpLogController extends Controller {

	private OpLogService oplogservice = new OpLogService();
	
	/*
	 * 日志首页
	 * @return 
	 * @author hujian
	 * @see 
	 * @
	 */
	public void index() {
		render("sysLog.jsp");
	}
	/*
	 * 静态方法
	 * 保存日志数据到数据库,域，用户名，机构，角色名称都从session拿
	 * @return  
	 * @author hujian
	 * @see jfinal
	 * @TODO：  session完了之后再改对应取值
	 */
	public  void logToDb(String opType,String opContent) {

		OpLog l = new OpLog();
		//从session获取数据
		String doName=getSessionAttr("user");
		String orgName=getSessionAttr("user");
		String roleName=getSessionAttr("user");
		String userName=getSessionAttr("user");
		//Model赋值
		l.set("DOMAIN_NAME", doName);
		l.set("ORG_UNIT_DESC", orgName);
		l.set("USER_NAME", userName);
		l.set("ROLE_NAME", roleName);
		l.set("OP_TYPE", opType);
		l.set("OP_CONTENT", opContent);
		l.set("OP_DATE", new Timestamp(System.currentTimeMillis()));
		l.save();
	}

	/*
	 * 获取日志数据 
	 * @return json 
	 * @author hujian
	 * @see ToolGetSql oplogservice
	 * @TODO：  现在域名写死了FTP，session完了之后再改对应取值
	 */
	public void logdata() {
		// 获取表单参数
		Integer pageSize = getParaToInt("pageSize");
		Integer pageNumber = getParaToInt("pageNumber");
		String user_uuid = getPara("user_uuid");
		String op_type = getPara("op_type");
		String startdate_start = getPara("startdate_start");
		String startdate_end = getPara("startdate_end");
		// 组装sql参数
		Map<String, Object> mpara = oplogservice.getMparam(user_uuid, op_type, startdate_start, startdate_end);
		// 获取select语句
		String selectsql = oplogservice.getSelectSql(OpLog.sqlId_log_select);
		// 获取from语句
		String fromsql = oplogservice.getFromSql(OpLog.sqlId_log_selectfrom, mpara);
		// 获取数据
		PageJson<OpLog> myjson = oplogservice.getPageData(pageSize, pageNumber, selectsql, fromsql);

		renderJson(myjson);
	}

}
