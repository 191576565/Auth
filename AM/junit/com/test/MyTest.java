package com.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.jfinal.kit.HttpKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.tianjian.platform.tools.ToolGetSql;

public class MyTest extends TestBase {
	@Test
	public void posturl() {
		String username="sys";
		String doid="rpm";
		String sql=ToolGetSql.getSql("tianjian.login.postsid");
		Record s=Db.findFirst(sql,username);
		String sid=s.get("user_sid");
		Map<String, String> headers=new HashMap<String, String>();
		headers.put("Sid", sid);
		System.out.println(sid);
		//url
		String sql1=ToolGetSql.getSql("tianjian.login.posturl");
		Record s1=Db.findFirst(sql1,doid);
		String url=s1.getStr("iport");
		url+="/login/out";
		System.out.println(url);
		//
		HttpKit.post(url, "tianjian", headers);
		
	}
}
