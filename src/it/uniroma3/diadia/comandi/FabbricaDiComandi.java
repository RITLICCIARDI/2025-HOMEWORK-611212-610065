package it.uniroma3.diadia.comandi;

public class FabbricaDiComandi {

	public Comando costruisciComando(String istruzione) {
		Comando comando = null;

        // Separa l'istruzione in nome del comando e parametro
        String[] parole = istruzione.split(" ");
        String nomeComando = parole[0];

        // A seconda del nome del comando, crea l'oggetto comando corretto
        switch (nomeComando) {
            case "vai":
                if (parole.length > 1) {
                    comando = new ComandoVai(parole[1]); // Direzione
                } else {
                    comando = new ComandoVai(null); // Nessuna direzione
                }
                break;
            case "prendi":
                if (parole.length > 1) {
                    comando = new ComandoPrendi(parole[1]); // Nome attrezzo
                }
                break;
            case "posa":
                if (parole.length > 1) {
                    comando = new ComandoPosa(parole[1]); // Nome attrezzo
                }
                break;
            case "aiuto":
                comando = new ComandoAiuto();
                break;
            case "fine":
                comando = new ComandoFine();
                break;
            case "guarda":
                comando = new ComandoGuarda();
                break;
            default:
                comando = new ComandoNonValido(); // Se il comando non è riconosciuto
                break;
        }

        return comando;
	}
}
