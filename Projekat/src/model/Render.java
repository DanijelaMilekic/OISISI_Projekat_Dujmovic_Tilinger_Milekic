package model;

import java.util.List;

import util.Formating;

public class Render {
	
//	Id rendera
	private String naziv;

	private List<String> materijali;

	private List<String> kamere;

	private String svetlo;

	private List<String> objekti;

	public Render(String naziv) {
		super();
		this.naziv = naziv;
	}

	public Render(String naziv, List<String> materijali, List<String> kamere, String svetlo, List<String> objekti) {
		super();
		this.naziv = naziv;
		this.materijali = materijali;
		this.kamere = kamere;
		this.svetlo = svetlo;
		this.objekti = objekti;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<String> getMaterijali() {
		return materijali;
	}

	public void setMaterijali(List<String> materijali) {
		this.materijali = materijali;
	}

	public List<String> getKamere() {
		return kamere;
	}

	public void setKamere(List<String> kamere) {
		this.kamere = kamere;
	}

	public String getSvetlo() {
		return svetlo;
	}

	public void setSvetlo(String svetlo) {
		this.svetlo = svetlo;
	}

	public List<String> getObjekti() {
		return objekti;
	}

	public void setObjekti(List<String> objekti) {
		this.objekti = objekti;
	}

	@Override
	public String toString() {
		return naziv + "," + Formating.formatList(materijali.toArray()) + "," + Formating.formatList(kamere.toArray())
				+ "," + svetlo + "," + Formating.formatList(objekti.toArray());
	}


}
