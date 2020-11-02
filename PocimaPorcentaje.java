package TP;

import java.util.ArrayList;

public class PocimaPorcentaje extends Pocima {
	
	private double valorIncremental;
	private String atributo;
	
	
	

	public PocimaPorcentaje(String nombre, double valorIncremental,
			String atributo) {
		super(nombre);
		this.valorIncremental = valorIncremental;
		this.atributo = atributo;
	}




	@Override
	public void aplicar(ArrayList<Atributo> atributos) {
		
		for(Atributo atributo:atributos){
			if(this.atributo == null) {
				atributo.setValor(atributo.getValor() + (atributo.getValor() * valorIncremental));
			}else if ( this.atributo != null && atributo.getNombre().equals(this.atributo)) {
				atributo.setValor(atributo.getValor() + (atributo.getValor() * valorIncremental));
			}
		}

	}

}
