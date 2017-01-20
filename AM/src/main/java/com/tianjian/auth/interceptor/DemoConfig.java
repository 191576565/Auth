package com.tianjian.auth.interceptor;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.tianjian.auth.controller.DemoController;

public class DemoConfig extends JFinalConfig{

	@Override
	public void configConstant(Constants arg0) {
		// TODO Auto-generated method stub
		arg0.setDevMode(true);
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

