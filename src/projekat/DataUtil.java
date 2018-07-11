package projekat;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import objects.Predmet;
import objects.Profesor;
import objects.Profesor.Zvanje;
import objects.Sluzbenik;
import objects.Student;
import objects.Student.Smer;

public class DataUtil {

	public static long pretraziKorisnike(String user, String pass) {
		long jmbg = 0;
		for (Student student : Main.studenti) {
			if (student.username.equals(user) && student.password.equals(pass)) {
				System.out.println("Ulogovali ste se kao korisnik " + student.ime + " " + student.prezime);
				jmbg = student.getJmbg();
				Main.x = 2;// zavisno koji broj se prosledi, ulazi se u
							// metodu ispod i prikaze se odgovarajući
							// meni za sluzbenika/studenta/profesora
			}
			;
		}
		;
		for (Sluzbenik sluzbenik : Main.sluzbenici) {
			if (sluzbenik.username.equals(user) && sluzbenik.password.equals(pass)) {
				System.out.println("Ulogovali ste se kao korisnik " + sluzbenik.ime + " " + sluzbenik.prezime);
				jmbg = sluzbenik.getJmbg();
				Main.x = 1;
			}
			;
		}
		;
		for (Profesor profesor : Main.profesori) {
			if (profesor.username.equals(user) && profesor.password.equals(pass)) {
				System.out.println("Ulogovali ste se kao korisnik " + profesor.ime + " " + profesor.prezime);
				jmbg = profesor.getJmbg();
				Main.x = 3;
			}
			;
		}
		;
		return jmbg;// dobijam jmbg korisnika
	}

	public static void prikaziMeni(int x) {

		if (x == 1) {// meni za službenika
			Sluzbenik.meniSluzbenika();
		}
		;
		if (x == 2) {// meni za studenta
			StudentINT.meniStudenta();
		}
		;
		if (x == 3) {// meni za profesora
			ProfesorINT.meniProfesora();
		}
	}

	public static ArrayList<Student> ucitajStudente() {
		String student;// jedna linija u fajlu = jedan student
		ArrayList<Student> studenti = new ArrayList<Student>();
		try {
			BufferedReader in = new BufferedReader(new FileReader("src/txtDatoteke/Studenti.txt"));
			while (!((student = in.readLine()) == null)) {
				studenti.add(parseStudent(student));
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("Fajl nije pronadjen");
		} catch (IOException e) {
			System.out.println("Greska prilikom citanja fajla");
		}
		return studenti;
	}

	private static Student parseStudent(String student) {
		String[] data = student.split("\\|");
		String ime = data[0];
		String prezime = data[1];
		long jmbg = Long.parseLong(data[2]);
		String username = data[3];
		String password = data[4];
		String imeRoditelja = data[5];
		int brojIndexa = Integer.parseInt(data[6]);
		Smer smer = Smer.valueOf(data[7]);
//		String datum = data[8];
		Date datumRodjenja = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy"); //Wed Jan 01 00:00:00 CET 1997
//			SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
			datumRodjenja = formatter.parse(data[8]);
		} catch (java.text.ParseException e) {
			System.out.println("Greska prilikom ucitavanja datuma");
			System.out.println(datumRodjenja);
		}
		;
		// String input = "Thu Jun 18 20:56:02 EDT 2009";
		// SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
		// Date date = parser.parse(input);
		// SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		// String formattedDate = formatter.format(date);

		String mesto = data[10];
		String email = data[9];
		String ulica = data[11];
		int ulicaBr = Integer.parseInt(data[12]);
		int tekucaGodina = Integer.parseInt(data[14]);
		int brojRacuna = Integer.parseInt(data[13]);
		int stanjeNaRacunu = Integer.parseInt(data[15]);
		return new Student(ime, prezime, jmbg, username, password, imeRoditelja, brojIndexa, smer, datumRodjenja, email,
				mesto, ulica, ulicaBr, brojRacuna, tekucaGodina, stanjeNaRacunu);
	}

