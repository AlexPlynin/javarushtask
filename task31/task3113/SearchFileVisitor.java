package com.javarush.task.task31.task3113;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    //private String partOfName, partOfContent;
    private int countOfFolder, countOfFiles, countOfBite;
//    private List<Path> foundFiles = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {// при заходе в файл выполняется
//        byte[] content = Files.readAllBytes(file); // размер файла: content.length
//        String contentString = new String(content);

        if (attrs.isDirectory()) {
            countOfFolder++;//сбда не зайдет, так как данный метод используется только для захода в файл
        } else countOfFiles++;

        countOfBite += attrs.size();
//необх


        return FileVisitResult.CONTINUE;// продолжение поиска
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {//перед просмотром директории выполняется
        countOfFolder++;

        return FileVisitResult.CONTINUE;
    }

    @Override
    public String toString() {
        return "Всего папок - "+(countOfFolder-1)+"\n"
                +"Всего файлов - "+countOfFiles+"\n"
                +"Общий размер - "+countOfBite;


    }
}
