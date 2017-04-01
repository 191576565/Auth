package com.tianjian.auth.mvc.dpgMgmt;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.auth.mvc.base.BaseService;
import com.tianjian.auth.mvc.model.DpgMgmt;
import com.tianjian.auth.mvc.model.User;
import com.tianjian.auth.mvc.oplog.OpLog;
import com.tianjian.platform.constant.ConstantRender;
import com.tianjian.platform.pjson.PageJson;
import com.tianjian.platform.plugin.ParamInitPlugin;
import com.tianjian.platform.tools.ToolCache;
import com.tianjian.platform.tools.ToolGetSql;
import com.tianjian.platform.tools.ToolSqlXml;

public class DpgMgmtService extends BaseService {
	
	public static String getSqlByBeetl(String sqlId, Map<String, Object> param){
    	return ToolSqlXml.getSql(sqlId, param, ConstantRender.sql_renderType_beetl);
    }
	
	public static String getSqlByBeetl(String sqlId, Map<String, Object> param, LinkedList<Object> list){
    	return ToolSqlXml.getSql(sqlId, param, ConstantRender.sql_renderType_beetl, list);
    }
	/*
	 * 组装sql参数，注意字段名和.sql.xml相同
	 * @return
	 * @author hujian
	 * @see 
	 * @
	 */
	public Map<String, Object> getMparam(String user_uuid,String op_type,String startdate_start,String startdate_end,String domain_id){
		Map<String, Object> mpara = new HashMap<String, Object>();
		
		mpara.put("domain_id", domain_id);
		mpara.put("user_uuid", user_uuid);
		mpara.put("op_type", op_type);
		mpara.put("startdate_start", startdate_start);
		mpara.put("startdate_end", startdate_end);
		return mpara;
	}
	/*
	 * 获取.sql.xml中sql
	 * @return
	 * @author hujian
	 * @see 
	 * @
	 */
	public String getSelectSql(String fromsql){
		return  ToolGetSql.getSql(fromsql);
	}
	/*
	 * 获取.sql.xml中sql，带参数
	 * @return
	 * @author hujian
	 * @see getMparam
	 * @
	 */
	public String getFromSql(String fromsql,Map<String, Object> mpara){
		return  ToolGetSql.getSql(fromsql, mpara);
	}
	
	/*
	 * 查询数据库，并返回
	 * @return
	 * @author hujian
	 * @see getMparam
	 * @
	 */
	public PageJson<DpgMgmt> getPageData(Integer pageSize,Integer pageNumber,String selectsql,String fromsql){
		Page<DpgMgmt> logall = DpgMgmt.dao.paginate(pageNumber, pageSize, selectsql, fromsql);
		PageJson<DpgMgmt> myjson = new PageJson<DpgMgmt>();
		myjson.buildJson(logall);
		return myjson;
	}
	
	//获取用户树
	public List<Record> userTree(String domainUuid){
		String sql = ToolGetSql.getSql("tianjian.dpg.userTree");
		List<Record> list = Db.find(sql,domainUuid,domainUuid);
		return list;
	}
	
	//获取机构树
	public List<Record> orgTree(String domainUuid){
		String sql = ToolGetSql.getSql("tianjian.dpg.orgTree");
		List<Record> list = Db.find(sql,domainUuid);
		return list;
	}
	
	//获取url
	public List<Record> getUrl(String domainUuid){
		String sql = ToolGetSql.getSql("tianjian.dpg.urlSelect");
		List<Record> list = Db.find(sql,domainUuid);
		return list;
	}
	
	//获取条件类型
	public List<Record> getConditionType(String domainId){
		String sql = ToolGetSql.getSql("tianjian.dpg.typeSelect");
		List<Record> list = Db.find(sql,domainId);
		return list;
	}
	
	//条件类型下的url
	public List<Record> typeUrl(String type){
		String sql = ToolGetSql.getSql("tianjian.dpg.typeUrlSelect");
		List<Record> list = Db.find(sql,type);
		return list;
	}
	
	//新增URL资源配置
	public void save(GouMgmt gouMgmt){
		gouMgmt.save();
	}
	
	//编辑URL资源配置
	public void update(GouMgmt gouMgmt){
		gouMgmt.update();
	}
	
	
	//获取组信息
	public List<Record> getGroupCode(String groupUuid){
		String sql = ToolGetSql.getSql("tianjian.dpg.groupCodeSelect");
		List<Record> list = Db.find(sql,groupUuid);
		return list;
	}
	
	//加载编辑信息
	public List<Record> loadEditPara(String uuid){
		String sql = ToolGetSql.getSql("tianjian.dpg.loadEditParam");
		List<Record> list = Db.find(sql,uuid);
		return list;
	}
	
}
