package gui.cetkica;
//Tabela za cetkice
import java.awt.Color;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Cetkica;

public class CetkicaTable extends AbstractTableModel {

	

	
	private List<Cetkica> cetkice;

	public CetkicaTable(List<Cetkica> cetkice) {
		this.cetkice = cetkice;
	}
	
	public void setCetkice(List<Cetkica> cetkice) {
		this.cetkice = cetkice;
	}
//redovi
	@Override
	public int getRowCount() {
		return cetkice.size();
	}
//colone
	@Override
	public int getColumnCount() {
		return 3;
	}
// .. 
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
	
//za prikazivanje vrednosti
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

//	za boju
	public Color getColor(int i) { // dodatno za prikaz obojenog polja
		return cetkice.get(i).getBoja();
	}
	
}
