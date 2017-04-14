package com.tianjian.auth.mainconfig;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlReporter;
import com.jfinal.plugin.activerecord.dialect.AnsiSqlDialect;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.activerecord.dialect.OracleDialect;
import com.jfinal.plugin.activerecord.dialect.PostgreSqlDialect;
import com.jfinal.plugin.activerecord.dialect.SqlServerDialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.tianjian.auth.mvc.dpgMgmt.GouMgmt;
import com.tianjian.auth.mvc.handler.GlobalHandler;
import com.tianjian.auth.mvc.handler.GlobalInterceptor;
import com.tianjian.auth.mvc.model.DpgMgmt;
import com.tianjian.auth.mvc.model.User;
import com.tianjian.auth.mvc.oplog.GlobalLogInterceptor;
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
import com.tianjian.platform.routes.ApiJsonRoutes;
import com.tianjian.platform.routes.DpgMgmtRoutes;
import com.tianjian.platform.routes.LoginRoutes;
import com.tianjian.platform.routes.ResMgmtRoutes;
import com.tianjian.platform.routes.RolMgmtRoutes;
import com.tianjian.platform.routes.SysMgmtRoutes;
import com.tianjian.platform.routes.UloginRoutes;
import com.tianjian.platform.routes.UptPwdRoutes;
import com.tianjian.platform.routes.UsrMgmtRoutes;
import com.tianjian.platform.tools.ToolCache;
import com.tianjian.platform.tools.ToolDataBase;
import com.tianjian.platform.tools.ToolGetSql;

public class MainConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
		// TODO Auto-generated method stub
		PropKit.use("authconfig.properties");
		me.setViewType(ViewType.JSP);
		// 设置开发模式
		me.setDevMode(PropKit.getBoolean("config.devMode"));
		if (PropKit.getBoolean("config.devMode")) {
			// SqlReporter.setLog(true);
		}
	}

	@Override
	public void configRoute(Routes me) {
		// TODO Auto-generated method stub
		// 添加路由
		me.add(new LoginRoutes());
		me.add(new UloginRoutes());
		me.add(new ApiJsonRoutes());
		me.add(new SysMgmtRoutes());
		me.add(new UsrMgmtRoutes());
		me.add(new RolMgmtRoutes());
		me.add(new ResMgmtRoutes());
		me.add(new DpgMgmtRoutes());
		me.add(new UptPwdRoutes());
	}

	@Override
	public void configPlugin(Plugins me) {
		// 获取数据库连接信息
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
		arp.addMapping("SYS_LOG", "UUID", OpLog.class);
		arp.addMapping("SYS_USER_INFO", "UUID", User.class);
		arp.addMapping("SYS_GROUP_INFO", "UUID", DpgMgmt.class);
		arp.addMapping("sys_domain_info", "UUID", SysMgmt.class);
		arp.addMapping("SYS_ORG_INFO", "UUID", OrgMgmt.class);
		arp.addMapping("SYS_USER_INFO", "UUID", UsrMgmt.class);
		arp.addMapping("SYS_ROLE_INFO", "UUID", RoleMgmt.class);
		arp.addMapping("SYS_RESOURCE_INFO", "UUID", ResMgmt.class);
		arp.addMapping("SYS_GROUP_URL_RELA", "UUID", GouMgmt.class);
		me.add(c3p0Plugin);

		me.add(arp);
		// 启动encache
		if (ToolCache.getCacheType().equals(ConstantCache.cache_type_ehcache)) {
			me.add(new EhCachePlugin());
		}
		// 配置sql解析
		me.add(new SqlXmlPlugin());
		// -----

	}

	@Override
	public void configInterceptor(Interceptors me) {
		// me.add(new GlobalInterceptor());
		me.add(new GlobalLogInterceptor());

	}

	@Override
	public void configHandler(Handlers me) {
		// TODO Auto-generated method stub
		me.add(new ContextPathHandler("ctxPath"));
		// me.add(new ContextPathHandler("contextPath"));
		me.add(new GlobalHandler());
	}
	//
	 public static void main(String[] args) {
	 JFinal.start("WebRoot", 8080, "/", 5);
	 }

	@Override
	public void configEngine(Engine me) {
		// TODO Auto-generated method stub

	}

//	public static void main(String[] args) {
//		Map<String, String> headers=new HashMap<String, String>();
//		headers.put("Sid", "iiii");
//		
//		//http://172.168.171.241:9090/login/out 
//		HttpKit.post("http://172.168.173.3:9090/api/rpm/login/out", "tianjian", headers);
//	}
}
