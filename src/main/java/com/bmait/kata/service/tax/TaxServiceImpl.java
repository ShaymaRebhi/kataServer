package com.bmait.kata.service.tax;


import com.bmait.kata.model.Category;
import com.bmait.kata.model.Product;
import com.bmait.kata.model.Receipt;
import com.bmait.kata.service.tax.validation.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collection;

import java.util.logging.Logger;


@Component
public class TaxServiceImpl implements TaxService {

    private static final Logger logger = Logger.getLogger(TaxServiceImpl.class.getName());

    private static final BigDecimal PERCENT_DIVISOR = new BigDecimal(100);

    @Value("${basic.sales.tax}")
    private BigDecimal basicSalesTax;
    @Value("${book.sales.tax}")
    private BigDecimal booksalestax;

    @Value("${import.duty.tax}")
    private BigDecimal importDutyTax;

    @Value("${round.scale}")
    private String roundScale;

    @Autowired
    private ValidationService validationService;
    public TaxServiceImpl() {
    }

    public TaxServiceImpl(BigDecimal basicSalesTax,BigDecimal booksalestax, BigDecimal importDutyTax, String roundScale) {
        this.basicSalesTax = basicSalesTax;
        this.booksalestax=booksalestax;
        this.importDutyTax = importDutyTax;
        this.roundScale = roundScale;
    }


    /**
     * This method calculates total amount of sales tax for a group of products
     */
    public Receipt calculateSalesTax(Collection<Product> products) {
        BigDecimal totalTax = BigDecimal.ZERO;
        BigDecimal pttcTotal = BigDecimal.ZERO;
        Receipt receipt = null;
        for (Product product : products) {
            validationService.validate(product);
            BigDecimal quantity = new BigDecimal(product.getCount());
            BigDecimal pht = (product.getUnitPrice()).multiply(quantity);
            BigDecimal productTax = calculateTax(product);
            BigDecimal productGroupTax = productTax.multiply(quantity);
            BigDecimal pttc = pht.add(productGroupTax);
            product.setPttc(pttc);
            totalTax = totalTax.add(productGroupTax);
            pttcTotal = pttcTotal.add(pttc);
            logger.info(String.format("total: %s", pttcTotal));
            receipt = new Receipt(totalTax,pttcTotal, products);
        }
        logger.info(String.format("Finished calculation of the total sale tax: %s", totalTax));
        return receipt;
    }


    @Override
    /**
     * This method calculates sales tax per product
     */
    public BigDecimal calculateTax(Product product) {
        BigDecimal taxAmount = calculateTaxAmount(product);
        logger.info(String.format("Finished calculation of the sale tax for product %s. Tax: %s", product.getDescription(), taxAmount));
        BigDecimal tax = product.getUnitPrice().multiply(taxAmount);
        return roundSalesTax(tax, roundScale);
    }

    @Override
    /**
     * This method rounds sales tax per product to specified rounding scale
     */
    public BigDecimal roundSalesTax(BigDecimal salesTaxAmount, String roundingScale) {

        if (roundingScale.isEmpty()) {
            logger.warning("Rounding scale is empty. Default rounding scale will be used.");
            roundingScale = "0.05";
        }

        BigDecimal scale = new BigDecimal(roundingScale);

        return salesTaxAmount
                .divide(scale)
                .setScale(0, BigDecimal.ROUND_UP)
                .multiply(scale);
    }

    private BigDecimal calculateTaxAmount(Product product) {
        BigDecimal tax = BigDecimal.ZERO;
        if (product.isImported()) {
            tax = tax.add(importDutyTax.divide(PERCENT_DIVISOR));
        }

        if (product.getCategory().isTaxable()) {
            if (product.getCategory().equals(Category.BOOK)){
                tax = tax.add(booksalestax.divide(PERCENT_DIVISOR));
            }else
            tax = tax.add(basicSalesTax.divide(PERCENT_DIVISOR));
        }
        return tax;
    }
}
