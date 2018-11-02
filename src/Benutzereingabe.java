import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Klasse ist für die Verarbeitung aller Benutzereingaben verantwortlich.
 * Erfüllt darüber hinaus die Aufgabe des "Programmflows"
 * 
 * @author Steffen Dworsky
 *
 */
public class Benutzereingabe {

	private Ausgabe ausgabe = LottoTippGenerator.ausgabe;
	private BufferedReader in;
	private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	public ProgramFlow programFlow = LottoTippGenerator.programFlow;

	/**
	 * Im Konstructor werden die benötigten Objekte initialisiert
	 */
	public Benutzereingabe() {
		in = new BufferedReader(new InputStreamReader(System.in));
		logger.log(Level.INFO, "Benutzereingabe Konstruktor durchgelaufen");
	}

	/**
	 * Funktion um den Spieler nach dem gewünschten Spielmodus zu Fragen. Gibt einen
	 * String zurück. Derzeitig sind 1 und 2 gültige Eingaben.
	 * 
	 */
	public String erfrageSpielModus() {
		String spielmodus;
		ausgabe.waehleSpiel();
		try {
			spielmodus = in.readLine();
			return spielmodus;
		} catch (IOException e) {
			logger.log(Level.WARNING, "Spielmodus konnte nicht eingelesen werden! 6aus49 wurde als Spielmodus gesetzt.",
					e);
			return spielmodus = "1";
		}
	}

	/**
	 * Funktion erwartet die Eingabe eines Befehls und gibt den Befehl mitsammt des
	 * Modus weiter an die Funktion BefehlAusfuehren()
	 * 
	 * @param modus
	 */
	public String erwarteBefehl(String modus) {
		String befehl = "";
		ausgabe.erwarteBefehl(modus);
		try {
			befehl = in.readLine();
			logger.log(Level.INFO, befehl + " als Befehl eingegeben!");
		} catch (IOException e) {
			logger.log(Level.WARNING, "Eingegebener Befehl konnte nicht eingelesen werden.", e);
		}
		return befehl;
	}

	/**
	 * Funktion um eine Zahl zu erfragen. Wandelt den eingegebenen String in ein int
	 * um und gibt dieses zurück. Wird keine Zahl übergeben, so wird die Funktion
	 * erneut aufgerufen bis eine Zahl übergeben wurde. Diese wird dann
	 * zurückgegeben.
	 * 
	 * @return Eine zahl
	 */
	public int erfrageLottoZahl() {
		ausgabe.zahlEingeben();
		String input = "";
		try {
			input = in.readLine();
		} catch (IOException e1) {
			logger.log(Level.WARNING, "Zahl konnte nicht eingelesen werden!", e1);
			e1.printStackTrace();
		}
		try {
			int zahl = Integer.parseInt(input);
			return zahl;
		} catch (NumberFormatException e) {
			ausgabe.istKeineZahl(input);
			logger.log(Level.WARNING, "Input (" + input + ") ist keine Zahl!", e);
			e.printStackTrace();
			erfrageLottoZahl();
		}
		return 0;
	}

	public void quit() {
		try {
			in.close();
			logger.log(Level.INFO, "BufferedReader in Benutzereingabe geschlossen.");
		} catch (IOException e) {
			logger.log(Level.WARNING, "BufferedReader konnte in Benutzereingabe nicht geschlossen werden!", e);
			e.printStackTrace();
		}
	}
}
