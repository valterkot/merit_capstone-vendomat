package com.techelevator;

public class Product {
    private String name;
    private double price;
    private String type;

    public Product(String type, String name, double price){
        this.name = name; 
        this.price = price;
        this.type = type;
    }

    public void printMsg(){
        System.out.println("\n>>>Enjoy your "+name +"!\n");

        if (this.type.equals("Chip")) {
            System.out.println(">>>Crunch Crunch, Yum!");
        } else if (this.type.equals("Candy")) {
            System.out.println(">>>Munch Munch, Yum!");
        } else if (this.type.equals("Drink")) {
            System.out.println(">>>Glug Glug, Yum!");
        } else if (this.type.equals("Gum")) {
            System.out.println(">>>Chew Chew, Yum!");
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

}
