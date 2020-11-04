package TP;

import java.util.ArrayList;
import java.util.Random;

public class Juego {
	private Mazo mazo;
	private Jugador j1;
	private Jugador j2;
	private int rondasMax;
	private int rondasJugadas=0;
	private ArrayList<Pocima> pocimas;
	private Pocima pocima; 
	private String juego;

	
	
	public Juego(Mazo mazo,Jugador j1,Jugador j2,int rondasMax){
		this.j1 = j1;
		this.j2 = j2;
		this.mazo= mazo;
		this.pocima = null;
		this.rondasMax = rondasMax;
		this.pocimas = new ArrayList<>();
		this.juego = "";

	}
	public void agregarPocima(Pocima pocima) {
		this.pocimas.add(pocima);
	}
	public int getRondasMax(){
		return this.rondasMax;
	}


	public void jugar(){
		
		mazo.limpiarMazo();
		//Nos aseguramos de que el mazo tenga por lo menos 2 cartas 
		if(mazo.mazoHabilParaJugar()){
			repartirCartas();
			
			repartirPocimas();
			comienzoJuego();
			
		}
		
		
	}
	
	private void repartirCartas(){
		
		if(mazo.mazoHabilParaJugar()){
			for(int i=0;i<mazo.cantCartas();i++){
				// Con el if nos aseguramos que si el mazo fuera inpar ,
				//el jugador uno quedaria con la carta extra.
				//Por eso consideramos que la constante en codigo 2 es correcta.
				if(i%2 != 0){
					j1.addCarta(mazo.devolverCarta(i));

				}else{
					j2.addCarta(mazo.devolverCarta(i));
				}
			}
		}
		
	}
	private void repartirPocimas() {
		
		for (Pocima pocima : this.pocimas) {
			Random random = new Random();	
			mazo.setPocima(pocima, random.nextInt(this.mazo.getCartas().size())); 
		}
	}
	
	public void comienzoJuego(){	
		Jugador ganadorRonda = j1;
		Atributo atributo;
		
		while(rondasJugadas<rondasMax && j1.quedanCartas() && j2.quedanCartas()){

			this.juego+="-------- RONDA N°" + (this.getRondaJugadas()+1) + "---------- \n";
			
			Carta c1 = j1.obtenerPrimera();
			Carta c2 = j2.obtenerPrimera();
			
			// aux carta 1
			Carta auxc1 = new Carta(j1.obtenerPrimera().getNombre());
			auxc1.setAtributos(j1.obtenerPrimera().getAtributos());
			//aux carta 2
			Carta auxc2 = new Carta(c2.getNombre());
			auxc2.setAtributos(c2.getAtributos());

			if(ganadorRonda.equals(j1)){
				 atributo = ganadorRonda.elegirAtributo(c1);
					this.juego+= "El jugador "+ j1.getNombre() +" selecciona competir por el atributo " + atributo.getNombre() +"\n";

			}else{
				 atributo = ganadorRonda.elegirAtributo(c2);
				 this.juego+="El jugador "+ j2.getNombre() +" selecciona competir por el atributo " + atributo.getNombre() +"\n";

			};
			
			this.turno(c1,atributo,j1);
			this.turno(c2,atributo,j2);

		
			
			ganadorRonda = this.turnoPelea(c1,c2,atributo,auxc1,auxc2,ganadorRonda);
		};
		
		
		if(rondasJugadas==rondasMax || (!j1.quedanCartas() || !j2.quedanCartas())){
			if(j1.cantidadDeCartas() > j2.cantidadDeCartas()){
				this.juego+="EL GANADOR DEL JUEGO ES: "+ j1.getNombre();
			}else if(j1.cantidadDeCartas() < j2.cantidadDeCartas()){
				this.juego+="EL GANADOR DEL JUEGO ES: " + j2.getNombre();
			}else{
				this.juego+="EMPATARON AMBOS JUGADORES";
			}
		}
		if(juego != null){
			System.out.println(this.juego);
		}

	}
	private int getRondaJugadas(){
		return this.rondasJugadas;
	}
	private void turno(Carta c,Atributo a,Jugador j){
		pocima = null;

		
		this.juego+= "La carta de " + j.getNombre()+ " es " + c.getNombre()+ " con " + a.getNombre()+" " +c.getValorAtributo(a) ;
		if(c.getPocima() != null) {
			

			pocima = c.getPocima();
			
			c.aplicarPocima();
			
		}
		
		if(pocima != null){
			this.juego+= " se aplico una pocima de " + c.getPocima() + " valor resultante " + c.getValorAtributo(a) + "\n";
		}else{
			this.juego+=  "\n";
		}
		
		
		
		
	}
	
