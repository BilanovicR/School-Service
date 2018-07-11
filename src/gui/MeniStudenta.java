package gui;

import java.util.ArrayList;
import java.util.Date;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import objects.Predmet;
import objects.Student;
import projekat.DataUtil;
import projekat.Main;

public class MeniStudenta {

	public static GridPane grid1 = prijaviIspit();
	public static GridPane grid2 = odjaviIspit();
	public static GridPane grid3 = polozeniIspiti();
	public static GridPane grid4 = prijavljeniIspitiST();
	public static GridPane grid5 = rezultatiIspita();
	public static GridPane grid6 = prosecnaOcena();
	public static GridPane grid7 = tekuciPredmeti();

	public static GridPane prijaviIspit() throws NullPointerException {
		projekat.Main.studenti = DataUtil.ucitajStudente();
		projekat.Main.prijavljeniIspiti = DataUtil.ucitajPrijavljeneIspite();
		@SuppressWarnings("deprecation")
		Date start = new Date(117, 7, 17);// offset za godinu je -1900
		@SuppressWarnings("deprecation")
		Date finish = new Date(117, 8, 3);
		Date now = new Date();
		GridPane grid = new GridPane();
		Label naslov = new Label("Ispiti koje mozete prijaviti: ");
		grid.add(naslov, 0, 0);
		int brojac = 0;
		ArrayList<Predmet> ispiti = new ArrayList<Predmet>();
		for (Student student : Main.studenti) {
			if (Main.ulogovan == student.getJmbg()) {
				if (now.after(start) && now.before(finish)) {
					if (student.getStanjeNaRacunu() >= 200) {						
						for (Predmet predmet : projekat.Main.predmeti) {
							if (student.getSmer() == predmet.getSmer()) {
								brojac++;
								Label ispit = new Label(predmet.getNaziv());
								ispit.setId(predmet.getNaziv());
								grid.add(ispit, 0, brojac);
								Button btn = new Button("Prijavi ispit");
								btn.setOnAction(e -> {
									projekat.Main.prijavljeniIspiti.add(predmet.getNaziv() + "|" + student.getJmbg());
									student.setStanjeNaRacunu((student.getStanjeNaRacunu()) - 200);
									projekat.DataUtil.upisiPrijavljeneIspite();
									btn.setText("Prijavljen");
									btn.setDisable(true);
								});
								grid.add(btn, 1, brojac);
								ispiti.add(predmet);
							}
						}
						;
					} else {
						Label alert1 = new Label("Nemate dovoljno sredstava na racunu");
						grid.add(alert1, 0, 1);
					}
				} else {
					Label alert2 = new Label("Trenutno ne postoji ispitni rok za koji mozete prijaviti ispit");
					grid.add(alert2, 0, 3);
				}
			}
		}
		return grid;
	}

	public static GridPane odjaviIspit() {
		projekat.Main.studenti = DataUtil.ucitajStudente();
		projekat.Main.prijavljeniIspiti = DataUtil.ucitajPrijavljeneIspite();
		GridPane grid = new GridPane();
		Label naslov = new Label("Ispiti koje mozete odjaviti: ");
		grid.add(naslov, 0, 0);
		int brojac = 0;
		boolean indikator = false;
		for (String ispit : projekat.Main.prijavljeniIspiti) {
			String[] data = ispit.split("\\|");
			if (projekat.Main.ulogovan == Long.valueOf(data[1])) {
				brojac++;
				Label prijavljen = new Label(data[0]);
				prijavljen.setId(data[0]);
				grid.add(prijavljen, 0, brojac);
				Button btn = new Button("Odjavi ispit");
				btn.setOnAction(e -> {
					projekat.Main.prijavljeniIspiti.remove(ispit);
					projekat.DataUtil.upisiPrijavljeneIspite();
					btn.setText("Odjavljen");
					btn.setDisable(true);
				});
				grid.add(btn, 1, brojac);
				indikator = true;
			}
		}
		;
		if (indikator == false) {
			Label alert1 = new Label("Niste prijavili ni jedan ispit");
			grid.add(alert1, 0, 1);
		}
		return grid;
	}

