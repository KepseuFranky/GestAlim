package GestAlim.model;

import java.time.LocalDate;
import GestAlim.util.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProduitEvolution {
	 private final StringProperty NomProduit;
     private final IntegerProperty CodeProduit;
     private final IntegerProperty CodeFournisseur;
     private final DoubleProperty Quantite;
     private final DoubleProperty PrixVente;
     private final DoubleProperty PrixAchat;
     private final ObjectProperty<LocalDate> DateInsertion;
     private final ObjectProperty<LocalDate> DatePeremption;
     private final StringProperty Description;
     private final IntegerProperty IdCategorie;
     /**
      * Default constructor.
      */
     public ProduitEvolution(produit pro)
     {
    	 NomProduit=pro.NomProduitProperty();
    	 CodeProduit = pro.CodeProduitProperty();
    	 CodeFournisseur= pro.CodeFournisseurProperty();
    	 Quantite = pro.QuantiteProperty();
    	 PrixVente = pro.PrixVenteProperty();
    	 PrixAchat = pro.PrixAchatProperty();
    	 DateInsertion = new SimpleObjectProperty<LocalDate>(DateUtil.parse(pro.DateInsertionProperty().get()));
    	 DatePeremption=new SimpleObjectProperty<LocalDate>(DateUtil.parse(pro.DatePeremptionProperty().get()));
    	 Description = pro.DescriptionProperty();
    	 IdCategorie=pro.IdCategorieProperty();
     }
     public ProduitEvolution(Integer CodeProduit,String NomProduit, Integer Quantite,  Object DateInsertion, Integer IdCategorie) {
         this.NomProduit = new SimpleStringProperty(NomProduit);
         this.CodeProduit = new SimpleIntegerProperty(CodeProduit);
         this.Quantite = new SimpleDoubleProperty(Quantite);
         this.PrixAchat = new SimpleDoubleProperty(0);
         this.PrixVente = new SimpleDoubleProperty(0);
         this.CodeFournisseur = new SimpleIntegerProperty(0);
         this.Description = new SimpleStringProperty("");
          
         this.DateInsertion = new SimpleObjectProperty<LocalDate>(LocalDate.now());
         this.DatePeremption = new SimpleObjectProperty<LocalDate>(LocalDate.now());
         this.IdCategorie = new SimpleIntegerProperty(IdCategorie);
     }
     public ProduitEvolution() {
         //this();
         this(0, " ", 0, 0, 0, 0, LocalDate.now(), LocalDate.now(), " ", 0);
     }
     public ProduitEvolution(Integer CodeProduit,String NomProduit, Integer Quantite, Integer PrixAchat, Integer PrixVente, Integer CodeFournisseur, Object DateInsertion, Object DatePeremption, String Description, int idCat) {
         this.NomProduit = new SimpleStringProperty(NomProduit);
         this.CodeProduit = new SimpleIntegerProperty(CodeProduit);
         this.Quantite = new SimpleDoubleProperty(Quantite);
         this.PrixAchat = new SimpleDoubleProperty(PrixAchat);
         this.PrixVente = new SimpleDoubleProperty(PrixVente);
         this.CodeFournisseur = new SimpleIntegerProperty(CodeFournisseur);
         // this.DateInsertion = new SimpleObjectProperty(DateInsertion);
         //this.DatePeremption = new SimpleObjectProperty(DatePeremption);
         this.Description = new SimpleStringProperty(Description);

         // Some initial dummy data, just for convenient testing.
     /*this.CodeFournisseur = new SimpleIntegerProperty(0000);
     this.Quantite = new SimpleIntegerProperty(00);
     this.PrixVente = new SimpleIntegerProperty(00);
     this.PrixAchat = new SimpleIntegerProperty(00);*/
         this.DateInsertion = new SimpleObjectProperty<LocalDate>(LocalDate.now());
         this.DatePeremption = new SimpleObjectProperty<LocalDate>(LocalDate.now());
         this.IdCategorie=new SimpleIntegerProperty(idCat);
     }

     public String getNomProduit() {
         return NomProduit.get();
     }

     public void setNomProduit(String NomProduit) {
         this.NomProduit.set(NomProduit);
     }

     public StringProperty NomProduitProperty() {
         return NomProduit;
     }

     public int getCodeProduit() {
         return CodeProduit.get();
     }

     public void setCodeProduit(int CodeProduit) {
         this.CodeProduit.set(CodeProduit);
     }

     public IntegerProperty CodeProduitProperty() {
         return CodeProduit;
     }

     public int getCodeFournisseur() {
         return CodeFournisseur.get();
     }

     public void setCodeFournisseur(int CodeFournisseur) {
         this.CodeFournisseur.set(CodeFournisseur);
     }

     public IntegerProperty CodeFournisseurProperty() {
         return CodeFournisseur;
     }

     public double getQuantite() {
         return Quantite.get();
     }
     
     public int getIdCategorie() {
         return IdCategorie.get();
     }

     public void setQuantite(int Quantite) {
         this.Quantite.set(Quantite);
     }

     public DoubleProperty QuantiteProperty() {
         return Quantite;
     }

     public double getPrixAchat() {
         return PrixAchat.get();
     }

     public void setPrixAchat(int PrixAchat) {
         this.PrixAchat.set(PrixAchat);
     }

     public DoubleProperty PrixAchatProperty() {
         return PrixAchat;
     }

     public double getPrixVente() {
         return PrixVente.get();
     }

     public void setPrixVente(int PrixVente) {
         this.PrixVente.set(PrixVente);
     }

     public DoubleProperty PrixVenteProperty() {
         return PrixVente;
     }

     public LocalDate getDateInsertion() {
         return DateInsertion.get();
     }

     public void setDateInsertion(LocalDate DateInsertion) {
         this.DateInsertion.set(DateInsertion);
     }

     /*public ObjectProperty<LocalDate> DateInsertionProperty() {
         return DateInsertion;
     }*/
     public StringProperty DateInsertionProperty() {
         StringProperty DateInsertionProduit = new SimpleStringProperty(DateUtil.format(DateInsertion.get()));
         return DateInsertionProduit;
     }



     public LocalDate getDatePeremption() {
         return DatePeremption.get();
     }

     public void setDatePeremption(LocalDate DatePeremption) {
         this.DatePeremption.set(DatePeremption);
     }

     /* public ObjectProperty<LocalDate> DatePeremptionProperty() {
          return DatePeremption;
      }*/
     public StringProperty DatePeremptionProperty() {
         StringProperty DatePeremptionProduit = new SimpleStringProperty(DateUtil.format(DatePeremption.get()));
         return DatePeremptionProduit;
     }

     public String getDescription() {
         return Description.get();
     }

     public void setDescription(String Description) {
         this.Description.set(Description);
     }

     public StringProperty DescriptionProperty() {
         return Description;
     }


}
