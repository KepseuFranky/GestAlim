package GestAlim.view;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import GestAlim.model.Facture;
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
public class ComptaVenteController {
	
    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis xAxis;

    private ObservableList<String> monthNames = FXCollections.observableArrayList();

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Get an array with the English month names.
        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        // Convert it to a list and add it to our ObservableList of months.
        monthNames.addAll(Arrays.asList(months));
        
        // Assign the month names as categories for the horizontal axis.
        xAxis.setCategories(monthNames);
    }

    /**
     * Sets the persons to show the statistics for.
     * 
     * @param persons
     */
    public void setVenteData(List<Facture> unefacture) {
    	// Count the number of people having their birthday in a specific month.
        int[] venteCounter = new int[12];
        for(int z=0; z<12; z++) {
        	venteCounter[z]=0;
        }
        for (Facture f : unefacture) {
            int month = f.getDate().getMonthValue() - 1;
            venteCounter[month]=(int) (venteCounter[month] + f.getMontant());

        }

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        
        // Create a XYChart.Data object for each month. Add it to the series.
        for (int i = 0; i < venteCounter.length; i++) {
        	series.getData().add(new XYChart.Data<>(monthNames.get(i), venteCounter[i]));
        }
        
        barChart.getData().add(series);
    }
    
}