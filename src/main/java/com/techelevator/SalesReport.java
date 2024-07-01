package com.techelevator;

import java.util.Map;
import java.util.TreeMap;

public class SalesReport {
    private static TreeMap<String, Product> report;

    public static void generateReport (Map<String, Product> vendingMap) {
        report = new TreeMap<>(vendingMap);
        double totalSales = 0;
        for (Map.Entry<String, Product> item : report.entrySet()){
            int sold = 5-item.getValue().getInStock();
            System.out.println(item.getValue().getName() + "|" + sold);
            totalSales +=  sold * item.getValue().getPrice();
        }
        System.out.println("\n**TOTAL SALES** $" + totalSales);
    }
    
}
