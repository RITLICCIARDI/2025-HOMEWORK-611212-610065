package testFunzioni;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class testStanza {

	private Stanza atrio;
	private Stanza aulaN11;
	private Attrezzo lanterna;
	
	@BeforeEach
	
	public void setUp() {
		
		atrio = new Stanza("atrio");
		aulaN11 = new Stanza("aula N11");
		lanterna = new Attrezzo("lanterna", 3);
	}
	
	@Test
	
	public void testAddAttrezzo() {
		boolean risultato = atrio.addAttrezzo(lanterna);
		
		assertTrue(risultato, "L'Attrezzo dovrebbe essere aggiunto correttamente.");
		
	}
	
	@Test
	public void testGetAttrezzo() {
		
		atrio.addAttrezzo(lanterna);
		Attrezzo attrezzoTrovato = atrio.getAttrezzo("lanterna");
		assertNotNull(attrezzoTrovato, "L'Attrezzo dovrebbe essere presente nella stanza.");
		assertEquals("lanterna", attrezzoTrovato.getNome(), "L'attrezzo trovato dovrebbe essere la  lanterna");
	}
	
	@Test
	public void testImpostaStanzaAdiacente() {
		atrio.impostaStanzaAdiacente("est", aulaN11);
		Stanza StanzaAdiacente = atrio.getStanzaAdiacente("est");
		assertNotNull(StanzaAdiacente, "La stanza adiacente dovrebbe essere correttamente impostata.");
		assertEquals(aulaN11.getNome(), StanzaAdiacente.getNome(), "La stanza adiacente dovrebbe essere aula N11");
		
	}
	

}
