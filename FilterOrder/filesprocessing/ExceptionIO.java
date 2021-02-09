package filesprocessing;

/**
 * A class for IO exceptions
 */
public class ExceptionIO extends TypeTwoException {

    public String getMessage() {
        return "ERROR: IO error.";
    }
}
