package com.fedHire.common;

public interface Payment {

	String addPayment(String clientName, double totalAmount, double discountedAmount, int parentId) throws Exception;

	String updatePayment(int clientId, double totalAmount, double discountedAmount, int parentId)throws Exception;
	
}
