package com.tianjian.auth.mainconfig;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.dialect.AnsiSqlDialect;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.activerecord.dialect.OracleDialect;
import com.jfinal.plugin.activerecord.dialect.PostgreSqlDialect;
import com.jfinal.plugin.activerecord.dialect.SqlServerDialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.tianjian.auth.mvc.login.Login;
import com.tianjian.auth.mvc.login.LoginController;
import com.tianjian.auth.mvc.oplog.OpLog;
import com.tianjian.auth.mvc.oplog.OpLogController;
import com.tianjian.platform.constant.ConstantCache;
import com.tianjian.platform.constant.ConstantInit;
import com.tianjian.platform.dto.DataBase;
import com.tianjian.platform.plugin.SqlXmlPlugin;
import com.tianjian.platform.routes.LoginRoutes;
import com.tianjian.platform.tools.ToolCache;
import com.tianjian.platform.tools.ToolDataBase;

public class MainConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
		// TODO Auto-generated method stub
		// me.setBaseViewPath("/auth");
		PropKit.use("init.properties");
		me.setViewType(ViewType.JSP);
		PropKit.getBoolean("config.devMode");
		me.setDevMode(PropKit.getBoolean("config.devMode"));

	}

	@Override
	public void configRoute(Routes me) {
		// TODO Auto-generated method stub
		// me.add("/log", OpLogController.class);
		// me.setBaseViewPath("/WEB-INF/auth");
//添加路由
		me.add(new LoginRoutes());
		// me.add(new roleroo());
	}

	@Override
	public void configPlugin(Plugins me) {
		//获取数据库连接信息
		DataBase db = ToolDataBase.getDbInfo();
		String driverClass = db.getDriverClass();
		String jdbcUrl = db.getJdbcUrl();
		String username = db.getUserName();
		String password = db.getPassWord();

		String db_type = PropKit.get(ConstantInit.db_type_key);
		System.out.println("db_type:" + db_type);
		C3p0Plugin c3p0Plugin = new C3p0Plugin(jdbcUrl, username, password, driverClass);

		// ORM
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		arp.setShowSql(true);

		arp.setContainerFactory(new CaseInsensitiveContainerFactory(true));// 澶у皬鍐欎笉鏁忔劅
		// 数据库类型
		if (db_type.equals(ConstantInit.db_type_postgresql)) {
			arp.setDialect(new PostgreSqlDialect());

		} else if (db_type.equals(ConstantInit.db_type_mysql)) {
			System.out.println("mysql init");
			arp.setDialect(new MysqlDialect());

		} else if (db_type.equals(ConstantInit.db_type_oracle)) {
			arp.setDialect(new OracleDialect());

		} else if (db_type.equals(ConstantInit.db_type_sqlserver)) {
			arp.setDialect(new SqlServerDialect());

		} else if (db_type.equals(ConstantInit.db_type_db2)) {
			arp.setDialect(new AnsiSqlDialect());
		}

		// 添加表映射
		arp.addMapping("SYS_LOG", OpLog.class);
		me.add(c3p0Plugin);

		me.add(arp);
		// 启动encache
		System.out.println(ToolCache.getCacheType());
		if (ToolCache.getCacheType().equals(ConstantCache.cache_type_ehcache)) {
			me.add(new EhCachePlugin());
		}
		//配置sql解析
		me.add(new SqlXmlPlugin());
		// -----

	}

	@Override
	public void configInterceptor(Interceptors me) {

	}

	@Override
	public void configHandler(Handlers me) {
		// TODO Auto-generated method stub
		// me.add(new ContextPathHandler("ctx"));
		//me.add(new ContextPathHandler("contextPath"));
	}

	public static void main(String[] args) {
		JFinal.start("WebRoot", 80, "/", 5);
	}

	@Override
	public void configEngine(Engine me) {
		// TODO Auto-generated method stub

	}

}