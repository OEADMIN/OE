package com.openexpense.datacontrol;


public class PageLimit {
    private int pageNo = 0;
    private int limit = 0;

    public PageLimit(){
    }
    public PageLimit(int pageNo,int limit){
        this.pageNo = pageNo;
        this.limit = limit;
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getLimit() {
        return limit;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
}
