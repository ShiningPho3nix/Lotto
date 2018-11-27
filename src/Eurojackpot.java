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
	 * Der Konstruktor f�hrt den super() Konstruktor aus, inizialisiert das extra
	 * Array zweiAusZehnTippzahlenArray und f�llt dieses anschlie�end mit den
	 * erforderlichen und erlaubten Zahlen.
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
	 * Anschlie�end werden 2 zahlen aus 10 gezogen und auch aufsteigend sortiert.
	 * Danach werden die beiden Arrays ausgegeben als Eurojackpot Tipp
	 */
	@Override
	public void generiereTipp() {
		Collections.shuffle(tippzahlenArray); // Mischt die Liste tippzahlenArray...
		Collections.shuffle(zweiAusZehntippzahlenArray); // ... und zweiAusZehntippzahlenArray.
		ArrayList<Integer> tipp = new ArrayList<Integer>(); // ArrayList wird deklariert und inizialisiert um den Tipp
															// zwischen zu speichern.
		for (int i = 0; i < 5; i++) {
			tipp.add(tippzahlenArray.get(i)); // Die ersten 6 Zahlen aus dem tippzahlenArray werden tipp
												// hinzugef�gt.
			logger.log(Level.INFO, tippzahlenArray.get(i).toString()
					+ " wurde als 5aus50 Tippzahl ausgew�hlt und dem dem Tipp hinzugef�gt.");
		}
		ArrayList<Integer> zweiAusZehnTipp = new ArrayList<Integer>(); // F�r die 2aus10 Ziehung wird ebenfalls eine
																		// ArrayList deklariert und inizialisiert.
		for (int i = 0; i < 2; i++) {
			zweiAusZehnTipp.add(zweiAusZehntippzahlenArray.get(i)); // Die ersten 2 Zahlen werden als 2aus10 tipp
																	// gezogen
			logger.log(Level.INFO, tippzahlenArray.get(i).toString()
					+ " wurde als 2aus10 Tippzahl ausgew�hlt und dem dem Tipp hinzugef�gt.");
		}
		Collections.sort(tipp, Collections.reverseOrder().reversed()); // Sortiert den tipp in aufsteigender Reihenfolge
		Collections.sort(zweiAusZehnTipp, Collections.reverseOrder().reversed()); // Die 2aus10 tipp ArrayList wird
																					// ebenfalls aufsteigend sortiert.
		System.out.println("5aus50:");
		System.out.println(tipp);
		System.out.println("2aus10");
		System.out.println(zweiAusZehnTipp);
		System.out.println("");
		logger.log(Level.INFO, "Tipp wurde dem Nutzer auf der Konsole ausgegeben.");
	}

	/**
	 * Methode zum entfernen von Zahlen aus der Menge an Zahlen f�r die
	 * Tippgenerierung. L�schen einer Zahl ist nur m�glich wenn noch nicht 6 Zahlen
	 * entfernt wurden und die Zahl selbst noch nicht entfernt worden ist.
	 * Anschli�end wird entferneAusTippzahlen ausgef�hrt.
	 */
	public void entferneZahlen(Integer[] zahlen) {
		for (int zahl : zahlen) {
			if (unglueckszahlenArray.size() < 6 && !unglueckszahlenArray.contains(zahl) && 0 < zahl && zahl < 51) {
				// Pr�ft ob noch nicht 6 ungl�ckszahlen gew�hlt sind, ob die aktuelle Zahl noch
				// keine unl�ckszahl ist und ob die Zahl im g�ltigen Bereich liegt.
				unglueckszahlenArray.add(zahl); // F�gt die Zahl der ungl�ckszahlen ArrayList hinzu.
				logger.log(Level.INFO, zahl + " wurde aus der Menge an m�glichen Zahlen f�r die " + modus()
						+ " Tippgenerierung entfernt.");
				entferneAusTippzahlen(unglueckszahlenArray);
				logger.log(Level.INFO, "ungl�ckszahlenArray gespeichert.");
			} else {
				ausgabe.nichtLoeschbar(zahl); // Sind die Bedingungen an eine der Zahlen nicht erf�llt, so wird auf der
												// Konsole ausgegeben das diese Zahl nicht gel�scht werden kann. Es wird
												// mit der n�chsten Zahl weiter gemacht.
				logger.log(Level.INFO, zahl + " wurde nicht aus der Menge an m�glichen Zahlen f�r die " + modus()
						+ " Tippgenerierung entfernt.");
			}
		}
		speichern();
		logger.log(Level.INFO, "entferneZahlen() durchgelaufen");
	}

	/**
	 * Methode um die ungl�cksZahlen aus den tippzahlenArrays zu entfernen.
	 */
	@Override
	public void entferneAusTippzahlen(ArrayList<Integer> unglueckszahlenArray) {
		for (Integer zahl : unglueckszahlenArray) {
			if (zweiAusZehntippzahlenArray.contains(zahl)) {
				zweiAusZehntippzahlenArray.remove((Integer) zahl); // entfernt die Aktuelle Zahl aus der 2aus10
																	// ArrayList.
			}
			if (tippzahlenArray.contains(zahl)) {
				tippzahlenArray.remove((Integer) zahl); // entfernt die Aktuelle Zahl aus der 5aus50 ArrayList
			} else {
				ausgabe.nichtLoeschbar(zahl); // Ist eine Zahl in keiner der Listen Vorhanden, so wird dem Nutzer
												// mitgeteilt das ein L�schen nicht m�glich ist
				logger.log(Level.INFO, zahl + " wurde nicht aus der Menge an m�glichen Zahlen f�r die " + modus()
						+ " Tippgenerierung entfernt.");
				continue; // Ist die Zahl nicht l�schbar, so wird mit der n�chsten Zahl weiter gemacht.
			}
			ausgabe.erfolgreichEntfernt(zahl, modus());
		}
	}

	/**
	 * Hat der Nutzer den Wunsch mehrere Tipps zu generieren, so wird diese Methode
	 * verwendet, welche generiereTipp() die gew�nschte anzahl an mal ausf�hrt und
	 * somit die gew�nschte anzahl an Tipps generiert werden.
	 */
	@Override
	public void generiereTipps(int quicktipp) {
		for (int i = 1; i <= quicktipp; i++) {
			System.out.println("Tipp#" + i); // F�r die �bergeben Anzahl an mal wird Tipp mit fortlaufender Zahl
												// ausgegeben...
			generiereTipp(); // ...und anschlie�end ein Tipp generiert.
		}
	}

	/**
	 * Funktion zur Erstellung einer Liste mit zul�ssigen Zahlen f�r die Tipp
	 * Generierung. Die ausgeschlossenen Zahlen werden hierbei nicht mit
	 * aufgenommen. Die Zul�ssigen Zahlen werden dem tippzahlenArray hinzugef�gt.
	 */
	@Override
	public void erstelleCollection() {
		for (int i = 1; i < 51; i++) { // F�r die Zahlen 1-50
			if (unglueckszahlenArray.contains((Integer) i)) {
				logger.log(Level.INFO, Integer.toString(i)
						+ " als Zahl aus der Menge an m�glichen Zahlen zur Tippgenerierung ausgeschlossen.");
				continue; // Wenn die aktuelle Zahl unter den ausgeschlossenen Zahlen ist, wird mit der
							// n�chsten Zahl weiter gemacht.
			} else {
				tippzahlenArray.add(i); // Ist die Zahl nicht im ungl�cksArray so wird die Zahl dem TIppArray
										// hinzugef�gt.
			}
		}
		logger.log(Level.INFO, "tippzahlenArray gef�llt.");
		for (int i = 1; i < 11; i++) { // F�r die Zahlen 1-10
			if (unglueckszahlenArray.contains((Integer) i)) {
				logger.log(Level.INFO, Integer.toString(i)
						+ " als Zahl aus der Menge an m�glichen Zahlen zur Tippgenerierung ausgeschlossen.");
				continue; // Wenn die aktuelle Zahl unter den ausgeschlossenen Zahlen ist, wird mit der
							// n�chsten Zahl weiter gemacht.
			} else {
				zweiAusZehntippzahlenArray.add(i); // Ist die Zahl nicht im ungl�cksArray so wird die Zahl dem
													// zweiAusZehntippzahlenArray hinzugef�gt.
			}
		}
		logger.log(Level.INFO, "zweiAusZehntippzahlenArray gef�llt.");
		logger.log(Level.INFO, "erstelleCollection durchgelaufen.");
	}
}
