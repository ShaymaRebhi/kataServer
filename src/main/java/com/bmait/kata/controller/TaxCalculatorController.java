package com.bmait.kata.controller;


import com.bmait.kata.model.Product;
import com.bmait.kata.model.Receipt;
import com.bmait.kata.service.tax.TaxServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "*")
public class TaxCalculatorController {

    private static final Logger logger = Logger.getLogger(TaxCalculatorController.class.getName());
    private static final String MEDIA_TYPE = "application/json";

    @Autowired
    private TaxServiceImpl taxService;

    @RequestMapping(value = {"/taxcalculator"}, method = {RequestMethod.POST},
            consumes = {MEDIA_TYPE}, produces = {MEDIA_TYPE})
    public Receipt calculateTax(@RequestBody Collection<Product> products) {
        logger.info("Sales Tax calculation started.");
        return taxService.calculateSalesTax(products);
    }

}
