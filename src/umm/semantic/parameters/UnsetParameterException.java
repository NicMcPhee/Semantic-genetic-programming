package umm.semantic.parameters;

/**
 * An exception that indicates that an attempt was made to read a
 * parameter that didn't yet have a value.
 * 
 * @author mcphee
 * @version $Revision: $
 */
//ESCA-JAVA0051:
public class UnsetParameterException extends RuntimeException {

    /**
     * A generated serialization ID to make a warning go away.
     */
    private static final long serialVersionUID = 5232463286570417910L;

    /**
     * Construct an instance of this exception with the specified message.
     * 
     * @param message the error message
     */
    public UnsetParameterException(String message) {
        super(message);
    }

}
