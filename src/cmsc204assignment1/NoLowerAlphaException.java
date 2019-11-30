package cmsc204assignment1;

/**
 * Custom exception when the password contains no lowercase alphabet.
 * @author Gabriel I Feliz
 */
public class NoLowerAlphaException extends Exception {
    
    /**
     * This no-arg constructor defines the exception message for no lower alpha.
     */
    public NoLowerAlphaException() {
        super("The password must contain one lowercase alphabet character");
    }
}