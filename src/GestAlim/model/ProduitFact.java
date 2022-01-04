package GestAlim.model;

public class ProduitFact {

	private int codePro;
	private String NomPro;
	private double prix;
	private double Qte;
	private String desc;
	private int IdCategorie;
	
	public ProduitFact(int codePro, String NomPro, double prix, double Qte, String desc, int IdCategorie) {
		this.codePro= codePro;
		this.NomPro= NomPro;
		this.prix=prix;
		this.Qte=Qte;
		this.desc=desc;
		this.IdCategorie=IdCategorie;
	}

	public int getCodePro() {
		return codePro;
	}

	public void setCodePro(int codePro) {
		this.codePro = codePro;
	}

	public String getNomPro() {
		return NomPro;
	}

	public void setNomPro(String nomPro) {
		NomPro = nomPro;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public double getQte() {
		return Qte;
	}

	public void setQte(double qte) {
		Qte = qte;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getIdCategorie() {
		return IdCategorie;
	}

	public void setIdCategorie(int idCategorie) {
		IdCategorie = idCategorie;
	}
	
}
