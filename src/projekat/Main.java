package projekat;

import java.util.ArrayList;

import objects.Predmet;
import objects.Profesor;
import objects.Sluzbenik;
import objects.Student;

public class Main {

	public static ArrayList<Predmet> predmeti;// inicijalne liste sa podacima
	public static ArrayList<Profesor> profesori;
	public static ArrayList<Student> studenti;
	public static ArrayList<Sluzbenik> sluzbenici;
	public static ArrayList<String> prijavljeniIspiti;
	public static ArrayList<String> polozeniIspiti;
	public static String user;
	public static String pass;
	public static int x = 0;
	public static long ulogovan = 0;

	public static void main(String[] args) {
		Sluzbenik.upisiSluzbenika();// iz liste upisuje dva sluzbenika
									// inicijalno u fajlove da bi se moglo
									// ulogovati na pocetku, ne postoji funkcija
									// za pravljenje novog sluzbenika
		sluzbenici = DataUtil.ucitajSluzbenike();
		profesori = DataUtil.ucitajProfesore();
		studenti = DataUtil.ucitajStudente();
		predmeti = DataUtil.ucitajPredmete();
		prijavljeniIspiti = DataUtil.ucitajPrijavljeneIspite();
		polozeniIspiti = DataUtil.ucitajPolozeneIspite();
		boolean start = true;
		while (start == true) {
			System.out.println("Početno logovanje:\n*Za prekid programa unesite exit*");
			System.out.println("Unesite korisničko ime: ");
			user = Skener.input.nextLine();
			if (user.equalsIgnoreCase("exit")) {
				System.out.println("Kraj programa");
				DataUtil.upisiProfesore();// upisujem sve promene podataka
				DataUtil.upisiStudente();
				DataUtil.upisiPredmete();
				DataUtil.upisiPrijavljeneIspite();
				DataUtil.upisiPolozeneIspite();
				Skener.input.close();
				break;
			}
			System.out.println("Unesite šifru: ");
			pass = Skener.input.nextLine();
			ulogovan = DataUtil.pretraziKorisnike(user, pass);
			start = false;
		}
		DataUtil.prikaziMeni(x);
	}

}
