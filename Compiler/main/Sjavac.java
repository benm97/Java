package oop.ex6.main;

import oop.ex6.method.Method;
import oop.ex6.method.MethodException;
import oop.ex6.method.ParseMethod;
import oop.ex6.structure.ParseStructure;
import oop.ex6.structure.StructureException;
import oop.ex6.variable.VariableException;

import java.io.File;
import java.io.IOException;

/**
 * This is the main class of our program, our manager
 */
public class Sjavac {

    /**
     * No file founded, no arguments given
     */
    private static final String NOFILE = "Missing argument";

    /**
     * Numbers to print
     */
    private static final int LEGAL = 0, ILLEGAL = 1, ERROR = 2;

    /**
     * The main ethod of our program calling every need class
     *
     * @param args - the file we should parse
     */
    public static void main(String[] args) {

        if (args.length == 0) {
            System.err.println(NOFILE);
            return;
        }
        try {

            File fileToCheck = new File(args[0]);
            ParseStructure.parsing(fileToCheck); //Decomposing the file into methods and variables
            for (Method method : ParseStructure.methodsList) { //Parsing every method
                ParseMethod.parsing(method);
            }
            System.out.println(LEGAL); //No pb was found in the file

        } catch (IOException e) { //Can't read the given file
            System.err.println(e.getMessage());
            System.out.println(ERROR);
        } catch (VariableException e) {
            System.err.println(e.getMessage());
            System.out.println(ILLEGAL);
        } catch (MethodException e) {
            System.err.println(e.getMessage());
            System.out.println(ILLEGAL);
        } catch (StructureException e) {
            System.err.println(e.getMessage());
            System.out.println(ILLEGAL);
        }
    }
}

