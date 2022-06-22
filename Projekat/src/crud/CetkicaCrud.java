package crud;
//ideja CRUD-a je da Create, Read, Update, Delete-uje, sve ili neke odredjene delove

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Cetkica;
import model.Softver;
import util.FileIO;
import util.Files;

public class CetkicaCrud {
	
	private static Map<String, Cetkica> cetkice; 
	public static void loadCetkiceMap() { //kod hashmap mozemo da identifikujemo objekat odma na osnovu kljuca
		cetkice = new HashMap<>();

		List<String> lines = FileIO.readFromFile(Files.CETKICA);

		for (String line : lines) {
			Cetkica cetkica = Cetkica.parse(line);
			cetkice.put(cetkica.getNaziv(), cetkica); //Naziv ID, a cetkica je vrednost
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
	//kada se edituje nesto, ucita se cela lista cetkica, i ovo nam sluzi da odredimo koje su slektovane, na osnovu indeksa
	public static int[] indices(List<Cetkica> list) {
		int[] indices = new int[list.size()]; //niz indeksa int
		List<Cetkica> cetkice = getAllCetkice();
		for (int i = 0; i < indices.length; i++) {
			indices[i] = cetkice.indexOf(list.get(i));
		}
		return indices;
	}
// Create - pravljenje cetkice
	public static boolean createCetkica(String naziv, String namena, Color boja) { 
		if (cetkice.containsKey(naziv)) return false;
		
		cetkice.put(naziv, new Cetkica(naziv, namena, boja));
		
		FileIO.appendToFile(Files.CETKICA, cetkice.get(naziv).toFileFormat());
		
		return true;
	}
//Update-ovanje fajla	
	private static boolean updateFile() {
		List<String> list = new ArrayList<>();
		for (Cetkica cetkica : cetkice.values()) {
			list.add(cetkica.toFileFormat());
		}
		return FileIO.writeToFile(Files.CETKICA, list);

	}
// Editovanje 
	public static boolean updateCetkica(Cetkica cetkica) {
		if (!cetkice.containsKey(cetkica.getNaziv())) return false; 
		
		cetkice.replace(cetkica.getNaziv(), cetkica); 
		
		updateFile();
		
		return true;
	}
// brisanje
	public static boolean deleteCetkica(Cetkica cetkica) {
		if (!cetkice.containsKey(cetkica.getNaziv())) return false;
		
		SoftverCrud.removeCetkica(cetkica);
		
		cetkice.remove(cetkica.getNaziv());
		
		updateFile();
		
		return true;
	}
	
}
