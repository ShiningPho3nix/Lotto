package de.ShiningPho3nix.Lotto;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Klasse Implementiert das Interface Lotto. Klasse eröglicht das Erzeugen von
 * Tipps für die Lottospielart 6aus49. Ermöglicht es ebenso bis zu 6 Zahlen aus
 * der Zahlenmenge für die Tippgenerierung zu entfernen. Mithilfe der Funktionen
 * laden und speichern, kann eine Liste mit entfernten Zahlen gespeichert und
 * geladen werden.
 * 
 * @author Steffen Dworsky
 *
 */
public class SechsAusNeunundvierzig extends SLotto implements ILotto {

	private final static Logger logger = LogManager.getLogger(SechsAusNeunundvierzig.class);

	/**
	 * Der Konstruktor führt den super() Konstruktor aus und füllt das tippArray mit
	 * den erforderlichen und erlaubten Zahlen.
	 * 
	 */
	public SechsAusNeunundvierzig() {
		super();
		logger.info("SechsAusNeunundvierzig Konstruktor durchgelaufen.");
	}

	/**
	 * Funktion verwendet die liste tippzahlenArray, shuffelt dieses und nimmt dann
	 * die ersten 6 Zahlen. Die Zahlen werden aufsteigend sortiert und als Tipp auf
	 * der Konsole ausgegeben. generiere Tipp verwendet nur das TippzahlenArray
	 * 
	 * @return Ein formatierter String, welcher den generierten Tipp enthält.
	 */
	@Override
	public String generiereTipp() {
		Collections.shuffle(tippzahlenArray); // Mischt das sortierte ZahlenArray durch, ...
		ArrayList<Integer> tipp = new ArrayList<Integer>();
		for (int i = 0; i < 6; i++) { // ... nimmt dann die ersten 6 Werte und ...
			tipp.add(tippzahlenArray.get(i)); // ... fügt diese dem tipp hinzu.
		}
		Collections.sort(tipp, Collections.reverseOrder().reversed()); // Sortiert die Tipp Liste in aufsteigender
																		// Reihenfolge.
		return (StringSammlung.sechsAusNeunundvierzigTipp(tipp)); // Gibt den Tipp auf der Konsole aus.
	}

	/**
	 * Funktion zur Erstellung einer Liste mit zulässigen Zahlen für die Tipp
	 * Generierung. Die ausgeschlossenen Zahlen werden hierbei nicht mit
	 * aufgenommen. Die Zulässigen Zahlen werden dem tippzahlenArray hinzugefügt.
	 * 
	 */
	@Override
	public void erstelleCollection() {
		for (int i = 1; i < 50; i++) { // Für die Zahlen 1-49.
			if (unglueckszahlenArray.contains((Integer) i)) { // Wenn eine Zahl auch im unglückszahlenarray vorhanden
																// ist, so wird diese nicht dem tippzahlenarray
																// hinzugefügt.
				logger.info(Integer.toString(i)
						+ " als Zahl aus der Menge an möglichen Zahlen zur Tippgenerierung ausgeschlossen.");
			} else {
				tippzahlenArray.add(i);
			}
		}
		logger.info("tippzahlenArray gefüllt");
		logger.info("erstelleCollection durchgelaufen.");
	}

	/**
	 * Gibt den Aktuellen Spielmodus als String zurück.
	 */
	@Override
	public String getModus() {
		logger.info("Modus 6aus49 als Modus zurückgegeben.");
		return "6aus49";
	}
}