	public static GridPane polozeniIspiti() {
		GridPane grid = new GridPane();
		Label naslov = new Label("Polozeni ispiti: ");
		grid.add(naslov, 0, 0);
		int brojac = 0;
		boolean indikator = false;
		for (String ispit : projekat.Main.polozeniIspiti) {
			String[] data = ispit.split("\\|");
			if (projekat.Main.ulogovan == Long.valueOf(data[1])) {
				brojac++;
				Label polozen = new Label(data[0] + " ocena: " + data[2]);
				grid.add(polozen, 0, brojac);
				indikator = true;
			}
		}
		;
		if (indikator == false) {
			Label alert1 = new Label("Nemate polozen ni jedan ispit");
			grid.add(alert1, 0, 1);
		}
		return grid;
	}

	public static GridPane prijavljeniIspitiST() {
		projekat.Main.studenti = DataUtil.ucitajStudente();
		projekat.Main.prijavljeniIspiti = DataUtil.ucitajPrijavljeneIspite();
		GridPane grid = new GridPane();
		Label naslov = new Label("Prijavljeni ispiti: ");
		grid.add(naslov, 0, 0);
		int brojac = 0;
		boolean indikator = false;
		for (String ispit : projekat.Main.prijavljeniIspiti) {
			String[] data = ispit.split("\\|");
			if (projekat.Main.ulogovan == Long.valueOf(data[1])) {
				brojac++;
				Label prijavljen = new Label(data[0]);
				grid.add(prijavljen, 0, brojac);
				indikator = true;
			}
		}
		;
		if (indikator == false) {
			Label alert1 = new Label("Niste prijavili ni jedan ispit");
			grid.add(alert1, 0, 1);
		}
		return grid;
	}

	public static GridPane rezultatiIspita() {
		GridPane grid = new GridPane();
		Label naslov = new Label("Rezultati ispita: ");
		grid.add(naslov, 0, 0);
		int brojac = 0;
		boolean indikator = false;
		for (String ispit : projekat.Main.polozeniIspiti) {
			String[] data = ispit.split("\\|");
			if (projekat.Main.ulogovan == Long.valueOf(data[1])) {
				brojac++;
				Label polozen = new Label(data[0] + " ocena: " + data[2]);
				grid.add(polozen, 0, brojac);
				indikator = true;
			}
		}
		;
		if (indikator == false) {
			Label alert1 = new Label("Niste polozili ni jedan ispit");
			grid.add(alert1, 0, 1);
		}
		return grid;
	}

	public static GridPane prosecnaOcena() {
		GridPane grid = new GridPane();
		int brIspit = 0;
		float prosek = 0;
		float suma = 0;
		int indeks = 0;
		for (Student student : projekat.Main.studenti) {
			if (student.getJmbg() == projekat.Main.ulogovan ) {
				indeks = student.getBrojIndexa();
			}
		}
		for (String ispit : projekat.Main.polozeniIspiti) {
			String[] data = ispit.split("\\|");
			if (Integer.valueOf(data[1]) == indeks) {
				brIspit++;
				suma = suma + Float.valueOf(data[2]);
			}
		}
		;
		prosek = suma / brIspit;
		Label l1 = new Label("Ukupno ste polozili: " + brIspit + " ispita");
		grid.add(l1, 0, 0);
		Label l2 = new Label("Vasa prosecna ocena je: " + prosek);
		grid.add(l2, 0, 1);
		return grid;

	}

	public static GridPane tekuciPredmeti() {
		GridPane grid = new GridPane();
		Label naslov = new Label("Predmeti koje imate u tekucoj godini: ");
		grid.add(naslov, 0, 0);
		int brojac = 0;
		for (Student student : projekat.Main.studenti) {
			if (student.getJmbg() == projekat.Main.ulogovan) {
				for (Predmet predmet : projekat.Main.predmeti) {
					if (student.getSmer() == predmet.getSmer()
							&& student.getTekucaGodina() == predmet.getGodinaStudija()) {
						Label pr = new Label(predmet.getNaziv());
						++brojac;
						grid.add(pr, 0, brojac);
					}
				}
			}
		}
		return grid;
	}

	public static void sacuvajPodatke() {
		projekat.DataUtil.upisiProfesore();// upisujem sve promene podataka
		projekat.DataUtil.upisiStudente();
		projekat.DataUtil.upisiPredmete();
		projekat.DataUtil.upisiPrijavljeneIspite();
		projekat.DataUtil.upisiPolozeneIspite();
		Meni.window.close();
		
	}

}
