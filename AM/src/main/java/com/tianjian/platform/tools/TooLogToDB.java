package com.tianjian.platform.tools;


import com.tianjian.auth.mvc.oplog.OpLog;
import com.tianjian.auth.mvc.oplog.OpLogService;

public class TooLogToDB  {
	
	private OpLogService oplogservice = new OpLogService();

	/*
	 * 域，用户名，机构，角色名称都从session拿
	 * 
	 * @return
	 * 
	 * @author hujian
	 * 
	 * @see jfinal
	 * 
	 * @TODO： 
	 */
	public void logToDb(Object userinfo,String opType, String opContent) {

		OpLog l = new OpLog();
		// 从session获取数据
		//Object userinfo = getSessionAttr("userinfo");
		// 将数据放进model
		oplogservice.getsessiondata(userinfo, l);
		// 保存
		oplogservice.saveLogData(l, opType, opContent);

	}
}
