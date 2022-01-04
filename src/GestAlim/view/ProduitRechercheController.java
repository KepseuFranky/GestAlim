package GestAlim.view;

import java.time.LocalDate;
import java.util.List;

import GestAlim.control.MainApp;
import GestAlim.model.Facture;
import GestAlim.model.LigneFacture;
import GestAlim.util.DateUtil;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ProduitRechercheController {
	@FXML
	private TableView<LigneFacture> tableProduitRecherche;
	@FXML
	private TableColumn<LigneFacture,Integer> codeFacture;
	@FXML
	private TableColumn<LigneFacture,String> nomProduit;
	@FXML 
	private TableColumn<LigneFacture,Double> quantite;
	@FXML
	private TableColumn<LigneFacture,Double> prixUnitaire;
	@FXML
	private TableColumn <LigneFacture,Double> total;
	@FXML 
	private Label totaux;
	private MainApp main;
	@FXML
    private Label codePro;
	private String codeProduit;
    private boolean okClicked = false;
    private Stage dialogStage;
	private static ObservableList<LigneFacture> listePro = FXCollections.observableArrayList();
	
	public boolean isOkClicked() {
        return okClicked;
    }

	public void setListProduit(String codePro, LocalDate deb, LocalDate fin, ObservableList<Facture> e)
	{
		int n =MainApp.dataLigneFacture.size();
		
		codeProduit=codePro;
		this.codePro.setText(codePro);
		if(e==null || e.size()==0) {
			for(int j=0; j<n; j++) {
				if(Integer.toString(MainApp.dataLigneFacture.get(j).getCodePro()).equals(codePro))
					listePro.addAll(MainApp.dataLigneFacture.get(j));
			}
		}else {
			int m=e.size();
			for(int i=0; i<m; i++) {
				for(int j=0; j<n; j++) {
					if(e.get(i).getIdFacture()==MainApp.dataLigneFacture.get(j).getIdLigne()) {
						if(Integer.toString(MainApp.dataLigneFacture.get(j).getCodePro()).equals(codePro))
							listePro.addAll(MainApp.dataLigneFacture.get(j));
					}
					
				}
			}
			
			
		}
		
		
		//this.codePro.setText(codePro);
	}
	 public void setMain(MainApp main ) {
	        //this.main = main;
	        //evolution = main.getListEvolutionClass();
	       tableProduitRecherche.setItems(listePro);
	 }
	 public void initialize()
	 {
		 this.codePro.setText(codeProduit);
		 //tableProduitRecherche.setItems(listePro);
		 codeFacture.setCellValueFactory(cellData -> (new SimpleIntegerProperty(cellData.getValue().getCodePro())).asObject());
		 nomProduit.setCellValueFactory(cellData-> (new SimpleStringProperty(cellData.getValue().getNomPro())));
		 quantite.setCellValueFactory(cellData->(new SimpleDoubleProperty(cellData.getValue().getQte()).asObject()));
		 prixUnitaire.setCellValueFactory(cellData->(new SimpleDoubleProperty(cellData.getValue().getPrixUnit()).asObject()));
		 total.setCellValueFactory(cellData->(new SimpleDoubleProperty(cellData.getValue().getPrixTotal())).asObject());
		 
		 totaux.setText(Double.toString(calculTotal(listePro)));
	 }
	 
	 public double calculTotal(List<LigneFacture> list)
	    {
	    	double montant = 0;
	    	for (LigneFacture f : list)
	    	{
	    		montant +=f.getPrixTotal();
	    	}
	    	return montant;
	    }
	 
	 public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	    }
}
