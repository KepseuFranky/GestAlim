package GestAlim.view;

import GestAlim.control.MethodeSQL;
import GestAlim.model.Fournisseur;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FournisseurEditDialogController {
    @FXML
    private TextField NameField;
    @FXML
    private TextField AddressField;
    @FXML
    private TextField TelField;

    private Stage dialogStage;
    private Fournisseur fournisseur;
    private boolean okClicked= false;

    @FXML
    private void initialize() { }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage=dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     *
     * @param fournisseur
     */
    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;

        NameField.setText(fournisseur.getName());
        AddressField.setText(fournisseur.getAddress());
        TelField.setText(fournisseur.getTel());
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
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (NameField.getText() == null || NameField.getText().length() == 0) {
            errorMessage += " Nom invalide! \n";
        }
        if (AddressField.getText() == null || AddressField.getText().length() == 0) {
            errorMessage += " Adresse invalide! \n";
        }
        if (TelField.getText() == null || TelField.getText().length() == 0) {
            errorMessage += " Numéro de téléphone invalide! \n";
        }
        if (errorMessage.length() == 0) {
            return true;
        }
        else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle(" Champs invalides ");
            alert.setHeaderText(" S'il vous plait, corrigez les champs incorrectes :");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if(isInputValid()) {
            fournisseur.setName(NameField.getText());
            fournisseur.setAddress(AddressField.getText());
            fournisseur.setTel(TelField.getText());
            MethodeSQL.updateFournisseur(fournisseur);
            okClicked = true;
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



}
