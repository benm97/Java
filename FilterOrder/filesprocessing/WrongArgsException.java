package filesprocessing;

/**
 * A class for exception in the arguments
 */
public class WrongArgsException extends TypeTwoException {
    private static final String WRONG_ARGS_MSG = "ERROR: Wrong arguments. Should be: sourcedir commandfile";

    @Override
    public String getMessage() {
        return WRONG_ARGS_MSG;
    }
}
