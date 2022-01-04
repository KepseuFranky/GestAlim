package GestAlim.model;

import GestAlim.control.MethodeSQL;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// Model class for Fournisseur
public class Fournisseur {
	
	
	private IntegerProperty idFournisseur;
    private StringProperty Name;
    private StringProperty Address;
    private StringProperty Tel;

    public Fournisseur(String nom) {
    	this.idFournisseur= new SimpleIntegerProperty(0);
        this.Name = new SimpleStringProperty(nom);
        this.Address= new SimpleStringProperty("");
        this.Tel= new SimpleStringProperty("");
    }

    public Fournisseur() { this(null); }
    
    public Fournisseur(int id, String name, String adress, String tel) {
    	this.idFournisseur= new SimpleIntegerProperty(id);
        this.Name = new SimpleStringProperty(name);
        this.Address= new SimpleStringProperty(adress);
        this.Tel= new SimpleStringProperty(tel);
    }
    
    
    public int getId() {return idFournisseur.get();}
    public void setId(int id) { this.idFournisseur.set(id);}
    public String getName() { return Name.get(); }
    public StringProperty nameProperty() { return Name; }
    public void setName(String name) { this.Name.set(name); }

    public String getAddress() { return Address.get(); }
    public StringProperty addressProperty() { return Address; }
    public void setAddress(String address) { this.Address.set(address); }

    public String getTel() { return Tel.get(); }
    public StringProperty telProperty() { return Tel; }
    public void setTel(String tel) { this.Tel.set(tel); }
    public IntegerProperty idProperty() {
    	return this.idFournisseur;
    }

}
