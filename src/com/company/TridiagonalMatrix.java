package com.company;

import java.io.*;
import java.util.Scanner;

/**
 * Класс для работы с трёхдиагональными матрицами (сложение, вычитание, умножение,
 * умножение на вектор, считывание из файла / с экрана, вывод в файл / на экран;
 * при задании матриц предусматривать, что a1= сn= 0).
 */
public class TridiagonalMatrix {

    float[][] matrix;

    TridiagonalMatrix(){
    }

    TridiagonalMatrix(float[][] _matrix) throws MatrixInputException{
        if (_matrix.length != _matrix[0].length)
            throw new MatrixInputException("матрица должна быть квадратная");
        int N = matrix.length;

        if (_matrix != null) {
            matrix = new float[N][N];

            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    matrix[i][j] = _matrix[i][j];
                }
            }
        }
    }

    TridiagonalMatrix(Vector a, Vector b, Vector c) throws MatrixInputException{
        int N = b.getSize();

        if (c.getSize() != N || a.getSize() != N)
            throw new MatrixInputException("веткоры должны иметь одинаковый размер");

        matrix = new float[N][N];

        if ((a.get(0) != 0) || (c.get(N-1) != 0))
            throw new MatrixInputException("A1 и Cn должны быть нулевые");

        matrix[0][0] = b.get(0);
        matrix[0][1] = c.get(0);
        for (int i = 1; i < N - 1; i++) {
            matrix[i][i-1] = a.get(i);
            matrix[i][i] = b.get(i);
            matrix[i][i+1] = c.get(i);
        }
        matrix[N - 1][N - 2] = a.get(N-1);
        matrix[N - 1][N - 1] = b.get(N-1);
    }

    public float get(int indexI, int indexJ){
        return ((indexI > 0) && (indexI < matrix.length) && (indexJ > 0) && (indexI < matrix[0].length))
                ? matrix[indexI][indexJ] : null;
    }

    public float getSize(){
        return matrix.length;
    }

    /**
     * Умножение матриц
     * @param _matrix
     * @return
     * @throws MatrixOperationException
     */
    public float[][] mult(TridiagonalMatrix _matrix) throws MatrixOperationException {
        if (_matrix.getSize() != matrix.length)
            throw new MatrixOperationException("не совпадает размер матриц");

        int N = matrix.length;

        float[][] res = new float[N][N];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                for(int k = 0; k < N; k++) {
                    res[i][j] += matrix[i][k] * _matrix.get(k,j);
                }
            }
        }
        return res;
    }

    /**
     * Сложение матриц
     * @param _matrix
     * @return
     * @throws MatrixOperationException
     */
    public float[][] add(TridiagonalMatrix _matrix) throws MatrixOperationException {
        if (_matrix.getSize() != matrix.length)
            throw new MatrixOperationException("не совпадает размер матриц");

        int N = matrix.length;

        float[][] res = new float[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                res[i][j] = matrix[i][j] + _matrix.get(i,j);
            }
        }
        return res;
    }

    /**
     * Вычитание матриц
     * @param _matrix
     * @return
     * @throws MatrixOperationException
     */
    public float[][] sub(TridiagonalMatrix _matrix) throws MatrixOperationException {
        if (_matrix.getSize() != matrix.length)
            throw new MatrixOperationException("не совпадает длина");

        int N = matrix.length;

        float[][] res = new float[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                res[i][j] = matrix[i][j] - _matrix.get(i,j);
            }
        }
        return res;
    }

    /**
     * Умножение на вектор
     * @param vector
     * @return
     * @throws MatrixOperationException
     */
    public float[] multByVector(Vector vector) throws MatrixOperationException {
        if (vector.getSize() != matrix.length)
            throw new MatrixOperationException("не совпадает размер матрицы и вектора");

        int N = matrix.length;

        float[] resVector = new float[N];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                resVector[i] += matrix[i][j] * vector.get(j);
            }
        }
        return resVector;
    }

    /**
     * Печать на экран
     */
    public void print() {
        System.out.println("Размер: " + matrix.length);
        String str;
        for (int i = 0; i < matrix.length; i++) {
            str = "";
            for (int j = 0; j < matrix[i].length; j++) {
                str = str + matrix[i][j] + "  ";
            }
            System.out.println(str);
        }
    }

    /**
     * Ввод с экрана
     */
    public void input() throws VectorInputException {
        Scanner in = new Scanner(System.in);

        try {
            int N = in.nextInt();
            matrix = new float[N][N];

            String str;

            for (int i = 0; i < matrix.length; i++) {
                str = in.nextLine();
                String[] values = str.trim().split(" +");
                for (int j = 0; j < N; i++) {
                    matrix[i][j] = Float.parseFloat(values[i]);
                }
            }
        } catch (Exception e) {
            throw new VectorInputException("неверные данные о матрице.");
        }
    }

    /**
     * Сохранение вектора в файл
     * @param filename
     */
    public void outFile(String filename) {
        File file = new File(filename);

        try {
            int N = matrix.length;
            FileWriter writer = new FileWriter(file.getAbsolutePath(), false);
            writer.write(N + "\r\n");
            writer.flush();

            String str;
            for (int i = 0; i < N; i++) {
                str = "";
                for (int j = 0; j < N; j++) {
                    str = str + matrix[i][j] + " ";
                }
                writer.write(str + "\r\n");
                writer.flush();
            }
        } catch(IOException e){
            System.err.println("Ошибка! Не удалось записать данные в файл.");
            return;
        }
    }

    /**
     * Загрузка вектора из файла
     * @param filename
     */
    public void inFile(String filename){
        File file = new File(filename);

        try {
            FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream, "windows-1251"));

            String strLine;

            if (!br.ready()) {
                System.out.println("Файл " + file.getName() + " пуст.");
                return;
            }

            //длина вектора
            int N = Integer.parseInt(br.readLine());

            matrix = new float[N][N];
            String str;
            for (int i = 0; i < N; i++) {
                str = br.readLine();
                String[] values = str.trim().split(" +");
                for (int j = 0; j < N; i++) {
                    matrix[i][j] = Float.parseFloat(values[i]);
                }
            }

            br.close();
        } catch (IOException ex){
            System.err.println("Не удалось прочитать данные из файла " + file.getName());
            return;
        }
    }
}
