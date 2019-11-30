package cmsc204assignment1;

/**
 * Custom exception when the password's length is between 6 and 9.
 * @author Gabriel I Feliz
 */
public class WeakPasswordException extends Exception {
    
    /**
     * This no-arg constructor defines the exception message for weak password.
     */
    public WeakPasswordException() {
        super("The password is OK but weak");
    }
}