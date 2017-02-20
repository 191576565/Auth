package com.tianjian.auth.mvc.base;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.jfinal.core.Controller;
import com.jfinal.log.Log;
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
	 public String bindUSessionId(HttpServletRequest request, HttpServletResponse response,String userids)
		            throws ServletException, IOException {
		        response.setCharacterEncoding("UTF=8");
		        response.setContentType("text/html;charset=UTF-8");
		        HttpSession session = request.getSession();
		        String sessionId = session.getId();
		       // if((session.getAttribute("username")) == null){
			        session.setAttribute("username", userids);
			        session.setAttribute("usersessionid", sessionId);
			        log.info("用户会话级别session绑定：user："+userids+"["+sessionId+"]");
			   //     }
		        return sessionId;
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
		        String sessionId = session.getId();
		        String username = (String) session.getAttribute("username");
		        if((session.getAttribute("usersessionid")) != null){
			        session.setAttribute("usersessionid", null);
			        log.info("用户会话级别session解除绑定：user："+username+"["+sessionId+"]");
			        }
		    }
	 
	 
	 /** 
		 *@Function 用户会话级别session认证      
		 *@Declare   全局拦截器中调用该方法，完成用户会话级别session认证
		 *@Author    谢涛
		 *@Return    void  
		 */
	 public  void CheckUserSessionId(HttpServletRequest request, HttpServletResponse response)
		            throws ServletException, IOException {
		        response.setCharacterEncoding("UTF=8");
		        response.setContentType("text/html;charset=UTF-8");
		        HttpSession session = request.getSession();
		        String usersessionid=(String) session.getAttribute("usersessionid");
		        String sessionid=session.getId();
		        if((usersessionid!=null)&&(sessionid.equals(usersessionid))){
		        		System.out.println( "用户会话级别session认证成功："+sessionid+"["+session.getAttribute("usersessionid")+"]");
		        	}else{
		        		System.out.println( "用户会话级别session认证失败："+sessionid+"["+session.getAttribute("usersessionid")+"]");
		        		//render("/user");
		        		//render("/");
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

