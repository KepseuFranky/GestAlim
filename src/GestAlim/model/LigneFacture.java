package GestAlim.model;

import GestAlim.control.MethodeSQL;

public class LigneFacture {
	private int IdLigne;//TODO: affecter la valeur de l'id avec les methodes SQL
	private int IdFacture;
	private double qte;
	private double prixUnit;
	private int codePro;
	private String nomPro;
	private double prixTotal;
	private static int nombre=0;
	public LigneFacture(int idLigne,int IdFacture,int codeProduit, double prixProduit, double qte, String nom) {
		this.IdLigne=idLigne;
		this.IdFacture=IdFacture;
		this.codePro=codeProduit;
		this.prixUnit=prixProduit;
		this.qte=qte;
		this.nomPro=nom;
		this.prixTotal=qte*prixProduit;
	}

	public int getIdLigne() {
		return IdLigne;
	}

	public void setIdLigne(int idLigne) {
		IdLigne = idLigne;
	}

	public int getIdFacture() {
		return IdFacture;
	}

	public void setIdFacture(int idFacture) {
		IdFacture = idFacture;
	}

	public double getPrixUnit() {
		return prixUnit;
	}

	public void setPrixUnit(double prixUnit) {
		this.prixUnit = prixUnit;
	}

	public int getCodePro() {
		return codePro;
	}

	public void setCodePro(int codePro) {
		this.codePro = codePro;
	}

	public String getNomPro() {
		return nomPro;
	}

	public void setNomPro(String nomPro) {
		this.nomPro = nomPro;
	}

	public double getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(double prixTotal) {
		this.prixTotal = prixTotal;
	}

	public double getQte() { return qte; }

	public void setQte(double qte) { this.qte = qte; }
}