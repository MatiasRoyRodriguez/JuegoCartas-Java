package TP;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class Mazo {
	private ArrayList<Carta> cartas;
	
	public Mazo(String mazoPath){
		this.cartas = new ArrayList<>();
		this.crearMazo(mazoPath);
	}
	public Mazo() {
		cartas = new ArrayList<>();
	}
	public void addCartas(Carta c){
		this.cartas.add(c);
	}
	
	public Carta getPrimeraCarta(){
		if(cartas != null){
			return this.cartas.get(0);
		}
		return null;
		
	}
	public int cantCartas(){
		return this.cartas.size();
	}
	
	public Carta devolverCarta(int i){
		Carta aux = this.cartas.get(i);
		return aux;
	}
	
	public  void crearMazo(String jsonFile) {
        //URL url = getClass().getResource(jsonFile);
        File jsonInputFile = new File(jsonFile);
        InputStream is;
        try {
            is = new FileInputStream(jsonInputFile);
            // Creo el objeto JsonReader de Json.
            JsonReader reader = Json.createReader(is);
            // Obtenemos el JsonObject a partir del JsonReader.
            JsonArray cartas = (JsonArray) reader.readObject().getJsonArray("cartas");
            for (JsonObject carta : cartas.getValuesAs(JsonObject.class)) {
            	
                String nombreCarta = carta.getString("nombre");
                
                JsonObject atributos = (JsonObject) carta.getJsonObject("atributos");
                String atributosStr = "";
                Carta c = new Carta(nombreCarta);
                this.cartas.add(c);
                for (String nombreAtributo:atributos.keySet()){
                	
                    atributosStr = atributosStr + nombreAtributo + ": " +
                            atributos.getInt(nombreAtributo) + "; ";
                    
                    Atributo a = new Atributo(nombreAtributo, atributos.getInt(nombreAtributo));
                    
                    
                    c.addAtributo(a);
                }
                
               
            }

            reader.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    } 
	
	
	
	
	public void limpiarMazo(){
		Carta cartaComp = this.getPrimeraCarta();
		ArrayList<Carta> aux = new ArrayList<>();
		for(Carta carta:cartas){

			if(cartaComp.equals(carta)){
				aux.add(carta);
			}
		}
		this.cartas.clear();
		this.cartas.addAll(aux);
		
		
	
	}
	
	
	public boolean mazoHabilParaJugar(){
		//Como minimo debe tener 2 cartas para poder jugar una ronda
		return (this.cartas.size()>=2);
	}
	public void deletePrimeraCarta(){
		this.cartas.remove(0);
	}
	public ArrayList<Carta> getCartas() {
		ArrayList<Carta> aux = new ArrayList<>(this.cartas);

		return aux;
	}

	public void setPocima(Pocima pocima, int indice) {
		this.cartas.get(indice).setPocima(pocima);		
	}
}
