package filesprocessing;

import java.io.*;
import java.util.*;

/**
 * A class to run the process
 */
public class DirectoryProcessor {
    private static final int COMMAND_INDEX = 1;
    private static final int DIRECTORY_INDEX = 0;
    private static final int ARGS_NUMBER = 2;
    private static final String NO_WARNING = "NO WARNING";

    /**
     * Check that the command file is actually a file and directory a directory
     *
     * @param commandFile given command file
     * @param directory   given directory
     * @throws WrongArgsException if conditions are not respected
     */
    private static void checkFiles(File commandFile, File directory) throws WrongArgsException {
        if (!commandFile.isFile() || !directory.isDirectory()) {
            throw new WrongArgsException();
        }
    }

    /**
     * Check that the user give exactly 2 arguments
     *
     * @param args args given by the user
     * @throws WrongArgsException if there is more or less than 2
     */
    private static void checkArgs(String[] args) throws WrongArgsException {
        if (args.length != ARGS_NUMBER) {
            throw new WrongArgsException();
        }
    }

    /**
     * Main method to run the program
     *
     * @param args args given by the user
     * @throws TypeTwoException All errors that should stop the program
     */
    public static void main(String[] args) throws TypeTwoException {
        try {
            checkArgs(args);
            File commandFile = new File(args[COMMAND_INDEX]);
            File directory = new File(args[DIRECTORY_INDEX]);
            checkFiles(commandFile, directory);
            // Get the sections object corresponding to the command file
            ArrayList<Section> sectionsList = Parse.parsing(commandFile);
            File[] filesList = directory.listFiles();
            ArrayList<File> resultList;
            for (Section section : sectionsList) {
                for (String msg : section.msgList) {
                    if (!msg.equals(NO_WARNING)) {
                        System.err.println(msg);
                    }
                }
                resultList = section.filter.apply(filesList);
                resultList.sort(section.order);
                // Printing the results for this section
                for (File file : resultList)
                    System.out.println(file.getName());
            }
        } catch (TypeTwoException e) {
            System.err.println(e.getMessage());
        }
    }
}
