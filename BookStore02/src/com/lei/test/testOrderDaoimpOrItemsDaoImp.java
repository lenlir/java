package com.lei.test;


import com.lei.beans.Order;
import com.lei.beans.OrderItems;
import com.lei.dao.imp.orderDaoImp;
import com.lei.dao.imp.orderItemsDaoImp;
import org.junit.Test;

import java.util.Date;

/**
 * User:雷志刚
 * Date:2020/11/18
 * Time:16:52
 */

public class testOrderDaoimpOrItemsDaoImp {
    @Test
    public void testInsert(){
        Order order = new Order("11111", new Date(), 3, 90.00, 0, 1);
        orderDaoImp orderDaoImp = new orderDaoImp();
        orderDaoImp.saveOrder(order);
        OrderItems orderItems = new OrderItems(null, 2, 60.00, "三毛流浪记", "小三", 30.00, "static/pages/san.jpg", "11111");
        OrderItems orderItems1 = new OrderItems(null, 1, 30.00, "斗罗大陆", "唐家三少", 30.00, "static/pages/douluo.jpg", "11111");
        orderItemsDaoImp orderItemsDaoImp = new orderItemsDaoImp();
        orderItemsDaoImp.saveOrderItems(orderItems);
        orderItemsDaoImp.saveOrderItems(orderItems1);

    }
}
