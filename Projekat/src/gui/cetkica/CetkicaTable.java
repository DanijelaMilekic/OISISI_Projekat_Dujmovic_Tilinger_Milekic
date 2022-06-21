package gui.cetkica;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Cetkica;

public class CetkicaTable extends AbstractTableModel  {

	
	
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
		return 2;
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
		}
		return null;
	}
}


