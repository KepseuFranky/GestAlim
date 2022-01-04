package GestAlim.view;

import java.time.LocalDate;

import GestAlim.control.MainApp;
import GestAlim.model.FactureEnCours2;
import GestAlim.model.InfoFacture;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class printFactureController {
	
    @FXML
    private Label factureData;
    @FXML
    private Label nomCaissier;
    @FXML
    public Label nomClient;
    @FXML
    public Label date;
    @FXML
    public Label remise;
    @FXML
    public Label total;
    @FXML
    public Label netPayer;
    
    MainApp mainApp= new MainApp();
    @FXML
    private Label salutLabel;
    
    static ObservableList<FactureEnCours2> dataFactEnCours = MainApp.dataFactEnCours;
    
    @FXML
    public void initialize() {
    	//salutLabel.setText(""+MainApp.userActuel.getNomEmployee()+"");
    	String text=" --------------------------------------------------------------------------------------------------------------------------";
        
    	text+="\n";
		  text+="| Code Pro | Nom du Produit                                                   | Qte     | Prix           | Total           |";
		  text+="\n";
		  text+=" -------------------------------------------------------------------------------------------------------------------------- ";
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
			  	for(int j=1; j< 66-nomProduit.length()+1; j++) {
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
		  text+=" -------------------------------------------------------------------------------------------------------------------------- ";
		  text+="\n";
		  
		  factureData.setText(text);
		  date.setText(LocalDate.now()+"");
		  nomCaissier.setText(MainApp.userActuel.getNomEmployee());
		 
    }
    InfoFacture info= new InfoFacture();
    public void setInfo(InfoFacture info) {
    	this.info=info;
    	total.setText(info.getTotal());
    	remise.setText(info.getRemise());
    	netPayer.setText(info.getNet());
    	nomClient.setText(info.getNomClient());
    }

	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    Stage dialogStage;
	public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    
}
