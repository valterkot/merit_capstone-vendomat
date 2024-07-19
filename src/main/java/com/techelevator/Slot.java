package com.techelevator;

import com.techelevator.logs.Log;

public class Slot  {

    private Product product; 
    private int stock; 

    public Slot (Product product, int stock) {
        this.product = product;
        this.stock = stock;
    }

    public Slot (Product product){
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean soldItem(){
        if (stock > 0) {
            stock--; 
            product.printMsg();
            Log.log(product.getName()+" has been purchased.");
            return true;
        }
        else {
            System.out.println("Product is out of stock! Please choose other product.");
            return false;
        }
    }

    // @Override
    // public int compareTo(Slot other) {
    //     return this.slotId.compareTo(other.slotId);   
    // }


}
