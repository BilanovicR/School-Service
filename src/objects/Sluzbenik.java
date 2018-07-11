package objects;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;

import objects.Profesor.Zvanje;
import objects.Student.Smer;
import projekat.DataUtil;
import projekat.Main;
import projekat.Skener;

public class Sluzbenik extends Osoba {

	public Sluzbenik(String ime, String prezime, Long jmbg, String username, String password) {
		// konstruktor za Službenika, identican kao za Osobu jer nema dodatnih
		// podataka
		super(ime, prezime, jmbg, username, password);
	}

	@Override
	public String toString() {
		// dobijam string sa podacima o sluzbeniku
		// u tom formatu cuvam u SluzbeniciS.txt
		return (getIme() + "|" + getPrezime() + "|" + getJmbg() + "|" + getUsername() + "|" + getPassword());
	}

	// Pravim nove instance Službenika
	public static Sluzbenik s1 = new Sluzbenik("Marko", "Marković", (long) 562712121, "markom", "mm");
	public static Sluzbenik s2 = new Sluzbenik("Ana", "Andrić", (long) 232334534, "anaa", "123aa");

	public static void upisiSluzbenika() {
		// i čuvam ih u .txt da bih imala inicijalno sluzbenike
		// preko kojih kreiram studente, profesore i predmete
		try {
			String path = "src/txtDatoteke/Sluzbenici.txt";
			PrintWriter out = new PrintWriter(path);
			out.println(s1.toString());
			out.println(s2.toString());
			System.out.println(s1.toString());
			System.out.println(s2.toString());
			out.close();
		} catch (FileNotFoundException e) {
			System.out.println("Tražena datoteka ne postoji!");
		}
	}

	public static void meniSluzbenika() {
		String opcija;
		boolean meni = true;
		while (meni == true) {
			System.out.println("Izaberite opciju (unesite 7 za izlaz iz aplikacije) : ");
			System.out.println("1. Upiši novog studenta");
			System.out.println("2. Upiši novog profesora");
			System.out.println("3. Upiši novi predmet");
			System.out.println("4. Izvrši uplatu");
			System.out.println("5. Pretraži studente");
			System.out.println("6. Prikaži sve studente");
			opcija = Skener.input.nextLine();
			switch (opcija) {
			case "1": {
				upisiNovogStudenta();
				break;
			}
			case "2": {
				upisiProfesora();
				break;
			}
			case "3": {
				upisiPredmet();
				break;
			}
			case "4": {
				izvrsiUplatu();
				break;
			}
			case "5": {
				pretragaStudenata();
				break;
			}
			case "6": {
				prikaziStudente();
				break;
			}
			case "7": {
				System.out.println("Izabrali ste izlaz iz aplikacije.");
				DataUtil.upisiProfesore();// upisujem sve promene podataka opet
											// u fajl na kraju
				DataUtil.upisiStudente();
				DataUtil.upisiPredmete();
				meni = false;
				break;
			}
			}
		}
	}

	// metode koje sluzbenik moze da obavi iz svog menija:

