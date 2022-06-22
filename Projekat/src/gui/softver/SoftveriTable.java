package gui.softver;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Cetkica;
import model.Softver;

public class SoftveriTable extends AbstractTableModel {

	private List<Softver> softveri;

	public SoftveriTable(List<Softver> softveri) {
		this.softveri = softveri;
	}
	
	public void setSoftveri(List<Softver> softveri) {
		this.softveri = softveri;
	}

	@Override
	public int getRowCount() {
		return softveri.size();
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0: {
			return "Naziv";
		}
		case 1: {
			return "Cetkice";
		}
		case 2: {
			return "Fajl format";
		}
		case 3: {
			return "Alati za animaciju";
		}
		case 4: {
			return "Render";
		}
		}
		return null;
	}
	
	

	@Override
	public String getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0: {
			return softveri.get(rowIndex).getNaziv();
		}
		case 1: {
			StringBuilder sb = new StringBuilder();
			for (Cetkica cetkica : softveri.get(rowIndex).getCetkice()) {
				sb.append(cetkica.getNaziv() + ", ");
			}
			return sb.toString();
		}
		case 2: {
			return softveri.get(rowIndex).getFajlFormat();
		}
		case 3: {
			StringBuilder sb = new StringBuilder();
			for (String string : softveri.get(rowIndex).getAlatiZaAnimaciju()) {
				sb.append(string + ", ");
			}
			return sb.toString();
		}
		case 4: {
			return softveri.get(rowIndex).getRender().getNaziv();
		}
		}
		return null;
	}
	
	public Softver getRowValue(int i) {
		return softveri.get(i);
	}
}

