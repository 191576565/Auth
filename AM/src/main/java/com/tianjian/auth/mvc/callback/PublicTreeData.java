package com.tianjian.auth.mvc.callback;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.ICallback;

/**
 * 存储过程调用
 * @author 董华健  dongcb678@163.com
 */
public class GetTreeData implements ICallback {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(GetTreeData.class);
	
	public  String domain_id;

	@Override
	public  String call(Connection conn) throws SQLException {
		CallableStatement proc =conn.prepareCall("{ call proc_orguser_tree(?,?) }");
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
