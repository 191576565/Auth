package com.tianjian.platform.constant;

/**
 * init配置文件对应的key
 * @author 董华健
 */
public interface ConstantInit {

	/**
	 * 扫描的包
	 * hujian
	 */
	public static final String config_scan_package = "config.scan.package";

	/**
	 * 扫描的jar
	 * hujian
	 */
	public static final String config_scan_jar = "config.scan.jar";
	
	/**
	 * 开发模式
	 */
	public static final String config_devMode = "config.devMode";
	
	
	/**
	 *  缓存类型配置
	 *  hujian
	 */
	public static final String config_cache_type = "config.cache.type";

	
	/**
	 *  国际化配置，资源文件前缀
	 *  hujian
	 */
	public static final String config_i18n_filePrefix = "config.i18n.filePrefix";
	/**
	 * 当前数据库类型
	 */
	public static final String db_type_key = "db.type";

	/**
	 * 当前数据库类型：postgresql
	 */
	public static final String db_type_postgresql = "postgresql";

	/**
	 * 当前数据库类型：mysql
	 */
	public static final String db_type_mysql = "mysql";

	/**
	 * 当前数据库类型：oracle
	 */
	public static final String db_type_oracle = "oracle";

	/**
	 * 当前数据库类型：sqlserver
	 */
	public static final String db_type_sqlserver = "sqlserver";

	/**
	 * 当前数据库类型：db2
	 */
	public static final String db_type_db2 = "db2";

	/**
	 * 数据库连接参数：驱动
	 */
	public static final String db_connection_postgresql_driverClass = "postgresql.driverClass";
	
	/**
	 * 数据库连接参数：连接URL
	 */
	public static final String db_connection_postgresql_jdbcUrl = "postgresql.jdbcUrl";
	
	/**
	 * 数据库连接参数：用户名
	 */
	public static final String db_connection_postgresql_userName = "postgresql.userName";
	
	/**
	 * 数据库连接参数：密码
	 */
	public static final String db_connection_postgresql_passWord = "postgresql.passWord";

	/**
	 * 数据库连接参数：驱动
	 */
	public static final String db_connection_mysql_driverClass = "mysql.driverClass";
	
	/**
	 * 数据库连接参数：连接URL
	 */
	public static final String db_connection_mysql_jdbcUrl = "mysql.jdbcUrl";
	
	/**
	 * 数据库连接参数：用户名
	 */
	public static final String db_connection_mysql_userName = "mysql.userName";
	
	/**
	 * 数据库连接参数：密码
	 */
	public static final String db_connection_mysql_passWord = "mysql.passWord";

	/**
	 * 数据库连接参数：驱动
	 */
	public static final String db_connection_oracle_driverClass = "oracle.driverClass";
	
	/**
	 * 数据库连接参数：连接URL
	 */
	public static final String db_connection_oracle_jdbcUrl = "oracle.jdbcUrl";
	
	/**
	 * 数据库连接参数：用户名
	 */
	public static final String db_connection_oracle_userName = "oracle.userName";
	
	/**
	 * 数据库连接参数：密码
	 */
	public static final String db_connection_oracle_passWord = "oracle.passWord";
	
	/**
	 * 数据库连接参数：驱动
	 */
	public static final String db_connection_sqlserver_driverClass = "sqlserver.driverClass";
	
	/**
	 * 数据库连接参数：连接URL
	 */
	public static final String db_connection_sqlserver_jdbcUrl = "sqlserver.jdbcUrl";
	
	/**
	 * 数据库连接参数：用户名
	 */
	public static final String db_connection_sqlserver_userName = "sqlserver.userName";
	
	/**
	 * 数据库连接参数：密码
	 */
	public static final String db_connection_sqlserver_passWord = "sqlserver.passWord";

	/**
	 * 数据库连接参数：驱动
	 */
	public static final String db_connection_db2_driverClass = "db2.driverClass";
	
	/**
	 * 数据库连接参数：连接URL
	 */
	public static final String db_connection_db2_jdbcUrl = "db2.jdbcUrl";
	
	/**
	 * 数据库连接参数：用户名
	 */
	public static final String db_connection_db2_userName = "db2.userName";
	
	/**
	 * 数据库连接参数：密码
	 */
	public static final String db_connection_db2_passWord = "db2.passWord";

	/**
	 * 数据库连接池参数：初始化连接大小
	 */
	public static final String db_initialSize = "db.initialSize";

	/**
	 * 数据库连接池参数：最少连接数
	 */
	public static final String db_minIdle = "db.minIdle";

	/**
	 * 数据库连接池参数：最多连接数
	 */
	public static final String db_maxActive = "db.maxActive";

	/**
	 *  主数据源名称：系统主数据源
	 */
	public static final String db_dataSource_main = "db.dataSource.main";

}
