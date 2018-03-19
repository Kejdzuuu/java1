package com.jetbrains.java1;

import com.jetbrains.java1.DiskDirectory;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        String path = "C:\\Users\\admin\\Desktop\\test";
        File file = new File(path);
        File[] array = file.listFiles();
        DiskDirectory dir = new DiskDirectory(path, 0);

        dir.print_all(dir.depth, 1);
    }
}
