package oop.ex6.method;

/**
 * The class method exception is creating in case of a illegal problem
 * during creating a method
 */
public class MethodException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Method Exception Message
     */
    private static final String MSSMETHODEXC = "Method exception";

    /**
     * @return the message explining its a method exception problem
     */
    public String getMessage() {
        return MSSMETHODEXC;
    }
}
