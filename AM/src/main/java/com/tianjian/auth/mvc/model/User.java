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
 * 用户model
 * @author 董华健
 */
@SuppressWarnings("unused")
public class User extends BaseModel<User> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static final Log log = Log.getLog(User.class);
	
	public static final User dao = new User();

	/**
	 *表名称
	 */
	public static final String table_name = "sys_user_info";

	// UUID	VARCHAR2(60)		sys_guid()	唯一标识符  PK
	public static final String uuid = "uuid";
	
	// DOMAIN_UUID	VARCHAR2(60)			所属域编码唯一标识
	public static final String domain_uuid = "domain_uuid";
	
	 // USER_ID	      VARCHAR2(60)			用户账号
	public static final String user_id = "user_id";
	
	 // ORG_UUID	   VARCHAR2(60)			机构唯一标识
	public static final String org_uuid = "org_uuid";
	
	 // ROLES	CLOB	Y		用户角色       （ROLE_UUID多角色逗号分隔)
	public static final String roles = "roles";
	
	 // USER_PWD	   VARCHAR2(30)	Y		用户密码
	public static final String user_pwd = "user_pwd";
	
	 // USER_NAME	VARCHAR2(100)	Y		用户名称
	public static final String user_name = "user_name";
	
	 // USER_EMAIL	VARCHAR2(30)	Y		用户邮箱
	public static final String user_email = "user_email";
	
	 // USER_PHONE	VARCHAR2(15)	Y		用户手机号
	public static final String user_phone = "user_phone";
	
	 // CREATED_DATE	DATE			创建时间
	public static final String created_date = "created_date";
	
	 // CREATOR	VARCHAR2(30)			创建人
	public static final String creator = "creator";
	
	 // MODIFIED_DATE	DATE			修改时间
	public static final String modified_date = "modified_date";
	
	 // MODIFIER	VARCHAR2(30)			修改人
	public static final String modifier = "modifier";
	
	 // MEMO	VARCHAR2(300)	  Y		备注
	public static final String memo = "memo";

	//COLUMN_SECRETKEY 加密key 
	public static final String secretkey = "secretkey";
	

	public  String getUuid() {
		return uuid;
	}

	public  String getDomainUuid() {
		return domain_uuid;
	}

	public  String getUserId() {
		return user_id;
	}

	public  String getOrgUuid() {
		return org_uuid;
	}

	public  String getRoles() {
		return roles;
	}

	public  String getUserPwd() {
		return user_pwd;
	}

	public  String getUserName() {
		return user_name;
	}

	public  String getUserEmail() {
		return user_email;
	}

	public  String getUserPhone() {
		return user_phone;
	}

	public  String getCreatedDate() {
		return created_date;
	}

	public  String getCreator() {
		return creator;
	}

	public  String getModifiedDate() {
		return modified_date;
	}

	public  String getModifier() {
		return modifier;
	}

	public  String getMemo() {
		return memo;
	}

	public  String getSecretkey() {
		return secretkey;
	}
	

	

	
}
