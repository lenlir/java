package com.lei.beans;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * User:雷志刚
 * Date:2020/11/3
 * Time:16:14
 */

public class cartItem implements Serializable {
    private Book book;  // 当前购物项的图书信息
    private int count; //当前购物项中图书的数量
    private double amount ; //当前购物项中图书的金额小计,通过计算得到

    public cartItem() {
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getAmount() {
        BigDecimal bigprice = new BigDecimal(book.getPrice() + "");
        BigDecimal bigcount = new BigDecimal(count + "");
        return   bigcount.multiply(bigprice).doubleValue();
    }

//    public void setAmount(double amount) {
//        this.amount = amount;
//    }
}
