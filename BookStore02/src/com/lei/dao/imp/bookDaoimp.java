package com.lei.dao.imp;

import com.lei.beans.Book;
import com.lei.beans.page;
import com.lei.dao.BasicDao;
import com.lei.dao.bookDao;

import java.sql.Connection;
import java.util.List;

/**
 * User:雷志刚
 * Date:2020/10/20
 * Time:16:14
 */

public class bookDaoimp extends BasicDao implements bookDao {


    @Override
    public List<Book> getBooks() {
           String sql = " select id,title,author,price,sales,stock,img_path from books ";
        List<Book> beanList = getBeanList(Book.class, sql);
        return beanList;
    }

    @Override
    public void deletebook(Integer id) {
          String sql = "delete from books where id = ?";
            update(sql, id);

    }

    @Override
    public Book getbook(Integer id) {
        String sql =  " select id,title,author,price,sales,stock,img_path from books where id = ?";
        Book bean = getBean(Book.class, sql, id);
        return bean;
    }

    @Override
    public void savebook(Book book) {
        String sql = "insert into books(title,author,price,sales,stock,img_path) values(?,?,?,?,?,?)";

        update(sql,book.getTitle(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath());

    }

    @Override
    public void updateBook(Book book) {
        String  sql ="update  books set title=?,author=?,price=?,sales=?,stock=? where id=?";
        update(sql,book.getTitle(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getId());
    }

    @Override
    public page<Book> getBooksbyPage(page<Book> page) {
        /**
         * @return 返回整页中的所有page信息
         * @param 参数仅仅传入page的当前页码
         */
        //查询一共有多少条记录
        String sql = "select count(*) from books";
        long max = (long) getSingleValue(sql);
        page.setTotalRecord((int)max);
        //以上的到 当前页数(传入) , 总页数 , 和总记录数 (差一个每页的book数据)
        String sql1 = " select id,title,author,price,sales,stock,img_path from books limit ? ,?";
        List<Book> beanList = getBeanList(Book.class, sql1, (page.getPageNo() - 1) * page.PAGE_SIZE, page.PAGE_SIZE);//从0开始查四个
        page.setList(beanList);
        return page ;
    }

    @Override
    public page<Book> getBooksbyPageAndPrice(page<Book> page, Double minPrice, Double maxPrice) {
        /**
         * @return 返回整页中的所有page信息
         * @param 参数仅仅传入page的当前页码,最低价格和最高价格
         */
        //查询一共有多少条记录
        String sql = "select count(*) from books where price between ? and ?";
        long max = (long) getSingleValue(sql,minPrice,maxPrice);
        page.setTotalRecord((int)max);
        //以上的到 当前页数(传入) , 总页数 , 和总记录数 (差一个每页的book数据)

        String sql1 = "select id,title,author,price,sales,stock,img_path from books where price between ? and ? limit ? ,?";
        List<Book> beanList = getBeanList(Book.class, sql1, minPrice,maxPrice,(page.getPageNo() - 1) * page.PAGE_SIZE, page.PAGE_SIZE);
        page.setList(beanList);
        return page ;
    }

    @Override
    public void batchUpdateBooks(Object[][] args) {
        String sql = "update books set sales=?,stock=? where id = ?";
        new BasicDao().batchUpdate(sql,args);
    }

}
