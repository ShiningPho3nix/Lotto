import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Klasse ist f�r die Verarbeitung aller Benutzereingaben verantwortlich.
 * Erf�llt dar�ber hinaus die Aufgabe des "Programmflows"
 * 
 * @author Steffen Dworsky
 *
 */
public class Benutzereingabe {

	private BufferedReader in;
	private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * Im Konstructor wird ein Ausgabe Objekt erzeugt.
	 */
	public Benutzereingabe() {
		logger.setUseParentHandlers(false); // Da es in den Testklassen auch log Ausgaben aus den Funktionen dieser
											// Klasse gibt, wird ParentHandlers auf False gesetzt um die Konsole sauber
											// zu halten.
		logger.log(Level.INFO, "Benutzereingabe Konstruktor durchgelaufen");
	}

	/**
	 * Funktion erwartet die Eingabe eines Befehls und gibt den Befehl mitsammt des
	 * Modus weiter an die Funktion BefehlAusfuehren(). Kann auch einen Befehl in
	 * form eines Reader �bergeben bekommen.
	 * 
	 * @param reader
	 */
	public String erwarteBefehl(Reader reader) {
		String befehl = "";
		StringSammlung.erwarteBefehl();
		in = new BufferedReader(reader); // ein neuer BufferedReader wird erzeugt mit dem �bergebenen reader.
											// Meist InputStreamReader(System.in), f�r Testzwecke kann auch ein
											// beliebiger String mittels StringReader �bergeben werden.
		try {
			befehl = in.readLine(); // Der eingegebene Befehl wird eingelesen...
			logger.log(Level.INFO, befehl + " als Befehl eingegeben!");
			return befehl = befehl.toUpperCase(); // ... und als toUpperCase returned
		} catch (IOException e) {
			logger.log(Level.WARNING, "Eingegebener Befehl konnte nicht eingelesen werden.", e);
			return befehl; // sollte aus beliebigen Gr�nden der eingegebene Befehl nicht eingelesen werden
							// k�nnen, so wird ein leerer String (so wie String befehl inizialisiert wurde)
							// zur�ckgegeben.
		}
	}

	/**
	 * Funktion um eine Zahl oder mehrere Zahlen zu erfragen. Die Zahlen k�nnen auch
	 * als Reader �bergeben werden. Wandelt den eingegebenen String in ein Integer[]
	 * um und gibt dieses zur�ck. Enth�lt der String Zeichen, welche keine Zahlen
	 * sind, so werden diese Ignoriert. zum abschluss wird nullEntfernen ausgef�hrt
	 * und das ergebniss als integer[] zur�ckgegeben.
	 * 
	 * @return Integer[]
	 */
	public Integer[] erfrageLottoZahlen(Reader reader) {
		String input = "";
		in = new BufferedReader(reader); // ein neuer BufferedReader wird erzeugt mit dem �bergebenen reader.
											// Meist InputStreamReader(System.in), f�r Testzwecke kann auch ein
											// beliebiger String mittels StringReader �bergeben werden.
		try {
			input = in.readLine(); // Die eingegebenen Zahlen werden eingelesen.
		} catch (IOException e1) {
			logger.log(Level.WARNING, "Zahlen konnte nicht eingelesen werden!", e1);
			e1.printStackTrace(); // sollte aus beliebigen Gr�nden die eingegebenen Zahlen nicht eingelesen werden
									// k�nnen, so wird eine IOException geworfen und die Ausf�hrung abgebrochen..
		}
		String[] parts = input.split(" "); // Die eingegebenen Zahlen werden an den Leerzeichen getrennt und in ein
											// String Array geschrieben.
		Integer[] zahlen = new Integer[parts.length]; // Ein Integer Array wird erzeugt, mit gr��e = Anzahl an Eintr�gen
														// im parts Array.
		String currentString = "";
		for (int i = 0; i < parts.length; i++) {
			currentString = parts[i];
			try {
				zahlen[i] = Integer.parseInt(parts[i]); // Jeder String im parts Array wird gecasted und ins zahlen
														// Array geschrieben.
			} catch (NumberFormatException e) {
				StringSammlung.istKeineZahl(currentString); // Ist eine der �bergebenen Zahlen keine Zahl (kann also
															// nicht zu
				// Integer gecasted werden) so erfolgt eine Ausgabe auf der
				// Console...
				logger.log(Level.WARNING, "Input (" + currentString + ") ist keine Zahl!", e);
				continue; // ... und es wird mit dem n�chstem Eintrag fortgefahren. Somit werden alle
							// Eingaben, welche keine Zahlen sind ignoriert.
			}
		}
		quit(); // Der Buffered Reader wird beendet.
		return nullEntfernen(zahlen); // Zur�ckgegeben wird das Array, nachdem alle nulls entfernt worden sind, welche
										// durch ung�ltige Eingaben im Array gelandet sein k�nnten.
	}

	/**
	 * Funktion entfernt aus einem �bergebenen Integer[] alle null Werte und gibt
	 * das Ergebnis als Integer[] zur�ck.
	 * 
	 * @param Integer[]
	 * @return Integer[]
	 */
	public static Integer[] nullEntfernen(Integer[] a) {
		ArrayList<Integer> nullEntfernt = new ArrayList<Integer>(); // Eine neue Array List wird erzeugt um die Eintr�ge
																	// im Integer Array welche nicht null sind zwischen
																	// zu speichern.
		for (Integer integer : a)
			if (integer != null)
				nullEntfernt.add(integer); // F�r jeden Integer im �bergebenen Array wird geschaut ob es sich hierbei um
											// null handelt. Falls der Eintrag nicht null ist wird der Eintrag dem null
											// entfernt Array hinzugef�gt.
		return nullEntfernt.toArray(new Integer[0]); // Um ein Array zur�ckzugeben wird die ArrayList in ein Integer
														// Array �bertragen.
	}

	/**
	 * Schli�t den BufferedReader.
	 */
	public void quit() {
		try {
			in.close(); // Es wird versucht den BufferedReader zu schlie�en.
			logger.log(Level.INFO, "BufferedReader in Benutzereingabe geschlossen.");
		} catch (IOException e) {
			logger.log(Level.WARNING, "BufferedReader konnte in Benutzereingabe nicht geschlossen werden!", e);
			e.printStackTrace(); // sollte dies Fehlschlagen, so wird die Ausf�hrung dieser Methode abgebrochen
									// und eine IOException geworfen.
		}
	}
}
