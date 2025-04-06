package it.uniroma3.diadia;

//import it.uniroma3.diadia.IOConsole.IOConsole;
import java.util.Scanner;

/**
 * Questa classe modella un comando.
 * Un comando consiste al piu' di due parole:
 * il nome del comando ed un parametro
 * su cui si applica il comando.
 * (Ad es. alla riga digitata dall'utente "vai nord"
 *  corrisponde un comando di nome "vai" e parametro "nord").
 *
 * @author  docente di POO
 * @version base
 */

public class Comando {

    private String nome;
    private String parametro;
    //private IOConsole io;
    

    //@SuppressWarnings("resource")
	public Comando(String istruzione) {
    	
    	/*if(this.io!=null)
    	
    	
    	istruzione = io.leggiRiga();
    	
    	String[] parola = istruzione.split("//s+");
    	
    	if(parola.length > 0) {
    		this.nome = parola[0];
    	}
    	
    	if(parola.length > 1) {
    		this.parametro = parola[1];
    	}
    	*/
    	
    	
		Scanner scannerDiParole = new Scanner(istruzione);
    	
    	

		// prima parola: nome del comando
		if (scannerDiParole.hasNext())     //
			this.nome = scannerDiParole.next(); 

		// seconda parola: eventuale parametro
		if (scannerDiParole.hasNext())
			this.parametro = scannerDiParole.next();

    }
    

    public String getNome() {
        return this.nome;
    }

    public String getParametro() {
        return this.parametro;
    }

    public boolean sconosciuto() {
        return (this.nome == null);
    }
}