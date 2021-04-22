package com.lei.test;

import com.lei.beans.Book;
import com.lei.beans.Cart;
import com.lei.beans.cartItem;
import org.junit.Test;

import java.util.Map;

/**
 * User:雷志刚
 * Date:2020/11/3
 * Time:17:28
 */

public class testCart {
    @Test
     public void   testcartmethod(){
        Cart cart = new Cart();
        Book book = new Book(1,"芜湖","大司马",0.01,100,100);
       // Book book1= new Book(2,"蛇皮","大司马",20.00,100,100);
        Book book3 = new Book(2,"芜湖","大司马",0.09,100,100);
        cart.addBook2cart(book);
        cart.addBook2cart(book3);
        double totalAmount = cart.getTotalAmount();
        int totalCount = cart.getTotalCount();
        System.out.println("总价为"+totalAmount);
        System.out.println("总数量为"+totalCount);


    }
}
