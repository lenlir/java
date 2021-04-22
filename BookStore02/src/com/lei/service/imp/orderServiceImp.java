package com.lei.service.imp;

import com.lei.beans.*;
import com.lei.dao.bookDao;
import com.lei.dao.imp.bookDaoimp;
import com.lei.dao.imp.orderDaoImp;
import com.lei.dao.imp.orderItemsDaoImp;
import com.lei.dao.orderItemsDao;
import com.lei.service.orderService;

import java.util.Date;
import java.util.List;

/**
 * User:雷志刚
 * Date:2020/11/19
 * Time:15:21
 */

public class orderServiceImp implements orderService {
    orderDaoImp orderDaoImp = new orderDaoImp();
    orderItemsDao orderItemsDao = new orderItemsDaoImp();
    bookDao bookDao = new bookDaoimp();
    @Override
    public String orderCart(Cart cart, User user) {
        //创建随机订单号
        String orderId = System.currentTimeMillis()+"";
        //创建订单
        User user1 = new serviceHandleImp().getUser(user);

        Order order = new Order(orderId, new Date(), cart.getTotalCount(), cart.getTotalAmount(), 0,user1.getId());
        //将订单项加入数据库中
        orderDaoImp.saveOrder(order);

        List<cartItem> cartItems = cart.getCartItems();
        Object[][] orderItems = new Object[cartItems.size()][];
        Object[][] bookUpdate = new Object[cartItems.size()][];

        for (int i = 0; i < cartItems.size(); i++) {
            //根据for循环获取每一个购物项
            cartItem cartItem = cartItems.get(i);
            //得到相应购物项的book对象
            Book book = cartItem.getBook();
            int count = cartItem.getCount();
            //创建相应的订单项项对象
           // OrderItems orderItems = new OrderItems(null,count , cartItem.getAmount(),book.getTitle(), book.getAuthor(), book.getPrice(), book.getImgPath(), orderId);
            //将其添加到数据库中
            //orderItemsDao.saveOrderItems(orderItems);
         //    "insert into order_items(count,amount,title,author,price,img_path,order_id) values(?,?,?,?,?,?,?)";
            orderItems[i]=new Object[]{count,cartItem.getAmount(),book.getTitle(),book.getAuthor(),book.getPrice(), book.getImgPath(), orderId};
           /* //☆记得更新库存与销量
            book.setSales(book.getSales()+count);
            book.setStock(book.getStock()-count);
            //数据库更新图书信息
            bookDao.updateBook(book);*/
           //"update books set sales=?,stock=? where id = ?";
           bookUpdate[i]=new Object[]{book.getSales()+count,book.getStock()-count,book.getId()};
        }
        new orderItemsDaoImp().batchUpdate(orderItems);
        new bookDaoimp().batchUpdateBooks(bookUpdate);
        //完成之后记得清空购物车
        cart.emptyCart();
        return orderId;
    }

    @Override
    public List<Order> getOrders() {
        List<Order> orders = new orderDaoImp().getOrders();

        return orders;
    }

    @Override
    public List<Order> getMyOrders(int id) {

        return new orderDaoImp().getMyOrders(id);
    }
   //更新物流状态
    @Override
    public void updateOrderState(String id ,int state) {
          new orderDaoImp().updateOrderState(id,state);
    }

    //发货
    @Override
    public void delivery(String id, int state) {
        new orderDaoImp().delivery(id,state);
    }
}
