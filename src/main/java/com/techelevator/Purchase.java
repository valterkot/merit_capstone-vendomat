package com.techelevator;

import java.math.BigDecimal;

import com.techelevator.logs.Log;

public class Purchase {
    
    private BigDecimal currentBalance;
    
    private final BigDecimal NICKEL = BigDecimal.valueOf(0.05); 
    private final BigDecimal DIME = BigDecimal.valueOf(0.1);
    private final BigDecimal QUARTER = BigDecimal.valueOf(0.25);
    
    public Purchase(){
        currentBalance = BigDecimal.ZERO;
    }
    
    public void getChange() {
        int qChange = 0;
        int dChange = 0;
        int nChange = 0;
        BigDecimal priorBalance = currentBalance;

        while (currentBalance.compareTo(BigDecimal.ZERO) > 0 ) {
            // qChange = (int)(currentBalance / 0.25); 
            if (currentBalance.compareTo(QUARTER) >= 0) {
                qChange++;
                currentBalance = currentBalance.subtract(QUARTER);
            } else if (currentBalance.compareTo(DIME) >=0 ) {
                dChange++;
                currentBalance = currentBalance.subtract(DIME); 
            } else if (currentBalance.compareTo(NICKEL) >= 0) {
                nChange++;
                currentBalance = currentBalance.subtract(NICKEL);
            }
            else { break; 
            }
            }
        Log.log("GHANGE GIVEN: $" + priorBalance + " >>> $" + currentBalance);
        System.out.println("Please take your change: " + qChange + "x-Quarters, " + dChange + "x-Dimes, " + nChange + "x-Nickels.");
    }

    public double getCurrentBalance() {
        return currentBalance.doubleValue();
    }
    public void setCurrentBalance(double moneyInserted) {
        BigDecimal moneyToAdd = BigDecimal.valueOf(moneyInserted);
        Log.log("Balance changed: $"+ currentBalance.doubleValue() + " >>> $" + currentBalance.add(moneyToAdd));
        this.currentBalance = this.currentBalance.add(moneyToAdd);
    }

}
