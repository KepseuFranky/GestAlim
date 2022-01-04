package GestAlim.view;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import GestAlim.control.MainApp;
import GestAlim.model.Facture;
import GestAlim.model.produit;
import GestAlim.view.produitOverviewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;


/**
 * The controller for the birthday statistics view.
 * 
 * @author Marco Jakob
 */
public class ComptaProduitController {
	
    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis xAxis;

    private ObservableList<String> NomPro = FXCollections.observableArrayList();
    private ObservableList<Integer> codesValues = FXCollections.observableArrayList();

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	
    	// on veut avoir un Array avec les codes des produits.
    	List<produit> prodList = MainApp.produitData;
        int[] codes = new int[prodList.size()];
        for (int i = 0;i<codes.length;i++)
        {
        	codes[i]=prodList.get(i).getCodeProduit();
        }
        
        String[] NomP = new String[prodList.size()];
        for (int i = 0;i<NomP.length;i++)
        {
        	NomP[i]=prodList.get(i).getNomProduit();
        }
        // prodList.get(0).getCodeFournisseur()
        
        // Convert it to a list and add it to our ObservableList of NomP.
        NomPro.addAll(Arrays.asList(NomP));
        
        // Assign the month names as categories for the horizontal axis.
        xAxis.setCategories(NomPro);
        
    }

    /**
     * Sets the persons to show the statistics for.
     * 
     * @param persons
     */
    public void setProduitData(List<produit> unproduit) {
    	
    	// On compte le nombre de produits vendus chaque mois
    	List<produit> prodList = MainApp.produitData;
        int[] produitCounter = new int[prodList.size()];
        
        
        int k=0;
        for (produit p : unproduit) {
        	//int month = p.getDateInsertion().getMonthValue();
            produitCounter[k]=(int) p.getQuantite();
            k++;
        }
        
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        
        // Create a XYChart.Data object for each month. Add it to the series.
        for (int i = 0; i < produitCounter.length; i++) {
        	series.getData().add(new XYChart.Data<>(NomPro.get(i), produitCounter[i]));
        }
        
        barChart.getData().add(series);
    }
    
}