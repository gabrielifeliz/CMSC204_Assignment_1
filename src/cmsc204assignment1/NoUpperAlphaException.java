package cmsc204assignment1;

/**
 * Custom exception when the password contains no uppercase alphabet.
 * @author Gabriel I Feliz
 */
public class NoUpperAlphaException extends Exception {
    
    /**
     * This no-arg constructor defines the exception message for no upper alpha.
     */
    public NoUpperAlphaException() {
        super("The password must contain one uppercase alphabet character");
    }
}