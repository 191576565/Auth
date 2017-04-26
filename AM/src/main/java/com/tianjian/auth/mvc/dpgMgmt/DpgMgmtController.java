package com.tianjian.auth.mvc.dpgMgmt;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.auth.mvc.callback.PrivateTreeData;
import com.tianjian.auth.mvc.callback.PublicTreeData;
import com.tianjian.auth.mvc.constant.ConstantLog;
import com.tianjian.auth.mvc.dpgMgmt.DpgMgmtController;
import com.tianjian.auth.mvc.model.DpgMgmt;
import com.tianjian.auth.mvc.rolMgmt.RoleMgmtService;
import com.tianjian.platform.pjson.PageJson;

public class DpgMgmtController extends Controller {
	protected DataSource dataSource;
	private static final Log log = Log.getLog(DpgMgmtController.class);
	private DpgMgmtService dpgmgmtservice = new DpgMgmtService();
	private RoleMgmtService mgmtService = new RoleMgmtService();
	
	public void index() {
		log.info("jump to dpgMgmt");
		render("dpgMgmt.jsp");
	}
	
	public void list() {
		String userId = ((Record)getSessionAttr("userinfo")).getStr("user_id");
		List<Record> list = mgmtService.paramSelect(userId);
		String domainUuid = list.get(0).getStr("uuid");
		String domainId = list.get(0).getStr("domain_id");
		String param = getPara("sendParam");
		if(!"".equals(param)){
			renderJson(dpgmgmtservice.paramSelect(domainId,domainUuid,param));
			return;
		}
		renderJson(dpgmgmtservice.pageSelect(domainId, domainUuid));
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
		String domain_uuid=((Record) userinfo).getStr("domain_uuid");
		List<Record> list = dpgmgmtservice.domainInfo(domain_id, domain_uuid);
		renderJson(list);
	}
	
	 /** 
		 *@Function 校验组id是否存在       
		 *@Declare   根据用户的权限获取域信息
		 *@Author    谢涛
		 *@Return    String  void
		 */
		public void verifygroupid() {
			Map<String, Object> mpara = new HashMap<String, Object>();
			Map<String,Object> insertflag = new HashMap<String,Object>();
			mpara.put("domainid", getPara("domaininfo"));
			mpara.put("groupid", getPara("groupid"));
			String chk=getPara("chk");
			if(chk==null || "".equals(chk)){
				insertflag.put("status", "success");
				renderJson(insertflag);
				return ;
			}
			
			String sql = dpgmgmtservice.getFromSql(DpgMgmt.sqlid_verifygid, mpara);
		    List<Record> dpgmgmt =Db.find(sql);
			if(dpgmgmt.size()>0){
				insertflag.put("status", "error");
			}else{
				insertflag.put("status", "success");
			}
			renderJson(insertflag);
		}
		
