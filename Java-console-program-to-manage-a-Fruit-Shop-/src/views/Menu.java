/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Controller.Management;
import Model.Fruit;
import Model.Order;
import util.Validation;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

/**
 *
 * @author Duy_Do
 */
public class Menu {

    Management cabinet = new Management();
    private static int key = 1;

    public void testCase() {

        cabinet.addFruit("Apple", 2, 10, "VN");
        cabinet.addFruit("Orange", 4, 10, "VN");
        cabinet.addFruit("Coconut", 5, 10, "VN");

    }

    public void addFruit() {

        char choice;

        String fruitName;
        do {

            while (true) {
                fruitName = Validation.name("FruitName: ", "^[a-zA-z_ ]+$", "FruitName can't be empty!", "FruitName just contains letters in the alphabet!");

                if (cabinet.checkName(fruitName) == false) {
                    System.out.println("This Fruit already exist!");
                } else {
                    break;
                }

            }
            double price = Validation.Double("Price: ", "Price have to greater than 0!", "Price must be number!");
            int quantity = Validation.Integer("Quantity: ", "Quantity have to geater than 0!", "Quantity must be a number!");
            String origin = Validation.string("Origin: ", "Origin can't be empty!");

            cabinet.addFruit(fruitName, price, quantity, origin);

            choice = Validation.getChar("Do you want to continue(Y/N)?", "^[Y|N]$", "^(?:YES|NO)$", "Can't be empty!", "Your choice must be Yes or No!");
        } while (choice != 'N');

        System.out.println("List of Fruit: ");

        displayFruit();
        System.out.println("");
    }

    public void shopping() {

        if (cabinet.closedShop()) {
            System.out.println("SOLD OUT! HAVE A GOOD DAY!");
            return;
        }

        ArrayList<Fruit> listFruit = new ArrayList<>();
        ArrayList<Double> listAmount = new ArrayList<>();
        char nextTurn = ' ';

        do {

            displayFruit();

            int choice;
            int quantity;
            double price;
            double amount;
            String origin;
            String fruitName;
            int index;
            while (true) {
                choice = Validation.Integer("Your choice(Fruit's ID): ", "ID is a number greater than 0!", "ID must is a number!");
                index = cabinet.search(choice);
                if (index == -1) {
                    System.out.println("ID isn't exist please try another one!!!!");
                } else if (cabinet.getQuantity(index) == 0) {

                    if (cabinet.closedShop() == true) {
                        System.out.println("The shop already sold out please order now!");
                        String nameCustomer = Validation.name("Customer's Name: ", "^[a-zA-Z ]+$", "Customer's name can't be empty!", "Customer's name just contains letters in the alphabet!");
                        Order a = new Order(nameCustomer, listFruit);  // , cabinet.getTotal(listAmount)
                        cabinet.shopping(nameCustomer, listFruit);
                        String s = String.valueOf(key);
                        cabinet.addToHash(s, a);
                        key++;
                        return;
                    } else {
                        System.out.println("SOLD OUT!!!!!");
                    }

                } else if (index != -1 && cabinet.getQuantity(index) != 0) {
                    break;

                }

            }
            fruitName = cabinet.nameOfFruit(index);
            System.out.println("You choose: " + fruitName);
            price = cabinet.getPriceOfFruit(index);
            origin = cabinet.getOrigin(index);
            quantity = checkQ(fruitName);
//    
            if (cabinet.checkName(fruitName, listFruit) == true) {

                int i = cabinet.checkNameOfFruit(listFruit, fruitName);
                int newQ = listFruit.get(i).getQuantity() + quantity;

                if (cabinet.checkStore2(fruitName, newQ) == false) {

                    while (true) {

                        quantity = Validation.Integer("Please enter the quantity: ", "Greater than 0!", "Must be an integer!");
                        int check = cabinet.checkStore1(fruitName, (quantity));

                        if (check == -1) {
                            listFruit.get(i).setQuantity(quantity + listFruit.get(i).getQuantity());
                            amount = (listFruit.get(i).getQuantity()) * price;
//                             System.out.println("if amount:" + amount);
                            listAmount.remove(i);
                            listAmount.add(amount);
                            display(listFruit, listAmount);
                            cabinet.updateQuantity(fruitName, quantity);
                            break;
                        } else if (check == -2) {
                            System.out.println("SOLD OUT!");
                            break;
                        } else {
                            if (check - listFruit.get(i).getQuantity() == 0) {
                                System.out.println("SOLD OUT!");
                                break;
                            } else {
                                System.out.println("The largest you can buy is: " + check);
                            }
                        }

                    }

                } else if (cabinet.checkStore2(fruitName, newQ) == true) {
                    listFruit.get(i).setQuantity(quantity + listFruit.get(i).getQuantity());
                    amount = (listFruit.get(i).getQuantity()) * price;
//                    System.out.println("else if amount:" + amount);
                    listAmount.remove(i);
                    listAmount.add(amount);
                    display(listFruit, listAmount);
                    cabinet.updateQuantity(fruitName, quantity);
                }

            } else {

                amount = quantity * price;
//                System.out.println("Else amount: " + amount);
                listFruit.add(new Fruit(quantity, fruitName, price, quantity, origin));
                listAmount.add(amount);
                display(listFruit, listAmount);
                cabinet.updateQuantity(fruitName, quantity);

            }
//            System.out.println("SOS: " + cabinet.getQuantity(index));
            nextTurn = Validation.getChar("Do you want to order now(Y/N)?", "^[Y|N]$", "^(?:YES|NO)$", "Can't be empty!", "Your choice must be Yes or No!");

        } while (nextTurn != 'Y');

        String nameCustomer = Validation.name("Customer's Name: ", "^[a-zA-Z ]+$", "Customer's name can't be empty!", "Customer's name just contains letters in the alphabet!");
        Order a = new Order(nameCustomer, listFruit);   //, cabinet.getTotal(listAmount)
        cabinet.shopping(nameCustomer, listFruit);
        String s = String.valueOf(key);
        cabinet.addToHash(s, a);
        key++;
    }

