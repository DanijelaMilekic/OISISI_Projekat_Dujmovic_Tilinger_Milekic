package gui.render;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Render;

public class RenderiTable extends AbstractTableModel {

	private static final long serialVersionUID = -6188261928784240167L;
	private List<Render> renderi;

	public RenderiTable(List<Render> renderi) {
		this.renderi = renderi;
	}
	
	public void setRenderi(List<Render> renderi) {
		this.renderi = renderi;
	}

	@Override
	public int getRowCount() {
		return renderi.size();
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
			return "Materijali";
		}
		case 2: {
			return "Kamere";
		}
		case 3: {
			return "Svetlo";
		}
		case 4: {
			return "Objekti";
		}
		}
		return null;
	}
	
	

	@Override
	public String getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0: {
			return renderi.get(rowIndex).getNaziv();
		}
		case 1: {
			StringBuilder sb = new StringBuilder();
			for (String string : renderi.get(rowIndex).getMaterijali()) {
				sb.append(string + ", ");
			}
			return sb.toString();
		}
		case 2: {
			StringBuilder sb = new StringBuilder();
			for (String string : renderi.get(rowIndex).getKamere()) {
				sb.append(string + ", ");
			}
			return sb.toString();
		}
		case 3: {
			return renderi.get(rowIndex).getSvetlo();
		}
		case 4: {
			StringBuilder sb = new StringBuilder();
			for (String string : renderi.get(rowIndex).getObjekti()) {
				sb.append(string + ", ");
			}
			return sb.toString();
		}
		}
		return null;
	}
	
	public Render getRowValue(int i) {
		return renderi.get(i);
	}
}