		 /** 
			 *@throws   SQLException 
		    * @Function 获取机构树    [非标准]   
			 *@Declare   根据用户的权限获取域下面的机构数据
			 *@Author    谢涛
			 *@Return    String  void
			 */
			public void getTreeOrguserData() throws SQLException {
				PrivateTreeData  callback=new PrivateTreeData();
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
			
			/** 
			 *@throws   SQLException 
		    * @Function 获取机构树    [标准]   
			 *@Declare   根据用户的权限获取域下面的机构数据
			 *@Author    谢涛
			 *@Return    String  void
			 */
			public void getTreeData() throws SQLException {
				PublicTreeData  callback=new PublicTreeData();
				Map<String,Object> TreeData = new HashMap<String,Object>();
				callback.domain_id=getPara("domainid");
				callback.dictcode=getPara("dictcode");
				 String jsondata=(String) Db.execute(callback);
				 if(jsondata.length()>0){
					 TreeData.put("status", "success");
				 }else{
					 TreeData.put("status", "error");}
				 TreeData.put("data", jsondata);
				 renderJson(TreeData);
			}

			 /** 
			 *@Function 保存权限组信息       
			 *@Declare   保存权限组信息
			 *@Author    谢涛
			 *@Return    String  void
			 */
			public void saveform() {
				Map<String, Object> mpara = new HashMap<String, Object>();
				Map<String,Object> insertflag = new HashMap<String,Object>();
				Object userinfo = getSessionAttr("userinfo");	
				mpara.put("domainid", getPara("domaininfo"));
				mpara.put("groupid", getPara("groupid"));
				mpara.put("groupname", getPara("groupname"));
				mpara.put("guserid", getPara("guserid"));
				mpara.put("opuser", ((Record) userinfo).getStr("user_id"));
				String sql = dpgmgmtservice.getFromSql(DpgMgmt.sqlId_ingroupinfo, mpara);
				int dpgmgmt =Db.update(sql);
				if(dpgmgmt==1){
					insertflag.put("status", "success");
				}else{
					insertflag.put("status", "error");
				}
				setAttr(ConstantLog.log_optype, ConstantLog.grp_add);
				String msg = "新增权限组-" + "　组编码:" + getPara("groupid") + " 组名称:"
						+ getPara("groupname");
				setAttr(ConstantLog.log_opcontent, msg);
				renderJson(insertflag);
			}
			
			 /** 
			 *@Function 保存权限组信息       
			 *@Declare   保存权限组信息
			 *@Author    谢涛
			 *@Return    String  void
			 */
			public void saveurlform() {
				Map<String, Object> mpara = new HashMap<String, Object>();
				Map<String,Object> insertflag = new HashMap<String,Object>();
				Object userinfo = getSessionAttr("userinfo");	
				mpara.put("groupuuid", getPara("groupuuid"));
				mpara.put("urlid", getPara("urlid"));
				mpara.put("urlname", getPara("urlname"));
				mpara.put("dictcode", getPara("dictcode"));
				mpara.put("dictinfo", getPara("dictinfo"));
				mpara.put("opuser", ((Record) userinfo).getStr("user_id"));
				String sql = dpgmgmtservice.getFromSql(DpgMgmt.sqlId_ingroup_url, mpara);
				int dpgmgmt =Db.update(sql);
				if(dpgmgmt==1){
					insertflag.put("status", "success");
				}else{
					insertflag.put("status", "error");}
				renderJson(insertflag);
			}
			
			 /** 
			 *@Function 删除权限组数据      
			 *@Declare   删除权限组数据   ,支持批量删除
			 *@Author    谢涛
			 *@Return    String  void
			 */
			public void delform() {
				Map<String,Object> insertflag = new HashMap<String,Object>();
				Map<String, Object> mpara = new HashMap<String, Object>();
				StringBuffer InString = new StringBuffer(); 
				String[] duid = getPara("uuid").split(","); 
				for (int i = 0; i < duid.length; i++) { 
					InString.append("'").append(duid[i]).append("'").append(","); 
				} 
			    String para=InString.toString().substring(1, InString.length() - 2);
				mpara.put("para", para.replaceAll("\\s*", ""));
		        String sql = dpgmgmtservice.getFromSql(DpgMgmt.sqlid_deletegid, mpara);
			    int dpgmgmt =Db.update(sql);
              	if(dpgmgmt>0){
					insertflag.put("status", "success");
					insertflag.put("delcount", dpgmgmt);
				}else{
					insertflag.put("status", "error");
				}
              	setAttr(ConstantLog.log_optype, ConstantLog.grp_del);
    			String msg = "删除权限组- UUID:" + getPara("uuid") + " 组编码:" + getPara("group_id") + " 组名称:" + getPara("group_desc");
    			setAttr(ConstantLog.log_opcontent, msg);
				renderJson(insertflag);
			}
			
			 /** 
			 *@Function 删除权限组url数据      
			 *@Declare   删除权限组数据   ,支持批量删除
			 *@Author    谢涛
			 *@Return    String  void
			 */
			public void delurlform() {
				Map<String,Object> insertflag = new HashMap<String,Object>();
				Map<String, Object> mpara = new HashMap<String, Object>();
				StringBuffer  InString = new StringBuffer(); 
				String[] duid = getPara("uuid").split(","); 
				for (int i = 0; i < duid.length; i++) { 
					InString.append("'").append(duid[i]).append("'").append(","); 
					} 
			    String para=InString.toString().substring(1, InString.length() - 2);
				mpara.put("para", para.replaceAll("\\s*", ""));
		        String sql = dpgmgmtservice.getFromSql(DpgMgmt.sqlid_deleteurl, mpara);
			    int dpgmgmt =Db.update(sql);
              	if(dpgmgmt>0){
					insertflag.put("status", "success");
					insertflag.put("delcount", dpgmgmt);
				 }else{
					insertflag.put("status", "error");}
				renderJson(insertflag);
			}
			
			 /** 
			 *@Function 更新权限组信息       
			 *@Declare   更新权限组信息
			 *@Author    谢涛
			 *@Return    String  void
			 */
			public void updateform() {
				Map<String, Object> mpara = new HashMap<String, Object>();
				Map<String,Object> insertflag = new HashMap<String,Object>();
				Object userinfo = getSessionAttr("userinfo");	
				mpara.put("domainid", getPara("domaininfo"));
				mpara.put("uuid", getPara("uuid"));
				mpara.put("groupid", getPara("groupid"));
				mpara.put("groupname", getPara("groupname"));
				mpara.put("guserid", getPara("guserid"));
				mpara.put("opuser", ((Record) userinfo).getStr("user_id"));
				String sql = dpgmgmtservice.getFromSql(DpgMgmt.sqlId_upgroupinfo, mpara);
				int dpgmgmt =Db.update(sql);
				if(dpgmgmt==1){
					insertflag.put("status", "success");
				}else{
					insertflag.put("status", "error");
				}
				setAttr(ConstantLog.log_optype, ConstantLog.grp_chg);
				String msg = "编辑权限组-" + " UUID:" + getPara("uuid") + " 组编码:" + getPara("groupid") + " 组名称:"
						+ getPara("groupname");
				setAttr(ConstantLog.log_opcontent, msg);
				renderJson(insertflag);
			}
			
			/** 
			 *@Function 更新权限组url信息       
			 *@Declare   更新权限组url信息  
			 *@Author    谢涛
			 *@Return    String  void
			 */
			public void updateurlform() {
				Map<String, Object> mpara = new HashMap<String, Object>();
				Map<String,Object> insertflag = new HashMap<String,Object>();
				Object userinfo = getSessionAttr("userinfo");	
				mpara.put("uuid", getPara("uuid"));
				mpara.put("urlid", getPara("urlid"));
				mpara.put("urlname", getPara("urlname"));
				mpara.put("dictcode", getPara("dictcode"));
				mpara.put("dictinfo", getPara("dictinfo"));
				mpara.put("opuser", ((Record) userinfo).getStr("user_id"));
				String sql = dpgmgmtservice.getFromSql(DpgMgmt.sqlId_upgroupurl, mpara);
				int dpgmgmt =Db.update(sql);
				if(dpgmgmt==1){
					insertflag.put("status", "success");
				}else{
					insertflag.put("status", "error");}
				renderJson(insertflag);
			}
			
			/*
			 * dpgMgmt/userTree
			 * 获取用户树
			 */
			public void userTree(){
				String domainUuid = getPara("domainUuid");
				List<Record> list = dpgmgmtservice.userTree(domainUuid);
				renderJson(list);
			}
			
			/*
			 * dpgMgmt/typeUrl
			 * 条件值URL
			 */
			public void typeUrl(){
				String type = getPara("type");
				List<Record> list = dpgmgmtservice.typeUrl(type);
				renderJson(list);
			}
			
			/*
			 * dpgMgmt/getUsers
			 * 获取users
			 */
			public void getUsers(){
				String uuid = getPara("uuid");
				List<Record> list = dpgmgmtservice.getUsers(uuid);
				renderJson(list);
			}
			
}
