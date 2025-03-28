package testFunzioni;
<<<<<<< Updated upstream
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;


class testPartita {
	
	private Partita partita;
	
	@BeforeEach
	public void setUp() {
		
		partita = new Partita();
		
		
	}
	
	@Test
	public void testPartitaNonFinita() {
		assertFalse(this.partita.isFinita());
		this.partita.setFinita();
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testPartitaVinta() {
		partita.setStanzaCorrente(partita.getStanzaVincente());
		assertTrue(this.partita.vinta());
	}
	
	
=======

public class testPartita {
>>>>>>> Stashed changes

}
