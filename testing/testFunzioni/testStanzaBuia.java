package testFunzioni;
import it.uniroma3.diadia.ambienti.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class testStanzaBuia {

    private StanzaBuia stanzaBuia;

    @Before
    public void setUp() {
        // La stanza è buia e si "illumina" solo con una lanterna
        stanzaBuia = new StanzaBuia("Atrio", "lanterna");
    }

    @Test
    public void testGetDescrizione_SenzaLanterna() {
        // Nessun attrezzo => dovrebbe essere buio
        assertEquals("qui c'è un buio pesto", stanzaBuia.getDescrizione());
    }

    @Test
    public void testGetDescrizione_ConLanterna() {
        // Aggiungo la lanterna => dovrebbe essere visibile la descrizione completa
        stanzaBuia.addAttrezzo(new Attrezzo("lanterna", 1));
        assertEquals(stanzaBuia.toString(), stanzaBuia.getDescrizione());
    }

    @Test
    public void testGetDescrizione_ConAltroAttrezzo() {
        // Aggiungo un attrezzo che non è la lanterna => ancora buio
        stanzaBuia.addAttrezzo(new Attrezzo("osso", 1));
        assertEquals("qui c'è un buio pesto", stanzaBuia.getDescrizione());
    }
}
