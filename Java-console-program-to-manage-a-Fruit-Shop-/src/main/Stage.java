/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import util.Validation;
import views.Menu;

/**
 *
 * @author Duy_Do
 */
public class Stage {

    public static void main(String[] args) {

        Menu menu = new Menu();

      menu.testCase();
        int choice = 0;
        while (true) {
            menu.displayOption();
            choice = Validation.getChoice("Get your choice: ", "Please enter a number in arrange 1 to 4!", "Please enter a number!");

            switch (choice) {

                case 1:
                    menu.addFruit();
                    break;
                case 2:
                    menu.viewOrder();
                    break;
                case 3:
                    menu.shopping();
                    break;
                case 4:
                    return;

            }
        }
    }
}
