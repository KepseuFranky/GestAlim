package GestAlim.view;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import GestAlim.control.MainApp;
import GestAlim.model.Gestionnaire;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class LoginOverviewController {
	@FXML
    private JFXTextField usernameField;
    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private Button signUpButton;
    @FXML
    private Button signInButton;
    @FXML
    private Button Retour;
    @FXML
    private Hyperlink forgotPasswordHyperlink;
        
    private Stage dialogStage;
    
    private Gestionnaire utilisateur= new Gestionnaire();
        
    private boolean signInClicked = false;
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    private MainApp mainApp = new MainApp();
    
    
    public LoginOverviewController() {
    }
    
    
    
    @FXML
    private void initialize() {
    }
    
    /*public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

    }*/
    
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    public boolean isSignInClicked() {
        return signInClicked;
    }
 

    
    @FXML
    private void handleSignIn() {
        if (isInputValid()) {
            utilisateur.setLoginEmployee(usernameField.getText());
            utilisateur.setPasswordEmployee(passwordField.getText());           
            if(mainApp.controlInput(utilisateur) && MainApp.userActuel.getActif()==1)  {
            	
            	MainApp.showDebutApp();
            	
            }
            else if(!mainApp.controlInput(utilisateur)) {
            	 // Show the error message.
                Alert alert = new Alert(AlertType.ERROR);
                alert.initOwner(dialogStage);
                alert.setTitle("Champs invalides");
                alert.setHeaderText("Veuillez corriger les champs invalides");
                alert.setContentText("Login ou Mot de passe invalides");

                alert.showAndWait();
            }else {
            	Alert alert = new Alert(AlertType.ERROR);
                alert.initOwner(dialogStage);
                alert.setTitle("Employé Inactif");
                alert.setHeaderText("Veuillez attendre qu'on vous active");
                alert.setContentText("Contactez l'administrateur");
            }
        }
        isInputValid();
    }
    
    @FXML
    private void handleSignUp() {
        mainApp.showCreationEmployeOverview();
    }
    
    @FXML
    private void handleForgotPassword() {
    	 Alert alert = new Alert(AlertType.WARNING);
         alert.initOwner(dialogStage);
         alert.setTitle("ForgotPassword");
         alert.setHeaderText("Contactez le manager");
         

         alert.showAndWait();
    }
    

    
    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (usernameField.getText() == null || usernameField.getText().length() == 0) {
            errorMessage += "Votre Login est invalide!\n";
        }
       
        if (passwordField.getText() == null || passwordField.getText().length() == 0) {
            errorMessage += "Votre mot de passe est invalide!\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Les champs sont invalides!");
            alert.setHeaderText("Vérifiez les champs remplis!");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}