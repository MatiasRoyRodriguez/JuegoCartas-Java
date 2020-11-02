package TP;

import java.util.ArrayList;

public class PocimaValorUnico extends Pocima {

	private int valorUnico;
	
	
	

	protected PocimaValorUnico(String nombre, int valorUnico) {
		super(nombre);
		this.valorUnico = valorUnico;
	}

	@Override
	public void aplicar(ArrayList<Atributo> atributos) {
		
		for(Atributo atributo:atributos){
			atributo.setValor(valorUnico);	
			}

	}

}
