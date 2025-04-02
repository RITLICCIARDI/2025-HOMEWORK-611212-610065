package it.uniroma3.diadia;

import it.uniroma3.diadia.IOConsole.IOConsole;

//import it.uniroma3.diadia.giocatore.Giocatore;
//import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "borsa"};

	private Partita partita;
	private IOConsole io;

	public DiaDia() {
		this.partita = new Partita();
		this.io = new IOConsole();
	}

	public void gioca() {
		String istruzione; 

		io.mostraMessaggio(MESSAGGIO_BENVENUTO);		
		do		
			istruzione = io.leggiRiga();
		while (!processaIstruzione(istruzione));//GIOCO CONTINUA FINCHE E' VERO QUANDO E' FALSO IL GIOCO FINISCE
	}   


	/**
	 * Processa una istruzione (NUCLEO DEL GIOCO)
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {	//PROCESSA SINGOLA ISTRUZIONE MODELLATA DA QUESTA STRINGA
		Comando comandoDaEseguire = new Comando(istruzione);

		if(comandoDaEseguire.getNome()!=null)
			
		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine();
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai")) {
			this.vai(comandoDaEseguire.getParametro());
		}
		else if (comandoDaEseguire.getNome().equals("prendi")) {
			this.prendi(comandoDaEseguire.getParametro());
		}
		else if (comandoDaEseguire.getNome().equals("posa")) {
			this.posa(comandoDaEseguire.getParametro());
		}
		else if (comandoDaEseguire.getNome().equals("aiuto")) {
			this.aiuto();
		}
		else if(comandoDaEseguire.getNome().equals("borsa")) {
			 io.mostraMessaggio(""+this.partita.getGiocatore().getBorsa());
		}
		else {
			io.mostraMessaggio("Comando inserito sconosciuto!!!");
		}
		if (this.partita.isFinita()) {
			if(this.partita.vinta()) {
					io.mostraMessaggio("CONGRATULAZIONI, HAI VINTO LA PARTITA!");
					return true;
			}
			else {
				io.mostraMessaggio("Hai perso...");
				return true;
			}
				
		} 
		/*else if(this.partita.getGiocatore().getCfu() == 0) {
			System.out.println("Hai esaurito i cfu!");
			return true;
		}*/
			return false;

	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			io.mostraMessaggio(elencoComandi[i]+" ");
		io.mostraMessaggio(" ");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {	//PRENDE LA DIREZIONI PER SPOSTARSI
		if(direzione==null)
			io.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null; 
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			io.mostraMessaggio("Direzione inesistente!");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(--cfu);
		}
		io.mostraMessaggio(this.partita.getStanzaCorrente().getDescrizione());
		io.mostraMessaggio("CFU rimanenti: " +this.partita.getGiocatore().getCfu());
	}

	
	private void prendi(String nomeAttrezzo) {
		if (nomeAttrezzo == null) {
			io.mostraMessaggio("Quale attrezzo vuoi prendere?");
		return;
		}
		else {
			Attrezzo attrezzo = this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
			if (attrezzo == null)
				io.mostraMessaggio("L'attrezzo che hai inserito non esiste nella stanza!");
			else {
				if (this.partita.getGiocatore().prendereAttrezzo(attrezzo)) {
					this.partita.getStanzaCorrente().removeAttrezzo(attrezzo);
					io.mostraMessaggio("Hai preso: "+nomeAttrezzo);

				} else
					io.mostraMessaggio("Non puoi prendere l'attrezzo!");
			}
		}
	}
	

	private void posa(String nomeAttrezzo) {
		if (nomeAttrezzo == null)
			io.mostraMessaggio("Quale attrezzo vuoi posare?");
		else {
			Attrezzo attrezzo = this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
			if (attrezzo == null)
				io.mostraMessaggio("L'attrezzo che hai inserito non esiste nella borsa!");
			else {
				this.partita.getGiocatore().posareAttrezzo(nomeAttrezzo);
				this.partita.getStanzaCorrente().addAttrezzo(attrezzo);
				io.mostraMessaggio("Hai posato: "+ nomeAttrezzo);
			}
		}
	}

	
	/**
	 * Comando "Fine".
	 */
	private void fine() {
		io.mostraMessaggio("Grazie per aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}