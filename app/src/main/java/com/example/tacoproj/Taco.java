package com.example.tacoproj;

public class Taco {
    private String name;
    private Double price;
    private int id;
    private String breakfast;//false means no, true means yes
    private String availability;//false means no, true means yes

    public Taco(int newId, String newName, double newPrice, String newAvailability, String newBreakfast){
        name = newName;
        price = newPrice;
        id = newId;
        breakfast = newBreakfast;

        availability = newAvailability;
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

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Taco{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", availability='" + availability + '\'' +
                ", breakfast='" + breakfast + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }
}
