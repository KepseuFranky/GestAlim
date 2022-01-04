package GestAlim.view;

import GestAlim.control.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;



/**
 * The controller for the birthday statistics view.
 * 
 * @author Marco Jakob
 */
public class ComptaOverviewController {
	
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
    private Label employeLab;
    @FXML
    private Label clientLab;
    @FXML
    private Label produitLab;
    @FXML
    private Label fournisseurLab;
    @FXML
    private Label VenteLab;
    MainApp mainApp= new MainApp();
    @FXML
    private Label salutLabel;
    @FXML
    public void initialize() {
    	salutLabel.setText(""+MainApp.userActuel.getNomEmployee()+"");
    	employeLab.setText(""+MainApp.employeeData.size());
    	clientLab.setText(""+MainApp.ClientData.size());
    	produitLab.setText(""+MainApp.produitData.size());
    	fournisseurLab.setText(""+MainApp.fournisseurData.size());
    	VenteLab.setText(""+this.getVente());
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
//    	}
    }
    @FXML
    private void handleFactureCompta() {
      mainApp.showComptaFacture();
    }
    
    @FXML
    private void handleProduitCompta() {
      mainApp.showComptaProduit();
    }
    
    @FXML
    private void handleClientCompta() {
      mainApp.showComptaClient();
    }
    
    @FXML
    private void handleVenteCompta() {
      mainApp.showComptaVente();
    }
    
    
    @FXML
    public void HandleAccueil() {
    	mainApp.showDebutApp();
    }
    @FXML
    public void HandleFacturation() {
    	mainApp.showFacturation();
    }
      
    @FXML
    public void HandleFournisseur() {
    	mainApp.showFournisseurOverview();
    }
    
    @FXML
    public void HandleProduit() {
    	mainApp.showproduitOverview();
    }
    
    @FXML
    public void HandleEmploye() {
    	mainApp.showPersonOverview();
    }
    @FXML
    public void HandleFacture() {
    	mainApp.showFactureOverview();
    }
    @FXML
    public void HandleEvolution() {
    	mainApp.showEvolutionPage();
    }
    @FXML
    public void HandleComptablilite() {
    	mainApp.showCOmptaOverview();;
    }
    @FXML
    public void HandleOut() {
    	mainApp.showLoginOverview();;
    }
    
    public void setMain(MainApp mainApp) {
        this.mainApp = mainApp;

    }
    
    public double getVente() {
    	int m= MainApp.dataFacture.size();
    	int j=0;
    	double total=0.0;
    	while(j<m) {
    		total+=MainApp.dataFacture.get(j).getMontant();
    		j++;
    	}
    	return total;
    }
}