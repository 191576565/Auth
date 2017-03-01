package com.tianjian.auth.mvc.oplog;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.auth.mvc.constant.ConstantLog;
import com.tianjian.auth.mvc.login.LoginController;

public class GlobalLogInterceptor implements Interceptor {
	private static final Log log = Log.getLog(GlobalLogInterceptor.class);
	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		inv.invoke();
		String optype = inv.getController().getAttr(ConstantLog.log_optype);
		String opcontent = inv.getController().getAttr(ConstantLog.log_opcontent);

		if ((optype != null) && (opcontent!=null) ){
			log.info("开始写数据库日志");
			//获取session
			Object userinfo = inv.getController().getSessionAttr("userinfo");
			//返回OpLog对象
			OpLog l=getSysLog(inv.getController().getRequest(),userinfo);
			//
			l.save();
		}

	}

	/**
	 * 创建日志对象,并初始化一些属性值
	 * 
	 * @param request
	 * @return
	 */
	public OpLog getSysLog(HttpServletRequest request,Object userinfo) {
		
		
		String ip = getIpAddr(request);
		String opType = (String)request.getAttribute("OPTYPE"); 
		String opContent = (String)request.getAttribute("OPCONTENT");
		
		//
		String doId=((Record) userinfo).getStr("domain_id");
		String doName=((Record) userinfo).getStr("domain_name");
		String orgName=((Record) userinfo).getStr("org_unit_desc");
		String roleName=((Record) userinfo).getStr("role_names");
		String userName=((Record) userinfo).getStr("user_id");
		
		OpLog l = new OpLog();
		
		l.set(OpLog.column_domain_id, doId);
		l.set(OpLog.column_domain_name, doName);
		l.set(OpLog.column_org_unit_desc, orgName);
		l.set(OpLog.column_user_name, userName);
		l.set(OpLog.column_role_name, roleName);
		//------------
		l.set(OpLog.column_op_ipadr, ip);
		l.set(OpLog.column_op_type, opType);
		l.set(OpLog.column_op_content, opContent);
		l.set(OpLog.column_op_date, new Timestamp(System.currentTimeMillis()));
		return l;
	}

	/**
	 * 获取客户端IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
