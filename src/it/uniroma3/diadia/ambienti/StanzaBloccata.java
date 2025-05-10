package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {

    private String direzioneBloccata;
    private String attrezzoSbloccante;

    public StanzaBloccata(String nome, String direzioneBloccata, String attrezzoSbloccante) {
        super(nome);
        this.direzioneBloccata = direzioneBloccata;
        this.attrezzoSbloccante = attrezzoSbloccante;
    }

    @Override
    public Stanza getStanzaAdiacente(String direzione) {
        if (direzione.equals(this.direzioneBloccata) && !super.hasAttrezzo(attrezzoSbloccante)) {
            return this; // Bloccata: ritorna se stessa
        }
        return super.getStanzaAdiacente(direzione);
    }

    @Override
    public String getDescrizione() {
        if (!super.hasAttrezzo(attrezzoSbloccante)) {
            return "Stanza bloccata nella direzione: " + direzioneBloccata +
                   "\nPrendi il " + attrezzoSbloccante + " e posalo nella stanza";
        }
        return super.getDescrizione();
    }
}
