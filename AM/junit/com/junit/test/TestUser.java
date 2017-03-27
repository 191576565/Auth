package com.junit.test;

import org.junit.Test;

import com.junit.TestBase;
import com.tianjian.auth.mvc.usrMgmt.UsrMgmt;

public class TestUser extends TestBase {
	@Test
	public void TestUser() {
		UsrMgmt u = UsrMgmt.dao.findById("4A44671656D63A57E055000000000001");
		System.out.println(u);
	}

}
