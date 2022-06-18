package model;

import java.util.ArrayList;
import java.util.List;

import util.Formating;

public class Softver {
	
//	Id softvera
	private String naziv;
	
	private List<Cetkica> cetkice;
	
	private String fajlFormat;
	
	private List<String> alatiZaAnimaciju;
	
	private Render render;
	
	public Softver(String naziv) {
		this.naziv = naziv;
	}

	public Softver(String naziv, List<Cetkica> cetkice, String fajlFormat, List<String> alatiZaAnimaciju,
			Render render) {
		super();
		this.naziv = naziv;
		this.cetkice = cetkice;
		this.fajlFormat = fajlFormat;
		this.alatiZaAnimaciju = alatiZaAnimaciju;
		this.render = render;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Cetkica> getCetkice() {
		return cetkice;
	}

	public void setCetkice(List<Cetkica> cetkice) {
		this.cetkice = cetkice;
	}

	public String getFajlFormat() {
		return fajlFormat;
	}

	public void setFajlFormat(String fajlFormat) {
		this.fajlFormat = fajlFormat;
	}

	public List<String> getAlatiZaAnimaciju() {
		return alatiZaAnimaciju;
	}

	public void setAlatiZaAnimaciju(List<String> alatiZaAnimaciju) {
		this.alatiZaAnimaciju = alatiZaAnimaciju;
	}

	public Render getRender() {
		return render;
	}

	public void setRender(Render render) {
		this.render = render;
	}
	
	private List<String> cetkiceToNaziv() {
		List<String> lista = new ArrayList<String>();
		for (Cetkica cetkica: cetkice) {
			lista.add(cetkica.getNaziv());	
		}
		return lista;
	}
	

	@Override
	public String toString() {
		return naziv 
				+ "," + Formating.formatList(cetkiceToNaziv().toArray()) 
				+ "," + fajlFormat 
				+ "," + Formating.formatList(alatiZaAnimaciju.toArray()) 
				+ "," + render.getNaziv();
	}
	
	

}
