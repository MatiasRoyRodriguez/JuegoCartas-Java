package TP;

public class Atributo {
	private String nombre;
	private int valor;
	
	
	public Atributo(String nombre, int valor) {
		this.nombre=nombre;
		this.valor=valor;
	}


	public String getNombre() {
		return nombre;
	}


	public int getValor() {
		return valor;
	}


	@Override
	public String toString() {
		return "  " + nombre + "=" + valor ;
	}

	@Override 
	public boolean equals(Object o){
		Atributo a = (Atributo) o;
		if(a.getNombre().equals(this.getNombre())){
			return true;
		}else{
			return false;	
		}
		
	}
	
	
	
	
	
}