	public static Student upisiNovogStudenta() {
		String string;
		int integer;
		String llong;
		// početno postavljam sve na null ili 0, da bih uradila provere unosa
		String ime = null;
		String prezime = null;
		long jmbg = 0;
		String username = null;
		String password = null;
		String imeRoditelja = null;
		int brojIndexa = 0;
		Smer smer = null;
		Date datumRodjenja = null;
		String email = null;
		String mesto = null;
		String ulica = null;
		int ulicaBr = 0;
		int brojRacuna = 0;
		int tekucaGodina = 1;
		int stanjeNaRacunu = 0;

		System.out.println("Izabrali ste upis novog studenta. Unesite sve podatke: ");
		while (ime == null) {
			System.out.println("Ime: ");
			string = Skener.input.nextLine();
			if (string.matches("[a-zA-Z]+")) {
				ime = string;
			} else {
				System.out.println("Pogresan format, ponovite!");
			}
			;
		}
		;
		while (prezime == null) {
			System.out.println("Prezime: ");
			string = Skener.input.nextLine();
			if (string.matches("[A-Za-z]+")) {
				prezime = string;
			} else {
				System.out.println("Pogresan format, ponovite!");
			}
			;
		}
		;
		while (jmbg == 0) {
			System.out.println("JMBG: ");
			try {
				llong = Skener.input.nextLine();
				if (llong.matches("[0-9]{13}")) {
					jmbg = Long.parseLong(llong);
				} else {
					System.out.println("Pogresan format za JMBG");
				}
			} catch (InputMismatchException e) {
				System.out.println("Pogresan format unosa ponovite!");
			}
		}
		;
		while (username == null) {
			System.out.println("Dodelite username: ");
			string = Skener.input.nextLine();
			if (string.matches("[a-zA-Z]+")) {
				username = string;
			} else {
				System.out.println("Pogresan format, ponovite!");
			}
			;
		}
		;
		while (password == null || password == "") {
			System.out.println("Dodelite šifru: (mora da sadrzi slova i brojeve)");
			string = Skener.input.nextLine();
			if (string.matches("[a-zA-Z]+[0-9]+")) {// sifra mora da sadrzi
													// slova alfabeta i brojeve
				password = string;
			} else {
				System.out.println("Pogresan format, ponovite!");
			}
			;
		}
		;
		while (imeRoditelja == null) {
			System.out.println("Ime roditelja: ");
			string = Skener.input.nextLine();
			if (string.matches("[a-zA-Z]+")) {
				imeRoditelja = string;
			} else {
				System.out.println("Pogresan format, ponovite!");
			}
			;
		}
		;
		while (brojIndexa == 0) {
			try {
				System.out.println("Broj indexa: (5 cifara)");
				int ind = Skener.input.nextInt();
				if (ind >= 10000 && ind <= 99999) {
					brojIndexa = ind;
				}
			} catch (InputMismatchException e) {
				System.out.println("Pogrešan unos, ponovite!");
			}
		}
		while (smer == null) {
			System.out.println("Smer: ");
			System.out.println("Unesite 1 za IT, 2 za SII");
			try {
				integer = Skener.input.nextInt();
				if (integer == 1) {
					smer = Smer.valueOf("IT");
				}
				;
				if (integer == 2) {
					smer = Smer.valueOf("SII");
				}
			} catch (InputMismatchException e) {
				System.out.println("Pogresan unos, ponovite");
			}
		}
		while (datumRodjenja == null) {
			System.out.println("Datum rođenja (dd.mm.gggg.): ");
			string = Skener.input.nextLine();
			try {
				SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy.");
				datumRodjenja = df.parse(string);
			} catch (ParseException e) {
				System.out.println("Pogrešan format unosa!");
				;
			} catch (java.lang.ClassCastException e) {
				System.out.println("Pogresan format, pokusajte ponovo");
			}
			;
		}
		while (email == null) {
			System.out.println("Email: ");
			string = Skener.input.nextLine();
			if (string.contains("@")) {
				email = string;
			} else {
				System.out.println("Pogrešan format, ponovite unos!");
			}
			;
		}
		while (mesto == null) {
			System.out.println("Mesto stanovanja: ");
			string = Skener.input.nextLine();
			if (string.matches("[a-zA-Z]+")) {
				mesto = string;
			} else {
				System.out.println("Pogresan format, ponovite!");
			}
			;
		}
		;
		while (ulica == null) {
			System.out.println("Ulica: ");
			string = Skener.input.nextLine();
			if (string.matches("[a-zA-Z]+")) {
				ulica = string;
			} else {
				System.out.println("Pogresan format, ponovite!");
			}
			;
		}
		;
		while (ulicaBr == 0) {
			System.out.println("Broj kuće/zgrade: ");
			try {
				integer = Skener.input.nextInt();
				ulicaBr = integer;
			} catch (InputMismatchException e) {
				System.out.println("Pogrešan unos, ponovite!");
			}
		}
		;
		while (brojRacuna == 0) {
			System.out.println("Broj računa: (6 sifara)");
			try {
				integer = Skener.input.nextInt();
				if (integer > 100000 && integer < 999999) {// uslov da racun ima
															// 6 cifara
					brojRacuna = integer;
				}
			} catch (InputMismatchException e) {
				System.out.println("Pogrešan unos, ponovite!");
			}
		}
		;
		Student s = new Student(ime, prezime, jmbg, username, password, imeRoditelja, brojIndexa, smer, datumRodjenja,
				email, mesto, ulica, ulicaBr, brojRacuna, tekucaGodina, stanjeNaRacunu);
		Main.studenti.add(s);
		DataUtil.upisiStudente();
		System.out.println("Uspesno ste uneli novog studenta u bazu podataka");
		return s;
	}

