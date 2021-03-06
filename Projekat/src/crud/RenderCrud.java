package crud;
//ideja CRUD-a je da Create, Read, Update, Delete-uje, sve ili neke odredjene delove

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Render;
import util.FileIO;
import util.Files;

public class RenderCrud {

	private static Map<String, Render> renderi;
//ucitavnje iz fajla
	public static void loadRenderiMap() {
		renderi = new HashMap<>();

		List<String> lines = FileIO.readFromFile(Files.RENDER);
		for (String line : lines) {
			Render render = Render.parse(line);
			renderi.put(render.getNaziv(), render);
		}
	}

	public static Map<String, Render> getRenderMap() {
		return renderi;
	}

	public static List<Render> getAllRenderi() {
		return new ArrayList<Render>(renderi.values());
	}

	public static List<Render> getRenderiByIDs(List<String> nazivi) {
		List<Render> list = new ArrayList<>();
		for (String naziv : nazivi) {
			list.add(renderi.get(naziv));
		}
		return list;
	}
//vraca niz rendera, za combo box 
	public static Render[] toArray(List<Render> list) {
		Render[] niz = new Render[list.size()]; 
		for (int i = 0; i < niz.length; i++) {
			niz[i] = list.get(i);
		}
		return niz;
	}

	public static Render getRenderByID(String naziv) {
		return renderi.get(naziv);
	}
	
//pravljenje  novog rendera
	public static boolean createRender(String naziv, List<String> materijali, List<String> kamere, String svetlo,
			List<String> objekti) {
		if (renderi.containsKey(naziv))
			return false;

		renderi.put(naziv, new Render(naziv, materijali, kamere, svetlo, objekti));

		FileIO.appendToFile(Files.RENDER, renderi.get(naziv).toFileFormat());

		return true;
	}
//update file RENDER
	private static boolean updateFile() {
		List<String> list = new ArrayList<>();
		for (Render render : renderi.values()) {
			list.add(render.toFileFormat());
		}
		return FileIO.writeToFile(Files.RENDER, list);
	}
//editovanje rendera
	public static boolean updateRender(Render render) {
		if (!renderi.containsKey(render.getNaziv()))
			return false;

		renderi.replace(render.getNaziv(), render);

		updateFile();

		return true;
	}
//brisanje rendera
	public static boolean deleteRender(Render render) {
		if (!renderi.containsKey(render.getNaziv())) return false;
		
		SoftverCrud.removeRender(render); 
		
		renderi.remove(render.getNaziv());

		updateFile();
		return true;
	}

}
