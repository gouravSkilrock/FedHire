package com.fedHire;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowRecords {

	public void displayData(ResultSet resultSet) throws SQLException {

		System.out.println("Cleint ID       Name               Total Amount          Discounted Amount");
		if(resultSet != null){
			
			while(resultSet.next()){
				System.out.println();
				System.out.println(resultSet.getInt("c_id")+"               "+resultSet.getString("c_name")+"                 "+resultSet.getDouble("total_amount")+"                 "+resultSet.getDouble("discounted_amount"));
			}
			
		}else{
			System.out.println("No Record Found !!!");
		}
		
	}

	public void displayAllData(ResultSet[] resultSet)throws SQLException  {

		System.out.println("Cleint ID       Name               Total Amount          Discounted Amount");
		for(int index =0; index<3;index++){

			if(resultSet[index] != null){
				if(index == 0){
					System.out.println("Standard Client");
				}
				if(index == 1){
					System.out.println();
					System.out.println("Loyalty Client");
				}
				if(index == 2){
					System.out.println();
					System.out.println("Staff Client");
				}
				while(resultSet[index].next()){
					System.out.println();
					System.out.println(resultSet[index].getInt("c_id")+"               "+resultSet[index].getString("c_name")+"                 "+resultSet[index].getDouble("total_amount")+"                 "+resultSet[index].getDouble("discounted_amount"));
				}
				
			}else{
				System.out.println("No Record Found !!!");
			}
			
		}
			
	
	}

	public void displayAllDataReport(ResultSet[] resultSet) throws SQLException {
		String accountType = null;
		System.out.println("Type Of Account       Total Amount Recieved               Total Discount Given");
		for(int index =0; index<4;index++){

			if(resultSet[index] != null){
				if(index == 0){
				accountType = "Standard Client";
				}
				if(index == 1){
					System.out.println();
					accountType = "Loyalty Client";
				}
				if(index == 2){
					System.out.println();
					accountType = "Staff Client";
				}
				if(index == 3){
					System.out.println("--------------------------------------------------------------------------------------------");
					accountType = "Total";
				}
				while(resultSet[index].next()){
					System.out.println();
					System.out.println(accountType+"               "+resultSet[index].getString("total_amount")+"                                    "+resultSet[index].getDouble("discounted_amount"));
				}
				
			}else{
				System.out.println("No Record Found !!!");
			}
			
		}System.out.println("--------------------------------------------------------------------------------------------");
		
		
	}

}
 