	public static Profesor upisiProfesora() {
		String string;
		int integer;
		String llong;
		// početno postavljam sve na null ili 0, da bih uradila provere unosa
		String ime = null;
		String prezime = null;
		long jmbg = 0;
		String username = null;
		String password = null;
		Zvanje zvanje = null;
		System.out.println("Izabrali ste unos novog profesora");
		while (ime == null) {
			System.out.println("Ime: ");
			string = Skener.input.nextLine();
			if (string.matches("[a-zA-Z]+")) {
				ime = string;
			} else {
				System.out.println("Pogresan format, ponovite!");
			}
			;
		}
		;
		while (prezime == null) {
			System.out.println("Prezime: ");
			string = Skener.input.nextLine();
			if (string.matches("[A-Za-z]+")) {
				prezime = string;
			} else {
				System.out.println("Pogresan format, ponovite!");
			}
			;
		}
		;
		while (jmbg == 0) {
			System.out.println("JMBG: ");
			try {
				llong = Skener.input.nextLine();
				if (llong.matches("[0-9]{13}")) {
					jmbg = Long.parseLong(llong);
				} else {
					System.out.println("Pogresan format za JMBG");
				}
			} catch (InputMismatchException e) {
				System.out.println("Pogresan format unosa ponovite!");
			}
		}
		;
		while (username == null) {
			System.out.println("Dodelite username: ");
			string = Skener.input.nextLine();
			if (string.matches("[a-zA-Z]+")) {
				username = string;
			} else {
				System.out.println("Pogresan format, ponovite!");
			}
			;
		}
		;
		while (password == null || password == "") {
			System.out.println("Dodelite šifru: ");
			string = Skener.input.nextLine();
			if (string.matches("[a-zA-Z]+[0-9]+")) {// sifra mora da sadrzi
													// slova alfabeta i brojeve
				password = string;
			} else {
				System.out.println("Pogresan format, ponovite!");
			}
			;
		}
		;
		while (zvanje == null) {
			System.out.println("Izaberite zvanje: ");
			System.out.println("Unesite 1 za DOCENT");
			System.out.println("2 za REDOVNI");
			System.out.println("3 za VANREDOVNI");
			try {
				integer = Skener.input.nextInt();
				if (integer == 1) {
					zvanje = Profesor.Zvanje.valueOf("DOCENT");
				}
				;
				if (integer == 2) {
					zvanje = Profesor.Zvanje.valueOf("REDOVNI");
				}
				;
				if (integer == 3) {
					zvanje = Profesor.Zvanje.valueOf("VANREDOVNI");
				}
				;

			} catch (InputMismatchException e) {
				System.out.println("Pogresan unos, ponovite");
			}
		}

		Profesor p = new Profesor(ime, prezime, jmbg, username, password, zvanje);
		Main.profesori.add(p);// dodajem novog profesora u listu
		DataUtil.upisiProfesore();// upisujem listu u fajl da bih sacuvala sve
									// promene odmah
		System.out.println("Uspešno ste uneli novog profesora u bazu podataka");
		return p;
	}

	public static Predmet upisiPredmet() {
		String predmet;
		Smer smer = null;
		int nedeljniFond;
		long unos;
		long profjmbg = 0;
		int godinaStudija;
		int sm;
		System.out.println("Izabrali ste unos novog predmeta");
		System.out.println("Unesite naziv predmeta: ");
		predmet = Skener.input.nextLine();
		System.out.println("Unesite godinu studija za predmet:");
		godinaStudija = Skener.input.nextInt();
		System.out.println("Izaberite smer za predmet (Unesite 1 za IT, 2 za SII):");
		sm = Skener.input.nextInt();
		if (sm == 1) {
			smer = Smer.IT;
		}
		;
		if (sm == 2) {
			smer = Smer.SII;
		}
		System.out.println("Unesite nedeljni fond casova: ");
		nedeljniFond = Skener.input.nextInt();
		System.out.println("Dodelite profesora tako sto cete uneti njegov jmbg: ");
		System.out.println("Prikaz profesora: ");
		prikaziProfesore();
		while (profjmbg == 0) {
			try {
				unos = Skener.input.nextLong();
				for (Profesor profesor : Main.profesori) {
					if (profesor.jmbg == unos) {
						profjmbg = unos;
					} else {
						System.out.println("Ne postoji profesor sa tim JMBG-om u bazi, pokusajte ponovo");
					}
				}
			} catch (Exception e) {
				System.out.println("Greska pri konverziji");
			}
		}
		Predmet p = new Predmet(predmet, smer, godinaStudija, nedeljniFond, profjmbg);
		Main.predmeti.add(p);// dodam na listu predmeta
		DataUtil.upisiPredmete();// i upisem u fajl
		System.out.println("Uspešno ste uneli novi predmet u bazu podataka!");
		return p;
	}

