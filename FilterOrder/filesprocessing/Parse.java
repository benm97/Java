package filesprocessing;

import java.io.*;
import java.util.ArrayList;

import filters.*;
import orders.*;

/**
 * A class to parse command file to a list of sections object
 */
class Parse {

    private static final String ABS = "abs";
    private static final String WARNING_MSG = "Warning in line ";
    private static final String EMPTY_MARKER = "EMPTY";
    private static final String FILTER_MARKER = "FILTER";
    private static final String ORDER_MARKER = "ORDER";
    private static final int FILTER_MARKER_INDEX = 0;
    private static final int ORDER_MARKER_INDEX = 2;
    private static final int FILTER_INDEX = 1;
    private static final int ORDER_INDEX = 3;
    private static final int FILTER_INDEX_FROM_END = 2;
    private static final String NO_WARNING = "NO WARNING";

    /**
     * A method that parse one given block to a section object
     *
     * @param block   block to parse
     * @param endLine line of the end of the block
     * @return corresponding section object
     */
    private static Section parseBlock(String[] block, int endLine) {
        Filter myFilter;
        Order myOrder;
        String[] msgList = {NO_WARNING, NO_WARNING};
        try {
            myFilter = FilterFactory.create(block[FILTER_INDEX]);
        } catch (TypeOneException e) {
            myFilter = FilterFactory.createDefault();
            msgList[0] = WARNING_MSG + (endLine - FILTER_INDEX_FROM_END);
        }

        try {
            myOrder = OrderFactory.create(block[ORDER_INDEX]);
        } catch (BadOrderNameException e) {
            myOrder = OrderFactory.createDefault();
            msgList[1] = WARNING_MSG + (endLine);
        }

        return new Section(myFilter, myOrder, msgList);
    }

    /**
     * A method to parse a command file to alist of section objects
     *
     * @param commandFile File to parse
     * @return list of section objects
     * @throws TypeTwoException throws Io and bad format exceptions
     */
    static ArrayList<Section> parsing(File commandFile) throws TypeTwoException {
        try {
            ArrayList<Section> sectionsList = new ArrayList<Section>();
            BufferedReader reader = new BufferedReader(new FileReader(commandFile));
            String line;
            int lineCounter = 1;
            String[] block = new String[]{EMPTY_MARKER, EMPTY_MARKER, EMPTY_MARKER, EMPTY_MARKER};

            while ((line = reader.readLine()) != null) { //Running on the file
                // Get the block using ==, so we could write EMPTY in the file
                if (block[ORDER_INDEX] == EMPTY_MARKER) {
                    int i = 0;
                    while (!block[i].equals(EMPTY_MARKER)) {
                        i++;
                    }
                    block[i] = line;
                }

                if (!block[ORDER_INDEX].equals(EMPTY_MARKER)) { //If the block is complete
                    //Check exceptions
                    if (!block[FILTER_MARKER_INDEX].equals(FILTER_MARKER) ||
                            !block[ORDER_MARKER_INDEX].equals(ORDER_MARKER)) {
                        throw new BadFormatException();
                    } else {
                        if (block[ORDER_INDEX].equals(FILTER_MARKER)) { //Particular case
                            block[ORDER_INDEX] = ABS;
                            sectionsList.add(parseBlock(block, lineCounter));
                            block = new String[]{FILTER_MARKER, EMPTY_MARKER, EMPTY_MARKER, EMPTY_MARKER};
                        } else {
                            sectionsList.add(parseBlock(block, lineCounter));
                            block = new String[]{EMPTY_MARKER, EMPTY_MARKER, EMPTY_MARKER, EMPTY_MARKER};
                        }

                    }
                }
                lineCounter++;
            }
            if (block[ORDER_MARKER_INDEX].equals(ORDER_MARKER)) { // If there is still something in the block
                block[ORDER_INDEX] = ABS;
                sectionsList.add(parseBlock(block, lineCounter));
            } else if (block[FILTER_MARKER_INDEX] != EMPTY_MARKER) {
                throw new BadFormatException();
            }
            return sectionsList;

        } catch (IOException e) {
            throw new ExceptionIO();
        } catch (BadFormatException e) {
            throw new BadFormatException();
        }
    }
}
