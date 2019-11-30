package cmsc204assignment1;

import java.util.ArrayList;

/**
 * Utility class with useful methods for password checker.
 * @author Gabriel I Feliz
 */
public class PasswordCheckerUtility {
    
    public PasswordCheckerUtility() {}

    /**
     * This static method responsible for determining whether a password 
     * is valid or not.
     * @param passwordString the password passed by user input.
     * @return whether a given password is valid or not.
     * @throws LengthException the given password is less than 6 characters.
     * @throws NoDigitException the given password has no digits.
     * @throws NoUpperAlphaException the given password has no uppercase 
     * characters.
     * @throws NoLowerAlphaException the given password has no lowercase 
     * characters.
     * @throws InvalidSequenceException  the given password has more than 
     * 2 characters in sequence.
     */
    public static boolean isValidPassword(String passwordString)
            throws LengthException, NoDigitException, NoUpperAlphaException, 
            NoLowerAlphaException, InvalidSequenceException {
        if (passwordString.length() < 6) {
            throw new LengthException();
        }

        for (int i = 0; i < passwordString.length(); i++) {
            if (!Character.isDigit(passwordString.charAt(i))) {
                if (i == passwordString.length() - 1) {
                    throw new NoDigitException();
                }
            } else {
                break;
            }
        }

        for (int i = 0; i < passwordString.length(); i++) {
            if (!Character.isUpperCase(passwordString.charAt(i))) {
                if (i == passwordString.length() - 1) {
                    throw new NoUpperAlphaException();
                }
            } else {
                break;
            }
        }

        for (int i = 0; i < passwordString.length(); i++) {
            if (!Character.isLowerCase(passwordString.charAt(i))) {
                if (i == passwordString.length() - 1) {
                    throw new NoLowerAlphaException();
                }
            } else {
                break;
            }
        }

        int len = passwordString.length();

        for (int i = 0; i < len; i++) {
            if ((i + 1 < len) && (i + 2 < len)) {
                if ((passwordString.charAt(i) == passwordString.charAt(i + 1))
                        && (passwordString.charAt(i + 1) 
                        == passwordString.charAt(i + 2))) {
                    throw new InvalidSequenceException();
                }
            }
        }

        return true;
    }
    
    /**
     * This static method is responsible for determine whether a password 
     * is weak or not.
     * @param passwordString the password passed by user input.
     * @return whether the given password is weak or not.
     */
    public static boolean isWeakPassword(String passwordString) {
        return (passwordString.length() > 5) && (passwordString.length() < 10);
    }
    
    /**
     * This static method is responsible for validating passwords
     * from a password and returning invalid password with exception messages.
     * @param passwords the array list of password to be validated obtained 
     * from a file.
     * @return invalid passwords with their respective exception message.
     */
    public static ArrayList<String> validPasswords(ArrayList<String> passwords) {
        ArrayList<String> invalidPassword = new ArrayList<>();

        for (String password : passwords) {
            try {
                isValidPassword(password);
            } catch (Exception ex) {
                invalidPassword.add(password + " " + ex.getMessage() + "\n");
            }
        }

        return invalidPassword;
    }
}