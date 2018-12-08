package com.company;

import java.util.Scanner;

public class Main {

    public static int getMenu(){
        System.out.println("----------------------------");
        System.out.println("        ГЛАВНОЕ МЕНЮ        ");
        System.out.println("----------------------------");
        System.out.println("Выберите режим работы:");
        System.out.println("1 - обычный");
        System.out.println("2 - тестирование");
        System.out.println("3 - таблица с погрешностями");
        System.out.println("----------------------------");
        System.out.println("0 - выход");
        System.out.print(">> ");

        Scanner in = new Scanner(System.in);
        int ch = in.nextInt();
        System.out.println();

        return ((ch >= 0) && (ch < 4)) ? ch : -1;
    }

    public static void main(String[] args) {
        Vector a = new Vector();
        Vector b = new Vector();
        Vector c = new Vector();
        Vector d = new Vector();
        Vector x = new Vector();

        int ch;
        do {
            ch = getMenu();
            switch (ch) {
                case 1:
                    Modes.usualMode(a, b, c, d);
                    break;
                case 2:
                    Modes.testMode(a, b, c, x);
                    break;
                case 3:
                    Modes.tableMode();
                    break;
            }
            System.out.println();
        } while (ch != 0);
    }

}
