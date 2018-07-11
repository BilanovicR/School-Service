package projekat;

import objects.Predmet;
import objects.Student;

public interface ProfesorINT {
	public static void meniProfesora() {
		boolean meni = true;
		int opcija;
		while (meni == true) {
			System.out.println("Izaberite opciju: ");
			System.out.println("1. Prijavljeni ispiti");
			System.out.println("2. Unesi rezultate ispita");
			System.out.println("3. Lista mojih predmeta");
			System.out.println("4. Izlaz iz aplikacije");
			opcija = Skener.input.nextInt();
			if (opcija == 1) {
				prijavljeniIspitiPR();
			}
			;
			if (opcija == 2) {
				unesiRezultate();
			}
			;
			if (opcija == 3) {
				listaPredmeta();
			}
			;
			if (opcija == 4) {
				System.out.println("Izabrali ste izlaz iz aplikacije");
				meni = false;
				break;
			}
		}
		;
	}

	public static void prijavljeniIspitiPR() {
		long prof;
		int indikator = 0;
		for (String linija : Main.prijavljeniIspiti) {// pretrazujem sve
														// prijavljene
														// ispite
			String[] data = linija.split("\\|");
			for (Predmet predmet : Main.predmeti) {// pretrazujem sve
													// predmete
				prof = predmet.getProfjmbg();// identifikujem profesora
												// koji predaje taj
												// predmet prema jmbg
				if (Main.ulogovan == prof && predmet.naziv.equals(data[0])) {
					// ako ulogovan profesor predaje taj predmet i prijavljen
					// predmet poklapaju onda prikazujem taj predmet
					System.out.println(predmet.naziv + " prijavio/la je student sa JMBG brojem: " + data[1]);
					indikator = 1;
				}
			}
		}
		if (indikator == 0) {
			System.out.println("U bazi ne postoji ni jedan prijavljen ispit");
		}

	}

	public static void unesiRezultate() {
		String ispit;
		String pr;
		String naziv = "";
		int indeks = 0;
		int in;
		int unos;
		int ocena = 0;
		boolean meni = true;
		while (meni == true) {
			System.out.println("Izabrali ste unos rezultata ispita.");
			while (naziv.isEmpty()) {
				System.out.println("Unesite naziv predmeta: ");
				pr = Skener.input.nextLine();
				for (Predmet predmet : Main.predmeti) {
					if (pr.equals(predmet.getNaziv()) && Main.ulogovan == predmet.getProfjmbg()) {
						naziv = pr;
						break;
					}
				}
			}
			while (indeks == 0) {
				System.out.println("Unesite broj indexa studenta");
				in = Skener.input.nextInt();
				for (Student student : Main.studenti) {
					if (in == student.getBrojIndexa()) {
						indeks = in;
					}
				}
			}
			while (ocena == 0) {
				System.out.println("Unesite ocenu:");
				unos = Skener.input.nextInt();
				if (unos >= 6 && unos <= 10) {
					ocena = unos;
				}
			}
			ispit = naziv + "|" + indeks + "|" + ocena;
			System.out.println("Ispit: " + ispit);
			System.out.println("Za upis rezultata u bazu podataka unesite 1, a za izlaz pritisnite 2");
			int opcija = Skener.input.nextInt();
			if (opcija == 1) {
				Main.polozeniIspiti.add(ispit);
				System.out.println("Uspesno ste uneli rezultate ispita.");
				DataUtil.upisiPolozeneIspite();
				meni = false;
				break;
			}
			;
			if (opcija == 2) {
				meni = false;
				break;
			}
		}
	}

	public static void listaPredmeta() {
		System.out.println("Prikaz Vasih predmeta: ");
		for (Predmet predmet : Main.predmeti) {
			if (predmet.profjmbg == Main.ulogovan) {
				System.out.println(predmet);
			}
		}
	}
}
