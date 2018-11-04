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
	 * Der Konstruktor führt den super() Konstruktor aus und füllt das tippArray mit
	 * den erforderlichen und erlaubten Zahlen.
	 * 
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
		for (int i = 0; i < shuffle; i++)
			Collections.shuffle(tippzahlenArray);
		ArrayList<Integer> tipp = new ArrayList<Integer>();
		for (int i = 0; i < 6; i++) {
			tipp.add(tippzahlenArray.get(i));
			logger.log(Level.INFO,
					tippzahlenArray.get(i).toString() + " wurde als Tippzahl ausgewählt und dem dem Tipp hinzugefügt.");
		}
		Collections.sort(tipp, Collections.reverseOrder().reversed());
		System.out.println(tipp);
		System.out.println("");
		logger.log(Level.INFO, "Tipp wurde dem Nutzer auf der Konsole ausgegeben.");
	}

	/**
	 * TODO Fertig schreiben Hat der Nutzer den Wunsch mehrere Tipps zu generieren,
	 * so wird diese Methode verwendet, welche generiereTipp() die gewünschte anzahl
	 * an malen ausführt und somit die gewünschte anzahl an Tipps generiert werden.
	 */
	public void generiereTipps(int quicktipp) {
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
	 * Gibt den Aktuellen Spielmodus als String zurück.
	 */
	public String modus() {
		return "6aus49";
	}
}
