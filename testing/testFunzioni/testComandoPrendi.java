package testFunzioni;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.*;
import it.uniroma3.diadia.comandi.*;

public class testComandoPrendi {

    private ComandoPrendi comando;
    private Partita partita;
    private Labirinto labirinto;
    private Stanza stanza;
    private IO io;

    @Before
    public void setUp() {
        comando = new ComandoPrendi();
        io = new IOConsole(); // Sostituibile con mock, ma funziona così
        comando.setIo(io);

        stanza = new Stanza("stanza di test");
        labirinto = new Labirinto();
        labirinto.setStanzaCorrente(stanza);

        partita = new Partita(); // Usa costruttore senza parametri
        partita.getLabirinto().setStanzaCorrente(stanza);
    }

    @Test
    public void testPrendiAttrezzo_PresenteConPesoCompatibile() {
        Attrezzo penna = new Attrezzo("penna", 1);
        stanza.addAttrezzo(penna);

        comando.setParametro("penna");
        comando.esegui(partita);

        assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("penna"));
        assertFalse(stanza.hasAttrezzo("penna"));
    }

    @Test
    public void testPrendiAttrezzo_TroppoPesante() {
        Attrezzo masso = new Attrezzo("masso", 1000);
        stanza.addAttrezzo(masso);

        comando.setParametro("masso");
        comando.esegui(partita);

        assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("masso"));
        assertTrue(stanza.hasAttrezzo("masso"));
    }

    @Test
    public void testPrendiAttrezzo_Inesistente() {
        comando.setParametro("spada");
        comando.esegui(partita);

        assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
    }
}
