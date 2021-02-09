package filters;

import java.io.File;

/**
 * A class used to represents the writable filter
 */
class WritableFilter extends Filter {
    private static final String NO = "NO";
    /* tell if the filter is inverted */
    private boolean inverse;
    private String value;


    /**
     * Constructor
     *
     * @param value   YES if we want writable files, NO for the others
     * @param inverse true iff the filter is inverted
     */
    WritableFilter(String value, boolean inverse) {
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
            return file.canWrite();
        } else {
            return !file.canWrite();
        }
    }
}