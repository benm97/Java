package filesprocessing;

/**
 * A class to represents type two exceptions, that stops the program
 */
public abstract class TypeTwoException extends Exception {
    protected static final long serialVersionUID = 1L;

    public abstract String getMessage();
}
