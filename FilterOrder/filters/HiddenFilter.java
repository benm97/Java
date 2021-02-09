package filters;

import java.io.File;

/**
 * A class used to represents the hidden filter
 */
class HiddenFilter extends Filter {
    /* tell if the filter is inverted */
    private boolean inverse;
    private String value;
    private static final String NO = "NO";

    /**
     * Constructor
     *
     * @param value   YES if we want hidden files, NO if if we not hidden
     * @param inverse true iff the filter is inverted
     */
    HiddenFilter(String value, boolean inverse) {
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

        if ((inverse && value.equals(NO)) || (!inverse && !value.equals(NO))) {
            return file.isHidden();
        } else {
            return !file.isHidden();
        }
    }
}
