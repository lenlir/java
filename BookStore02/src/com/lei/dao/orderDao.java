package com.lei.dao;

import com.lei.beans.Order;


import java.util.List;

/**
 * User:雷志刚
 * Date:2020/11/18
 * Time:16:38
 */
public interface orderDao {
    void saveOrder(Order order);
    List<Order> getOrders();
    List<Order>getMyOrders(int id );
    void updateOrderState(String id,int state);
    void delivery(String id ,int state);

}
