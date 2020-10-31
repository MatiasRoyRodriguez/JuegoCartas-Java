package TP;

public class Juego {
	private Mazo mazo;
	private Jugador j1;
	private Jugador j2;
	private int rondasMax;
	private int rondasJugadas=0;
	public Juego(Mazo mazo,int rondasMax){
		this.j1 = new Jugador("Cacha");
		this.j2 = new Jugador("Matt");
		this.mazo= mazo;
		this.rondasMax = rondasMax;
	}
	
	public int getRondasMax(){
		return this.rondasMax;
	}
	// TODO: ACOMODAR EL VER CARTAS XD DE JUGADOR
	public void jugar(){
		
		mazo.limpiarMazo();
		//Nos aseguramos de que el mazo tenga por lo menos 2 cartas 
		if(mazo.mazoHabilParaJugar()){
			repartirCartas();

			comienzoJuego();
			
		}
		
		
	}
	
	public void repartirCartas(){
		
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
	
	public void comienzoJuego( ){
		
		Jugador ganadorRonda = j1;
		String juego="";
		Atributo atributo;
		while(rondasJugadas<rondasMax && j1.quedanCartas() && j2.quedanCartas()){
			System.out.println("rondasJugadas" + rondasJugadas);
			System.out.println("rondasMax " + rondasMax);

			juego+="-------- RONDA N°" + (this.getRondaJugadas()+1) + "---------- \n";

			Carta c1 = j1.obtenerPrimera();
			Carta c2 = j2.obtenerPrimera();
			if(ganadorRonda.equals(j1)){
				 atributo = ganadorRonda.getAtributoRandom(c1);
					juego+= "El jugador "+ j1.getNombre() +" selecciona competir por el atributo " + atributo.getNombre() +"\n";

			}else{
				 atributo = ganadorRonda.getAtributoRandom(c2);
					juego+="El jugador "+ j2.getNombre() +" selecciona competir por el atributo " + atributo.getNombre() +"\n";

			};
			
			
			juego+= "La carta de " + j1.getNombre()+ " es " + c1.getNombre()+ " con " + atributo.getNombre()+" " +c1.getValorAtributo(atributo) + "\n";
			juego+= "La carta de " + j2.getNombre()+ " es " + c2.getNombre()+ " con " + atributo.getNombre()+" " +c2.getValorAtributo(atributo) + "\n";

			if( c1.getValorAtributo(atributo) > c2.getValorAtributo(atributo)){
				System.out.println("ENTRO 1");
				j1.addCarta(c1);
				j1.addCarta(c2);
				j1.deletePrimeraCarta();
				j2.deletePrimeraCarta();
				ganadorRonda = j1;
				juego += "Gana la ronda "+ j1.getNombre() + "\n";
				juego += j1.getNombre() + " posee ahora " + j1.cantidadDeCartas() + " cartas " + j2.getNombre() + " posee ahora " + j2.cantidadDeCartas() + " cartas \n";
				this.rondasJugadas++;
			}else if(c1.getValorAtributo(atributo) < c2.getValorAtributo(atributo)){
				System.out.println("ENTRO 2");

				j2.addCarta(c1);
				j2.addCarta(c2);
				j1.deletePrimeraCarta();
				j2.deletePrimeraCarta();
				ganadorRonda = j2;

				juego += "Gana la ronda "+ j2.getNombre() + "\n";
				juego += j1.getNombre() + " posee ahora " + j1.cantidadDeCartas() + " cartas " + j2.getNombre() + " posee ahora " + j2.cantidadDeCartas() + " cartas \n";

				this.rondasJugadas++;

			}else if(c1.getValorAtributo(atributo) == c2.getValorAtributo(atributo)){
				

				j1.addCarta(c1);
				j2.addCarta(c2);
				j1.deletePrimeraCarta();
				j2.deletePrimeraCarta();
				juego += " EMPATE " +"\n";
				juego += j1.getNombre() + " posee ahora " + j1.cantidadDeCartas() + " cartas " + j2.getNombre() + " posee ahora " + j2.cantidadDeCartas() + " cartas";

				this.rondasJugadas++;
			}
			
		};
		if(rondasJugadas==rondasMax || (!j1.quedanCartas() || !j2.quedanCartas())){
			if(j1.quedanCartas()){
				juego+="EL GANADOR DEL JUEGO ES: "+ j1.getNombre();
			}else{
				juego+="EL GANADOR DEL JUEGO ES: " + j2.getNombre();
			}
		}
		System.out.println(juego);

	}
	public int getRondaJugadas(){
		return this.rondasJugadas;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
