package com.lei.filter;

import com.alibaba.druid.util.JdbcUtils;
import com.lei.utils.JDBCUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * User:雷志刚
 * Date:2020/11/26
 * Time:16:37
 */

/**
 *  开启事务的过滤器
 *  为什么要这样做 :
 *   这样可以使每一个Servlet的方法中的connnection都是同一个
 */
@WebFilter("/*")
public class transcationFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        //获取connection对象
        Connection connection = JDBCUtils.getConnection();
        try {
            //开启事务
            connection.setAutoCommit(false);
            //放行
            chain.doFilter(req, resp);
            //没有异常正常提交
            connection.commit();
        } catch (Exception e) {

            try {
                //有异常回滚
                connection.rollback();
                //重定向到异常界面
               response.sendRedirect(request.getContextPath()+"/pages/error/error.jsp");
            } catch (SQLException ex) {

            }
        } finally {
            //释放连接
            JDBCUtils.releaseConnection();
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
