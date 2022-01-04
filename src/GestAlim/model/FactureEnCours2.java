package GestAlim.model;

import javafx.collections.ObservableList;

public class FactureEnCours2 {
	private int codePro;
	private String nomPro;
	private int qte;
	private double prixUnit;
	private double prixTotal;

	public FactureEnCours2(int codePro,int qte,ObservableList<produit> dataProduit) {
		int i=0;
		this.codePro= codePro;
		while(i<dataProduit.size()) {
			if(dataProduit.get(i).getCodeProduit()==codePro) {
				this.nomPro=dataProduit.get(i).getNomProduit();
				this.prixUnit=dataProduit.get(i).getPrixVente();
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

	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
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
