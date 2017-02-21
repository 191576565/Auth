<<<<<<< HEAD
package com.tianjian.auth.mvc.login;

import com.tianjian.auth.mvc.base.BaseSecurityMD5;
import com.tianjian.auth.mvc.constant.ConstantLogin;
import com.tianjian.auth.mvc.model.User;
import com.tianjian.auth.mvc.model.UserService;

public class LoginService {

	 /** 
	 *@Function 用户登录            
	 *@Declare   根据用户名和密码进行登录验证
	 *@Author    谢涛
	 *@Return    int  [ 0：用户不存在  1：用户停用    2：密码次数超限，账户锁定   3：登录成功   4：密码错误，登录失败]
	 */
	public int validUser(String username, String password) {
		// 1.取用户
		User user = UserService.cacheGetByUserName(username);
		if (null == user) {
			return ConstantLogin.login_info_0;// 用户不存在
		}

		// 4.验证密码
		String ApassStr = BaseSecurityMD5.encodeMD5Hex(password);		// 密文
		if(ApassStr.equals((String)user.getStr(User.user_pwd))){
			// 密码验证成功
			/**
			 * 登录成功，添加日志记录方法
			 *
			 *
			 **/
			//AuthInterceptor.setCurrentUser(request, response, user, autoLogin);// 设置登录账户
			return ConstantLogin.login_info_3;
		} else {
			// 密码验证失败
			/**
			 * 登录失败，添加日志记录方法
			 *
			 *
			 **/
			//Db.use(ConstantInit.db_dataSource_main).update(sql, ToolDateTime.getSqlTimestamp(ToolDateTime.getDate()), errorCount+1, user.getPKValue());
			// 更新缓存
			//User.cacheAdd(user.getPKValue());
			return ConstantLogin.login_info_4;
		}
	}
}
=======
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
/*              test
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
>>>>>>> branch 'master' of https://github.com/191576565/Auth
