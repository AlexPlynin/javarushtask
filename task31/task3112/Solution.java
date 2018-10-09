package com.javarush.task.task31.task3112;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/* 
Загрузчик файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://javarush.ru/testdata/secretPasswords.txt", Paths.get("C:\\Users\\hooty\\Desktop\\Jrush\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task31\\task3112\\DowDir"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        // implement this method

        URL url = new URL(urlString);
        Path tempFile = Files.createTempFile("-temp2",".tmp");
        InputStream inputStream = url.openStream();

        Files.copy(inputStream,tempFile,StandardCopyOption.REPLACE_EXISTING);

        Path destFile = Paths.get(url.getFile()).getFileName();
        Path filePath = Paths.get(downloadDirectory.toString()+"/"+destFile.toString());
       // File file = new File(downloadDirectory+"\\"+destFile.getFileName());
        //Path destPath = Paths.get(url.getFile())
        inputStream.close();
        return Files.move(tempFile,filePath);
//return null;
    }
}
