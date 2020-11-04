package TP;

import java.util.ArrayList;
import java.util.Collections;

public class Carta {
	private String nombre;
	private ArrayList<Atributo>atributos;
	private Pocima pocima;

	
	
	public Carta(String nombre){
		this.nombre=nombre;
		this.atributos = new ArrayList<>();
	}
	
	public void setAtributos(ArrayList<Atributo>atributos){
		ArrayList<Atributo> aux = new ArrayList<Atributo>();
		
		for(Atributo elem:atributos){
			Atributo copia = new Atributo(elem.getNombre(), elem.getValor());
			aux.add(copia);
		}
		this.atributos.addAll(aux);
	}
		
	public void addAtributo(Atributo a){
		this.atributos.add(a);
	}

	public String getNombre() {
		return nombre;
	}
	public Pocima getPocima() {
		return this.pocima;
	}

	public ArrayList<Atributo> getAtributos(){
		ArrayList<Atributo> aux = new ArrayList<Atributo>(atributos);
		return aux;
	}
	
	public Atributo getAtributoRandom(){
		int numeroRandom = (int)(Math.random()*atributos.size());
		Atributo atributo= this.atributos.get(numeroRandom);
		
		return atributo;
	}
	
	

	public int getCantAtributos(){
		return this.atributos.size();
	}
	
	@Override
	public String toString() {
		return  this.nombre ;
	}
	
	
	
	public int getValorAtributo(Atributo a){
		int retorno = 0;

		for(Atributo elem:atributos){

			if(elem.equals(a)){
				
				retorno = elem.getValor();
				return retorno;
			}
		}
		
		return retorno;
		
	}
	@Override
	public boolean equals(Object o){
		Carta c = (Carta)o;
		boolean retorno = true;
		if(c.getCantAtributos() == this.getCantAtributos()){
			for(int i=0; i<c.getCantAtributos();i++){
				if(!c.getAtributos().get(i).equals(this.getAtributos().get(i))){
					retorno= false;
				}
			}
		}else if(!(c.getCantAtributos() == this.getCantAtributos())){
			retorno = false;
		};
		return retorno;
	}

	public void setPocima(Pocima pocima) {
		this.pocima = pocima;
	}
	public Atributo getAtributoMayor(){
		ArrayList<Atributo> aux = new ArrayList<>(this.atributos);

		Collections.sort(aux);
		Atributo retorno = aux.get(0);

		return retorno;
		
	}
	public void aplicarPocima() {
		
		this.pocima.aplicar(this.atributos);
	}
}
