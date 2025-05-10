package testFunzioni;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.comandi.*;

public class testComandoVai {

    private Partita partita;
    private Labirinto labirinto;
    private Stanza stanzaIniziale;
    private Stanza stanzaNord;
    private ComandoVai comando;
    private IO io;

    @Before
    public void setUp() {
        // Inizializzazione ambiente di gioco
        stanzaIniziale = new Stanza("iniziale");
        stanzaNord = new Stanza("nord");

        stanzaIniziale.impostaStanzaAdiacente("nord", stanzaNord);

        labirinto = new Labirinto();
        labirinto.setStanzaCorrente(stanzaIniziale);

        partita = new Partita(); // Usa costruttore predefinito
        partita.getLabirinto().setStanzaCorrente(stanzaIniziale);

        comando = new ComandoVai();
        io = new IOConsole(); // puoi usare anche un mock
        comando.setIo(io);
    }

    @Test
    public void testVaiDirezioneValida() {
        comando.setParametro("nord");
        int cfuIniziali = partita.getGiocatore().getCfu();

        comando.esegui(partita);

        assertEquals("nord", partita.getStanzaCorrente().getNome());
        assertEquals(cfuIniziali - 1, partita.getGiocatore().getCfu());
    }

    @Test
    public void testVaiDirezioneInesistente() {
        comando.setParametro("sud");
        Stanza attuale = partita.getStanzaCorrente();
        int cfuIniziali = partita.getGiocatore().getCfu();

        comando.esegui(partita);

        assertEquals(attuale, partita.getStanzaCorrente());
        assertEquals(cfuIniziali, partita.getGiocatore().getCfu());
    }

    @Test
    public void testVaiSenzaParametro() {
        comando.setParametro(null);
        Stanza attuale = partita.getStanzaCorrente();
        int cfuIniziali = partita.getGiocatore().getCfu();

        comando.esegui(partita);

        assertEquals(attuale, partita.getStanzaCorrente());
        assertEquals(cfuIniziali, partita.getGiocatore().getCfu());
    }
}
