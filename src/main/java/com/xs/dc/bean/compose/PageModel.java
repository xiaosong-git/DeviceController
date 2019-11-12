package com.xs.dc.bean.compose;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页
 * @param <T>
 *
 * @Date 2016/7/21 15:52
 * @Author xiaojf
 */
public class PageModel<T> implements Serializable{

	private long total;
	private List<T> rows;
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}