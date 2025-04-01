package testFunzioni;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import it.uniroma3.diadia.ambienti.*;

public class testLabirinto {

		private final Labirinto l = new Labirinto();
		private final Stanza si = new Stanza("Atrio");
		private final Stanza sd = new Stanza("Stanza diversa");
		//private final Stanza sv = new Stanza("Bibilioteca");
		
		@Test
		public void getStanzaIniziale() {
			//verifica che il nome della stanza iniziale nel labirinto corrisponde al valore attesso "Atrio"
			assertEquals(si.getNome(),l.getStanzaIniziale().getNome());
		}
		@Test
		 public void getStanzaVincente() {
			//garantisce che il nome della stanza finale sia quello atteso "Biblioteca"
			 assertEquals("Biblioteca",l.getStanzaFinale().getNome());
		 }
		
		@Test
		public void diversoAtrio() {
			//controlla che la stanza iniziale non sia la stessa di un'altra stanza
			assertFalse(l.getStanzaIniziale().getNome() == sd.getNome());
		}
		
}
