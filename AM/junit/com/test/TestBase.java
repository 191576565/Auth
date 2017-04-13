package com.test;

import org.junit.BeforeClass;

import com.jfinal.kit.PropKit;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.dialect.AnsiSqlDialect;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.activerecord.dialect.OracleDialect;
import com.jfinal.plugin.activerecord.dialect.PostgreSqlDialect;
import com.jfinal.plugin.activerecord.dialect.SqlServerDialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.tianjian.auth.mvc.model.DpgMgmt;
import com.tianjian.auth.mvc.model.User;
import com.tianjian.auth.mvc.oplog.OpLog;
import com.tianjian.auth.mvc.resMgmt.ResMgmt;
import com.tianjian.auth.mvc.rolMgmt.RoleMgmt;
import com.tianjian.auth.mvc.sysMgmt.OrgMgmt;
import com.tianjian.auth.mvc.sysMgmt.SysMgmt;
import com.tianjian.auth.mvc.usrMgmt.UsrMgmt;
import com.tianjian.platform.constant.ConstantCache;
import com.tianjian.platform.constant.ConstantInit;
import com.tianjian.platform.dto.DataBase;
import com.tianjian.platform.plugin.SqlXmlPlugin;
import com.tianjian.platform.tools.ToolCache;
import com.tianjian.platform.tools.ToolDataBase;

public  class TestBase {
private static final Log log = Log.getLog(TestBase.class);
	
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    	log.info("启动 start ......");
    	PropKit.use("authconfig.properties");
    	DataBase db = ToolDataBase.getDbInfo();
		String driverClass = db.getDriverClass();
		String jdbcUrl = db.getJdbcUrl();
		String username = db.getUserName();
		String password = db.getPassWord();
		
		String db_type = PropKit.get(ConstantInit.db_type_key);
		C3p0Plugin c3p0Plugin = new C3p0Plugin(jdbcUrl, username, password, driverClass);

		// ORM
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		arp.setShowSql(true);
		
		arp.setContainerFactory(new CaseInsensitiveContainerFactory(true));//
		// 数据库类型
		if (db_type.equals(ConstantInit.db_type_postgresql)) {
			arp.setDialect(new PostgreSqlDialect());

		} else if (db_type.equals(ConstantInit.db_type_mysql)) {
			arp.setDialect(new MysqlDialect());

		} else if (db_type.equals(ConstantInit.db_type_oracle)) {
			arp.setDialect(new OracleDialect());

		} else if (db_type.equals(ConstantInit.db_type_sqlserver)) {
			arp.setDialect(new SqlServerDialect());

		} else if (db_type.equals(ConstantInit.db_type_db2)) {
			arp.setDialect(new AnsiSqlDialect());
		}

		// 添加表映射
		arp.addMapping("SYS_LOG","UUID" ,OpLog.class);
		arp.addMapping("SYS_USER_INFO","UUID",User.class);
		arp.addMapping("SYS_GROUP_INFO","UUID",DpgMgmt.class);
		arp.addMapping("sys_domain_info","UUID", SysMgmt.class);
		arp.addMapping("SYS_ORG_INFO", "UUID", OrgMgmt.class);
		arp.addMapping("SYS_USER_INFO","UUID", UsrMgmt.class);
		arp.addMapping("SYS_ROLE_INFO","UUID", RoleMgmt.class);
		arp.addMapping("SYS_RESOURCE_INFO","UUID", ResMgmt.class);
		
		c3p0Plugin.start();
		arp.start();
		// 启动encache
		
		new EhCachePlugin().start();
	
		new SqlXmlPlugin().start();
		// -----
    	log.info("启动 end ......");
    }
}
