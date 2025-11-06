/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coffeeshop.model;

/**
 *
 * @author Administrator
 */
public class Business {
    private ProductCatalog productCatalog;
    private OrderDirectory orderDirectory;

    public Business() {
        this.productCatalog = new ProductCatalog();
        this.orderDirectory = new OrderDirectory();
    }


    public ProductCatalog getProductCatalog() {
        return productCatalog;
    }

    public OrderDirectory getOrderDirectory() {
        return orderDirectory;
    }
}
