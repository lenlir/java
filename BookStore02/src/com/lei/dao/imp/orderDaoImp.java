package com.lei.dao.imp;

import com.lei.beans.Order;
import com.lei.dao.BasicDao;
import com.lei.dao.orderDao;

import java.util.List;

/**
 * User:雷志刚
 * Date:2020/11/18
 * Time:16:40
 */

public class orderDaoImp extends BasicDao implements orderDao {
    @Override
    public void saveOrder(Order order) {
          String sql = "insert into orders(id,order_time,total_count,total_amount,state,user_id) values(?,?,?,?,?,?) ";
          update(sql,order.getId(),order.getOrderTime(),order.getTotalCount(),order.getTotalAmount(),order.getState(),order.getUserId());
    }
    @Override
    public List<Order> getOrders() {
        String sql = "select id,order_time orderTime,total_count totalCount,total_amount totalAmount,state,user_id userId from orders";
        List<Order> beanList = getBeanList(Order.class, sql);
        return beanList;
    }
    @Override
    public List<Order> getMyOrders(int id) {
        String sql =  "select id,order_time orderTime,total_count totalCount,total_amount totalAmount,state,user_id userId from orders where user_id = ?";
        List<Order> beanList = getBeanList(Order.class, sql,id);
        return beanList;
    }
    @Override
    public void updateOrderState(String id, int state) {
          String  sql = "update orders set state = ? where id = ? ";
          update(sql,state,id);
    }
    @Override
    public void delivery(String id, int state) {
        String  sql = "update orders set state = ? where id = ? ";
        update(sql,state,id);
    }
}
