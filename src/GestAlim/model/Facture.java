package GestAlim.model;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import GestAlim.control.MethodeSQL;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Facture {
	private final IntegerProperty idFacture;//TODO: affecter la valeur de l'id avec les methodes SQL
	private final ObjectProperty<LocalDate> date;
	private final DoubleProperty remise;
	private final DoubleProperty montant;
	private final StringProperty modePaiement;
	private final IntegerProperty idCaissiere;
	private final StringProperty nomClient;
	private static int nombre=0;
	public Facture(int id, double remise, double montant, String modePaiement,int idCaissiere, String nomClient) {
		
		nombre++;
		this.idFacture= new SimpleIntegerProperty(id);
		this.montant=new SimpleDoubleProperty(montant);
		this.modePaiement=new SimpleStringProperty(modePaiement);
		this.idCaissiere= new SimpleIntegerProperty(idCaissiere);
		this.nomClient= new SimpleStringProperty(nomClient);
		this.remise=new SimpleDoubleProperty(remise);
		this.date=new SimpleObjectProperty<LocalDate>(LocalDate.now());
	}

	public Facture() {
		this.idFacture= new SimpleIntegerProperty(0);
		this.montant=new SimpleDoubleProperty(0.0);
		this.modePaiement=new SimpleStringProperty("");
		this.idCaissiere= new SimpleIntegerProperty(0);
		this.nomClient= new SimpleStringProperty("");
		this.remise=new SimpleDoubleProperty(0.0);
		this.date=new SimpleObjectProperty<LocalDate>(LocalDate.now());
	}

	public int getIdFacture() {
		return idFacture.get();
	}

	public IntegerProperty idFactureProperty() {
		return new SimpleIntegerProperty(idFacture.get());
	}

	public void setIdFacture(int idFacture) {
		this.idFacture.set(idFacture);
	}

	public LocalDate getDate() {
		return date.get();
	}

	public ObjectProperty<LocalDate> dateProperty() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date.set(date);
	}

	public double getRemise() {
		return remise.get();
	}

	public DoubleProperty remiseProperty() {
		return new SimpleDoubleProperty(remise.get());
	}

	public void setRemise(double remise) {
		this.remise.set(remise);
	}

	public double getMontant() {
		return montant.get();
	}

	public DoubleProperty montantProperty() {
		return new SimpleDoubleProperty(montant.get());
	}

	public void setMontant(double montant) {
		this.montant.set(montant);
	}

	public String getModePaiement() {
		return modePaiement.get();
	}

	public StringProperty modePaiementProperty() {
		return modePaiement;
	}

	public void setModePaiement(String modePaiement) {
		this.modePaiement.set(modePaiement);
	}

	public int getIdCaissiere() {
		return idCaissiere.get();
	}

	public IntegerProperty idCaissiereProperty() {
		return new SimpleIntegerProperty(idCaissiere.get());
	}

	public void setIdCaissiere(int idCaissiere) {
		this.idCaissiere.set(idCaissiere);
	}

	public String getNomClient() {
		return nomClient.get();
	}

	public StringProperty nomClientProperty() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient.set(nomClient);
	}
}
