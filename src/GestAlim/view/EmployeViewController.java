package GestAlim.view;


import GestAlim.control.MainApp;
import GestAlim.control.MethodeSQL;
import GestAlim.model.Gestionnaire;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumnBase;
import javafx.scene.control.TableView;

import javafx.scene.control.TableRow;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class EmployeViewController {

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
    private TableColumn<Gestionnaire, Integer> IdEmpColumn;

    @FXML
    private TableColumn<Gestionnaire, Integer> TypeEmpColumn;

    @FXML
    private TableView<Gestionnaire> GestionnaireTable;

    @FXML
    private TableColumn<Gestionnaire, String> LoginEmpColumn;

    @FXML
    private TableColumn<Gestionnaire, String> NomEmpColumn;

    
    // Reference to the main application.
    private MainApp mainApp;
    @FXML
	private Label salutLabel;
   

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public EmployeViewController() {
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
        IdEmpColumn.setCellValueFactory(
                cellData -> cellData.getValue().IdEmployee().asObject());
        NomEmpColumn.setCellValueFactory(
                cellData -> cellData.getValue().NomEmployee());
        LoginEmpColumn.setCellValueFactory(
                cellData -> cellData.getValue().LoginEmployee());
        TypeEmpColumn.setCellValueFactory(
                cellData -> cellData.getValue().TypeEmployee().asObject());
        GestionnaireTable.setRowFactory(row->{
        	return new TableRow<Gestionnaire>()
        	{
        		@Override
        		public void updateItem(Gestionnaire item,boolean empty) {
        			super.updateItem(item, empty);
        			if (item==null) {
        				setStyle("");
        			}
        			else if(item.getActif()==0) {
        				setStyle("-fx-background-color:RED;");
        			}
        			else {
        				setStyle("-fx-background-color:#6fd643;");
        			}
        		}
        	};
        }
        
        		);
        
    }
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        GestionnaireTable.setItems(mainApp.getPersonData());
        
        
    }
    
    @FXML
    void handleModifier() {
    	Gestionnaire selectedPerson = GestionnaireTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
            	
            }    

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Aucune Selection");
            alert.setHeaderText("Aucun employé Selectionné");
            alert.setContentText("S'il vous plait selectionnez une personee dans le tableau.");

            alert.showAndWait();
        }
    }
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
     * Called when the user clicks on the delete button.
     */
    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    void handleSupprimer() {
    	 int selectedIndex = GestionnaireTable.getSelectionModel().getSelectedIndex();
         if (selectedIndex >= 0) {
             GestionnaireTable.getItems().remove(selectedIndex);
             MethodeSQL.deleteDataGestionnaire(GestionnaireTable.getItems().remove(selectedIndex));
         } else {
             // Nothing selected.
             Alert alert = new Alert(AlertType.WARNING);
             alert.initOwner(mainApp.getPrimaryStage());
             alert.setTitle("Aucune selection");
             alert.setHeaderText("Aucun employé selectionné");
             alert.setContentText("S'il vous plait selectionnez un employé dans le tableau.");

             alert.showAndWait();
         }
    }

}

