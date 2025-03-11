package com.exchange.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exchange.model.BillRequest;
import com.exchange.service.DiscountService;

@RestController
@RequestMapping("/api")
public class BillController {
	private static final Logger logger = LoggerFactory.getLogger(BillController.class);

    @Autowired
    private DiscountService discountService;
    
    @PostMapping("/calculate")
    public double calculate(@RequestBody BillRequest bill) {
    	logger.info("Calculating Bill...........");
    	 	
        double discountedAmount = discountService.applyDiscounts(bill);
        
        logger.info("DiscountedAmount :::::::"+discountedAmount);
       
        double finalAmount = discountService.calculateFinalAmount(discountedAmount, bill);
        logger.info("Final Paybale Ammount is :::::::"+discountedAmount);
        return finalAmount;
    }
}
