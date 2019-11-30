package cmsc204assignment1;

/**
 * Custom exception when the password contains no digits.
 * @author Gabriel I Feliz
 */
public class NoDigitException extends Exception {
    
    /**
     * This no-arg constructor defines the exception message for no digit.
     */
    public NoDigitException() {
        super("The password must contain one digit");
    }
}