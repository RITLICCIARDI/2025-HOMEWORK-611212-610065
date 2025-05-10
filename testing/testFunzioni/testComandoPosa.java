package testFunzioni;

import it.uniroma3.diadia.comandi.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.ambienti.Stanza;
//import it.uniroma3.diadia.giocatore.Giocatore;

public class testComandoPosa {

    private ComandoPosa comando;
    private Partita partita;
    private IO io;

    @Before
    public void setUp() {
        this.comando = new ComandoPosa();
        this.io = new IOConsole();  // Puoi usare una versione mockata se preferisci
        this.comando.setIo(io);

        Stanza stanza = new Stanza("atrio");
        this.partita = new Partita();
        this.partita.setStanzaCorrente(stanza);
    }

    @Test
    public void testPosaAttrezzo_PresenteInBorsaEStanzaLibera() {
        Attrezzo attrezzo = new Attrezzo("chiave", 1);
        partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);

        comando.setParametro("chiave");
        comando.esegui(partita);

        assertNull(partita.getGiocatore().getBorsa().getAttrezzo("chiave"));
        assertTrue(partita.getStanzaCorrente().hasAttrezzo("chiave"));
    }

    @Test
    public void testPosaAttrezzo_NonPresenteInBorsa() {
        comando.setParametro("martello");
        comando.esegui(partita);

        assertFalse(partita.getStanzaCorrente().hasAttrezzo("martello"));
    }

    @Test
    public void testPosaAttrezzo_StanzaPiena() {
        // Riempie la stanza fino al massimo
        for (int i = 0; i < 10; i++) {
            partita.getStanzaCorrente().addAttrezzo(new Attrezzo("a" + i, 1));
        }

        Attrezzo attrezzo = new Attrezzo("pietra", 2);
        partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
        comando.setParametro("pietra");
        comando.esegui(partita);

        assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("pietra"));
        assertFalse(partita.getStanzaCorrente().hasAttrezzo("pietra"));
    }
}

