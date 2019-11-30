package cmsc204assignment1;

/**
 * Custom exception when the password's length is not at least 6.
 * @author Gabriel I Feliz
 */
public class LengthException extends Exception {
    
    /**
     * This no-arg constructor defines the exception message for length.
     */
    public LengthException() {
        super("The password's length must be at least 6");
    }
}