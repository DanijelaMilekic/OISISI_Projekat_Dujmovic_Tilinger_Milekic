package model;

public class Adresa {
	
//	Nema id-a, uzimamo ga kao opisni objekat koji 
//	cemo staviti odmah uz zaposlenog u fajlu
	private int broj;
	
	private String ulica;
	
	private String grad;

	public Adresa(int broj, String ulica, String grad) {
		this.broj = broj;
		this.ulica = ulica;
		this.grad = grad;
	}

	public int getBroj() {
		return broj;
	}

	public void setBroj(int broj) {
		this.broj = broj;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	@Override
	public String toString() {
		return broj + ";" + ulica + ";" + grad;
	}
	
	public static Adresa parse(String line) {
		String[] tokens = line.split(";");
		return new Adresa(Integer.parseInt(tokens[0]), tokens[1], tokens[2]);
	}
	
	
	
}
