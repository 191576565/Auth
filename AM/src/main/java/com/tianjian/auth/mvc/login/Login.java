package com.tianjian.auth.mvc.login;


import com.jfinal.plugin.activerecord.Model;
public class Login extends Model<Login>{
	public static final Login dao=new Login();	
	
	public static final String sqlId_uhasrole="tianjian.usrMgmt.loginrolecheck";
}
