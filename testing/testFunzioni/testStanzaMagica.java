package testFunzioni;
import it.uniroma3.diadia.ambienti.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class testStanzaMagica {

    private StanzaMagica stanzaMagica;

    @Before
    public void setUp() {
        // Soglia = 2 → il 3° attrezzo viene trasformato
        stanzaMagica = new StanzaMagica("Magica", 2);
    }

    @Test
    public void testAttrezzoPrimaDellaSoglia_NonTrasformato() {
        Attrezzo libro = new Attrezzo("libro", 1);
        stanzaMagica.addAttrezzo(libro);

        assertTrue(stanzaMagica.hasAttrezzo("libro"));
        assertEquals(1, stanzaMagica.getAttrezzo("libro").getPeso());
    }

    @Test
    public void testAttrezzoEsattamenteAllaSoglia_NonTrasformato() {
        stanzaMagica.addAttrezzo(new Attrezzo("penna", 1));  // contatore = 1
        stanzaMagica.addAttrezzo(new Attrezzo("quaderno", 2));  // contatore = 2

        assertTrue(stanzaMagica.hasAttrezzo("quaderno"));
        assertEquals(2, stanzaMagica.getAttrezzo("quaderno").getPeso());
    }

    @Test
    public void testAttrezzoDopoSoglia_Trasformato() {
        stanzaMagica.addAttrezzo(new Attrezzo("mela", 1));     // contatore = 1
        stanzaMagica.addAttrezzo(new Attrezzo("banana", 1));   // contatore = 2
        stanzaMagica.addAttrezzo(new Attrezzo("pietra", 3));   // contatore = 3 → TRASFORMATO

        // "pietra" → nome trasformato = "arteip", peso = 6
        assertFalse(stanzaMagica.hasAttrezzo("pietra"));
        assertTrue(stanzaMagica.hasAttrezzo("arteip"));
        Attrezzo trasformato = stanzaMagica.getAttrezzo("arteip");
        assertEquals(6, trasformato.getPeso());
    }
}
