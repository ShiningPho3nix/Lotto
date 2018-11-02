import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;

/**
 * Klasse Implementiert das Interface Lotto. Klasse eröglicht das erzeugen von
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
	 * Der Konstruktor inizialisiert die beiden Arrays für die Menge an Zahlen zur
	 * Tippgenerierung, sowie für die entfernten Zahlen. Anschließend wird die
	 * Funktion laden() ausgeführt.
	 */
	public SechsAusNeunundvierzig() {
		super("6aus49");
		erstelleCollection();
		logger.log(Level.INFO, "SechsAusNeunundvierzig Konstruktor durchgelaufen.");
	}

	/**
	 * Funktion verwendet die liste sechsAusNeunundvierzig, shuffelt diese und nimmt
	 * dann die ersten 6 Zahlen. Die Zahlen werden aufsteigend sortiert und als Tipp
	 * auf der Konsole ausgegeben.
	 */
	@Override
	public void generiereTipp() {
		int shuffle = (int) (Math.random() * 200);
		System.out.println(shuffle);
		for (int i = 0; i < shuffle; i++)
			Collections.shuffle(tippzahlenArray);
		ArrayList<Integer> tipp = new ArrayList<Integer>();
		for (int i = 0; i < 6; i++) {
			tipp.add(tippzahlenArray.get(i));
			logger.log(Level.INFO,
					tippzahlenArray.get(i).toString() + " wurde als Tippzahl ausgewählt und dem dem Tipp hinzugefügt.");
		}
		Collections.sort(tipp, Collections.reverseOrder().reversed());
		tipp.forEach(System.out::println);
		logger.log(Level.INFO, "Tipp wurde dem Nutzer auf der Konsole ausgegeben.");
	}

	/**
	 * Funktion zur Erstellung einer Liste mit zulässigen Zahlen für die Tipp
	 * Generierung. Die ausgeschlossenen Zahlen werden hierbei nicht mit
	 * aufgenommen. Die Zulässigen Zahlen werden dem sechsAusNeunundvierzig Array
	 * hinzugefügt.
	 */
	@Override
	public void erstelleCollection() {

		for (int i = 1; i < 50; i++) {
			if (unglueckszahlenArray.contains((Integer) i)) {
				logger.log(Level.INFO, Integer.toString(i)
						+ " als Zahl aus der Menge an möglichen Zahlen zur Tippgenerierung ausgeschlossen.");
				continue;
			}
			tippzahlenArray.add(i);
		}
		logger.log(Level.INFO, "tippzahlenArray gefüllt, erstelleCollection durchgelaufen.");
	}

	/**
	 * Methode zum entfernen von Zahlen aus der Menge an Zahlen für die
	 * Tippgenerierung. Löschen einer Zahl ist nur möglich wenn noch nicht 6 Zhalen
	 * entfernt wurden und die Zahl selbst noch nicht entfernt worden ist.
	 */
	@Override
	public void entferneZahlen(int zahl) {
		if (tippzahlenArray.size() > 43 && tippzahlenArray.contains(zahl)) {
			tippzahlenArray.remove((Integer) zahl);
			unglueckszahlenArray.add(zahl);
			logger.log(Level.INFO, zahl + " wurde aus der Menge an möglichen Zahlen für " + modus() + " entfernt.");
			ausgabe.erfolgreichEntfernt(zahl);
			tippzahlenArray.forEach(System.out::println);
			speichern();
			logger.log(Level.INFO, "unglückszahlenArray gespeichert.");

		} else {
			ausgabe.nichtLoeschbar(zahl);
			logger.log(Level.INFO,
					zahl + " wurde nicht aus der Menge an möglichen Zahlen für " + modus() + " entfernt.");
		}
		logger.log(Level.INFO, "entferneZahlen() durchgelaufen");
	}

	/**
	 * Funktion um eine Zahl welche ausgeschlossen wurde wieder zuzulassen. Dafür
	 * muss die Zahl eine Zahl sein, in dem Array: unglueckszahlenArray vorhanden
	 * und in dem Array: sechsAusNeunundvierzig nicht vorhanden sein.
	 */
	public void entferneUnglueckszahl(int zahl) {
		if (unglueckszahlenArray.contains(zahl) && !tippzahlenArray.contains(zahl)) {
			unglueckszahlenArray.remove((Integer) zahl);
			tippzahlenArray.add(zahl);
			logger.log(Level.INFO,
					zahl + " wurde der Menge an möglichen Zahlen für " + modus() + " wieder hinzugefügt.");
			ausgabe.erfolgreichWiederHinzugefuegt(zahl);
			speichern();
		} else {
			ausgabe.hinzufuegenNichtMoeglich(zahl);
			logger.log(Level.INFO,
					zahl + " wurde nicht der Menge an möglichen Zahlen für " + modus() + " wieder hinzugefügt.");
		}
		logger.log(Level.INFO, "entferneUnglueckszahl() durchgelaufen");
	}

	public void reset() {
		for (Integer integer : unglueckszahlenArray) {
			entferneUnglueckszahl(integer);
		}
		logger.log(Level.INFO, modus() + " wurde resetet");
	}
}
