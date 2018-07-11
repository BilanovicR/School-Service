package objects;

import objects.Student.Smer;

public class Predmet {
	public String naziv;
	public Smer smer;
	public int godinaStudija;
	public int nedeljniFond;
	public long profjmbg;//cuvam podatak o profesoru prema njegovom JMBG

	public Predmet(String naziv, Smer smer, int godStudija, int nedFond, long prof) {
		this.naziv = naziv;
		this.smer = smer;
		this.godinaStudija = godStudija;
		this.nedeljniFond = nedFond;
		this.profjmbg = prof;
	}

	@Override
	public String toString() {
		return (getNaziv() + "|" + getSmer() + "|" + getGodinaStudija() + "|" + getNedeljniFond() + "|"
				+ getProfjmbg());
	}

	public String getNaziv() {
		return naziv;
	}

	public long getProfjmbg() {
		return profjmbg;
	}

	public Smer getSmer() {
		return smer;
	}

	public int getGodinaStudija() {
		return godinaStudija;
	}

	public int getNedeljniFond() {
		return nedeljniFond;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public void setProfjmbg(int profesor) {
		this.profjmbg = profesor;
	}

	public void setSmer(Smer smer) {
		this.smer = smer;
	}

	public void setNedeljniFond(int nedeljniFond) {
		this.nedeljniFond = nedeljniFond;
	}

	public void setGodinaStudija(int godinaStudija) {
		this.godinaStudija = godinaStudija;
	}

}
