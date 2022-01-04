package GestAlim.view;

import GestAlim.control.MainApp;
import GestAlim.model.Facture;
import GestAlim.model.LigneFacture;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.time.LocalDate;

public class FactureShowDialogController {
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
    public Label idFacture;
    @FXML
    public Label total;
    @FXML
    public Label netPayer;

    MainApp mainApp= new MainApp();
    @FXML
    private Label salutLabel;
    public ObservableList<Facture> dataFact = MainApp.dataFacture;
    public ObservableList<LigneFacture> dataLigneFact = MainApp.dataLigneFacture;
    public ObservableList<LigneFacture> lignesFactChoisie= FXCollections.observableArrayList();
    
    @FXML
    public void initialize() {
        //salutLabel.setText(""+MainApp.userActuel.getNomEmployee()+"");
    	//System.out.println("Initialize");
        

        date.setText(factureChoisie.getDate()+"");

    }
    public Facture factureChoisie= new Facture();
    
    public void setFacture(Facture info) {
    	
        this.factureChoisie=info;
        //System.out.println(factureChoisie.getIdFacture()+" Set Fact");
        
        //
        String text=" --------------------------------------------------------------------------------------------------------------------------";

        text+="\n";
        text+="| Code Pro | Nom du Produit                                                   | Qte     | Prix           | Total           |";
        text+="\n";
        text+=" -------------------------------------------------------------------------------------------------------------------------- ";
        text+="\n";
        int i=0;
        while(i<dataLigneFact.size()) {
        	//System.out.println(i+"1");
        	//System.out.println(this.factureChoisie.getIdFacture()+" Id ci");
        	if( dataLigneFact.get(i).getIdFacture()==this.factureChoisie.getIdFacture()) {
        		this.lignesFactChoisie.add(dataLigneFact.get(i));
        	}
        	i++;
        }
        i=0;
        //System.out.println(lignesFactChoisie.size()+" Lign CHois");
        while(i<lignesFactChoisie.size()) {
        	//System.out.println(i+"2");
            String codeProduit=" "+lignesFactChoisie.get(i).getCodePro();
            String nomProduit=" "+lignesFactChoisie.get(i).getNomPro();
            String qteProduit=" "+lignesFactChoisie.get(i).getQte();
            String prixProduitUnit=" "+lignesFactChoisie.get(i).getPrixUnit();
            String prixTotalProduit=" "+lignesFactChoisie.get(i).getPrixTotal();
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
        //
        factureData.setText(text);
        total.setText(Double.toString(factureChoisie.getMontant()));
        remise.setText(Double.toString(factureChoisie.getRemise()));
        netPayer.setText(Double.toString(factureChoisie.getMontant()-factureChoisie.getRemise()));
        nomClient.setText(factureChoisie.getNomClient());
        
        idFacture.setText(factureChoisie.getIdFacture()+"");
        
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    Stage dialogStage;
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

}
