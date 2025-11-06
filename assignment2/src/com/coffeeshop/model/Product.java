/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coffeeshop.model;


public class Product {
    private String productId;
    private String name;
    private String category;
    private double price;
    private int stockNumber; 
    private int prepTime; 

   
   
    
    
    
    
    public Product(String productId, String name, String category, double price, int stockNumber, int prepTime) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stockNumber = stockNumber;
        this.prepTime = prepTime;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(int stockNumber) {
        this.stockNumber = stockNumber;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }


    @Override
    public String toString() {
        return name + " (#" + productId + ")"; 
    }
}
