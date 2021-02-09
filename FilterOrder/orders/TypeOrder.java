package orders;

import java.io.*;

/**
 * Type class extends from Order, that implement the compare method
 * order by file type.
 */
class TypeOrder extends Order {
    private boolean reverse;

    /**
     * constructor for the size order
     *
     * @param reverse if the order is reversed or not
     */
    TypeOrder(boolean reverse) {
        this.reverse = reverse;
    }

    /**
     * find the type of the file
     *
     * @param name name file
     * @return a string of the type
     */
    private String getSuffix(String name) {
        String[] nameParts = name.split("\\.");
        if (nameParts.length == 1 || (nameParts[0].equals("") && nameParts.length == 2)) {
            return "";
        } else {
            return nameParts[nameParts.length - 1];
        }

    }

    /**
     * This method compare two file by file type, going from ‘a’ to ‘z’
     *
     * @param file1 first file to compare
     * @param file2 second file
     * @return -1 if file1 is the first, 1 if the file2 is the first, or if there are equal compare with the absolute
     * order
     */
    public int compare(File file1, File file2) {

        String suffix1 = getSuffix(file1.getName());

        String suffix2 = getSuffix(file2.getName());
        if (suffix1.compareTo(suffix2) == 0) {
            return new AbsoluteOrder(reverse).compare(file1, file2);
        }
        if (reverse) {
            return Integer.signum(suffix2.compareTo(suffix1));
        } else {
            return Integer.signum(suffix1.compareTo(suffix2));
        }
    }

}
