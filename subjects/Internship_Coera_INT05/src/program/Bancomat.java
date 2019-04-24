package program;

public class Bancomat {
	private String nume="";
	private int desch_h=0, desch_m=0;
	private int ingh_h=0, inch_m=0;
	double suma=0;
	
	public Bancomat() {}
	
	public Bancomat(String nume, int desch_h, int desch_m, int ingh_h, int inch_m, double suma) {
		this.nume = nume;
		this.desch_h = desch_h;
		this.desch_m = desch_m;
		this.ingh_h = ingh_h;
		this.inch_m = inch_m;
		this.suma=suma;
	}

	
	public double getSuma() {
		return suma;
	}

	public void setSuma(double suma) {
		this.suma = suma;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public int getDesch_h() {
		return desch_h;
	}

	public void setDesch_h(int desch_h) {
		this.desch_h = desch_h;
	}

	public int getDesch_m() {
		return desch_m;
	}

	public void setDesch_m(int desch_m) {
		this.desch_m = desch_m;
	}

	public int getIngh_h() {
		return ingh_h;
	}

	public void setIngh_h(int ingh_h) {
		this.ingh_h = ingh_h;
	}

	public int getInch_m() {
		return inch_m;
	}

	public void setInch_m(int inch_m) {
		this.inch_m = inch_m;
	}



	
}
