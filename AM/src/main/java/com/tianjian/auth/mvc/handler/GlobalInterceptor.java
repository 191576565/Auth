package com.tianjian.auth.mvc.handler;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.tianjian.auth.mvc.base.BaseSessionController;

public class GlobalInterceptor implements Interceptor {

	private static final Set<String> excludedActionKeys = new HashSet<String>();

	@Override
	public void intercept(Invocation inv) {
		System.out.println("action:" + inv.getActionKey());
		System.out.println("name:" + inv.getMethod().getName());
		/*
		 * yeqc 17/05/23 为审批流提供接口,设置不拦截的路由
		 */
		if (inv.getActionKey().equals("/activiti/getUserTree") || inv.getActionKey().equals("/activiti/getUserName")
				|| inv.getActionKey().equals("/activiti/ssoLogin")
				|| inv.getActionKey().equals("/activiti/getUserInfo")) {
			inv.invoke();
			return;
		}

		// TODO Auto-generated method stub
		Controller contro = (Controller) inv.getController();
		BaseSessionController SessionController = new BaseSessionController();
		int sessionflag = 0;
		try {
			sessionflag = SessionController.CheckUserSessionId(contro.getRequest(), contro.getResponse());
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("sessflag:" + sessionflag);
		if (sessionflag == 2) {
			inv.getController().redirect("/ulogin/userexit");
		} else if (sessionflag == 0) {
			if (!inv.getMethod().getName().equals("validLogin") && !inv.getMethod().getName().equals("init_login")
					&& !inv.getMethod().getName().equals("rpmParam")) {
				inv.getController().redirect("/init_login");
			} else {
				inv.invoke();
			}
		} else {
			if ((inv.getActionKey().equals("/")) || inv.getMethod().getName().equals("init_login")) {
				if (inv.getController().getSession().isNew()) {

				} else {
					
					inv.getController().redirect("/loginafter");
					return;
				}
			}
			inv.invoke();
		}

	}

	public static void addExcludedActionKey(String ApiJsonController) {
		excludedActionKeys.add(ApiJsonController);
	}

}