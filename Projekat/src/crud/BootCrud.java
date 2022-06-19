package crud;

public class BootCrud {
	
	public static void bootCrud() {
		CetkicaCrud.loadCetkiceMap();
		RenderCrud.loadRenderiMap();
		SoftverCrud.loadSoftveriMap();
		ZaposleniCrud.loadZaposlenisMap();
	}


}
