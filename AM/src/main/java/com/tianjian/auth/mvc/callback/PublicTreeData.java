package com.tianjian.auth.mvc.callback;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.ICallback;

/**
 * 存储过程调用
 * @author 董华健  dongcb678@163.com
 */
public class PublicTreeData implements ICallback {

	private static final Log log = Log.getLog(PublicTreeData.class);
	
	public  String domain_id; 
	
	public  String dictcode;

	@Override
	public  String call(Connection conn) throws SQLException {
		CallableStatement proc =conn.prepareCall( "{call proc_"+dictcode+"_tree(?,?)}");
		proc.setString(1, domain_id);
		proc.registerOutParameter(2, Types.VARCHAR);
		proc.execute();
		String tree = proc.getString(2);
		return tree;
	}
	

	public String getDomainid() {
		return domain_id;
	}

	public void setDomainid(String domain_id) {
		this.domain_id = domain_id;
	}
	
}
