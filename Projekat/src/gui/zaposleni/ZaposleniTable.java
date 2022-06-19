package gui.zaposleni;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Softver;
import model.Zaposleni;
import util.Formating;

public class ZaposleniTable extends AbstractTableModel {

	private static final long serialVersionUID = -7897789469491057501L;

	private List<Zaposleni> zaposlenis;

	public ZaposleniTable(List<Zaposleni> zaposlenis) {
		this.zaposlenis = zaposlenis;
	}

	@Override
	public int getRowCount() {
		return zaposlenis.size();
	}

	@Override
	public int getColumnCount() {
		return 8;
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0: {
			return "JMBG";
		}
		case 1: {
			return "Ime";
		}
		case 2: {
			return "Prezime";
		}
		case 3: {
			return "DatumRodjenja";
		}
		case 4: {
			return "Email";
		}
		case 5: {
			return "Adresa stanovanja";
		}
		case 6: {
			return "Radno mesto";
		}
		case 7: {
			return "Softveri";
		}
		}
		return null;
	}

	@Override
	public String getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0: {
			return zaposlenis.get(rowIndex).getJMBG();
		}
		case 1: {
			return zaposlenis.get(rowIndex).getIme();
		}
		case 2: {
			return zaposlenis.get(rowIndex).getPrezime();
		}
		case 3: {
			return Formating.formatDate(zaposlenis.get(rowIndex).getDatumRodjenja());
		}
		case 4: {
			return zaposlenis.get(rowIndex).getEmail();
		}
		case 5: {
			return zaposlenis.get(rowIndex).getAdresaStanovanja().toString();
		}
		case 6: {
			return zaposlenis.get(rowIndex).getRadnoMesto().toString();
		}
		case 7: {
			StringBuilder sb = new StringBuilder();
			for (Softver softver: zaposlenis.get(rowIndex).getSoftveri()) {
				sb.append(softver.getNaziv() + ", ");
			}
			return sb.toString();
		}
		}
		return null;
	}
}

