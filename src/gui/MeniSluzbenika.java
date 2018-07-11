package gui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import objects.Predmet;
import objects.Profesor;
import objects.Profesor.Zvanje;
import objects.Student;
import objects.Student.Smer;
import projekat.DataUtil;
import projekat.Main;

public class MeniSluzbenika {

	public static GridPane grid1 = upisiNovogStudenta();
	public static GridPane grid2 = upisiNovogProfesora();
	public static GridPane grid3 = upisiNoviPredmet();
	public static GridPane grid4 = evidentirajUplatu();
	public static GridPane grid5 = pretragaStudenata();
	public static StackPane stack6 = prikazStudenata(ucitajStudente());

	// uredjujem sadrzaj prvog taba
	public static GridPane upisiNovogStudenta() {

		GridPane grid = new GridPane();

		Label imeLbl = new Label("Ime:");
		grid.add(imeLbl, 0, 0);

		TextField imeTxt = new TextField();
		grid.add(imeTxt, 1, 0);

		Label prezimeLbl = new Label("Prezime:");
		grid.add(prezimeLbl, 0, 1);

		TextField prezimeTxt = new TextField();
		grid.add(prezimeTxt, 1, 1);

		Label jmbgLbl = new Label("Jmbg:");
		grid.add(jmbgLbl, 0, 2);

		TextField jmbgTxt = new TextField();
		grid.add(jmbgTxt, 1, 2);

		Label userLbl = new Label("Korisnicko ime:");
		grid.add(userLbl, 0, 3);

		TextField userTxt = new TextField();
		grid.add(userTxt, 1, 3);

		Label passLbl = new Label("Lozinka (mora sadrzati slova i brojeve):");
		grid.add(passLbl, 0, 4);

		PasswordField passTxt = new PasswordField();
		grid.add(passTxt, 1, 4);

		Label datumLbl = new Label("Datum rodjenja (dd.MM.yyyy.): ");
		grid.add(datumLbl, 0, 5);

		TextField datTxt = new TextField();
		grid.add(datTxt, 1, 5);

		Label indexLbl = new Label("Broj indeksa (pet cifara):");
		grid.add(indexLbl, 0, 6);

		TextField indexTxt = new TextField();
		grid.add(indexTxt, 1, 6);

		Label smerLbl = new Label("Smer:");
		grid.add(smerLbl, 0, 7);

		ToggleGroup smer = new ToggleGroup();
		ToggleButton s1 = new ToggleButton("SII");
		s1.setUserData(Smer.SII);
		ToggleButton s2 = new ToggleButton("IT");
		s2.setUserData(Smer.IT);
		smer.getToggles().add(s1);
		smer.getToggles().add(s2);
		grid.add(s1, 1, 7);
		grid.add(s2, 1, 8);

		Label godinaLbl = new Label("Godina:");
		grid.add(godinaLbl, 0, 9);

		TextField godina = new TextField();
		grid.add(godina, 1, 9);

		Label roditeljLbl = new Label("Ime roditelja:");
		grid.add(roditeljLbl, 2, 0);

		TextField roditeljTxt = new TextField();
		grid.add(roditeljTxt, 3, 0);

		Label emailLbl = new Label("Email:");
		grid.add(emailLbl, 2, 1);

		TextField emailTxt = new TextField();
		grid.add(emailTxt, 3, 1);

		Label mestoLbl = new Label("Mesto:");
		grid.add(mestoLbl, 2, 2);

		TextField mestoTxt = new TextField();
		grid.add(mestoTxt, 3, 2);

		Label ulicaLbl = new Label("Ulica:");
		grid.add(ulicaLbl, 2, 3);

		TextField ulicaTxt = new TextField();
		grid.add(ulicaTxt, 3, 3);

		Label brojUliceLbl = new Label("Broj stana/kuce:");
		grid.add(brojUliceLbl, 2, 4);

		TextField brojUliceTxt = new TextField();
		grid.add(brojUliceTxt, 3, 4);

		Label brojRacunaLbl = new Label("Broj racuna (sest cifara):");
		grid.add(brojRacunaLbl, 2, 5);

		TextField brojRacunaTxt = new TextField();
		grid.add(brojRacunaTxt, 3, 5);

		Button registerBtn = new Button("Dodaj studenta");
		grid.add(registerBtn, 0, 13);

		registerBtn.setOnAction(e -> {
			napraviStudenta(imeTxt, prezimeTxt, jmbgTxt, userTxt, passTxt, roditeljTxt, indexTxt, smer, datTxt,
					emailTxt, mestoTxt, ulicaTxt, brojUliceTxt, brojRacunaTxt, godina);
			imeTxt.clear();
			prezimeTxt.clear();
			jmbgTxt.clear();
			userTxt.clear();
			passTxt.clear();
			roditeljTxt.clear();
			indexTxt.clear();
			datTxt.clear();
			emailTxt.clear();
			mestoTxt.clear();
			ulicaTxt.clear();
			brojUliceTxt.clear();
			brojRacunaTxt.clear();
			godina.clear();
		});
		return grid;
	}

