package filters;

import java.io.*;

/**
 * A class used to represents the greater than filter
 */
class GreaterThanFilter extends Filter {
    /* tell if the filter is inverted */
    private double minSize;
    private boolean inverse;
    private static final int TO_BITS = 1024;

    /**
     * Constructor
     *
     * @param minSize minimal size in k-bytes
     * @param inverse true iff the filter is inverted
     */
    GreaterThanFilter(double minSize, boolean inverse) {
        this.minSize = minSize * TO_BITS;
        this.inverse = inverse;
    }

    /**
     * The method used by the apply method to check if a file respects the filter
     *
     * @param file file to check
     * @return true iff the file respect it
     */
    boolean filterCheck(File file) {
        if (inverse) {
            return file.length() <= minSize;
        } else {
            return (file.length() > minSize);
        }

    }
}
