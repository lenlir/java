package com.lei.dao;


import com.lei.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * ★通过QuerRunner实现通用的增删改、查询一个对象、查询所有对象的工具类（重点掌握）
 */
public class BasicDao {

    private QueryRunner qr = new QueryRunner();

    //通用的增删改的方法
    public int update(String sql , Object ...args){
        //1.获取连接
        Connection connection = null;
        int update = 0;
        try {
            //1.获取连接
            connection = JDBCUtils.getConnection();
            //2.调用QueryRunner中通用的增删改的方法
            update = qr.update(connection, sql, args);
        } catch (SQLException e) {
           throw  new RuntimeException();
        }finally{
            //3.释放资源
          //  JDBCUtils.releaseConnection(connection);
        }
        return update;
    }

    //获取一个对象的方法
    public <T> T getBean(Class<T> clazz ,String sql , Object ... args){
        Connection connection = null;
        T bean = null;
        try {
            //1.获取连接
            connection = JDBCUtils.getConnection();
            //2.调用QueryRunner中查询的方法
            bean = qr.query(connection, sql, new BeanHandler<T>(clazz), args);
        } catch (SQLException e) {
            throw  new RuntimeException();
        } finally {
            //3.释放资源
            //JDBCUtils.releaseConnection(connection);
        }
        return bean;
    }
    //获取一个List的方法
    public <T> List<T> getBeanList(Class<T> clazz , String sql , Object ... args){
        Connection connection = null;
        List<T> beanList = null;
        try {
            //1.获取连接
            connection = JDBCUtils.getConnection();
            //2.调用QueryRunner中查询的方法
            beanList = qr.query(connection, sql, new BeanListHandler<T>(clazz), args);
        } catch (SQLException e) {
            throw  new RuntimeException();
        } finally {
            //3.释放资源
            //JDBCUtils.releaseConnection(connection);
        }
        return beanList;
    }

    //获取一个单一值的方法，专门用来执行像 selecte count(*) 这样的sql语句
    public Object getSingleValue(String sql , Object ... args){
        Connection connection = null;
        Object count = null;
        try {
            //1.获取连接
            connection = JDBCUtils.getConnection();
            //2.调用QueryRunner中查询的方法
            count = qr.query(connection, sql, new ScalarHandler<>(), args);
        } catch (SQLException e) {
            throw  new RuntimeException();
        } finally {
            //3.释放资源
           // JDBCUtils.releaseConnection(connection);
        }
        return count;
    }
    //添加批处理的方法
    /*
       二维数组的解释:
       第一维:要查询的sql语句的条数
       第二位:所要填充的占位符
     */
     public void batchUpdate(String sql , Object[][]args){
         //1.获取连接
         Connection connection = null;

         try {
             //1.获取连接
             connection = JDBCUtils.getConnection();
             //2.调用QueryRunner中通用的批处理的方法
             qr.batch(connection, sql, args);
         } catch (SQLException e) {
             throw  new RuntimeException();
         }finally{
             //3.释放资源
            // JDBCUtils.releaseConnection(connection);
         }
     }
}
