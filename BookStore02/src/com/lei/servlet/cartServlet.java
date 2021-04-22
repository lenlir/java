package com.lei.servlet;

import com.google.gson.Gson;
import com.lei.beans.Book;
import com.lei.beans.Cart;
import com.lei.beans.cartItem;
import com.lei.service.bookService;
import com.lei.service.imp.bookServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * User:雷志刚
 * Date:2020/11/4
 * Time:15:16
 */
@WebServlet("/cartServlet")
public class cartServlet extends BaseServlet {
    bookService bookService = new bookServiceImp();
  //增加购物项
    protected void addCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookId = request.getParameter("bookId");
        Book book = bookService.getbook(Integer.parseInt(bookId));
        HttpSession session = request.getSession();
        //得到session 先判断域中是否有cart
       Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null){
            //如果没有,创建一个购物车,加入session域中
            cart  = new Cart();
            session.setAttribute("cart",cart);
        }
        session.setAttribute("booktitle",book.getTitle());
        cart.addBook2cart(book);
        //获取当前图书的库存
        Integer stock = book.getStock();
        //获取当前购物项
        cartItem cartItem = cart.getMap().get(bookId);
        //获取购物项中的数量
        int count = cartItem.getCount();
        //如果图书的库存小于当前购物项中的数量
        if(stock.intValue()<count){
            session.setAttribute("message","这本书已经卖光了!!!");
            cartItem.setCount(stock);
        }
        //从哪里来 回哪里去
        String referer = request.getHeader("Referer");
        response.sendRedirect(referer);

    }

     //清空购物车
    protected void emptyCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        //因为session域的存在时间为30min 假如用户在购物页面停留30分钟没有操作,然后点击清空会出现空指针异常
        if(cart !=null ){
        cart.emptyCart();
        }
        resp.sendRedirect(req.getContextPath()+"/pages/cart/cart.jsp");
    }
    //删除购物项
     protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         String bookid = req.getParameter("bookid");
         HttpSession session = req.getSession();
         Cart cart = (Cart) session.getAttribute("cart");
         if(cart !=null){
         cart.deleteCartItem(bookid);
         }
         resp.sendRedirect(req.getContextPath()+"/pages/cart/cart.jsp");


     }
     //更新购物篮
//    protected void updateItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String bookid = req.getParameter("bookid");
//        String count = req.getParameter("count");
//        HttpSession session = req.getSession();
//        Cart  cart =(Cart) session.getAttribute("cart");
//        if(cart != null){
//            cart.updataCartItem(bookid,count);
//        }
//        resp.sendRedirect(req.getContextPath()+"/pages/cart/cart.jsp");
//    }
    //通过发送Ajax请求来更新购物篮
     protected void updateItemByAjax(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         String bookid = req.getParameter("bookid");
         String count = req.getParameter("count");
         HttpSession session = req.getSession();
         Cart  cart =(Cart) session.getAttribute("cart");
         if(cart != null){
             cart.updataCartItem(bookid,count);
         }
         //得到购物车总结额
         double totalAmount = cart.getTotalAmount();
         //得到购物车商品数量
         int totalCount = cart.getTotalCount();
         cartItem cartItem = cart.getMap().get(bookid);
         //根据bookid得到这个商品的金额
         double amount = cartItem.getAmount();
         Map<String,String> map = new HashMap<>();
         map.put("totalAmount",totalAmount+"");
         map.put("totalCount",totalCount+"");
         map.put("amount",amount+"");
         Gson gson = new Gson();
         //将对象转换为字符串
         String s = gson.toJson(map);
         resp.getWriter().write(s);


//         resp.sendRedirect(req.getContextPath()+"/pages/cart/cart.jsp");
     }
}
