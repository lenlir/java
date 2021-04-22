package com.lei.beans;

import java.util.Date;

/**
 * User:雷志刚
 * Date:2020/11/18
 * Time:14:41
 */

public class Order {
    private String id;//订单号
    private Date orderTime; // 生成订单的时间
    private int totalCount; //购物车中的商品总数量
    private double totalAmount;//购物车中商品总金额
    private int state; // 订单状态 0: 订单未发货 1 : 已发货 2 : 交易完成
    private int userId; // 订单所属的用户

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", orderTime=" + orderTime +
                ", totalCount=" + totalCount +
                ", totalAmount=" + totalAmount +
                ", state=" + state +
                ", userId=" + userId +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Order(String id, Date orderTime, int totalCount, double totalAmount, int state, int userId) {
        this.id = id;
        this.orderTime = orderTime;
        this.totalCount = totalCount;
        this.totalAmount = totalAmount;
        this.state = state;
        this.userId = userId;
    }
}
