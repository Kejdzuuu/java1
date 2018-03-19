package com.jetbrains.java1;

import java.io.File;

public abstract class DiskElement implements Comparable<DiskElement>{
    protected File file;
    protected int depth=1;
    protected String id;
    protected String path;
    //static int max_length = 0;

    protected abstract void print(int depth);

    public void print() { print(0); }

    public abstract void print_all(int depth);

    @Override
    public int compareTo(DiskElement element){
        int compare = Integer.compare(this.depth, element.depth);

        if(compare == 0) {
            compare = id.compareTo(element.id);
            if (compare == 0) {
                return path.compareTo(element.path);
            } else return compare;
        } else return compare;
    }
}
