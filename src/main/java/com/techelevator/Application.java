package com.techelevator;

import java.io.InputStream;
import java.util.Scanner;

public class Application {

	private VendingMachine vendo;
	private Scanner in; 
	private static int choice = 0;

	private final String HEADER_FOR_ITEMS_DISPLAY = String.format("%-" + 5 + "s\t%-"+ 5 + "s\t%-" + 20 + "s\t%-" + 5 + "s\t%s", "Slot", "Class", "Product", "Price", "Available"); 
	private final String HEADER_FOR_REPOERT = String.format("%-" + 5 + "s\t%-"+ 20 + "s\t%-" + 5 + "s\t%s", "Slot", "Product Name", "Price", "Sold");


	public Application (String file, InputStream input) {
		this.vendo = new VendingMachine(file);
		this.in = new Scanner(input); 
	} 

	public static void main(String[] args)  {
		String fileWithProductInventory = "vendingmachine.csv";
				
		Application application = new Application(fileWithProductInventory, System.in);
		application.welcomePage();

	}



	public void welcomePage() {
		System.out.println("\n \n \n Welcome to the VENDO-MACHINE 800 emulation!");

		boolean running = true;
		while (running) {
			printHeading("\n Welcome to the main menu: ");

			System.out.println("(1) Display Vending Machine Items");
			System.out.println("(2) Purchase");
			System.out.println("(3) Exit");

			String userInput = in.nextLine();
			try {
				choice = Integer.parseInt(userInput);
			} catch (NumberFormatException e) {
				handlingInvalidInput(userInput);
				continue; 
			}
			if (choice != 1 && choice != 2 && choice != 3 && choice != 4 ){
				handlingInvalidInput(userInput);
			}

			if (choice == 1) {
				printHeading(HEADER_FOR_ITEMS_DISPLAY);
				vendo.displayItemsinVendingMachine();
			} else if (choice == 2) {
				purchasePage();
			} else if (choice == 3) {
				running = false; 
			} else if (choice == 4) {
				System.out.println("You redirected to Sales Report: \n");

				printHeading(HEADER_FOR_REPOERT);
				vendo.generateReport();
			}
        }
	}

	public void purchasePage() {

		Purchase newPurchase = new Purchase(); 
		choice = 0; 

		while (true) {
			printHeading("Select purchase option: ");
			System.out.println("(1) Feed Money");
			System.out.println("(2) Select Product");
			System.out.println("(3) Finish Transaction");

			String userInput = in.nextLine();
			try {
				choice = Integer.parseInt(userInput);
			} catch (NumberFormatException e) {
				handlingInvalidInput(userInput);
				continue; 
			}
			if (choice != 1 && choice != 2 && choice != 3 ){
				handlingInvalidInput(userInput);
			}

			else if (choice == 1) {
				System.out.print("Please insert money to update your current balance: "); 
				String moneyInserted = in.nextLine();
				newPurchase.setCurrentBalance(Double.parseDouble(moneyInserted));
				System.out.println ("\n Money Provided: $" + newPurchase.getCurrentBalance());

			} else if (choice == 2) {
				printHeading(HEADER_FOR_ITEMS_DISPLAY);
				vendo.displayItemsinVendingMachine();

				System.out.print("Select a slot with product you want to buy: ");
				String itemSelected = in.nextLine();

				if (vendo.isExists(itemSelected)){
					double itemPrice = vendo.getProductPrice(itemSelected);
					if(itemPrice<=newPurchase.getCurrentBalance()){
						if (vendo.dropProduct(itemSelected)){
							newPurchase.setCurrentBalance(-1*itemPrice);
							System.out.println ("\n Current balance is: $" + newPurchase.getCurrentBalance());
						}
					}
					else {
						System.out.println("Your current balance is not enough for that purchase, please insert more money");
						}
				} 
				else {
					handlingInvalidInput(itemSelected);
				}
		
			} else if (choice == 3) {
				printHeading("Thank you for using our Vending machine!");
				newPurchase.giveAChange();
				choice = 0;
				break; 
			}
		}
	}

	private void printHeading(String headingText) {
		System.out.println("\n" + headingText);
		for (int i = 0; i < headingText.length(); i++) {
			System.out.print("=");
		}
		System.out.println();
	}

	private void handlingInvalidInput(String userInput){
		System.out.println("\n*** " + userInput + " is not a valid option ***\n");
	}
}
