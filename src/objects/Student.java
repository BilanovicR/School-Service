package objects;

import java.util.Date;

import projekat.StudentINT;

public class Student extends Osoba implements StudentINT {

	public enum Smer {
		SII, IT
	};

	private String imeRoditelja;
	private int brojIndexa;
	private Date datumRodjenja;
	private String email;
	private String mesto;
	private String ulica;
	private int ulicaBr;
	private int telefon;
	private int tekucaGodina;
	private int brojRacuna;
	private int stanjeNaRacunu;
	public Smer smer;

	public Student(String ime, String prezime, long jmbg, String username, String password, String imeRoditelja,
			int brojIndexa, Smer smer, Date datumRodjenja, String email, String mesto, String ulica, int ulicaBr,
			int brojRacuna, int tekucaGodina, int stanjeNaRacunu) {

		super(ime, prezime, jmbg, username, password);
		this.imeRoditelja = imeRoditelja;
		this.brojIndexa = brojIndexa;
		this.datumRodjenja = datumRodjenja;
		this.email = email;
		this.mesto = mesto;
		this.ulica = ulica;
		this.ulicaBr = ulicaBr;
		this.tekucaGodina = tekucaGodina;
		this.brojRacuna = brojRacuna;
		this.stanjeNaRacunu = stanjeNaRacunu;
		this.smer = smer;

	}

	@Override
	public String toString() {
		// dobijam string sa podacima o studentu
		// u tom formatu cuvam u Studenti.txt
		return (getIme() + "|" + getPrezime() + "|" + getJmbg() + "|" + getUsername() + "|" + getPassword() + "|"
				+ getImeRoditelja() + "|" + getBrojIndexa() + "|" + getSmer() + "|" + getDatumRodjenja() + "|"
				+ getEmail() + "|" + getMesto() + "|" + getUlica() + "|" + getUlicaBr() + "|" + getBrojRacuna() + "|"
				+ getTekucaGodina() + "|" + getStanjeNaRacunu());

	}

	public int getBrojIndexa() {
		return brojIndexa;
	}

	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public String getImeRoditelja() {
		return imeRoditelja;
	}

	public String getMesto() {
		return mesto;
	}

	public String getUlica() {
		return ulica;
	}

	public int getUlicaBr() {
		return ulicaBr;
	}

	public int getTelefon() {
		return telefon;
	}

	public int getTekucaGodina() {
		return tekucaGodina;
	}

	public int getBrojRacuna() {
		return brojRacuna;
	}

	public int getStanjeNaRacunu() {
		return stanjeNaRacunu;
	}

	public Smer getSmer() {
		return smer;
	}

	public String getEmail() {
		return email;
	}

	public void setBrojIndexa(int brojIndexa) {
		this.brojIndexa = brojIndexa;
	}

	public void setBrojRacuna(int brojRacuna) {
		this.brojRacuna = brojRacuna;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public void setImeRoditelja(String imeRoditelja) {
		this.imeRoditelja = imeRoditelja;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setMesto(String mesto) {
		this.mesto = mesto;
	}

	public void setSmer(Smer smer) {
		this.smer = smer;
	}

	public void setStanjeNaRacunu(int stanjeNaRacunu) {
		this.stanjeNaRacunu = stanjeNaRacunu;
	}

	public void setTekucaGodina(int tekucaGodina) {
		this.tekucaGodina = tekucaGodina;
	}

	public void setTelefon(int telefon) {
		this.telefon = telefon;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public void setUlicaBr(int ulicaBr) {
		this.ulicaBr = ulicaBr;
	}

}
