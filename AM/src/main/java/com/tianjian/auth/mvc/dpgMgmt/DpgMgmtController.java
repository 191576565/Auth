package com.tianjian.auth.mvc.dpgMgmt;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.DbKit;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.auth.mvc.api.ApiJsonService;
import com.tianjian.auth.mvc.callback.GetTreeData;
import com.tianjian.auth.mvc.dpgMgmt.DpgMgmtController;
import com.tianjian.auth.mvc.model.DpgMgmt;
import com.tianjian.auth.mvc.oplog.OpLog;
import com.tianjian.auth.mvc.oplog.OpLogService;
import com.tianjian.platform.pjson.PageJson;

public class DpgMgmtController extends Controller {
	protected DataSource dataSource;
	private static final Log log = Log.getLog(DpgMgmtController.class);
	private DpgMgmtService dpgmgmtservice = new DpgMgmtService();
	
	public void index() {
		log.info("jump to dpgMgmt");
		render("dpgMgmt.jsp");
	}
	
	 /** 
	 *@Function 获取数据权限组管理list清单           
	 *@Declare   init 数据权限组管理清单
	 *@Author    谢涛
	 *@Return    String  void
	 */
	public void list() {
		Map<String, Object> mpara = new HashMap<String, Object>();
		// 获取表单参数
		Integer pageSize = getParaToInt("pageSize");
		Integer pageNumber = getParaToInt("pageNumber");
 		String user_id = getPara("user_id");
//		String op_type = getPara("op_type");
//		String startdate_start = getPara("startdate_start");
//		String startdate_end = getPara("startdate_end");
		//获取session中domain_id
		Object userinfo = getSessionAttr("userinfo");	
		String domain_id=((Record) userinfo).getStr("domain_id");
		
	    // 组装sql参数
		//mpara.put("user_id", user_id);
		mpara.put("domain_id", domain_id);
		String selectsql = dpgmgmtservice.getSelectSql(DpgMgmt.sqlId_DM_select);
		// 获取from语句
		String wheresql = dpgmgmtservice.getFromSql(DpgMgmt.sqlId_DM_selectfrom, mpara);
		// 获取数据
		PageJson<DpgMgmt> myjson = dpgmgmtservice.getPageData(pageSize, pageNumber, selectsql, wheresql);
		renderJson(myjson);
	}
	 /** 
	 *@Function 获取域信息           
	 *@Declare   根据用户的权限获取域信息
	 *@Author    谢涛
	 *@Return    String  void
	 */
	public void domaininfo() {
		Map<String, Object> mpara = new HashMap<String, Object>();
		Object userinfo = getSessionAttr("userinfo");	
		String domain_id=((Record) userinfo).getStr("domain_id");
		mpara.put("domain_id", domain_id);
		String sql = dpgmgmtservice.getFromSql(DpgMgmt.sqlId_domaininfo, mpara);
		// 获取数据
		List<Record> dpgmgmt = (List<Record>) Db.find(sql);
		renderJson(dpgmgmt);
	}
	
	 /** 
		 *@Function 校验组id是否存在       
		 *@Declare   根据用户的权限获取域信息
		 *@Author    谢涛
		 *@Return    String  void
		 */
		public void verifygroupid() {
			Map<String, Object> mpara = new HashMap<String, Object>();
			Object userinfo = getSessionAttr("userinfo");	
			String domain_id=((Record) userinfo).getStr("domain_id");
			mpara.put("domain_id", domain_id);
			String sql = dpgmgmtservice.getFromSql(DpgMgmt.sqlId_domaininfo, mpara);
			// 获取数据
			List<Record> dpgmgmt = (List<Record>) Db.find(sql);
			renderJson(dpgmgmt);
		}
		
		 /** 
			 *@throws SQLException 
		 * @Function 获取机构树       
			 *@Declare   根据用户的权限获取域下面的机构数据
			 *@Author    谢涛
			 *@Return    String  void
			 */
			public void getTreeData() throws SQLException {
				GetTreeData  callback=new GetTreeData();
				Map<String,Object> TreeData = new HashMap<String,Object>();
				callback.domain_id=getPara("domainid");
				 String jsondata=(String) Db.execute(callback);
				 if(jsondata.length()>0){
					 TreeData.put("status", "success");
				 }else{
					 TreeData.put("status", "error");}
				 TreeData.put("data", jsondata);
				 renderJson(TreeData);
			}

		private String getStr(String string) {
			// TODO Auto-generated method stub
			return string;
		}

	
}
