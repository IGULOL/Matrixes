package com.company;

public class Main {

    public static void main(String[] args) {
        Vector a = new Vector();
        Vector b = new Vector();
        Vector c = new Vector();
        Vector d = new Vector();

        a.inFile("a.txt");
        b.inFile("b.txt");
        c.inFile("c.txt");
        d.inFile("d.txt");

        Vector x = Methods.sweepMethod(a,b,c,d);

        x.print();

        Vector x2 = Methods.unstableMethod(a,b,c,d);

        x2.print();
    }

}
