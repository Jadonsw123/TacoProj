package com.example.tacoproj;

public class Taco {
    private String name;
    private Double price;
    private int id;

    public Taco(int newId, String newName, double newPrice){
        name = newName;
        price = newPrice;
        id = newId;

    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Taco{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }
}
