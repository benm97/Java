package orders;

import java.io.*;
import java.util.*;

/**
 * Abstract class implements from Comparator that implement the compare method.
 */
public abstract class Order implements Comparator<File> {
    /**
     * Abstract method that compare two file
     *
     * @param file1 first file to compare
     * @param file2 second file
     * @return -1 if file1 is the first, 1 if the file2 is the first, 0 is there are equal
     */
    public abstract int compare(File file1, File file2);
}
