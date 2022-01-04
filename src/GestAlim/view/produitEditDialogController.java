package GestAlim.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import GestAlim.control.MainApp;
import GestAlim.control.MethodeSQL;
import GestAlim.model.ProduitEvolution;
import GestAlim.model.produit;
import GestAlim.util.DateUtil;

/**
 * Dialog to edit details of a product.
 *
 * @author Marco Jakob
 */
public class produitEditDialogController {

    @FXML
    private TextField CodeProduitField;
    @FXML
    private TextField NomProduitField;
    @FXML
    private TextField QuantiteField;
    @FXML
    private TextField CodeFournisseurField;
    @FXML
    private TextField PrixAchatField;
    @FXML
    private TextField PrixVenteField;
    @FXML
    private TextField DateInsertionField;
    @FXML
    private TextField DatePeremptionField;
    @FXML
    private TextField DescriptionField;
    @FXML
    private TextField IdCategorieField;


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

        CodeProduitField.setText(Integer.toString(unproduit.getCodeProduit()));
        NomProduitField.setText(unproduit.getNomProduit());
        QuantiteField.setText(Double.toString(unproduit.getQuantite()));
        CodeFournisseurField.setText(Integer.toString(unproduit.getCodeFournisseur()));
        PrixAchatField.setText(Double.toString(unproduit.getPrixAchat()));
        PrixVenteField.setText(Double.toString(unproduit.getPrixVente()));
        DateInsertionField.setText(DateUtil.format(unproduit.getDateInsertion()));
        DateInsertionField.setPromptText("dd.mm.yyyy");
        DatePeremptionField.setText(DateUtil.format(unproduit.getDatePeremption()));
        DatePeremptionField.setPromptText("dd.mm.yyyy");
        DescriptionField.setText(unproduit.getDescription());
        IdCategorieField.setText(Integer.toString(unproduit.getIdCategorie()));
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
        	unproduit.setCodeProduit(Integer.parseInt(CodeProduitField.getText()));
            unproduit.setNomProduit(NomProduitField.getText());
            unproduit.setQuantite(Double.parseDouble(QuantiteField.getText()));
            unproduit.setCodeFournisseur(Integer.parseInt(CodeFournisseurField.getText()));
            unproduit.setPrixAchat(Double.parseDouble(PrixAchatField.getText()));
            unproduit.setPrixVente(Double.parseDouble(PrixVenteField.getText()));
            unproduit.setDateInsertion(DateUtil.parse(DateInsertionField.getText()));
            unproduit.setDatePeremption(DateUtil.parse(DatePeremptionField.getText()));
            unproduit.setDescription(DescriptionField.getText());
            unproduit.setIdCategorie(Integer.parseInt(IdCategorieField.getText()));
            //MethodeSQL.insertDataProduit(unproduit);
            //MainApp.produitData.add(unproduit);
            EvolutionController.addElementProduitEvolution(0, new ProduitEvolution(unproduit),"Création",MainApp.userActuel.getNomEmployee(), "Livraison");
            
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

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";
        
        if (CodeProduitField.getText() == null || CodeProduitField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n";
        } else {
            // try to parse the CodeProduit into an int.
            try {
                Integer.parseInt(CodeProduitField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Code du Produit invalide (Doit être un entier)!\n";
            }
        }
        if (NomProduitField.getText() == null || NomProduitField.getText().length() == 0) {
            errorMessage += "Nom du Produit invalide!\n";
        }
        if (QuantiteField.getText() == null || QuantiteField.getText().length() == 0) {
            errorMessage += "Quantité invalide!\n";
        } else {
            // try to parse the Quantity into an int.
            try {
                Double.parseDouble(QuantiteField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "La Quantité du produit est invalide (Doit être un entier)!\n";
            }
        }
        if (PrixAchatField.getText() == null || PrixAchatField.getText().length() == 0) {
            errorMessage += "Prix d'Achat invalide!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Double.parseDouble(PrixAchatField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Prix d'achat invalide (Doit être un entier)!\n";
            }
        }
        if (PrixVenteField.getText() == null || PrixVenteField.getText().length() == 0) {
            errorMessage += "Prix de Vente invalide!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Double.parseDouble(PrixVenteField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Prix de vente invalide (Doit être un entier)!\n";
            }
        }
        if (DescriptionField.getText() == null || DescriptionField.getText().length() == 0) {
            errorMessage += "La description est invalide!\n";
        }

        if (DateInsertionField.getText() == null || DateInsertionField.getText().length() == 0) {
            errorMessage += "Date d'Insertion invalide!\n";
        } else {
            if (!DateUtil.validDate(DateInsertionField.getText())) {
                errorMessage += "La Date d'Insertion est Invalide. Utilisez le format dd.mm.yyyy!\n";
            }
        }
        if (DatePeremptionField.getText() == null || DatePeremptionField.getText().length() == 0) {
            errorMessage += "Date de Peremption invalide!\n";
        } else {
            if (!DateUtil.validDate(DatePeremptionField.getText())) {
                errorMessage += "La Date de Peremption est Invalide. Utilisez le format dd.mm.yyyy!\n";
            }
        }
        if (IdCategorieField.getText() == null || IdCategorieField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n";
        } else {
            // try to parse the CodeProduit into an int.
            try {
                Integer.parseInt(IdCategorieField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "L'Id Categorie invalide (Doit être un entier)!\n";
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