	public static ArrayList<Predmet> ucitajPredmete() {
		String predmet;
		ArrayList<Predmet> predmeti = new ArrayList<Predmet>();
		try {
			BufferedReader in = new BufferedReader(new FileReader("src/txtDatoteke/Predmeti.txt"));
			while ((predmet = in.readLine()) != null) {
				predmeti.add(parsePredmet(predmet));
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("Fajl nije pronadjen");
		} catch (IOException e) {
			System.out.println("Greska prilikom citanja fajla");
		}
		return predmeti;
	}

	private static Predmet parsePredmet(String predmet) {
		String[] data = predmet.split("\\|");
		String naziv = data[0];
		Smer smer = Smer.valueOf(data[1]);
		int godinaStudija = Integer.parseInt(data[2]);
		int nedeljniFond = Integer.parseInt(data[3]);
		long profesor = Long.parseLong(data[4]);
		return new Predmet(naziv, smer, godinaStudija, nedeljniFond, profesor);
	}

	public static ArrayList<Profesor> ucitajProfesore() {
		String profesor;
		ArrayList<Profesor> profesori = new ArrayList<Profesor>();
		try {
			BufferedReader in = new BufferedReader(new FileReader("src/txtDatoteke/Profesori.txt"));
			while ((profesor = in.readLine()) != null) {
				profesori.add(parseProfesor(profesor.trim()));
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("Fajl nije pronadjen");
		} catch (IOException e) {
			System.out.println("Greska prilikom citanja fajla");
		}
		return profesori;
	}

	private static Profesor parseProfesor(String profesor) {
		String[] data = profesor.split("\\|");
		String ime = data[0];
		String prezime = data[1];
		long jmbg = Long.parseLong(data[2]);
		String username = data[3];
		String password = data[4];
		Zvanje zvanje = Zvanje.valueOf(data[5]);
		return new Profesor(ime, prezime, jmbg, username, password, zvanje);
	}

	public static ArrayList<Sluzbenik> ucitajSluzbenike() {
		String sluzbenik;
		ArrayList<Sluzbenik> sluzbenici = new ArrayList<Sluzbenik>();
		try {
			BufferedReader in = new BufferedReader(new FileReader("src/txtDatoteke/Sluzbenici.txt"));
			while (!((sluzbenik = in.readLine()) == null)) {
				sluzbenici.add(parseSluzbenik(sluzbenik));
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("Fajl nije pronadjen");
		} catch (IOException e) {
			System.out.println("Greska prilikom citanja fajla");
		}
		return sluzbenici;
	}

	public static ArrayList<String> ucitajPrijavljeneIspite() {
		String prIspit;
		ArrayList<String> prijavljeniIspiti = new ArrayList<String>();
		try {
			BufferedReader in = new BufferedReader(new FileReader("src/txtDatoteke/PrijavljeniIspiti.txt"));
			while (!((prIspit = in.readLine()) == null)) {
				prijavljeniIspiti.add(prIspit);
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("Fajl nije pronadjen");
		} catch (IOException e) {
			System.out.println("Greska prilikom citanja fajla");
		}
		return prijavljeniIspiti;
	}

	public static ArrayList<String> ucitajPolozeneIspite() {
		String ispit;
		ArrayList<String> polozeniIspiti = new ArrayList<String>();
		try {
			BufferedReader in = new BufferedReader(new FileReader("src/txtDatoteke/Ispiti.txt"));
			while (!((ispit = in.readLine()) == null)) {
				polozeniIspiti.add(ispit);
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("Fajl nije pronadjen");
		} catch (IOException e) {
			System.out.println("Greska prilikom citanja fajla");
		}
		return polozeniIspiti;
	}

	private static Sluzbenik parseSluzbenik(String sluzbenik) {
		String[] data = sluzbenik.split("\\|");
		String ime = data[0];
		String prezime = data[1];
		long jmbg = Long.parseLong(data[2]);
		String username = data[3];
		String password = data[4];
		return new Sluzbenik(ime, prezime, jmbg, username, password);
	}

	public static void upisiStudente() {// na kraju rada uvek pozivam funkciju
										// kako bi se sacuvale sve promene sa
										// bazom podataka
		try {
			PrintWriter out = new PrintWriter("src/txtDatoteke/Studenti.txt");
			for (Student student : Main.studenti) {
				out.println(student);
			}
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			System.out.println("Tražena datoteka ne postoji!");
		}
	}

	public static void upisiProfesore() {
		try {
			PrintWriter out = new PrintWriter("src/txtDatoteke/Profesori.txt");
			for (Profesor profesor : Main.profesori) {
				out.println(profesor);
			}
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			System.out.println("Tražena datoteka ne postoji!");
		}
	}

	public static void upisiPredmete() {
		try {
			PrintWriter out = new PrintWriter("src/txtDatoteke/Predmeti.txt");
			for (Predmet predmet : Main.predmeti) {
				out.println(predmet);
			}
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			System.out.println("Tražena datoteka ne postoji!");
		}
	}

	public static void upisiPrijavljeneIspite() {
		try {
			PrintWriter out = new PrintWriter("src/txtDatoteke/PrijavljeniIspiti.txt");
			for (String prIspit : Main.prijavljeniIspiti) {
				out.println(prIspit);
			}
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			System.out.println("Tražena datoteka ne postoji!");
		}
	}

	public static void upisiPolozeneIspite() {
		try {
			PrintWriter out = new PrintWriter("src/txtDatoteke/Ispiti.txt");
			for (String ispit : Main.polozeniIspiti) {
				out.println(ispit);
			}
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			System.out.println("Tražena datoteka ne postoji!");
		}
	}

}
