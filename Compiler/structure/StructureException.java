package oop.ex6.structure;

/**
 * A structure exception is creating in case of a bad file structure
 */
public class StructureException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Structure Exception message
     */
    private static final String MESSSTRUCTUREEXC = "Structure exception";

    /**
     * @return the message explaining its a structure exception problem
     */
    public String getMessage() {
        return MESSSTRUCTUREEXC;
    }
}
