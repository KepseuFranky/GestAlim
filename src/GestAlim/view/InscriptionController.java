package GestAlim.view;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import GestAlim.control.MainApp;
import GestAlim.control.MethodeSQL;
import GestAlim.model.Gestionnaire;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class InscriptionController {

	@FXML
	private JFXTextField nomField;
    @FXML
    private JFXTextField typeField;
    @FXML
    private JFXTextField loginField;
    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private JFXPasswordField confirmPasswordField;
    
    @FXML
    private Button confirmButton;
    @FXML
    private Button annulerButton;
   
    private Stage dialogStage;

    private Gestionnaire employee= new Gestionnaire();
    
    private boolean signInClicked = false;
    
    private MainApp mainApp = new MainApp();
    
    public InscriptionController() {
    	
    }
    
    @FXML
    private void initialize() {
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean confirmClicked() {
        return signInClicked;
    }
    
    @FXML
    private void handleConfirm() {
        if (isInputValid()) {
            employee.setNomEmployee(nomField.getText().toString());
            employee.setPasswordEmployee(passwordField.getText().toString());
            employee.setLoginEmployee(loginField.getText().toString());
            employee.setTypeEmployee(Integer.parseInt(typeField.getText().toString()));
            MethodeSQL.insertDataGestionnaire(employee);
            MainApp.employeeData.add(employee);
            
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.initOwner(dialogStage);
            alert.setTitle("Création de compte réussie");
            alert.setHeaderText("Votre Compte a été créé avec succès !");
            alert.setContentText("Contacter l'administrateur pour activation.");
            alert.showAndWait();
            mainApp.showLoginOverview();
            //sql.InsertDataEmployee(employee);
            
            //mainApp.showLoginPageOverview();
            
            
        }
    }
    
    @FXML
    private void handleCancel() {        
    	
    	
    	mainApp.showLoginOverview();
      
    }
    private boolean isInputValid() {
        String errorMessage = "";
        
        if (nomField.getText() == null || nomField.getText().length() == 0) {
            errorMessage += "Nom invalide!\n";
        }
       
        if (passwordField.getText() == null || passwordField.getText().length() == 0) {
            errorMessage += "Mot de passe invalide!\n";
        }
                
        if (!confirmPasswordField.getText().toString().equals(passwordField.getText().toString())) {
            errorMessage += "La confirmation de mot de passe ne correspond pas!\n";
        }
        
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Champs invalides");
            alert.setHeaderText("Verifier les champs!");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
