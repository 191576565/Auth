package com.tianjian.auth.mvc.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.net.SocketTimeoutException
import com.jfinal.kit.HttpKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.auth.mvc.base.BaseSecurityMD5;
import com.tianjian.auth.mvc.constant.ConstantLogin;
import com.tianjian.auth.mvc.model.User;
import com.tianjian.auth.mvc.model.UserService;
import com.tianjian.platform.tools.ToolGetSql;

public class LoginService {

	/**
	 * @Function 用户登录
	 * @Declare 根据用户名和密码进行登录验证
	 * @Author 谢涛
	 * @Return int [ 0：用户不存在 1：用户停用 2：密码次数超限，账户锁定 3：登录成功 4：密码错误，登录失败]
	 */
	public int validUser(String username, String password) {
		// 1.取用户
		User user = UserService.cacheGetByUserName(username);
		if (null == user) {
			return ConstantLogin.login_info_0;// 用户不存在
		}

		// 4.验证密码
		String ApassStr = BaseSecurityMD5.encodeMD5Hex(password); // 密文
		if (ApassStr.equals((String) user.getStr(User.user_pwd))) {
			// 密码验证成功
			/**
			 * 登录成功，添加日志记录方法
			 * 
			 * 
			 **/

			return ConstantLogin.login_info_3;
		} else {
			// 密码验证失败
			/**
			 * 登录失败，添加日志记录方法
			 *
			 *
			 **/
			// Db.use(ConstantInit.db_dataSource_main).update(sql,
			// ToolDateTime.getSqlTimestamp(ToolDateTime.getDate()),
			// errorCount+1, user.getPKValue());
			// 更新缓存
			// User.cacheAdd(user.getPKValue());
			return ConstantLogin.login_info_4;
		}
	}

	/**
	 * @param sqlId
	 * @param para
	 * @return
	 */
	public boolean uhasrole(String sqlId, String para) {
		String sql = ToolGetSql.getSql(sqlId);
		List<Record> ur = Db.find(sql, para);
		if (ur.size() > 0) {
			return true;
		} else {
			return false;
		}

	}

	public String sendPost(String username, String doid) {

		Map<String, String> headers = new HashMap<String, String>();
		System.out.println("domainid:" + doid);
		String sql = ToolGetSql.getSql("tianjian.login.postsid");
		Record s = Db.findFirst(sql, username);
		if (s != null) {
			String sid = s.get("user_sid");
			headers.put("Sid", sid);
		}

		// url
		String sql1 = ToolGetSql.getSql("tianjian.login.posturl");
		Record s1 = Db.findFirst(sql1, doid);
		String url = "";
		if (s1 != null) {
			url = s1.getStr("iport");
			url += "/api/rpm/loginout/am";
		}
		//
		System.out.println("url:" + url);
		String reponse = "";

//		try {
//			reponse = HttpKit.post(url, "tianjian", headers);
//		} catch (Exception e) {
//			System.out.println("发送 POST 请求出现异常！" + e);
//			e.printStackTrace();
//		}
		reponse=sendHttpPost(url,headers);
		return reponse;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendHttpPost(String url, Map<String, String> headers) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// conn.setRequestProperty("sid", "123456789");
			// 设置header
			if (headers != null){
				for (String key : headers.keySet()) {
					//System.out.println("key= " + key + " and value= " + headers.get(key));
					conn.setRequestProperty(key,headers.get(key));
				}
			}
			
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setConnectTimeout(4000);
			conn.setReadTimeout(4000);

			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数

			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (SocketTimeoutException e) {
			System.out.println("发送 POST请求,服务器响应超时！" + e);
			return "SocketTimeoutException";
			
		} catch (Exception e) {
			System.out.println("发送 POST请求出现异常！" + e);
			e.printStackTrace();
		}

		// 使用finally块来关闭输出流、输入流throw new RuntimeException(e);
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
}
