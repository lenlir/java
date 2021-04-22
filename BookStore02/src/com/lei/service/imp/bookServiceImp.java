package com.lei.service.imp;

import com.lei.beans.Book;
import com.lei.beans.page;
import com.lei.dao.bookDao;
import com.lei.dao.imp.bookDaoimp;
import com.lei.service.bookService;

import java.util.List;

/**
 * User:雷志刚
 * Date:2020/10/20
 * Time:17:00
 */

public class bookServiceImp implements bookService {
    bookDao bookDao = new bookDaoimp();

    @Override
    public List<Book> getBooks() {
        return bookDao.getBooks();
    }

    @Override
    public void deletebook(Integer id) {
        bookDao.deletebook(id);
    }

    @Override
    public Book getbook(Integer id) {

        return bookDao.getbook(id);
    }

    @Override
    public void savebook(Book book) {
        bookDao.savebook(book);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public page<Book> getBooksbyPage(String pageNo) {
        page page = new page();
        int defaultnum = 1;
        try {
            //防止输入 "abc" 类无关的字符串
            defaultnum = Integer.parseInt(pageNo);
        } catch (Exception e) {

        } finally {
            page.setPageNo(defaultnum);
        }

        return bookDao.getBooksbyPage(page);
    }

    @Override
    public page<Book> getBooksbyPageAndPrice(String pageNo, String minPrice, String maxPrice) {
        page page = new page();
        int defaultnum = 1;
        double defaultmin = 0;
        double defaultmax = Double.MAX_VALUE;
        try {
            //防止输入 "abc" 类无关的字符串
            defaultnum = Integer.parseInt(pageNo);
        } catch (Exception e) {

        } finally {
            page.setPageNo(defaultnum);
        }
        try {
            defaultmin = Double.parseDouble(minPrice);
        }catch (Exception e){
        }
        try {
            defaultmax = Double.parseDouble(maxPrice);
        }catch (Exception e){
        }
        return bookDao.getBooksbyPageAndPrice(page,defaultmin,defaultmax);
    }
}
