package filters;

import java.io.File;

class SmallerThanFiilter extends Filter {

    private double maxSize;
    private boolean inverse;
    private static final int TO_BITS = 1024;

    SmallerThanFiilter(double maxSize, boolean inverse) {
        this.maxSize = maxSize * TO_BITS;
        this.inverse = inverse;
    }

    boolean filterCheck(File file) {
        if (inverse) {
            return file.length() >= maxSize;
        } else {
            return (file.length() < maxSize);
        }

    }
}
