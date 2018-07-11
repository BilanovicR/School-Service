package objects;

import projekat.ProfesorINT;

public class Profesor extends Osoba implements ProfesorINT {

	public enum Zvanje {
		DOCENT, REDOVNI, VANREDOVNI
	};

	private Zvanje zvanje;

	public Profesor(String ime, String prezime, Long jmbg, String username, String password, Zvanje zvanje) {
		super(ime, prezime, jmbg, username, password);
		this.zvanje = zvanje;
	}
	
	@Override
	public String toString() {
		// dobijam string sa podacima o profesoru
		// u tom formatu cuvam u Profesori.txt
		return (getIme() + "|" + getPrezime() + "|" + getJmbg() + "|" + getUsername() + "|" + getPassword() + "|" + getZvanje());

	}

	public void setZvanje(Zvanje zvanje) {
		this.zvanje = zvanje;
	}

	public Zvanje getZvanje() {
		return zvanje;
	}
}
