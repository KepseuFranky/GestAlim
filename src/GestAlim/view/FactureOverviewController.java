package GestAlim.view;

import GestAlim.control.MainApp;
import GestAlim.model.Facture;
import GestAlim.model.LigneFacture;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

import java.time.LocalDate;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

public class FactureOverviewController {

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
    private Label salutLabel;
    @FXML
    private TableView<Facture> factureTable;
    @FXML
    private TableColumn<Facture,Integer> idFacture;
    @FXML
    private TableColumn<Facture, LocalDate> DateColumn;
    @FXML
    private TableColumn<Facture, Double> MontantColumn;
    @FXML
    private TableColumn<Facture, Integer> CaissiereColumn;
    @FXML
    private TableColumn<Facture, String> ClientColumn;
    @FXML
    private JFXButton actualiser;
    @FXML
    private DatePicker startPicker = new DatePicker();
    @FXML
    private DatePicker endPicker = new DatePicker();
    @FXML
    private JFXTextField rechercher ; 
    @FXML
    private Label montant;
    @FXML
    private JFXButton rechercherProduit;
    @FXML
    private JFXTextField codeProduitRechercher;
    private MainApp mainApp;

    public FactureOverviewController() {}

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
    	idFacture.setCellValueFactory(cellData -> cellData.getValue().idFactureProperty().asObject());
        DateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        MontantColumn.setCellValueFactory(cellData -> cellData.getValue().montantProperty().asObject());
        CaissiereColumn.setCellValueFactory(cellData -> cellData.getValue().idCaissiereProperty().asObject());
        ClientColumn.setCellValueFactory(cellData -> cellData.getValue().nomClientProperty());
        
        montant.setText(""+this.calculTotal(factureTable.getItems()));
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp= mainApp;

        factureTable.setItems(mainApp.getFactureData());
    }
    public ObservableList<LigneFacture> recherchePro(int codeProduit,List<LigneFacture> lignesFactures)
    {
     ObservableList<LigneFacture>  newList = FXCollections.observableArrayList();
    for (LigneFacture ligne : lignesFactures)
     {
    	 if (ligne.getCodePro()==codeProduit)
    	 {
    		 newList.add(ligne);
    	 }
     }
     return newList;
    }
    // ici c'est pour rechercher les produits et afficher la page resultante
   
    public void setElementListe(LocalDate min,LocalDate max) {
  	  ObservableList newList = FXCollections.observableArrayList(); 
  	  for (Facture ele : mainApp.getFactureData()) 
  	  { 
  		  if (ele.getDate().isAfter(min)&&
    ele.getDate().isBefore(max)) 
  		  {
  			  newList.add(ele); 
  		  } 
  		  if (ele.getDate().isEqual(min)||ele.getDate().isEqual(max))
  		  {
  			  newList.add(ele);
  		  }
  		  
  	  }
  	  	//if (ele.getDate())
    factureTable.setItems(newList);
    montant.setText(Double.toString(calculTotal(newList)));
    
    }
    public double calculTotal(List<Facture> list)
    {
    	double montant = 0;
    	for (Facture f : list)
    	{
    		montant +=f.getMontant();
    	}
    	return montant;
    }
    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteFacture() {
        int selectedIndex= factureTable.getSelectionModel().getSelectedIndex();
        if(selectedIndex >=0) {
            factureTable.getItems().remove(selectedIndex);
        }
        else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle(" Aucun élément sélectionné ");
            alert.setHeaderText(" Aucune facture selectionnée ");
            alert.setContentText(" S'il vous plait, sélectionnez une facture dans le tableau. ");

            alert.showAndWait();
        }
    }

    /**
     * Called when the user clicks the show button. Opens a dialog to show
     * details for the selected facture.
     */
    @FXML
    private void handleShowFacture() {
        Facture selectedFacture = factureTable.getSelectionModel().getSelectedItem();
        if (selectedFacture != null) {
            boolean okClicked = mainApp.showFactureDialog(selectedFacture);
            //System.out.println("handleShowFacture");
        }
        else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle(" Aucun élément sélectionné ");
            alert.setHeaderText(" Aucune facture selectionnée ");
            alert.setContentText(" S'il vous plait, sélectionnez une facture dans le tableau. ");

            alert.showAndWait();
        }
    }
    public void rechercherDate() 
    {
  	  if (startPicker.getValue()!=null && endPicker.getValue()!=null)
  	  {
  		  setElementListe(startPicker.getValue(),endPicker.getValue()); 
  	  }
    }
    @FXML
    public void handleModifier() {
    	String codePro= rechercher.getText();
    	boolean b= false;
    	try {
    		int j= Integer.parseInt(codePro);
    		b= true;
    	}catch(Exception e) {
    		
    	}
        if ( b) {
        	
            mainApp.showProduitFactPage(codePro, startPicker.getValue(),endPicker.getValue(), factureTable.getItems());

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Error");
            alert.setHeaderText("Mauvaise Entr�e");
            alert.setContentText("S'il vous plait entrez un code valide!");

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
