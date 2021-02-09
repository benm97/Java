package filters;

import java.io.File;

/**
 * A class used to represents the suffix filter
 */
class SuffixFilter extends Filter {
    /* tell if the filter is inverted */
    private boolean inverse;
    private String value;

    /**
     * Constructor
     *
     * @param value   the suffix we want the file to have
     * @param inverse true iff the filter is inverted
     */
    SuffixFilter(String value, boolean inverse) {
        this.value = value;
        this.inverse = inverse;
    }

    /**
     * The method used by the apply method to check if a file respects the filter
     *
     * @param file file to check
     * @return true iff the file respect it
     */
    boolean filterCheck(File file) {
        if (inverse)
            return !file.getName().endsWith(value);
        else
            return file.getName().endsWith(value);

    }
}
