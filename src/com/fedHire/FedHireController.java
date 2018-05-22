package com.fedHire;

import java.sql.SQLException;
import java.util.Scanner;

public class FedHireController {

	public static void main(String[] args) throws Exception {
		
		System.out.println(" FedHire Payment System ");
		System.out.println("==========================");
		Scanner scanner = new Scanner(System.in);
		for(;;){
			int menuId = displayMainMenu(scanner);
			//System.out.println(menuId);
			if(menuId == 6){
				System.out.println("Exiting FedHire Payment System");
				System.out.println("  ");
				System.out.println(" Good Bye  !!!");
				break;
			}
			switch (menuId) {
				case 1:
						StandardPayment payment = new StandardPayment();
						payment.start();
						break;
				case 2: 
						LoyaltyPayment lPayment = new LoyaltyPayment();
						lPayment.start();
						break;
				case 3:
					
					StaffPayment sPayment = new StaffPayment();
					sPayment.start();
					break;
				case 4:
					StandardPayment standardPayment = new StandardPayment();
					standardPayment.fetchAllExistingClient();
					break;
				case 5:
					StandardPayment standardPayment2 = new StandardPayment();
					standardPayment2.getReport();
					break;
				case 6:
					break;
		
				default: System.out.println("Please enter right options !!!");
					break;
			}
				
		}
		
	}

	private static int displayMainMenu(Scanner scanner) {
		int flag =0;
		int menuId = 0 ;
		System.out.println();
		System.out.println();
		System.out.println();
		
		System.out.println("1. Standard Payment");
		System.out.println("2. Loyalty Payment");
		System.out.println("3. Staff Payment");
		System.out.println("4. List of Clients");
		System.out.println("5. Cleint Payment Recieved Report");
		System.out.println("6. Exit");
		System.out.println();
		System.out.println();
		System.out.print("Please Enter your Choice : ");
		
		for(;;){
			if(scanner.hasNextInt()){
				menuId = scanner.nextInt();
				flag=1;
			}else{
				System.out.println("Wrong Input, Please try again");
			}
			
			if(flag == 1)
				break;
		}
		
		System.out.println();
		
		return menuId;
	}
}
