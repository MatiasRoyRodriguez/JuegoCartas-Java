package TP;

import java.util.ArrayList;

public class PocimaCocktail extends Pocima {

	private ArrayList<Pocima> pocimas;

	
	protected PocimaCocktail(String nombre) {
		super(nombre);
		this.pocimas = new ArrayList<>();
	}



	public void agregarPocima(Pocima pocima) {
		this.pocimas.add(pocima);
	}
	
	
	
	
	@Override
	public void aplicar(ArrayList<Atributo> atributos) {
		for(Pocima pocima : this.pocimas) {
			pocima.aplicar(atributos);	
		}
	}

}
