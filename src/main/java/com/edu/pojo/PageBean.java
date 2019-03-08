package com.edu.pojo;

import java.util.List;

public class PageBean {
	private Integer pagenum = 1;// 當前頁
	private Integer totalCount; // 總記錄數
	private Integer pagesize = 5;// 每頁條數
	private Integer totalPage;// 總頁數
	private List list;// 儲存數據集合
	private Integer startIndex; // 數據庫查詢位置
	private Integer start;// 顯示
	private Integer end;// 顯示

	public PageBean() {

	}

	public PageBean(Integer pagenum, Integer totalCount, Integer pagesize) {

		this.pagenum = pagenum;
		this.totalCount = totalCount;
		this.pagesize = pagesize;

		this.totalCount = totalCount;

		this.pagesize = pagesize;

		this.pagenum = pagenum;

		if (this.pagenum == null) {
			// 如页面没有指定显示那一页.显示第一页.
			this.pagenum = 1;
		}

		if (this.pagesize == null) {
			// 如果每页显示条数没有指定,默认每页显示3条
			this.pagesize = 3;
		}

		// 计算总页数
		this.totalPage = (this.totalCount + this.pagesize - 1) / this.pagesize;

		// 判断当前页数是否超出范围
		// 不能小于1
		if (this.pagenum < 1) {
			this.pagenum = 1;
		}
		// 不能大于总页数
		if (this.pagenum > this.totalPage) {
			this.pagenum = this.totalPage;
		}
	}

	public Integer getPagenum() {
		return pagenum;
	}

	public void setPagenum(Integer pagenum) {
		this.pagenum = pagenum;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getpagesize() {
		return pagesize;
	}

	public void setpagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	public Integer getTotalPage() {
		this.totalPage = (this.totalCount + this.pagesize - 1) / this.pagesize;
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Integer getStartIndex() {
		startIndex = (pagenum - 1) * pagesize;
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public Integer getStart() {

		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	// 离线查询的，由于无法修改bean，重新写了个
	public Integer getStartBean() {
		return start = (this.pagenum - 1) * this.pagesize;
	}
}
