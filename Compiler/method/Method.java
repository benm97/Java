package oop.ex6.method;

import oop.ex6.Patterns;
import oop.ex6.variable.ParseVariable;
import oop.ex6.variable.Variable;
import oop.ex6.variable.VariableException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is the representing class of a method containing the name of the method, its parameters
 * and its method code
 */
public class Method {
    /**
     * the method name
     */
    private String name;

    /**
     * The method parameters
     */
    private ArrayList<Variable> parameters;

    /**
     * The method Code
     */
    private List<String> methodCode;

    /**
     * Regexs
     */
    private static final String VOIDREG = "void +", NAMEREG = "[a-zA-Z]\\w*", PARAMSREG = "\\s*,\\s*", NOPARAM = "\\s*";

    /**
     * The constructor method is checking that the method name il legal, and also checing its
     * parameters legalness
     *
     * @param methodCode - the method code
     * @throws MethodException   - Method Exception in case the name or the parameters are illegal
     * @throws VariableException - Variable Exception in case a parameter is illegal
     */
    public Method(List<String> methodCode) throws MethodException, VariableException {
        String line = methodCode.get(0);
        String name;
        ArrayList<Variable> parametersList = new ArrayList<>();//creating a parameters list
        line = line.trim();
        line = line.substring(0, line.length() - 1);//We want to work without the "{"
        Pattern pattern = Pattern.compile(VOIDREG);
        Matcher matcher = pattern.matcher(line);
        if (matcher.lookingAt()) {
            line = line.substring(matcher.end());
        }
        pattern = Pattern.compile(NAMEREG);
        matcher = pattern.matcher(line);
        if (matcher.find()) {  // getting a legal name method
            name = line.substring(matcher.start(), matcher.end());
            line = line.substring(matcher.end());
        } else {
            throw new MethodException();
        }
        line = line.trim();
        line = line.substring(1, line.length() - 1);
        List<String> lineArray = Arrays.asList(line.split(PARAMSREG));

        for (String parameter : lineArray) {  // Checking the legalness of every parameter
            if (parameter.matches(NOPARAM) && lineArray.size() == 1) {
                break;
            } else if (!Patterns.isParameter(parameter)) {
                throw new MethodException();
            }
            ArrayList<Variable> myParameters =
                    ParseVariable.declaration(parameter, new Stack<Variable>(), true);
            for (Variable myVar : myParameters) {
                for (Variable par : parametersList) {
                    if (myVar.getName().equals(par.getName())) {  //check if its an already
                        throw new MethodException();          // name value in the same block
                    }
                }
                parametersList.add(myVar);
            }
        }

        this.name = name;
        this.parameters = parametersList;
        this.methodCode = methodCode;
    }

    /**
     * @return the name method
     */
    String getName() {
        return name;
    }

    /**
     * @return the parameters list of the method
     */
    ArrayList<Variable> getParameters() {
        return parameters;
    }

    /**
     * @return all the method code
     */
    List<String> getMethodCode() {
        return methodCode;
    }


}
