package GestAlim.view;

import GestAlim.control.MainApp;
import GestAlim.control.MethodeSQL;
import GestAlim.model.Fournisseur;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class FournisseurOverviewController {

	@FXML
    private Button Accueil;
    @FXML
    private Button produit;
    @FXML
    private Button facturation;
    @FXML
    private Button factures;
    @FXML
    private Button evolution;
    @FXML
    private Button employe;
    @FXML
    private Button fournisseur;
    @FXML
    private Button comptabilite;
    @FXML
    private TableView<Fournisseur> fournisseurTable;
    @FXML
    private TableColumn<Fournisseur,Integer> idColumn;
    @FXML
    private TableColumn<Fournisseur,String> NameColumn;
    @FXML
    private TableColumn<Fournisseur,String> AddressColumn;
    @FXML
    private TableColumn<Fournisseur,String> TelColumn;

    @FXML
    private Label NameLabel;
    @FXML
    private Label AddressLabel;
    @FXML
    private Label TelLabel;

    private MainApp mainApp;
    @FXML
	private Label salutLabel;

    public FournisseurOverviewController() {}


    public void initialize() {
    	salutLabel.setText(""+MainApp.userActuel.getNomEmployee()+"");
//    	if(MainApp.userActuel.getTypeEmployee()==1) {
//    		facturation.setOpacity(0.5);
//    		factures.setOpacity(0.5);
//    		employe.setOpacity(0.5);
//    		comptabilite.setOpacity(0.5);
//    	}
//    	if(MainApp.userActuel.getTypeEmployee()==2) {
//    		produit.setOpacity(0.5);
//    		evolution.setOpacity(0.5);
//    		employe.setOpacity(0.5);
//    		fournisseur.setOpacity(0.5);
//    		comptabilite.setOpacity(0.5);
//    	}
//    	if(MainApp.userActuel.getTypeEmployee()==3) {
//    		facturation.setOpacity(0.5);
//    		produit.setOpacity(0.5);
//    		
//    		
//    	}
    	idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject() );
        NameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty() );
        AddressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        TelColumn.setCellValueFactory(cellData -> cellData.getValue().telProperty());
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp= mainApp;

        fournisseurTable.setItems(mainApp.getFournisseurData());
    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteFournisseur() {
        int selectedIndex= fournisseurTable.getSelectionModel().getSelectedIndex();
        if(selectedIndex >=0) {
            fournisseurTable.getItems().remove(selectedIndex);
            MethodeSQL.deleteFournisseur(fournisseurTable.getItems().remove(selectedIndex));
        }
        else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle(" Aucun élément sélectionné ");
            alert.setHeaderText(" Aucun fournisseur selectionné ");
            alert.setContentText(" S'il vous plait, sélectionnez un fournisseur dans le tableau. ");

            alert.showAndWait();
        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewFournisseur() {
        Fournisseur tempFournisseur = new Fournisseur();
        boolean okClicked = mainApp.showFournisseurEditDialog(tempFournisseur);
        if (okClicked) {
        	tempFournisseur.setId(MethodeSQL.getMaxIdFournisseur()+1);
            mainApp.getFournisseurData().add(tempFournisseur);
            MethodeSQL.newFournisseur(tempFournisseur);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditFournisseur() {
        Fournisseur selectedFournisseur = fournisseurTable.getSelectionModel().getSelectedItem();
        if (selectedFournisseur != null) {
            boolean okClicked = mainApp.showFournisseurEditDialog(selectedFournisseur);
        }
        else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle(" Aucun élément sélectionné ");
            alert.setHeaderText(" Aucun fournisseur selectionné ");
            alert.setContentText(" S'il vous plait, sélectionnez un fournisseur dans le tableau. ");

            alert.showAndWait();
        }
    }
    @FXML
    public void HandleAccueil() {
    	MainApp.showDebutApp();
    	
    }
    
    @FXML
    public void HandleFournisseur() {
    	if(MainApp.userActuel.getTypeEmployee()==1 || MainApp.userActuel.getTypeEmployee()==0) {
    		mainApp.showFournisseurOverview();
    	}
    	
    }
    
    @FXML
    public void HandleProduit() {
    	if(MainApp.userActuel.getTypeEmployee()==1 || MainApp.userActuel.getTypeEmployee()==0) {
    		mainApp.showproduitOverview();
    	}
    	
    }
    
    @FXML
    public void HandleFacturation() {
    	if(MainApp.userActuel.getTypeEmployee()==2 || MainApp.userActuel.getTypeEmployee()==0) {
    		mainApp.showFacturation();
    	}
    	
    }
    
    @FXML
    public void HandleFacture() {
    	if(MainApp.userActuel.getTypeEmployee()==2 || MainApp.userActuel.getTypeEmployee()==0) {
    		mainApp.showFactureOverview();
    	}
    	
    }
    
    @FXML
    public void HandleEvolution() {
    	if(MainApp.userActuel.getTypeEmployee()==3 || MainApp.userActuel.getTypeEmployee()==1 || MainApp.userActuel.getTypeEmployee()==0) {
    		mainApp.showEvolutionPage();
    	}
    	
    }
    
    @FXML
    public void HandleEmploye() {
    	if(MainApp.userActuel.getTypeEmployee()==3 || MainApp.userActuel.getTypeEmployee()==0) {
    		mainApp.showPersonOverview();
    	}
    	
    }
    
    @FXML
    public void HandleComptabilite() {
    	if(MainApp.userActuel.getTypeEmployee()==3 || MainApp.userActuel.getTypeEmployee()==0) {
			mainApp.showCOmptaOverview();
    	}
    	
    }
    
    @FXML
    public void HandleOut() {
    	mainApp.showLoginOverview();;
    }
}
