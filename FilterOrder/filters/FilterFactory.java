package filters;

import filesprocessing.TypeOneException;

/**
 * A class to create the wanted filter objects
 */
public class FilterFactory {
    private static final String GREATER = "greater_than", BETWEEN = "between", SMALLER = "smaller_than",
            FILE = "file", CONTAINS = "contains", PREFIX = "prefix", SUFFIX = "suffix", WRITABLE = "writable",
            EXECUTABLE = "executable", HIDDEN = "hidden", ALL = "all",
            NOT = "NOT", SEPARATE = "#", YES = "YES", NO = "NO";

    /**
     * Check that the parameters is positive
     *
     * @param number number param
     * @throws NegativeParametersException the exception for negative parameters
     */
    private static void checkPositive(Double number) throws NegativeParametersException {
        if (number < 0) {
            throw new NegativeParametersException();
        }
    }

    /**
     * Check that the permission arg is correct
     *
     * @param value permission arg
     * @throws BadPermissionArgException the exception for bad permission arg
     */
    private static void checkPermissionValue(String value) throws BadPermissionArgException {
        if (!value.equals(YES) && !value.equals(NO)) {
            throw new BadPermissionArgException();
        }
    }

    /**
     * Check that the given bounds are corrects
     *
     * @param min min bound
     * @param max max bound
     * @throws BoundsException the exception for incorrect bounds
     */
    private static void checkBounds(Double min, Double max) throws BoundsException {
        if (min > max) {
            throw new BoundsException();
        }
    }

    /**
     * The method used to create the wanted filter, from the given filter name
     *
     * @param filterLine given (supposed) filter name
     * @return filter object
     * @throws TypeOneException exceptions in the filter name or parameters
     */
    public static Filter create(String filterLine) throws TypeOneException {
        String[] argParts = filterLine.split(SEPARATE);
        boolean inverse = false;

        if (argParts[argParts.length - 1].equals(NOT)) {
            inverse = true;
        }
        switch (argParts[0]) {
            case GREATER:
                checkPositive(Double.parseDouble(argParts[1]));
                return new GreaterThanFilter(Double.parseDouble(argParts[1]), inverse);
            case BETWEEN:
                //Check exceptions
                checkPositive(Double.parseDouble(argParts[1]));
                checkPositive(Double.parseDouble(argParts[2]));
                checkBounds(Double.parseDouble(argParts[1]), Double.parseDouble(argParts[2]));
                return new BetweenFilter(Double.parseDouble(argParts[1]), Double.parseDouble(argParts[2]), inverse);
            case SMALLER:
                //Check exceptions
                checkPositive(Double.parseDouble(argParts[1]));
                return new SmallerThanFiilter(Double.parseDouble(argParts[1]), inverse);
            case FILE:
                return new FileFilter(argParts[1], inverse);
            case CONTAINS:
                return new ContainsFilter(argParts[1], inverse);
            case PREFIX:
                return new PrefixFilter(argParts[1], inverse);
            case SUFFIX:
                return new SuffixFilter(argParts[1], inverse);
            case WRITABLE:
                //Check exceptions
                checkPermissionValue(argParts[1]);
                return new WritableFilter(argParts[1], inverse);
            case EXECUTABLE:
                //Check exceptions
                checkPermissionValue(argParts[1]);
                return new ExecutableFilter(argParts[1], inverse);
            case HIDDEN:
                //Check exceptions
                checkPermissionValue(argParts[1]);
                return new HiddenFilter(argParts[1], inverse);
            case ALL:
                return new AllFilter(inverse);
            default:
                throw new BadFilterNameException();

        }

    }

    /**
     * The method that create the default filter, all filter
     *
     * @return AllFilter object
     */
    public static Filter createDefault() {
        return new AllFilter(false);
    }
}
