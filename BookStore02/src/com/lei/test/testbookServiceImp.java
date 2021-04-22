package com.lei.test;


import com.lei.beans.Book;
import com.lei.beans.page;
import com.lei.service.imp.bookServiceImp;
import org.junit.Test;
import java.util.List;

/**
 * User:雷志刚
 * Date:2020/10/27
 * Time:16:52
 */

public class testbookServiceImp {
    bookServiceImp bookServiceImp = new bookServiceImp();
    @Test
    public void test1(){
        page<Book> page = bookServiceImp.getBooksbyPage("1");
        int totalPageNo = page.getTotalPageNo();
        System.out.println("总页数为:"+totalPageNo);
        //获取总记录数
        int totalRecord = page.getTotalRecord();
        System.out.println("总记录数为:"+totalRecord);
        //获取当前页的所有图书
        List<Book> list = page.getList();
        for (Book book : list) {
            System.out.println(book);
        }


    }
}
