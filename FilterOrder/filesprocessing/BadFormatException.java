package filesprocessing;

/**
 * A class for bad format exceptions (of the command file)
 */
public class BadFormatException extends TypeTwoException {
    private static final String BAD_FORMAT_MSG = "ERROR: Bad command format";

    /**
     * get the error message
     *
     * @return error message
     */
    public String getMessage() {
        return BAD_FORMAT_MSG;
    }
}
