package GestAlim.model;

public class InfoFacture {

	private String nomClient="ND";
	private String remise="0";
	private String netPayer="0";
	private String total="0";
	
	
	public InfoFacture() {}
	public InfoFacture(String a, String b, String c, String d) {
		nomClient=a;
		remise=b;
		netPayer=c;
		total=d;
	}
	public String getNomClient() {
		return nomClient;
	}
	public String getRemise() {
		return remise;
	}
	public String getNet() {
		return netPayer;
	}
	public String getTotal() {
		return total;
	}
}
