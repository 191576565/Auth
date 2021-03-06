package com.tianjian.auth.mvc.dpgMgmt;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.auth.mvc.base.BaseService;
import com.tianjian.auth.mvc.model.DpgMgmt;
import com.tianjian.platform.constant.ConstantRender;
import com.tianjian.platform.pjson.PageJson;
import com.tianjian.platform.tools.ToolGetSql;
import com.tianjian.platform.tools.ToolSqlXml;

public class DpgMgmtService extends BaseService {
	
	//补丁:根据域ID获取对应UUID
	public String idToUuid(String domainId){
		String sql = "SELECT T.UUID FROM SYS_DOMAIN_INFO T WHERE T.DOMAIN_ID=?";
		List<Record> list = Db.find(sql,domainId);
		return list.get(0).getStr("uuid");
	}
	
	//新增
	public void save(DpgMgmt dpgMgmt){
		dpgMgmt.save();
	}
	
	//重复性验证(新增)
	public boolean notRepeated(String groupId, String groupName, String domainUuid){
		String sql = ToolGetSql.getSql("tianjian.dpg.repeatSelect");
		List<Record> list = Db.find(sql,groupId,groupName,domainUuid);
		return list.isEmpty();
	}
	
	//获取权限组信息
	public List<Record> domainInfo(String domainId, String domainUuid){
		String sql = ToolGetSql.getSql("tianjian.dpg.domainInfo");
		List<Record> list = Db.find(sql,domainId,domainUuid);
		return list;
	}
	
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
		for(int i=list.size()-1; i>=0; i--){
			String flag = list.get(i).getStr("flag");
			String orgUuid = list.get(i).getStr("id");
			if("0".equals(flag) && getLeaf(domainUuid,orgUuid)==0){
				list.remove(i);
			}
		}
		return list;
	}
	
	//传入域和机构的uuid，查询其下叶子节点的个数
	public int getLeaf(String domainUuid,String orgUuid){
		String sql = ToolGetSql.getSql("tianjian.dpg.getLeaf");
		List<Record> list = Db.find(sql,domainUuid,orgUuid);
		return list.size();
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
	public List<Record> typeUrl(String type, String domainId){
		String sql = ToolGetSql.getSql("tianjian.dpg.typeUrlSelect");
		List<Record> list = Db.find(sql,type,domainId);
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
	
	//删除数据权限
	public boolean delete(GouMgmt gouMgmt){
		return gouMgmt.delete();
	}
	
	//批量删除
	public boolean deleteMore(String idValues){
		String[] idValue = idValues.split(",");
		for(int i=0; i<idValue.length; i++){
			GouMgmt.dao.deleteById(idValue[i]);
		}
		return true;
	}
	
	//获取users
	public List<Record> getUsers(String uuid){
		String sql = ToolGetSql.getSql("tianjian.dpg.getUsers");
		List<Record> list = Db.find(sql,uuid);
		return list;
	}
	
	//条件搜索(第一层)
	public List<Record> paramSelect(String domainId, String domainUuid, String param){
		String sql = ToolGetSql.getSql("tianjian.dpg.paramSelect");
		String findParam = "%"+param+"%";
		List<Record> list = Db.find(sql,domainId,domainUuid,findParam,findParam);
		return list;
	}
	
	//条件搜索(第二层)
	public List<Record> gouParamSelect(String groupUuid, String param){
		String sql = ToolGetSql.getSql("tianjian.dpg.gouParamSelect");
		String findParam = "%"+param+"%";
		List<Record> list = Db.find(sql,groupUuid,findParam);
		return list;
	}
	
	//第一层分页
	public List<Record> pageSelect(String domainId, String domainUuid){
		String sql = ToolGetSql.getSql("tianjian.dpg.pageSelect");
		List<Record> list = Db.find(sql,domainId,domainUuid);
		return list;
	}
	
	//第二层分页
	public List<Record> gouPageSelect(String groupUuid){
		String sql = ToolGetSql.getSql("tianjian.dpg.gouPageSelect");
		List<Record> list = Db.find(sql,groupUuid);
		return list;
	}
}
