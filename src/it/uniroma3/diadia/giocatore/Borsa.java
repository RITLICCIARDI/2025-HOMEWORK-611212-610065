package it.uniroma3.diadia.giocatore;
import it.uniroma3.diadia.attrezzi.Attrezzo;


public class Borsa {

	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;
	public Borsa() {
	this(DEFAULT_PESO_MAX_BORSA);
	}
	public Borsa(int pesoMax) {
	this.pesoMax = pesoMax;
	this.attrezzi = new Attrezzo[10]; // speriamo bastino...
	this.numeroAttrezzi = 0;
	}
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(attrezzo == null) {
			return false;
		}
	if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
	return false;
	if (this.numeroAttrezzi==10)
	return false;
	this.attrezzi[this.numeroAttrezzi] = attrezzo;
	this.numeroAttrezzi++;
	return true;
	}
	
	public int getPesoMax() {
	return pesoMax;
	}
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
	Attrezzo a = null;
	for (int i= 0; i<this.numeroAttrezzi; i++)
	if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
	a = attrezzi[i];

	return a;
	}
	
	public int getPeso() {
		int peso = 0;
		for (int i= 0; i<this.numeroAttrezzi; i++)
		peso += this.attrezzi[i].getPeso();

		return peso;
		} 
	 public int getPesoRimanente(Attrezzo attrezzo) {
	        int pesoTotale = this.getPeso() + attrezzo.getPeso();
	        return this.pesoMax - pesoTotale;
	    }
	
		public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
		}
		public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
		}
		public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i = 0; i < this.numeroAttrezzi; i++) {
	        if (this.attrezzi[i].getNome().equals(nomeAttrezzo)) {
	            Attrezzo attrezzoRimosso = this.attrezzi[i];

	            // Spostiamo gli attrezzi successivi per chiudere il buco nell'array
	            for (int j = i; j < this.numeroAttrezzi - 1; j++) {
	                this.attrezzi[j] = this.attrezzi[j + 1];
	            }

	            this.attrezzi[this.numeroAttrezzi - 1] = null; // Pulizia della posizione finale
	            this.numeroAttrezzi--;

	            return attrezzoRimosso;
	        }
	    }
	
		return a;
		}
		
		public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
		s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
		for (int i= 0; i<this.numeroAttrezzi; i++)
		s.append(attrezzi[i].toString()+" ");
		}
		else
		s.append("Borsa vuota");
		return s.toString();
		}
}
