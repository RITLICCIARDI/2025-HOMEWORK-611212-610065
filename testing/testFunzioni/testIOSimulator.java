package testFunzioni;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.*;

import static org.junit.jupiter.api.Assertions.*;

class testIOSimulator {

    private IOSimulator ioSimulator;

    @BeforeEach
    void setUp() {
        // Creiamo un array con le righe da leggere per simulare l'input
        String[] righeDaLeggere = {"Comando 1", "Comando 2", "Comando 3"};
        ioSimulator = new IOSimulator(righeDaLeggere);
    }

    @Test
    void testLeggiRiga() {
        // Verifica che il metodo legga correttamente le righe
        assertEquals("Comando 1", ioSimulator.leggiRiga());
        assertEquals("Comando 2", ioSimulator.leggiRiga());
        assertEquals("Comando 3", ioSimulator.leggiRiga());
    }

    @Test
    void testMostraMessaggio() {
        // Simula l'output che l'IOSimulator deve "scrivere"
        ioSimulator.mostraMessaggio("Messaggio 1");
        ioSimulator.mostraMessaggio("Messaggio 2");

        // Verifica che i messaggi siano stati registrati correttamente
        assertEquals("Messaggio 1", ioSimulator.nextMessaggio());
        assertEquals("Messaggio 2", ioSimulator.nextMessaggio());
    }

    @Test
    void testHasNextMessaggio() {
        // Aggiungiamo un messaggio e verifichiamo la condizione
        ioSimulator.mostraMessaggio("Messaggio 1");

        // Verifica che ci siano ancora messaggi da leggere
        assertTrue(ioSimulator.hasNextMessaggio());

        // Dopo aver letto il messaggio, non ci sono più messaggi
        ioSimulator.nextMessaggio();
        assertFalse(ioSimulator.hasNextMessaggio());
    }
}
