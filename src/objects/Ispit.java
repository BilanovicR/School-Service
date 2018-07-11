package objects;

import java.util.Date;

public class Ispit {
	private Predmet nazivPredmeta;
	
	public enum Tip {
		Usmeni, Pismeni, Praksa
	};

	private Tip tip;

	public enum Status {
		Redovni, Vanredni
	};

	private Status status;
	private Date datumIspita;

	public Ispit(Predmet predmet, Tip tip, Status status, Date datum) {
		this.nazivPredmeta = predmet;
		this.tip = tip;
		this.status = status;
		this.datumIspita = datum;
	}

	public void setNazivPredmeta(Predmet nazivPredmeta) {
		this.nazivPredmeta = nazivPredmeta;
	}

	public void setDatumIspita(Date datumIspita) {
		this.datumIspita = datumIspita;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setTip(Tip tip) {
		this.tip = tip;
	}

	public Predmet getNazivPredmeta() {
		return nazivPredmeta;
	}

	public Date getDatumIspita() {
		return datumIspita;
	}

	public Status getStatus() {
		return status;
	}

	public Tip getTip() {
		return tip;
	}

}