	private Jugador turnoPelea(Carta c1,Carta c2,Atributo atributo,Carta auxc1,Carta auxc2,Jugador ganadorRonda){
		
		if( c1.getValorAtributo(atributo) > c2.getValorAtributo(atributo)){
			j1.addCarta(auxc1);
			j1.addCarta(auxc2);
			j1.deletePrimeraCarta();
			j2.deletePrimeraCarta();
			ganadorRonda = j1;
			this.juego += "Gana la ronda "+ j1.getNombre() + "\n";
			this.juego += j1.getNombre() + " posee ahora " + j1.cantidadDeCartas() + " cartas " + j2.getNombre() + " posee ahora " + j2.cantidadDeCartas() + " cartas \n";
			this.rondasJugadas++;
		}else if(c1.getValorAtributo(atributo) < c2.getValorAtributo(atributo)){

			j2.addCarta(auxc1);
			j2.addCarta(auxc2);
			j1.deletePrimeraCarta();
			j2.deletePrimeraCarta();
			ganadorRonda = j2;

			this.juego += "Gana la ronda "+ j2.getNombre() + "\n";
			this.juego += j1.getNombre() + " posee ahora " + j1.cantidadDeCartas() + " cartas " + j2.getNombre() + " posee ahora " + j2.cantidadDeCartas() + " cartas \n";

			this.rondasJugadas++;

		}else if(c1.getValorAtributo(atributo) == c2.getValorAtributo(atributo)){
			

			j1.addCarta(auxc1);
			j2.addCarta(auxc2);
			j1.deletePrimeraCarta();
			j2.deletePrimeraCarta();
			this.juego += " EMPATE " +"\n";
			this.juego += j1.getNombre() + " posee ahora " + j1.cantidadDeCartas() + " cartas " + j2.getNombre() + " posee ahora " + j2.cantidadDeCartas() + " cartas \n";

			this.rondasJugadas++;
		}
		return ganadorRonda;
	};
		
	
	
