package program;

import java.util.Date;

public class Cont {
	private String den="";				//denumirea contului
	private Date d_exp=new Date();		//data expirarii
	private double suma=0;				//suma din cont
	private double dob1=0,dob2=0;		//dobanda 1: (0,500) lei ; dobanda 2: [500,5000) lei
	
	public Cont() {}
	
	public Cont(String den, Date d_exp, double suma, double dob1, double dob2) {
		super();
		this.den = den;
		this.d_exp = d_exp;
		this.suma = suma;
		this.dob1 = dob1;
		this.dob2 = dob2;
	}

	public Cont(Cont cont) {
		this.den = cont.den;
		this.d_exp = cont.d_exp;
		this.suma = cont.suma;
		this.dob1 = cont.dob1;
		this.dob2 = cont.dob2;
	}

	public String getDen() {
		return den;
	}
	
	public void setDen(String den) {
		this.den = den;
	}
	
	public Date getD_exp() {
		return d_exp;
	}
	
	public void setD_exp(Date d_exp) {
		this.d_exp = d_exp;
	}
	
	public double getSuma() {
		return suma;
	}
	
	public void setSuma(double suma) {
		this.suma = suma;
	}
	
	public double getDob1() {
		return dob1;
	}
	
	public void setDob1(double dob1) {
		this.dob1 = dob1;
	}
	
	public double getDob2() {
		return dob2;
	}
	
	public void setDob2(double dob2) {
		this.dob2 = dob2;
	}
	
}
