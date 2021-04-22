package com.lei.service;

import com.lei.beans.Cart;
import com.lei.beans.Order;
import com.lei.beans.User;

import java.util.List;

/**
 * User:雷志刚
 * Date:2020/11/19
 * Time:15:19
 */
public interface orderService {
    String orderCart(Cart cart , User user);
    List<Order> getOrders();
    List<Order>getMyOrders(int id );
    void updateOrderState(String id , int state);
    void delivery(String id, int state);
}
