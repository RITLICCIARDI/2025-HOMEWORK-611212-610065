package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
//import it.uniroma3.diadia.giocatore.Giocatore;



public class ComandoVai implements Comando {
	
	private String nome;
    private String parametro;
    private IO io;
    private final static String NOME = "vai";
    
    public ComandoVai() {
        // Costruttore senza parametri, per poterlo istanziare senza parametri
        this.nome = NOME;  // Imposta un valore predefinito per il nome
    }

    public ComandoVai(String parametro) {
        this.parametro = parametro;
        this.nome = NOME;
    }

/**
* esecuzione del comando
*/
@Override
public void esegui(Partita partita) {
// qui il codice per cambiare stanza ...
	
	Stanza stanzaCorrente = partita.getStanzaCorrente();
	Stanza prossimaStanza = null;
	if (this.parametro == null) {
		this.io.mostraMessaggio("Dove vuoi andare? Devi specificare una direzione");
		return;
	}

	prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.parametro);
	if (prossimaStanza == null) {
		this.io.mostraMessaggio("Direzione inesistente");
		return;
	}

	partita.setStanzaCorrente(prossimaStanza);
	
	this.io.mostraMessaggio(partita.getStanzaCorrente().getNome());
	partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
	}

@Override
public void setParametro(String parametro) {
	this.parametro = parametro;
}

@Override
public String getParametro() {
	return this.parametro;
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
    return this.parametro == null || this.parametro.isEmpty();
}
}