package com.lei.service;

import com.lei.beans.OrderItems;

import java.util.List;

/**
 * User:雷志刚
 * Date:2020/11/25
 * Time:14:48
 */
public interface orderItemsService {
    List<OrderItems> getOrderItems(String orderId);
}
