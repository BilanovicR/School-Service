package objects;

public abstract class Osoba {
	public String ime;
	public String prezime;
	public long jmbg;
	public String username;
	public String password;

	public Osoba(String ime, String prezime, long jmbg, String username, String password) {
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.username = username;
		this.password = password;
		}
	
	public String getIme() {
		return ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public long getJmbg() {
		return jmbg;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void setIme(String inputIme) {
		if (inputIme instanceof String && inputIme != "") {
			this.ime = inputIme;
		} else {
			System.out.println("Pogrešan format za unos podataka, pokušajte ponovo");
		}
	}

	public void setPrezime(String inputPrezime) {
		if (inputPrezime instanceof String && inputPrezime != "") {
			this.prezime = inputPrezime;
		} else {
			System.out.println("Pogrešan format za unos podataka, pokušajte ponovo");
		}
	}

	public void setJmbg(long inputJmbg) {
		this.jmbg = inputJmbg;
	}

	public void setPassword(String inputPassword) {
		if (inputPassword instanceof String && inputPassword != "") {
			this.password = inputPassword;
		} else {
			System.out.println("Pogrešan format za unos podataka, pokušajte ponovo");
		}
	}

	public void setUsername(String inputUsername) {
		if (inputUsername instanceof String && inputUsername != "") {
			this.username = inputUsername;
		} else {
			System.out.println("Pogrešan format za unos podataka, pokušajte ponovo");
		}
	}

}