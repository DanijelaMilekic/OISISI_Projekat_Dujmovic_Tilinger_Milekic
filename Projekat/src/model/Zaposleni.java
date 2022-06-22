package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import crud.SoftverCrud;
import util.Formating;

public class Zaposleni {

	
//	Id Zaposlenog
	//sadrzi nekoliko polja
	private final String JMBG; 
	
	private String ime;
	
	private String prezime;
	
	private Date datumRodjenja;
	
	private String email;
	
	private Adresa adresaStanovanja;
	
	private List<Softver> softveri; 
	
	private RadnoMesto radnoMesto;


	public Zaposleni(String JMBG) {
		this.JMBG = JMBG;
	}
	
	public Zaposleni(String JMBG, String ime, String prezime, Date datumRodjenja, String email, Adresa adresaStanovanja,
			List<Softver> softveri, RadnoMesto radnoMesto) {
		this.JMBG = JMBG;
		this.ime = ime;
		this.prezime = prezime;
		this.datumRodjenja = datumRodjenja;
		this.email = email;
		this.adresaStanovanja = adresaStanovanja;
		this.softveri = softveri;
		this.radnoMesto = radnoMesto;
	}

	
	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Adresa getAdresaStanovanja() {
		return adresaStanovanja;
	}

	public void setAdresaStanovanja(Adresa adresaStanovanja) {
		this.adresaStanovanja = adresaStanovanja;
	}

	public List<Softver> getSoftveri() {
		return softveri;
	}

	public void setSoftveri(List<Softver> softveri) {
		this.softveri = softveri;
	}

	public void addSoftver(Softver softver) {
		if (!softveri.contains(softver)) {
			softveri.add(softver);
		}
	}
	
	public void removeSoftver(Softver softver) {
		if (softveri.contains(softver)) {
			softveri.remove(softver);
		}
	}

	public RadnoMesto getRadnoMesto() {
		return radnoMesto;
	}

	public void setRadnoMesto(RadnoMesto radnoMesto) {
		this.radnoMesto = radnoMesto;
	}


	public String getJMBG() {
		return JMBG;
	}
	
	private List<String> softveriToNaziv() {
		List<String> lista = new ArrayList<String>();
		for (Softver softver: softveri) {
			lista.add(softver.getNaziv());	
		}
		return lista;
	}

	
	/**
	 * Svaki toString metod je ustavri formatiranje za ispis u fajl
	 * */
	@Override
	public String toString() {
		return ime + " " + prezime + ", " + JMBG + ", " + email;
	}
	
	public String toFileFormat() {
		return JMBG 
				+ "," + ime 
				+ "," + prezime 
				+ "," + Formating.formatDate(datumRodjenja) //tipa date, a ne string, tako da treba da se formatira 
				+ "," + email 
				+ "," + adresaStanovanja.toFileFormat()
				+ "," + Formating.formatList(softveriToNaziv().toArray())//samo nazive softvera
				+ "," + radnoMesto.toString(); // vraca string - naziv  iz enuma
	}
	 
	 
	/**
	 * Kasnije ga samo parsiramo iz tog zapisa
	 * */
	public static Zaposleni parse(String line) {
		String tokens[] = line.split(",");
		return new Zaposleni( 
				tokens[0], 
				tokens[1], 
				tokens[2], 
				Formating.parseDate(tokens[3]), //uzima string i prebacuje u datum
				tokens[4],
				Adresa.parse(tokens[5]), 
				SoftverCrud.getSoftveriByIDs(Formating.toList(tokens[6])), 
				RadnoMesto.valueOf(tokens[7])); //ucitavamo koji je enum valueof..
		
	}
}
