/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coffeeshop.model;

import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class ProductCatalog {
    private ArrayList<Product> products;

    public ProductCatalog() {
        this.products = new ArrayList<>();
    }
    
    public Product addProduct(Product p) {
        products.add(p);
        return p;
    }
    
    public void removeProduct(Product p) {
        products.remove(p);
    }
    
    public ArrayList<Product> getAllProducts() {
        return products;
    }
}
