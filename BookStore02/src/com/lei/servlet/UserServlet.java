package com.lei.servlet;

import com.alibaba.druid.pool.vendor.SybaseExceptionSorter;
import com.lei.beans.User;
import com.lei.service.imp.serviceHandleImp;
import com.lei.service.serviceHandle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * User:雷志刚
 * Date:2020/10/15
 * Time:16:54
 */
@WebServlet( "/UserServlet")
public class UserServlet extends BaseServlet {
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        serviceHandle serviceHandle = new serviceHandleImp();
        User login = serviceHandle.login(username, password);
            if(login !=null){
            //如果登陆成功
             //将用户信息放进session域中
                HttpSession session = request.getSession();
                session.setAttribute("user",login);
                response.sendRedirect(request.getContextPath()+"/pages/user/login_success.jsp");

        }else{
            //如果登录失败
            request.setAttribute("msg","用户名或密码错误!");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/user/login.jsp");
            requestDispatcher.forward(request,response);
        }
    }
    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //获取用户输入的验证码
        String imgcode = request.getParameter("imgcode");

        HttpSession session = request.getSession();
        //获取session域中生成的验证码
        String codeKey =(String) session.getAttribute("codeKey");
        String email = request.getParameter("email");
        serviceHandle serviceHandle = new serviceHandleImp();
        boolean flag = serviceHandle.register(username);
        //首先判断验证码是否正确 (加快效率,防止浪费资源)
        if(imgcode.equals(codeKey)){
            if(flag){
                //如果flag为true 就是注册过了
                request.setAttribute("msg","该用户已经注册!");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/user/regist.jsp");
                requestDispatcher.forward(request,response);
            }else{
                //保存用户信息
                serviceHandle.saveinfo(username,password,email);
                //重定向回注册页面
                response.sendRedirect(request.getContextPath()+"/pages/user/regist_success.jsp");
            }
        }else{
              request.setAttribute("msg","验证码不正确!");
              //转发回注册页面
              request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
        }

    }
  // 立即使session失效(注销)
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath()+"/index.jsp");
    }

//判断是否重名
    protected void isDupName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        serviceHandle serviceHandle = new serviceHandleImp();
        boolean flag = serviceHandle.register(name);
        if(flag){
            resp.getWriter().write("用户名已注册!");
        }else{
            resp.getWriter().write("<font style='color:blue'>用户名可用</font>");
        }
    }
}
