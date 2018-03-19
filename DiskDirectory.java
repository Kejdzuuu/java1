package com.jetbrains.java1;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

public class DiskDirectory extends DiskElement {
    Set<DiskElement> children = new TreeSet<>();
    List<DiskElement> elements = new ArrayList<DiskElement>();

    public DiskDirectory(String path, int depth){
        this.id = "K";
        this.depth = depth;

        file = new File(path);
        this.last_modified = file.lastModified();
        File[] files = file.listFiles();
        this.path = path.substring(this.file.getParent().length());

        //this.print(this.depth);
        for(int i = 0; i < files.length; i++){
            if(files[i].isDirectory()) {
                DiskDirectory child = new DiskDirectory(files[i].getAbsolutePath(), this.depth + 1);
                children.add(child);
                elements.add(child);
            }
            if(files[i].isFile()){
                DiskFile child = new DiskFile(files[i].getAbsolutePath(), this.depth + 1);
                children.add(child);
                elements.add(child);
            }
        }
        Collections.sort(elements, new DiskComparator());

    }

    @Override
    public void print_all(int depth, int mode){
        this.print(this.depth);
        if(mode == 0) {
            for (DiskElement item : this.children) {
                item.print_all(item.depth, mode);
            }
        }
        else{
            for (DiskElement item : this.elements) {
                item.print_all(item.depth, mode);
            }
        }
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
        if(this.depth == 1) path = path.substring(this.file.getParent().length());
        else path = path.substring(this.file.getParent().length() + 1);
        name += path;
        if(name.length() > 30){
            name = name.substring(0, 30);
            name += "...";
        }
        //int this_length = path.length() + this.depth;
        //if(this_length > max_length) max_length = this_length;
        System.out.println(String.format("%-35s K  %s" , name, formattedDate));

    }
}
