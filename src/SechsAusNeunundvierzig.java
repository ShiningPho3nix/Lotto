import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;

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

	/**
	 * Der Konstruktor führt den super() Konstruktor aus und füllt das tippArray mit
	 * den erforderlichen und erlaubten Zahlen.
	 * 
	 */
	public SechsAusNeunundvierzig() {
		super("6aus49");
		erstelleCollection(); // Durch den Super-Konstruktor wird das unglückszahlenarray gefüllt. Durch
								// erstelle Collectiuon wird das Zahlen-Array ohne die unglückszahlen befüllt.
		logger.log(Level.INFO, "SechsAusNeunundvierzig Konstruktor durchgelaufen.");
	}

	/**
	 * Funktion verwendet die liste tippzahlenArray, shuffelt dieses und nimmt dann
	 * die ersten 6 Zahlen. Die Zahlen werden aufsteigend sortiert und als Tipp auf
	 * der Konsole ausgegeben.
	 */
	@Override
	public void generiereTipp() {
		Collections.shuffle(tippzahlenArray); // Mischt das sortierte ZahlenArray durch, ...
		ArrayList<Integer> tipp = new ArrayList<Integer>();
		for (int i = 0; i < 6; i++) { // ... nimmt dann die ersten 6 Werte und ...
			tipp.add(tippzahlenArray.get(i)); // ... fügt diese dem tipp hinzu.
			logger.log(Level.INFO,
					tippzahlenArray.get(i).toString() + " wurde als Tippzahl ausgewählt und dem dem Tipp hinzugefügt.");
		}
		Collections.sort(tipp, Collections.reverseOrder().reversed()); // Sortiert die Tipp Liste in aufsteigender
																		// Reihenfolge.
		ausgabe.sechsAusNeunundvierzigTipp(tipp); // Gibt den Tipp auf der Konsole aus.
		logger.log(Level.INFO, "Tipp wurde dem Nutzer auf der Konsole ausgegeben.");
	}

	/**
	 * Methode zum entfernen von Zahlen aus der Menge an Zahlen für die
	 * Tippgenerierung. Löschen einer Zahl ist nur möglich wenn noch nicht 6 Zahlen
	 * entfernt wurden, die Zahl selbst noch nicht entfernt worden ist und im
	 * bereich 1 und 50 liegt.
	 */
	public void entferneZahlen(Integer[] zahlen) {
		for (int zahl : zahlen) {
			if (unglueckszahlenArray.size() < 6 && !unglueckszahlenArray.contains(zahl) && 0 < zahl && zahl < 51) {
				// Eine unglückszahl darf nur dann gewählt werden wenn sie im gültigen Bereich
				// ist und es noch nicht 6 Unglückszahlen gibt.
				unglueckszahlenArray.add(zahl);
				logger.log(Level.INFO, zahl + " wurde aus der Menge an möglichen Zahlen für die " + modus()
						+ "  Tippgenerierung entfernt.");
				ausgabe.erfolgreichEntfernt(zahl, modus());
				entferneAusTippzahlen(unglueckszahlenArray);
				logger.log(Level.INFO, "unglückszahlenArray gespeichert.");
			} else {
				ausgabe.nichtLoeschbar(zahl);
				logger.log(Level.INFO, zahl + " wurde nicht aus der Menge an möglichen Zahlen für die " + modus()
						+ " Tippgenerierung entfernt.");
			}
		}
		speichern();
		logger.log(Level.INFO, "entferneZahlen() durchgelaufen");
	}

	/**
	 * Methode um die unglücksZahlen aus den tippzahlenArrays zu entfernen.
	 */
	@Override
	public void entferneAusTippzahlen(ArrayList<Integer> unglueckszahlenArray) {
		for (int zahl : unglueckszahlenArray) {
			if (tippzahlenArray.contains(zahl)) { // Ist eine (unglücks-) zahl im tippzahlenarray, so wird diese
													// entfernt, wenn sie es nicht ist, wurde diese bereits entfernt.
				tippzahlenArray.remove((Integer) zahl);
				logger.log(Level.INFO, zahl + " aus tippzahlenArray von " + modus() + " entfernt");
			}
		}
	}

	/**
	 * Hat der Nutzer den Wunsch mehrere Tipps zu generieren, so wird diese Methode
	 * verwendet, welche generiereTipp() die gewünschte anzahl an mal ausführt und
	 * somit die gewünschte anzahl an Tipps generiert werden.
	 */
	public void generiereTipps(int quicktipp) {
		if (quicktipp < 1) {
			quicktipp = 1;
		}
		for (int i = 1; i <= quicktipp; i++) {
			System.out.println("Tipp#" + i + ":");
			generiereTipp();
		}
	}

	/**
	 * Funktion zur Erstellung einer Liste mit zulässigen Zahlen für die Tipp
	 * Generierung. Die ausgeschlossenen Zahlen werden hierbei nicht mit
	 * aufgenommen. Die Zulässigen Zahlen werden dem tippzahlenArray hinzugefügt.
	 */
	@Override
	public void erstelleCollection() {
		for (int i = 1; i < 50; i++) {
			if (unglueckszahlenArray.contains((Integer) i)) { // Wenn eine Zahl auch im unglückszahlenarray vorhanden
																// ist, so wird diese nicht dem tippzahlenarray
																// hinzugefügt.
				logger.log(Level.INFO, Integer.toString(i)
						+ " als Zahl aus der Menge an möglichen Zahlen zur Tippgenerierung ausgeschlossen.");
			} else {
				tippzahlenArray.add(i);
			}
		}
		logger.log(Level.INFO, "tippzahlenArray gefüllt, erstelleCollection durchgelaufen.");
	}

	@Override
	public String generiereTippTest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generiereTippsTest(int anzahlTipps) {
		// TODO Auto-generated method stub
		return null;
	}
}
