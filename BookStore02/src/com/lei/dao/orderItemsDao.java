package com.lei.dao;

import com.lei.beans.OrderItems;

import java.util.List;

/**
 * User:雷志刚
 * Date:2020/11/18
 * Time:16:39
 */
public interface orderItemsDao {
    void saveOrderItems(OrderItems orderItems);
    void  batchUpdate(Object[][] args);
    List<OrderItems> getOrderItems(String orderId);
}
