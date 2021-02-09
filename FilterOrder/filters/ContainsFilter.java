package filters;

import java.io.File;

/**
 * A class used to represents the contains filter
 */
class

ContainsFilter extends Filter {
    /* tell if the filter is inverted */
    private boolean inverse;
    private String value;

    /**
     * Constructor
     *
     * @param value
     * @param inverse true iff the filter is inverted
     */
    ContainsFilter(String value, boolean inverse) {
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
            return !file.getName().contains(value);
        else
            return file.getName().contains(value);

    }
}
