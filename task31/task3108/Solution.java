package com.javarush.task.task31.task3108;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/* 
Исследуем Path
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Path path1 = Paths.get("D:/test/data/firstDir");
        Path path2 = Paths.get("D:/test/data/secondDir/third");
        path1.relativize(path2);
        Path resultPath = getDiffBetweenTwoPaths(path1, path2);
        System.out.println(resultPath);   //expected output '../secondDir/third' or '..\secondDir\third'
    }

    public static Path getDiffBetweenTwoPaths(Path path1, Path path2) {//вопрос как мы реализуем логику метода .ralativize если мы на вход методу передаем ссылку на интерейс\
        //???????????????????


        return path1.relativize(path2);//метод находящий соответствие
    }
}
