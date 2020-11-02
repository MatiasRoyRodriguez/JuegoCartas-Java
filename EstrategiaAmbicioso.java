package TP;


public class EstrategiaAmbicioso implements Estrategia {

	
	
	@Override
	public Atributo elegirAtributo(Carta carta) {
		
		return carta.getAtributoMayor();
		
		
	}

}
