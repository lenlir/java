package com.lei.servlet;

import com.lei.beans.Book;
import com.lei.beans.page;
import com.lei.dao.BasicDao;
import com.lei.service.bookService;
import com.lei.service.imp.bookServiceImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * User:雷志刚
 * Date:2020/10/20
 * Time:17:01
 */
@WebServlet("/bookServlet")
public class bookServlet extends BaseServlet {
    bookService bookService = new bookServiceImp();
   //得到所有图书的信息
    protected void getBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> books = bookService.getBooks();
        //将所有图书放在requset域中
        request.setAttribute("books",books);
        //转发到图书管理页面
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }

 //得到一本图书的信息
    protected void getbook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookId = req.getParameter("bookId");
        Book getbook = bookService.getbook(Integer.valueOf(bookId));
        req.setAttribute("book",getbook);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher( "/pages/manager/book_update.jsp");
        requestDispatcher.forward(req,resp);
    }
  //删除图书
    protected void deletebook (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取图书的id号
        String bookId = request.getParameter("bookId");
        //根据id号删除图书
        bookService.deletebook(Integer.valueOf(bookId));
        //重定向到管理页面
        response.sendRedirect(request.getContextPath()+"/bookServlet?methodName=getBooksbyPage");
    }
    //添加图书



//    protected void addBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String title = req.getParameter("title");
//        String price = req.getParameter("price");
//        String author = req.getParameter("author");
//        String sales = req.getParameter("sales");
//        String stock = req.getParameter("stock");
//        Book book = new Book(null, title, author, Double.parseDouble(price), Integer.parseInt(sales), Integer.parseInt(stock));
//        bookService.savebook(book);
//        //方式一
//         //getBooks(req,resp);
//         //方式二
//        resp.sendRedirect(req.getContextPath()+"/bookServlet?methodName=getBooks");
//
//    }

   //添加图书或者更新图书
    protected void addorupdateBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取图书的信息
        String id = req.getParameter("id");
        String title = req.getParameter("title");
        String price = req.getParameter("price");
        String author = req.getParameter("author");
        String sales = req.getParameter("sales");
        String stock = req.getParameter("stock");
            //如果有id就是更新 否则就是添加
            if("".equals(id)){
                Book book = new Book( null,title, author, Double.parseDouble(price), Integer.parseInt(sales), Integer.parseInt(stock));
                //更新图书信息
                bookService.savebook(book);
            }else {
                Book book = new Book(Integer.parseInt(id),title, author, Double.parseDouble(price), Integer.parseInt(sales), Integer.parseInt(stock));
                //更新图书信息
                bookService.updateBook(book);
            }


        resp.sendRedirect(req.getContextPath()+"/bookServlet?methodName=getBooksbyPage");
    }


    protected void getBooksbyPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageNo = req.getParameter("pageNo");//获取页数
        page<Book> page = bookService.getBooksbyPage(pageNo);//获取页数中的内容

       req.setAttribute("page",page);
        //重定向到管理页面中去
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}
