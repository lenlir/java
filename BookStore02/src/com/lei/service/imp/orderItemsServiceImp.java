package com.lei.service.imp;

import com.lei.beans.OrderItems;
import com.lei.dao.imp.orderItemsDaoImp;
import com.lei.service.orderItemsService;

import java.util.List;

/**
 * User:雷志刚
 * Date:2020/11/25
 * Time:14:50
 */

public class orderItemsServiceImp implements orderItemsService {
    @Override
    public List<OrderItems> getOrderItems(String orderId) {
       return  new orderItemsDaoImp().getOrderItems(orderId);
    }
}
