package dto;

 

public class Mobile {
 private String make;
 private String modelNumber;
 private double price;

 // Getters and setters
 public String getMake() { return make; }
 public void setMake(String make) { this.make = make; }

 public String getModelNumber() { return modelNumber; }
 public void setModelNumber(String modelNumber) { this.modelNumber = modelNumber; }

 public double getPrice() { return price; }
 public void setPrice(double price) { this.price = price; }

 // toString method
 @Override
 public String toString() {
     return "Mobile{" +
             "make='" + make + '\'' +
             ", modelNumber='" + modelNumber + '\'' +
             ", price=" + price +
             '}';
 }
}
