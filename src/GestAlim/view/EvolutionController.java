package GestAlim.view;
import GestAlim.model.*;
import GestAlim.control.*;
import GestAlim.util.*;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ListIterator;

public class EvolutionController {
   // Declaration des attributs
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
    private JFXTextField rechercher;
    @FXML
    private TableView<EvolutionClass> tableEvolution;
    @FXML
    private TableColumn <EvolutionClass,String> codeProduit;
    @FXML
    private TableColumn<EvolutionClass,String> nomProduit;
    @FXML
    private TableColumn <EvolutionClass,String >gestionnaire;
    @FXML
    private TableColumn<EvolutionClass,String> quantite ;
   @FXML
    private TableColumn<EvolutionClass,String> action;
    @FXML
    private TableColumn <EvolutionClass, String> dateInsertion;
    @FXML
    private TableColumn <EvolutionClass, String> motif;
    
    private static ObservableList<EvolutionClass> listEvolutionClass = MainApp.listEvolutionClass;
    private static ObservableList<ProduitEvolution> produitData = FXCollections.observableArrayList();
    @FXML
    private DatePicker startPicker = new DatePicker(LocalDate.now());
     @FXML
     private DatePicker endPicker = new DatePicker(LocalDate.now());
    private MainApp main ;
    @FXML
	private Label salutLabel;

    // methode d'i nitialisation

    @FXML
    private  void initialize() {
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
        codeProduit.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getProduit().getCodeProduit())));
        nomProduit.setCellValueFactory(cellData -> cellData.getValue().getProduit().NomProduitProperty());
        gestionnaire.setCellValueFactory(cellData -> cellData.getValue().gestionnaireProperty());
        action.setCellValueFactory(cellData -> cellData.getValue().operationProperty());
        quantite.setCellValueFactory(cellData -> new SimpleStringProperty(Double.toString(cellData.getValue().getProduit().getQuantite())));
        dateInsertion.setCellValueFactory(cellData -> new SimpleStringProperty(DateUtil.format(cellData.getValue().getDateInsertion())));
        motif.setCellValueFactory(cellData -> cellData.getValue().motifProperty());
        listEvolutionClass  = elementsListe();
        FilteredList<EvolutionClass> filtre = new FilteredList<>(listEvolutionClass,p->true);
        // EvolutionClass evolutionClass;
       // System.out.println(main.getListEvolutionClass().size());
        // Set the filter predicate whenever the filter changes
        rechercher.textProperty().addListener((ObservableValue<?extends String> observable, String oldValue, String newValue)->{
           // System.out.println(evolution.size());
            filtre.setPredicate(evolutionClass->{
                if ((newValue == null) || newValue.isEmpty()){
                   // System.out.println("nonnn");
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
              //  System.out.println(lowerCaseFilter);
                if (evolutionClass.getProduit().getNomProduit().toLowerCase().contains(lowerCaseFilter))
                {
                //    System.out.println("produit");
                    return true;
                }
                if (Integer.toString(evolutionClass.getProduit().getCodeProduit()).toLowerCase().contains(lowerCaseFilter))
                {
                    return true;
                }
                if (Double.toString(evolutionClass.getProduit().getQuantite()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                if (evolutionClass.getGestionnaire().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                if (evolutionClass.getOperation().toLowerCase().contains(lowerCaseFilter))
                {
                    return true;
                }
                if (evolutionClass.getProduit().getDescription().contains(lowerCaseFilter))
                {
                    return true;
                }
              //  System.out.println("rien");
                return  false;
            });
            //System.out.println("apres rien");
            //System.out.println(filtre.size());
            SortedList <EvolutionClass> sortedData = new SortedList<>(filtre);

            sortedData.comparatorProperty().bind(tableEvolution.comparatorProperty());
            tableEvolution.setItems(sortedData);
          //  System.out.println(sortedData.size());

        });
        //System.out.println("je");
        //System.out.println(filtre.size());
       // SortedList <EvolutionClass> sortedData = new SortedList<>(filtre);

        //sortedData.comparatorProperty().bind(tableEvolution.comparatorProperty());
        //tableEvolution.setItems(sortedData);
        //System.out.println(sortedData.size());


}
    public void setMain(MainApp main ) {
        this.main = main;
        //evolution = main.getListEvolutionClass();
       tableEvolution.setItems(listEvolutionClass);
       // System.out.println( "oui");


    }
    public ObservableList<EvolutionClass> elementsListe()
    {
       // ObservableList<Produit> produitData = FXCollections.observableArrayList();
       
        return listEvolutionClass;
    }
    public static void addElementProduitEvolution(int id ,ProduitEvolution prod,String operation,String gestionnaire,String  motif)
    {
    	//produitData.add(prod);
    	listEvolutionClass.add(new EvolutionClass(id, prod,operation,gestionnaire,motif));
    	MethodeSQL.newEvolution(new EvolutionClass(id, prod,operation,gestionnaire,motif));
    }
    public void setElementListe(LocalDate min,LocalDate max) {
  	  ObservableList newList = FXCollections.observableArrayList(); for (EvolutionClass ele :
    listEvolutionClass) { if (ele.getDateInsertion().isAfter(min)&&
    ele.getDateInsertion().isBefore(max)) { newList.add(ele); } }
    tableEvolution.setItems(newList);
    
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
        	
            main.showProduitFactPage(codePro,null,null,null );

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("Error");
            alert.setHeaderText("Mauvaise Entrée");
            alert.setContentText("S'il vous plait entrez un code valide!");

            alert.showAndWait();
        }
    }
   public void actualiser()
   {
  	 tableEvolution.setItems(listEvolutionClass);
  	 initialize();
  	 startPicker.setValue(null);
  	 endPicker.setValue(null);
   }
      
   @FXML
   public void HandleAccueil() {
   	MainApp.showDebutApp();
   	
   }
   
   @FXML
   public void HandleFournisseur() {
   	if(MainApp.userActuel.getTypeEmployee()==1 || MainApp.userActuel.getTypeEmployee()==0) {
   		main.showFournisseurOverview();
   	}
   	
   }
   
   @FXML
   public void HandleProduit() {
   	if(MainApp.userActuel.getTypeEmployee()==1 || MainApp.userActuel.getTypeEmployee()==0) {
   		main.showproduitOverview();
   	}
   	
   }
   
   @FXML
   public void HandleFacturation() {
   	if(MainApp.userActuel.getTypeEmployee()==2 || MainApp.userActuel.getTypeEmployee()==0) {
   		main.showFacturation();
   	}
   	
   }
   
   @FXML
   public void HandleFacture() {
   	if(MainApp.userActuel.getTypeEmployee()==2 || MainApp.userActuel.getTypeEmployee()==0) {
   		main.showFactureOverview();
   	}
   	
   }
   
   @FXML
   public void HandleEvolution() {
   	if(MainApp.userActuel.getTypeEmployee()==3 || MainApp.userActuel.getTypeEmployee()==1 || MainApp.userActuel.getTypeEmployee()==0) {
   		main.showEvolutionPage();
   	}
   	
   }
   
   @FXML
   public void HandleEmploye() {
   	if(MainApp.userActuel.getTypeEmployee()==3 || MainApp.userActuel.getTypeEmployee()==0) {
   		main.showPersonOverview();
   	}
   	
   }
   
   @FXML
   public void HandleComptabilite() {
   	if(MainApp.userActuel.getTypeEmployee()==3 || MainApp.userActuel.getTypeEmployee()==0) {
			main.showCOmptaOverview();
   	}
   	
   }
   
   @FXML
   public void HandleOut() {
   	main.showLoginOverview();;
   }

}