	public static void napraviStudenta(TextField imeS, TextField prezimeS, TextField jmbgS, TextField userS,
			PasswordField passS, TextField roditeljS, TextField indexS, ToggleGroup smerS, TextField datumS,
			TextField emailS, TextField mestoS, TextField ulicaS, TextField brUliceS, TextField brRacunaS,
			TextField godinaS) {
		boolean indikator = true;
		Student s;
		String ime = "";
		String prezime = "";
		String jmbg = "";
		String username = "";
		String password = "";
		String imeRoditelja = "";
		int brojIndexa = 0;
		Smer smer = null;
		Date datumRodjenja = null;
		String email = "";
		String mesto = "";
		String ulica = "";
		int ulicaBr = 0;
		int brojRacuna = 0;
		int tekucaGodina = 0;
		int stanjeNaRacunu = 0;

		if (!imeS.getText().trim().matches("[a-zA-Z]+")) {
			imeS.clear();
			imeS.setStyle("-fx-text-box-border: red");
			indikator = false;
		} else {
			ime = imeS.getText();
		}
		;
		if (!prezimeS.getText().trim().matches("[a-zA-Z]+")) {
			prezimeS.clear();
			prezimeS.setStyle("-fx-text-box-border: red");
			indikator = false;
		} else {
			prezime = prezimeS.getText();
		}
		;
		if (!jmbgS.getText().matches("[0-9]{13}")) {
			jmbgS.clear();
			jmbgS.setStyle("-fx-text-box-border: red");
			indikator = false;
		} else {
			jmbg = jmbgS.getText();
		}
		;
		if (!userS.getText().trim().matches("[a-zA-Z]+")) {
			userS.clear();
			userS.setStyle("-fx-text-box-border: red");
			indikator = false;
		} else {
			username = userS.getText();
		}
		;
		if (!passS.getText().matches("[a-zA-Z]+[0-9]+")) {
			passS.clear();
			passS.setStyle("-fx-text-box-border: red");
			indikator = false;
		} else {
			password = passS.getText();
		}
		;
		if (!roditeljS.getText().trim().matches("[a-zA-Z]+")) {
			roditeljS.clear();
			roditeljS.setStyle("-fx-text-box-border: red");
			indikator = false;
		} else {
			imeRoditelja = roditeljS.getText();
		}
		;
		try {
			if (!(Integer.parseInt(indexS.getText()) >= 10000 && Integer.parseInt(indexS.getText()) <= 99999)) {
				indexS.clear();
				indexS.setStyle("-fx-text-box-border: red");
				indikator = false;
			} else {
				brojIndexa = Integer.parseInt(indexS.getText());
			}
		} catch (NumberFormatException e) {
			indexS.clear();
			indexS.setStyle("-fx-text-box-border: red");
			indikator = false;
		}
		;
		try {
			if (smerS.getSelectedToggle().getUserData().equals(Smer.IT)) {
				smer = Smer.valueOf("IT");
			}
			;
			if (smerS.getSelectedToggle().getUserData().equals(Smer.SII)) {
				smer = Smer.valueOf("SII");
			}
		} catch (NullPointerException e) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("Niste izabrali smer!");
			indikator = false;
		}
		;
		try {
			SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy.");
			datumRodjenja = df.parse(datumS.getText());
		} catch (ParseException a) {
			datumS.setStyle("-fx-text-box-border: red");
			indikator = false;
		} catch (java.lang.ClassCastException a) {
			datumS.setStyle("-fx-text-box-border: red");
			indikator = false;
		}
		;
		if (!emailS.getText().contains("@")) {
			emailS.setStyle("fx-text-box-border: red");
			indikator = false;
		} else {
			email = emailS.getText();
		}
		;
		if (!mestoS.getText().trim().matches("[a-zA-Z]+")) {
			mestoS.setStyle("fx-text-box-border: red");
			indikator = false;
		} else {
			mesto = mestoS.getText();
			;
		}
		;
		if (!ulicaS.getText().trim().matches("[a-zA-Z]+")) {
			ulicaS.setStyle("fx-text-box-border: red");
			indikator = false;
		} else {
			ulica = ulicaS.getText();
		}
		;
		try {
			if (Integer.valueOf(brUliceS.getText()) == 0) {
				brUliceS.setStyle("fx-text-box-border: red");
				indikator = false;
			} else {
				ulicaBr = Integer.valueOf(brUliceS.getText());
			}
		} catch (NumberFormatException e) {
			brUliceS.setStyle("fx-text-box-border: red");
		}
		;
		try {
			if (!(Integer.valueOf(brRacunaS.getText()) > 100000 && Integer.valueOf(brRacunaS.getText()) < 999999)) {
				brRacunaS.setStyle("fx-text-box-border: red");
				indikator = false;
			} else {
				brojRacuna = Integer.valueOf(brRacunaS.getText());
			}
		} catch (NumberFormatException e) {
			brRacunaS.setStyle("fx-text-box-border: red");
		}
		;
		try {
			if (!(Integer.valueOf(godinaS.getText()) >= 1 && Integer.valueOf(godinaS.getText()) <= 4)) {
				godinaS.setStyle("fx-text-box-border: red");
				indikator = false;
			} else {
				tekucaGodina = Integer.valueOf(godinaS.getText());
			}
		} catch (NumberFormatException e) {
			godinaS.setStyle("fx-text-box-border: red");
		}
		;
		if (indikator == true) {
			s = new Student(ime, prezime, Long.parseLong(jmbg), username, password, imeRoditelja, brojIndexa, smer,
					datumRodjenja, email, mesto, ulica, ulicaBr, brojRacuna, tekucaGodina, stanjeNaRacunu);
			projekat.Main.studenti.add(s);
			projekat.DataUtil.upisiStudente();
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("Uspesno ste uneli novog studenta");
			alert.showAndWait();

		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Niste uneli validne podatke");
			alert.showAndWait();
		}
	}

	// uredjujem prikaz sadrzaja drugog taba
	public static GridPane upisiNovogProfesora() {

		GridPane grid = new GridPane();

		Label imeLbl = new Label("Ime:");
		grid.add(imeLbl, 0, 0);

		TextField imeTxt = new TextField();
		grid.add(imeTxt, 1, 0);

		Label prezimeLbl = new Label("Prezime:");
		grid.add(prezimeLbl, 0, 1);

		TextField prezimeTxt = new TextField();
		grid.add(prezimeTxt, 1, 1);

		Label jmbgLbl = new Label("Jmbg:");
		grid.add(jmbgLbl, 0, 2);

		TextField jmbgTxt = new TextField();
		grid.add(jmbgTxt, 1, 2);

		Label userLbl = new Label("Korisnicko ime:");
		grid.add(userLbl, 0, 3);

		TextField userTxt = new TextField();
		grid.add(userTxt, 1, 3);

		Label passLbl = new Label("Lozinka (mora sadrzati slova i brojeve):");
		grid.add(passLbl, 0, 4);

		PasswordField passTxt = new PasswordField();
		grid.add(passTxt, 1, 4);

		Label zvanjeLbl = new Label("Zvanje:");
		grid.add(zvanjeLbl, 0, 5);

		ToggleGroup zvanje = new ToggleGroup();
		ToggleButton z1 = new ToggleButton("Docent");
		z1.setUserData(Zvanje.DOCENT);
		ToggleButton z2 = new ToggleButton("Redovni");
		z2.setUserData(Zvanje.REDOVNI);
		ToggleButton z3 = new ToggleButton("Vanredovni");
		z3.setUserData(Zvanje.VANREDOVNI);

		zvanje.getToggles().add(z1);
		zvanje.getToggles().add(z2);
		zvanje.getToggles().add(z3);
		grid.add(z1, 1, 5);
		grid.add(z2, 1, 6);
		grid.add(z3, 1, 7);

		Button registerBtn = new Button("Dodaj profesora");
		grid.add(registerBtn, 0, 8);
		registerBtn.setOnAction(e -> {
			napraviProfesora(imeTxt, prezimeTxt, jmbgTxt, userTxt, passTxt, zvanje);
			imeTxt.clear();
			prezimeTxt.clear();
			jmbgTxt.clear();
			userTxt.clear();
			passTxt.clear();
		});

		return grid;
	}

	public static void napraviProfesora(TextField imeS, TextField prezimeS, TextField jmbgS, TextField userS,
			PasswordField passS, ToggleGroup zvanjeS) {
		Profesor p;
		String ime = "";
		String prezime = "";
		String jmbg = "";
		String username = "";
		String password = "";
		Zvanje zvanje = null;
		boolean indikator = true;

		if (!imeS.getText().trim().matches("[a-zA-Z]+")) {
			imeS.clear();
			imeS.setStyle("-fx-text-box-border: red");
			indikator = false;
		} else {
			ime = imeS.getText();
		}
		;
		if (!prezimeS.getText().trim().matches("[a-zA-Z]+")) {
			prezimeS.clear();
			prezimeS.setStyle("-fx-text-box-border: red");
			indikator = false;
		} else {
			prezime = prezimeS.getText();
		}
		;
		if (!jmbgS.getText().matches("[0-9]{13}")) {
			jmbgS.clear();
			jmbgS.setStyle("-fx-text-box-border: red");
			indikator = false;
		} else {
			jmbg = jmbgS.getText();
		}
		;
		if (!userS.getText().trim().matches("[a-zA-Z]+")) {
			userS.clear();
			userS.setStyle("-fx-text-box-border: red");
			indikator = false;
		} else {
			username = userS.getText();
		}
		;
		if (!passS.getText().trim().matches("[a-zA-Z]+[0-9]+")) {
			passS.clear();
			passS.setStyle("-fx-text-box-border: red");
			indikator = false;
		} else {
			password = passS.getText();
		}
		;
		try {
			if (zvanjeS.getSelectedToggle().getUserData().equals(Zvanje.DOCENT)) {
				zvanje = Zvanje.DOCENT;
			}
			;
			if (zvanjeS.getSelectedToggle().getUserData().equals(Zvanje.REDOVNI)) {
				zvanje = Zvanje.REDOVNI;
			}
			;
			if (zvanjeS.getSelectedToggle().getUserData().equals(Zvanje.VANREDOVNI)) {
				zvanje = Zvanje.VANREDOVNI;
			}
			;
		} catch (NullPointerException e) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("Niste izabrali zvanje!");
			indikator = false;
		}
		;

		if (indikator == true) {
			p = new Profesor(ime, prezime, Long.parseLong(jmbg), username, password, zvanje);
			projekat.Main.profesori.add(p);
			projekat.DataUtil.upisiProfesore();
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("Uspesno ste uneli novog profesora");
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Niste uneli validne podatke");
			alert.showAndWait();
		}
	}

	public static GridPane upisiNoviPredmet() {

		GridPane grid = new GridPane();

		Label nazivLbl = new Label("Naziv predmeta:");
		grid.add(nazivLbl, 0, 0);

		TextField nazivTxt = new TextField();
		grid.add(nazivTxt, 1, 0);

		Label godinaLbl = new Label("Godina studija:");
		grid.add(godinaLbl, 0, 1);

		TextField godinaTxt = new TextField();
		grid.add(godinaTxt, 1, 1);

		Label smerLbl = new Label("Smer:");
		grid.add(smerLbl, 0, 5);

		ToggleGroup smer = new ToggleGroup();
		ToggleButton s1 = new ToggleButton("SII");
		s1.setUserData(Smer.SII);
		ToggleButton s2 = new ToggleButton("IT");
		s2.setUserData(Smer.IT);
		smer.getToggles().add(s1);
		smer.getToggles().add(s2);
		grid.add(s1, 1, 5);
		grid.add(s2, 1, 6);

		Label fondLbl = new Label("Nedeljni fond casova:");
		grid.add(fondLbl, 0, 7);

		TextField fondTxt = new TextField();
		grid.add(fondTxt, 1, 7);

		Label profLbl = new Label("Unesite jmbg profesora:");
		grid.add(profLbl, 0, 8);

		TextField profTxt = new TextField();
		grid.add(profTxt, 1, 8);

		Button registerBtn = new Button("Dodaj predmet");
		grid.add(registerBtn, 0, 10);
		registerBtn.setOnAction(e -> {
			napraviPredmet(nazivTxt, godinaTxt, smer, fondTxt, profTxt);
			nazivTxt.clear();
			godinaTxt.clear();
			fondTxt.clear();
			profTxt.clear();
		});

		return grid;

	}

	public static void napraviPredmet(TextField nazivS, TextField godinaS, ToggleGroup smerS, TextField fondS,
			TextField profS) {
		Predmet p;
		String naziv = "";
		Smer smer = null;
		int godina = 0;
		int fond = 0;
		long prof = 0;
		boolean indikator = true;

		if (nazivS.getText().matches("[a-zA-Z]+")) {
			naziv = nazivS.getText();
		} else {
			nazivS.clear();
			nazivS.setStyle("-fx-text-box-border: red");
			indikator = false;
		}
		;
		System.out.println(indikator);
		try {
			if (Integer.parseInt(godinaS.getText()) >= 1 && Integer.parseInt(godinaS.getText()) <= 4) {
				godina = Integer.parseInt(godinaS.getText());
			} else {
				godinaS.clear();
				godinaS.setStyle("fx-text-box-border: red");
				indikator = false;
			}
		} catch (NumberFormatException e) {
			godinaS.setStyle("fx-text-box-border: red");
		}
		;
		System.out.println(indikator);
		try {
			if (Integer.parseInt(fondS.getText()) >= 1 && Integer.parseInt(fondS.getText()) <= 10) {
				fond = Integer.parseInt(fondS.getText());
			} else {
				fondS.clear();
				fondS.setStyle("fx-text-box-border: red");
				indikator = false;
			}
		} catch (NumberFormatException e) {
			fondS.setStyle("fx-text-box-border: red");
		}
		;
		System.out.println(indikator);
		try {
			for (Profesor profesor : projekat.Main.profesori) {
				if (profesor.getJmbg() == Long.valueOf(profS.getText())) {
					prof = Long.valueOf(profS.getText());
					break;
				} else {
					profS.clear();
					profS.setStyle("-fx-text-box-border: red");
					indikator = false;
				}
				;
			}
		} catch (NumberFormatException e) {
			fondS.setStyle("fx-text-box-border: red");
		}
		;
		System.out.println(indikator);
		try {
			if (smerS.getSelectedToggle().getUserData().equals(Smer.IT)) {
				smer = Smer.valueOf("IT");
			}
			;
			if (smerS.getSelectedToggle().getUserData().equals(Smer.SII)) {
				smer = Smer.valueOf("SII");
			}
		} catch (NullPointerException e) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("Niste izabrali smer!");
			indikator = false;
		}
		;
		System.out.println(indikator);
		if (indikator == true) {
			p = new Predmet(naziv, smer, godina, fond, prof);
			Main.predmeti.add(p);// dodam na listu predmeta
			DataUtil.upisiPredmete();// i upisem u fajl
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("Uspesno ste uneli novi predmet");
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Niste uneli validne podatke");
			alert.showAndWait();
		}
		;
	}

	public static GridPane evidentirajUplatu() {
		GridPane grid = new GridPane();

		Label indexLbl = new Label("Unesite broj indexa:");
		grid.add(indexLbl, 0, 0);

		TextField indexTxt = new TextField();
		grid.add(indexTxt, 1, 0);

		Label iznosLbl = new Label("Unesite iznos uplate:");
		grid.add(iznosLbl, 0, 1);

		TextField iznosTxt = new TextField();
		grid.add(iznosTxt, 1, 1);

		Button uplataBtn = new Button("Izvrsi uplatu");
		grid.add(uplataBtn, 0, 3);
		uplataBtn.setOnAction(e -> {
			int iznos = 0;
			for (Student student : projekat.Main.studenti) {
				try {
					if (student.getBrojIndexa() == Integer.valueOf(indexTxt.getText())) {
						try {
							iznos = Integer.valueOf(iznosTxt.getText());
							student.setStanjeNaRacunu(student.getStanjeNaRacunu() - iznos);
							Alert alert = new Alert(AlertType.CONFIRMATION);
							alert.setContentText("Uspesno ste uneli novog profesora");
							alert.showAndWait();
						} catch (NumberFormatException e2) {
							iznosTxt.setStyle("fx-text-box-border: red");
						}
					}
				} catch (NumberFormatException a) {
					indexTxt.setStyle("fx-text-box-border: red");
				}
			}
			;
			indexTxt.clear();
			iznosTxt.clear();
		});

		return grid;
	}

	public static GridPane pretragaStudenata() {
		GridPane grid = new GridPane();

		Label naslov = new Label("Pretraga studenata prema: ");
		grid.add(naslov, 0, 0);

		Label indexLbl = new Label("Broju indeksa: ");
		grid.add(indexLbl, 0, 1);

		TextField indexTxt = new TextField();
		grid.add(indexTxt, 1, 1);

		Label ili = new Label(" ili ");
		grid.add(ili, 0, 2);

		Label prezLbl = new Label("Prezimenu: ");
		grid.add(prezLbl, 0, 3);

		TextField prezTxt = new TextField();
		grid.add(prezTxt, 1, 3);

		Button pretragaBtn = new Button("Izvrsi pretragu");
		grid.add(pretragaBtn, 0, 4);
		pretragaBtn.setOnAction(e -> {
			grid.add(new StackPane(pretragaStudenata(prezTxt, indexTxt)), 1, 5);
			indexTxt.clear();
			prezTxt.clear();
		});

		return grid;

	}

	public static StackPane pretragaStudenata(TextField prez, TextField ind) {
		int index;
		String prezime;
		StackPane sp = new StackPane();
		boolean indikator = false;
		if (!ind.getText().isEmpty()) {
			try {
				index = Integer.valueOf(ind.getText());
				for (Student student : projekat.Main.studenti) {
					if (index == student.getBrojIndexa()) {
						indikator = true;
						sp = prikazStudenata(pretraziStudenteIndex(index));
					}
				}
			} catch (NumberFormatException e2) {
				ind.setStyle("fx-text-box-border: red");
			}
			;
		}
		;
		if (!prez.getText().trim().isEmpty()) {
			try {
				prezime = prez.getText();
				System.out.println(prezime);
				for (Student student : projekat.Main.studenti) {
					if (String.CASE_INSENSITIVE_ORDER.compare(student.getPrezime(), prezime) == 0) {
						sp = prikazStudenata(pretraziStudentePrezime(prezime));
						indikator = true;
					}
				}
			} catch (NumberFormatException e2) {
				prez.setStyle("fx-text-box-border: red");
			}
		}
		;
		if (indikator == false) {
			Alert eror = new Alert(AlertType.ERROR);
			eror.setContentText("Nije pronadjen ni jedan student koji odgovara zahtevima pretrage");

		}
		;
		return sp;
	}

	public static ObservableList<Student> pretraziStudenteIndex(int i) {
		ObservableList<Student> studenti = FXCollections.observableArrayList();
		for (Student student : projekat.Main.studenti) {
			if (i == student.getBrojIndexa()) {
				studenti.add(new Student(student.getIme(), student.getPrezime(), student.getJmbg(),
						student.getUsername(), "***", student.getImeRoditelja(), student.getBrojIndexa(),
						student.getSmer(), student.getDatumRodjenja(), student.getEmail(), student.getMesto(),
						student.getUlica(), student.getUlicaBr(), student.getBrojRacuna(), student.getTekucaGodina(),
						student.getStanjeNaRacunu()));
			}
		}
		return studenti;
	}

	public static ObservableList<Student> pretraziStudentePrezime(String p) {
		ObservableList<Student> studenti = FXCollections.observableArrayList();
		for (Student student : projekat.Main.studenti) {
			if (String.CASE_INSENSITIVE_ORDER.compare(student.getPrezime(), p) == 0) {
				studenti.add(new Student(student.getIme(), student.getPrezime(), student.getJmbg(),
						student.getUsername(), "***", student.getImeRoditelja(), student.getBrojIndexa(),
						student.getSmer(), student.getDatumRodjenja(), student.getEmail(), student.getMesto(),
						student.getUlica(), student.getUlicaBr(), student.getBrojRacuna(), student.getTekucaGodina(),
						student.getStanjeNaRacunu()));
			}
		}
		return studenti;
	}

	@SuppressWarnings("unchecked")
	public static StackPane prikazStudenata(ObservableList<Student> s) {

		projekat.Main.sluzbenici = DataUtil.ucitajSluzbenike();
		projekat.Main.profesori = DataUtil.ucitajProfesore();
		projekat.Main.studenti = DataUtil.ucitajStudente();
		projekat.Main.predmeti = DataUtil.ucitajPredmete();
		projekat.Main.prijavljeniIspiti = DataUtil.ucitajPrijavljeneIspite();
		projekat.Main.polozeniIspiti = DataUtil.ucitajPolozeneIspite();
		StackPane stack = new StackPane();
		TableView<Student> table = new TableView<Student>();

		TableColumn<Student, String> imeCol = new TableColumn<Student, String>("Ime");
		TableColumn<Student, String> prezCol = new TableColumn<Student, String>("Prezime");
		TableColumn<Student, Long> jmbgCol = new TableColumn<>("Jmbg");
		TableColumn<Student, String> userCol = new TableColumn<>("Korisnicko ime");
		TableColumn<Student, Integer> indexCol = new TableColumn<>("Broj indexa");
		TableColumn<Student, Smer> smerCol = new TableColumn<>("Smer");
		TableColumn<Student, Date> datumCol = new TableColumn<>("Datum rodjenja");
		TableColumn<Student, Integer> godinaCol = new TableColumn<>("Godina studija");
		TableColumn<Student, String> roditeljCol = new TableColumn<>("Ime roditelja");
		TableColumn<Student, String> emailCol = new TableColumn<Student, String>("Email");
		TableColumn<Student, String> mestoCol = new TableColumn<Student, String>("Mesto");
		TableColumn<Student, String> ulicaCol = new TableColumn<Student, String>("Ulica");
		TableColumn<Student, Integer> brojUlCol = new TableColumn<Student, Integer>("Broj stana/kuce");
		TableColumn<Student, Integer> racunCol = new TableColumn<Student, Integer>("Broj racuna");
		TableColumn<Student, Integer> stanjeCol = new TableColumn<>("Stanje na racunu");

		table.getColumns().addAll(imeCol, prezCol, jmbgCol, userCol, indexCol, smerCol, datumCol, godinaCol,
				roditeljCol, emailCol, mestoCol, ulicaCol, brojUlCol, racunCol, stanjeCol);

		imeCol.setCellValueFactory(new PropertyValueFactory<Student, String>("ime"));
		prezCol.setCellValueFactory(new PropertyValueFactory<Student, String>("prezime"));
		jmbgCol.setCellValueFactory(new PropertyValueFactory<Student, Long>("jmbg"));
		userCol.setCellValueFactory(new PropertyValueFactory<Student, String>("username"));
		indexCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("brojIndexa"));
		smerCol.setCellValueFactory(new PropertyValueFactory<Student, Smer>("smer"));
		datumCol.setCellValueFactory(new PropertyValueFactory<Student, Date>("datumRodjenja"));
		godinaCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("tekucaGodina"));
		roditeljCol.setCellValueFactory(new PropertyValueFactory<Student, String>("imeRoditelja"));
		emailCol.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
		mestoCol.setCellValueFactory(new PropertyValueFactory<Student, String>("mesto"));
		ulicaCol.setCellValueFactory(new PropertyValueFactory<Student, String>("ulica"));
		brojUlCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("ulicaBr"));
		racunCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("brojRacuna"));
		stanjeCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("stanjeNaRacunu"));

		table.setItems(s);

		stack.getChildren().add(table);
		return stack;
	}

	// ucitam studente da bih dobila listu za pregled
	public static ObservableList<Student> ucitajStudente() {
		projekat.Main.studenti = projekat.DataUtil.ucitajStudente();
		ObservableList<Student> studenti = FXCollections.observableArrayList();
		for (Student student : projekat.Main.studenti) {
			studenti.add(new Student(student.getIme(), student.getPrezime(), student.getJmbg(), student.getUsername(),
					"***", student.getImeRoditelja(), student.getBrojIndexa(), student.getSmer(),
					student.getDatumRodjenja(), student.getEmail(), student.getMesto(), student.getUlica(),
					student.getUlicaBr(), student.getBrojRacuna(), student.getTekucaGodina(),
					student.getStanjeNaRacunu()));
		}
		return studenti;
	}

	public static void sacuvajPodatke() {
		projekat.DataUtil.upisiProfesore();
		projekat.DataUtil.upisiStudente();
		projekat.DataUtil.upisiPredmete();
		Meni.window.close();
		
	}
}
