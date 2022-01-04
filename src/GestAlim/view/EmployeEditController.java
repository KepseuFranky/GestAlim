package GestAlim.view;

import com.jfoenix.controls.JFXTextField;

import GestAlim.control.MethodeSQL;
import GestAlim.model.Gestionnaire;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class EmployeEditController {

    @FXML
    private JFXTextField typeEmp;

    @FXML
    private JFXTextField idEmp;

    @FXML
    private JFXTextField nomEmp;

    @FXML
    private JFXTextField loginEmp;

    @FXML
    private JFXTextField actif;
    
    private Stage dialogStage;
    private Gestionnaire person;
    private boolean okClicked = false;
   
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }
    
    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     *
     * @param person
     */
    public void setPerson(Gestionnaire person) {
        this.person = person;

        idEmp.setText(Integer.toString(person.getIdEmployee()));
        nomEmp.setText(person.getNomEmployee());
        typeEmp.setText(Integer.toString(person.getTypeEmployee()));
        loginEmp.setText(person.getLoginEmployee());
        actif.setText(Integer.toString(person.getActif()));
        
    }
    
    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    
    @FXML
    void handleOk() {
    	if (isInputValid()) {
            person.setIdEmployee(Integer.parseInt(idEmp.getText()));
            person.setNomEmployee(nomEmp.getText());
            person.setTypeEmployee(Integer.parseInt(typeEmp.getText()));
            person.setLoginEmployee(loginEmp.getText());
            person.setActif(Integer.parseInt(actif.getText()));
            MethodeSQL.EditDataGestionnaire(person);
            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    void handleAnnuler() {
    	dialogStage.close();
    }
    
    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (nomEmp.getText() == null || nomEmp.getText().length() == 0) {
            errorMessage += "Nom invalide!\n";
        }
        if (loginEmp.getText() == null || loginEmp.getText().length() == 0) {
            errorMessage += "Login invalide!\n";
        }
        

        if (idEmp.getText() == null || idEmp.getText().length() == 0) {
            errorMessage += "Identifiant invalide!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(idEmp.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Identifiant invalide (Doit être un entier)!\n";
            }
        }
        
        if (typeEmp.getText() == null || typeEmp.getText().length() == 0) {
            errorMessage += "Type invalide!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(typeEmp.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Type invalide (Doit être un entier)!\n";
            }
        }

        

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Champs invalides");
            alert.setHeaderText("S'il vous plait corrigez les champs invalides!!");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

}
