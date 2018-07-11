package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import objects.Osoba;
import objects.Profesor;
import objects.Sluzbenik;
import objects.Student;

public class Meni {
	public static Stage window = new Stage();
	public static TabPane tp = new TabPane();
	public static Text naslov = new Text();
	public static Osoba korisnik = null;
	public static Pane layout = new Pane();
	public static Scene scena = new Scene(layout, 900, 680, Color.WHITE);
	public static Image logo = new Image("/gui/logo.png");
	public static ImageView iv = new ImageView();

	public static void meni(int x) {

		// pretrazujem bazu da dobijem konkretnog korisnika
		for (Sluzbenik sluzbenik : projekat.Main.sluzbenici) {
			if (sluzbenik.getJmbg() == projekat.Main.ulogovan) {
				korisnik = sluzbenik;
			}
		}
		;
		for (Student student : projekat.Main.studenti) {
			if (student.getJmbg() == projekat.Main.ulogovan) {
				korisnik = student;
			}
		}
		;
		for (Profesor profesor : projekat.Main.profesori) {
			if (profesor.getJmbg() == projekat.Main.ulogovan) {
				korisnik = profesor;
			}
		}
		;

		// osnova menija za svakog korisnika
		window.setTitle("Korisnicki meni");
		naslov.setText("Korisnik: " + korisnik.getIme() + " " + korisnik.getPrezime());
		iv.setImage(logo);

		// prikaz menija za sluzbenika
		if (korisnik.getClass().equals(objects.Sluzbenik.class)) {

			Tab tab1 = new Tab("Upisi novog studenta");
			tp.getTabs().add(tab1);
			tab1.setContent(MeniSluzbenika.grid1);

			Tab tab2 = new Tab("Upisi novog profesora");
			tp.getTabs().add(tab2);
			tab2.setContent(MeniSluzbenika.grid2);

			Tab tab3 = new Tab("Upisi novi predmet");
			tp.getTabs().add(tab3);
			tab3.setContent(MeniSluzbenika.grid3);

			Tab tab4 = new Tab("Evidentiraj uplatu");
			tp.getTabs().add(tab4);
			tab4.setContent(MeniSluzbenika.grid4);

			Tab tab5 = new Tab("Pretraga studenata");
			tp.getTabs().add(tab5);
			tab5.setContent(MeniSluzbenika.grid5);

			Tab tab6 = new Tab("Prikaz svih studenata");
			tp.getTabs().add(tab6);
			tab6.setContent(MeniSluzbenika.stack6);

			Button btn1 = new Button("Log out");
			btn1.setOnAction(e -> {
				MeniSluzbenika.sacuvajPodatke();
			});
			btn1.relocate(830, 190);
			btn1.setStyle("-fx-color:red");
			iv.relocate(0, -10);
			naslov.relocate(740, 175);
			tp.relocate(0, 190);

			tp.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
			layout.getChildren().add(iv);
			layout.getChildren().add(tp);
			layout.getChildren().add(naslov);
			layout.getChildren().add(btn1);

		}
		;

		// prikaz menija za studenta
		if (korisnik.getClass().equals(objects.Student.class)) {

			Tab tab1 = new Tab("Prijavi ispit");
			tp.getTabs().add(tab1);
			tab1.setContent(MeniStudenta.grid1);
	
			Tab tab2 = new Tab("Odjavi ispit");
			tp.getTabs().add(tab2);
			tab2.setContent(MeniStudenta.grid2);

			Tab tab3 = new Tab("Prikaz polozenih ispita");
			tp.getTabs().add(tab3);
			tab3.setContent(MeniStudenta.grid3);

			Tab tab4 = new Tab("Prikaz prijavljenih ispita");
			tp.getTabs().add(tab4);
			tab4.setContent(MeniStudenta.grid4);

			Tab tab5 = new Tab("Rezultati ispita");
			tp.getTabs().add(tab5);
			tab5.setContent(MeniStudenta.grid5);

			Tab tab6 = new Tab("Prosek ocena");
			tp.getTabs().add(tab6);
			tab6.setContent(MeniStudenta.grid6);

			Tab tab7 = new Tab("Tekuci predmeti");
			tp.getTabs().add(tab7);
			tab7.setContent(MeniStudenta.grid7);

			Button btn1 = new Button("Log out");
			btn1.setOnAction(e -> {
				 MeniStudenta.sacuvajPodatke();
			});
			btn1.relocate(830, 190);
			btn1.setStyle("-fx-color:red");

			tp.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
			iv.setImage(logo);
			layout.getChildren().add(iv);
			layout.getChildren().add(tp);
			layout.getChildren().add(naslov);
			layout.getChildren().add(btn1);

			iv.relocate(0, -10);
			naslov.relocate(740, 175);
			tp.relocate(0, 190);

		}
		;

		// prikaz menija za profesora
		if (korisnik.getClass().equals(objects.Profesor.class)) {

			Tab tab1 = new Tab("Prijavljeni ispiti");
			tab1.setContent(MeniProfesora.grid1);
			tp.getTabs().add(tab1);
			;

			Tab tab2 = new Tab("Unesi rezultate ispita");
			tab2.setContent(MeniProfesora.grid2);
			tp.getTabs().add(tab2);

			Tab tab3 = new Tab("Lista mojih predmeta");
			tab3.setContent(MeniProfesora.grid3);
			tp.getTabs().add(tab3);

			Button btn1 = new Button("Log out");
			btn1.setOnAction(e -> {
				MeniProfesora.sacuvajPodatke();
			});
			btn1.relocate(830, 190);
			btn1.setStyle("-fx-color:red");

			tp.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
			iv.setImage(logo);
			layout.getChildren().add(iv);
			layout.getChildren().add(tp);
			layout.getChildren().add(naslov);
			layout.getChildren().add(btn1);

			iv.relocate(0, -10);
			naslov.relocate(740, 175);
			tp.relocate(0, 190);

		}

		window.setScene(scena);
		window.show();
	}

}
