/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Fruit;
import Model.Order;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author Duy_Do
 */

// here
public class Management {

    private ArrayList<Fruit> listFruit = new ArrayList<>();
    private Hashtable<String, Order> hash = new Hashtable<>();
    private ArrayList<Order> listOrder = new ArrayList<>();

    private static int id = 1;

    public void addFruit(String fruitName, double price, int quantity, String origin) {
        listFruit.add(new Fruit(id, fruitName, price, quantity, origin));
        id++;

    }

    public void shopping(String customerName, ArrayList<Fruit> a) {

        listOrder.add(new Order(customerName, a));

    }

    public void addToHash(String ss, Order a) {

        hash.put(ss, a);

    }

    public boolean checkName(String name) {

        for (int i = 0; i < listFruit.size(); i++) {
            if (listFruit.get(i).getFruitName().equalsIgnoreCase(name)) {
                return false;
            }
        }

        return true;

    }

    public int search(int id) {

        int index = -1;
        for (int i = 0; i < listFruit.size(); i++) {
            if (listFruit.get(i).getFruitID() == id) {
                index = i;
            }
        }

        return index;

    }

    public double getTotal(ArrayList<Double> a) {
        double total = 0;

        for (int i = 0; i < a.size(); i++) {
            total += a.get(i);
        }

        return total;
    }

    public int checkNameOfFruit(ArrayList<Fruit> a, String name) {
        int index = -1;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).getFruitName().equalsIgnoreCase(name)) {
                index = i;
            }
        }

        return index;

    }

    public int getQuantity(String name) {
        int quantity = -1;
        for (int i = 0; i < listFruit.size(); i++) {
            if (listFruit.get(i).getFruitName().equalsIgnoreCase(name)) {
                quantity = listFruit.get(i).getQuantity();
            }
        }

        return quantity;

    }

    public int getQuantity(int index) {

        return listFruit.get(index).getQuantity();

    }

    public int checkStore1(String name, int Quantity) {

        int sold = 0;
        for (int i = 0; i < listOrder.size(); i++) {
            if (checkName(name, listOrder.get(i).getList())) {
                sold += sumSold(listOrder.get(i).getList(), name);
            }
        }

//        System.out.println("sold: " + sold);
        int oldQuantity = getQuantity(name);
//        System.out.println("old: " + getQuantity(name));

        if (oldQuantity == 0) {
            return -2;
        }

        int newQuatity = sold + Quantity;
//        System.out.println("Q: " + Quantity);
//        System.out.println("new: " + newQuatity);
        if (sold == 0 && Quantity <= oldQuantity) {
            return -1;
//        } else if (sold != 0 && sold == oldQuantity) {
//            return -2;
        } else if (newQuatity < oldQuantity) {
            return -1;
        } else if (newQuatity > oldQuantity && sold == 0) {
//            System.out.println("1");
            return oldQuantity;
        } else if (newQuatity > (oldQuantity + sold) && sold != 0) {

            return (oldQuantity + sold) - sold;

        } else {
            return -1;
        }

    }

    public boolean closedShop() {

        int sum = 0;

        for (int i = 0; i < listFruit.size(); i++) {
            sum += listFruit.get(i).getQuantity();
        }

        if (sum == 0) {
            return true;
        } else {
            return false;
        }

    }

//    public int checkStore3(String name, int Quantity) {
//        
//       
//        
//        
//        int sold = 0;
//        for (int i = 0; i < listOrder.size(); i++) {
//            if (checkName(name, listOrder.get(i).getList())) {
//                sold += sumSold(listOrder.get(i).getList(), name);
//            }
//        }
//        
//        
//        System.out.println("sold: " + sold);
//        int oldQuantity = getQuantity(name);
//        System.out.println("old: " + getQuantity(name));
//        
//        if(oldQuantity == 0){
//            return -1;   
//        }
//
//        int newQuatity = sold + Quantity;
//        System.out.println("new: " + newQuatity);
//        if (sold == 0 && Quantity <= oldQuantity) {
//            return 1;
//        } else if (sold != 0 && sold == oldQuantity) {
//            return -1;
//        } else if (newQuatity < oldQuantity) {
//            return 1;
//        } else if (newQuatity > oldQuantity && sold == 0) {
//           
//            return oldQuantity;
//        } else if (newQuatity > oldQuantity && sold != 0) {
//             
//            return oldQuantity - sold;
//            
//        } else {
//            return 1;
//        }
//
//    }
    public boolean checkStore2(String name, int Quantity) {

        int sold = 0;
        for (int i = 0; i < listOrder.size(); i++) {
            if (checkName(name, listOrder.get(i).getList())) {
                sold += sumSold(listOrder.get(i).getList(), name);
            }
        }

        int oldQuantity = getQuantity(name);

        if (oldQuantity == 0) {
            return false;
        }

        int newQuatity = sold + Quantity;

        if (sold == 0 && Quantity <= oldQuantity) {
            return true;
        } else if (sold != 0 && sold == oldQuantity) {
            return false;
        } else if (newQuatity < oldQuantity) {
            return true;
        } else if (newQuatity > oldQuantity && sold == 0) {
            return true;
        } else if (newQuatity > oldQuantity && sold != 0) {

            return true;
        } else {
            return false;
        }

    }

    public boolean checkName(String name, ArrayList<Fruit> a) {

        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).getFruitName().equalsIgnoreCase(name)) {
                return true;
            }
        }

        return false;

    }

    public void updateQuantity(String name, int sold) {

        for (int i = 0; i < listFruit.size(); i++) {
            if (listFruit.get(i).getFruitName().equalsIgnoreCase(name)) {
                listFruit.get(i).setQuantity(listFruit.get(i).getQuantity() - sold);
            }
        }

    }

    public int sumSold(ArrayList<Fruit> sc, String fruitName) {

        int sold = 0;

        for (int i = 0; i < sc.size(); i++) {
            if (sc.get(i).getFruitName().equalsIgnoreCase(fruitName)) {
                sold += sc.get(i).getQuantity();
            }
        }

        return sold;

    }

    public int sizeOfFruit() {
        return listFruit.size();
    }

    public int sizeOfHash() {
        return hash.size();
    }

    public ArrayList getListFruit() {
        return listFruit;
    }

    public String nameOfFruit(int index) {
        return listFruit.get(index).getFruitName();
    }

    public double getPriceOfFruit(int index) {

        return listFruit.get(index).getPrice();
    }

    public String getOrigin(int index) {

        return listFruit.get(index).getOrigin();

    }

    public ArrayList getListOrder() {
        return listOrder;
    }

    public Hashtable getHashTable() {
        return hash;
    }

}
