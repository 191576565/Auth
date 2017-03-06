package com.tianjian.auth.mvc.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jfinal.core.Controller;
import com.jfinal.handler.Handler;
import com.jfinal.render.Render;
import com.tianjian.auth.mvc.base.BaseSessionController;
/** 
     *@Company  重庆天健金管科技股份有限公司
	 *@Function   全局拦截器            
	 *@Declare     符合JFinal路由规则，全部拦截并进行session用户认证
	 *@Author      谢涛
	 */
public class GlobalHandler extends Handler {
	

	@Override
	public void handle(String target, HttpServletRequest request,
			HttpServletResponse response, boolean[] isHandled) {
	    	 //   int index = target.lastIndexOf(".jsp");
	     	//    if (index != -1)
	     	//      target = target.substring(0, index);
		    nextHandler.handle(target, request, response, isHandled);
		  }
		 
  }

