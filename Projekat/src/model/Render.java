package model;

import java.util.ArrayList;
import java.util.List;

import util.Formating;

public class Render {

//	Id rendera
	private final String naziv;

	private List<String> materijali;

	private List<String> kamere;

	private String svetlo;

	private List<String> objekti;

	public Render(String naziv) {
		super();
		this.naziv = naziv;
		this.materijali = new ArrayList<>();
		this.kamere = new ArrayList<>();
		this.objekti = new ArrayList<>();
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



	public List<String> getMaterijali() {
		return materijali;
	}

	public void setMaterijali(List<String> materijali) {
		this.materijali = materijali;
	}
	
	public void addMaterijal(String materijal) {
		if (!materijali.contains(materijal)) {
			materijali.add(materijal);
		}
	}
	
	public void removeMaterijal(String materijal) {
		if (materijali.contains(materijal)) {
			materijali.remove(materijal);
		}
	}

	public List<String> getKamere() {
		return kamere;
	}

	public void setKamere(List<String> kamere) {
		this.kamere = kamere;
	}
	
	public void addKamera(String kamera) {
		if (!kamere.contains(kamera)) {
			kamere.add(kamera);
		}
	}
	
	public void removeKamera(String kamera) {
		if (kamere.contains(kamera)) {
			kamere.remove(kamera);
		}
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
	
	public void addObjekat(String objekat) {
		if (!objekti.contains(objekat)) {
			objekti.add(objekat);
		}
	}
	
	public void removeObjekat(String objekat) {
		if (objekti.contains(objekat)) {
			objekti.remove(objekat);
		}
	}
	

	@Override
	public String toString() {
		return naziv;
	} 
	
	public String toFileFormat() {
		return naziv 
				+ "," + Formating.formatList(materijali.toArray()) 
				+ "," + Formating.formatList(kamere.toArray())
				+ "," + svetlo 
				+ "," + Formating.formatList(objekti.toArray());
	}
	
	public static Render parse(String line) {
		String[] tokens = line.split(","); 
		return new Render(
				tokens[0], 
				Formating.toList(tokens[1]), 
				Formating.toList(tokens[2]), 
				tokens[3], 
				Formating.toList(tokens[4]));
	}

}
