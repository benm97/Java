package oop.ex6;

/**
 * This class contains all the patterns we need to check
 */
public class Patterns {
    private static final String COMMENT_PATTERN = "//.*", BLANK_PATTERN = "\\s*",
            METHOD_DECLARATION_PATTERN = "\\s*void\\s+[a-zA-Z]\\w*\\s*\\([\\w,\\s]*\\)\\s*\\{\\s*",
            VARIABLE_DECLARATION_PATTERN = "(\\s*|\\s*final\\s+)[a-zA-Z]+\\s+([a-zA-Z]\\w*|_\\w+)[^();]*;\\s*",
            PARAMETER_PATTERN = "(\\s*|\\s*final\\s+)[a-zA-Z]+\\s+([a-zA-Z]\\w*|_\\w+)\\s*",
            BLOCKSTART_PATTERN = "\\s*(if|while)\\s*\\([^();]+\\)\\s*\\{\\s*",
            VARIABLE_ASSIGNATION_PATTERN = "\\s*([a-zA-Z]\\w*|_\\w+)\\s*=\\s*[^();]+\\s*;\\s*",
            METHOD_CALL_PATTERN = "\\s*[a-zA-Z]\\w*\\s*\\([^();]*\\)\\s*;\\s*",
            STRING_PATTERN = "\".*\"", BOOLEAN_PATTERN = "true|false|-?(([0-9]+.[0-9]+)|[0-9]+)",
            INT_PATTERN = "-?[0-9]+", CHAR_PATTERN = "'.'", DOUBLE_PATTERN = "-?(([0-9]+\\.[0-9]+)|[0-9]+)",
            VARIABLE_NAME_PATTERN = "([a-zA-Z]\\w*|_\\w+)", RETURN_PATTERN = "\\s*return\\s*;\\s*";

    /**
     * @param line - line to check
     * @return true iff the line is a comment line
     */
    public static boolean isComment(String line) {
        return line.matches(COMMENT_PATTERN);
    }

    /**
     * @param line - line to check
     * @return true iff the line is a blank line
     */
    public static boolean isBlank(String line) {
        return line.matches(BLANK_PATTERN);
    }

    /**
     * @param line - line to check
     * @return true iff the line is a Method declaration line
     */
    public static boolean isMethodDeclaration(String line) {
        return line.matches(METHOD_DECLARATION_PATTERN);
    }

    /**
     * @param line - line to check
     * @return true iff the line is a variable declaration line
     */
    public static boolean isVariableDeclaration(String line) {
        return line.matches(VARIABLE_DECLARATION_PATTERN);
    }

    /**
     * @param line - line to check
     * @return true iff the line is a legal parameter
     */
    public static boolean isParameter(String line) {
        return line.matches(PARAMETER_PATTERN);
    }

    /**
     * @param line - line to check
     * @return true iff the line is a block start
     */
    public static boolean isBlockStart(String line) {
        return line.matches(BLOCKSTART_PATTERN);
    }

    /**
     * @param line - line to check
     * @return true iff the line is a variable assignation line
     */
    public static boolean isVariableAssignation(String line) {
        return line.matches(VARIABLE_ASSIGNATION_PATTERN);
    }

    /**
     * @param line - line to check
     * @return true iff the line is a method call line
     */
    public static boolean isMethodCall(String line) {
        return line.matches(METHOD_CALL_PATTERN);
    }

    /**
     * @param line - line to check
     * @return true iff the line is a string
     */
    public static boolean isString(String line) {
        return line.matches(STRING_PATTERN);
    }

    /**
     * @param line - line to check
     * @return true iff the line is a boolean
     */
    public static boolean isBoolean(String line) {
        return line.matches(BOOLEAN_PATTERN);
    }

    /**
     * @param line - line to check
     * @return true iff the line is an int
     */
    public static boolean isInt(String line) {
        return line.matches(INT_PATTERN);
    }

    /**
     * @param line - line to check
     * @return true iff the line is a character
     */
    public static boolean isChar(String line) {
        return line.matches(CHAR_PATTERN);
    }

    /**
     * @param line - line to check
     * @return true iff the line is a double
     */
    public static boolean isDouble(String line) {
        return line.matches(DOUBLE_PATTERN);
    }

    /**
     * @param line - line to check
     * @return true iff the line is a legal variable name
     */
    public static boolean isVarName(String line) {
        return line.matches(VARIABLE_NAME_PATTERN);
    }

    /**
     * @param line - line to check
     * @return true iff the line is a return line
     */
    public static boolean isReturn(String line) {
        return line.matches(RETURN_PATTERN);
    }
}
