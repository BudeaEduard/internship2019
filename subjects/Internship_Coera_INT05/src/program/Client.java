package program;

import java.util.ArrayList;
import java.util.List;

public class Client {
	private int id=0;					
	private String poz="";							//pozitia unde se afla clientul
	private double suma_f=0;						//suma fizica pe care o are clientul asupra sa
	private List<Cont> conturi=new ArrayList<Cont>();		//conturile clientului
	
	public Client() {}
	
	public Client(int id, String poz, double suma_f, List<Cont> conturi) {
		super();
		this.id = id;
		this.poz = poz;
		this.suma_f = suma_f;
		List<Cont> c=new ArrayList<Cont>();
		for (int i=0;i<conturi.size();i++) {
			c.add(new Cont(conturi.get(i)));
		}
		this.conturi = c;
	}

	public Client(Client c) {
		this.id=c.getId();
		this.poz=c.getPoz();
		this.suma_f=c.getSuma_f();
		List<Cont> co=new ArrayList<Cont>();
		for (int i=0;i<c.conturi.size();i++) {
			co.add(new Cont(c.conturi.get(i)));
		}
		this.conturi = co;
		this.conturi=co;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPoz() {
		return poz;
	}
	
	public void setPoz(String poz) {
		this.poz = poz;
	}
	
	public double getSuma_f() {
		return suma_f;
	}
	
	public void setSuma_f(double suma_f) {
		this.suma_f = suma_f;
	}
	
	public List<Cont> getConturi() {
		return conturi;
	}
	
	public void setConturi(List<Cont> conturi) {
		this.conturi = conturi;
	}
	
	public int getNrConturi() {
		return this.conturi.size();
	}
};
