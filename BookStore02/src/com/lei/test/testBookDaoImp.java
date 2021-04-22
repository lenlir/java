package com.lei.test;

import com.lei.beans.Book;
import com.lei.beans.page;
import com.lei.dao.imp.bookDaoimp;
import com.lei.beans.page;
import org.junit.Test;

import java.awt.print.Pageable;
import java.util.List;

/**
 * User:雷志刚
 * Date:2020/10/20
 * Time:16:28
 */

public class testBookDaoImp {
   @Test
    public  void  test1(){
       List<Book> books = new bookDaoimp().getBooks();
       for (Book book : books) {
           System.out.println(book);
       }
   }
   @Test
    public  void test2(){
         new bookDaoimp().deletebook(1);

   }
    @Test
   public void test3(){
        Book getbook = new bookDaoimp().getbook(7);
        System.out.println(getbook);
    }
    @Test
    public void test4(){
        Book book = new Book(null, "水浒传", "施耐庵", 24.00, 230, 23);
        new bookDaoimp().savebook(book);
    }
    @Test
    public void test5(){
       Book book = new Book(18,"三国演义","小罗",23.23,1000,300);
       new bookDaoimp().updateBook(book);
    }
    @Test
    public void test6(){
        page page = new page();
        page.setPageNo(10);
          page = new bookDaoimp().getBooksbyPage(page);
        //获取总页数
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
    @Test
    public void test7(){
        page page = new page();
        page.setPageNo(1);
        page = new bookDaoimp().getBooksbyPageAndPrice(page,10.00,50.00);
        //获取总页数
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
