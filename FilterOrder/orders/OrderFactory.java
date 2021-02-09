package orders;

/**
 * The order factory that create order object who implement the compare method
 */
public class OrderFactory {
    private static final String ABS = "abs", TYPE = "type", SIZE = "size", SEPARATE = "#", REVERSE = "REVERSE";

    /**
     * create the good Order object
     *
     * @param orderLine command line to create the order
     * @return Order Object (Absolute, Type or Size)
     * @throws BadOrderNameException no order type that correspond with the orderLine
     */
    public static Order create(String orderLine) throws BadOrderNameException {
        String[] argParts = orderLine.split(SEPARATE);
        boolean reverse = false;
        if (argParts[argParts.length - 1].equals(REVERSE)) {  // if reversed order
            reverse = true;
        }
        switch (argParts[0]) {
            case ABS:
                return new AbsoluteOrder(reverse);
            case TYPE:
                return new TypeOrder(reverse);
            case SIZE:
                return new SizeOrder(reverse);

            default: // no order correspond
                throw new BadOrderNameException();

        }

    }

    /**
     * create the default order object, who is absolute
     *
     * @return absolute order object
     */
    public static Order createDefault() {
        return new AbsoluteOrder(false);
    }
}
