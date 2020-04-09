package com.yoshiker.utils;

import java.util.List;

/**
 * 
 * @Title: PagedGridResult.java
 * @Package com.yoshiker.utils
 * @Description: 用来返回分页Grid的数据格式
 * Copyright: Copyright (c) 2019
 */
public class PagedGridResult {
	
	private int page;			// 当前页数
	private int pageTotal;		// 总页数
	private long counts;		// 总记录数
    private List<?> rows;		// 每行显示的内容

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public long getCounts() {
        return counts;
    }

    public void setCounts(long counts) {
        this.counts = counts;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

}
