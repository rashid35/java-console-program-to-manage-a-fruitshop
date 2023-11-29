/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Duy_Do
 */
public class Order {

    private String customerName;
    private ArrayList<Fruit> list;
  
    public Order(String customerName, ArrayList<Fruit> list) {
        this.customerName = customerName;
        this.list = list;

    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ArrayList<Fruit> getList() {
        return list;
    }

    public void setList(ArrayList<Fruit> list) {
        this.list = list;
    }

    public void display() {

        System.out.println("Customer: " + customerName);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).display();
        }

    }

}
