package com.tianjian.platform.plugin;

import com.jfinal.log.Log;
import com.jfinal.plugin.IPlugin;

/**
 * 系统初始化缓存操作类
 * @author 董华健  2012-10-16 下午1:16:56
 */
public class ParamInitPlugin implements IPlugin {
	
	private static final Log log = Log.getLog(ParamInitPlugin.class);

	/**
	 * 数据批处理大小，每批次处理一万行
	 */
	protected static final int splitDataSize = 10000;
	
    /**
     * 用户缓存key前缀
     */
	public static String cacheStart_user = "user_";

    /**
     * 分组功能缓存key前缀
     */
	public static String cacheStart_group_operator = "group_operator_";

    /**
     * 岗位功能缓存key前缀
     */
	public static String cacheStart_station_operator = "station_operator_";
    
	/**
     * 功能缓存key前缀
     */
	public static String cacheStart_operator = "operator_";
    
	/**
     * 字典缓存key前缀
     */
	public static String cacheStart_dict = "dict_";
    
	/**
     * 字典子节点缓存key前缀
     */
	public static String cacheStart_dict_child =  "dict_child_";
    
	/**
     * 参数缓存key前缀
     */
	public static String cacheStart_param = "param_";
    
	/**
     * 参数子节点缓存key前缀
     */
	public static String cacheStart_param_child =  "param_child_";

	@Override
	public boolean start() {
		log.info("缓存参数初始化 start ...");


		log.info("缓存参数初始化 end ...");
		return true;
	}

	@Override
	public boolean stop() {
		return true;
	}

	
}
