package com.fedHire.payment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fedHire.ShowRecords;
import com.fedHire.Dao.FedHireDao;
import com.fedHire.common.Payment;
import com.fedHire.connection.DBConnect;

public class ProcessPayment implements Payment{


	@Override
	public String addPayment(String clientName, double totalAmount, double discountedAmount,int parentId) throws Exception {
		FedHireDao dao;
		Connection connection = null;
		String result = null;
		try{
		    dao = new FedHireDao();
			connection = DBConnect.getConnection();
			result = dao.insertRow(clientName, totalAmount, discountedAmount,parentId, connection);	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	    return result;
		
	}

	@Override
	public String updatePayment(int clientId, double totalAmount, double discountedAmount,int parentId) throws Exception {
		FedHireDao dao;
		Connection connection = null;
		String result = null;
		try{
		    dao = new FedHireDao();
			connection = DBConnect.getConnection();
			result = dao.updateRow(clientId, totalAmount, discountedAmount,parentId, connection);	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	    return result;
		
		
		
	}

	

}
