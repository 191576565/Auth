package com.tianjian.auth.mvc.dpgMgmt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.auth.mvc.model.DpgMgmt;
import com.tianjian.platform.pjson.PageJson;

public class GouMgmtController extends Controller{
	private static final Log log = Log.getLog(DpgMgmtController.class);
	private DpgMgmtService dpgmgmtservice = new DpgMgmtService();
	public void index() {
		log.info("jump to gouMgmt");
		setAttr("domainlist",dpgmgmtservice.getUrl(getPara("domainid")));
		setAttr("condition",dpgmgmtservice.getConditionType());
		/*权限管理组模块子页面*/
		setAttr("groupuuid", getPara("groupuuid"));
		render("gouMgmt.jsp");
	}
	
	/** 
	 *@Function 获取数据权限组管理-权限管理子页面list清单           
	 *@Declare   init 获取数据权限组管理-权限管理子页面list清单  
	 *@Author    谢涛
	 *@Return    String  void
	 */
	public void goulist() {
		Map<String, Object> mpara = new HashMap<String, Object>();
		// 获取表单参数
		Integer pageSize = getParaToInt("pageSize");
		Integer pageNumber = getParaToInt("pageNumber");
		String groupuuid = getPara("groupuuid");
		Object userinfo = getSessionAttr("userinfo");	
		String domain_id=((Record) userinfo).getStr("domain_id");
		
	    // 组装sql参数
		mpara.put("groupuuid", groupuuid);
		String selectsql = dpgmgmtservice.getSelectSql(DpgMgmt.sqlId_DG_select);
		// 获取from语句
		String wheresql = dpgmgmtservice.getFromSql(DpgMgmt.sqlId_DG_selectfrom, mpara);
		// 获取数据
		PageJson<DpgMgmt> myjson = dpgmgmtservice.getPageData(pageSize, pageNumber, selectsql, wheresql);
		renderJson(myjson);
	}
	
	/** 
	 *@Function 获取dictcode           
	 *@Declare   根据组信息获取dictcode
	 *@Author    谢涛
	 *@Return    String  void
	 */
	public void getdictcode() {
		Map<String, Object> mpara = new HashMap<String, Object>();
		mpara.put("groupuuid", getPara("groupuuid"));
		String sql = dpgmgmtservice.getFromSql(DpgMgmt.sqlId_dictcode, mpara);
		// 获取数据
		List<Record> dpgmgmt = (List<Record>) Db.find(sql);
		renderJson(dpgmgmt);
	}
	
	/** 
	 *@Function 校验URL是否存在       
	 *@Declare   根据用户的权限获取域信息
	 *@Author    谢涛
	 *@Return    String  void
	 */
	public void verifyurlid() {
		Map<String, Object> mpara = new HashMap<String, Object>();
		Map<String,Object> insertflag = new HashMap<String,Object>();
		mpara.put("groupuuid", getPara("groupuuid"));
		mpara.put("urlid", getPara("urlid"));
		String sql = dpgmgmtservice.getFromSql(DpgMgmt.sqlid_verifyurl, mpara);
	    List<Record> dpgmgmt =Db.find(sql);
		if(dpgmgmt.size()>0){
			insertflag.put("status", "error");
		}else{
			insertflag.put("status", "success");}
		renderJson(insertflag);
	}
}
