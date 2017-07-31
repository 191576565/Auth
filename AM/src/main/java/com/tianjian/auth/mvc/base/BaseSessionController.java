package com.tianjian.auth.mvc.base;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.auth.mvc.model.UserService;
/** 
 *@Company  重庆天健金管科技股份有限公司
 *@Function   Session 管理控制器            
 *@Declare     会话级Session 管理方法 ：Session 绑定、解绑、认证、获取
 *@Author      谢涛
 */
public class BaseSessionController extends Controller{

	 private static final Log log = Log.getLog(BaseSessionController.class);
	 
	 /** 
		 *@Function 初始化用户session id            
		 *@Declare   登录成功后调用该方法，完成用户会话级别session绑定
		 *@Author    谢涛
		 *@Return    String  sessionId
		 */
	 public String bindUSessionId(HttpServletRequest request, HttpServletResponse response,String username)
		            throws ServletException, IOException {
		        response.setCharacterEncoding("UTF=8");
		        response.setContentType("text/html;charset=UTF-8");
		        HttpSession session = request.getSession();
		        String sessionId = session.getId();
		       // if((session.getAttribute("username")) == null){
			        session.setAttribute("user_id", username);
			        session.setAttribute("usersessionid", sessionId);
			        
			        log.info("用户会话级别session绑定：user："+username+"["+sessionId+"]");
			   //     }
		        return sessionId;
		    }

	 /** 
		 *@Function 绑定用户信息       
		 *@Declare   登录成功后调用该方法，完成会话级别用户信息绑定
		 *@Author    谢涛
		 *@Return    String  sessionId
		 */
	 public void bindUserInfo(HttpServletRequest request, HttpServletResponse response,String username)
		            throws ServletException, IOException {
		        response.setCharacterEncoding("UTF=8");
		        response.setContentType("text/html;charset=UTF-8");
		        HttpSession session = request.getSession();
		        String sessionId = (String) session.getAttribute("usersessionid");
		        Record userinfo=BaseService.getUserInfo(username);
		        session.setAttribute("userinfo", userinfo);
			   log.info("用户信息绑定：user："+username+"  sessionId :["+sessionId+"]");
		    }
	 
	 /** 
		 *@Function 用户session id 解绑        
		 *@Declare   退出和session超时调用，完成用户会话级别session解绑
		 *                   这里session超时为系统级别控制，详见 ＜web.xml＞
		 *@Author    谢涛
		 *@Return    void
		 */
	 public void ubindUSessionId(HttpServletRequest request, HttpServletResponse response)
		            throws ServletException, IOException {
		        response.setCharacterEncoding("UTF=8");
		        response.setContentType("text/html;charset=UTF-8");
		        HttpSession session = request.getSession();
		      
		        String sessionId = (String) session.getAttribute("usersessionid");
		        String username = (String) session.getAttribute("user_id");
		        	
		        if(sessionId!=null){
			    session.setAttribute("usersessionid", null);
			   // session.invalidate();//17/05/25
			    UserService.ulogin(username, sessionId, "1");
			   log.info("用户会话级别session解除绑定：user："+username+"["+sessionId+"]");


		        }
		        session.invalidate();//17/05/25
		       }
	 
	 
	 /** 
		 *@Function 用户会话级别session认证      
		 *@Declare   全局拦截器中调用该方法，完成用户会话级别session认证
		 *@Author    谢涛
		 *@Return    void  
		 */
	 public  int CheckUserSessionId(HttpServletRequest request, HttpServletResponse response)
		            throws ServletException, IOException {
		        response.setCharacterEncoding("UTF=8");
		        response.setContentType("text/html;charset=UTF-8");
		        HttpSession session = request.getSession();
		        String usersessionid=(String) session.getAttribute("usersessionid");
		        String sessionid=session.getId();
		        String username=(String) session.getAttribute("user_id");
		        if(usersessionid==null){
		        	//usersessionid为空，用户登录，不拦截 ,Api Json 数据不拦截
		        	return 0;
		        }
		        //如果是只允许一个账号在线  备用
//		        System.out.println("dbsid:"+UserService.login_sid(username));
//		        System.out.println("sid:"+sessionid);
//		        if (!(UserService.login_sid(username).equals(sessionid))){
//		        	return 3;
//		        }
		        if(sessionid.equals(usersessionid)){
		        		 if(UserService.login_status(username)){
		  		        	session.setAttribute("usersessionid", null);
		  		        	log.info( "用户主动注销登录："+username+"-"+sessionid+"["+session.getAttribute("usersessionid")+"]");
		  		        	return 2;
		  		        }else{return 1;}
		        	}else{
		        		return 2;
		        }
		    }
	 
	 /** 
		 *@Function 获取用户session id      
		 *@Declare   获取当前用户会话通话级别session id，需要验证username
		 *                   注：只有在用处成功登陆后方能获取到相关session id
		 *@Author    谢涛
		 *@Return    String  
		 */
	 public String getUserSessionId(HttpServletRequest request, HttpServletResponse response)
		            throws ServletException, IOException {
		        response.setCharacterEncoding("UTF=8");
		        response.setContentType("text/html;charset=UTF-8");
		        HttpSession session = request.getSession();
		        if((session.getAttribute("usersessionid")) != null ){
		             return	 (String) session.getAttribute("usersessionid");
		        	}else{
		        	 return	 null;
		        	}
		        }
		       

	 /** 
		 *@Function 获取用户session id      
		 *@Declare   获取当前用户会话通话级别session id，不需要验证username
		 *                   注：只有在用处成功登陆后方能获取到相关session id
		 *@Author    谢涛
		 *@Return    String  
		 */
	 public String getUserSessionId(HttpServletRequest request, HttpServletResponse response,String userids)
		            throws ServletException, IOException {
		        response.setCharacterEncoding("UTF=8");
		        response.setContentType("text/html;charset=UTF-8");
		        HttpSession session = request.getSession();
		        if((session.getAttribute("usersessionid")) != null && (session.getAttribute("username"))==userids){
		             return	 (String) session.getAttribute("usersessionid");
		        	}else{
		        	 return	 null;
		        	}
		        }
	 
	
	  
	}  

