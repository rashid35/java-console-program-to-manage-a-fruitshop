/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Duy_Do
 */
public class Fruit {

    private int fruitID;
    private String fruitName;
    private double price;
    private int quantity;
    private String origin;

    public Fruit(int fruitID, String fruitName, double price, int quantity, String origin) {
        this.fruitID = fruitID;
        this.fruitName = fruitName;
        this.price = price;
        this.quantity = quantity;
        this.origin = origin;
    }

    public int getFruitID() {
        return fruitID;
    }

    public void setFruitID(int fruitID) {
        this.fruitID = fruitID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

    public void display() {
        System.out.printf("%-4d %-13s  %-2.1f$      %-10s\n",
                getFruitID(), getFruitName(), getPrice(), getOrigin());
    }

    public void display2() {
        System.out.printf("%-13s  %-5.1f$    %-10d      %-15s",
                 getFruitName(), getPrice(), getQuantity(), getOrigin());

    }

}