	public static void izvrsiUplatu() {
		int unos;
		int index;
		int iznos;
		System.out.println("Izabrali ste evidentiranje uplate za prijavu ispita");
		System.out.println("Unesite broj indexa studenta: ");
		unos = Skener.input.nextInt();
		for (Student student : Main.studenti) {
			index = student.getBrojIndexa();
			if (index == unos) {
				System.out.println("Pronadjen je student:");
				System.out.println(student.ime + " " + student.prezime + " " + student.getBrojIndexa() + " "
						+ student.getJmbg() + " " + student.getSmer());
				System.out.println("Unesite novo stanje na racunu studenta: ");
				iznos = Skener.input.nextInt();
				student.setStanjeNaRacunu(iznos);
				System.out.println("Uspesno ste izmenili stanje na racunu.");
				System.out.println("Novo stanje na racunu je: " + student.getStanjeNaRacunu());
				System.out.println("Povratak na glavni meni.");
				DataUtil.upisiStudente();
				break;
			} else {
				System.out.println("Nije pronadjen student sa trazenim brojem indexa");
			}
			;
		}
		;
	}

	public static void pretragaStudenata() {
		if (Main.studenti.isEmpty()) {
			System.out.println("U bazi ne postoji ni jedan student!");
		} else {
			System.out.println("Izabrali ste pretragu studenata");
			System.out.println("Unesite parametar za pretragu: 1. Broj indexa 2. Prezime");
			String unos = Skener.input.nextLine();
			if (unos.matches("1")) {
				System.out.println("Unesite broj indexa za pretragu: ");
				int j = Skener.input.nextInt();
				for (Student student : Main.studenti) {
					int index = student.getBrojIndexa();
					if (index == j) {
						System.out.println("Pronadjen je student:");
						System.out.println(student.ime + " " + student.prezime + " " + student.getBrojIndexa() + " "
								+ student.getJmbg() + " " + student.getSmer());
						break;
					} else {
						System.out.println("Nije pronadjen student sa trazenim brojem indexa");
					}
					;
				}
			}
			;
			if (unos.matches("2")) {
				int indikator = 0;
				System.out.println("Unesite trazeno prezime: ");
				String p = Skener.input.nextLine();
				System.out.println("Rezultat pretrage: ");
				for (Student student : Main.studenti) {
					if (student.getPrezime().toLowerCase().contains(p.toLowerCase().trim())) {
						System.out.println(student.getIme() + " " + student.getPrezime() + " " + student.getBrojIndexa()
								+ " " + student.getJmbg() + " " + student.getSmer());
						indikator = 1;
					}
				}
				if (indikator == 0) {
					System.out.println("Nije pronadjen ni jedan student koji odgovara zahtevima pretrage");
				}
			}
		}
		;
	}

	public static void prikaziStudente() {
		System.out.println("Izabrali ste prikaz svih studenata: ");
		if (Main.studenti.isEmpty()) {
			System.out.println("Ne postoji ni jedan student u bazi podataka");
		} else {
			for (Student student : Main.studenti) {
				System.out.println(student.toString());
			}
		}
	}

	public static void prikaziProfesore() {// pretraga profesora da bi se
											// izabrao prema jmbg pri upisu
											// novog predmeta
		if (Main.profesori.isEmpty()) {
			System.out.println("U bazi ne postoji ni jedan profesor!");
		} else {
			for (Profesor profesor : Main.profesori) {
				System.out.println(profesor.ime + " " + profesor.prezime + " " + profesor.jmbg);
			}
		}
	}

}
