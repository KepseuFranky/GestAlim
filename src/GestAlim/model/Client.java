package GestAlim.model;

import GestAlim.control.MethodeSQL;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Client {
	private final IntegerProperty IdClient;
    private final StringProperty NomClient;
    private final IntegerProperty TelClient;
    private final StringProperty Adresse;
    private final IntegerProperty Bonus;
    
    
    /**
     * Default constructor.
     */
    public Client() {
    	//this();
        this(0," ", 0, " ", 0);
    }

	public Client(Integer IdClient,String NomClient, Integer TelClient, String Adresse, Integer Bonus) {
        this.NomClient = new SimpleStringProperty(NomClient);
        this.IdClient = new SimpleIntegerProperty(IdClient);
        this.TelClient = new SimpleIntegerProperty(TelClient);
        this.Adresse = new SimpleStringProperty(Adresse);
        this.Bonus = new SimpleIntegerProperty(Bonus);
    }
    
    public String getNomClient() {
        return NomClient.get();
    }

    public void setNomClient(String NomClient) {
        this.NomClient.set(NomClient);
    }
    
    public StringProperty NomClientProperty() {
        return NomClient;
    }
    
    public String getAdresse() {
        return Adresse.get();
    }

    public void setAdresse(String Adresse) {
        this.Adresse.set(Adresse);
    }
    
    public StringProperty AdresseProperty() {
        return Adresse;
    }

    public int getIdClient() {
        return IdClient.get();
    }

    public void setIdClient(int IdClient) {
        this.IdClient.set(IdClient);
    }
    
    public IntegerProperty IdClientProperty() {
        return IdClient;
    }
    
    public int getBonus() {
        return Bonus.get();
    }

    public void setBonus(int Bonus) {
        this.Bonus.set(Bonus);
    }
    
    public IntegerProperty BonusProperty() {
        return Bonus;
    }
    
    public int getTelClient() {
        return TelClient.get();
    }

    public void setTelClient(int TelClient) {
        this.TelClient.set(TelClient);
    }
    
    public IntegerProperty TelClientProperty() {
        return TelClient;
    }
}


