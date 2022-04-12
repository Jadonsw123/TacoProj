package com.example.tacoproj;

public class Drink {
    private String name;
    private Double price;
    private int id;
    private String breakfast;//false means no, true means yes
    private String availability;//false means no, true means yes
    private String type;

    public Drink(int newId, String newName, double newPrice, String newAvailability, String newBreakfast, String newType){
        name = newName;
        price = newPrice;
        id = newId;
        breakfast = newBreakfast;

        availability = newAvailability;
        type = newType;
    }


    public String getName() {
        return name;
    }
    public String getBreakfast() {
        return breakfast;
    }
    public String getAvailability() {
        return availability;
    }
    public String getType  () {
        return type;
    }
    public Double getPrice() {
        return price;
    }

    public void setName(String newName) {
        name = newName;
    }
    public void setBreakfast(String newBreakfast) {
        breakfast = newBreakfast;
    }
    public void setAvailability(String newAvailability) {
        availability = newAvailability;
    }
    public void setPrice(double newPrice) {
        price = newPrice;
    }
    public void setType(String newType) {
        type = newType;
    }

    @Override
    public String toString() {
        return "Drink{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", availability='" + availability + '\'' +
                ", breakfast='" + breakfast + '\'' +
                ", type='" + type + '\'' +

                '}';
    }

    public int getId() {
        return id;
    }
}
