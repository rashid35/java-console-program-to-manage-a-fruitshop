/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Scanner;

/**
 *
 * @author Duy_Do
 */
public class Validation {

    private static Scanner sc = new Scanner(System.in);

    public static String string(String msg, String error) {

        String a;

        while (true) {
            System.out.print(msg);
            a = sc.nextLine();
            if (a.length() == 0 || a.isEmpty()) {
                System.out.println(error);
            } else {
                return a.toUpperCase();
            }

        }

    }

    public static String name(String msg, String regex, String error1, String error2) {

        String a;
        boolean match;

        while (true) {
            System.out.print(msg);
            a = sc.nextLine();
            if (a.isEmpty() || a.length() == 0) {
                System.out.println(error1);
            }
            if (a.matches(regex) == false) {
                System.out.println(error2);
            } else {
                return a.toUpperCase();
            }

        }

    }

    public static double Double(String msg, String error1, String error2) {

        double a;

        while (true) {

            try {
                System.out.print(msg);
                a = Double.parseDouble(sc.nextLine());
                if (a <= 0) {
                    System.out.println(error1);

                } else {
                    return a;
                }
            } catch (Exception e) {
                System.out.println(error2);
            }

        }

    }

    public static char Char(String msg) {

        String a;

        while (true) {
            System.out.print(msg);
            a = sc.nextLine().toUpperCase().trim();

            if (a.length() == 0 || a.isEmpty()) {
                System.out.println("This is empty!");
            }
            if (a.charAt(0) != 'Y' && a.charAt(0) != 'N') {
                System.out.println("Must be Yes or No!");
            } else {
                return a.charAt(0);
            }

        }

    }

    public static int Integer(String msg, String error, String error2) {

        int a;

        while (true) {
            try {

                System.out.print(msg);
                a = Integer.parseInt(sc.nextLine());
                if (a <= 0) {
                    System.out.println(error);

                } else {
                    return a;
                }
            } catch (Exception e) {
                System.out.println(error2);
            }

        }

    }

    public static int getChoice(String msg, String error1, String error2) {
        int a;

        while (true) {
            try {
                System.out.print(msg);
                a = Integer.parseInt(sc.nextLine());
                if (a <= 0 || a > 4) {
                    System.out.println(error1);
                } else {
                    return a;
                }
            } catch (Exception e) {
                System.out.println(error2);
            }

        }

    }

    public static char getChar(String msg, String regex1, String regex2, String error1, String error2) {

        String a;
        boolean match = false;
        boolean match2 = false;

        while (true) {
            System.out.print(msg);
            a = sc.nextLine().toUpperCase().trim();
            if (a.length() == 0 || a.isEmpty()) {
                System.out.println(error1);
            }
            if (a.matches(regex1) == false && a.matches(regex2) == false) {
                System.out.println(error2);
            } else if (a.matches(regex1) == true || a.matches(regex2) == true) {
                return a.charAt(0);
            }

        }

    }
}
