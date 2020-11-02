package TP;


public class EstrategiaTimbero implements Estrategia {

	@Override
	public Atributo elegirAtributo(Carta carta) {
		
		int numeroRandom = (int)(Math.random()*carta.cantAtributos());
		Atributo atributo= carta.getAtributos().get(numeroRandom);	
		
		return atributo;
	}
	
}
