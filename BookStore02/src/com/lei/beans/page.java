package com.lei.beans;

import java.util.List;

/**
 * User:雷志刚
 * Date:2020/10/27
 * Time:15:19
 */

public class page<T> {
      private List<T> list;// 每页中的数据存放的集合
      public static final int PAGE_SIZE =4; //每页显示数据的条数
      private int pageNo;//当前页面
      private int totalPageNo; // 总页数,通过计算得到
      private int totalRecord;// 总记录数,通过查询数据库得到
    public List<T> getList() {
        return list;
    }
    public void setList(List<T> list) {
        this.list = list;
    }
    public void  setPageNo( int pageNo){
        this.pageNo= pageNo;
    }

    public static int getPageSize() {
        return PAGE_SIZE;
    }

    public int getPageNo() {
        if(pageNo<1){
                return 1;
        }else if(pageNo>getTotalPageNo()){
            return getTotalPageNo();
        }else {
            return pageNo;
        }


    }


    public int getTotalPageNo() {
        if(getTotalRecord() % PAGE_SIZE == 0)
            return getTotalRecord()/PAGE_SIZE;
        else
            return totalRecord/PAGE_SIZE +1;

    }

    public void setTotalPageNo(int totalPageNo) {
        this.totalPageNo = totalPageNo;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }
}
