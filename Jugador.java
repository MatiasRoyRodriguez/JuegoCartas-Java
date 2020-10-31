package TP;

public class Jugador {
	private String nombre;
	private Mazo mazo;
	
	public Jugador(String nombre){
		this.nombre = nombre;
		this.mazo= new Mazo();
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void addCarta(Carta c){
		this.mazo.addCartas(c);
	}


	public void verCartas(){
		mazo.verMazo();
	}
	
	public Carta obtenerPrimera(){
		
		return this.mazo.devolverCarta(0);
	}
	public boolean quedanCartas(){
		return mazo.cantCartas()>0;
	}
	public int cantidadDeCartas(){
		return mazo.cantCartas();
	}
	public Atributo getAtributoRandom(Carta c){
		
		return c.getAtributoRandom();
	}
	public void deletePrimeraCarta(){
		mazo.deletePrimeraCarta();
	}
}
