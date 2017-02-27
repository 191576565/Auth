package com.tianjian.platform.pjson;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;
/** 
 *@Company  重庆天健金管科技股份有限公司
 *@Function  bootstrap table的 Json 封装的方法 , buildJson方法的Page类型必须为jfinal的Page类型          
 *@Declare     
 *@Author    hujian
 */
public class PageJson<T> {
	//总行数
	private int total;
	//数据
	private List<T> rows;
	//用jfinal的page函数返回值封装PageJson类
	public void buildJson(Page<T> p){
		this.rows=p.getList();
		this.total=p.getTotalRow();
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
