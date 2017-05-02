package com.tianjian.auth.mvc.dpgMgmt;

import java.sql.Timestamp;
import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.auth.mvc.constant.ConstantLog;

public class GouMgmtController extends Controller{
	private static final Log log = Log.getLog(DpgMgmtController.class);
	private DpgMgmtService dpgmgmtservice = new DpgMgmtService();
	//保存接收参数("groupUUid")
	public static String g_id = "";
	public static String domain_id = "";
	
	public void index() {
		log.info("jump to gouMgmt");
		render("gouMgmt.jsp");
	}
	
	/*
	 * gouMgmt/getCondition
	 * 获取条件值
	 */
	public void getCondition(){
		renderJson(dpgmgmtservice.getConditionType(domain_id));
	}
	
	/*
	 * gouMgmt/getUrlDesc
	 * 根据条件类型获取URL数据
	 */
	public void getUrlDesc(){
		String type = getPara("type");
		renderJson(dpgmgmtservice.typeUrl(type,domain_id));
	}
	
	/*
	 * gouMgmt/goulist
	 * bootstrapTable分页数据
	 */
	public void goulist() {
		String groupuuid = g_id;
		String param = getPara("sendParam");
		if(!"".equals(param)){
			renderJson(dpgmgmtservice.gouParamSelect(groupuuid,param));
			return;
		}
		renderJson(dpgmgmtservice.gouPageSelect(groupuuid));
	}
	
	
	/*
	 * gouMgmt/orgTree
	 * 获取机构树
	 */
	public void orgTree(){
		List<Record> list = dpgmgmtservice.orgTree(domain_id);
		renderJson(list);
	}
	
	/*
	 * gouMgmt/accept
	 * 接收数据
	 */
	public void accept(){
		g_id = getPara("uuid");
		domain_id = getPara("domainid");
		renderJson(true);
	}
	
	/*
	 * gouMgmt/save
	 * 新增URL资源配置
	 */
	public void save(){
		GouMgmt gouMgmt = new GouMgmt();
		//权限组唯一标识
		gouMgmt.set("GROUP_UUID", getPara("groupUuid"));
		//请求-URL值
		gouMgmt.set("REQ_URL", getPara("dictcode"));
		//条件值(机构UUID逗号分隔)
		gouMgmt.set("CONDITION_CONTENT", getPara("orgs"));
		//----------
		gouMgmt.set("CREATOR", ((Record)getSessionAttr("userinfo")).getStr("user_id"));
		gouMgmt.set("MODIFIER", ((Record)getSessionAttr("userinfo")).getStr("user_id"));
		gouMgmt.set("CREATED_DATE", new Timestamp(System.currentTimeMillis()));
		gouMgmt.set("MODIFIED_DATE", new Timestamp(System.currentTimeMillis()));
		dpgmgmtservice.save(gouMgmt);
		setAttr(ConstantLog.log_optype, ConstantLog.grp2_add);
		String msg = "新增URL-" + "　URL值:" + getPara("dictcode") + " 条件值:"
				+ getPara("orgs");
		setAttr(ConstantLog.log_opcontent, msg);
		renderJson(true);
	}
	
	/*
	 * gouMgmt/update
	 * 编辑URL资源配置
	 */
	public void update(){
		GouMgmt gouMgmt = new GouMgmt();
		gouMgmt.set("UUID", getPara("uuid"));
		gouMgmt.set("REQ_URL", getPara("dictcode"));
		gouMgmt.set("CONDITION_CONTENT", getPara("orgs"));
		gouMgmt.set("MODIFIER", ((Record)getSessionAttr("userinfo")).getStr("user_id"));
		gouMgmt.set("MODIFIED_DATE", new Timestamp(System.currentTimeMillis()));
		dpgmgmtservice.update(gouMgmt);
		setAttr(ConstantLog.log_optype, ConstantLog.grp2_chg);
		String msg = "编辑URL-" + " UUID:" + getPara("uuid") + "　URL值:" + getPara("dictcode") + " 条件值:"
				+ getPara("orgs");
		setAttr(ConstantLog.log_opcontent, msg);
		renderJson(true);
	}
	
	/*
	 * gouMgmt/getGroupCode
	 * 获取组编码
	 */
	public void getGroupCode(){
		renderJson(dpgmgmtservice.getGroupCode(g_id));
	}
	
	/*
	 * gouMgmt/loadEditPara
	 * 加载编辑数据
	 */
	public void loadEditPara(){
		String uuid = getPara("uuid");
		List<Record> list = dpgmgmtservice.loadEditPara(uuid);
		renderJson(list);
	}
	
	/*
	 * gouMgmt/delete
	 * 删除数据
	 */
	public void delete(){
		GouMgmt gouMgmt = new GouMgmt();
		String uuid = getPara("uuid");
		gouMgmt.set("UUID", uuid);
		if(!dpgmgmtservice.delete(gouMgmt)){
			renderJson(false);
			return;
		}
		renderJson(true);
		//日志
		setAttr(ConstantLog.log_optype, ConstantLog.grp2_del);
		String msg = "删除URL配置-" +  " UUID:" + uuid + " URL:" + getPara("req_url") + " URL描述:"
					+ getPara("req_url_desc");
		setAttr(ConstantLog.log_opcontent, msg);
	}
	
	/*
	 * gouMgmt/deleteMore
	 * 批量删除
	 */
	public void deleteMore(){
		String uuids = getPara("uuid");
		if(dpgmgmtservice.deleteMore(uuids)){
			renderJson(true);
			//日志
			setAttr(ConstantLog.log_optype, ConstantLog.grp2_del);
			String msg = "删除URL配置-" +  " UUID:" + uuids + " URL:" + getPara("req_url") + " URL描述:"
						+ getPara("req_url_desc");
			setAttr(ConstantLog.log_opcontent, msg);
			return;
		}
		renderJson(false);
	}
}
