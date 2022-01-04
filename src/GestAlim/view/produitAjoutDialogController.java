package GestAlim.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import com.jfoenix.controls.JFXTextField;

import GestAlim.control.MainApp;
import GestAlim.control.MethodeSQL;
import GestAlim.model.ProduitEvolution;
import GestAlim.model.produit;

/**
 * Dialog to edit details of a product.
 *
 * @author Marco Jakob
 */
public class produitAjoutDialogController {

    @FXML
    private JFXTextField QuantiteField;
    private Stage dialogStage;
    private produit unproduit;
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
    public void setproduit(produit unproduit) {
        
    	this.unproduit = unproduit;


        QuantiteField.setText(Double.toString(0.0));
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
    	produit p= new produit(unproduit);
		p.setQuantite(Double.parseDouble(QuantiteField.getText()));
    		unproduit.setQuantite(unproduit.getQuantite()+Double.parseDouble(QuantiteField.getText()));
            EvolutionController.addElementProduitEvolution(0,new ProduitEvolution(p),"Ajout",MainApp.userActuel.getNomEmployee(),"Arrivage");
            MethodeSQL.EditDataProduit(unproduit);
            okClicked = true;
            dialogStage.close();
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

        if (QuantiteField.getText() == null || QuantiteField.getText().length() == 0) {
            errorMessage += "Quantité invalide!\n";
        } else {
            // try to parse the Quantity into an int.
            try {
                Integer.parseInt(QuantiteField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "La Quantité du produit est invalide (Doit être un entier)!\n";
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