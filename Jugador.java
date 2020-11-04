package TP;


public class Jugador {
	private String nombre;
	private Mazo mazo;
	private Estrategia estrategia;

	public Jugador(String nombre,Estrategia estrategia){
		this.nombre = nombre;
		this.estrategia = estrategia;
		this.mazo = new Mazo();
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void addCarta(Carta c){
		this.mazo.addCartas(c);
	}

	public void setEstrategia(Estrategia estrategia) {
		this.estrategia = estrategia;
	}
	
	
	public Carta obtenerPrimera(){
		
		return this.mazo.getPrimeraCarta();
	}
	public boolean quedanCartas(){
		return mazo.cantCartas()>0;
	}
	public int cantidadDeCartas(){
		return mazo.cantCartas();
	}
	
	public Atributo elegirAtributo(Carta carta) {
	 	
 		return this.estrategia.elegirAtributo(carta);
 	}
 	
	public void deletePrimeraCarta(){
		mazo.deletePrimeraCarta();
	}
	
	
}
