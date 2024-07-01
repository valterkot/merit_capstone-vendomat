package com.techelevator;

public class Product {
    private String name;
    private double price;
    private String type;   
    private int inStock = 5;

    public Product(String type, String name, double price){
        this.name = name; 
        this.price = price;
        this.type = type;
        if (type.equals("Chip")) { 
            inStock = 0 ;
        }
    }

    public boolean buy(){
        if (inStock > 0) {
            inStock--;
            printMsg();
            return true;
        }
        else 
        {
            System.out.println("This Item is out of stock! Please choose another Product!"); 
            return false;
        }
    }

    private void printMsg(){
        if (this.type.equals("Chip")) {
            System.out.println("Crunch Crunch, Yum!");
        } else if (this.type.equals("Candy")) {
            System.out.println("Munch Munch, Yum!");
        } else if (this.type.equals("Drink")) {
            System.out.println("Glug Glug, Yum!");
        } else if (this.type.equals("Gum")) {
            System.out.println("Chew Chew, Yum!");
        }  
    }

    @Override
    public String toString(){
        if (inStock == 0) {
            return String.format("%-" + 5 + "s\t%-" + 25 + "s\t%s",  
            type, name, "!!!OUT OF STOCK!!!");
        } else {
            return String.format("%-" + 5 + "s\t%-" + 20 + "s\t%-" + 5 + ".2f\t%s",
            type, name, price, "In Stock! (QTY: " + inStock + ")");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    

}
