package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import com.techelevator.logs.Log;

public class VendingMachine {

    private Map<String, Product> itemsInMachine = new HashMap<>();

    public Map<String, Product> getItemsInMachine() {
        return itemsInMachine;
    }

    public void buyProduct(String itemLoc, Purchase curSession){
        double itemPrice = itemsInMachine.get(itemLoc).getPrice();
        if(itemPrice > curSession.getCurrentBalance()){
            System.out.println("Your current balance is not enough for that purchase, please provide more money");
        } else {
            if (itemsInMachine.get(itemLoc).buy()) {
                Log.log(itemsInMachine.get(itemLoc).getName() + " " + itemLoc + " $" + curSession.getCurrentBalance() + " >>> $" + (curSession.getCurrentBalance() - itemPrice));
                curSession.setCurrentBalance(-1*itemsInMachine.get(itemLoc).getPrice());
            };
        }
    }

    public void loadMachine(String file) throws FileNotFoundException{
        File inventory = new File(file);
        Scanner itemsFromFile = new Scanner(inventory); 

        while(itemsFromFile.hasNextLine()) {
            String newLine = itemsFromFile.nextLine();
            String[] itemToFill = newLine.split("\\|"); 
            double itemPrice = Double.parseDouble(itemToFill[2]);
            itemsInMachine.put(itemToFill[0], new Product(itemToFill[3], itemToFill[1], itemPrice));
        }
        itemsFromFile.close();   
    }

    @Override
    public String toString(){
        
        TreeMap<String, Product> sortedMap = new TreeMap<>(itemsInMachine);
		StringBuffer output = new StringBuffer();

        for (Map.Entry<String, Product> item : sortedMap.entrySet()){
            output.append(item.getKey() + " >>> " + item.getValue()+ "\n");
            // output.append(item.getKey() + " >>> " + item.getValue().getType()+ "\t" + item.getValue().getName() + "\t" + item.getValue().getPrice() + "\n");
            // output.append(); 
        }
        return output.toString();
    }

}
