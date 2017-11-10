package com.dhc.ddshop.common.dto;

public class Page {

    //当前页
    private int page;
    //每页显示的条数
    private int rows;


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }


    public int getOffset() {
        return (page - 1) * rows;
    }


}
