package com.tianjian.auth.mvc.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.auth.mvc.base.BaseModel;
import com.tianjian.platform.tools.ToolCache;




/** 
 *@Company  重庆天健金管科技股份有限公司
 *@Function   apitype1 Model         
 *@Declare     
 *@Author      谢涛
 */
@SuppressWarnings("unused")
public class Apitype1 extends BaseModel<Apitype1> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static final Log log = Log.getLog(Apitype1.class);
	
	public static final Apitype1 dao = new Apitype1();


	// UUID	VARCHAR2(60)		sys_guid()	唯一标识符  PK
	public static final String domain_id = "domain_id";
	
	// DOMAIN_UUID	VARCHAR2(60)			所属域编码唯一标识
	public static final String domain_name = "domain_name";
	
	 // USER_ID	      VARCHAR2(60)			用户账号
	public static final String org_unit_id = "org_unit_id";
	
	 // ORG_UUID	   VARCHAR2(60)			机构唯一标识
	public static final String org_unit_desc = "org_unit_desc";
	
	 // ROLES	CLOB	Y		用户角色       （ROLE_UUID多角色逗号分隔)
	public static final String user_id = "user_id";
	
	 // USER_PWD	   VARCHAR2(30)	Y		用户密码
	public static final String user_name = "user_name";
	
	 // USER_NAME	VARCHAR2(100)	Y		用户名称
	public static final String role_ids = "role_ids";
	
	 // USER_EMAIL	VARCHAR2(30)	Y		用户邮箱
	public static final String role_names = "role_names";
	
	 // USER_PHONE	VARCHAR2(15)	Y		用户手机号
	public static final String res_ids = "res_ids";
	
	 // CREATED_DATE	DATE			创建时间
	public static final String res_names = "res_names";
	
	 // CREATOR	VARCHAR2(30)			创建人
	public static final String res_up_ids = "res_up_ids";
	
	 // MODIFIED_DATE	DATE			修改时间
	public static final String res_css = "res_css";
	
	 // MODIFIER	VARCHAR2(30)			修改人
	public static final String res_color = "res_color";
	
	 // MEMO	VARCHAR2(300)	  Y		备注
	public static final String res_icon = "res_icon";

	//COLUMN_SECRETKEY 加密key 
	public static final String res_type = "res_type";
	
}
