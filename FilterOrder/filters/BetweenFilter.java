package filters;

import java.io.File;

/**
 * A class used to represents the between filter
 */
class BetweenFilter extends Filter {

    private double minSize;
    private double maxSize;
    /* tell if the filter is inverted */
    private boolean inverse;
    private static final int TO_BITS = 1024;

    /**
     * Constructor
     *
     * @param minSize minimal size in k-bytes
     * @param maxSize maximal size in k-bytes
     * @param inverse true iff the filter is inverted
     */
    BetweenFilter(double minSize, double maxSize, boolean inverse) {
        this.minSize = minSize * TO_BITS;
        this.maxSize = maxSize * TO_BITS;
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
            return (file.length() < minSize || file.length() > maxSize);
        } else {
            return (file.length() >= minSize && file.length() <= maxSize);
        }

    }
}
