package com.jetbrains.java1;

import java.io.File;
import java.util.Comparator;

public abstract class DiskElement implements Comparable<DiskElement> {
    protected File file;
    protected int depth=1;
    protected String id;
    protected String path;
    protected long last_modified;
    //static int max_length = 0;

    protected abstract void print(int depth);

    public void print() { print(0); }

    public abstract void print_all(int depth, int mode);

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

    protected class DiskComparator implements Comparator<DiskElement> {
        @Override
        public int compare(DiskElement element1, DiskElement element2) {
            long compare = element1.last_modified - element2.last_modified;
            if (compare == 0) {
                return element1.compareTo(element2);
            }
            if (compare > 0) {
                return -1;
            }
            return 1;
        }
    }

}

