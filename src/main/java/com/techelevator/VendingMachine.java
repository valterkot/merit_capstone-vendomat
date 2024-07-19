package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

// import com.techelevator.logs.Log;

public class VendingMachine {

    private Map<String, Slot> itemsInMachine = new TreeMap<>();

    public VendingMachine(String file) {
        loadMachine(file);
    }
    
    public Map<String, Slot> getItemsInMachine() {
        return itemsInMachine;
    }

    public double getProductPrice(String slotId){
        return itemsInMachine.get(slotId).getProduct().getPrice();
    }

    public boolean isAvailable(String slotId) {
        return itemsInMachine.get(slotId).getStock() != 0;
    }

    public boolean isExists(String slotId){
        return itemsInMachine.containsKey(slotId);
    }

    public boolean dropProduct(String slotId){
        return itemsInMachine.get(slotId).soldItem();
    }

    public void loadMachine(String file) {
        try {
            File inventory = new File(file);
            Scanner itemsFromFile = new Scanner(inventory); 
            System.out.print("\n Loading inventory...");
            
            while(itemsFromFile.hasNextLine()) {
                String newLine = itemsFromFile.nextLine();
                String[] itemToFill = newLine.split("\\|"); 

                String slotId = itemToFill[0];
                String productName = itemToFill[1];
                double itemPrice = Double.parseDouble(itemToFill[2]);
                String productType = itemToFill[3]; 
                Product product = new Product(productType, productName, itemPrice);

                Slot slot = new Slot(product, 5);

                itemsInMachine.put(slotId, slot);
                System.out.print(slotId+"...");
            }
            itemsFromFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // @Override
    public void displayItemsinVendingMachine(){
        for (Map.Entry<String, Slot> item : itemsInMachine.entrySet()){
            
            String slotId = item.getKey();
            int inStock = item.getValue().getStock();
            String type = item.getValue().getProduct().getType();
            String name = item.getValue().getProduct().getName();
            double price = item.getValue().getProduct().getPrice();

            String availability = String.format("%s\t%d\t", "In Stock! QTY:", inStock);
            if (inStock == 0) {
                availability = ">>> OUT OF STOCK <<<";
            } 
            String line = String.format("%-" + 5 + "s\t%-"+ 5 + "s\t%-" + 20 + "s\t%-" + 5 + "s\t%s",
                                        slotId, type, name, price, availability);
            System.out.println(line);
        }

    }
    public void generateReport () {
        double totalSales = 0;
        StringBuffer report = new StringBuffer(); 
        for (Map.Entry<String, Slot> item : itemsInMachine.entrySet()){
            String productName = item.getValue().getProduct().getName();
            int sold = 5 - item.getValue().getStock();
            totalSales +=  sold * getProductPrice(item.getKey());
            if (sold > 0) {
                String reportLine = String.format("%-" + 5 + "s\t%-"+ 20 + "s\t%-" + 5 + "s\t%s" +"\n",
                item.getKey(), productName, getProductPrice(item.getKey()), sold);
                report.append(reportLine.toString());
            }

        }
        System.out.println(report);
        System.out.println(String.format("%-5s\t%-15s%.2f\t", " **TOTAL SALES** $", "", totalSales));
    }

}
