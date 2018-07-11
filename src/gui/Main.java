package gui;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import projekat.DataUtil;

public class Main extends Application {

	public static Stage window; 
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//ucitavanje svih podataka
		projekat.Main.sluzbenici = DataUtil.ucitajSluzbenike();
		projekat.Main.profesori = DataUtil.ucitajProfesore();
		projekat.Main.studenti = DataUtil.ucitajStudente();
		projekat.Main.predmeti = DataUtil.ucitajPredmete();
		projekat.Main.prijavljeniIspiti = DataUtil.ucitajPrijavljeneIspite();
		projekat.Main.polozeniIspiti = DataUtil.ucitajPolozeneIspite();
		
		window = primaryStage;
		window.setTitle("Univerzitet");
		
		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);
		
		Text naslov = new Text("Dobrodosli na portal studentske sluzbe");		
		gp.add(naslov, 0, 0);
		
		Label l1 = new Label("Username");
		Label l2 = new Label("Password");
		gp.add(l1, 0, 1);
		gp.add(l2, 0, 2);
		
		TextField userField = new TextField();
		PasswordField passField = new PasswordField();

		gp.add(userField, 1, 1);
		gp.add(passField, 1, 2);
		
		Button btn = new Button("Log in");
		gp.add(btn, 2, 3);
		btn.setOnAction( e -> {
			Text alert = new Text("");	
			gp.add(alert, 1, 3);		
			
			String user = userField.getText();
			String pass = passField.getText();
	 
			projekat.Main.ulogovan = DataUtil.pretraziKorisnike(user, pass);
			if (projekat.Main.ulogovan == 0) {
				alert = new Text("Pokusajte ponovo!");
				alert.setFill(Color.RED);;
				gp.add(alert, 1, 3);				
			} else {
			window.close();
			Meni.meni(projekat.Main.x);
			}
		});
		Scene scena = new Scene(gp, 500, 250);
		window.setScene(scena);
		window.show();
	
	}

}
