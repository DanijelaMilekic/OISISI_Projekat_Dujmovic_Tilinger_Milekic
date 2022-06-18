package model;

import java.util.Date;
import java.util.List;

import util.Formating;

public class Zaposleni {
	
//	Id Zaposlenog
	private final String JMBG;
	
	private String ime;
	
	private String prezime;
	
	private Date datumRodjenja;
	
	private String email;
	
	private Adresa adresaStanovanja;
	
	private List<Softver> softveri;
	
	private RadnoMesto radnoMesto;


	public Zaposleni(String jMBG) {
		JMBG = jMBG;
	}
	
	public Zaposleni(String jMBG, String ime, String prezime, Date datumRodjenja, String email, Adresa adresaStanovanja,
			List<Softver> softveri, RadnoMesto radnoMesto) {
		JMBG = jMBG;
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

	public RadnoMesto getRadnoMesto() {
		return radnoMesto;
	}

	public void setRadnoMesto(RadnoMesto radnoMesto) {
		this.radnoMesto = radnoMesto;
	}

	public String getJMBG() {
		return JMBG;
	}

	@Override
	public String toString() {
		return JMBG + "," + ime + "," + prezime + "," + Formating.formatDate(datumRodjenja) + "," + email + "," + adresaStanovanja + ","
				+ softveri + "," + radnoMesto;
	}
	
	public static Zaposleni parseZaposleni(String line) {
		String tokens[] = line.split(",");
//		Zaposleni zaposleni = new Zaposleni(
//				tokens[0], 
//				tokens[1], 
//				tokens[2], 
//				Formating.parseDate(tokens[3]), 
//				tokens[4],
//				Adresa.parseAdresa(tokens[5]), , null, null)
		return null;
	}

}
