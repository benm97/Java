package orders;

import java.io.*;

/**
 * Type class extends from Order, that implement the compare method
 * order by file size.
 */
class SizeOrder extends Order {
    private boolean reverse;

    /**
     * constructor for the size order
     *
     * @param reverse if the order is reversed or not
     */
    SizeOrder(boolean reverse) {
        this.reverse = reverse;
    }

    /**
     * This method compare two file by file size, going from smallest to largest
     *
     * @param file1 first file to compare
     * @param file2 second file
     * @return -1 if file1 is the first, 1 if the file2 is the first, 0 is there are equal
     */
    public int compare(File file1, File file2) {
        if (Long.compare(file1.length(), file2.length()) == 0) {
            return new AbsoluteOrder(reverse).compare(file1, file2); // sort by absolute order
        }
        if (reverse) { // from largest to smallest
            return Long.compare(file2.length(), file1.length());
        } else {
            return Long.compare(file1.length(), file2.length());
        }
    }

}
