package com.company;

import java.io.*;
import java.util.Scanner;

/**
 * Класс для работы с векторами (сложение, вычитание, скалярное умножение,
 * вычисление нормы как максимальной по модулю компоненты, считывание
 * из файла / с экрана, вывод в файл / на экран)
 */

public class Vector {
    float[] vector;

    Vector() {
    }

    Vector(int size) {
        vector = new float[size];
    }

    Vector(float[] _vector) {
        int N = _vector.length;

        if (_vector != null) {
            vector = new float[N];

            for (int i = 0; i < N; ++i) {
                vector[i] = _vector[i];
            }
        }
    }

    public float get(int index){
        return ((index > 0) && (index < vector.length)) ? vector[index] : null;
    }

    public int getSize(){
        return vector.length;
    }

    /**
     * Умножение векторов
     * @param _vector
     * @throws VectorOperationException
     */
    public void mult(Vector _vector) throws VectorOperationException {
        if (_vector.getSize() != vector.length)
            throw new VectorOperationException("не совпадает длина");

        for (int i = 0; i < vector.length; i++) {
            vector[i] = vector[i] * _vector.get(i);
        }
    }

    /**
     * Сложение векторов
     * @param _vector
     * @throws VectorOperationException
     */
    public void add(Vector _vector) throws VectorOperationException {
        if (_vector.getSize() != vector.length)
            throw new VectorOperationException("не совпадает длина");

        for (int i = 0; i < vector.length; i++) {
            vector[i] = vector[i] + _vector.get(i);
        }
    }

    /**
     * Вычитание векторов
     * @param _vector
     * @throws VectorOperationException
     */
    public void sub(Vector _vector) throws VectorOperationException {
        if (_vector.getSize() != vector.length)
            throw new VectorOperationException("не совпадает длина векторов");

        for (int i = 0; i < vector.length; i++) {
            vector[i] = vector[i] - _vector.get(i);
        }
    }

    /**
     * Скалярное произвдение векторов
     * @param _vector
     * @return результат умножения
     * @throws VectorOperationException
     */
    public float scalarMult(Vector _vector) throws VectorOperationException {
        if (_vector.getSize() != vector.length)
            throw new VectorOperationException("не совпадает длина");

        float res = 0;
        for (int i = 0; i < vector.length; i++) {
            res = res + vector[i]*_vector.get(i);
        }
        return res;
    }

    /**
     * Норма вектора
     * @return
     * @throws VectorOperationException
     */
    public float norm() throws VectorOperationException {
        return (float) Math.sqrt(scalarMult(this));
    }

    /**
     * Печать на экран
     */
    public void print() {
        System.out.println("Длина: " + vector.length);
        for (int i = 0; i < vector.length; i++) {
            System.out.println(vector[i]);
        }
    }

    /**
     * Ввод с экрана
     */
    public void input() throws VectorInputException {
        Scanner in = new Scanner(System.in);

        try {
            int size = in.nextInt();
            vector = new float[size];

            for (int i = 0; i < vector.length; i++) {
                vector[i] = in.nextFloat();
            }
        } catch (Exception e) {
            throw new VectorInputException("неверные данные о векторе.");
        }
    }

    /**
     * Сохранение вектора в файл
     * @param filename
     */
    public void outFile(String filename) {
        File file = new File(filename);

        try {
            FileWriter writer = new FileWriter(file.getAbsolutePath(), false);
            writer.write(vector.length + "\r\n");
            writer.flush();
            for (int i = 0; i < vector.length; i++) {
                writer.write(vector[i] + "\r\n");
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
            int size = Integer.parseInt(br.readLine());

            vector = new float[size];

            for (int j = 0; j < size; j++) {
                vector[j] = Float.parseFloat(br.readLine());
            }

            br.close();
        } catch (IOException ex){
            System.err.println("Не удалось прочитать данные из файла " + file.getName());
            return;
        }
    }
}
