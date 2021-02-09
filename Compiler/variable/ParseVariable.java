package oop.ex6.variable;

import oop.ex6.structure.ParseStructure;

import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is checking the legalness of a varible
 */
public class ParseVariable {

    /**
     * Default string and regexs
     */
    private static final String DEFAULTSTR = "\"default-string\"", FINAL = "final\\s+",
            NAME = "[A-Za-z]+", VIRSPLIT = ",", ANYSTR = "\".*\"", VARS = "\\s*,\\s*",
            SPACE = "\\s*", EQUAL = "=", EQUALSPLIT = "\\s*=\\s*";

    /**
     * This method is parsing a variable
     *
     * @param line      - line of the variable declaration
     * @param variables - variables already existing
     * @param isMethod  - if the declaration is in a method
     * @return ArrayList<Variable> - all the variables once they are creating
     * @throws VariableException - in need
     */
    public static ArrayList<Variable> declaration(String line, Stack<Variable> variables, boolean isMethod)
            throws VariableException {
        ArrayList<Variable> variablesList = new ArrayList<>();
        boolean isFinal = false;
        String type;
        line = line.trim();
        if (line.endsWith(";")) {
            line = line.substring(0, line.length() - 1);
        }
        Pattern pattern = Pattern.compile(FINAL);
        Matcher matcher = pattern.matcher(line);
        if (matcher.lookingAt()) { //For a final variable
            isFinal = true;
            line = line.substring(matcher.end());

        }
        pattern = Pattern.compile(NAME);
        matcher = pattern.matcher(line);
        if (matcher.find() && !line.endsWith(VIRSPLIT)) {
            type = line.substring(matcher.start(), matcher.end()); //Getting the type of the variable
            line = line.substring(matcher.end());
        } else {
            throw new VariableException();
        }
        line = line.replaceAll(ANYSTR, DEFAULTSTR);
        line = line.trim();
        String[] lineArray = line.split(VARS); //Getting all the possible variables in the same line
        for (String declaration : lineArray) {
            if (declaration.matches(SPACE)) {
                throw new VariableException();
            }
            if (declaration.contains(EQUAL)) { //If there is also an assignement
                String[] declarationArray = declaration.split(EQUALSPLIT);
                if (declarationArray.length != 2) {
                    throw new VariableException();
                }
                if (!Variable.checkType(type, declarationArray[1].trim())) {
                    declarationArray[1] = getVariable(variables, declarationArray[1].trim()).getValue();
                }
                variablesList.add(new Variable(type, declarationArray[0].trim(), declarationArray[1].trim(), isFinal));
            } else {

                variablesList.add(new Variable(type, declaration.trim(), isFinal, isMethod)); //Creating new variable
            }
        }
        return variablesList; //All the variable that were creating from that line
    }

    /**
     * this method is checking the legalness of a variable assignaton
     *
     * @param assignLine - the line with the assignation
     * @param variables  - variables already existing
     * @param counters   - counters of variables in each block if we are in a method
     * @throws VariableException exception
     */
    public static void assignation(String assignLine, Stack<Variable> variables, Stack<Integer> counters)
            throws VariableException {

        String line = assignLine.trim();
        line = line.substring(0, line.length() - 1).trim();
        String[] assignArray = line.split(EQUALSPLIT);
        if (assignArray.length != 2) { //Missing a value
            throw new VariableException();
        }
        Variable myVariable = ParseVariable.getVariable(variables, assignArray[0].trim()); //Getting the variable
        if (myVariable.getFinal()) { //check if its final or not
            throw new VariableException();  //cant assign a final variable
        }
        if (!Variable.checkType(myVariable.getType(), assignArray[1].trim())) { //Check the type
            assignArray[1] = ParseVariable.getVariable(variables, assignArray[1]).getValue();
            if (!Variable.checkType(myVariable.getType(), assignArray[1].trim())) {
                throw new VariableException();
            }
        }
        if (ParseStructure.globalVariables.contains(myVariable) && !counters.empty()) {
            variables.push(new Variable(myVariable.getType(), myVariable.getName(), //Update the ariables stack
                    myVariable.getValue(), myVariable.getFinal()));
            counters.push(counters.pop() + 1); //Update the counters stack if in a method
        } else {
            myVariable.setValue(myVariable.getType());
        }
    }

    /**
     * This method returns an already existing variable
     *
     * @param variables variables stack to search the variable
     * @param name      name of the variable
     * @return the founded variable
     * @throws VariableException if not found
     */
    public static Variable getVariable(Stack<Variable> variables, String name) throws VariableException {
        int index;
        for (int i = variables.size() - 1; i >= 0; i--) { //in the method
            if (variables.get(i).getName().equals(name)) {
                index = i;
                return variables.get(index);
            }
        }
        for (Variable variable : ParseStructure.globalVariables) { //in the globals
            if (variable.getName().equals(name)) {
                return variable;
            }
        }
        throw new VariableException(); //Not existing vraiable
    }

}
