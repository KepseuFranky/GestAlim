package GestAlim.model;
import java.time.LocalDate;

import GestAlim.control.MethodeSQL;
import javafx.beans.property.*;

import java.time.LocalDate;

public class EvolutionClass {
    // Declaration des attributs
	private IntegerProperty idEvolution;
    private ProduitEvolution produit;
    private StringProperty operation;
    private StringProperty gestionnaire ;
    private final ObjectProperty<LocalDate> dateInsertion;
    private StringProperty motif;
    // constructeur
    public EvolutionClass( int id, ProduitEvolution produit ,String operation,String gestionnaire,String motif )
    {
    	this.idEvolution=new SimpleIntegerProperty(MethodeSQL.getMaxIdEvolution()+1);
       this.produit = produit;
        this.operation = new SimpleStringProperty(operation);
        this.gestionnaire = new SimpleStringProperty(gestionnaire);
        this.dateInsertion = new SimpleObjectProperty<LocalDate>(LocalDate.now());
        this.motif=new SimpleStringProperty(motif);
    }

    public EvolutionClass()
    {
        this(MethodeSQL.getMaxIdEvolution()+1,new ProduitEvolution(),"","","");
    }
    //getters et setters


    public void setProduit(ProduitEvolution produit) {
        this.produit = produit;
    }

    public void setOperation(String operation) {
        this.operation.set(operation);
    }

    public void setGestionnaire(String gestionnaire) {
        this.gestionnaire.set(gestionnaire);
    }

    public void setMotif(String motif) {
    	this.motif.set(motif);
    }

    public ProduitEvolution getProduit() {
        return produit;
    }

    public String getOperation() {
        return operation.get();
    }
    
    public String getMotif() {
        return motif.get();
    }
    
    public int getId() {
        return idEvolution.get();
    }

    public StringProperty operationProperty() {
        return operation;
    }
    
    public StringProperty motifProperty() {
        return motif;
    }

    public String getGestionnaire() {
        return gestionnaire.get();
    }

    public StringProperty gestionnaireProperty() {
        return gestionnaire;
    }

    public LocalDate getDateInsertion() {
        return dateInsertion.get();
    }

    public ObjectProperty<LocalDate> dateInsertionProperty() {
        return dateInsertion;
    }
}
