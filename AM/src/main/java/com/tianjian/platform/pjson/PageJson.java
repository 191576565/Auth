package com.tianjian.platform.pjson;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;
//hujian 2017.2.21
public class PageJson<T> {
	private int total;
	private List<T> rows;
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
