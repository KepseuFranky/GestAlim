package GestAlim.view;


import com.jfoenix.controls.JFXTextField;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.time.LocalDate;

import GestAlim.control.MainApp;
import GestAlim.control.MethodeSQL;
import GestAlim.model.produit;

public class produitOverviewController {
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
    private TableView<produit> produitTable;
    @FXML
    private TableColumn<produit, String> NomProduitColumn;
    @FXML
    private TableColumn<produit, Integer> CodeProduitColumn;
    @FXML
    private TableColumn<produit, Integer> CodeFournisseurColumn;
    @FXML
    private TableColumn<produit, Double> QuantiteColumn;
    @FXML
    private TableColumn<produit, Double> PrixAchatColumn;
    @FXML
    private TableColumn<produit, Double> PrixVenteColumn;
    @FXML
    private TableColumn<produit, String> DateInsertionColumn;
    @FXML
    private TableColumn<produit, String> DatePeremptionColumn;
    private static ObservableList<produit> produitList = MainApp.produitData;
    @FXML
    private Label NomProduitLabel;
    @FXML
    private Label DescriptionLabel;
    @FXML
    private JFXTextField rechercher;
    @FXML
    private Pagination pagination = new Pagination((MainApp.produitData.size() / 18 + 1), 0);
    
    private Node createPage(int pageIndex) {

        int fromIndex = pageIndex * 18;
        int toIndex = Math.min(fromIndex + 18, MainApp.produitData.size());
        if(fromIndex<toIndex)
        	produitTable.setItems(FXCollections.observableArrayList(MainApp.produitData.subList(fromIndex, toIndex)));

        return new BorderPane(produitTable);
    }
    
    

    // Reference to the main application.
    private MainApp mainApp;
    @FXML
	private Label salutLabel;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public produitOverviewController() {
    }
    
    private void showproduitDescription(produit unproduit) {
        if (unproduit != null) {
            // Fill the labels with info from the person object.
            NomProduitLabel.setText(unproduit.getNomProduit());
            DescriptionLabel.setText(unproduit.getDescription());

            // TODO: We need a way to convert the birthday into a String!
            // birthdayLabel.setText(...);
        } else {
            // Person is null, remove all the text.
            NomProduitLabel.setText("");
            DescriptionLabel.setText("");
        }
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
        NomProduitColumn.setCellValueFactory(cellData -> cellData.getValue().NomProduitProperty());
        CodeProduitColumn.setCellValueFactory(cellData -> cellData.getValue().CodeProduitProperty().asObject());
        CodeFournisseurColumn.setCellValueFactory(cellData -> cellData.getValue().CodeFournisseurProperty().asObject());
        QuantiteColumn.setCellValueFactory(cellData -> cellData.getValue().QuantiteProperty().asObject());
        PrixAchatColumn.setCellValueFactory(cellData -> cellData.getValue().PrixAchatProperty().asObject());
        PrixVenteColumn.setCellValueFactory(cellData -> cellData.getValue().PrixVenteProperty().asObject());
        DateInsertionColumn.setCellValueFactory(cellData -> cellData.getValue().DateInsertionProperty());
        DatePeremptionColumn.setCellValueFactory(cellData -> cellData.getValue().DatePeremptionProperty());
        
        produitTable.setRowFactory(row->{
        	return new TableRow<produit>()
        	{
        		@Override
        		public void updateItem(produit item,boolean empty) {
        			super.updateItem(item, empty);
        			try {
        				boolean boll=item.getDatePeremption().minusMonths(3).isBefore(LocalDate.now());
        				//System.out.println(item.getDatePeremption().minusMonths(3));
            			boolean boll2=item.getDatePeremption().minusMonths(1).isBefore(LocalDate.now());
            			//System.out.println(item.getDatePeremption().minusMonths(1));
            			if (boll2) {
            				setStyle("-fx-background-color:RED;");
            			}
            			else if(boll) {
            				setStyle("-fx-background-color:ORANGE;");
            			}
            			else {
            				setStyle("");
            			}
        			}catch(Exception e) {
        				//e.printStackTrace();
        				//System.out.println("Erreur: "+e.getMessage());
        				
        			}
        			
        		}
        	};
        }
       
        		);

        // Clear produit details.
        showproduitDescription(null);
        //produitTable.
        // Listen for selection changes and show the person details when changed.
        produitTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showproduitDescription(newValue));
        produitList  = elementsListe();
        FilteredList<produit> filtre = new FilteredList<>(produitList,p->true);
        //System.out.println("Test2");
        rechercher.textProperty().addListener((ObservableValue<?extends String> observable, String oldValue, String newValue)->{
        	//System.out.println("Test".contains("T"));
            filtre.setPredicate(product->{
            	//System.out.println("hhh");
                if ((newValue == null) || newValue.isEmpty()){

                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (product.getNomProduit().toLowerCase().contains(lowerCaseFilter))
                {

                    return true;
                }
                if (Integer.toString(product.getCodeProduit()).toLowerCase().contains(lowerCaseFilter))
                {
                    return true;
                }
                if (Double.toString(product.getPrixAchat()).toLowerCase().contains(lowerCaseFilter))
                {
                    return true;
                }
                if (Double.toString(product.getPrixVente()).toLowerCase().contains(lowerCaseFilter))
                {
                    return true;
                }
                if (Double.toString(product.getQuantite()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                if (Integer.toString(product.getCodeFournisseur()).toLowerCase().contains(lowerCaseFilter))
                {
                    return true;
                }
               
                if (product.getDescription().contains(lowerCaseFilter))
                {
                    return true;
                }
                //System.out.println("Test");
                return  false;
            });
            SortedList <produit> sortedData = new SortedList<>(filtre);

            sortedData.comparatorProperty().bind(produitTable.comparatorProperty());
            produitTable.setItems(sortedData);
        });

        pagination.setPageFactory(this::createPage);
        
        
    }
    

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteproduit() {
        int selectedIndex = produitTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            produitTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Aucune Selection");
            alert.setHeaderText("Vous n'avez sélectionné aucun produit");
            alert.setContentText("Veuillez sélectionner un produit de la table.");

            alert.showAndWait();
        }
    }
    
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewproduit() {
        produit tempProduit = new produit();
        tempProduit.setCodeProduit(MethodeSQL.getMaxIdProduit()+1);
        boolean okClicked = mainApp.showproduitEditDialog(tempProduit);
        if (okClicked) {
        	
            mainApp.getproduitData().add(tempProduit);
            MethodeSQL.insertDataProduit(tempProduit);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected produit.
     */
    @FXML
    private void handleReductProduit() {
        produit selectedProduit = produitTable.getSelectionModel().getSelectedItem();
        if (selectedProduit != null) {
            boolean okClicked = mainApp.showproduitRetractDialog(selectedProduit);
            if (okClicked) {
                showproduitDescription(selectedProduit);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
    @FXML
    private void handleAjoutproduit() {
        produit selectedProduit = produitTable.getSelectionModel().getSelectedItem();
        if (selectedProduit != null) {
            boolean okClicked = mainApp.showproduitAjoutDialog(selectedProduit);
            if (okClicked) {
                showproduitDescription(selectedProduit);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

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
        produitTable.setItems(mainApp.getproduitData());
    }
    public ObservableList<produit> elementsListe()
    {
       
        return produitList;
    }
}