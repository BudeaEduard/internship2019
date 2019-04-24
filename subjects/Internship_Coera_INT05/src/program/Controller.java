package program;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Controller {
	private Client c=new Client();
	private Client caux=new Client();
	//caux -> un client auxiliar, echivalent cu primul, dar in care vom face o simulare pentru conturi
	private Bancomat[] atm = new Bancomat[4];
	private Distante[] dist= new Distante[15];
	private Date data_curenta=new Date();
	
	
	
	
	@SuppressWarnings("deprecation")
	public void date_auxiliare() {
		//voi adauga manual niste date pentru problema, in special datele din textul problemei
		//setez data si ora curenta
		this.data_curenta.setDate(19);;
		this.data_curenta.setMonth(2);
		this.data_curenta.setYear(2019-1900);
		this.data_curenta.setHours(11);
		this.data_curenta.setMinutes(30);
		
		//setez conturile clientului
		List<Cont> conturi = new ArrayList<Cont>() ;
		
		String denumire="ARGINT";
		double d1=0.3, d2=0.2, s=5000;
		Date d=new Date();
		d.setDate(23);
		d.setMonth(4);
		d.setYear(2020-1900);
		Cont cont1=new Cont(denumire,d,s,d1,d2);
		conturi.add(cont1);
		
		denumire="AUR";
		d1=0.6; d2=0.4; s=700;
		d=new Date();
		d.setDate(5);
		d.setMonth(6);
		d.setYear(2020-1900);
		Cont cont2=new Cont(denumire,d,s,d1,d2);
		conturi.add(cont2);
		
		denumire="PLATINA";
		d1=0.9; d2=0.5; s=300;
		d=new Date();
		d.setDate(15);
		d.setMonth(2);
		d.setYear(2020-1900);
		Cont cont3=new Cont(denumire,d,s,d1,d2);
		conturi.add(cont3);
		
		this.c=new Client(1,"Home",0,conturi);
		this.caux=new Client(this.c);
		
		//voi seta distantele intre locatii
		Distante[] dist=new Distante[16];
		for (int i=0;i<dist.length;i++) {dist[i]=new Distante();}
		//de la 'Home' la ATM
		dist[0].setPoz_act("Home");
		dist[0].setPoz("ATM1");
		dist[0].setDist(5);
		dist[1].setPoz_act("Home");
		dist[1].setPoz("ATM2");
		dist[1].setDist(60);
		dist[2].setPoz_act("Home");
		dist[2].setPoz("ATM3");
		dist[2].setDist(30);
		dist[3].setPoz_act("Home");
		dist[3].setPoz("ATM4");
		dist[3].setDist(45);
		
		//de la 'ATM1' la ATM
		dist[4].setPoz_act("ATM1");
		dist[4].setPoz("ATM2");
		dist[4].setDist(40);
		dist[5].setPoz_act("ATM1");
		dist[5].setPoz("ATM3");
		dist[5].setDist(40);
		dist[6].setPoz_act("ATM1");
		dist[6].setPoz("ATM4");
		dist[6].setDist(45);
		
		//de la 'ATM2' la ATM
		dist[7].setPoz_act("ATM2");
		dist[7].setPoz("ATM1");
		dist[7].setDist(40);
		dist[8].setPoz_act("ATM2");
		dist[8].setPoz("ATM3");
		dist[8].setDist(15);
		dist[9].setPoz_act("ATM2");
		dist[9].setPoz("ATM4");
		dist[9].setDist(30);
		
		//de la 'ATM3' la ATM
		dist[10].setPoz_act("ATM3");
		dist[10].setPoz("ATM1");
		dist[10].setDist(40);
		dist[11].setPoz_act("ATM3");
		dist[11].setPoz("ATM2");
		dist[11].setDist(15);
		dist[12].setPoz_act("ATM3");
		dist[12].setPoz("ATM4");
		dist[12].setDist(15);
		
		//de la 'ATM4' la ATM
		dist[13].setPoz_act("ATM4");
		dist[13].setPoz("ATM1");
		dist[13].setDist(45);
		dist[14].setPoz_act("ATM4");
		dist[14].setPoz("ATM2");
		dist[14].setDist(30);
		dist[15].setPoz_act("ATM4");
		dist[15].setPoz("ATM3");
		dist[15].setDist(15);
		
		this.dist=dist.clone();
		
		//va trebuii sa setez si bancomatele disponibile
		Bancomat[] b=new Bancomat[4];
		for (int i=0;i<b.length;i++) {b[i]=new Bancomat();}
		b[0].setNume("ATM1");
		b[0].setDesch_h(12);
		b[0].setDesch_m(0);
		b[0].setIngh_h(18);
		b[0].setInch_m(0);
		b[0].setSuma(5000);
		
		b[1].setNume("ATM2");
		b[1].setDesch_h(10);
		b[1].setDesch_m(0);
		b[1].setIngh_h(17);
		b[1].setInch_m(0);
		b[1].setSuma(5000);
		
		b[2].setNume("ATM3");
		b[2].setDesch_h(22);
		b[2].setDesch_m(0);
		b[2].setIngh_h(13);
		b[2].setInch_m(0);
		b[2].setSuma(5000);
		
		b[3].setNume("ATM4");
		b[3].setDesch_h(17);
		b[3].setDesch_m(0);
		b[3].setIngh_h(1);
		b[3].setInch_m(0);
		b[3].setSuma(5000);
		
		this.atm=b.clone();
	}
		
	public List<Cont> redistributeMoney(List<Cont> accounts) {
		this.deplasare_eficienta();
		//clientul face deplasarea cea mai eficienta adica spre cel mai apropiat atm deschis
		
		this.simulare();
		//in variabila caux(Client) avem modul in care ar trebui sa fie distribuiti banii
		//iar in variabila c(Client) avem conturile intiale
		
		//verificam la ce bancomat se afla clientul
		int k=0;
		for (int i=0;i<this.atm.length;i++) {
			if (this.c.getPoz()==this.atm[i].getNume()) {k=i; break;}}
		//algoritmul de mutare dintr-un bancomat
		boolean ok=false;
		double suma_in=0;
		double sum=0;
		while (!ok && this.atm[k].getSuma()>0) {
			for (int i=0;i<this.c.getNrConturi();i++) {
				for (int j=0;j<this.c.getNrConturi();j++) {
					if (this.caux.getConturi().get(i).getDen()==this.c.getConturi().get(j).getDen()) {
						suma_in=accounts.get(j).getSuma();
						sum=this.caux.getConturi().get(i).getSuma();
						if (suma_in>sum) {
							if (suma_in-sum<=this.atm[k].getSuma()) {
								//daca exista suma necesara in bancomat, retrag cat am nevoie
								this.c.setSuma_f(this.c.getSuma_f()+suma_in-sum);
								this.atm[k].setSuma(this.atm[k].getSuma()-suma_in+sum);
								this.c.getConturi().get(j).setSuma(sum);
								accounts.get(j).setSuma(sum);
							}
							else {
								//retrag cat mai este in bancomat
								this.c.setSuma_f(this.c.getSuma_f()+this.atm[k].getSuma());
								this.c.getConturi().get(j).setSuma(suma_in-this.atm[k].getSuma());
								accounts.get(j).setSuma(suma_in-this.atm[k].getSuma());
								this.atm[k].setSuma(0);
							}
						}
						else if (suma_in<sum) {
							if (sum-suma_in<=this.c.getSuma_f()) {
								//daca exista suma necesara fizica asupra clientului, depun cat am nevoie in cont
								this.c.getConturi().get(j).setSuma(sum);
								accounts.get(j).setSuma(sum);
								this.atm[k].setSuma(this.atm[k].getSuma()+sum-suma_in);
								this.c.setSuma_f(this.c.getSuma_f()-sum+suma_in);
							}
							else {
								//depun cat detine clientul la el
								this.c.getConturi().get(j).setSuma(suma_in+this.c.getSuma_f());
								accounts.get(j).setSuma(suma_in+this.c.getSuma_f());
								this.atm[k].setSuma(this.atm[k].getSuma()+this.c.getSuma_f());
								this.c.setSuma_f(0);
							}
						}
					}
				}
			}
			//verific daca am finalizat tranzactiile
			ok=true;
			for (int i=0;i<this.caux.getNrConturi();i++) {
				for (int j=0;j<this.c.getNrConturi();j++) {
					if (this.caux.getConturi().get(i).getDen()==this.c.getConturi().get(j).getDen()){
						if (this.caux.getConturi().get(i).getSuma()!=this.c.getConturi().get(j).getSuma()) {
							ok=false;
						}
					}
				}
			}
		}
		//daca ok e tot fals, inseamna ca nu am terminat de mutat banii cum trebuie, si deci
		//inseamna ca suma din bancomat e 0 si trebuie sa mergem la alt bancomat
		if (!ok) {
			accounts=this.redistributeMoney(accounts);
			//apelez functia recursiv
		}
		
		return accounts;
	}
	
	@SuppressWarnings("deprecation")
	private void deplasare_eficienta() {
		/* in primul rand recunosc ca nu am implementat o situatie de genul:
		 * ora este 11:30, distanta cea mai mare catre un atm este 60 minute
		 * primul atm care se deschide este la ora 15:00
		 * programul nu va rula cum trebuie, sorry :)
		 */
		String actual=this.c.getPoz();
		Distante[] d=this.dist.clone();
		String nou="";
		
		//ora actuala + timpul de deplasare
		//>= ora deschiderii celui mai aproape bancomat
		//atunci actual=ATMi
		int ora=0, minutul=0;
		int ora_optima=25, minutul_optim=61;
		boolean ok=false;
		for (int i=0;i<d.length;i++) {
			if (d[i].getPoz_act()==actual) {
				//vom estima ora la care clientul va fi la atm-ul i
				ora=this.data_curenta.getHours();
				minutul=this.data_curenta.getMinutes()+d[i].getDist();
				if (minutul>=60) {
					ora++;
					minutul-=60;
				}
				//verificam daca atm-ul va fi deschis in momentul in care clientul ajunge acolo
				for (int j=0;j<this.atm.length;j++) {
					if (this.atm[j].getNume()==d[i].getPoz()) {
						if (this.atm[j].getDesch_h()<=ora) {
							if (this.atm[j].getDesch_m()<=minutul) {
								//verificam daca optiunea este optima
								if ((ora<ora_optima) || (ora==ora_optima && minutul<minutul_optim)) {
									ora_optima=ora; minutul_optim=minutul;
									nou=this.atm[j].getNume();
									ok=true;
								}
							}
						}
					}
				}
			}
		}
		if (ok) {
			this.data_curenta.setHours(ora_optima);
			this.data_curenta.setMinutes(minutul_optim);
			this.c.setPoz(nou);
		}
		
	}

	public void simulare() {
			/*ideea mea e de a construii o matrice de 2 x n unde n sa fie numarul
			de conturi ale clientului, unde pe coloana 1 vor vi procentele care
			se iau in calcul pentru (0,500) lei, iar pe coloana 2 vor fi procentele
			care se iau in calcul pentru [500,5000)*/
		double[][] procente=new double[this.caux.getNrConturi()][2];
		List<Cont> conturi=new ArrayList<Cont>();
		for (int i=0;i<this.caux.getConturi().size();i++) {
			conturi.add(new Cont(this.caux.getConturi().get(i)));
		}

		int n=conturi.size();
		long diff=0, dif=0;
			/*vreau sa calculez procentul maxim 
			al fiecarui cont, din ziua curenta pana la data expirarii
			va trebui sa aflu cate zile sunt din ziua respectiva pana la data expirarii
			si sa construiesc in acelasi timp matricea de procente*/
		int k=0;
		double[] prc=new double[2*n]; //pentru a putea distribuii banii uniform
		double suma=0; 			//voi avea nevoie si de suma totala din conturi
		for (int i=0;i<n;i++) {
			dif = conturi.get(i).getD_exp().getTime() - this.data_curenta.getTime();
			diff = dif / (24 * 60 * 60 * 1000);
			
			//ca sa nu ma complic consider ca anul are 365 de zile
			if (diff>0) {
				procente[i][0]=conturi.get(i).getDob1()*diff/365;
				procente[i][1]=conturi.get(i).getDob2()*diff/365;
				prc[k]=procente[i][0];
				prc[k+1]=procente[i][1];
				k=k+2;
			}
			else {k=k+2;}
			suma=suma+conturi.get(i).getSuma();
			conturi.get(i).setSuma(0);
		}
		/*din exemplul dat pe github deduc ca procentul care se ia in 
		calcul pentru (0,500) este mai mare decat cel pentru [500,5000)
		atunci voi vrea sa ordonez matricea doar dupa prima coloana*/
		double aux=0;
		Cont c_aux=conturi.get(0);
		for (int i=0;i<n-1;i++) {
			for (int j=i+1;j<n;j++) {
				if (procente[i][0]<procente[j][0]) {
					aux=procente[i][0];
					procente[i][0]=procente[j][0];
					procente[j][0]=aux;
					aux=procente[i][1];
					procente[i][1]=procente[j][1];
					procente[j][1]=aux;
					//voi modifica si ordinea conturilor, pentru a
					//tine minte legatura dintre matrice si conturi
					c_aux=conturi.get(i);
					conturi.set(i, conturi.get(j));
					conturi.set(j, c_aux);
				}
			}
		}
		//ordonez vectorul prc
		aux=0;
		for (int i=0;i<2*n-1;i++) {
			for (int j=i+1;j<2*n;j++) {
				if (prc[i]<prc[j]) {
					aux=prc[i];
					prc[i]=prc[j];
					prc[j]=aux;
				}
			}
		}
		//voi continua sa distribui banii in conturi
		//dar mai intai verific daca exista vreun procent
		//deoarece daca nu, inseamna ca nu avem nici un cont activ
		boolean ok=false;
		for (int i=0;i<prc.length;i++) {
			if (prc[i]!=0) {
				ok=true;
				break;
			}
		}
		int l=0; //va fi un contor de securitate
		int c=0; //ma ajuta sa verific sa nu fie o bucla infinita
		if (ok) {
			k=0;
			while (k<2*n-1 && suma!=0 && l<3) {
				for (int i=0;i<n;i++){
					c=0;
					if (procente[i][0]==prc[k]) {
						//distribui in contul i, suma de 500 daca exista
						//iar daca nu, voi distribui cati bani au mai ramas
						if (prc[k]>0) {
							if (((suma<500  && conturi.get(i).getSuma()+suma>500) || suma>500) && conturi.get(i).getSuma()<500) {
								suma-=500-conturi.get(i).getSuma();
								conturi.get(i).setSuma(500);
								k++;
							}else if (suma<500  && conturi.get(i).getSuma()+suma<500){
								conturi.get(i).setSuma(conturi.get(i).getSuma()+suma);
								suma=0;
								break;
							}
							
						}
						else k++;
					}
					else {c++;}
					if (procente[i][1]==prc[k]) {
						//distribui in contul i, suma de 4500 daca exista
						//iar daca nu, voi distribui cati bani au mai ramas
						//iar procedeul s-a incheiat
						if (prc[k]>0) {
							if (((suma<4500  && conturi.get(i).getSuma()+suma>4500) ||suma>4500) && conturi.get(i).getSuma()<4500) {
								suma-=4500-conturi.get(i).getSuma()+500;
								conturi.get(i).setSuma(5000);
								k++;
							}else if (suma<4500  && conturi.get(i).getSuma()+suma<4500){
								conturi.get(i).setSuma(suma+conturi.get(i).getSuma());
								suma=0;
								break;
							}
						}
						else k++;
					}
					else {c++;}
					if (c==2) {k+=2;}
				}
				if (suma>0) {k=0; l++;}
			}
		}
		if (l==3) {
			//caut un cont activ
			for (int i=0;i<conturi.size();i++) {
				if (conturi.get(i).getD_exp().after(this.data_curenta)){
					conturi.get(i).setSuma(conturi.get(i).getSuma()+suma);
					break;
				}
			}
		}
			/*deoarece pana acum a avut loc doar o simulare pentru cum ar trebui
			  sa fie distribuiti banii in conturi, lista initiala de conturi nu s-a  modificat
			  acest procedeu se va face in functie de ATM-urile disponibile, de timpul efectuat
			  de client pana la ATM, sau intre ATM-uri
			  iar consecintele legate de timp trebuie luate in considerare*/
		for (int i=0;i<this.caux.getConturi().size();i++) {
			this.caux.getConturi().set(i, new Cont(conturi.get(i)));
		}
	}

	@SuppressWarnings("deprecation")
	public double profit(int n) {
		Date data=new Date();
		data.setDate(this.data_curenta.getDate());
		data.setMonth(this.data_curenta.getMonth());
		data.setYear(this.data_curenta.getYear());

		int year=data.getYear();
		int month=n+data.getMonth();
		while (month>11) {
			year++;
			month-=12;
		}
		data.setMonth(month);
		data.setYear(year);
		
		this.simulare();
		long dif=0,diff=0;
		long[] zile=new long[this.caux.getNrConturi()];
		for (int i=0;i<this.caux.getNrConturi();i++) {
			dif = this.caux.getConturi().get(i).getD_exp().getTime() - this.data_curenta.getTime();
			diff = dif / (24 * 60 * 60 * 1000);
			zile[i]=diff;
		}
		dif = data.getTime()- this.data_curenta.getTime();
		diff = dif / (24 * 60 * 60 * 1000);
		//ordonez dupa cea mai mica perioada activa a contului
		Cont contaux=new Cont();
		long aux=0;
		for (int i=0;i<zile.length-1;i++) {
			for (int j=i+1;j<zile.length;j++) {
				if (zile[i]>zile[j]) {
					aux=zile[i];
					zile[i]=zile[j];
					zile[j]=aux;
					contaux=this.caux.getConturi().get(i);
					this.caux.getConturi().set(i, this.caux.getConturi().get(j));
					this.caux.getConturi().set(j, contaux);
				}
			}
		}
		//acum voi calcula profitul
		double suma=0,s=0, profit=0;
		boolean ok=false;
		int k=0;
		while (!ok && k<zile.length) {
			if(zile[k]<diff) {
				for (int i=0;i<zile.length;i++) {
					suma=0;
					s=this.getCaux().getConturi().get(i).getSuma();
					if (s>500) {
						suma+=this.getCaux().getConturi().get(i).getDob1()*500*zile[k]/36500;
						suma+=this.getCaux().getConturi().get(i).getDob2()*(s-500)*zile[k]/36500;
						this.caux.getConturi().get(i).setSuma(s+this.getCaux().getConturi().get(i).getDob1()*500*diff/36500+this.getCaux().getConturi().get(i).getDob2()*(s-500)*diff/36500);
					}
					else {
						suma+=this.getCaux().getConturi().get(i).getDob1()*s*zile[k]/36500;
						this.caux.getConturi().get(i).setSuma(s+this.getCaux().getConturi().get(i).getDob1()*s*diff/36500);
					}
					profit+=suma;
				}	
				for (int i=0;i<zile.length-k;i++) {
					zile[zile.length-i-1]-=zile[k];
				}
				//setez data curenta cu data in care inceteaza primul cont
				this.data_curenta.setDate(this.getC().getConturi().get(k).getD_exp().getDate());
				this.data_curenta.setMonth(this.getC().getConturi().get(k).getD_exp().getMonth());
				this.data_curenta.setYear(this.getC().getConturi().get(k).getD_exp().getYear());
				
				dif = data.getTime()- this.data_curenta.getTime();
				diff = dif / (24 * 60 * 60 * 1000);
				this.simulare();
				k++;
			}
			else if (zile[k]>=diff) {
				//perioada data de utilizator este mai scurta decat prima perioada de expirare a conturilor
				for(int i=0;i<zile.length;i++) {
					suma=0;
					s=this.getCaux().getConturi().get(i).getSuma();
					if (s>500) {
						suma+=this.getCaux().getConturi().get(i).getDob1()*500*diff/36500;
						suma+=this.getCaux().getConturi().get(i).getDob2()*(s-500)*diff/36500;
						this.caux.getConturi().get(i).setSuma(s+this.getCaux().getConturi().get(i).getDob1()*500*diff/36500+this.getCaux().getConturi().get(i).getDob2()*(s-500)*diff/36500);
					}
					else {
						suma+=this.getCaux().getConturi().get(i).getDob1()*s*diff/36500;
						this.caux.getConturi().get(i).setSuma(s+this.getCaux().getConturi().get(i).getDob1()*s*diff/36500);
					}
					profit=profit+suma;
				}
				k++;
				diff=0;
			}
			if (diff==0 || zile[zile.length-1]==0) {ok=true;}
		}
		for (int i=0;i<this.c.getConturi().size();i++) {
			profit+=this.c.getConturi().get(i).getSuma();
		}
		return profit;
	}
	
	public Client getC() {
		return c;
	}

	public void setC(Client c) {
		this.c = c;
	}

	public Bancomat[] getAtm() {
		return atm;
	}

	public void setAtm(Bancomat[] atm) {
		this.atm = atm;
	}

	public Date getData_curenta() {
		return data_curenta;
	}
	
	public void setData_curenta(Date data_curenta) {
		this.data_curenta = data_curenta;
	}
	
	public Client getCaux() {
		return caux;
	}

	public void setCaux(Client caux) {
		this.caux = caux;
	}

	public Distante[] getDist() {
		return dist;
	}

	public void setDist(Distante[] dist) {
		this.dist = dist;
	}

}
