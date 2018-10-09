package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private String partOfName, partOfContent;
    private int minSize, maxSize;
    private List<Path> foundFiles = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        byte[] content = Files.readAllBytes(file); // размер файла: content.length
        String contentString = new String(content);
        boolean flag = true;
//необходимо выполнть все условия
        if (partOfName != null)
            flag &= file.getFileName().toString().contains(partOfName);//накопление флага
        if (partOfContent != null)
            flag &= contentString.contains(partOfContent);
        if (minSize != 0)
            flag &= content.length > minSize;
        if (maxSize != 0)
            flag &= content.length < maxSize;
        if (flag)
            foundFiles.add(file);


        return FileVisitResult.CONTINUE;// продолжение поиска
    }


    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public List<Path> getFoundFiles() {
        return foundFiles;
    }
}
