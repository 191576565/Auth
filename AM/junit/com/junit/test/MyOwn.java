package com.junit.test;

import org.junit.Test;

import com.junit.TestBase;
import com.tianjian.auth.mvc.oplog.OpLog;
import com.tianjian.platform.tools.ToolGetSql;

public class MyOwn extends TestBase{
	@Test
	public void tsql() {
		String sql =  ToolGetSql.getSql(OpLog.sqlId_log_select);
		System.out.println("sql"+sql);
	}
}
