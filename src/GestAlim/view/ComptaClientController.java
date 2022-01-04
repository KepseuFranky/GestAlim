package GestAlim.view;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import GestAlim.control.MainApp;
import GestAlim.model.Facture;
import GestAlim.model.produit;
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
public class ComptaClientController {
	
    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis xAxis;

    private ObservableList<String> NomCli = FXCollections.observableArrayList();

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	// on veut avoir un Array avec les codes des produits.
    	List<Facture> factList = MainApp.dataFacture;
        
        
        String[] NomC = new String[factList.size()];
        for (int i = 0;i<NomC.length;i++)
        {
        	NomC[i]=factList.get(i).getNomClient();
        }
        // prodList.get(0).getCodeFournisseur()
        
        // Convert it to a list and add it to our ObservableList of NomP.
        NomCli.addAll(Arrays.asList(NomC));
        
        // Assign the month names as categories for the horizontal axis.
        xAxis.setCategories(NomCli);
    }

    /**
     * Sets the persons to show the statistics for.
     * 
     * @param persons
     */
    public void setClientxData(List<Facture> unefacture) {
    	
    	// On compte le nombre de produits vendus chaque mois
    	List<Facture> factList = MainApp.dataFacture;
        int[] montantCounter = new int[factList.size()];
       
        
        int k=0;
        for (Facture f : unefacture) {
        	//int month = p.getDateInsertion().getMonthValue();
            montantCounter[k]=(int) f.getMontant();
            k++;
        }
        
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        
        // Create a XYChart.Data object for each month. Add it to the series.
        for (int i = 0; i < montantCounter.length; i++) {
        	series.getData().add(new XYChart.Data<>(NomCli.get(i), montantCounter[i]));
        }
        
        barChart.getData().add(series);
    }
    
    
}