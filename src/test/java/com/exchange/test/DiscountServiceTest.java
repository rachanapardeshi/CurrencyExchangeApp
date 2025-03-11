package com.exchange.test;

import com.exchange.model.BillRequest;
import com.exchange.model.CurrencyExchangeResponse;
import com.exchange.model.Item;
import com.exchange.service.CurrencyExchangeService;
import com.exchange.service.DiscountService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class DiscountServiceTest {

    @InjectMocks
    private DiscountService discountService;

    @Mock
    private CurrencyExchangeService currencyExchangeService;

    @Mock
    private CurrencyExchangeResponse currencyExchangeResponse;
    
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test case for calculating the final amount with items and currency conversion
    @Test
    void testCalculateFinalAmountWithItemsAndCurrencyConversion() {
    	Item i1= new Item("Laptop", "electronics", 1000.0);
    	Item i2= new Item("Headphones", "electronics", 150.0);
    	List<Item> list = new ArrayList<Item>();
    	list.add(i1);
    	list.add(i2);

    	
        BillRequest bill = new BillRequest();
        bill.setItems(list);
        bill.setTotalAmount(1150.0);  // Total amount after adding laptop + headphones
        bill.setUserType("affiliate");
        bill.setCustomerTenure(3);
        bill.setOriginalCurrency("USD");
        bill.setTargetCurrency("EUR");
        bill.setEmployee(true);
        bill.setAffiliate(false);

        // Mock currency exchange rate
        when(currencyExchangeService.getExchangeRate("USD", "EUR")).thenReturn(currencyExchangeResponse);
        when(currencyExchangeResponse.getRates()).thenReturn(Map.of("EUR", 0.84));

        double discountedAmount = 112.5;  

        double result = discountService.calculateFinalAmount(discountedAmount, bill);

        assertEquals(94.5, result, "The final amount after conversion and discount should be 94.5");
    }

    // Test case for applying discount for affiliate user type
    @Test
    void testApplyDiscountsForAffiliateWithItems() {
    	
    	Item i1= new Item("Laptop", "electronics", 1000.0);
    	Item i2= new Item("Headphones", "electronics", 150.0);
    	List<Item> list = new ArrayList<Item>();
    	list.add(i1);
    	list.add(i2);
        BillRequest bill = new BillRequest();
        bill.setItems(list);
        bill.setTotalAmount(1150.0);  
        bill.setUserType("affiliate");
        bill.setCustomerTenure(3);
        bill.setEmployee(true);  // Employee flag
        bill.setAffiliate(false);  // Affiliate flag
        bill.setOriginalCurrency("USD");
        bill.setTargetCurrency("EUR");

        // Mock currency exchange rate
        when(currencyExchangeService.getExchangeRate("USD", "EUR")).thenReturn(currencyExchangeResponse);
        when(currencyExchangeResponse.getRates()).thenReturn(Map.of("EUR", 0.84));

        // Call applyDiscounts method
        double result = discountService.applyDiscounts(bill);
System.out.println("result"+result);
        assertEquals(750.0, result, "The final amount after discount should be 750.0 for affiliate");
    }

    // Test case for applying discount for employee user type
    @Test
    void testApplyDiscountsForEmployee() {
    	Item i1= new Item("Laptop", "electronics", 1000.0);
    	Item i2= new Item("Headphones", "electronics", 150.0);
    	List<Item> list = new ArrayList<Item>();
    	list.add(i1);
    	list.add(i2);
        BillRequest bill = new BillRequest();
        bill.setItems(list);
        bill.setTotalAmount(1150.0);  // Total amount of items
        bill.setUserType("employee");
        bill.setCustomerTenure(3);
        bill.setEmployee(true);  // Employee flag
        bill.setAffiliate(false);  // Affiliate flag
        bill.setOriginalCurrency("USD");
        bill.setTargetCurrency("EUR");

        // Mock currency exchange rate
        when(currencyExchangeService.getExchangeRate("USD", "EUR")).thenReturn(currencyExchangeResponse);
        when(currencyExchangeResponse.getRates()).thenReturn(Map.of("EUR", 0.84));

        double result = discountService.applyDiscounts(bill);

        // Employee discount should be applied
        assertEquals(750.0, result, "The final amount after employee discount should be 750.0");
    }

    // Test case for applying discount for customer with tenure more than 2 years
    @Test
    void testApplyDiscountsForCustomerWithTenure() {
        
    	Item i1= new Item("Laptop", "electronics", 1000.0);
    	Item i2= new Item("Headphones", "electronics", 150.0);
    	List<Item> list = new ArrayList<Item>();
    	list.add(i1);
    	list.add(i2);
    	BillRequest bill = new BillRequest();
        bill.setItems(list);
        bill.setTotalAmount(1150.0);  // Total amount of items
        bill.setUserType("customer");
        bill.setCustomerTenure(3);  // Tenure > 2 years
        bill.setEmployee(false);  // Not an employee
        bill.setAffiliate(false);  // Not an affiliate
        bill.setOriginalCurrency("USD");
        bill.setTargetCurrency("EUR");

        // Mock currency exchange rate
        when(currencyExchangeService.getExchangeRate("USD", "EUR")).thenReturn(currencyExchangeResponse);
        when(currencyExchangeResponse.getRates()).thenReturn(Map.of("EUR", 0.84));

        double result = discountService.applyDiscounts(bill);
     //   System.out.println("result testApplyDiscountsForCustomerWithTenure "+result);
        // Apply 5% discount for customer with tenure > 2 years
        assertEquals(1037.5, result, "The final amount after discount should be 1037.5");
    }

    // Test case for applying discount for groceries (no discount)
    @Test
    void testApplyDiscountsForGroceries() {
    	Item i1= new Item("Laptop", "electronics", 1000.0);
    	Item i2= new Item("Headphones", "electronics", 150.0);
    	List<Item> list = new ArrayList<Item>();
    	list.add(i1);
    	list.add(i2);
        BillRequest bill = new BillRequest();
        bill.setItems(list);
        bill.setTotalAmount(1150.0);  // Total amount of items
        bill.setUserType("customer");
        bill.setCustomerTenure(5);  // Tenure > 2 years
        bill.setGroceries(true);  // No discount for groceries
        bill.setEmployee(false);
        bill.setAffiliate(false);
        bill.setOriginalCurrency("USD");
        bill.setTargetCurrency("EUR");

        // Mock currency exchange rate
        when(currencyExchangeService.getExchangeRate("USD", "EUR")).thenReturn(currencyExchangeResponse);
        when(currencyExchangeResponse.getRates()).thenReturn(Map.of("EUR", 0.84));

        double result = discountService.applyDiscounts(bill);

        // No discount for groceries, final amount should remain the same
        assertEquals(1150.0, result, "The final amount should be 1150.0 (no discount for groceries)");
    }

    // Test case for applying discount with additional discount logic (e.g., for tenure > 2 years)
    @Test
    void testApplyDiscountsWithAdditionalDiscount() {
    	Item i1= new Item("Laptop", "electronics", 1000.0);
    	Item i2= new Item("Headphones", "electronics", 150.0);
    	List<Item> list = new ArrayList<Item>();
    	list.add(i1);
    	list.add(i2);
        BillRequest bill = new BillRequest();
        bill.setItems(list);
        bill.setTotalAmount(1150.0);  // Total amount of items
        bill.setUserType("customer");
        bill.setCustomerTenure(3);  // Tenure > 2 years
        bill.setEmployee(false);
        bill.setAffiliate(false);
        bill.setOriginalCurrency("USD");
        bill.setTargetCurrency("EUR");

        // Mock currency exchange rate
        when(currencyExchangeService.getExchangeRate("USD", "EUR")).thenReturn(currencyExchangeResponse);
        when(currencyExchangeResponse.getRates()).thenReturn(Map.of("EUR", 0.84));

        double result = discountService.applyDiscounts(bill);

        System.out.println("result testApplyDiscountsWithAdditionalDiscount "+result);
        assertEquals(1037.5, result, "The final amount should be1037.5 after the additional discount");
    }
}
