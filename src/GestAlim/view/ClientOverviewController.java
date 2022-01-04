package GestAlim.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import GestAlim.control.MainApp;
import GestAlim.control.MethodeSQL;
import GestAlim.model.Client;

public class ClientOverviewController {
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
    private TableView<Client> ClientTable;
    @FXML
    private TableColumn<Client, String> NomClientColumn;
    @FXML
    private TableColumn<Client, String> AdresseColumn;
    @FXML
    private TableColumn<Client, Integer> IdClientColumn;
    @FXML
    private TableColumn<Client, Integer> BonusColumn;
    @FXML
    private TableColumn<Client, Integer> TelClientColumn;
   


    // Reference to the main application.
    private MainApp mainApp=new MainApp();
    @FXML
	private Label salutLabel;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public ClientOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
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
        // Initialize the person table with the two columns.
        NomClientColumn.setCellValueFactory(cellData -> cellData.getValue().NomClientProperty());
        AdresseColumn.setCellValueFactory(cellData -> cellData.getValue().AdresseProperty());
        IdClientColumn.setCellValueFactory(cellData -> cellData.getValue().IdClientProperty().asObject());
        BonusColumn.setCellValueFactory(cellData -> cellData.getValue().BonusProperty().asObject());
        TelClientColumn.setCellValueFactory(cellData -> cellData.getValue().TelClientProperty().asObject());
        

        // Clear produit details.
       // showproduitDescription(null);

        // Listen for selection changes and show the person details when changed.
        //produitTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showproduitDescription(newValue));

    }
    

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteClient() {
        int selectedIndex = ClientTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            ClientTable.getItems().remove(selectedIndex);
            MethodeSQL.deleteDataClient(ClientTable.getItems().remove(selectedIndex));
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Aucune Selection");
            alert.setHeaderText("Vous n'avez sélectionné aucun Client");
            alert.setContentText("Veuillez sélectionner un Client.");

            alert.showAndWait();
        }
    }
    
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewClient() {
        Client tempClient = new Client();
        
        boolean okClicked = mainApp.showClientEditDialog(tempClient);
        if (okClicked) {
        	tempClient.setIdClient(MethodeSQL.getMaxIdClient()+1);
            mainApp.getClientData().add(tempClient);
            MethodeSQL.insertDataClient(tempClient);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected produit.
     */
    @FXML
    private void handleEditClient() {
        Client selectedClient = ClientTable.getSelectionModel().getSelectedItem();
        if (selectedClient != null) {
            boolean okClicked = mainApp.showClientEditDialog(selectedClient);
            /*if (okClicked) {
                showClientDescription(selectedClient);
            }*/

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Client Selected");
            alert.setContentText("Please select a Client in the table.");

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
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        ClientTable.setItems(mainApp.getClientData());
    }
}
