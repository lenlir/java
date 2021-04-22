package com.lei.dao;

import com.lei.beans.Book;
import com.lei.beans.page;

import java.util.List;

/**
 * User:雷志刚
 * Date:2020/10/20
 * Time:16:13
 */
public interface bookDao {
    List<Book> getBooks();
    void deletebook(Integer id);
    Book getbook(Integer id);
    void savebook(Book book);
    void updateBook(Book book);
    page<Book> getBooksbyPage(page<Book> page);
    page<Book> getBooksbyPageAndPrice(page<Book> page,Double minPrice,Double maxPrice);
    void batchUpdateBooks(Object[][] args);
}
