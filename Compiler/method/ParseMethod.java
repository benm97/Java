package oop.ex6.method;

import oop.ex6.Patterns;
import oop.ex6.structure.ParseStructure;
import oop.ex6.structure.StructureException;
import oop.ex6.variable.ParseVariable;
import oop.ex6.variable.Variable;
import oop.ex6.variable.VariableException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This ParseMethod Class is a class parsing all line contains in the method cod of one method
 */
public class ParseMethod {

    /**
     * All types
     */
    private static final String intType = "int", doubleType = "double", charType = "char",
            strType = "String", boolType = "boolean";

    /**
     * Regexs
     */
    private static final String ENDBLOCK = "\\s*\\}\\s*", PARAM = "\\([^();]+\\)", ANDCOND = ".*&\\s*&.*",
            ORCOND = "\\s*\\|\\s*\\|\\s*", ANDSPLIT = "\\s*&\\s*&\\s*", NAMEMETHOD = "[a-zA-Z]\\w*",
            PARAMSMETH = "\\s*,\\s*", SPACE = "\\s*";

    /**
     * This method is check=cking every line of the Method
     *
     * @param method - a method object
     * @throws StructureException - in case of a structure pb
     * @throws VariableException  - in case of a variable pb
     * @throws MethodException    - in case of an illegal line in the method code
     */
    public static void parsing(Method method) throws StructureException, VariableException, MethodException {
        int counter = 1; //starting after the name method (already checked)
        Stack<Variable> variables = new Stack<>();
        Stack<Integer> counters = new Stack<>();
        counters.push(method.getParameters().size()); //for the variable of the parameters
        variables.addAll(method.getParameters());
        String line;
        boolean flagReturn = false; //Check the last code line of the method was an empty return
        while (counter < method.getMethodCode().size()) {
            line = method.getMethodCode().get(counter);
            if (Patterns.isBlockStart(line)) {
                flagReturn = false;
                checkBlockStart(line, variables);  //starting a new counter for a new block
                counters.push(0);
            } else if (Patterns.isVariableDeclaration(line)) {
                flagReturn = false;
                checkVarDeclaration(line, variables, counters);
            } else if (Patterns.isVariableAssignation(line)) {

                flagReturn = false;
                ParseVariable.assignation(line, variables, counters);

            } else if (Patterns.isMethodCall(line)) {  //Check a legal method call
                flagReturn = false;
                checkMethodCall(line, variables);

            } else if (Patterns.isReturn(line)) {
                flagReturn = true;
            } else if (line.matches(ENDBLOCK)) {
                if (!flagReturn && counter == method.getMethodCode().size() - 1) {
                    throw new MethodException();
                }
                flagReturn = false;
                int blockCount = counters.pop();
                for (int i = 0; i < blockCount; i++) {
                    variables.pop();
                }
            } else if (!Patterns.isReturn(line) && !Patterns.isBlank(line) && !Patterns.isComment(line)) {
                throw new MethodException();
            }
            counter++;
        }
    }

    /**
     * This method is checking for a block start (with legal conditions)
     *
     * @param line      - line of the block start
     * @param variables - variable already existing
     * @throws StructureException exception
     * @throws VariableException  exception
     */
    private static void checkBlockStart(String line, Stack<Variable> variables)
            throws StructureException, VariableException {
        Pattern pattern = Pattern.compile(PARAM);
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            checkConditions(line.substring(matcher.start() + 1, matcher.end() - 1), variables);
            //check if the condition is legal
        } else {
            throw new StructureException();
        }
    }

    /**
     * Check if the variable declaration is legal
     *
     * @param line      - line of the variable declaration
     * @param variables - variable already existing
     * @param counters  - counters of the variable number in a block
     * @throws VariableException - in need
     */
    private static void checkVarDeclaration(String line, Stack<Variable> variables, Stack<Integer> counters)
            throws VariableException {
        ArrayList<Variable> toDeclare = ParseVariable.declaration(line, variables, false);
        for (Variable variable : toDeclare) {
            int index = -1;
            for (int i = variables.size() - 1; i >= 0; i--) {
                if (variables.get(i).getName().equals(variable.getName())) { //already existing
                    index = i;
                    break;
                }
            }
            if (index != -1 && index <= counters.peek()) {
                throw new VariableException();
            }
            variables.push(variable);
            counters.push(counters.pop() + 1);
        }
    }

    /**
     * Check the legalness of the conditions
     *
     * @param condition exception
     * @param variables exception
     * @throws VariableException exception
     */
    private static void checkConditions(String condition, Stack<Variable> variables) throws VariableException {
        ArrayList<String> conditions = new ArrayList<>();
        String[] conditionsArray = condition.split(ORCOND);
        for (String myCondition : conditionsArray) {
            if (myCondition.matches(ANDCOND)) {
                conditions.addAll(Arrays.asList(myCondition.split(ANDSPLIT)));
            } else {
                conditions.add(myCondition);  //Separating al the possible conditions
            }
        }
        for (String possibleCond : conditions) { //Check every found condition
            possibleCond = possibleCond.trim();
            boolean validType = Variable.checkType(intType, possibleCond) ||//check the type of the condition
                    Variable.checkType(doubleType, possibleCond) ||
                    Variable.checkType(boolType, possibleCond);
            if (!validType) {
                Variable myVariable = ParseVariable.getVariable(variables, possibleCond);
                if (myVariable.getType().equals(charType) || myVariable.getType().equals(strType) ||
                        myVariable.getValue() == Variable.getDefaultValue()) { //Check that the value is existing
                    throw new VariableException();
                }
            }
        }
    }

    /**
     * Check a method call
     *
     * @param line      - line of the method call
     * @param variables - variable already existing
     * @throws VariableException - in need
     * @throws MethodException   - in need
     */
    private static void checkMethodCall(String line, Stack<Variable> variables)
            throws VariableException, MethodException {
        String name;
        line = line.trim();
        line = line.substring(0, line.length() - 1);
        Pattern pattern = Pattern.compile(NAMEMETHOD);
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            name = line.substring(matcher.start(), matcher.end()); //Getting the method name
            line = line.substring(matcher.end());
        } else {
            throw new MethodException();
        }
        Method calledMethod = null;
        for (Method method : ParseStructure.methodsList) {
            if (method.getName().equals(name)) { //Check if the method called exists
                calledMethod = method;
                break;
            }
        }
        line = line.trim();
        line = line.substring(1, line.length() - 1);
        String[] lineArray = line.split(PARAMSMETH); //For the parameters list
        if (lineArray[0].matches(SPACE) && lineArray.length == 1) {
            lineArray = new String[0];
        }
        if (calledMethod == null || lineArray.length != calledMethod.getParameters().size()) { //Pb of parameters
            throw new MethodException();                     //or not existing method
        }
        for (int i = 0; i < lineArray.length; i++) {
            boolean correctType;
            correctType = Variable.checkType(calledMethod.getParameters().get(i).getType(), lineArray[i].trim());
            if (!correctType) { //checking th egiven parameters during the method call
                Variable myVariable = ParseVariable.getVariable(variables, lineArray[i].trim());
                if (!myVariable.getType().equals(calledMethod.getParameters().get(i).getType())
                        || myVariable.getValue() == Variable.getDefaultValue()) {
                    throw new VariableException();
                }

            }

        }
    }


}
