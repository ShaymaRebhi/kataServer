package com.bmait.kata.model;

import java.math.BigDecimal;

public class Product {
    private String description;
    private Integer count;
    private BigDecimal unitPrice;
    private BigDecimal Pttc;
    private Boolean imported;
    private Category category;

    public Product() {
    }

    public Product(String description, Integer count, BigDecimal unitPrice, Boolean imported, Category category) {
        this.description = description;
        this.count = count;
        this.unitPrice = unitPrice;
        this.imported = imported;
        this.category = category;
    }

    public Product(String description, Integer count, BigDecimal unitPrice, BigDecimal pttc, Boolean imported, Category category) {
        this.description = description;
        this.count = count;
        this.unitPrice = unitPrice;
        Pttc = pttc;
        this.imported = imported;
        this.category = category;
    }

    public BigDecimal getPttc() {
        return Pttc;
    }

    public void setPttc(BigDecimal pttc) {
        Pttc = pttc;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCount() {
        return count;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public Boolean isImported() {
        return imported;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "description='" + description + '\'' +
                ", count=" + count +
                ", unitPrice=" + unitPrice +
                ", imported=" + imported +
                ", category=" + category +
                '}';
    }
}
