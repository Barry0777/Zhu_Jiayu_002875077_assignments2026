/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coffeeshop.model;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author Administrator
 */
public class OrderDirectory {
    private ArrayList<Order> orders;

    public OrderDirectory() {
        this.orders = new ArrayList<>();
    }
    
    public Order addOrder(Order o) {
        orders.add(o);
        return o;
    }
    
    public void removeOrder(Order o) {
        orders.remove(o);
    }
    
    public ArrayList<Order> getAllOrders() {
        return orders;
    }
    
 
   
    
    
    public ArrayList<Order> findOrdersByCustomer(String customerId) {
        return orders.stream()
                     .filter(order -> order.getCustomer().getCustomerId().equals(customerId))
                     .collect(Collectors.toCollection(ArrayList::new));
    }
}
