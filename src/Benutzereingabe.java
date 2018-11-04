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
	 * Funktion erwartet die Eingabe eines Befehls und gibt den Befehl mitsammt des
	 * Modus weiter an die Funktion BefehlAusfuehren()
	 * 
	 * @param modus
	 */
	public String erwarteBefehl() {
		String befehl = "";
		ausgabe.erwarteBefehl();
		try {
			befehl = in.readLine();
			logger.log(Level.INFO, befehl + " als Befehl eingegeben!");
			return befehl = befehl.toUpperCase();
		} catch (IOException e) {
			logger.log(Level.WARNING, "Eingegebener Befehl konnte nicht eingelesen werden.", e);
			return "";
		}
	}

	/**
	 * Funktion um eine Zahl zu erfragen. Wandelt den eingegebenen String in ein int
	 * um und gibt dieses zurück. Wird keine Zahl übergeben, so wird die Funktion
	 * erneut aufgerufen bis eine Zahl übergeben wurde. Diese wird dann
	 * zurückgegeben.
	 * 
	 * @return Eine zahl
	 */
	public int[] erfrageLottoZahlen() {
		ausgabe.zahlEingeben();
		String input = "";
		try {
			input = in.readLine();
		} catch (IOException e1) {
			logger.log(Level.WARNING, "Zahlen konnte nicht eingelesen werden!", e1);
			e1.printStackTrace();
		}
		String[] parts = input.split(" ");
		int[] zahlen = new int[parts.length];
		String currentString = "";
		for (int i = 0; i < parts.length; i++) {
			try {
				zahlen[i] = Integer.parseInt(parts[i]);
				currentString = parts[i];
			} catch (NumberFormatException e) {
				ausgabe.istKeineZahl(currentString);
				logger.log(Level.WARNING, "Input (" + currentString + ") ist keine Zahl!", e);
				e.printStackTrace();
				continue;
			}
		}
		return zahlen;
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
