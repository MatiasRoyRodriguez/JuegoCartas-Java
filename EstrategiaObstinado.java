package TP;

public class EstrategiaObstinado implements Estrategia {
	
	private boolean obstinado; 
	private Atributo atributo;
	
	
	
	public EstrategiaObstinado() {
		super();
		this.obstinado = false;
	}



	@Override
	public Atributo elegirAtributo(Carta carta) {

		//Aca vemos si ya esta obstinado o no con una carta en especifico 
		if(this.obstinado == false) {
			int numeroRandom = (int)(Math.random()*carta.cantAtributos());
			Atributo atributo= carta.getAtributos().get(numeroRandom);	
			this.atributo = atributo;
			this.obstinado = true;
		}	
		return this.atributo;
				
	}

}
