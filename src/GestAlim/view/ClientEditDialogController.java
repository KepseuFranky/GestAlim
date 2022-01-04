package GestAlim.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import GestAlim.control.MethodeSQL;
import GestAlim.model.Client;
//import GestAlimm.util.DateUtil;

/**
 * Dialog to edit details of a product.
 *
 * @author Marco Jakob
 */
public class ClientEditDialogController {

    @FXML
    private TextField IdClientField;
    @FXML
    private TextField NomClientField;
    @FXML
    private TextField AdresseField;
    @FXML
    private TextField TelClientField;
    @FXML
    private TextField BonusField;


    private Stage dialogStage;
    private Client unclient;
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
    public void setClient(Client unclient) {
        
    	this.unclient = unclient;

        IdClientField.setText(Integer.toString(unclient.getIdClient()));
        NomClientField.setText(unclient.getNomClient());
        TelClientField.setText(Integer.toString(unclient.getTelClient()));
        BonusField.setText(Integer.toString(unclient.getBonus()));
        AdresseField.setText(unclient.getAdresse());
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
    private void handleOk() {
        if (isInputValid()) {
        	unclient.setIdClient(Integer.parseInt(IdClientField.getText()));
        	unclient.setNomClient(NomClientField.getText());
        	unclient.setAdresse(AdresseField.getText());
        	unclient.setBonus(Integer.parseInt(BonusField.getText()));
        	unclient.setTelClient(Integer.parseInt(TelClientField.getText()));
            okClicked = true;
            MethodeSQL.EditDataClient(unclient);
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";
        
        if (IdClientField.getText() == null || IdClientField.getText().length() == 0) {
            errorMessage += "No valid Id Client!\n";
        } else {
            // try to parse the CodeProduit into an int.
            try {
                Integer.parseInt(IdClientField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "L'Id du Client invalide (Doit être un entier)!\n";
            }
        }
        if (NomClientField.getText() == null || NomClientField.getText().length() == 0) {
            errorMessage += "Nom du Client invalide!\n";
        }
        if (AdresseField.getText() == null || AdresseField.getText().length() == 0) {
            errorMessage += "Adresse invalide!\n";
        }
        if (TelClientField.getText() == null || TelClientField.getText().length() == 0) {
            errorMessage += "Numero de téléphone invalide!\n";
        } else {
            // try to parse the Quantity into an int.
            try {
                Integer.parseInt(TelClientField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Le Numéro de téléphone du Client est invalide (Doit être un entier)!\n";
            }
        }
        if (BonusField.getText() == null || BonusField.getText().length() == 0) {
            errorMessage += "Bonus invalide!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(BonusField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Bonus invalide (Doit être un entier)!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Champ Invalide");
            alert.setHeaderText("Veuillez corriger le champ invalide");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
        
    }
}