    public void viewOrder() {

        if (cabinet.sizeOfHash() == 0) {
            System.out.println("Shop is closed!");
            return;
        }

        System.out.println("View: ");
        Hashtable<String, Order> hash = new Hashtable<>();

        hash = cabinet.getHashTable();
        Set<String> keySet = hash.keySet();
        for (String string : keySet) {
            System.out.println("Customer: " + hash.get(string).getCustomerName());
            System.out.println("FruitName      Price    Quantity        Origin               Amount");
            displayAll(hash.get(string));

            System.out.println("");
        }

    }

    public void display(ArrayList<Fruit> a, ArrayList<Double> b) {
        System.out.println("FruitName      Price    Quantity        Origin         Amount");
        for (int i = 0; i < a.size(); i++) {
            a.get(i).display2();
            System.out.println(b.get(i));
            System.out.println("");
        }
        System.out.println("Total: " + cabinet.getTotal(b));

    }

    public void displayFruit() {
        ArrayList<Fruit> listFruit = new ArrayList<Fruit>();
        listFruit = cabinet.getListFruit();
        System.out.println("ID  FruitName      Price      Origin    ");
        for (int i = 0; i < listFruit.size(); i++) {
            listFruit.get(i).display();
        }

        System.out.println("");

    }

    public int checkQ(String fruitName) {
        int quantity = 0;
        while (true) {
            quantity = Validation.Integer("Quantity: ", "Greater than 0!", "Must be an integer!");
            int check = cabinet.checkStore1(fruitName, quantity);

            if (check == -1) {
                break;
            } else if (check == -2) {
                System.out.println("SOLD OUT!!!!");

            } else {
                System.out.println("The largest you can buy is: " + check);
            }

        }

        return quantity;

    }

    public void displayAll(Order b) {

        ArrayList<Fruit> a = b.getList();

        double total = 0;
        for (int i = 0; i < a.size(); i++) {
            System.out.printf("%-13s  %-5.1f$    %-10d      %-15s     %-4.1f$\n", a.get(i).getFruitName(), a.get(i).getPrice(), a.get(i).getQuantity(), a.get(i).getOrigin(), (a.get(i).getPrice() * a.get(i).getQuantity()));
            total += a.get(i).getPrice() * a.get(i).getQuantity();
        }

        System.out.println("Total: " + total + "$");

    }

    public void displayOption() {

        System.out.println("FRUIT SHOP SYSTEM: ");
        System.out.println("1. Create Fruit");
        System.out.println("2. View orders");
        System.out.println("3. Shopping(for buyer)");
        System.out.println("4. Exit");

    }

}
