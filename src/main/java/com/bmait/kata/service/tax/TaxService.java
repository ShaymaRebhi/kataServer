package com.bmait.kata.service.tax;



import com.bmait.kata.model.Product;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;



@Service
public interface TaxService {

    BigDecimal calculateTax(Product product);

    BigDecimal roundSalesTax(BigDecimal salesTaxAmount, String roundingScale);
}