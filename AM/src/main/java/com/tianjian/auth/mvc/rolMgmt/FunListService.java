package com.tianjian.auth.mvc.rolMgmt;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.platform.tools.ToolGetSql;

/**
 * 
 * 角色管理
 * --角色功能菜单选择-业务实现类
 * @author caoguopeng
 * 
 * 
 * ***/
public class FunListService {
	
	public List<Record> selectFunList(String userId) {
		String sql = ToolGetSql.getSql("tianjian.roleMgmt.selectFunList");
		List<Record> list = Db.find(sql,userId);
//		for(int i=0; i<list.size(); i++){
//			if(list.get(i).getStr("chkdisabled").equals("true")){
//				list.get(i).set("chkdisabled", true);
//			};
//			if(list.get(i).getStr("chkdisabled").equals("false")){
//				list.get(i).set("chkdisabled", false);
//			};
//		}
		return list;
	}

}
