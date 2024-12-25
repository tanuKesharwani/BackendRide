package com.example.ride.pojo;

import java.util.Map;

import lombok.Data;

@Data
public class PageAttributes {

	private Integer pageNum;

	private Integer pageSize;

	private Integer limit = 0;

	private String sortCol;

	private String sortOrder; 

	private Map<String, Boolean> sortOrderMap;

	public String getSortCol() {
		return sortCol;
	}

	public void setSortCol(String sortCol) {
		this.sortCol = sortCol;
	}	

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public void pageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageNum() {
		return this.pageNum == null ? 0 : this.pageNum;
	}

	public Integer getPageSize() {
		return this.pageSize == null ? 10 : this.pageSize;
	}

	public String getSortOrder() {
		return this.sortOrder == null ? "ASC" : this.sortOrder;
	}

}
