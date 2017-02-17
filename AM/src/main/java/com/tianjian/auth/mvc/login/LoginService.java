package com.tianjian.auth.mvc.login;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.auth.mvc.login.Login;
import com.tianjian.platform.tools.ToolBase64;
import com.tianjian.platform.tools.ToolGetSql;

public class LoginService {

	public boolean validUser(String username, String password) {
		return true;
/*
		String sql = ToolGetSql.getSqlByDbtype("tianjian.login.ucheck");

		List<Record> loginuser = Db.find(sql, username);
		System.out.println(loginuser);
		
		if (loginuser.size() != 1) {
			return false;
		}
		String pass = loginuser.get(0).get("USER_PWD");
		String depass = ToolBase64.decryptBASE64(pass);
		if (password.equals(depass)) {
			return true;
		} else {
			return false;
		}*/
	}
}
