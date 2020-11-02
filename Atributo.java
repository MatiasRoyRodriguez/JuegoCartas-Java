package TP;

public class Atributo implements Comparable<Atributo>{
	private String nombre;
	private int valor;
	
	
	public Atributo(String nombre, int valor) {
		this.nombre=nombre;
		this.valor=valor;
	}

	public void setValor(double d) {
		 this.valor = (int) d;
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
	@Override
	public int compareTo(Atributo a){
		if(this.getValor()<a.getValor()) {
			return 1;
		}else if(this.getValor()>a.getValor()) {
			return -1;
		}else {
			return 0;
		}
	}
	
	
	
}
