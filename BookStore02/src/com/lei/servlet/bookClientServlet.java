package com.lei.servlet;

import com.lei.beans.Book;
import com.lei.beans.page;
import com.lei.service.bookService;
import com.lei.service.imp.bookServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User:雷志刚
 * Date:2020/10/28
 * Time:15:31
 */
@WebServlet("/bookClientServlet")
public class bookClientServlet extends BaseServlet {
    bookService bookService = new bookServiceImp();

    protected void getBooksbyPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageNo = req.getParameter("pageNo");//获取页数
        page<Book> page = bookService.getBooksbyPage(pageNo);//获取页数中的内容
        req.setAttribute("page",page);
        //重定向到管理页面中去
        req.getRequestDispatcher("/pages/manager/book.jsp").forward(req,resp);
    }

    protected void getBooksbyPageAndPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageNo = req.getParameter("pageNo");//获取当前页数
        String minPrice = req.getParameter("minPrice");//获取最低价
        String maxPrice = req.getParameter("maxPrice");//获取最高价
     System.out.println(pageNo+minPrice+maxPrice);
        page<Book> page = bookService.getBooksbyPageAndPrice(pageNo,minPrice,maxPrice);//获取页数中的内容和价格
        req.setAttribute("page",page);
        req.setAttribute("minPrice",minPrice);
        req.setAttribute("maxPrice",maxPrice);
        //转发到管理页面中去
        req.getRequestDispatcher("/pages/manager/book.jsp").forward(req,resp);
    }
}




