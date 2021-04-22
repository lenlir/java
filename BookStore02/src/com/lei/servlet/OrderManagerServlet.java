package com.lei.servlet;

import com.lei.beans.Cart;
import com.lei.beans.Order;
import com.lei.beans.OrderItems;
import com.lei.beans.User;
import com.lei.service.imp.orderItemsServiceImp;
import com.lei.service.imp.orderServiceImp;
import com.lei.service.imp.serviceHandleImp;
import com.lei.service.orderItemsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * User:雷志刚
 * Date:2020/11/10
 * Time:17:44
 */
@WebServlet("/OrderManagerServlet")
public class OrderManagerServlet extends BaseServlet {
    com.lei.service.orderService orderService = new orderServiceImp();


  //获取所有订单
    protected void getOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orders = new orderServiceImp().getOrders();
        req.setAttribute("orders",orders);
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);
    }

    //获取订单详情


    protected void getOrderItems(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        orderItemsService orderItemsService = new orderItemsServiceImp();
        String orderId = req.getParameter("orderId");

        List<OrderItems> orderItems = orderItemsService.getOrderItems(orderId);

        req.setAttribute("orderItems",orderItems);
        req.getRequestDispatcher("/pages/order/orderItems.jsp").forward(req,resp);

    }


    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        new  orderServiceImp().updateOrderState(id,1);
        getOrders(req,resp);
    }
}
