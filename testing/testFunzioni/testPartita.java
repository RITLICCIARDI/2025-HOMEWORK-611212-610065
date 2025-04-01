package testFunzioni;

import org.junit.jupiter.api.BeforeEach;



import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.giocatore.*;


class testPartita {
	
	private Partita p;
	private Labirinto l;
	private Giocatore g;
	
	@BeforeEach
	public void setUp() {
		
		//inizializzazione degli oggetti
		l = new Labirinto();
		p = new Partita();
		g = p.getGiocatore();
		
	}
	
	@Test
	public void testPartitaNonFinita() {
		//verifica che la partita non sia finita all'inizio
		assertFalse(p.isFinita());
		//this.partita.setFinita();
		//assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testPartitaVinta() {
		//simula vittoria della partita
		Stanza stanzaFinale = new Stanza("Stanza Finale");
		p.setStanzaCorrente(stanzaFinale);
		assertTrue(p.isFinita());
	}
	
	@Test
	public void zeroCfuTest() {
		//verifica che la partita finisca quando ci sono 0 cfu
		g.setCfu(0);
		assertTrue(p.isFinita());
	}
}
