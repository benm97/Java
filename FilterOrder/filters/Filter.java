package filters;

import java.io.*;

import java.util.ArrayList;

/**
 * An abstract class to represents filter object, with the method to apply the filter
 */
public abstract class Filter {
    /**
     * The abstract method used by the apply method to check if a file respects the filter
     *
     * @param file file to check
     * @return true iff the file respect it
     */
    abstract boolean filterCheck(File file);

    /**
     * The method apply a filter to a list and return a filtered array list
     *
     * @param filesList files to filtrate
     * @return an array list of the filtered files
     */
    public ArrayList<File> apply(File[] filesList) {
        ArrayList<File> results = new ArrayList<File>();
        for (File file : filesList) {
            if (filterCheck(file) && file.isFile()) {
                results.add(file);
            }
        }
        return results;
    }
}
