package com.example.tacoproj;

public class Topping {
    private String name;
    private Double price;
    private int id;
    private String breakfast;//false means no, true means yes
    private String availability;//false means no, true means yes
    private String type;

    public Topping(int newId, String newName, double newPrice, String newAvailability, String newBreakfast, String newType){
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

    @Override
    public String toString() {
        return "Topping{" +
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
