package com.tianjian.auth.mvc.login;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.kit.HttpKit;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.auth.mvc.base.BaseSessionController;
import com.tianjian.auth.mvc.handler.GlobalInterceptor;
import com.tianjian.auth.mvc.menu.MenuService;
import com.tianjian.auth.mvc.model.UserService;

/** 
 *@Company  重庆天健金管科技股份有限公司
 *@Function   用户登录控制器            
 *@Declare     1.用户登录进行控制 2.对外部系统进行交互 
 *@Author      谢涛
 */

public class LoginController extends Controller {
	private LoginService loginservice=new LoginService();
	private MenuService menuService = new MenuService();
	private static final Log log = Log.getLog(LoginController.class);
	public void index() {
		log.info("welcome to login");
		render("login_v2.html");

	}
	
	public void init_login() {
		log.info("welcome to init_login");
		render("login_v2.html");
		
		Map<String, String> headers=new HashMap<String, String>();
		headers.put("Sid", "123456");
		HttpKit.post("www.baidu.com", null, headers);

	}
	/**
	 * hujian 
	 * 登录后处理
	 */
	public void loginafter(){
		String sessionId = (String) getRequest().getSession().getAttribute("usersessionid");
		Object userinfo = getSessionAttr("userinfo");
		String userName=((Record) userinfo).getStr("user_id");
		setAttr("userid", userName);
		setAttr("sid", sessionId);
		render("login_after.jsp");
	}
	 /** 
		 *@Function 用户登录            
		 *@Declare   实现用户登录功能：用户密码认证，用户Session绑定，用户权限交互-登录
		 *@Author    谢涛
		 *@Return    String  sessionId
		 */
	@Clear(GlobalInterceptor.class)
	public void validLogin() {
		BaseSessionController SessionController=new BaseSessionController();
		String username=getPara("username");
		String password=getPara("password");
		//用户登录认证
		int b=loginservice.validUser(username,password);
		if(b==3){
			//用户是否非配角色校验
			if (!loginservice.uhasrole(Login.sqlId_uhasrole,username)){
				renderJson("{\"code\":\"0\",\"message\":\"请向管理员分配角色后再登录\"}");
				return;
			}
			
			//用户Session绑定
			try {
				SessionController.bindUSessionId(getRequest(), getResponse(), username);
				SessionController.bindUserInfo(getRequest(), getResponse(), username);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
			//hujian modify
			Object userinfo = getSessionAttr("userinfo");
			String doid=((Record) userinfo).getStr("domain_id");
			
			loginservice.sendPost(username,doid);
			String sessionId=getSession().getId();
			UserService.login(username, sessionId, "0");
			renderJson("{\"code\":\"1\",\"message\":\"登陆成功\"}");
		}else{
			renderJson("{\"code\":\"0\",\"message\":\"用户名或密码错误\"}");
		}
	}
	
	//获取用户拥有的菜单项
	public void getMenu(){
		renderJson(menuService.getScopeInfo(((Record)getSessionAttr("userinfo")).getStr("user_id")));
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
    public static String sendPost(String url, String param) {
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
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    } 
}
