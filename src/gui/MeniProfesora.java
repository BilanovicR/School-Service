package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import objects.Predmet;
import objects.Student;
import objects.Student.Smer;

public class MeniProfesora {

	public static GridPane grid1 = prijavljeniIspitiPR();
	public static GridPane grid2 = unesiRezultate();
	public static GridPane grid3 = izlistajPredmete();

	public static GridPane prijavljeniIspitiPR() {
		GridPane grid = new GridPane();
		int br1 = 0;
		int br2 = 0;
		Label naslovIs = new Label("Predmet: ");
		grid.add(naslovIs, 0, br1++);
		Label naslovPr = new Label("Student: ");
		grid.add(naslovPr, 2, br2++);
		for (String ispit : projekat.Main.prijavljeniIspiti) {
			String[] data = ispit.split("\\|");
			for (Predmet predmet : projekat.Main.predmeti) {
				if (data[0].equals(predmet.getNaziv())) {

					Label pr = new Label(data[0]);
					grid.add(pr, 1, br1++);
					for (Student student : projekat.Main.studenti) {
						if (student.getJmbg() == Long.valueOf(data[1])) {
							Label st = new Label(
									student.getIme() + " " + student.getPrezime() + " " + student.getBrojIndexa());
							grid.add(st, 3, br2++);
						}
					}
				}
			}
		}
		return grid;
	}

	public static GridPane unesiRezultate() {

		GridPane grid = new GridPane();
		ComboBox<String> predmeti = new ComboBox<String>();

		for (Predmet predmet : projekat.Main.predmeti) {
			if (predmet.getProfjmbg() == projekat.Main.ulogovan) {
				predmeti.getItems().add(predmet.getNaziv());
			}
		}
		;

		Label indexLbl = new Label("Broj indexa studenta: ");
		TextField indexTxt = new TextField();
		grid.add(indexLbl, 0, 0);
		grid.add(indexTxt, 1, 0);

		Label predmetLbl = new Label("Predmet: ");
		grid.add(predmetLbl, 0, 1);
		grid.add(predmeti, 1, 1);

		Label ocenaLbl = new Label("Ocena: ");
		ComboBox<Integer> ocene = new ComboBox<Integer>();
		ocene.getItems().addAll(6, 7, 8, 9, 10);
		grid.add(ocenaLbl, 0, 2);
		grid.add(ocene, 1, 2);

		Button btn = new Button("Unesi rezultate");
		grid.add(btn, 0, 3);
		btn.setOnAction(e -> {
			upisOcene(indexTxt, predmeti, ocene);
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("Uspesno ste uneli rezultate ispita.");
			projekat.DataUtil.upisiPolozeneIspite();
			indexTxt.clear();
			predmeti.setValue(null);
			ocene.setValue(null);
		});

		return grid;
	}

	public static void upisOcene(TextField ind, ComboBox<String> pred, ComboBox<Integer> oc) {
		int brInd = 0;
		String prNaziv = "";
		int prOcena = 0;
		String ispit = "";
		boolean indikator = false;
		try {
			for (Student student : projekat.Main.studenti) {
				if (student.getBrojIndexa() == Integer.valueOf(ind.getText().trim())) {
					brInd = Integer.valueOf(ind.getText().trim());
					indikator = true;
				}
			}
		} catch (NumberFormatException e) {
			ind.clear();
			ind.setStyle("-fx-text-box-border: red");
		}
		;
		if (indikator == false) {
			prNaziv = pred.getValue().toString();
			prOcena = oc.getValue().intValue();
			ispit = prNaziv + "|" + brInd + "|" + prOcena;
			projekat.Main.polozeniIspiti.add(ispit);
		}
	}

	public static GridPane izlistajPredmete() {
		GridPane grid = new GridPane();
		grid.add(new StackPane(prikazPredmeta(ucitajPredmete(projekat.Main.ulogovan))), 1, 1);
		return grid;
	}

	@SuppressWarnings("unchecked")
	public static StackPane prikazPredmeta(ObservableList<Predmet> p) {
		StackPane stack = new StackPane();
		TableView<Predmet> table = new TableView<Predmet>();
		TableColumn<Predmet, String> nazivCol = new TableColumn<Predmet, String>("Naziv predmeta");
		TableColumn<Predmet, Smer> smerCol = new TableColumn<Predmet, Smer>("Smer");
		TableColumn<Predmet, Integer> godinaCol = new TableColumn<Predmet, Integer>("Godina studija");
		TableColumn<Predmet, Integer> fondCol = new TableColumn<Predmet, Integer>("nedeljniFond");

		table.getColumns().addAll(nazivCol, smerCol, godinaCol, fondCol);

		nazivCol.setCellValueFactory(new PropertyValueFactory<Predmet, String>("naziv"));
		smerCol.setCellValueFactory(new PropertyValueFactory<Predmet, Smer>("smer"));
		godinaCol.setCellValueFactory(new PropertyValueFactory<Predmet, Integer>("godinaStudija"));
		fondCol.setCellValueFactory(new PropertyValueFactory<Predmet, Integer>("nedeljniFond"));

		table.setItems(p);

		stack.getChildren().add(table);
		stack.setPrefSize(500, 200);

		return stack;
	}

	public static ObservableList<Predmet> ucitajPredmete(long jmbg) {
		ObservableList<Predmet> predmeti = FXCollections.observableArrayList();
		for (Predmet predmet : projekat.Main.predmeti) {
			if (predmet.getProfjmbg() == jmbg) {
				predmeti.add(new Predmet(predmet.getNaziv(), predmet.getSmer(), predmet.getGodinaStudija(),
						predmet.getNedeljniFond(), predmet.getProfjmbg()));
			}
			;
		}
		return predmeti;
	}

	public static void sacuvajPodatke() {

		projekat.DataUtil.upisiPolozeneIspite();
		Meni.window.close();

	}

}
