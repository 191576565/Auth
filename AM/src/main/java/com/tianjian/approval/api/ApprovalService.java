package com.tianjian.approval.api;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.platform.tools.ToolGetSql;

public class ApprovalService {
	
	//机构和用户
	public List<Record> orgUser(String orgUUID){
		String sql = ToolGetSql.getSql("tianjian.approval.orgUser");
		List<Record> list = Db.find(sql,orgUUID,orgUUID);
		return list;
	}
	
	//初始化机构和用户
	public List<Record> initOrgUser(){
		String sql = ToolGetSql.getSql("tianjian.approval.initOrgUser");
		return Db.find(sql);
	}
	
	//转换大小写
	public String caseCast(String str){
		String[] sArr = str.split(",");

        for(int i=0;i<sArr.length;i++){
            String s = sArr[i];
            if(s.substring(1,9).equals("groupsid")){
              sArr[i] = "\"groupsId"+s.substring(9);
            }else if(s.substring(1,10).equals("parentdep")){
              sArr[i] = "\"parentDep"+s.substring(10);
            }else if(s.substring(1,12).equals("superiordep")){
              sArr[i] = "\"superiorDep"+s.substring(12);
        	}else if(s.substring(1,7).equals("userid")){
              sArr[i] = "\"userId"+s.substring(7);
        	}else if(s.substring(1,9).equals("username")){
              sArr[i] = "\"userName"+s.substring(9);
            }else if (s.substring(1,9).equals("isparent")){
              sArr[i] = "\"isParent"+s.substring(9);
            }
        }

        StringBuilder stb = new StringBuilder();

        for(int i=0;i<sArr.length;i++){
            stb.append(sArr[i]+",");
        }
        stb.deleteCharAt(stb.lastIndexOf(","));
        String rs = stb.toString();
        return rs;
	}
	
	//把字符串类型的true/false转换成布尔类型
	public List<Record> typeCast(List<Record> list){
		List<Record> newList = new ArrayList<Record>();
		for(int i=0;i<list.size(); i++){
			Record rd = new Record();
			rd = list.get(i);
			String temp = rd.get("isparent");
			rd.remove("isparent");
			if("TRUE".equals(temp)){
				rd.set("isparent", true);
			}else if("FALSE".equals(temp)){
				rd.set("isparent", false);
			}
			newList.add(rd);
		}
		return newList;
	}
	
	//根据userId获得userName
	public String getUserName(String userId){
		String sql = ToolGetSql.getSql("tianjian.approval.getUserName");
		List<Record> list = Db.find(sql,userId);
		if(list.size()==0){
			return "";
		}else{
			String userName = list.get(0).getStr("user_name");
			return userName;
		}
	}
	
	//获取用户所在机构和上级机构
	public List<Record> getOrg(String userUuid){
		String sql = ToolGetSql.getSql("tianjian.approval.getOrg");
		List<Record> list = Db.find(sql, userUuid);
		return list;
	}
	
	//获取用户信息(带参数)
	public List<Record> getUserInfo(String userId){
		String sql = ToolGetSql.getSql("tianjian.approval.getOrg");
		List<Record> list = Db.find(sql, userId);
		return list;
	}
}
