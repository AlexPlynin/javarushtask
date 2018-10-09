package com.javarush.task.task31.task3101;

import java.io.File;
import java.util.Comparator;

public class FileComparator implements Comparator<File> {
    @Override
    public int compare(File o1, File o2) {


//        if (o1.getName().compareTo(o2.getName()) == 1) return 1;
//        else if (o1.getName().compareTo(o2.getName()) == -1) return -1;
//        else return 0;

        return o1.getName().compareTo(o2.getName());

    }


}
