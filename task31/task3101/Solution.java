package com.javarush.task.task31.task3101;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
Проход по дереву файлов
Проход по дереву файлов
1. На вход метода main подаются два параметра.
Первый - path - путь к директории, второй - resultFileAbsolutePath - имя (полный путь) существующего файла, который будет содержать результат.
2. Для каждого файла в директории path и в ее всех вложенных поддиректориях выполнить следующее:
Если у файла длина в байтах НЕ больше 50, то для всех таких файлов:
2.1. Отсортировать их по имени файла в возрастающем порядке, путь не учитывать при сортировке.
2.2. Переименовать resultFileAbsolutePath в 'allFilesContent.txt' (используй метод FileUtils.renameFile, и, если понадобится, FileUtils.isExist).
2.3. В allFilesContent.txt последовательно записать содержимое всех файлов из п. 2.2.1. После каждого тела файла записать "\n".
Все файлы имеют расширение txt.
В качестве разделителя пути используй "/".


Требования:
1. Файл, который приходит вторым параметром в main, должен быть переименован в allFilesContent.txt.
2. Нужно создать поток для записи в переименованный файл.
3. Содержимое всех файлов, размер которых не превышает 50 байт, должно быть записано в файл allFilesContent.txt в отсортированном по имени файла порядке.
4. Поток для записи в файл нужно закрыть.
5. Не используй статические переменные.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
        File result = new File(args[1]);
        //dir.re
        File dir = new File(args[0]);
        File destFile = new File(result.getParent() + "/allFilesContent.txt");
        if (FileUtils.isExist(result)) {
            FileUtils.renameFile(result, destFile);
        }

        File[] files = dir.listFiles();
        ArrayList<File> nameList = new ArrayList<>();
//        for (File file : files) {
//            if (file.isDirectory())
//                System.out.println(file.getName());
//
//        }

        solution.reqWalk(dir, nameList);
        //System.out.println(nameList);
        Collections.sort(nameList, new FileComparator());
       // Collections.reverse(nameList);
        System.out.println(nameList);
        System.out.println(nameList.size());
        FileOutputStream fout = new FileOutputStream(destFile);
       // PrintWriter printWriter = new PrintWriter(destFile);
        FileInputStream fis = null;
        byte[] buff;
        for (File file : nameList) {
            fis = new FileInputStream(file);
            buff = new byte[fis.available()];
            fis.read(buff);


            fout.write(buff);
           fout.write("\n".getBytes());


        }
        fis.close();
        fout.close();


    }

    public void reqWalk(File file, ArrayList<File> list) {
        //file.пуе
        File[] files = file.listFiles();
        //тут выполняется рекурсивный бход дериктории ! и запись в список имен файлов.
        for (File filed : files) {
            if (filed.length() <= 50) {//
                if (filed.isDirectory()) {
                    reqWalk(filed, list);
                    // System.out.println(file.getName());
                } else {
                    list.add(filed.getAbsoluteFile());

                }
            }
        }


    }
}


