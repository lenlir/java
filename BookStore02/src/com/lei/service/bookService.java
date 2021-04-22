package com.lei.service;

import com.lei.beans.Book;
import com.lei.beans.page;

import java.util.List;

/**
 * User:雷志刚
 * Date:2020/10/20
 * Time:16:59
 */
public interface bookService {
    List<Book>getBooks();
    void deletebook(Integer id );
    Book getbook(Integer id);
    void savebook(Book book);
    void updateBook(Book book );
      page<Book>  getBooksbyPage(String pageNo);
    page<Book>  getBooksbyPageAndPrice(String pageNo,String minPrice,String maxPrice);


}
