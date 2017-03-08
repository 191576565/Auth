package com.tianjian.auth.mvc.menu;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;

public class MenuController extends Controller{
	private MenuService menuService = new MenuService();
	//获取用户拥有的菜单项
	public void getMenu(){
		renderJson(menuService.getFunInfo(((Record)getSessionAttr("userinfo")).getStr("user_id")));
	}
}
