package com.fedHire.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FedHireDao {

	public ResultSet getExistingData(int parentId, Connection connection) {
		ResultSet resultSet = null;
		PreparedStatement preparedStatement;
		String query;
		try{
			query = "select * from bank_data where parent_id = ?";
			preparedStatement = connection.prepareStatement(query); 
			preparedStatement.setInt(1, parentId);
			resultSet = preparedStatement.executeQuery();			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return resultSet;
	}

	public String insertRow(String clientName, double totalAmount, double discountedAmount, int parentId,
			Connection connection) throws Exception {
		PreparedStatement preparedStatement = null;
		String query;
		int flag = 0;
		String result = null;
		try{
			query = "INSERT INTO bank_data (`c_name`, `parent_id`, `total_amount`, `discounted_amount`) VALUES (?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, clientName);
			preparedStatement.setInt(2, parentId);
			preparedStatement.setDouble(3, totalAmount);
			preparedStatement.setDouble(4, discountedAmount);
			flag = preparedStatement.executeUpdate();
			if(flag!=0){
				result = "success";
			}else{
				result = "error";
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			preparedStatement.close();
		}
		
		return result;
	}

	public ResultSet searchClient(int input, int parentId, Connection connection) throws Exception {
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		String query;
		try{
			query = "select * from bank_data where parent_id = ? and c_id = ?";
			preparedStatement = connection.prepareStatement(query); 
			preparedStatement.setInt(1, parentId);
			preparedStatement.setInt(2, input);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				//System.out.println(resultSet.getDouble("total_amount"));
				return resultSet;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			/*preparedStatement.close();
			//resultSet.close();
			connection.close();*/
		}
		
		
		return null;
	}

	public String updateRow(int clientId, double totalAmount, double discountedAmount, int standardPayment,
			Connection connection) throws Exception {
		PreparedStatement preparedStatement = null;
		String query;
		int flag = 0;
		String result = null;
		try{
			query = "UPDATE bank_data SET total_amount = ?, discounted_amount = ? WHERE c_id = ? ";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setDouble(1, totalAmount);
			preparedStatement.setDouble(2, discountedAmount);
			preparedStatement.setInt(3, clientId);
			flag = preparedStatement.executeUpdate();
			if(flag!=0){
				result = "success";
			}else{
				result = "error";
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			preparedStatement.close();
		}
		
		return result;
	
	}

	public ResultSet[] getAllExistingData(Connection connection) {
		ResultSet[] resultSet = new ResultSet[3];
		PreparedStatement preparedStatement;
		String query;
		try{
			for(int parentId=0; parentId<3;parentId++){

				query = "select * from bank_data where parent_id = ?";
				preparedStatement = connection.prepareStatement(query); 
				preparedStatement.setInt(1, parentId+1);
				resultSet[parentId] = preparedStatement.executeQuery();	
			}	
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return resultSet;
	}

	public ResultSet[] generateReport(Connection connection){
		ResultSet[] resultSet = new ResultSet[4];
		PreparedStatement preparedStatement;
		String query;
		try{
			for(int parentId=0; parentId<4;parentId++){
				if(parentId < 3){
					query = " SELECT SUM(total_amount) AS total_amount, SUM(discounted_amount) AS discounted_amount FROM bank_data WHERE parent_id = ?";
					preparedStatement = connection.prepareStatement(query); 
					preparedStatement.setInt(1, parentId+1);
					resultSet[parentId] = preparedStatement.executeQuery();	
				}else{
					query = " SELECT SUM(total_amount) AS total_amount, SUM(discounted_amount) AS discounted_amount FROM bank_data";
					preparedStatement = connection.prepareStatement(query); 
					resultSet[parentId] = preparedStatement.executeQuery();	
					
				}
			}	
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return resultSet;
		
		
	}
	
}
