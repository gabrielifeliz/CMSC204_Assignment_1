package cmsc204assignment1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * GUI Application that asks the user to enter and re-enter a password
 * to determine whether it is valid and strong.
 * @author Gabriel I Feliz
 */
public class PasswordCheckerGui extends Application {
    
    /**
     * Label with rules of passwords.
     */
    private Label passwordRules;
    
    /**
     * Label that asks user to enter password to be validated.
     */
    private Label pwdPrompt;
    
    /**
     * Label that asks user to re-enter password to be validated.
     */
    private Label retypePwdPrompt;
    
    /**
     * Text field to enter password.
     */
    private TextField password;
    
    /**
     * Text field to re-enter password.
     */
    private TextField retypePassword;
    
    /**
     * Button to validate password entered on text field.
     */
    private Button checkPwd;
    
    /**
     * Button to validate passwords from a file.
     */
    private Button checkPwdFile;
    
    /**
     * Button to exit or leave the program.
     */
    private Button exit;
    
    /**
     * FileChooser object to select a file from the computer.
     */
    private FileChooser fileChooser;
    
    /**
     * File to be selected from FileChooser.
     */
    private File filePasswords;
    
    /**
     * Horizontal box section for password rules.
     */
    private HBox topPane;
    
    /**
     * Horizontal box section for entering password.
     */
    private HBox midPane1;
    
    /**
     * Horizontal box section for re-entering password.
     */
    private HBox midPane2;
    
    /**
     * Horizontal box section for buttons.
     */
    private HBox bottomPane;
    
    /**
     * Vertical box section showing all horizontal box sections.
     */
    private VBox mainPane;
    
    /**
     * This method initiates the GUI application responsible for validating
     * passwords either from user input or a file.
     * @param primaryStage stage of the application
     */
    @Override
    public void start(Stage primaryStage) {
        
        topSection();
        
        middleSection();
        
        bottomSection();
        
        mainPane = new VBox(topPane, midPane1, midPane2, bottomPane);
        
        primaryStage.setScene(new Scene(mainPane, 500, 350));
        primaryStage.setTitle("Password Checker");
        primaryStage.show();
    }
    
    /**
     * This private method sets the top section of the GUI application,
     * which is establishing the rules for a valid, strong password.
     */
    private void topSection() {
        passwordRules = new Label(
                " Use the following rules when creating your passwords:"
                + "\n\t1. Length must be greater than 6; a strong password "
                        + "will contain at least 10 characters"
                + "\n\t2. Must contain at least one lower case alpha character"
                + "\n\t3. Must contain at least one upper case alpha character"
                + "\n\t4. Must contain al least one numeric character"
                + "\n\t5. May noy have more than 2 of the same character "
                        + "in sequence"
        );
        
        HBox.setMargin(passwordRules, new Insets(5, 5, 30, 5));
        
        topPane = new HBox(passwordRules);
    }
    
    /**
     * This private method sets the middle section of the GUI application,
     * which is entering and re-entering a password to validate it.
     */
    private void middleSection() {
        pwdPrompt = new Label("Password");
        
        retypePwdPrompt = new Label("Re-type\npassword");
        
        password = new TextField();
        password.setPromptText("Enter password");
        
        retypePassword = new TextField();
        retypePassword.setPromptText("Re-type password");
        
        HBox.setMargin(pwdPrompt, new Insets(5, 5, 30, 5));
        HBox.setMargin(password, new Insets(5, 5, 30, 5));
        
        HBox.setMargin(retypePwdPrompt, new Insets(5, 5, 30, 5));
        HBox.setMargin(retypePassword, new Insets(5, 5, 30, 5));

        midPane1 = new HBox(pwdPrompt, password);
        midPane1.setAlignment(Pos.CENTER);
        
        midPane2 = new HBox(retypePwdPrompt, retypePassword);
        midPane2.setAlignment(Pos.CENTER);
    }
    
    /**
     * This private method sets the bottom section of the GUI application,
     * which is deciding what to do by pressing buttons: The "Check Password"
     * button validates the password entered by the user, the "Check Passwords 
     * in File" button validates passwords from a file, and the "Exit" button 
     * allows one to leave the program.
     */
    private void bottomSection() {
        
        Alert information = new Alert(Alert.AlertType.INFORMATION);
        
        checkPwd = new Button("Check _Password");
        checkPwd.setMnemonicParsing(true);
        checkPwd.setTooltip(new Tooltip("Validate password entered on fields"));
        checkPwd.setOnAction(e -> {
            
            information.setTitle("Password Status");
            information.setHeaderText(null);
            
            try {
                
                if (!password.getText().equals(retypePassword.getText())) {
                    throw new UnmatchedException();
                } 
                
                PasswordCheckerUtility.isValidPassword(password.getText());               

                if (PasswordCheckerUtility.isWeakPassword(password.getText())) {
                    throw new WeakPasswordException();
                }
                
                information.setContentText("The password is OK");
                information.showAndWait(); 
                
            } catch (Exception ex) {
                information.setContentText(ex.getMessage());
                information.showAndWait(); 
            }
            
        });
        
        checkPwdFile = new Button("Check Passwords in _File");
        checkPwdFile.setMnemonicParsing(true);
        checkPwdFile.setTooltip(new Tooltip("Validate passwords from a file"));
        checkPwdFile.setOnAction(e -> {
            
            ArrayList<String> passwords = new ArrayList<>();

            fileChooser = new FileChooser();
            fileChooser.setTitle("Choose a file to check passwords");
            
            if ((filePasswords = fileChooser.showOpenDialog(null)) != null) {
                try {
                    Scanner readFile = new Scanner(filePasswords);
                    while(readFile.hasNextLine()) {
                        passwords.add(readFile.nextLine());
                    }
                    
                    String notificationMessage = "";
                    for (String invalidPasswords : 
                            PasswordCheckerUtility.validPasswords(passwords)) {
                        notificationMessage += invalidPasswords + "\n";
                    }
                    
                    information.setTitle("Illegal Passwords");
                    information.setHeaderText("Illegal Passwords");
                    information.setContentText(notificationMessage);
                    information.showAndWait();
                    
                } catch (FileNotFoundException ex) {
                    information.setTitle("File status");
                    information.setContentText("File could not be found");
                    information.showAndWait();                
                }
            }
        });
        
        exit = new Button("_Exit");
        exit.setMnemonicParsing(true);
        exit.setTooltip(new Tooltip("Leave the program"));
        exit.setOnAction(e -> { Platform.exit();    System.exit(0); } );
        
        HBox.setMargin(checkPwd, new Insets(5));
        HBox.setMargin(checkPwdFile, new Insets(5));
        HBox.setMargin(exit, new Insets(5));
        
        bottomPane = new HBox(checkPwd, checkPwdFile, exit);
        bottomPane.setAlignment(Pos.CENTER);
    }

    /**
     * The main method is only needed for the IDE with limited 
     * JavaFX support, but not needed for running from the command line.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
