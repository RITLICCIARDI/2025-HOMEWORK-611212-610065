package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;


public class ComandoPrendi implements Comando{
	private IO io;
	private String nomeAttrezzo;
	private final static String NOME = "prendi";

	
	public ComandoPrendi(String nomeAttrezzo) {
	    this.nomeAttrezzo = nomeAttrezzo;
	}
	
	// Costruttore senza parametro (necessario per il fabbricante di comandi)
    public ComandoPrendi() {
        this.nomeAttrezzo = ""; // Imposta un valore di default
    }
	
	@Override
	public void esegui(Partita partita) {
		Attrezzo a = partita.getLabirinto().getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		if(partita.getGiocatore().getBorsa().getPesoRimanente(a)>=0) {
			partita.getGiocatore().getBorsa().addAttrezzo(a);
			partita.getLabirinto().getStanzaCorrente().removeAttrezzo(a);
			io.mostraMessaggio("Hai preso l'attrezzo: " + nomeAttrezzo);
		} 
		else {
			io.mostraMessaggio("Attrezzo troppo pesante per entrare nella borsa!");
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo  = parametro;

	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

	@Override
	public void setIo(IO io) {
		this.io = io;
	}
	
	@Override
	public String getNome() {
		return NOME;
	}

	@Override
    public boolean sconosciuto() {
        // Restituisci false, poiché il comando non ha parametri sconosciuti
        return this.nomeAttrezzo == null || this.nomeAttrezzo.isEmpty();
    }
	
	
}
