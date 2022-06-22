package gui.cetkica;

import java.awt.Color;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Cetkica;

public class CetkicaTable extends AbstractTableModel {

	
	private static final long serialVersionUID = -7870870896455561500L;
	
	private List<Cetkica> cetkice;

	public CetkicaTable(List<Cetkica> cetkice) {
		this.cetkice = cetkice;
	}
	
	public void setCetkice(List<Cetkica> cetkice) {
		this.cetkice = cetkice;
	}

	@Override
	public int getRowCount() {
		return cetkice.size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0: {
			return "Naziv";
		}
		case 1: {
			return "Namena";
		}
		case 2: {
			return "Boja";
		}
		}
		return null;
	}
	
	

	@Override
	public String getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0: {
			return cetkice.get(rowIndex).getNaziv();
		}
		case 1: {
			return cetkice.get(rowIndex).getNamena();
		}
		case 2: {
			return "";
		}
		}
		return null;
	}
	
	public Cetkica getRowValue(int i) {
		return cetkice.get(i);
	}
	
	public Color getColor(int i) { 
		return cetkice.get(i).getBoja();
	}
	
}


