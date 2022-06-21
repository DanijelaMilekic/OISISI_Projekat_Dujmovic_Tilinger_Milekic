package crud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Cetkica;
import model.Render;
import model.Softver;
import util.FileIO;
import util.Files;

public class SoftverCrud {

	private static Map<String, Softver> softveri;

	public static void loadSoftveriMap() {
		softveri = new HashMap<>();

		List<String> lines = FileIO.readFromFile(Files.SOFTVER);
		for (String line : lines) {
			Softver softver = Softver.parse(line);
			softveri.put(softver.getNaziv(), softver);
		}
	}

	public static Map<String, Softver> getSoftverMap() {
		return softveri;
	}

	public static List<Softver> getAllSoftveri() {
		return new ArrayList<Softver>(softveri.values());
	}

	public static List<Softver> getSoftveriByIDs(List<String> nazivi) {
		List<Softver> list = new ArrayList<>();
		for (String naziv : nazivi) {
			list.add(softveri.get(naziv));
		}
		return list;
	}
	
	public static int[] indices(List<Softver> list) {
		int[] indices = new int[list.size()];
		List<Softver> softeri = getAllSoftveri();
		for (int i = 0; i < indices.length; i++) {
			indices[i] = softeri.indexOf(list.get(i));
		}
		return indices;
	}
	
	public static Softver[] toArray(List<Softver> list) {
		Softver[] niz = new Softver[list.size()];
		for (int i = 0; i < niz.length; i++) {
			niz[i] = list.get(i);
		}
		return niz;
	}

	public static Softver getSoftverByID(String naziv) {
		return softveri.get(naziv);
	}

	public static boolean createSoftver(String naziv, List<Cetkica> cetkice, String fajlFormat,
			List<String> alatiZaAnimaciju, Render render) {
		if (softveri.containsKey(naziv))
			return false;

		softveri.put(naziv, new Softver(naziv, cetkice, fajlFormat, alatiZaAnimaciju, render));

		FileIO.appendToFile(Files.SOFTVER, softveri.get(naziv).toString());

		return true;
	}

	private static boolean updateFile() {
		List<String> list = new ArrayList<>();
		for (Softver softver : softveri.values()) {
			list.add(softver.toString());
		}
		return FileIO.writeToFile(Files.SOFTVER, list);
	}

	public static boolean updateSoftver(Softver softver) {
		if (!softveri.containsKey(softver.getNaziv()))
			return false;

		softveri.replace(softver.getNaziv(), softver);

		updateFile();

		return true;
	}

	public static boolean deleteSoftver(Softver softver) {
		if (!softveri.containsKey(softver.getNaziv()))
			return false;

		ZaposleniCrud.removeSoftver(softver);

		softveri.remove(softver.getNaziv());

		updateFile();

		return true;
	}

	public static void removeRender(Render render) {
		for (Map.Entry<String, Softver> entry : softveri.entrySet()) {
			if (entry.getValue().getRender().getNaziv().equals(render.getNaziv())) {
				deleteSoftver(entry.getValue());
				break;
			}
		}

	}
	
	public static void removeCetkica(Cetkica cetkica) {
		for (Map.Entry<String, Softver> entry : softveri.entrySet()) {
			entry.getValue().removeCetkica(cetkica); 
			if (entry.getValue().getCetkice().size() == 0) {
				deleteSoftver(entry.getValue());
			}
		}
		
		updateFile();
		
	}
}
