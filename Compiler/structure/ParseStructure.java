package oop.ex6.structure;


import oop.ex6.Patterns;
import oop.ex6.method.Method;
import oop.ex6.method.MethodException;
import oop.ex6.variable.ParseVariable;
import oop.ex6.variable.Variable;
import oop.ex6.variable.VariableException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

/**
 * This class is parsing all the file and checks the structure
 */
public class ParseStructure {

    /**
     * The global variables of the file
     */
    public static ArrayList<Variable> globalVariables;

    /**
     * The methods list of the file
     */
    public static ArrayList<Method> methodsList;

    /**
     * Regexs
     */
    private static final String OPENBLOCK = ".*\\{\\s*", CLOSEBLOCK = "\\s*\\}\\s*";

    /**
     * This method is parsinf the file and decomposing it in methods or variables
     *
     * @param file - the file
     * @throws IOException        exception
     * @throws VariableException  exception
     * @throws MethodException    exception
     * @throws StructureException exception
     */
    public static void parsing(File file) throws IOException, VariableException, MethodException, StructureException {

        BufferedReader reader = new BufferedReader(new FileReader(file));
        ArrayList<String> fileArray = new ArrayList<>();
        globalVariables = new ArrayList<>();
        methodsList = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            fileArray.add(line); //stocking the file in an array
        }

        int counter = 0;
        while (counter < fileArray.size()) { //Parsing line by line
            String myLine = fileArray.get(counter);
            if (Patterns.isVariableDeclaration(myLine)) {
                globalVarDeclaration(myLine);
            } else if (Patterns.isMethodDeclaration(myLine)) {
                int bracesCount = 0;
                int methodStart = counter;
                while (counter < fileArray.size()) { //Stocking a whole full method to create it
                    if (fileArray.get(counter).matches(OPENBLOCK)) { //opening block
                        bracesCount++;
                    } else if (fileArray.get(counter).matches(CLOSEBLOCK)) { //closing block
                        bracesCount--;
                    }
                    if (bracesCount == 0) {
                        break;
                    }
                    counter++;
                }

                if (bracesCount != 0) {
                    throw new StructureException();
                }
                methodsList.add(new Method(fileArray.subList(methodStart, counter + 1)));
            } else if (Patterns.isVariableAssignation(myLine)) { //variable assignment
                ParseVariable.assignation(myLine.trim(), new Stack<Variable>(), new Stack<Integer>());
            } else if (!Patterns.isComment(myLine) && !Patterns.isBlank(myLine)) {
                throw new StructureException();
            }
            counter++;
        }
    }

    /**
     * Parsing a global variable declaration
     *
     * @param line - line of the variable declaration
     * @throws StructureException exception
     * @throws VariableException  exception
     */
    private static void globalVarDeclaration(String line) throws StructureException, VariableException {
        ArrayList<Variable> lineVariables = ParseVariable.declaration(
                line.trim(), new Stack<Variable>(), false);
        for (Variable variable : lineVariables) {
            for (Variable globalVariable : globalVariables) {
                if (globalVariable.getName().equals(variable.getName())) { //If already existing
                    throw new StructureException();
                }
            }
            globalVariables.add(variable);
        }
    }
}
