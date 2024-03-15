package controller;


import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.*;


public class LoginControllerTest extends LoginController {

    private LoginController loginController;

    @Test
    public void start(Stage stage) throws Exception {
        loginController = new LoginController();
    }

    /*
    @Test
    public void testLoginButtonClicked() {
        // Setup
        TextField emailField = new TextField("test@email.com");
        PasswordField passwordField = new PasswordField();
        passwordField.setText("password");
        loginController.emailField = emailField;
        loginController.passwordField = passwordField;

        // Action
        loginController.LoginButtonClicked(new ActionEvent());
}
*/
    }


