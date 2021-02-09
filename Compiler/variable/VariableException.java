package oop.ex6.variable;

/**
 * A variable exception is creating during a bad variable utilisation or bad declaration
 * or a bad assignment
 */
public class VariableException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Variable Exception Message
     */
    private static final String MSSVARIABLEEXC = "Variable exception";

    /**
     * @return the message explaining its a variable exception problem
     */
    public String getMessage() {
        return MSSVARIABLEEXC;
    }
}
