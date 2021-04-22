package com.lei.test;

import com.lei.beans.*;
import com.lei.dao.imp.orderDaoImp;
import com.lei.dao.imp.orderItemsDaoImp;
import com.lei.service.imp.orderServiceImp;
import org.junit.Test;

import java.util.List;

/**
 * User:雷志刚
 * Date:2020/11/19
 * Time:15:54
 */

public class orderTest {
   @Test
    public void testOrder(){
       Book book1 = new Book(9,"三体","刘慈欣",78.00,234,13);
       Book book2 = new Book(10,"神笔马良","洪汛涛",78.00,200,48);
       Cart cart = new Cart();
       User user = new User();
       user.setId(1);
       cart.addBook2cart(book1);
       cart.addBook2cart(book2);
       cart.addBook2cart(book2);
       orderServiceImp orderServiceImp = new orderServiceImp();
       orderServiceImp.orderCart(cart,user);


   }
   @Test
   public void  getOrderList(){
      List<Order> orders = new orderDaoImp().getOrders();
      for (Order order : orders) {
         System.out.println(order);
      }
   }
   @Test
   public void getMyOrdersTest(){
      List<Order> myOrders = new orderDaoImp().getMyOrders(1);
      for (Order myOrder : myOrders) {

         System.out.println(myOrder);
      }
   }
   @Test
   public void testGetOrderItems(){
      List<OrderItems> orderItems = new orderItemsDaoImp().getOrderItems("1606114105700");
      for (OrderItems orderItem : orderItems) {

         System.out.println(orderItem);
      }
   }
   @Test
   public void testOrderState(){
        new orderDaoImp().updateOrderState("1606114810784",1);
   }

}
