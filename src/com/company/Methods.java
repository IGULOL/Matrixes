package com.company;

public class Methods {


    public static Vector sweepMethod(Vector a, Vector b, Vector c, Vector d){
        int N = a.getSize();

        float r;
        Vector L = new Vector(N);
        Vector M = new Vector(N+1);

        for (int i = 0; i < N; i++) {
            r = b.get(i) - a.get(i)*L.get(i);
            if (r == 0) return null;
            r = 1/r;
            L.set(i+1,c.get(i)*r);
            M.set(i+1,(d.get(i) - a.get(i)*M.get(i))*r);
        }

        Vector x = new Vector(N);
        x.set(N-1,M.get(N));
        for (int i = N-2; i >= 1; i--) {
            x.set(i, M.get(i+1) - L.get(i+1)*x.get(i+1));
        }

        return x;
    }

    public static Vector unstableMethod(Vector a, Vector b, Vector c, Vector d){
        int N = a.getSize();

        Vector y = new Vector(N);
        Vector z = new Vector(N);

        z.set(0,1);
        y.set(1,d.get(0)/c.get(0));
        z.set(1,-b.get(0)/c.get(0));

        for (int i = 1; i < N; i++) {
            y.set(i+1, (d.get(i)-a.get(i)*y.get(i-1)-b.get(i)*y.get(i))/c.get(i));
            z.set(i+1, - (a.get(i)*z.get(i-1)+b.get(i)*z.get(i))/c.get(i));
        }

        float K = (d.get(N-1)-a.get(N-1)*y.get(N-2)-b.get(N-1)*y.get(N-1))
                /(a.get(N-1)*z.get(N-2)+b.get(N-1)*z.get(N-1));

        Vector x = new Vector(N);
        for (int i = 1; i < N; i++) {
            x.set(i, y.get(i)+K*z.get(i));
        }

        return x;
    }
}
