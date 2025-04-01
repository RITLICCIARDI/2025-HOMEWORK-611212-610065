package testFunzioni;

import it.uniroma3.diadia.attrezzi.*;
import static org.junit.Assert.*;
import it.uniroma3.diadia.giocatore.*;

import org.junit.Test;


public class testBorsa {
	
	@Test
	public void testAddAttrezzo() {
		//verifica che quando un attrezzo valido viene agg. alla borsa, il metodo restituisce true
		Attrezzo a = new Attrezzo("spada", 2);
		Borsa b = new Borsa();
		assertTrue(b.addAttrezzo(a));
	}
	
	@Test
	public void testAddAttrezzoNull() {
		//verifica che quando viene passato un null come attrezzo, il metodo restuisce false
		Borsa b = new Borsa();
		assertFalse(b.addAttrezzo(null));
	}
	
	@Test
	public void testGetPesoMax() {
		//verifica che il peso totale della borsa non superi il peso max consentito
		Borsa b = new Borsa();
		assertFalse(b.getPesoMax() == 5);
	}
}
