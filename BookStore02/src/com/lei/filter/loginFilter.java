package com.lei.filter;

import com.lei.beans.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * User:雷志刚
 * Date:2020/11/10
 * Time:17:45
 */
@WebFilter("/OrderClientServlet")
public class loginFilter implements Filter {
    public void destroy() {

    }
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if (user != null) {
            //如果所查的用户不为空,给予放行
        chain.doFilter(req, resp);
        }else{
            request.setAttribute("msg","该操作需要先登录!");
            request.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
