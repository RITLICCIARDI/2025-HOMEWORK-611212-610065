package it.uniroma3.diadia;


import java.util.Scanner;



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
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	private Partita partita;

	public DiaDia() {
		this.partita = new Partita();
	}

	public void gioca() {
		String istruzione; 
		Scanner scannerDiLinee;

		System.out.println(MESSAGGIO_BENVENUTO);
		scannerDiLinee = new Scanner(System.in);		
		do		
			istruzione = scannerDiLinee.nextLine();
		while (!processaIstruzione(istruzione));//GIOCO CONTINUA FINCHE E' VERO QUANDO E' FALSO IL GIOCO FINISCE
		scannerDiLinee.close();
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
		else {
			System.out.println("Comando inserito non corretto!!!");
		}
		if (this.partita.isFinita()) {
			if(this.partita.vinta()) {
					System.out.println("CONGRATULAZIONI, HAI VINTO LA PARTITA!");
					return true;
			}
			else {
				System.out.println("Hai perso...");
				return false;
			}
				
		} 
		else if(this.partita.getGiocatore().getCfu() <= 0) {
			System.out.println("Hai esaurito i cfu!");
			return true;
		}
			return false;

	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			System.out.print(elencoComandi[i]+" ");
		System.out.println(" ");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {	//PRENDE LA DIREZIONI PER SPOSTARSI
		if(direzione==null)
			System.out.println("Dove vuoi andare ?");
		Stanza prossimaStanza = null; 
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			System.out.println("Direzione inesistente!");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(--cfu);
		}
		System.out.println(this.partita.getStanzaCorrente().getDescrizione());
		System.out.println("CFU rimanenti: " +this.partita.getGiocatore().getCfu());
	}

	
	private void prendi(String nomeAttrezzo) {
		if (nomeAttrezzo == null) {
			System.out.println("Quale attrezzo vuoi prendere?");
		return;
		}
		else {
			Attrezzo attrezzo = this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
			if (attrezzo == null)
				System.out.println("L'attrezzo che hai inserito non esiste nella stanza!");
			else {
				if (this.partita.getGiocatore().prendereAttrezzo(attrezzo)) {
					this.partita.getStanzaCorrente().removeAttrezzo(attrezzo);
					System.out.println("Hai preso: "+nomeAttrezzo);

				} else
					System.out.println("Non puoi prendere l'attrezzo!");
			}
		}
	}

	private void posa(String nomeAttrezzo) {
		if (nomeAttrezzo == null)
			System.out.println("Quale attrezzo vuoi posare?");
		else {
			Attrezzo attrezzo = this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
			if (attrezzo == null)
				System.out.println("L'attrezzo che hai inserito non esiste nella borsa!");
			else {
				this.partita.getGiocatore().posareAttrezzo(nomeAttrezzo);
				this.partita.getStanzaCorrente().addAttrezzo(attrezzo);
				System.out.println("Hai posato: "+ nomeAttrezzo);
			}
		}
	}

	
	
	/**
	 * Comando "Fine".
	 */
	private void fine() {
		System.out.println("Grazie per aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}