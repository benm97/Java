package orders;

import java.io.*;

/**
 * Absolute class extends from Order, that implement the compare method
 * order by absolute name.
 */
class AbsoluteOrder extends Order {
    private boolean reverse;

    /**
     * Constructor for the absolute order
     *
     * @param reverse if the order is reversed or not
     */
    AbsoluteOrder(boolean reverse) {
        this.reverse = reverse;
    }

    /**
     * This method compare two file by absolute name going from ‘a’ to ‘z’
     *
     * @param file1 first file to compare
     * @param file2 second file
     * @return -1 if file1 is the first, 1 if the file2 is the first, 0 is there are equal
     */
    public int compare(File file1, File file2) {
        if (reverse) {
            return Integer.signum(file2.getAbsolutePath().compareTo(file1.getAbsolutePath()));
        } else {
            return Integer.signum(file1.getAbsolutePath().compareTo(file2.getAbsolutePath()));
        }
    }

}
