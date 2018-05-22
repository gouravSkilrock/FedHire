package com.fedHire;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.fedHire.Dao.FedHireDao;
import com.fedHire.common.Payment;
import com.fedHire.connection.DBConnect;
import com.fedHire.payment.ProcessPayment;

public class StaffPayment {



	 private static final int STAFF_PAYMENT = 3;
	private static final double discount = 0.20;

	public void start() throws Exception{
		 int flag=0;
		 int input = 0;
		 double amount;
		 double discountedAmount;
		 double totalAmount;
		 double totalDiscountedAmount;
		 String clientName;
		 ResultSet resultSet = null;
		 Scanner scanner = new Scanner(System.in);
		 fetchExistingClient();
		 System.out.print("Enter existing Client Id or Zero('0') to add new one : ");
		 for(;;){
			 if(scanner.hasNextInt()){
				 input = scanner.nextInt();
				 flag=1;
			 }else{
				 System.out.println("Wrong Input, Please try again");
			 }

			 if(flag == 1)
				 break;
		 }
		 if(input == 0){
			 
			 System.out.print("Please enter name : ");
			 clientName = scanner.next();
			 System.out.println();
			 System.out.print("Please enter amount : ");
			 amount = scanner.nextDouble();
			 discountedAmount = getDiscountedAmount(amount);
			 System.out.println();
			 System.out.println("Amount is processing... ");
			 Payment payment = new ProcessPayment();
			 String result = payment.addPayment(clientName, (amount-discountedAmount), discountedAmount,STAFF_PAYMENT);
			 if(result.equalsIgnoreCase("success")){
				 System.out.println("Amount added successfully !!!");	 
			 }else if(result.equalsIgnoreCase("error")){
				 System.out.println("Failed!!!");
			 }
		}else if(input!=0){
			 resultSet = isClientExist(input);
			 if(resultSet!=null){
				 System.out.print("Please enter amount : ");
				 amount = scanner.nextDouble();
				 discountedAmount = getDiscountedAmount(amount);
				 totalAmount = (amount-discountedAmount) + resultSet.getDouble("total_amount");
				 totalDiscountedAmount = discountedAmount +resultSet.getDouble("discounted_amount");
				 Payment payment = new ProcessPayment();
				 String result = payment.updatePayment(input,totalAmount, totalDiscountedAmount,STAFF_PAYMENT);
				 if(result.equalsIgnoreCase("success")){
					 System.out.println("Amount added successfully !!!");	 
				 }else if(result.equalsIgnoreCase("error")){
					 System.out.println("Failed!!!");
				 }	 
			 }else{
				 System.out.println("Please enter correct client Id");
			 }
		 }
		 
	 }

	private ResultSet isClientExist(int input) throws Exception {
		FedHireDao dao = new FedHireDao();
		Connection connection = DBConnect.getConnection();
		return dao.searchClient(input,STAFF_PAYMENT,connection);
		
	}

	private double getDiscountedAmount(double amount) {
		
		return amount*discount;
	}

	private void fetchExistingClient() throws SQLException {
		FedHireDao dao;
		Connection connection = null;
		ResultSet resultSet;
		ShowRecords showRecord;
		try{
		    dao = new FedHireDao();
			connection = DBConnect.getConnection();
			resultSet = dao.getExistingData(STAFF_PAYMENT, connection);
			showRecord = new ShowRecords();
			showRecord.displayData(resultSet);	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	}
	



}
