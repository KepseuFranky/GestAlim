package GestAlim.control;

import java.io.IOException;
import java.time.LocalDate;

import GestAlim.model.Facture;
import GestAlim.model.FactureEnCours2;
import GestAlim.model.Fournisseur;
import GestAlim.model.Gestionnaire;
import GestAlim.model.InfoFacture;
import GestAlim.model.LigneFacture;
import GestAlim.model.produit;
import GestAlim.view.ProduitRechercheController;
import GestAlim.view.FournisseurEditDialogController;
import GestAlim.view.FournisseurOverviewController;
import GestAlim.view.produitAjoutDialogController;
import GestAlim.view.produitEditDialogController;
import GestAlim.view.produitOverviewController;
import GestAlim.view.produitRetractDialogController;
import GestAlim.control.MainApp;
import GestAlim.view.ClientEditDialogController;
import GestAlim.view.ClientOverviewController;
import GestAlim.view.ComptaClientController;
import GestAlim.view.ComptaFactureController;
import GestAlim.view.ComptaOverviewController;
import GestAlim.view.ComptaProduitController;
import GestAlim.view.ComptaVenteController;
import GestAlim.view.EvolutionController;
import GestAlim.model.Client;
import GestAlim.model.EvolutionClass;
import GestAlim.view.FactureOverviewController;
import GestAlim.view.FactureShowDialogController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {
	private  Stage primaryStage;
	private static BorderPane rootLayout;
	
	public static ObservableList<Gestionnaire> employeeData = FXCollections.observableArrayList();
	public static ObservableList<Fournisseur> fournisseurData= FXCollections.observableArrayList();
	public static ObservableList<produit> produitData = FXCollections.observableArrayList();

    public static ObservableList<EvolutionClass> listEvolutionClass = FXCollections.observableArrayList();
	public static ObservableList<Client> ClientData = FXCollections.observableArrayList();
	public static ObservableList<FactureEnCours2> dataFactEnCours = FXCollections.observableArrayList();
	public static ObservableList<Facture> dataFacture = FXCollections.observableArrayList();
	public static ObservableList<LigneFacture> dataLigneFacture = FXCollections.observableArrayList();
	
	public static Gestionnaire userActuel= new Gestionnaire();
	
	public boolean controlInput(Gestionnaire input) {
    	if(!employeeData.isEmpty()) {
    		for(Gestionnaire utilisateur: employeeData) {
        		if(utilisateur.sameEmployee(input)) {
        			userActuel=utilisateur;
        			return true;
        		}
        	}
    	}
    	return false;
    }
	public MainApp() {
		
        
	}
	
	 public ObservableList<Fournisseur> getFournisseurData() {
	        return fournisseurData;
	    }
	 public ObservableList<produit> getproduitData() {
	        return produitData;
	    }
	 public ObservableList<Gestionnaire> getPersonData() {
	        return employeeData;
	    }
	 public ObservableList<Facture> getFactureData() {
	        return dataFacture;
	    }
	 public ObservableList<Client> getClientData() {
	        return ClientData;
	    }
	 public Stage getPrimaryStage() { return primaryStage; }
	 public  void initRootLayout() {
	        try {
	        	
	        	
	        	
	        	employeeData.add(new Gestionnaire(0, "BigBoss", 1, "root" , "root", 1));
	    		
	        	// Load root layout from fxml file.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("../view/RootLayout.fxml"));
	            rootLayout = (BorderPane) loader.load();

	            
	            // Show the scene containing the root layout.
	            Scene scene = new Scene(rootLayout);
	            primaryStage.centerOnScreen();
	            primaryStage.setScene(scene);
	            primaryStage.centerOnScreen();
	           
	            primaryStage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
		       
	        
	        ////////////////////////////connexion à la bd///////////
	        
	}
	public void showLoginOverview() {
	       try {
	        	MethodeSQL.RemplissageListeGestionnaire();
	        	
	            // Load person overview.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("../view/LoginOverview.fxml"));
	            AnchorPane loginPageOverview = (AnchorPane) loader.load();

	            // Set person overview into the center of root layout.
	            rootLayout.setCenter(loginPageOverview);

	            

	       } catch (IOException e) {
	            e.printStackTrace();
	       }
	}
	
	public void showCreationEmployeOverview() {
	       try {
	        	
	            // Load person overview.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("../view/Inscription.fxml"));
	            AnchorPane loginPageOverview = (AnchorPane) loader.load();

	            // Set person overview into the center of root layout.
	            rootLayout.setCenter(loginPageOverview);

	            

	       } catch (IOException e) {
	            e.printStackTrace();
	       }
	}
	
	public static void showDebutApp() {
	       try {
	        	
	            // Load person overview.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("../view/debutApp.fxml"));
	            AnchorPane loginPageOverview = (AnchorPane) loader.load();

	            // Set person overview into the center of root layout.
	            rootLayout.setCenter(loginPageOverview);

	            

	       } catch (IOException e) {
	            e.printStackTrace();
	       }
	}
	
	public void showFacturation() {
	       try {
	        	MethodeSQL.RemplissageListeProduit();
	        	MethodeSQL.selectAllClient();
	            // Load person overview.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("../view/Facturation2.fxml"));
	            AnchorPane loginPageOverview = (AnchorPane) loader.load();

	            // Set person overview into the center of root layout.
	            rootLayout.setCenter(loginPageOverview);

	            

	       } catch (IOException e) {
	            e.printStackTrace();
	       }
	}
	public void showFournisseurOverview() {
        try{
        	MethodeSQL.selectAllFournisseur();
            // Load fournisseur overview
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/FournisseurOverview.fxml"));
            AnchorPane fournisseurOverview= loader.load();

            // Set fournisseur overview into the center of root layout
            rootLayout.setCenter(fournisseurOverview);

            // Give the controller access to the main app
            FournisseurOverviewController controller= loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	public boolean showFournisseurEditDialog(Fournisseur fournisseur) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/FournisseurEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Modifier un fournisseur");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            FournisseurEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setFournisseur(fournisseur);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
	 public void showproduitOverview() {
	        try {
	        	MethodeSQL.RemplissageListeProduit();
	            // Load produit overview.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("../view/produitOverview.fxml"));
	            AnchorPane produitOverview = (AnchorPane) loader.load();
	            
	            // Set produit overview into the center of root layout.
	            rootLayout.setCenter(produitOverview);
	            
	            // Give the controller access to the main app.
	            produitOverviewController controller = loader.getController();
	            controller.setMainApp(this);
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 public boolean showproduitEditDialog(produit unproduit) {
	        try {
	            // Load the fxml file and create a new stage for the popup dialog.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("../view/produitEditDialog.fxml"));
	            AnchorPane page = (AnchorPane) loader.load();

	            // Create the dialog Stage.
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("Edit Produit");
	            dialogStage.initModality(Modality.WINDOW_MODAL);
	            dialogStage.initOwner(primaryStage);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);

	            // Set the person into the controller.
	            produitEditDialogController controller = loader.getController();
	            controller.setDialogStage(dialogStage);
	            controller.setproduit(unproduit);

	            // Show the dialog and wait until the user closes it
	            dialogStage.showAndWait();

	            return controller.isOkClicked();
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	 
	 public boolean showproduitRetractDialog(produit unproduit) {
	        try {
	            // Load the fxml file and create a new stage for the popup dialog.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("../view/produitRetractDialog.fxml"));
	            AnchorPane page = (AnchorPane) loader.load();

	            // Create the dialog Stage.
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("Retirer Produit");
	            dialogStage.initModality(Modality.WINDOW_MODAL);
	            dialogStage.initOwner(primaryStage);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);

	            // Set the person into the controller.
	            produitRetractDialogController controller = loader.getController();
	            controller.setDialogStage(dialogStage);
	            controller.setproduit(unproduit);

	            // Show the dialog and wait until the user closes it
	            dialogStage.showAndWait();

	            return controller.isOkClicked();
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	 
	 public boolean showproduitAjoutDialog(produit unproduit) {
	        try {
	            // Load the fxml file and create a new stage for the popup dialog.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("../view/produitAjoutDialog.fxml"));
	            AnchorPane page = (AnchorPane) loader.load();

	            // Create the dialog Stage.
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("Ajouter Produit");
	            dialogStage.initModality(Modality.WINDOW_MODAL);
	            dialogStage.initOwner(primaryStage);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);

	            // Set the person into the controller.
	            produitAjoutDialogController controller = loader.getController();
	            controller.setDialogStage(dialogStage);
	            controller.setproduit(unproduit);

	            // Show the dialog and wait until the user closes it
	            dialogStage.showAndWait();

	            return controller.isOkClicked();
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	 
	 public void showPersonOverview() {
	        try {
	        	MethodeSQL.RemplissageListeGestionnaire();
	            // Load person overview.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("../view/EmployeOverview.fxml"));
	            AnchorPane personOverview = (AnchorPane) loader.load();

	            // Set person overview into the center of root layout.
	            rootLayout.setCenter(personOverview);

	            // Give the controller access to the main app.
	            GestAlim.view.EmployeViewController controller = loader.getController();
	            controller.setMainApp(this);

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 public boolean showPersonEditDialog(Gestionnaire person) {
	        try {
	            // Load the fxml file and create a new stage for the popup dialog.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("../view/EmployeEdit.fxml"));
	            AnchorPane page = (AnchorPane) loader.load();

	            // Create the dialog Stage.
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("Modifier...");
	            dialogStage.initModality(Modality.WINDOW_MODAL);
	            dialogStage.initOwner(primaryStage);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);

	            // Set the person into the controller.
	            GestAlim.view.EmployeEditController controller = loader.getController();
	            controller.setDialogStage(dialogStage);
	            controller.setPerson(person);

	            // Show the dialog and wait until the user closes it
	            dialogStage.showAndWait();

	            return controller.isOkClicked();
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	 
	
	 public void showFactureView(InfoFacture info) {
	        try {
	            // Load the fxml file and create a new stage for the popup dialog.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("../view/FactureViewForFacturation.fxml"));
	            AnchorPane page = (AnchorPane) loader.load();

	            // Create the dialog Stage.
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("Visualisation Facture");
	            dialogStage.initModality(Modality.WINDOW_MODAL);
	            dialogStage.initOwner(primaryStage);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);

	            // Set the person into the controller.
	            GestAlim.view.printFactureController controller = loader.getController();
	            controller.setDialogStage(dialogStage);
	            controller.setInfo(info);
	            // Show the dialog and wait until the user closes it
	            dialogStage.showAndWait();

	            
	        } catch (IOException e) {
	            e.printStackTrace();
	            
	        }
	    }
	 public void showFactureOverview() {
	        try{
	        	MethodeSQL.selectAllFacture();
	        	MethodeSQL.selectAllLigneFacture();
	            // Load facture overview
	            FXMLLoader loader= new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("../view/FactureOverview.fxml"));
	            AnchorPane factureOverview= loader.load();

	            // Set facture overview into the center of root layout
	            rootLayout.setCenter(factureOverview);

	            // Give the controller access to the main app
	            FactureOverviewController controller= loader.getController();
	            controller.setMainApp(this);

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public boolean showFactureDialog(Facture facture) {
	        try {
	            // Load the fxml file and create a new stage for the popup dialog.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("../view/FactureView.fxml"));
	            AnchorPane page = (AnchorPane) loader.load();

	            // Create the dialog Stage.
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("Visualiser une facture");
	            dialogStage.initModality(Modality.WINDOW_MODAL);
	            dialogStage.initOwner(primaryStage);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);

	            // Set the person into the controller.
	            FactureShowDialogController controller = loader.getController();
	            controller.setDialogStage(dialogStage);
	            controller.setFacture(facture);

	            // Show the dialog and wait until the user closes it
	            dialogStage.showAndWait();

	            return true;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    public void showClientOverview() {
	        try {
	        	MethodeSQL.selectAllClient();
	            // Load client overview.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("../view/ClientOverview.fxml"));
	            AnchorPane ClientOverview = (AnchorPane) loader.load();
	            
	            // Set client overview into the center of root layout.
	            rootLayout.setCenter(ClientOverview);
	            
	            // Give the controller access to the main app.
	            ClientOverviewController controller = loader.getController();
	            controller.setMainApp(this);
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 public boolean showClientEditDialog(Client unclient) {
	        try {
	            // Load the fxml file and create a new stage for the popup dialog.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("../view/ClientEditDialog.fxml"));
	            AnchorPane page = (AnchorPane) loader.load();

	            // Create the dialog Stage.
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("Edit Client");
	            dialogStage.initModality(Modality.WINDOW_MODAL);
	            dialogStage.initOwner(primaryStage);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);

	            // Set the person into the controller.
	            ClientEditDialogController controller = loader.getController();
	            controller.setDialogStage(dialogStage);
	            controller.setClient(unclient);

	            // Show the dialog and wait until the user closes it
	            dialogStage.showAndWait();

	            return controller.isOkClicked();
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	 public void showEvolutionPage()
	 {
		 try{
			 MethodeSQL.selectAllEvolutiuon();
			 
	            // Load fournisseur overview
	            FXMLLoader loader= new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("../view/EvolutionView.fxml"));
	            AnchorPane EvolutionOverview= loader.load();

	            // Set fournisseur overview into the center of root layout
	            rootLayout.setCenter(EvolutionOverview);

	            // Give the controller access to the main app
	            EvolutionController controller= loader.getController();
	            controller.setMain(this);

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
 
	 }
	 
	 public void showCOmptaOverview()
	 {
		 try{
	            // Load fournisseur overview
	            FXMLLoader loader= new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("../view/ComptaOverview.fxml"));
	            AnchorPane ComptaOverview= loader.load();

	            // Set fournisseur overview into the center of root layout
	            rootLayout.setCenter(ComptaOverview);

	            // Give the controller access to the main app
	            ComptaOverviewController controller= loader.getController();
	            controller.setMain(this);

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
 
	 }
	 
	 
	 public void showComptaFacture() {
		    try {
		        // Load the fxml file and create a new stage for the popup.
		        FXMLLoader loader = new FXMLLoader();
		        loader.setLocation(MainApp.class.getResource("../view/ComptaFacture.fxml"));
		        AnchorPane page = (AnchorPane) loader.load();
		        Stage dialogStage = new Stage();
		        dialogStage.setTitle("Statistiques des Factures");
		        dialogStage.initModality(Modality.WINDOW_MODAL);
		        dialogStage.initOwner(primaryStage);
		        Scene scene = new Scene(page);
		        dialogStage.setScene(scene);

		        // Set the persons into the controller.
		        ComptaFactureController controller = loader.getController();
		        controller.setFactureData(dataFacture);

		        dialogStage.show();

		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
	 
	 public void showComptaProduit() {
		    try {
		        // Load the fxml file and create a new stage for the popup.
		        FXMLLoader loader = new FXMLLoader();
		        loader.setLocation(MainApp.class.getResource("../view/ComptaProduit.fxml"));
		        AnchorPane page = (AnchorPane) loader.load();
		        Stage dialogStage = new Stage();
		        dialogStage.setTitle("Statistiques des Produits");
		        dialogStage.initModality(Modality.WINDOW_MODAL);
		        dialogStage.initOwner(primaryStage);
		        Scene scene = new Scene(page);
		        dialogStage.setScene(scene);

		        // Set the persons into the controller.
		        ComptaProduitController controller = loader.getController();
		        controller.setProduitData(produitData);

		        dialogStage.show();

		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
	 public void showComptaClient() {
		    try {
		        // Load the fxml file and create a new stage for the popup.
		        FXMLLoader loader = new FXMLLoader();
		        loader.setLocation(MainApp.class.getResource("../view/ComptaClient.fxml"));
		        AnchorPane page = (AnchorPane) loader.load();
		        Stage dialogStage = new Stage();
		        dialogStage.setTitle("Statistiques des Clients");
		        dialogStage.initModality(Modality.WINDOW_MODAL);
		        dialogStage.initOwner(primaryStage);
		        Scene scene = new Scene(page);
		        dialogStage.setScene(scene);

		        // Set the persons into the controller.
		        ComptaClientController controller = loader.getController();
		        controller.setClientxData(dataFacture);

		        dialogStage.show();

		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}

	 public void showComptaVente() {
		    try {
		        // Load the fxml file and create a new stage for the popup.
		        FXMLLoader loader = new FXMLLoader();
		        loader.setLocation(MainApp.class.getResource("../view/ComptaVente.fxml"));
		        AnchorPane page = (AnchorPane) loader.load();
		        Stage dialogStage = new Stage();
		        dialogStage.setTitle("Statistiques des Ventes");
		        dialogStage.initModality(Modality.WINDOW_MODAL);
		        dialogStage.initOwner(primaryStage);
		        Scene scene = new Scene(page);
		        dialogStage.setScene(scene);

		        // Set the persons into the controller.
		        ComptaVenteController controller = loader.getController();
		        controller.setVenteData(dataFacture);

		        dialogStage.show();

		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
	 public boolean showProduitFactPage(String codePro, LocalDate deb, LocalDate fin, ObservableList el)
	 {
		 try{
	            // Load fournisseur overview
	            FXMLLoader loader= new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("../view/produitRechercheFactureOverView.fxml"));

	            AnchorPane page = (AnchorPane) loader.load();

	         // Create the dialog Stage.
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("Produit Vendu");
	            dialogStage.initModality(Modality.WINDOW_MODAL);
	            dialogStage.initOwner(primaryStage);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);

	            // Set the person into the controller.
	            ProduitRechercheController controller = loader.getController();
	            controller.setDialogStage(dialogStage);
	            
	            controller.setListProduit(codePro, deb, fin, el);
	            controller.setMain(this);
	            // Show the dialog and wait until the user closes it
	            dialogStage.showAndWait();

	            return controller.isOkClicked();
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	 }

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("GestAlim");
        //this.primaryStage.getIcons().add(new Image("file:lib/BON.jpg"));
        initRootLayout();
        MethodeSQL.RemplissageListeGestionnaire();
        MethodeSQL.RemplissageListeProduit();
        MethodeSQL.selectAllClient();
        MethodeSQL.selectAllEvolutiuon();
        MethodeSQL.selectAllFacture();
        MethodeSQL.selectAllFournisseur();
        MethodeSQL.selectAllLigneFacture();
       showLoginOverview();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
