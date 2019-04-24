package program;

public class Distante {
	private String poz_act="", poz="";
	private int dist=0;
	
	public Distante() {}

	public Distante(String poz_act, String poz, int dist) {
		this.poz_act = poz_act;
		this.poz = poz;
		this.dist = dist;
	}
	
	public Distante(Distante d) {
		this.poz_act = d.poz_act;
		this.poz = d.poz;
		this.dist = d.dist;
	}
	
	public String getPoz_act() {
		return poz_act;
	}
	public void setPoz_act(String poz_act) {
		this.poz_act = poz_act;
	}
	public String getPoz() {
		return poz;
	}
	public void setPoz(String poz) {
		this.poz = poz;
	}
	public int getDist() {
		return dist;
	}
	public void setDist(int dist) {
		this.dist = dist;
	}
	
	
}
