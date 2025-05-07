package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;


public class ComandoPosa implements Comando{

	
	private IO io;
	private String nomeAttrezzo;
	private final static String NOME = "posa";

	public ComandoPosa(String nomeAttrezzo) {
        this.nomeAttrezzo = nomeAttrezzo;
    }

	// Costruttore senza parametro (necessario per il fabbricante di comandi)
    public ComandoPosa() {
        this.nomeAttrezzo = ""; // Imposta un valore di default
    }
	
    
	@Override
	public void esegui(Partita partita) {
		Attrezzo a = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);

		if(partita.getStanzaCorrente().getNumeroAttrezziPossibili()>partita.getStanzaCorrente().getNumeroAttrezzi()) {
			partita.getStanzaCorrente().addAttrezzo(a);
			partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
			io.mostraMessaggio("Hai posato l'attrezzo " + nomeAttrezzo + " nella stanza.");
		}
		else {
			io.mostraMessaggio("Non c'e' spazio nella stanza per poter inserire questo attrezzo!");
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
