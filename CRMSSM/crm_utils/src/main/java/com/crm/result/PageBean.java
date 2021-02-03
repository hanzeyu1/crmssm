package com.crm.result;

import java.util.List;

public class PageBean<T> {
    private List<T> list;//当前页数据集合
    private int totalPage;//总页数
    private int totalCount;//总记录数
    private int nowPage;//当前页码
    private int pageSize;//每页显示条数
    private int pageCount = 10;//页码条显示页码数量
    private int beginPage;//页码条开始页码
    private int endPage;//页码条结束页码

    public PageBean(int nowPage, int pageSize) {
        this.nowPage = nowPage;
        this.pageSize = pageSize;
    }

    public PageBean() {

    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTotalPage() {
        return totalPage;
        //return totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize +1;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        setTotalPage(this.totalCount % this.pageSize == 0 ? this.totalCount / this.pageSize : this.totalCount / this.pageSize +1);
    }

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

   /* public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }*/

    public int getBeginPage() {
        return beginPage;
    }

    public void setBeginPage(int beginPage) {
        this.beginPage = beginPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "list=" + list +
                ", totalPage=" + totalPage +
                ", totalCount=" + totalCount +
                ", nowPage=" + nowPage +
                ", pageSize=" + pageSize +
                ", beginPage=" + beginPage +
                ", endPage=" + endPage +
                '}';
    }

    /**
     * 就按开始结束页码
     */
    public void cal(){
        //默认显示10个页码
        //总页数小于等于页码条显示页码数
        if(totalPage <= pageCount){
            if(nowPage <= 5){
                this.beginPage = 1;
                this.endPage = 10;
            }else {
                this.beginPage = nowPage - 5;
                this.endPage = nowPage + 4;
            }
        }else{
            //总页数大于页码条显示页码数
            //当前页小于等于5
            if(nowPage <= 5){
                this.beginPage = 1;
                this.endPage = 10;
            }else{
                //当前页大于页码条显示页码数5
                if(nowPage < totalPage - 4){
                    this.beginPage = nowPage - 5;
                    this.endPage = nowPage + 4;
                }else{
                    this.beginPage = totalPage - 9;
                    this.endPage = totalPage;
                }
            }
        }
    }
}
