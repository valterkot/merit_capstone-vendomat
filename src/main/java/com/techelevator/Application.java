package com.techelevator;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Application {

	public static VendingMachine vendo = new VendingMachine(); 
	private static final Scanner scanner = new Scanner(System.in);
	private static int choice = 0;

	public static void main(String[] args) throws FileNotFoundException {

		System.out.println("Hello to the new VENDO-MACHINE 800 emulation!");

		vendo.loadMachine("vendingmachine.csv");

		welcomePage();

	}

	public static void welcomePage() {
		
        // Scanner scanner = new Scanner(System.in);
		
        while (choice != 3) {
			System.out.println("\n Welcome to the main menu: ");
			System.out.println("===========================");
			System.out.println("(1) Display Vending Machine Items");
			System.out.println("(2) Purchase");
			System.out.println("(3) Exit");

			String input = scanner.nextLine();
			try {
				choice = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("Invalid input! Please enter a number 1-3 to choose next step from menu: ");
				continue; 
			}

			if (choice == 1) {
				System.out.println("Here is available items in a vending machine: ");
				System.out.println(vendo.toString());
			} else if (choice == 2) {
				System.out.println("You will be redirected to purchase page shortly: ");
				// scanner.close();
				purchasePage();
			} else if (choice == 4) {
				System.out.println("You chose hidden option for Sales Report: ");
				SalesReport.generateReport(vendo.getItemsInMachine());
				// System.out.println("Incorrect input, please choose one of options bellow: ");
			}
        }
	}

	public static void purchasePage() {

		Purchase newPurchase = new Purchase(); 
		choice = 0; 
		// Scanner scanner = new Scanner(System.in);

		while (choice != 3) {
			System.out.println("\n Select purchase option: ");
			System.out.println("===========================");
			System.out.println("(1) Feed Money");
			System.out.println("(2) Select Product");
			System.out.println("(3) Finish Transaction");

			String input = scanner.nextLine();
			try {
				choice = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("Invalid input! Please enter a number 1-3 to choose next step from menu: ");
				continue; 
			}

			if (choice == 1) {
				System.out.print("Please add money to your current balance: "); 
				String moneyAmount = scanner.nextLine();
				newPurchase.setCurrentBalance(Double.parseDouble(moneyAmount));
				System.out.println ("Current Money Provided: $" + newPurchase.getCurrentBalance());

			} else if (choice == 2) {
				System.out.println(vendo.toString());
				System.out.print("Select product you want to buy: ");
				String itemForPurchase = scanner.nextLine();
				if ((itemForPurchase.charAt(0) >= 'A' && itemForPurchase.charAt(0) <= 'D') 
					&& (itemForPurchase.charAt(1) >= '1' && itemForPurchase.charAt(1) <= '4')){
						vendo.buyProduct(itemForPurchase, newPurchase);
						System.out.println("\nGet your: " + vendo.getItemsInMachine().get(itemForPurchase).getName());
						System.out.println("Your current balance is: $"+newPurchase.getCurrentBalance());
					} else {
						System.out.println("You provided incorrect Item location, please try again!");
					}
			} else if (choice == 3) {
				// scanner.close();
				System.out.println("Thank you for using our Vending machine! ");
				newPurchase.getChange();
				choice = 0;
				welcomePage();
			}
		}    

	}
	
}
