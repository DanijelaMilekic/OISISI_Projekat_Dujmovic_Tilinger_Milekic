package crud;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Adresa;
import model.RadnoMesto;
import model.Softver;
import model.Zaposleni;
import util.FileIO;
import util.Files;

public class ZaposleniCrud {
	
	private static Map<String, Zaposleni> zaposlenis;

	public static void loadZaposlenisMap() {
		zaposlenis = new HashMap<>();

		List<String> lines = FileIO.readFromFile(Files.ZAPOSLENI);
		for (String line : lines) {
			Zaposleni zaposleni = Zaposleni.parse(line);
			zaposlenis.put(zaposleni.getJMBG(), zaposleni);
		}
	}

	public static Map<String, Zaposleni> getZaposleniMap() {
		return zaposlenis;
	}

	public static List<Zaposleni> getAllZaposlenis() {
		return new ArrayList<Zaposleni>(zaposlenis.values());
	}

	public static List<Zaposleni> getZaposlenisByIDs(List<String> JMBGs) {
		List<Zaposleni> list = new ArrayList<>();
		for (String JMBG : JMBGs) {
			list.add(zaposlenis.get(JMBG));
		}
		return list;
	}

	public static Zaposleni getZaposleniByID(String JMBG) {
		return zaposlenis.get(JMBG);
	}

	public static boolean createZaposleni(String JMBG, String ime, String prezime, Date datumRodjenja, String email, Adresa adresaStanovanja,
			List<Softver> softveri, RadnoMesto radnoMesto) {
		if (zaposlenis.containsKey(JMBG))
			return false;

		zaposlenis.put(JMBG, new Zaposleni(JMBG, ime, prezime, datumRodjenja, email, adresaStanovanja, softveri, radnoMesto));
		
		FileIO.appendToFile(Files.ZAPOSLENI, zaposlenis.get(JMBG).toString());

		return true;
	}

	private static boolean updateFile() {
		List<String> list = new ArrayList<>();
		for (Zaposleni zaposleni: zaposlenis.values()) {
			list.add(zaposleni.toString());
		}
		return FileIO.writeToFile(Files.ZAPOSLENI, list);
	}

	public static boolean updateZaposleni(Zaposleni zaposleni) {
		if (!zaposlenis.containsKey(zaposleni.getJMBG()))
			return false;

		zaposlenis.replace(zaposleni.getJMBG(), zaposleni);

		updateFile();

		return true;
	}

	public static boolean deleteZaposleni(Zaposleni zaposleni) {
		boolean successful = zaposlenis.remove(zaposleni.getJMBG(), zaposleni);

		if (successful) {
			updateFile();
		}

		return successful;
	}
	


}
