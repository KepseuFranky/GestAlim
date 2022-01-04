package GestAlim.model;

import java.time.LocalDate;

import GestAlim.control.MethodeSQL;
import GestAlim.util.DateUtil;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class produit {
	
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
    public produit() {
    	//this();
        this(MethodeSQL.getMaxIdProduit()+1, "", 0, 0, 0, 0, LocalDate.now(), LocalDate.of(2021, 01, 01), "",0);
    }
    public produit(Integer CodeProduit,String NomProduit, Integer Quantite, Integer PrixAchat, Integer PrixVente, Integer CodeFournisseur, LocalDate DateInsertion, LocalDate DatePeremption, String Description,Integer IdCategorie) {
        this.NomProduit = new SimpleStringProperty(NomProduit);
        this.CodeProduit = new SimpleIntegerProperty(MethodeSQL.getMaxIdProduit()+1);
        this.Quantite = new SimpleDoubleProperty(Quantite);
        this.PrixAchat = new SimpleDoubleProperty(PrixAchat);
        this.PrixVente = new SimpleDoubleProperty(PrixVente);
        this.CodeFournisseur = new SimpleIntegerProperty(CodeFournisseur);
        this.Description = new SimpleStringProperty(Description);
         
        this.DateInsertion = new SimpleObjectProperty<LocalDate>(DateInsertion);
        this.DatePeremption = new SimpleObjectProperty<LocalDate>(DatePeremption);
        this.IdCategorie = new SimpleIntegerProperty(IdCategorie);
    }
    
    public produit(produit p) {
        this.NomProduit = (p.NomProduit);
        this.CodeProduit = (p.CodeProduit);
        this.Quantite = new SimpleDoubleProperty(0.0);
        this.PrixAchat = (p.PrixAchat);
        this.PrixVente = (p.PrixVente);
        this.CodeFournisseur = (p.CodeFournisseur);
        this.Description = (p.Description);
         
        this.DateInsertion = (p.DateInsertion);
        this.DatePeremption = (p.DatePeremption);
        this.IdCategorie = (p.IdCategorie);
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

    public void setQuantite(double Quantite) {
        this.Quantite.set(Quantite);
    }
    
    public DoubleProperty QuantiteProperty() {
        return Quantite;
    }
    
    public double getPrixAchat() {
        return PrixAchat.get();
    }

    public void setPrixAchat(double PrixAchat) {
        this.PrixAchat.set(PrixAchat);
    }
    
    public DoubleProperty PrixAchatProperty() {
        return PrixAchat;
    }

    public double getPrixVente() {
        return PrixVente.get();
    }

    public void setPrixVente(double PrixVente) {
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
    
    public int getIdCategorie() {
        return IdCategorie.get();
    }

    public void setIdCategorie(int IdCategorie) {
        this.IdCategorie.set(IdCategorie);
    }
    
    public IntegerProperty IdCategorieProperty() {
        return IdCategorie;
    }
}

