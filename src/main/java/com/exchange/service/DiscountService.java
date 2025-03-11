
package com.exchange.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exchange.model.BillRequest;
import com.exchange.model.CurrencyExchangeResponse;
import com.exchange.model.Item;

@Service
public class DiscountService {

	private static final Logger logger = LoggerFactory.getLogger(DiscountService.class);
	private List<Item> items;
	 
	@Autowired
	private CurrencyExchangeService currencyExchangeService;

	public double calculateFinalAmount(double discountedAmount, BillRequest bill) {
		logger.info("Calculating final amount after discount for bill: {}", bill);

		CurrencyExchangeResponse exchangeResponse = currencyExchangeService.getExchangeRate(bill.getOriginalCurrency(),
				bill.getTargetCurrency());

		Double conversionRate = exchangeResponse.getRates().get(bill.getTargetCurrency());
        System.out.println("conversionRate:::::::::::: "+conversionRate);
		if (conversionRate == null) {
			logger.error("Invalid target currency: {}", bill.getTargetCurrency());
			throw new IllegalArgumentException("Invalid target currency: " + bill.getTargetCurrency());
		}
		System.out.println("Conversion rate for {} to {}: {}"+ bill.getOriginalCurrency()+"and "+ bill.getTargetCurrency()+"and "+
				conversionRate);
		logger.debug("Conversion rate for {} to {}: {}", bill.getOriginalCurrency(), bill.getTargetCurrency(),
				conversionRate);

		double convertedAmount = discountedAmount * conversionRate;

		logger.debug("Discounted amount after conversion: {}", convertedAmount);

		return convertedAmount;
	}

	public double applyDiscounts(BillRequest bill) {
		logger.info("Applying discounts for bill: {}", bill);

		double totalAmount = bill.getTotalAmount();
        System.out.println("RACHA:::::::::"+totalAmount);
	
		logger.debug("User Type: {}", bill.getUserType());
		logger.debug("Customer Tenure: {}", bill.getCustomerTenure());
		logger.debug("Is Affiliate: {}", bill.isAffiliate());
		logger.debug("Is Employee: {}", bill.isEmployee());

		double categorisedDiscount = 0.0;

		
		if (bill.isGroceries()) {
			logger.info("No discount applied for groceries.");
			return totalAmount; 
		}

		
		if (bill.isEmployee()) {
			categorisedDiscount = 0.30; 
			System.out.println("EMPLOYEE DISCOUNT ");
			logger.debug("Employee discount applied: 30%");
		} else if (bill.isAffiliate()) {
			categorisedDiscount = 0.10;
			System.out.println("Affiliate DISCOUNT ");
			logger.debug("Affiliate discount applied: 10%");
		} else if (bill.getCustomerTenure() > 2) {
			categorisedDiscount = 0.05; 
			System.out.println("Customer DISCOUNT ");
			logger.debug("Customer tenure discount applied: 5%");
		}

		items = bill.getItems();
        for (Item item : items) {
            if (item.getCategory().equalsIgnoreCase("grocery")) {
            	categorisedDiscount = 0;  
                break;
            }
        }
		double additionalDiscountOfEachHundredSpent = Math.floor(totalAmount / 100) * 5.0 / totalAmount;
		
		System.out.println("additionalDiscount :::::::::::"+additionalDiscountOfEachHundredSpent);
		
		categorisedDiscount = categorisedDiscount + additionalDiscountOfEachHundredSpent;
		
		//logger.debug("Additional discount applied: {}", additionalDiscountOfEachHundredSpent);

		System.out.println("Final categorisedDiscount discount applied :::::::::::"+categorisedDiscount);
		
		double totalDiscountAmount = totalAmount * categorisedDiscount;
		
		System.out.println("discountAmount= :::::::::::"+totalDiscountAmount);
		
		totalAmount = totalAmount - totalDiscountAmount;
		
		System.out.println("totalAmount :::::::::::"+totalAmount);
		
		
		//logger.info("Total amount after discount: {}", totalAmount);

		return totalAmount;
	}
}
