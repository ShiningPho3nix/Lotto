import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
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

	private Ausgabe ausgabe;
	private BufferedReader in;
	private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	public ProgramFlow programFlow = LottoTippGenerator.programFlow;

	/**
	 * Im Konstructor wird ein Ausgabe Objekt erzeugt.
	 */
	public Benutzereingabe() {
		ausgabe = new Ausgabe();
		logger.setUseParentHandlers(false);
		logger.log(Level.INFO, "Benutzereingabe Konstruktor durchgelaufen");
	}

	/**
	 * Funktion erwartet die Eingabe eines Befehls und gibt den Befehl mitsammt des
	 * Modus weiter an die Funktion BefehlAusfuehren(). Kann auch einen Befehl in
	 * form eines Reader übergeben bekommen.
	 * 
	 * @param reader
	 */
	public String erwarteBefehl(Reader reader) {
		String befehl = "";
		ausgabe.erwarteBefehl();
		if (reader == null) {
			return befehl;
		} else {
			in = new BufferedReader(reader);
			try {
				befehl = in.readLine();
				logger.log(Level.INFO, befehl + " als Befehl eingegeben!");
				return befehl = befehl.toUpperCase();
			} catch (IOException e) {
				logger.log(Level.WARNING, "Eingegebener Befehl konnte nicht eingelesen werden.", e);
				return befehl;
			}
		}
	}

	/**
	 * Funktion um eine Zahl oder mehrere Zahlen zu erfragen. Die Zahlen können auch
	 * als Reader übergeben werden. Wandelt den eingegebenen String in ein Integer[]
	 * um und gibt dieses zurück. Enthält der String Zeichen, welche keine Zahlen
	 * sind, so werden diese Ignoriert. zum abschluss wird nullEntfernen ausgeführt
	 * und das ergebniss als integer[] zurückgegeben.
	 * 
	 * @return Integer[]
	 */
	public Integer[] erfrageLottoZahlen(Reader reader) {
		String input = "";
		in = new BufferedReader(reader);
		try {
			input = in.readLine();
		} catch (IOException e1) {
			logger.log(Level.WARNING, "Zahlen konnte nicht eingelesen werden!", e1);
			e1.printStackTrace();
		}
		String[] parts = input.split(" ");
		Integer[] zahlen = new Integer[parts.length];
		String currentString = "";
		for (int i = 0; i < parts.length; i++) {
			currentString = parts[i];
			try {
				zahlen[i] = Integer.parseInt(parts[i]);
			} catch (NumberFormatException e) {
				ausgabe.istKeineZahl(currentString);
				logger.log(Level.WARNING, "Input (" + currentString + ") ist keine Zahl!", e);
				continue;
			}
		}
		quit();
		return nullEntfernen(zahlen);
	}

	/**
	 * Funktion entfernt aus einem übergebenen Integer[] alle null Werte und gibt
	 * das Ergebnis als Integer[] zurück.
	 * 
	 * @param Integer[]
	 * @return Integer[]
	 */
	public static Integer[] nullEntfernen(Integer[] a) {
		ArrayList<Integer> nullEntfernt = new ArrayList<Integer>();
		for (Integer integer : a)
			if (integer != null)
				nullEntfernt.add(integer);
		return nullEntfernt.toArray(new Integer[0]);
	}

	/**
	 * Schlißt den BufferedReader.
	 */
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
