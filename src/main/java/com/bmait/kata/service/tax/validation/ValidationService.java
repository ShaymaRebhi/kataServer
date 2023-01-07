package com.bmait.kata.service.tax.validation;

import com.bmait.kata.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;


@Validated
@Service
public interface ValidationService {

    void validate(@Valid Product product);
}
