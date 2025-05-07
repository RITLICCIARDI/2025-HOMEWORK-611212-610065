package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;



import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;
//import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	static final private int CFU_INIZIALI = 20;

	private Labirinto labirinto;
	//private Stanza stanzaCorrente;
	//private Stanza stanzaVincente;
	private boolean finita;
	//private int cfu;
	private Giocatore giocatore;
	
	public Partita(){
		labirinto = new Labirinto();
		//this.stanzaCorrente = labirinto.getStanzaIniziale();
		//creaStanze();
		this.finita = false;
		giocatore = new Giocatore(); 
		this.giocatore.setCfu(CFU_INIZIALI);
		//labirinto.creaStanze();
	}

    /**
     * Crea tutte le stanze e le porte di collegamento
     */
	
	/*
    private void creaStanze() {

		/* crea gli attrezzi */
	/*
    	Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
    	
		/* crea stanze del labirinto */
	/*
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");
		
		/* collega le stanze */
	/*
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);

        /* pone gli attrezzi nelle stanze */
	/*
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);

		// il gioco comincia nell'atrio
        stanzaCorrente = atrio;  
		stanzaVincente = biblioteca;
    }
*/
	/*
	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}
	
	
	
	

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	*/
	
	public Labirinto getLabirinto(){
		return labirinto;
	}

	public void setLabirinto(Labirinto labirinto) {
		this.labirinto = labirinto;
	}


	public Giocatore getGiocatore() {
		return giocatore;
	}

	public void setGiocatore(Giocatore giocatore) {
		this.giocatore = giocatore;
	}

	
	
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		//return this.getStanzaCorrente().equals(this.labirinto.getStanzaFinale());
		return labirinto.getStanzaCorrente()== labirinto.getStanzaFinale();

	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || this.giocatore.getCfu() == 0;
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
/*
	public void setStanzaCorrente(Stanza stanza) {
		this.stanzaCorrente = stanza;
		if(stanza.getNome().equals("Stanza Finale")) {
			this.finita = true;
		}
	}
	*/
	public void setFinita() {
		this.finita = true;
	}

	public int getCfu() {
		return this.giocatore.getCfu();
	}

	public void setCfu(int cfu) {
		this.giocatore.setCfu(cfu);;		
	}	
	
	/*
	public String toString() {
	return this.getStanzaCorrente()+ "\nCfu: " + this.getCfu();
	}
	
	
	public Giocatore getGiocatore() {
		return this.giocatore;
	}
	*/
	/**
	 * Imposta la partita come finita
	 *
	 */


	public boolean giocatoreIsVivo() {
		return this.giocatore.getCfu()>0;
	}
	
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.getLabirinto().setStanzaCorrente(stanzaCorrente);
	}

	public Stanza getStanzaCorrente() {
		return this.getLabirinto().getStanzaCorrente();
	}
	

	
}
