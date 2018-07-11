package projekat;

import java.util.Date;

import objects.Predmet;
import objects.Student;
import objects.Student.Smer;

public interface StudentINT {
	public static void meniStudenta() {
		boolean meni = true;
		// ugnezdi metode navedene ispod u ovu metodu
		while (meni == true) {
			int opcija;
			System.out.println("Izaberite opciju:");
			System.out.println("1. Prijavi ispit");
			System.out.println("2. Odjavi ispit");
			System.out.println("3. Prikaz položenih ispita");
			System.out.println("4. Prikaz prijavljenih ispita");
			System.out.println("5. Rezultati ispita");
			System.out.println("6. Prosečna ocena");
			System.out.println("7. Tekući predmeti");
			System.out.println("8. Izlaz iz aplikacije");			
			System.out.println("Izaberite opciju: ");
			opcija = Skener.input.nextInt();
			if (opcija == 1) {
				prijaviIspit();
			}
			;
			if (opcija == 2) {
				odjaviIspit();
			}
			;
			if (opcija == 3) {
				polozeniIspiti();
			}
			;
			if (opcija == 4) {
				prijavljeniIspitiST();
			}
			;
			if (opcija == 5) {
				rezultatiIspita();
			}
			;
			if (opcija == 6) {
				prosecnaOcena();
			}
			;
			if (opcija == 7) {
				tekuciPredmeti();
			}
			;
			if (opcija == 8) {
				DataUtil.upisiPrijavljeneIspite();
				meni = false;
				break;
			}
			;
		}

	};

	public static void prijaviIspit() {//postavila sam da je ispitni rok unapred vremenski definisan 
		@SuppressWarnings("deprecation")
		Date start = new Date(117, 7, 17);//offset za godinu je -1900
		@SuppressWarnings("deprecation")
		Date finish = new Date(117, 8, 3);
		Date now = new Date();
		System.out.println(start);
		System.out.println(finish);
		System.out.println(now);
		String unos;
		int pr = 0;
		System.out.println("Izabrali ste prijavu ispita");
		for (Student student : Main.studenti) {
			if (Main.ulogovan == student.getJmbg()) {
				if (now.after(start) && now.before(finish)) {
					if (student.getStanjeNaRacunu() >= 400) {
						while (pr == 0) {
							System.out.println("Unesite naziv predmeta:");
							unos = Skener.input.nextLine();
							for (Predmet predmet : Main.predmeti) {
								if (unos.equals(predmet.naziv) && student.getSmer() == predmet.getSmer()) {
									System.out.println("Izabrali ste sledeci predmet za prijavu:");
									System.out.println(predmet.naziv);
									Main.prijavljeniIspiti.add(predmet.naziv + "|" + student.getJmbg());
									student.setStanjeNaRacunu(student.getStanjeNaRacunu() - 400);
									DataUtil.upisiPrijavljeneIspite();
									pr = 1;
									break;
								}
							}
						}
					} else {
						System.out.println("Nemate dovoljno sredstava na racunu");
					}
				} else {
					System.out.println("Trenutno ne postoji ispitni rok za koji mozete prijaviti ispit");
				}
			}
		}
	}

	public static void odjaviIspit() {
		System.out.println("Izabrali ste odjavu ispita");
		int odjava = 0;
		String unos;
		boolean meni = true;
		while (meni == true) {
			for (String predmet : Main.prijavljeniIspiti) {
				String data[] = predmet.split("\\|");
				if (Long.parseLong(data[1]) == Main.ulogovan) {
					System.out.println("Prijavili ste predmet: " + data[0]);
					odjava = 1;// indikator da li je student uopste i
								// prijavio neki predmet za ispit
				}
				;
			}
			;
			if (odjava == 0) {
				System.out.println("Nemate ni jedan prijavljen predmet u trenutnom ispitnom roku");
				meni = false;
				break;
			}
		}
		System.out.println("Unesite naziv predmeta koji zelite odjavite: ");
		unos = Skener.input.nextLine();
		for (String predmet : Main.prijavljeniIspiti) {
			String[] data = predmet.split("\\|");
			if (data[0].equals(unos) && Long.parseLong(data[1]) == Main.ulogovan) {
				Main.prijavljeniIspiti.remove(predmet);
				System.out.println("Uspesno ste odjavili predmet");
				DataUtil.upisiPrijavljeneIspite();
				meni = false;
				break;
			}
		}
	}

	public static void polozeniIspiti() {
		System.out.println("Prikaz svih polozenih ispita:");
		for (String ispit : Main.polozeniIspiti) {
			String [] data = ispit.split("\\|");
			if (Long.valueOf(data[1]) == Main.ulogovan) {
				System.out.println(data[0] + ": " + data[2]);				
			}
		}
	}

	public static void prijavljeniIspitiST() {
		int prijava = 0;
		int ispiti = Main.prijavljeniIspiti.size();
		while (ispiti > 0) {
			for (String predmet : Main.prijavljeniIspiti) {
				String data[] = predmet.split("\\|");
				if (Long.parseLong(data[1]) == Main.ulogovan) {
					System.out.println("Prijavili ste predmet: " + data[0]);
					prijava = 1;// indikator da li je student uopste prijavio
								// neki predmet
				}
				;
				ispiti = ispiti - 1;// kad dodje do poslednjeg prijavljenog
									// ispita u listi prekida se petlja
			}
			;
			if (prijava == 0) {
				System.out.println("Nemate ni jedan prijavljen predmet u trenutnom ispitnom roku");
				break;
			}
		}
	}

	public static void rezultatiIspita() {
		String pr = "";
		int ind = 0; //indikator da li u bazi postoji polozen ispit
		System.out.println("Unesite naziv predmeta: ");
		Skener.input.nextLine();
		pr = Skener.input.nextLine();
		for (String predmet : Main.polozeniIspiti) {
			String[] data = predmet.split("\\|");
			if (data[0].equals(pr) && Long.valueOf(data[1]) == Main.ulogovan) {
				System.out.println("Ispit je polozen sa ocenom: " + data[2]);
				ind = 1;
			} 
		};
		if (ind == 0) {
			System.out.println("U bazi ne postoji Vas polozen ispit");
		}
	}

	public static void prosecnaOcena() {
		int brIspit = 0 ;
		float prosek = 0;
		float suma = 0;
		for (String ispit : Main.polozeniIspiti) {
			String [] data = ispit.split("\\|");
			if (Long.valueOf(data[1]) == Main.ulogovan) {
				System.out.println(data[2]);
				brIspit ++;
				suma = suma + Float.valueOf(data[2]);				
			}
		};
		prosek = suma / brIspit;		
		System.out.println("Ukupno ste polozili: " + brIspit + " ispita");
		System.out.println("Vasa prosecna ocena je: " + prosek);
	}

	public static void tekuciPredmeti() {
		System.out.println("Predmeti koje imate u tekucoj godini: ");
		Smer s;
		for (Student student : Main.studenti) {
			if (student.getJmbg() == Main.ulogovan) {
				s = student.getSmer();
				for (Predmet predmet : Main.predmeti) {
					if (s == predmet.getSmer()) {
						System.out.println(predmet.getNaziv());
					}
				}
			}
		}
	}
}