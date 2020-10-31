package TP;

import java.util.ArrayList;

public class Carta {
	private String nombre;
	private ArrayList<Atributo>atributos;
	
	public Carta(String nombre){
		this.nombre=nombre;
		this.atributos = new ArrayList<>();
	}
	
	public void addAtributo(Atributo a){
		this.atributos.add(a);
	}

	public String getNombre() {
		return nombre;
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
	
	
	public String getAtributosString(){
		ArrayList<Atributo> aux = new ArrayList<Atributo>(atributos);
		
		String auxx = "";
		
		for(int i=0; i<aux.size();i++){
			auxx += aux.get(i).toString();
			;
		}
		return auxx;
		
		
	}
	public int getCantAtributos(){
		return this.atributos.size();
	}
	public int cantAtributos(){
		return this.atributos.size();
	}
	
	@Override
	public String toString() {
		return  nombre + getAtributosString() + "";
	}
	
	public boolean compareTo(Carta c){
		return false;
	}
	//TODO: CAPAS HAY QUE BORRAR ESTO
	public Atributo getAtributo(String nombre){
		Atributo retorno = null;
		for(Atributo elem:atributos){
			if(elem.equals(nombre)){
				retorno= elem;
			}
		}
		return retorno;
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
	
}
