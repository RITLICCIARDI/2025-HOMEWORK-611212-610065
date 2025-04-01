package testFunzioni;

import it.uniroma3.diadia.giocatore.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;


public class testGiocatore {

		Giocatore g = new Giocatore();
		
		@Test
		//assicurarsi che quando viene creato un giocatore, i suoi CFU sono inizializzati correttamente
		public void testGetCfu() {
			assertEquals(20, g.getCfu());
		}

		@Test
		//modifica il valore dei cfu
		public void testSetCfu() {
			g.setCfu(3);
			assertEquals(3, g.getCfu());
		}
		
		@Test
		//assicurarsi che la borsa venga correttamente inizializzata
		public void testGetBorsa() {
			assertNotNull(g.getBorsa());
		}
	
}


