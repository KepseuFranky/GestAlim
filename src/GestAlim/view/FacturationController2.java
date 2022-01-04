package GestAlim.view;
import GestAlim.model.*;

import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import GestAlim.control.MainApp;
import GestAlim.control.MethodeSQL;
import GestAlim.control.PDFgenerator;
import GestAlim.model.Facture;
import GestAlim.model.FactureEnCours2;
import GestAlim.model.InfoFacture;
import GestAlim.model.LigneFacture;
import GestAlim.model.ProduitFact;
import GestAlim.model.produit;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class FacturationController2 {
	public FacturationController2() {
		
	}
	//Partie générale pour la page principale
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
    
    MainApp mainApp= new MainApp();
    @FXML
    private Label salutLabel;
    @FXML
    public void initialize() {
    	salutLabel.setText(" "+MainApp.userActuel.getNomEmployee()+"");
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
    public void HandleClient() {
    	if(MainApp.userActuel.getTypeEmployee()==2 || MainApp.userActuel.getTypeEmployee()==0) {
    		mainApp.showClientOverview();
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
    // Partie du controle véritable///////////////////////////////////////
    static ObservableList<FactureEnCours2> dataFactEnCours = MainApp.dataFactEnCours;
	static ObservableList<produit> dataProduit = MainApp.produitData;
    
    private static boolean tableVide=true;
	private double Total2=0;
	
    
	
	@FXML private TableView<FactureEnCours2> tableFactureEnCours ;
	@FXML private TableColumn<FactureEnCours2, Integer> ccodeProF;
	@FXML private TableColumn<FactureEnCours2, String> cnomPro;
	@FXML private TableColumn<FactureEnCours2, Double> cQuantite; 
	@FXML private TableColumn<FactureEnCours2, Double> cPrixUnit;
	@FXML private TableColumn<FactureEnCours2, Double> cPrixTotal;
	
	
	@FXML public Label Total;
	@FXML public Label NetPayer;
	
	@FXML private JFXButton OK;
	@FXML private JFXTextField Codepro;
	@FXML private JFXTextField QtePro;
	@FXML private JFXButton VALIDER;
	@FXML private JFXTextField nomClient;
	@FXML private JFXTextField Remise;
	@FXML private JFXTextField modePaiement;
    
	public void OK() {
		int i=0;
		Total2=0;

		if(isInputValid()) {
		/*while (i<=50) {*/
        tableVide=false;
		ccodeProF.setCellValueFactory(new PropertyValueFactory<FactureEnCours2, Integer> ("codePro"));
		cnomPro.setCellValueFactory(new PropertyValueFactory<FactureEnCours2, String> ("nomPro"));
		cQuantite.setCellValueFactory(new PropertyValueFactory<FactureEnCours2, Double> ("qte"));
		cPrixUnit.setCellValueFactory(new PropertyValueFactory<FactureEnCours2, Double>("prixUnit") );
		cPrixTotal.setCellValueFactory(new PropertyValueFactory<FactureEnCours2, Double> ("prixTotal"));
		dataFactEnCours.add( new FactureEnCours2(Integer.parseInt(Codepro.getText()),Integer.parseInt(QtePro.getText()) , dataProduit));
        tableFactureEnCours.setItems(dataFactEnCours);
        
        while(i<dataFactEnCours.size()) {
        	Total2=Total2+dataFactEnCours.get(i).getPrixTotal();
        	i++;
        }
        Total.setText(Total2+"");
        if(Remise.getText()!=null && !Remise.getText().equals("") ) {
        	NetPayer.setText((Total2-Double.parseDouble(Remise.getText())) +"");
        }
        else {
        	NetPayer.setText((Total2)+"");
        }
		}
	}
	public void setNetPayer() {
		if(Remise.getText()!=null && !Remise.getText().equals("") ) {
			NetPayer.setText((Total2-Double.parseDouble(Remise.getText())) +"");
		}
		else {
			NetPayer.setText((Total2)+"");
		}
	}
	
	public void handleSupprimer() {
   	 int selectedIndex = tableFactureEnCours.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
        	tableFactureEnCours.getItems().remove(selectedIndex);
        	dataFactEnCours.remove(selectedIndex);
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
	public void Clear() {

		/*while (i<=50) {*/
		dataFactEnCours.clear();
		ccodeProF.setCellValueFactory(new PropertyValueFactory<FactureEnCours2, Integer> ("codePro"));
		cnomPro.setCellValueFactory(new PropertyValueFactory<FactureEnCours2, String> ("nomPro"));
		cQuantite.setCellValueFactory(new PropertyValueFactory<FactureEnCours2, Double> ("qte"));
		cPrixUnit.setCellValueFactory(new PropertyValueFactory<FactureEnCours2, Double>("prixUnit") );
		cPrixTotal.setCellValueFactory(new PropertyValueFactory<FactureEnCours2, Double> ("prixTotal"));
        tableFactureEnCours.setItems(dataFactEnCours);
        tableVide=true;
        Total2=0;
        Total.setText(""+Total2+"");
	}
	
	private boolean isInputValid() {
		boolean check=true;
	    String errorMessage = "";
	    if (Codepro.getText() == null || Codepro.getText().length() == 0) {
	        errorMessage += "No valid Code produit!\n";
	    }
	    else {
	        // try to parse the postal code into an int.
	        try {
	            Integer.parseInt(Codepro.getText());
	        } catch (NumberFormatException e) {
	            errorMessage += "No valid code Produit !\n";
	            check=false;
	        }
	        if(check) {
		    	if(!(codeValide(Integer.parseInt(Codepro.getText())))){
		    		errorMessage += "No valid code Produit !\n";
		    	}
		    }
	    }
	    
	    
	    
	    if (QtePro.getText() == null || QtePro.getText().length() == 0) {
	        errorMessage += "No valid Quantité!\n";
	    }
	    else {
	        // try to parse the postal code into an int.
	        try {
	            Double.parseDouble(QtePro.getText());
	        } catch (NumberFormatException e) {
	            errorMessage += "No valid Qte (must be a double)!\n";
	        }
	    }
	    
	   	    	    
	    if (errorMessage.length() == 0) {
	        return true;
	    } else {
	        // Show the error message.
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Invalid Fields");
	        alert.setHeaderText("Please correct invalid fields");
	        alert.setContentText(errorMessage);

	        alert.showAndWait();

	        return false;
	    }
	}
	
	public boolean codeValide(int codePro) {
		int i=0;
		boolean valide=false;
		while(i<dataProduit.size()) {
			if(dataProduit.get(i).getCodeProduit()==codePro) {
				valide=true;
			}
			i++;
		}
		return valide;
	}
	
	Facture facture;// Pour contenir La facture en cours
	public void valider() throws DocumentException, IOException {
		String ListePro="";
		int i=0;
		if(!tableVide) {
			if(isInputValid2()) {
				//Enregistrement de la facture
				facture= new Facture(MethodeSQL.getMaxIdFacture()+1 ,Double.parseDouble(Remise.getText()), Double.parseDouble(Total.getText()), modePaiement.getText(), MainApp.userActuel.getIdEmployee(), nomClient.getText());
				MainApp.dataFacture.add(facture);
				MethodeSQL.insertFacture(facture);
				this.sortirPdf(facture.getIdFacture());
				//Enregistrement de la ligne de facture
				
				while(i<dataFactEnCours.size()) {
					LigneFacture ligne = new LigneFacture(MethodeSQL.getMaxIdLigneFacture()+1, facture.getIdFacture(), dataFactEnCours.get(i).getCodePro(),dataFactEnCours.get(i).getPrixUnit(),dataFactEnCours.get(i).getQte(), dataFactEnCours.get(i).getNomPro() );
					MainApp.dataLigneFacture.add(ligne);
					MethodeSQL.insertLigneFacture(ligne);
					i++;
				}
				
				//Modification des quantités dans la liste des produits même même
				i=0;
				while(i<dataFactEnCours.size()) {
					int j=0;
					while(j<dataProduit.size()) {
						if(dataProduit.get(j).getCodeProduit()==dataFactEnCours.get(i).getCodePro()) {
							dataProduit.get(j).setQuantite(dataProduit.get(j).getQuantite()-dataFactEnCours.get(i).getQte());
							// mise a jour de la quantite restante
							MethodeSQL.EditDataProduit(dataProduit.get(j));
							
						}
						j++;
					}
					i++;
				}
				MainApp.dataFactEnCours.clear();
				mainApp.showFacturation();
			}
			
		}else {
			
		}

		
	}
	
	private boolean isInputValid2() {
		boolean check=true;
	    String errorMessage = "";
	    
	    
	    if (nomClient.getText() == null ||nomClient.getText().length() == 0) {
	        errorMessage += "No valid Client name!\n";
	    }
	    else {
	        // try to parse the postal code into an int.
	        
	    }
	    if (modePaiement.getText() == null ||modePaiement.getText().length() == 0) {
	        errorMessage += "No valid paiement mode!\n";
	    }
	    if(Remise.getText() == null ||Remise.getText().length() == 0) {
	    	errorMessage += "No valid remise !\n";
	    }else {
	    	try {
	        	Integer.parseInt(Remise.getText());
	        } catch (NumberFormatException e) {
	            errorMessage += "No valid remise (must be a double)!\n";
	        }
	    }
	    
	   	    	    
	    if (errorMessage.length() == 0) {
	        return true;
	    } else {
	        // Show the error message.
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Invalid Fields");
	        alert.setHeaderText("Please correct invalid fields");
	        alert.setContentText(errorMessage);

	        alert.showAndWait();

	        return false;
	    }
	}
	// Visualiser la facture
	
	
	private Stage dialogStage;
	
	@FXML
	public void Visualiser() { // Bouton Visualiser la facture
		
		mainApp.showFactureView(new InfoFacture(nomClient.getText(), Remise.getText(), NetPayer.getText(), Total.getText()));
		//printFactureController.nomClient.setText("");
	}
	
	public void sortirPdf(int idFacture) throws DocumentException, IOException {
		//salutLabel.setText(""+MainApp.userActuel.getNomEmployee()+"");
		String text="";
    	text+="Nom Caissier= "+MainApp.userActuel.getNomEmployee()+"                         Id Client= "+nomClient.getText()+"                         N° Facture= "+facture.getNomClient();
		text+="\n ------------------------------------------------------------------------------------------------------";
        
    	text+="\n";
		  text+="| Code Pro | Nom du Produit                               | Qte     | Prix           | Total           |";
		  text+="\n";
		  text+=" ------------------------------------------------------------------------------------------------------ ";//105
		  text+="\n";
		  int i=0;
		  while(i<dataFactEnCours.size()) {
				String codeProduit=" "+dataFactEnCours.get(i).getCodePro();
				String nomProduit=" "+dataFactEnCours.get(i).getNomPro();
				String qteProduit=" "+dataFactEnCours.get(i).getQte();
				String prixProduitUnit=" "+dataFactEnCours.get(i).getPrixUnit();
				String prixTotalProduit=" "+dataFactEnCours.get(i).getPrixTotal();
				text+="|";
			  	text+=codeProduit;
			  	for(int j=1; j< 10-codeProduit.length()+1; j++) {
			  		text+=" ";
			  	}
			  	text+="|";
			  	
			  	text+=nomProduit;
			  	for(int j=1; j< 46-nomProduit.length()+1; j++) {
			  		text+=" ";
			  	}
			  	text+="|";
			  	
			  	text+=qteProduit;
			  	for(int j=1; j< 9-qteProduit.length()+1; j++) {
			  		text+=" ";
			  	}
			  	text+="|";
			  	
			  	text+=prixProduitUnit;
			  	for(int j=1; j< 16-prixProduitUnit.length()+1; j++) {
			  		text+=" ";
			  	}
			  	text+="|";
			  	
			  	text+=prixTotalProduit;
			  	for(int j=1; j< 17-prixTotalProduit.length()+1; j++) {
			  		text+=" ";
			  	}
			  	text+="|\n";
			  	
				i++;
			}
		  
		  text+=" ------------------------------------------------------------------------------------------------------ ";
		  text+="\nRemise= "+Remise.getText()+"                              Total= "+Total.getText()+"                              Net Payé= "+NetPayer.getText();
		  text+="\nLes marchandises vendues ne sont ni échangées, ni reprises. Merci pour vos achats.\n";
		  text+="\n";
		  String[] g=new String[2];
		  g[0]=Integer.toString(idFacture);
				  g[1]=text;
		  PDFgenerator.main( g );
	}
}
	

