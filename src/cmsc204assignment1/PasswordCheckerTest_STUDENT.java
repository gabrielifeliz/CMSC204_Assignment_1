package cmsc204assignment1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author Gabriel I Feliz
 *
 */
public class PasswordCheckerTest_STUDENT {
    
    ArrayList<String> passwords;
    
    @Before
    public void setUp() throws Exception {
        String[] passwordStrings = {"dKghhxe44", "pfhd9cy82jfrr", "EQx7b",
            "stJUkPveae", "eKgsssK8q7", "W5CK9LXG", "f7YxNMkdbRQh6a8"};
        
        passwords = new ArrayList<>(Arrays.asList(passwordStrings));
    }
    
    @After
    public void tearDown() throws Exception {
        passwords = null;
    }

    /**
     * Test if the password has more than 2 of the same character in sequence
     * This test should throw a InvalidSequenceException for second case
     */
    @Test
    public void testIsValidPasswordInvalidSequence() {
        try {
            PasswordCheckerUtility.isValidPassword("cQxxx9LbdW3qJ");
            assertTrue("InvalidSequenceException isn't thrown", false);
        } catch (InvalidSequenceException e) {
            assertTrue("InvalidSequenceException successfully thrown", true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertTrue("Wrong exception is thrown", false);
        }
    }

    /**
     * Test if the password has at least one digit
     * One test should throw a NoDigitException
     */
    @Test
    public void testIsValidPasswordNoDigit() {
        try {
            PasswordCheckerUtility.isValidPassword("dmksLJLBcha");
            assertTrue("NoDigitException isn't thrown", false);
        } catch (NoDigitException e) {
            assertTrue("NoDigitException successfully thrown", true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertTrue("Wrong exception thrown", false);
        }
    }

    /**
     * Test if the password has at least one lowercase alpha character
     * This test should throw a NoLowerAlphaException for second case
     */
    @Test
    public void testIsValidPasswordNoLowerAlpha() {
        try {
            PasswordCheckerUtility.isValidPassword("R5Z6L6NDNL9");
            assertTrue("NoLowerAlphaException isn't thrown", false);
        } catch (NoLowerAlphaException e) {
            assertTrue("NoLowerAlphaException successfully thrown", true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertTrue("Wrong exception thrown", false);
        }
    }

    /**
     * Test if the password has at least one uppercase alpha character
     * This test should throw a NoUpperAlphaException for second case
     */
    @Test
    public void testIsValidPasswordNoUpperAlpha() {
        try {
            PasswordCheckerUtility.isValidPassword("sxwbng6guzr");
            assertTrue("NoUpperAlphaException isn't thrown", false);
        } catch (NoUpperAlphaException e) {
            assertTrue("NoUpperAlphaException successfully thrown", true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertTrue("Wrong exception thrown", false);
        }
    }

    /**
     * Test correct passwords
     * This test should not throw an exception
     */
    @Test
    public void testIsValidPasswordSuccessful() {
        try {
            PasswordCheckerUtility.isValidPassword("DHJpXN27Sfh");
            PasswordCheckerUtility.isValidPassword("8tQ3n9E4zE6");
            PasswordCheckerUtility.isValidPassword("TR3cCjddrFY");
            PasswordCheckerUtility.isValidPassword("rN9CWaQ7h6T");
            assertTrue("Valid password", true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertTrue("Unexpected exception thrown", false);
        }
    }

    /**
     * Test if the password is less than 8 characters long.
     * This test should throw a LengthException for second case.
     */
    @Test
    public void testIsValidPasswordTooShort() {
        try {
            PasswordCheckerUtility.isValidPassword("ABc1");
            assertTrue("LengthException isn't thrown", false);
        } catch (LengthException e) {
            assertTrue("LengthException successfully thrown", true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertTrue("Wrong exception thrown", false);
        }
    }

    /**
     * Test if the password has more than 2 of the same character in sequence
     * This test should throw a InvalidSequenceException for second case
     */
    @Test
    public void testIsWeakPassword() {
        try {
            assertEquals(true, PasswordCheckerUtility.isValidPassword("Abc123"));
            assertTrue(PasswordCheckerUtility.isWeakPassword("Abc123"));
        } catch (Exception e) {
            assertTrue("Unexpected exception thrown: " + e.getMessage(), false);
        }
    }

    /**
     * Test the validPasswords method
     * Check the results of the ArrayList of Strings returned by the validPasswords method
     */
    @Test
    public void testValidPasswords() {
        ArrayList<String> results;
        results = PasswordCheckerUtility.validPasswords(passwords);

        Scanner scan = new Scanner(results.get(0));
        assertEquals("pfhd9cy82jfrr", scan.next());
        String nextResults = scan.nextLine().toLowerCase();
        assertTrue(nextResults.contains("uppercase"));
        
        scan = new Scanner(results.get(1));    
        assertEquals("EQx7b", scan.next());
        nextResults = scan.nextLine().toLowerCase();
        assertTrue(nextResults.contains("at least 6"));
        
        scan = new Scanner(results.get(2));    
        assertEquals("stJUkPveae", scan.next());
        nextResults = scan.nextLine().toLowerCase();
        assertTrue(nextResults.contains("digit"));
        
        scan = new Scanner(results.get(3));    
        assertEquals("eKgsssK8q7", scan.next());
        nextResults = scan.nextLine().toLowerCase();
        assertTrue(nextResults.contains("more than two"));
        
        scan = new Scanner(results.get(4));    
        assertEquals("W5CK9LXG", scan.next());
        nextResults = scan.nextLine().toLowerCase();
        assertTrue(nextResults.contains("lowercase"));
    }
}