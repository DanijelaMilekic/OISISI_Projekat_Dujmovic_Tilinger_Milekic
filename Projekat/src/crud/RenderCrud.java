package crud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Render;
import util.FileIO;
import util.Files;

public class RenderCrud {
	
	private static Map<String, Render> renderi;

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

	public static Render getRenderByID(String naziv) {
		return renderi.get(naziv);
	}

	public static boolean createRender(String naziv, List<String> materijali, List<String> kamere, String svetlo,
			List<String> objekti) {
		if (renderi.containsKey(naziv))
			return false;

		renderi.put(naziv, new Render(naziv, materijali, kamere, svetlo, objekti));

		FileIO.appendToFile(Files.RENDER, renderi.get(naziv).toString());

		return true;
	}

	private static boolean updateFile() {
		List<String> list = new ArrayList<>();
		for (Render render : renderi.values()) {
			list.add(render.toString());
		}
		return FileIO.writeToFile(Files.RENDER, list);
	}

	public static boolean updateRender(Render render) {
		if (!renderi.containsKey(render.getNaziv()))
			return false;

		renderi.replace(render.getNaziv(), render);

		updateFile();

		return true;
	}

	public static boolean deleteRender(Render render) {
		boolean successful = renderi.remove(render.getNaziv(), render);

		if (successful) {
			updateFile();
		}

		return successful;
	}


}
