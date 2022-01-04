package GestAlim.model;

import javafx.collections.ObservableList;

public class FactureEnCours {
	private int codePro;
	private String nomPro;
	private double qte;
	private double prixUnit;
	private double prixTotal;

	public FactureEnCours(int codePro,double qte,ObservableList<ProduitFact> dataProduit) {
		int i=0;
		this.codePro= codePro;
		while(i<dataProduit.size()) {
			if(dataProduit.get(i).getCodePro()==codePro) {
				this.nomPro=dataProduit.get(i).getNomPro();
				this.prixUnit=dataProduit.get(i).getPrix();
			}
			i++;
		}
		this.qte= qte;
		this.prixTotal=this.qte*this.prixUnit;	}

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

	public double getQte() {
		return qte;
	}

	public void setQte(double qte) {
		this.qte = qte;
	}

	public double getPrixUnit() {
		return prixUnit;
	}

	public void setPrixUnit(double prixUnit) {
		this.prixUnit = prixUnit;
	}

	public double getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(double prixTotal) {
		this.prixTotal = prixTotal;
	}
}
