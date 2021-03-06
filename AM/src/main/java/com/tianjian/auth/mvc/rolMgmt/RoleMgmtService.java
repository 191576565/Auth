package com.tianjian.auth.mvc.rolMgmt;

import java.util.List;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.auth.mvc.sysMgmt.SysMgmt;
import com.tianjian.platform.tools.ToolGetSql;


 /**
 * 角色管理
 * --业务实现类
 * @author caoguopeng
 */

public class RoleMgmtService {
	
	public List<Record> defaultSelect(String domainUuid, String domainId) {
		String sql = ToolGetSql.getSql("tianjian.roleMgmt.defaultSelect");
		List<Record> list = Db.find(sql,domainId,domainUuid);
		return list;
	}
	
	//根据userId获取域uuid和域编码
	public List<Record> paramSelect(String userId) {
		String sql = ToolGetSql.getSql("tianjian.roleMgmt.paramSelect");
		List<Record> list = Db.find(sql,userId);
		return list;
	}
	
	//新增角色
	public void save(RoleMgmt roleMgmt) {
		roleMgmt.save();
	}
	
	//新增角色重复性验证
	public boolean notRepeated(String scopeCode, String scopeName){
		String sql = ToolGetSql.getSql("tianjian.roleMgmt.repeatSelect");
		return RoleMgmt.dao.find(sql, scopeCode, scopeName).isEmpty();
	}
	
	//修改角色
	public boolean update(RoleMgmt roleMgmt) {
		return roleMgmt.update();
	}
	
	//删除角色信息
	public boolean delete(RoleMgmt roleMgmt) {
		return roleMgmt.delete();
	}
	
	//条件搜索
	public List<Record> likeSelect(String domainId, String domainUuid, String param){
		String sql = ToolGetSql.getSql("tianjian.roleMgmt.likeSelect");
		String findParam = "%"+param+"%";
		List<Record> list = Db.find(sql,domainId,domainUuid,findParam,findParam);
		return list;
	}
	
	//批量删除角色信息
	public boolean deleteMore(String idValues){
		String[] idValue = idValues.split(",");
		//若没有关联用户则允许删除
		for(int i=0; i<idValue.length; i++){
			if(usrSelect(idValue[i])){
				return false;
			}
		}
		for(int i=0; i<idValue.length; i++){
			RoleMgmt.dao.deleteById(idValue[i]);
		}
		return true;
	}
	
	//判断是否关联用户
	public boolean usrSelect(String domainUuid){
		String sql = ToolGetSql.getSql("tianjian.roleMgmt.rUsrSelect");
		List<Record> list = Db.find(sql, domainUuid);
		if(list.size()>0){
			return true;
		}
		return false;
	}

}