	 public static void main(String[] args) {
	     
		 
		 	String mazoPath = "./superheroes.json";
	    	//String mazoPath = "./autos.json";

	    	Mazo m1 = new Mazo(mazoPath);
	    	
	    	
	    	
	    	EstrategiaAmbicioso ambicioso = new EstrategiaAmbicioso();
			EstrategiaObstinado obstinado = new EstrategiaObstinado();
		//	EstrategiaTimbero timbero = new EstrategiaTimbero();
			
			
			//-------------Pócimas con el primer ejemplo como el enunciado , y el siguiente con un valor diferente 
			//-------------(Dos de cada tipo)
			
			PocimaPorcentaje pocimaF1 = new PocimaPorcentaje("Pócima Fortalecedora",0.2);
			PocimaPorcentaje pocimaF2 = new PocimaPorcentaje("Pócima Fortalecedora",0.4);
			
			PocimaPorcentaje pocimaFPlus1 = new PocimaPorcentaje("Pócima Fortalecedora Plus", 0.5);
			PocimaPorcentaje pocimaFPlus2 = new PocimaPorcentaje("Pócima Fortalecedora Plus", 0.7);
			
			PocimaPorcentaje pocimaR1 = new PocimaPorcentaje("Kriptonita", -0.25);
			PocimaPorcentaje pocimaR2 = new PocimaPorcentaje("Kriptonita", -0.40);
			
			PocimaPorcentaje pocimaR3 = new PocimaPorcentaje("Reductor de plomo", -0.55);
			PocimaPorcentaje pocimaR4 = new PocimaPorcentaje("Reductor de plomo", -0.75);
			
			
			
			PocimaValorUnico pocimaV1 = new PocimaValorUnico("Quiero vale 4", 4);
			PocimaValorUnico pocimaV2 = new PocimaValorUnico("Número mágico", 23);
			
			
			PocimaPorcentaje pocimaSF1 = new PocimaPorcentaje("Pócima Selectiva Fuerza", 0.35, "fuerza");
			PocimaPorcentaje pocimaSF2 = new PocimaPorcentaje("Pócima Selectiva Fuerza", 0.55, "fuerza");
			
			PocimaPorcentaje pocimaSP1 = new PocimaPorcentaje("Pócima Selectiva Peso", 0.43, "peso");
			PocimaPorcentaje pocimaSP2 = new PocimaPorcentaje("Pócima Selectiva Peso", 0.50, "peso");
			
			
			//------------Integrantes de las Cocktail-------------
			
			
			PocimaPorcentaje pocimaSF = new PocimaPorcentaje("Pócima Selectiva Fuerza", 0.35 ,"fuerza");
			PocimaPorcentaje pocimaFC1 = new PocimaPorcentaje("Pócima Fortalecedora", 0.2);

			PocimaPorcentaje pocimaR5 = new PocimaPorcentaje("Kriptonita", -0.25);
			PocimaValorUnico pocimaV3 = new PocimaValorUnico("Quiero vale 4", 4);
			PocimaPorcentaje pocimaFC2 = new PocimaPorcentaje("Pócima Fortalecedora", 0.4);
			
			PocimaValorUnico pocimaVU2 = new PocimaValorUnico("Pócima Valor Unico", 1);
			PocimaPorcentaje pocimaFPlus3 = new PocimaPorcentaje("Pócima Fortalecedora Plus", 0.6);
			
			
			
			//----------------PocimasCocktail---------------
			PocimaCocktail pocimaC1 = new PocimaCocktail("Pócima Cocktail");
			pocimaC1.agregarPocima(pocimaSF);
			pocimaC1.agregarPocima(pocimaFC1);
			
			PocimaCocktail pocimaC2 = new PocimaCocktail("Pócima Cocktail");
			pocimaC2.agregarPocima(pocimaR5);
			pocimaC2.agregarPocima(pocimaV3);
			pocimaC2.agregarPocima(pocimaFC2);
			
			
			
			PocimaCocktail pocimaC3 = new PocimaCocktail("Pócima Cocktail");
			pocimaC3.agregarPocima(pocimaVU2);
			pocimaC3.agregarPocima(pocimaFPlus3);
			pocimaC3.agregarPocima(pocimaC1);
	    	
	    	
	    	Jugador j1 = new Jugador("Cacha", obstinado);
	    	Jugador j2 = new Jugador("Matt", ambicioso);

	    	Juego juego = new Juego(m1,j1,j2,250);
	    	juego.agregarPocima(pocimaF1);
	    	juego.agregarPocima(pocimaF2);
	    	juego.agregarPocima(pocimaFPlus1);
	    	juego.agregarPocima(pocimaFPlus2);
	    	juego.agregarPocima(pocimaR1);
			juego.agregarPocima(pocimaR2);
			juego.agregarPocima(pocimaR3);
			juego.agregarPocima(pocimaR4);
			juego.agregarPocima(pocimaV1);
			juego.agregarPocima(pocimaV2);
			juego.agregarPocima(pocimaSF1);
			juego.agregarPocima(pocimaSF2);
			juego.agregarPocima(pocimaSP1);
			juego.agregarPocima(pocimaSP2);
			juego.agregarPocima(pocimaC1);
			juego.agregarPocima(pocimaC2);
	    	
	    
	    	
	    	juego.jugar();
	    	
	    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
