package testFunzioni;
import it.uniroma3.diadia.ambienti.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class testStanzaBloccata {

    private StanzaBloccata stanzaBloccata;
    private Stanza stanzaSuccessiva;

    @Before
    public void setUp() {
        // Stanza bloccata a nord, si sblocca con "passepartout"
        stanzaBloccata = new StanzaBloccata("Ingresso", "nord", "passepartout");
        stanzaSuccessiva = new Stanza("Biblioteca");
        stanzaBloccata.impostaStanzaAdiacente("nord", stanzaSuccessiva);
    }

    @Test
    public void testGetStanzaAdiacente_DirezioneBloccata_SenzaAttrezzo() {
        // La direzione è bloccata => ritorna se stessa
        Stanza stanza = stanzaBloccata.getStanzaAdiacente("nord");
        assertSame("La stanza dovrebbe restituire se stessa (bloccata)", stanzaBloccata, stanza);
    }

    @Test
    public void testGetStanzaAdiacente_DirezioneBloccata_ConAttrezzoSbloccante() {
        // Posiamo il passepartout => direzione si sblocca
        stanzaBloccata.addAttrezzo(new Attrezzo("passepartout", 1));
        Stanza stanza = stanzaBloccata.getStanzaAdiacente("nord");
        assertSame("La stanza dovrebbe essere sbloccata", stanzaSuccessiva, stanza);
    }

    @Test
    public void testGetDescrizione_SenzaAttrezzoSbloccante() {
        // Nessun attrezzo => descrizione indica il blocco
        String descrizione = stanzaBloccata.getDescrizione();
        assertTrue("La descrizione dovrebbe indicare il blocco", 
                   descrizione.contains("Stanza bloccata nella direzione: nord"));
        assertTrue("La descrizione dovrebbe suggerire l'attrezzo sbloccante", 
                   descrizione.contains("passepartout"));
    }
}
