package com.team.house.util;


//封装分页参数   针对 easyui
public class PageUtil {
   private int page; //取名叫page,因为前端前传的参数叫page
    private int rows;  //封装页大小

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
}
