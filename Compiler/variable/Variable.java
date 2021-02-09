package oop.ex6.variable;

import oop.ex6.Patterns;

/**
 * A variable class
 */
public class Variable {
    /**
     * A default value for a non assignment variable
     */
    private static final String DEFAULT_VALUE = "DEFAULT VALUE";

    /**
     * Specific of a variable
     */
    private String type, name, value;

    /**
     * If the variable is final or not
     */
    private boolean isFinal;

    /**
     * All legal types
     */
    private static final String STRTYPE = "String", INTTYPE = "int",
            BOOLTYPE = "boolean", CHARTYPE = "char", DOUBLETYPE = "double";
    /**
     * All the default values
     */
    private static final String DEFSTR = "\"default-string\"", DEFINT = "0",
            DEFDOUBLE = "0.0", DEFBOOL = "true", DEFCHAR = "'*'";

    /**
     * Constructor with a given value (already assign variable)
     *
     * @param type    - type variable
     * @param name    - variable name
     * @param value   -variable value
     * @param isFinal - value final or not
     * @throws VariableException - in need
     */
    public Variable(String type, String name, String value, boolean isFinal) throws VariableException {
        if (!checkType(type, value.trim())) { //Value different from the type asked
            throw new VariableException();
        }
        checkName(name);  //For a legal name
        this.type = type;
        this.name = name;
        setValue(this.type);
        this.isFinal = isFinal;
    }

    /**
     * Constructor with no given value
     *
     * @param type     - type variable
     * @param name     - variable name
     * @param isFinal  - value final or not
     * @param isMethod - true iff its a parameter method
     * @throws VariableException - in need
     */
    public Variable(String type, String name, boolean isFinal, boolean isMethod) throws VariableException {
        if (isFinal && !isMethod) {
            throw new VariableException(); //Illegal to create a final variable without a value
        }
        checkName(name);
        checkType(type, DEFAULT_VALUE); //check the given type
        this.type = type;
        this.name = name;
        this.isFinal = isFinal;
        if (isMethod) {
            setValue(this.type);
        } else {
            this.value = DEFAULT_VALUE;
        }
    }

    /**
     * @return the default value of an non assign variable
     */
    public static String getDefaultValue() {
        return DEFAULT_VALUE;
    }

    /**
     * Check if the type is an existig type and if the value goes with it
     *
     * @param exceptedType -variable type
     * @param value        - variable value
     * @return true iff the type matches the value
     * @throws VariableException - in need
     */
    public static boolean checkType(String exceptedType, String value) throws VariableException {
        switch (exceptedType) {
            case STRTYPE:
                return Patterns.isString(value);
            case INTTYPE:
                return Patterns.isInt(value);
            case DOUBLETYPE:
                return Patterns.isDouble(value);
            case BOOLTYPE:
                return Patterns.isBoolean(value);
            case CHARTYPE:
                return Patterns.isChar(value);
            default:
                throw new VariableException();

        }
    }

    /**
     * Check if it's a legal name
     *
     * @param name - variable name
     * @throws VariableException - in need
     */
    private static void checkName(String name) throws VariableException {
        if (!Patterns.isVarName(name)) {
            throw new VariableException();
        }
    }

    /**
     * @return the variable name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the variable type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the variable value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value despite the type
     *
     * @param type - varable type
     */
    void setValue(String type) {
        switch (type) {
            case STRTYPE:
                this.value = DEFSTR;
                break;
            case INTTYPE:
                this.value = DEFINT;
                break;
            case DOUBLETYPE:
                this.value = DEFDOUBLE;
                break;
            case BOOLTYPE:
                this.value = DEFBOOL;
                break;
            case CHARTYPE:
                this.value = DEFCHAR;
                break;
        }
    }

    /**
     * @return true iff the variable is a final variable
     */
    public boolean getFinal() {
        return isFinal;
    }
}
