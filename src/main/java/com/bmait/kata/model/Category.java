package com.bmait.kata.model;


public enum Category {
    FOOD(false), MEDICAL_PRODUCTS(false), BOOK(true),  OTHER_PRODUCTS(true);

    final boolean taxable;


    Category(boolean taxable) {
        this.taxable = taxable;


    }

    public boolean isTaxable() {
        return this.taxable;
    }
}
