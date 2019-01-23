package de.ShiningPho3nix.Lotto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Klasse ist f�r die Verarbeitung aller Benutzereingaben verantwortlich.
 * Erf�llt dar�ber hinaus die Aufgabe des "Programmflows"
 * 
 * @author Steffen Dworsky
 *
 */
public class Benutzereingabe {

	private static BufferedReader in;
	private final static Logger logger = LogManager.getLogger(Benutzereingabe.class);

	public Benutzereingabe() {
		logger.info("Benutzereingabe Konstruktor durchgelaufen");
	}

	/**
	 * Funktion um eine Zahl oder mehrere Zahlen anzunehmen und zu verarbeiten. Die
	 * Zahlen k�nnen auch als Reader �bergeben werden. Wandelt den eingegebenen
	 * String in ein Integer[] um und gibt dieses zusammen mit einem String welcher
	 * die ereignisse festh�lt als Tupel zur�ck. Enth�lt der String Zeichen, welche
	 * keine Zahlen sind, so werden diese Ignoriert. Zum Abschluss wird
	 * nullEntfernen ausgef�hrt.
	 * 
	 * @return Tupel mit einem Array aus g�ltigen Zahlen und dem ereigniss String.
	 */
	public static Tuple verarbeiteZahlen(Reader reader) {
		String input = null;
		StringBuilder sb = new StringBuilder();
		in = new BufferedReader(reader); // ein neuer BufferedReader wird erzeugt mit dem �bergebenen reader.
											// Meist InputStreamReader(System.in), f�r Testzwecke kann auch ein
											// beliebiger String mittels StringReader �bergeben werden.
		try {
			input = in.readLine(); // Die eingegebenen Zahlen werden eingelesen.
		} catch (IOException e1) {
			logger.error("Zahlen konnte nicht eingelesen werden!", e1);
			e1.printStackTrace(); // sollte aus beliebigen Gr�nden die eingegebenen Zahlen nicht eingelesen werden
									// k�nnen, so wird eine IOException geworfen und die Ausf�hrung abgebrochen..
		}
		if (input == null) {
			return new Tuple(new Integer[0], "");
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
				sb.append(StringSammlung.istKeineZahl(currentString)); // Ist eine der �bergebenen Zahlen keine Zahl
																		// (kann also
				// nicht zu
				// Integer gecasted werden) so erfolgt eine Ausgabe auf der
				// Console...
				logger.warn("Input (" + currentString + ") ist keine Zahl!", e);
				continue; // ... und es wird mit dem n�chstem Eintrag fortgefahren. Somit werden alle
							// Eingaben, welche keine Zahlen sind ignoriert.
			}
		}
		try {// Der Buffered Reader wird beendet.
			in.close(); // Es wird versucht den BufferedReader zu schlie�en.
			logger.info("BufferedReader in Benutzereingabe geschlossen.");
		} catch (IOException e) {
			logger.error("BufferedReader konnte in Benutzereingabe nicht geschlossen werden!", e);
			e.printStackTrace();
		}
		return new Tuple(nullEntfernen(zahlen), sb.toString()); // Zur�ckgegeben wird das Array, nachdem alle nulls
																// entfernt worden sind, welche durch ung�ltige Eingaben
																// im Array gelandet sein k�nnten, sowie den
																// fehlerhaften eingaben als string in einem Tuple.
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
			logger.info(befehl + " als Befehl eingegeben!");
			return befehl = befehl.toUpperCase(); // ... und als toUpperCase returned
		} catch (IOException e) {
			logger.error("Eingegebener Befehl konnte nicht eingelesen werden.", e);
			return befehl; // sollte aus beliebigen Gr�nden der eingegebene Befehl nicht eingelesen werden
							// k�nnen, so wird ein leerer String (so wie String befehl inizialisiert wurde)
							// zur�ckgegeben.
		}
	}
}
