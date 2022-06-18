package util;


public enum Files {
	ZAPOSLENI("res/Zaposleni.txt"),
	SOFTVER("res/Softver.txt"),
	RENDER("res/Render.txt"),
	CETKICA("res/Cetkica.txt"),
	ADRESA("res/Adresa.txt");
	
	public final String filename;
	
	private Files(String filename) {
		this.filename = filename;
	}
}
