package com.company;

import java.util.Scanner;

public class Modes {

    private static int getMenu(){
        System.out.println("----------------------------");
        System.out.println("         МЕНЮ ВЫБОРА        ");
        System.out.println("----------------------------");
        System.out.println("Заполнение векторов:");
        System.out.println("1 - из файлов");
        System.out.println("2 - случайными числами");
        System.out.println("----------------------------");
        System.out.println("0 - выход");
        System.out.print(">> ");

        Scanner in = new Scanner(System.in);
        int ch = in.nextInt();
        System.out.println();

        return ((ch >= 0) && (ch < 3)) ? ch : -1;
    }

    /**
     * Вычисление x и x2
     * @param a вектор
     * @param b вектор
     * @param c вектор
     * @param d результат
     */
    private static void calcXAndX2(Vector a, Vector b, Vector c, Vector d){
        Vector x;
        Vector x2;

        System.out.println("Метод прогонки:");
        x = Methods.sweepMethod(a,b,c,d);
        if (x != null) {
            x.print();
        } else {
            System.out.println("Что-то пошло не так.");
        }

        System.out.println("Неустойчивый метод:");
        x2 = Methods.unstableMethod(a,b,c,d);
        if (x2 != null) {
            x2.print();
        } else {
            System.out.println("Что-то пошло не так.");
        }

        try {
            System.out.println("Норма: " + x.norm(x2));
        } catch (Exception e){
            System.out.println("Произошла ошибка вычисления нормы.");
        }
    }

    /**
     * Вычисление x и x2
     * @param a вектор
     * @param b вектор
     * @param c вектор
     * @param x результат
     */
    private static void calcTestXAndX2(Vector a, Vector b, Vector c, Vector x){
        Vector d = new Vector();
        Vector x2;
        Vector x3;

        try {
            TridiagonalMatrix matr = new TridiagonalMatrix(a, b, c);
            d = new Vector(matr.multByVector(x));
        } catch (Exception e) {
            System.out.println("Что-то пошло не так.");
        }

        System.out.println("Метод прогонки:");
        x2 = Methods.sweepMethod(a,b,c,d);
        try {
            System.out.println("Норма: " + x.norm(x2));
        } catch (Exception e){
            System.out.println("Произошла ошибка вычисления нормы.");
        }

        System.out.println("Неустойчивый метод:");
        x3 = Methods.sweepMethod(a,b,c,d);
        try {
            System.out.println("Норма: " + x.norm(x3));
        } catch (Exception e){
            System.out.println("Произошла ошибка вычисления нормы.");
        }
    }


    /**
     * Обычный режим
     * @param a вектор
     * @param b вектор
     * @param c вектор
     * @param d результат
     */
    public static void usualMode(Vector a, Vector b, Vector c, Vector d){
        int ch;
        int N;
        do {
            ch = getMenu();
            switch (ch) {
                case 1:
                    a.inFile("a.txt");
                    b.inFile("b.txt");
                    c.inFile("c.txt");
                    d.inFile("d.txt");

                    calcXAndX2(a,b,c,d);
                    break;
                case 2:
                    System.out.print("Введите размер вектора: ");
                    Scanner in = new Scanner(System.in);
                    N = in.nextInt();

                    a.Random(N);
                    a.set(0,0);
                    System.out.println("Вектор a:");
                    a.print();

                    b.Random(N);
                    System.out.println("Вектор b:");
                    b.print();

                    c.Random(N);
                    c.set(N-1,0);
                    System.out.println("Вектор c:");
                    c.print();

                    d.Random(N);
                    System.out.println("Вектор d:");
                    d.print();


                    calcXAndX2(a,b,c,d);
                    break;
            }
            System.out.println();
        } while (ch != 0);
    }

    /**
     * Тестовый режим
     * @param a вектор
     * @param b вектор
     * @param c вектор
     * @param x результат
     */
    public static void testMode(Vector a, Vector b, Vector c, Vector x){
        int ch;
        int N;
        do {
            ch = getMenu();
            switch (ch) {
                case 1:
                    a.inFile("a.txt");
                    b.inFile("b.txt");
                    c.inFile("c.txt");
                    x.inFile("x.txt");

                    calcTestXAndX2(a,b,c,x);
                    break;
                case 2:
                    System.out.print("Введите размер вектора: ");
                    Scanner in = new Scanner(System.in);
                    N = in.nextInt();

                    a.Random(N);
                    a.set(0,0);
                    System.out.println("Вектор a:");
                    a.print();

                    b.Random(N);
                    System.out.println("Вектор b:");
                    b.print();

                    c.Random(N);
                    c.set(N-1,0);
                    System.out.println("Вектор c:");
                    c.print();

                    x.Random(N);
                    System.out.println("Вектор x:");
                    x.print();

                    calcTestXAndX2(a,b,c,x);
                    break;
            }
            System.out.println();
        } while (ch != 0);
    }

    public static void tableMode(){

    }
}
