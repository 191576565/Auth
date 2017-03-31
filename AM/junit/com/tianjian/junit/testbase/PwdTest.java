package com.tianjian.junit.testbase;

import org.junit.Test;

import com.jfinal.plugin.activerecord.Record;
import com.tianjian.auth.mvc.base.BaseSecurityMD5;
import com.tianjian.auth.mvc.usrMgmt.UsrMgmt;

public class PwdTest extends TestBase {
	@Test
	public void modifypwd() {
		UsrMgmt u=new UsrMgmt();
		
		//String oldp = getPara("oldpassword");
	    String newp = "778899";//getPara("newpassword");
	    String renewp ="778899"; //getPara("renewpassword");
	    String pass=BaseSecurityMD5.encodeMD5Hex(newp);
	    u.set(UsrMgmt.column_uuid, "48A9C3D9438B1B35E055000000000001");
	    u.set(UsrMgmt.column_user_id, "jfinal");
	    u.set(UsrMgmt.column_user_pwd, pass);

	    if (newp.equals(renewp)){
	    	u.update();
	    }
	}
}
