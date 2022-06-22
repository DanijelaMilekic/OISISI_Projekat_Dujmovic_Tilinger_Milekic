package crud;

//Kojim redom sta ucitavamo, da ne bi pukao program
public class BootCrud {
	
	public static void bootCrud() {
		CetkicaCrud.loadCetkiceMap();
		RenderCrud.loadRenderiMap();
		SoftverCrud.loadSoftveriMap();
		ZaposleniCrud.loadZaposlenisMap();
	}

}