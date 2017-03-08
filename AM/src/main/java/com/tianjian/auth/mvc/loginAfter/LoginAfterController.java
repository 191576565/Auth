package com.tianjian.auth.mvc.loginAfter;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.auth.mvc.loginAfter.LoginAfterController;
import com.tianjian.auth.mvc.menu.MenuService;

public class LoginAfterController extends Controller {
	
	private static final Log log = Log.getLog(LoginAfterController.class);
	private MenuService menuService = new MenuService();
	
	public void index() {
		log.info("jump to index");
		render("index.jsp");

	}
	
	//获取用户拥有的菜单项
	public void getMenu(){
		renderJson(menuService.getFunInfo(((Record)getSessionAttr("userinfo")).getStr("user_id")));
	}
}
