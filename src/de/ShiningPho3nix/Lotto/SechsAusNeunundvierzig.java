package de.ShiningPho3nix.Lotto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;

/**
 * Klasse Implementiert das Interface Lotto. Klasse er�glicht das Erzeugen von
 * Tipps f�r die Lottospielart 6aus49. Erm�glicht es ebenso bis zu 6 Zahlen aus
 * der Zahlenmenge f�r die Tippgenerierung zu entfernen. Mithilfe der Funktionen
 * laden und speichern, kann eine Liste mit entfernten Zahlen gespeichert und
 * geladen werden.
 * 
 * @author Steffen Dworsky
 *
 */
public class SechsAusNeunundvierzig extends SLotto implements ILotto {

	/**
	 * Der Konstruktor f�hrt den super() Konstruktor aus und f�llt das tippArray mit
	 * den erforderlichen und erlaubten Zahlen.
	 * 
	 */
	public SechsAusNeunundvierzig() {
		super("6aus49");
		logger.log(Level.INFO, "SechsAusNeunundvierzig Konstruktor durchgelaufen.");
	}

	/**
	 * Funktion verwendet die liste tippzahlenArray, shuffelt dieses und nimmt dann
	 * die ersten 6 Zahlen. Die Zahlen werden aufsteigend sortiert und als Tipp auf
	 * der Konsole ausgegeben. generiere Tipp verwendet nur das TippzahlenArray
	 * 
	 * @return Ein formatierter String, welcher den generierten Tipp enth�lt.
	 */
	@Override
	public String generiereTipp() {
		Collections.shuffle(tippzahlenArray); // Mischt das sortierte ZahlenArray durch, ...
		ArrayList<Integer> tipp = new ArrayList<Integer>();
		for (int i = 0; i < 6; i++) { // ... nimmt dann die ersten 6 Werte und ...
			tipp.add(tippzahlenArray.get(i)); // ... f�gt diese dem tipp hinzu.
		}
		Collections.sort(tipp, Collections.reverseOrder().reversed()); // Sortiert die Tipp Liste in aufsteigender
																		// Reihenfolge.
		logger.log(Level.INFO, tipp + " wurde generiert.");
		return (StringSammlung.sechsAusNeunundvierzigTipp(tipp)); // Gibt den Tipp auf der Konsole aus.
	}

	/**
	 * Funktion zur Erstellung einer Liste mit zul�ssigen Zahlen f�r die Tipp
	 * Generierung. Die ausgeschlossenen Zahlen werden hierbei nicht mit
	 * aufgenommen. Die Zul�ssigen Zahlen werden dem tippzahlenArray hinzugef�gt.
	 */
	@Override
	public void erstelleCollection() {
		for (int i = 1; i < 50; i++) {
			if (unglueckszahlenArray.contains((Integer) i)) { // Wenn eine Zahl auch im ungl�ckszahlenarray vorhanden
																// ist, so wird diese nicht dem tippzahlenarray
																// hinzugef�gt.
				logger.log(Level.INFO, Integer.toString(i)
						+ " als Zahl aus der Menge an m�glichen Zahlen zur Tippgenerierung ausgeschlossen.");
			} else {
				tippzahlenArray.add(i);
			}
		}
		for (int i = 1; i <= 10; i++) { // F�r die Zahlen 1-10
			if (unglueckszahlenArray.contains(i)) {
				logger.log(Level.INFO, Integer.toString(i)
						+ " als Zahl aus der Menge an m�glichen Zahlen zur Tippgenerierung ausgeschlossen.");
				continue; // Wenn die aktuelle Zahl unter den ausgeschlossenen Zahlen ist, wird mit der
							// n�chsten Zahl weiter gemacht.
			} else {
				zweiAusZehnTippzahlenArray.add(i); // Ist die Zahl nicht im ungl�cksArray so wird die Zahl dem
													// zweiAusZehntippzahlenArray hinzugef�gt.
			}
		}
		logger.log(Level.INFO, "zweiAusZehntippzahlenArray gef�llt.");
		logger.log(Level.INFO, "tippzahlenArray gef�llt");
		logger.log(Level.INFO, "erstelleCollection durchgelaufen.");
	}
}
