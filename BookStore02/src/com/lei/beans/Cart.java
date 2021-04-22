package com.lei.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * User:雷志刚
 * Date:2020/11/3
 * Time:16:19
 */

public class Cart implements Serializable {
    //map 中的key是图书的id,Map中的value是图书对应的购物项
    private Map<String,cartItem> map = new LinkedHashMap<>();
    private  int totalCount;  //购物车中图书的总数量,通过计算得到
    private double totalAmount;//购物车中的总价格
   public List<cartItem> getCartItems(){
       Collection<cartItem> values = map.values();
       return new ArrayList<>(values);
   }
    public Map<String, cartItem> getMap() {
        return map;
    }

//    public void setMap(Map<String, cartItem> map) {
//        this.map = map;
//    }
     //总数量
    public int getTotalCount() {
       int totalcount = 0;
        List<cartItem> cartItems = getCartItems();
        for (cartItem cartItem : cartItems) {
            int count = cartItem.getCount();
            totalcount += count;
        }
        return totalcount;
    }

//    public void setTotalCount(int totalCount) {
//        this.totalCount = totalCount;
//    }
  //总价钱
    public double getTotalAmount() {
        BigDecimal totalAmount = new BigDecimal("0");
        List<cartItem> cartItems = getCartItems();
        for (cartItem cartItem : cartItems) {
            BigDecimal bigamout = new BigDecimal(cartItem.getAmount() + "");
            totalAmount =totalAmount.add(bigamout);
        }
        return totalAmount.doubleValue();
    }

//    public void setTotalAmount(double totalAmount) {
//        this.totalAmount = totalAmount;
//    }
    //清空购物车
    public void emptyCart(){
        map.clear();
    }
    //删除购物项
    public void deleteCartItem(String bookId){
          map.remove(bookId);
    }
    //更新购物车中购物项的方法
    public void updataCartItem(String  bookId ,String bookcount){
        cartItem cartItem = map.get(bookId);
        try{
            int i = Integer.parseInt(bookcount);
            if(i>0){
                cartItem.setCount(i);
            }
        }catch(Exception e){

        }


    }
    public void addBook2cart(Book book){
        //这里分两种情况
        //1.购物车里已经有了你要买的图书,在原来的基础上count值加1就行
        //2.购物车没有要买的图书,将此书加入进来就行
        cartItem cartItem = map.get(book.getId() + "");
        if(cartItem == null){
            //购物车没有要买的图书,将此书加入进来就行
            cartItem = new cartItem();
            cartItem.setBook(book);
            cartItem.setCount(1);
            map.put(book.getId()+"",cartItem);
        }else{
            // 购物车里已经有了你要买的图书,在原来的基础上count值加1就行
            cartItem.setCount(cartItem.getCount()+1);
        }
    }
}
