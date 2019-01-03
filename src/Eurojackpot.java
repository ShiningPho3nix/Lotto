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
	 * Anschließend werden 2 zahlen aus 10 gezogen und auch aufsteigend sortiert.
	 * Danach werden die beiden Arrays ausgegeben als Eurojackpot Tipp
	 * 
	 * @return
	 */
	@Override
	public void generiereTipp() {
		Collections.shuffle(tippzahlenArray); // Mischt die Liste tippzahlenArray...
		Collections.shuffle(zweiAusZehntippzahlenArray); // ... und zweiAusZehntippzahlenArray.
		ArrayList<Integer> tipp = new ArrayList<Integer>(); // ArrayList wird deklariert und inizialisiert um den Tipp
															// zwischen zu speichern.
		for (int i = 0; i < 5; i++) {
			tipp.add(tippzahlenArray.get(i)); // Die ersten 6 Zahlen aus dem tippzahlenArray werden tipp
												// hinzugefügt.
			logger.log(Level.INFO, tippzahlenArray.get(i).toString()
					+ " wurde als 5aus50 Tippzahl ausgewählt und dem dem Tipp hinzugefügt.");
		}
		ArrayList<Integer> zweiAusZehnTipp = new ArrayList<Integer>(); // Für die 2aus10 Ziehung wird ebenfalls eine
																		// ArrayList deklariert und inizialisiert.
		for (int i = 0; i < 2; i++) {
			zweiAusZehnTipp.add(zweiAusZehntippzahlenArray.get(i)); // Die ersten 2 Zahlen werden als 2aus10 tipp
																	// gezogen
			logger.log(Level.INFO, tippzahlenArray.get(i).toString()
					+ " wurde als 2aus10 Tippzahl ausgewählt und dem dem Tipp hinzugefügt.");
		}
		Collections.sort(tipp, Collections.reverseOrder().reversed()); // Sortiert den tipp in aufsteigender Reihenfolge
		Collections.sort(zweiAusZehnTipp, Collections.reverseOrder().reversed()); // Die 2aus10 tipp ArrayList wird

		System.out.println(StringSammlung.eurojackpotTipp(tipp, zweiAusZehnTipp)); // Gibt den Tipp auf der Konsole aus.
		logger.log(Level.INFO, "Tipp wurde dem Nutzer auf der Konsole ausgegeben.");
	}

	/**
	 * Test Methode um die Ausgabe/Rückgabe eines generierten Tipps zu
	 * verallgemeinern. Es wird ein formatierter String mit dem aktuellem Tipp
	 * zurückgegeben.
	 * 
	 * @return
	 */
	@Override
	public String generiereTippTest() {
		StringBuilder sb = new StringBuilder();
		Collections.shuffle(tippzahlenArray); // Mischt die Liste tippzahlenArray...
		Collections.shuffle(zweiAusZehntippzahlenArray); // ... und zweiAusZehntippzahlenArray.
		ArrayList<Integer> tipp = new ArrayList<Integer>(); // ArrayList wird deklariert und inizialisiert um den Tipp
															// zwischen zu speichern.
		for (int i = 0; i < 5; i++) {
			tipp.add(tippzahlenArray.get(i)); // Die ersten 6 Zahlen aus dem tippzahlenArray werden tipp
												// hinzugefügt.
			logger.log(Level.INFO, tippzahlenArray.get(i).toString()
					+ " wurde als 5aus50 Tippzahl ausgewählt und dem dem Tipp hinzugefügt.");
		}
		ArrayList<Integer> zweiAusZehnTipp = new ArrayList<Integer>(); // Für die 2aus10 Ziehung wird ebenfalls eine
																		// ArrayList deklariert und inizialisiert.
		for (int i = 0; i < 2; i++) {
			zweiAusZehnTipp.add(zweiAusZehntippzahlenArray.get(i)); // Die ersten 2 Zahlen werden als 2aus10 tipp
																	// gezogen
			logger.log(Level.INFO, tippzahlenArray.get(i).toString()
					+ " wurde als 2aus10 Tippzahl ausgewählt und dem dem Tipp hinzugefügt.");
		}
		Collections.sort(tipp, Collections.reverseOrder().reversed()); // Sortiert den tipp in aufsteigender Reihenfolge
		Collections.sort(zweiAusZehnTipp, Collections.reverseOrder().reversed()); // Die 2aus10 tipp ArrayList wird
																					// ebenfalls aufsteigend sortiert.
		sb.append("5aus50: \n");
		sb.append(tipp + "\n");
		sb.append("2aus10: \n");
		sb.append(zweiAusZehnTipp + "\n");
		sb.append("\n");
		System.out.println(StringSammlung.eurojackpotTipp(tipp, zweiAusZehnTipp)); // Gibt den Tipp auf der Konsole aus.
		logger.log(Level.INFO, "Tipp wurde dem Nutzer auf der Konsole ausgegeben.");
		return sb.toString();
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
				// Prüft ob noch nicht 6 unglückszahlen gewählt sind, ob die aktuelle Zahl noch
				// keine unlückszahl ist und ob die Zahl im gültigen Bereich liegt.
				unglueckszahlenArray.add(zahl); // Fügt die Zahl der unglückszahlen ArrayList hinzu.
				logger.log(Level.INFO, zahl + " wurde aus der Menge an möglichen Zahlen für die " + modus()
						+ " Tippgenerierung entfernt.");
				entferneAusTippzahlen(unglueckszahlenArray);
				logger.log(Level.INFO, "unglückszahlenArray gespeichert.");
			} else {
				System.out.println(StringSammlung.nichtLoeschbar(zahl)); // Sind die Bedingungen an eine der Zahlen nicht erfüllt, so wird
														// auf der
				// Konsole ausgegeben das diese Zahl nicht gelöscht werden kann. Es wird
				// mit der nächsten Zahl weiter gemacht.
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
				zweiAusZehntippzahlenArray.remove((Integer) zahl); // entfernt die Aktuelle Zahl aus der 2aus10
																	// ArrayList.
			}
			if (tippzahlenArray.contains(zahl)) {
				tippzahlenArray.remove((Integer) zahl); // entfernt die Aktuelle Zahl aus der 5aus50 ArrayList
			} else {
				System.out.println(StringSammlung.nichtLoeschbar(zahl)); // Ist eine Zahl in keiner der Listen Vorhanden, so wird dem Nutzer
				// mitgeteilt das ein Löschen nicht möglich ist
				logger.log(Level.INFO, zahl + " wurde nicht aus der Menge an möglichen Zahlen für die " + modus()
						+ " Tippgenerierung entfernt.");
				continue; // Ist die Zahl nicht löschbar, so wird mit der nächsten Zahl weiter gemacht.
			}
			System.out.println(StringSammlung.erfolgreichEntfernt(zahl));
		}
	}

	/**
	 * Hat der Nutzer den Wunsch mehrere Tipps zu generieren, so wird diese Methode
	 * verwendet, welche generiereTipp() die gewünschte anzahl an mal ausführt und
	 * somit die gewünschte anzahl an Tipps generiert werden.
	 */
	@Override
	public void generiereTipps(int quicktipp) {
		if (quicktipp < 1) {
			quicktipp = 1;
		}
		for (int i = 1; i <= quicktipp; i++) {
			System.out.println("Tipp#" + i); // Für die Übergeben Anzahl an mal wird Tipp mit fortlaufender Zahl
												// ausgegeben...
			generiereTipp(); // ...und anschließend ein Tipp generiert.
		}
	}

	@Override
	public String generiereTippsTest(int quicktipp) {
		StringBuilder sb = new StringBuilder();
		if (quicktipp < 1) {
			quicktipp = 1;
		}
		for (int i = 1; i <= quicktipp; i++) {
			sb.append("Tipp#" + i + " \n"); // Für die Übergeben Anzahl an mal wird Tipp mit fortlaufender Zahl
											// ausgegeben...
			sb.append(generiereTippTest()); // ...und anschließend ein Tipp generiert.
		}
		return sb.toString();
	}

	/**
	 * Funktion zur Erstellung einer Liste mit zulässigen Zahlen für die Tipp
	 * Generierung. Die ausgeschlossenen Zahlen werden hierbei nicht mit
	 * aufgenommen. Die Zulässigen Zahlen werden dem tippzahlenArray hinzugefügt.
	 */
	@Override
	public void erstelleCollection() {
		for (int i = 1; i < 51; i++) { // Für die Zahlen 1-50
			if (unglueckszahlenArray.contains((Integer) i)) {
				logger.log(Level.INFO, Integer.toString(i)
						+ " als Zahl aus der Menge an möglichen Zahlen zur Tippgenerierung ausgeschlossen.");
				continue; // Wenn die aktuelle Zahl unter den ausgeschlossenen Zahlen ist, wird mit der
							// nächsten Zahl weiter gemacht.
			} else {
				tippzahlenArray.add(i); // Ist die Zahl nicht im unglücksArray so wird die Zahl dem TIppArray
										// hinzugefügt.
			}
		}
		logger.log(Level.INFO, "tippzahlenArray gefüllt.");
		for (int i = 1; i < 11; i++) { // Für die Zahlen 1-10
			if (unglueckszahlenArray.contains((Integer) i)) {
				logger.log(Level.INFO, Integer.toString(i)
						+ " als Zahl aus der Menge an möglichen Zahlen zur Tippgenerierung ausgeschlossen.");
				continue; // Wenn die aktuelle Zahl unter den ausgeschlossenen Zahlen ist, wird mit der
							// nächsten Zahl weiter gemacht.
			} else {
				zweiAusZehntippzahlenArray.add(i); // Ist die Zahl nicht im unglücksArray so wird die Zahl dem
													// zweiAusZehntippzahlenArray hinzugefügt.
			}
		}
		logger.log(Level.INFO, "zweiAusZehntippzahlenArray gefüllt.");
		logger.log(Level.INFO, "erstelleCollection durchgelaufen.");
	}
}
