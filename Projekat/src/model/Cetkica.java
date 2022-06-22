package model;

import java.awt.Color;

public class Cetkica {

	private final String naziv;
	
	private String namena;
	
	private Color boja; 

	
	
	public Cetkica(String naziv) {
		super();
		this.naziv = naziv;
	}


	public Cetkica(String naziv, String namena, Color boja) {
		super();
		this.naziv = naziv;
		this.namena = namena;
		this.boja = boja;
	}


	public String getNaziv() {
		return naziv;
	}





	public String getNamena() {
		return namena;
	}


	public void setNamena(String namena) {
		this.namena = namena;
	}


	public Color getBoja() {
		return boja;
	}


	public void setBoja(Color boja) {
		this.boja = boja;
	}


	@Override 
	public String toString() {
		return naziv;
	}
	
	public String toFileFormat() {
		return naziv + "," + namena + "," + boja.getRGB();
	}
	
	public static Cetkica parse(String line) {
		String[] tokens = line.split(","); 
		return new Cetkica(tokens[0], tokens[1], new Color(Integer.parseInt(tokens[2]))); 
	}
	
}