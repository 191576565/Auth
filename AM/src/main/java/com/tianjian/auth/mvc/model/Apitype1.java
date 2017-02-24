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


	// 域编码
	public static final String domain_id = "domain_id";
	
	// 域名称
	public static final String domain_name = "domain_name";
	
	 // 机构编码
	public static final String org_unit_id = "org_unit_id";
	
	 //机构名称
	public static final String org_unit_desc = "org_unit_desc";
	
	 //用户编码
	public static final String user_id = "user_id";
	
	 //用户名称
	public static final String user_name = "user_name";
	
	 //角色编码列表
	public static final String role_ids = "role_ids";
	
	 //角色名称列表
	public static final String role_names = "role_names";
	
	 //资源编码列表  （逗号分隔)
	public static final String res_ids = "res_ids";
	
	 //资源名称列表  （逗号分隔)
	public static final String res_names = "res_names";
	
	 //上级资源列表  （逗号分隔)
	public static final String res_up_ids = "res_up_ids";
	
	 //资源CSS列表   （逗号分隔)
	public static final String res_css = "res_css";
	
	 //资源颜色列表  （逗号分隔)
	public static final String res_color = "res_color";
	
	 //资源图标列表  （逗号分隔)
	public static final String res_icon = "res_icon";

	//资源类型  （逗号分隔)
	public static final String res_type = "res_type";

	public  String getDomainId() {
		return domain_id;
	}

	public  String getDomainName() {
		return domain_name;
	}

	public  String getOrgUnitId() {
		return org_unit_id;
	}

	public  String getOrgUnitDesc() {
		return org_unit_desc;
	}

	public  String getUserId() {
		return user_id;
	}

	public  String getUserName() {
		return user_name;
	}

	public  String getRoleIds() {
		return role_ids;
	}

	public  String getRoleNames() {
		return role_names;
	}

	public  String getResIds() {
		return res_ids;
	}

	public  String getResNames() {
		return res_names;
	}

	public  String getResUpIds() {
		return res_up_ids;
	}

	public  String getResCss() {
		return res_css;
	}

	public  String getResColor() {
		return res_color;
	}

	public  String getResIcon() {
		return res_icon;
	}

	public  String getResType() {
		return res_type;
	}
	
	
}
