package com.jetbrains.java1;

import com.jetbrains.java1.DiskElement;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DiskFile extends DiskElement {

    public DiskFile(String path, int depth){
        this.id = "P";
        this.depth = depth;
        file = new File(path);
        this.last_modified = file.lastModified();
        File[] files = file.listFiles();
        this.path = path.substring(this.file.getParent().length());
        //this.print(this.depth);
    }

    @Override
    public void print_all(int depth, int mode){
        this.print(depth);
    }

    @Override
    protected void print(int depth) {
        Date date = new Date(file.lastModified());
        SimpleDateFormat template = new SimpleDateFormat("yyy-MM-dd");
        String formattedDate = template.format(date);
        String name = "";
        for(int i = 0; i < this.depth; i++){
            name += "-";
        }
        String path = this.file.getPath();
        path = path.substring(this.file.getParent().length() + 1);
        name += path;
        if(name.length() > 30){
            name = name.substring(0, 30);
            name += "...";
        }
        //int this_length = path.length() + this.depth;
        //if(this_length > max_length) max_length = this_length;
        System.out.println(String.format("%-35s P  %s" , name, formattedDate));

    }
}
