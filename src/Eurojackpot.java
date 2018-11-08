import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;

/**
 * 
 */

/**
 * @author Steffen Dworsky
 *
 */
public class Eurojackpot extends SLotto implements ILotto {

	public ArrayList<Integer> zweiAusZehntippzahlenArray;

	/**
	 * Der Konstruktor führt den super() Konstruktor aus, inizialisiert das extra
	 * Array zweiAusZehnTippzahlenArray und füllt dieses anschließend mit den
	 * erforderlichen und erlaubten Zahlen..
	 * 
	 */
	public Eurojackpot() {
		super("Eurojackpot");
		zweiAusZehntippzahlenArray = new ArrayList<Integer>();
		erstelleCollection();
	}

	/**
	 * Funktion verwendet die liste sechsAusNeunundvierzig, shuffelt diese und nimmt
	 * dann die ersten 5 Zahlen. Die Zahlen werden aufsteigend sortiert.
	 * Anschließend werden 2 zahlen aus 10 gezogen und auch aufsteigend sortiert.
	 * Danach werden die beiden Arrays ausgegeben als Eurojackpot Tipp
	 */
	@Override
	public void generiereTipp() {
		int shuffle = (int) (Math.random() * 200);
		for (int i = 0; i < shuffle; i++)
			Collections.shuffle(tippzahlenArray);
		Collections.shuffle(zweiAusZehntippzahlenArray);
		ArrayList<Integer> tipp = new ArrayList<Integer>();
		for (int i = 0; i < 5; i++) {
			tipp.add(tippzahlenArray.get(i));
			logger.log(Level.INFO, tippzahlenArray.get(i).toString()
					+ " wurde als 5aus50 Tippzahl ausgewählt und dem dem Tipp hinzugefügt.");
		}
		ArrayList<Integer> zweiAusZehnTipp = new ArrayList<Integer>();
		for (int i = 0; i < 2; i++) {
			zweiAusZehnTipp.add(zweiAusZehntippzahlenArray.get(i));
			logger.log(Level.INFO, tippzahlenArray.get(i).toString()
					+ " wurde als 2aus10 Tippzahl ausgewählt und dem dem Tipp hinzugefügt.");
		}
		Collections.sort(tipp, Collections.reverseOrder().reversed());
		Collections.sort(zweiAusZehnTipp, Collections.reverseOrder().reversed());
		System.out.println("5aus50:");
		System.out.println(tipp);
		System.out.println("2aus10");
		System.out.println(zweiAusZehnTipp);
		System.out.println("");
		logger.log(Level.INFO, "Tipp wurde dem Nutzer auf der Konsole ausgegeben.");
	}

	/**
	 * Methode zum entfernen von Zahlen aus der Menge an Zahlen für die
	 * Tippgenerierung. Löschen einer Zahl ist nur möglich wenn noch nicht 6 Zahlen
	 * entfernt wurden und die Zahl selbst noch nicht entfernt worden ist.
	 * Anschlißend wird entferneAusTippzahlen ausgeführt.
	 */
	public void entferneZahlen(Integer[] zahlen) {
		for (int zahl : zahlen) {
			if (unglueckszahlenArray.size() < 6 && !unglueckszahlenArray.contains(zahl) && 0 < zahl && zahl < 51) {
				tippzahlenArray.remove((Integer) zahl);
				unglueckszahlenArray.add(zahl);
				logger.log(Level.INFO, zahl + " wurde aus der Menge an möglichen Zahlen für die " + modus()
						+ " Tippgenerierung entfernt.");
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
		for (Integer zahl : unglueckszahlenArray) {
			if (zweiAusZehntippzahlenArray.contains(zahl)) {
				zweiAusZehntippzahlenArray.remove((Integer) zahl);
			}
			if (tippzahlenArray.contains(zahl)) {
				tippzahlenArray.remove((Integer) zahl);
			}
		}
	}

	/**
	 * Hat der Nutzer den Wunsch mehrere Tipps zu generieren, so wird diese Methode
	 * verwendet, welche generiereTipp() die gewünschte anzahl an mal ausführt und
	 * somit die gewünschte anzahl an Tipps generiert werden.
	 */
	@Override
	public void generiereTipps(int quicktipp) {
		for (int i = 1; i <= quicktipp; i++) {
			System.out.println("Tipp#" + i);
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
		for (int i = 1; i < 51; i++) {
			if (unglueckszahlenArray.contains((Integer) i)) {
				logger.log(Level.INFO, Integer.toString(i)
						+ " als Zahl aus der Menge an möglichen Zahlen zur Tippgenerierung ausgeschlossen.");
			} else {
				tippzahlenArray.add(i);
			}
		}
		logger.log(Level.INFO, "tippzahlenArray gefüllt.");
		for (int i = 1; i < 11; i++) {
			if (unglueckszahlenArray.contains((Integer) i)) {
				logger.log(Level.INFO, Integer.toString(i)
						+ " als Zahl aus der Menge an möglichen Zahlen zur Tippgenerierung ausgeschlossen.");
				continue;
			} else {
				zweiAusZehntippzahlenArray.add(i);
			}
		}
		logger.log(Level.INFO, "zweiAusZehntippzahlenArray gefüllt.");
		logger.log(Level.INFO, "erstelleCollection durchgelaufen.");
	}
}
