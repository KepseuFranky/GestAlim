package GestAlim.model;


import GestAlim.control.MethodeSQL;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Gestionnaire {
	
	
	
	
	
	private int idEmployee=MethodeSQL.getMaxIdGestionnaire()+1;
	private String nomEmployee;
	private int typeEmployee;
	private String loginEmployee;
	private String passwordEmployee;
	private int actif=0;
	
	public Gestionnaire() {}
	public Gestionnaire(int id, String nom, int type, String login, String pass, int actif) {
		this.idEmployee=MethodeSQL.getMaxIdGestionnaire()+1;
		this.nomEmployee=nom;
		this.typeEmployee=type;
		this.loginEmployee=login;
		this.passwordEmployee=pass;
		this.actif=actif;
	}
	public int getIdEmployee() {
		return this.idEmployee;
	}
	
	public void setIdEmployee(int id) {
		this.idEmployee=id;
	}
	
	public IntegerProperty IdEmployee() {
		final IntegerProperty tel= new SimpleIntegerProperty(this.getIdEmployee());
		return tel;
	}
	public void setNomEmployee(String nom) {
		this.nomEmployee=nom;
	}
	
	
	
	public void setLoginEmployee(String email) {
		this.loginEmployee=email;
	}
	
	public void setPasswordEmployee(String pass) {
		this.passwordEmployee=pass;
	}
	
	public void setTypeEmployee(int type) {
		this.typeEmployee=type;
	}
	
	public void setActif(int actif){
		this.actif=actif;
	}
	
	public String getNomEmployee() {
		return this.nomEmployee;
	}
	
	public String getLoginEmployee() {
		return this.loginEmployee;
	}
	
	public String getPasswordEmployee() {
		return this.passwordEmployee;
	}
	
	public int getTypeEmployee() {
		return this.typeEmployee;
	}
	
	public int getActif(){
		return this.actif;
	}
	
	public StringProperty NomEmployee() {
		final StringProperty tel=new SimpleStringProperty(this.getNomEmployee());
    	return tel;
	}

	public StringProperty LoginEmployee() {
		final StringProperty tel=new SimpleStringProperty(this.getLoginEmployee());
    	return tel;
	}
	
	public IntegerProperty TypeEmployee() {
		final IntegerProperty tel=new SimpleIntegerProperty(this.getTypeEmployee());
    	return tel;
	}
	
	public IntegerProperty ActifEmployee() {
		final IntegerProperty tel=new SimpleIntegerProperty(this.getActif());
    	return tel;
	}
	
	public boolean sameEmployee(Gestionnaire user) {
		if(this.getLoginEmployee().toString().
				equals(user.getLoginEmployee().toString()) && this.getPasswordEmployee().toString().
				equals(user.getPasswordEmployee().toString()) ) {
			return true;
		}
		else {
			return false;
		}
	}
}
