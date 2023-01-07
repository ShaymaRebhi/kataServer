package com.bmait.kata.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;


public class Receipt {

        private BigDecimal salesTax;
    private BigDecimal Total;
    private Collection<Product> product ;
        public Receipt(BigDecimal salesTax ,BigDecimal Total, Collection<Product> product) {

            this.salesTax = salesTax;
            this.Total=Total;
            this.product = product;
        }

        public BigDecimal getSalesTax() {
            return salesTax;
        }

    public void setSalesTax(BigDecimal salesTax) {
        this.salesTax = salesTax;
    }


    public Collection<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public BigDecimal getTotal() {
        return Total;
    }

    public void setTotal(BigDecimal total) {
        Total = total;
    }

    public void setProduct(Collection<Product> product) {
        this.product = product;
    }
}
