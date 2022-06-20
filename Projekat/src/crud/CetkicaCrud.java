package crud;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Cetkica;
import util.FileIO;
import util.Files;

public class CetkicaCrud {

	private static Map<String, Cetkica> cetkice;

	public static void loadCetkiceMap() {
		cetkice = new HashMap<>();

		List<String> lines = FileIO.readFromFile(Files.CETKICA);

		for (String line : lines) {
			Cetkica cetkica = Cetkica.parse(line);
			cetkice.put(cetkica.getNaziv(), cetkica);
		}
	}

	public static Map<String, Cetkica> getCetkiceMap() {
		return cetkice;
	}
	
	public static List<Cetkica> getAllCetkice() {
		return new ArrayList<Cetkica>(cetkice.values());
	}
	
	

	public static List<Cetkica> getCetkiceByIDs(List<String> nazivi) {
		List<Cetkica> list = new ArrayList<Cetkica>();
		for (String naziv : nazivi) {
			list.add(cetkice.get(naziv));
		}
		return list;
	}
	
	public static Cetkica getCetkicaByID(String naziv) {
		return cetkice.get(naziv);
	}
	
	public static boolean createCetkica(String naziv, String namena, Color boja) { 
		if (cetkice.containsKey(naziv)) return false;
		
		cetkice.put(naziv, new Cetkica(naziv, namena, boja));
		
		FileIO.appendToFile(Files.CETKICA, cetkice.get(naziv).toString());
		
		return true;
	}
	
	private static boolean updateFile() {
		List<String> list = new ArrayList<>();
		for (Cetkica cetkica : cetkice.values()) {
			list.add(cetkica.toString());
		}
		return FileIO.writeToFile(Files.CETKICA, list);

	}
	
	public static boolean updateCetkica(Cetkica cetkica) {
		if (!cetkice.containsKey(cetkica.getNaziv())) return false;
		
		cetkice.replace(cetkica.getNaziv(), cetkica);
		
		updateFile();
		
		return true;
	}
	
	public static boolean deleteCetkica(Cetkica cetkica) {
		if (!cetkice.containsKey(cetkica.getNaziv())) return false;
		
		SoftverCrud.removeCetkica(cetkica);
		
		updateFile();
		
		return true;
	}
	
}
