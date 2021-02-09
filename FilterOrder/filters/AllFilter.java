package filters;

import java.io.*;

/**
 * A class used to represents the all filter
 */
class AllFilter extends Filter {
    /* tell if the filter is inverted */
    private boolean inverse;

    /**
     * Constructor
     *
     * @param inverse true iff the filter is inverted
     */
    AllFilter(boolean inverse) {
        this.inverse = inverse;
    }

    /**
     * The method used by the apply method to check if a file respects the filter
     *
     * @param file file to check
     * @return true iff the file respect it
     */
    boolean filterCheck(File file) {
        return !inverse;

    }
}
