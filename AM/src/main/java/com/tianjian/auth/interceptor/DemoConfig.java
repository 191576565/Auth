package com.tianjian.auth.interceptor;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.PropKit;
import com.jfinal.render.ViewType;
import com.tianjian.auth.controller.DemoController;

public class DemoConfig extends JFinalConfig{

	@Override
	public void configConstant(Constants arg0) {
		// JFinal常量配置
		// 常量配置是第一个被加载的因为后面的一些配置都需要这里配置的常量作为基础
		// 1.加载数据库配置　读取配置文件使用loadPropertyFile或者PropKit
//		loadPropertyFile("config.properties");
		PropKit.use("config.properties");
		// 2.设置开发模式
		arg0.setDevMode(true);
		// 3.配置默认的视图类型　默认是freemarker
		arg0.setViewType(ViewType.JSP);
		// 4.配置默认视图层路径viewpath
		arg0.setBaseViewPath("/WEB-INF/view");
		// 5.设置默认上传路径
//		arg0.setBaseUploadPath("yeqcUpload");
	}

	@Override
	public void configHandler(Handlers arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configInterceptor(Interceptors arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configPlugin(Plugins arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configRoute(Routes arg0) {
		// TODO Auto-generated method stub
		arg0.add("/test",DemoController.class);
	}
	
}

