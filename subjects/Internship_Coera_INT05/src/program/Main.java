package program;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class Main {
	private static Controller ctrl=new Controller();
		
	public static void main(String[] args) {
		long start = System.currentTimeMillis();


		//distribui banii in conturi
		ctrl.date_auxiliare();
		ctrl.getC().setConturi(ctrl.redistributeMoney(ctrl.getC().getConturi()));
		afisare_conturi(ctrl.redistributeMoney(ctrl.getC().getConturi()));
		System.out.println("Data si ora: "+ctrl.getData_curenta().toString()+". Banii au fost distribuiti.");
		System.out.println();	
		
		//afisez profitul dupa 39 de luni
		ctrl.date_auxiliare();
		afisare(39);
		
		


		long end = System.currentTimeMillis();
		NumberFormat formatter = new DecimalFormat("#0.00000");
		System.out.print("Execution time is " + formatter.format((end - start) / 1000d) + " seconds");	
	}

	private static void afisare_conturi(List<Cont> accounts) {
		int n=accounts.size();
		for (int i=0;i<n;i++) {
			System.out.println("In contul "+accounts.get(i).getDen()+" aveti "+accounts.get(i).getSuma()+" lei.");
		}

	}
	
	public static void afisare(int n) {
		//afisez care va fi suma totala dupa n luni
		double profit=ctrl.profit(n);
		System.out.println("Dupa "+n+" luni, suma totala va fi de "+String.format( "%.2f", profit )+" lei.");
	}
}
