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
@WebServlet("/OrderClientServlet")
public class OrderClientServlet extends BaseServlet {
    com.lei.service.orderService orderService = new orderServiceImp();
    protected  void  checkOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        Cart cart = (Cart)session.getAttribute("cart");
//        User user = (User)session.getAttribute("user");
//         //调用service中去结账的方法
//        String orderId = orderService.orderCart(cart, user);
//        //为什么不放在requset域中 ?
//        // 因为放在requset域中 每次刷新页面 都会生成不同的订单单号
//        session.setAttribute("orderId",orderId);
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Cart cart = (Cart)session.getAttribute("cart");

        String orderId = orderService.orderCart(cart, user);
        session.setAttribute("orderId",orderId);
        response.sendRedirect(request.getContextPath()+"/pages/cart/checkout.jsp");

    }


    //获取我的订单
    protected void getMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        User use = new serviceHandleImp().getUser(user);
        List<Order> orders = new orderServiceImp().getMyOrders(use.getId());
        req.setAttribute("orders",orders);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
    }
    //获取订单详情
    protected void getOrderItems(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        orderItemsService orderItemsService = new orderItemsServiceImp();
        String orderId = req.getParameter("orderId");
        List<OrderItems> orderItems = orderItemsService.getOrderItems(orderId);
        req.setAttribute("orderItems",orderItems);
        req.getRequestDispatcher("/pages/order/orderItems.jsp").forward(req,resp);
    }


    protected void delivery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        new  orderServiceImp().delivery(id,2);
        getMyOrders(req,resp);
    }
}
