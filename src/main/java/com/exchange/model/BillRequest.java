package com.exchange.model;


import java.util.List;

public class BillRequest {
    private List<Item> items;
    private boolean isEmployee;
    private boolean isAffiliate;
    private String userType;
    private int customerTenure;  
    private double totalAmount;
    private String originalCurrency;
    private String targetCurrency;
    private boolean isGroceries;
    
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public boolean isEmployee() {
		return isEmployee;
	}
	public void setEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
	}
	public boolean isAffiliate() {
		return isAffiliate;
	}
	public void setAffiliate(boolean isAffiliate) {
		this.isAffiliate = isAffiliate;
	}
	public int getCustomerTenure() {
		return customerTenure;
	}
	public void setCustomerTenure(int customerTenure) {
		this.customerTenure = customerTenure;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getOriginalCurrency() {
		return originalCurrency;
	}
	public void setOriginalCurrency(String originalCurrency) {
		this.originalCurrency = originalCurrency;
	}
	public String getTargetCurrency() {
		return targetCurrency;
	}
	public void setTargetCurrency(String targetCurrency) {
		this.targetCurrency = targetCurrency;
	}
	
	public boolean isGroceries() {
		return isGroceries;
	}
	public void setGroceries(boolean isGroceries) {
		this.isGroceries = isGroceries;
	}
	@Override
	public String toString() {
		return "Bill [items=" + items + ", isEmployee=" + isEmployee + ", isAffiliate=" + isAffiliate + ", userType="
				+ userType + ", customerTenure=" + customerTenure + ", totalAmount=" + totalAmount
				+ ", originalCurrency=" + originalCurrency + ", targetCurrency=" + targetCurrency + "]";
	}
	
   
    
}
