/*IntelliJ IDEA 2017.1.2
        Build #IC-171.4249.39, built on April 25, 2017
        JRE: 1.8.0_112-release-736-b16 amd64
        JVM: OpenJDK 64-Bit Server VM by JetBrains s.r.o
        Windows 10 10.0*/
package com.thumbtack_2017;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        FileWriter output_file = new FileWriter("output.txt"); //объект для записи в файл

        if (!new File("input.txt").exists()) { //проверка на существование входного файла
            output_file.write("Нет входного файла \"input.txt\"");
            output_file.flush();
            output_file.close();
            return;
        }

        List<String> lines = Files.readAllLines(Paths.get("input.txt"),
                Charset.forName("cp1251")); //чтение данных из файла в стандартной кодировке Windows
        int n = lines.size(); //количество строк в матрице
        ArrayList<String>[] matrix = new ArrayList[n]; //матрица

        for (int i = 0; i < n; i++) { //заполнение матрицы
            String[] line = lines.get(i).split(" "); //разделение строки на элементы матрицы
            matrix[i]=new ArrayList<>(); //создание строки матрицы
            for (int j = 0; j < line.length; j++) {
                matrix[i].add(line[j]); //заполнение строки матрицы
            }
        }

        for (int i = 0; i < n - 1; i++) { //проверка на совпадение множества элементов в строках
            if (matrix[i] != null){
                for(int j=i+1;j<n;j++)
                    if(matrix[j]!=null)
                    if (matrix[i].containsAll(matrix[j]) && matrix[j].containsAll(matrix[i]))
                        matrix[j] = null; //если есть совпадение строка удаляется
            }
        }

        for (int i = 0; i < n;i++) { //запись результата в файл
            if (matrix[i]!=null){
                int s=matrix[i].size();
                for (int j=0;j<s;j++){
                output_file.write(matrix[i].get(j)); //запись элемента матрицы
                if(j<s-1) output_file.write(" "); //добавление пробела
                }
                if(i<n-1)output_file.write(System.lineSeparator()); //добавление разделителя строки
            }
        }
        output_file.flush(); //запись файла на диск
        output_file.close(); //закрытие файла на запись
    }
}