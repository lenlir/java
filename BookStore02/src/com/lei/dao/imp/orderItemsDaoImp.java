package com.lei.dao.imp;

import com.lei.beans.OrderItems;
import com.lei.dao.BasicDao;
import com.lei.dao.orderItemsDao;

import java.util.List;

/**
 * User:雷志刚
 * Date:2020/11/18
 * Time:16:46
 */

public class orderItemsDaoImp extends BasicDao implements orderItemsDao {

    @Override
    public void saveOrderItems(OrderItems orderItems) {
        String sql = "insert into order_items(count,amount,title,author,price,img_path,order_id) values(?,?,?,?,?,?,?)";
        update(sql, orderItems.getCount(), orderItems.getAmount(), orderItems.getTitle(), orderItems.getAuthor(), orderItems.getPrice(), orderItems.getImgPath(), orderItems.getOrderId());

    }

    @Override
    public void batchUpdate(Object[][] args) {
        String sql = "insert into order_items(count,amount,title,author,price,img_path,order_id) values(?,?,?,?,?,?,?)";
        new BasicDao().batchUpdate(sql, args);
    }

    @Override
    public List<OrderItems> getOrderItems(String orderId) {
        String sql = "SELECT  id , COUNT,amount,title,author,price,img_path imgPath,order_id orderId  FROM order_items WHERE order_id = ?";
        List<OrderItems> OrderItems = getBeanList(OrderItems.class, sql, orderId);
        return OrderItems;
    }
}
