import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SLotto {

	public ArrayList<Integer> tippzahlenArray;
	public ArrayList<Integer> zweiAusZehnTippzahlenArray;
	public ArrayList<Integer> unglueckszahlenArray;
	protected static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private String currentmode;

	/**
	 * Der Konstruktor inizialisiert die beiden Arrays für die Menge an Zahlen zur
	 * Tippgenerierung, sowie für die entfernten Zahlen. Anschließend wird die
	 * Funktion laden() ausgeführt.
	 */
	public SLotto(String mode) {
		this.currentmode = mode;
		tippzahlenArray = new ArrayList<Integer>();
		zweiAusZehnTippzahlenArray = new ArrayList<Integer>();
		unglueckszahlenArray = new ArrayList<Integer>();
		logger.log(Level.INFO, "tippzahlenArray & unglueckszahlenArray wurden inizialisiert");
		laden();
		erstelleCollection();
		logger.log(Level.INFO, "Super Konstruktor für " + currentmode + " durchgelaufen.");
	}

	/**
	 * Methode zum entfernen von Zahlen aus der Menge an Zahlen für die
	 * Tippgenerierung. Löschen einer Zahl ist nur möglich wenn noch nicht 6 Zahlen
	 * entfernt wurden und die Zahl selbst noch nicht entfernt worden ist.
	 * Anschlißend wird entferneAusTippzahlen ausgeführt.
	 */
	public String neueUnglueckszahlAusschliessen(Integer[] zahlen) {
		StringBuilder sb = new StringBuilder();
		for (int zahl : zahlen) {
			if (unglueckszahlenArray.size() < 6 && !unglueckszahlenArray.contains(zahl) && 0 < zahl && zahl < 51
					&& tippzahlenArray.contains(zahl)) {
				// Prüft ob noch nicht 6 unglückszahlen gewählt sind, ob die aktuelle Zahl noch
				// keine unlückszahl ist und ob die Zahl im gültigen Bereich liegt.
				unglueckszahlenArray.add(zahl); // Fügt die Zahl der unglückszahlen ArrayList hinzu.
				tippzahlenArray.remove((Integer) zahl);
				if (zweiAusZehnTippzahlenArray.contains(zahl)) {
					zweiAusZehnTippzahlenArray.remove((Integer) zahl); // entfernt die Aktuelle Zahl aus der 2aus10
					// ArrayList.
					logger.log(Level.INFO, zahl + " wurde auch aus dem zweiAusZehnTippzahlenArray entfernt.");
				}
				logger.log(Level.INFO,
						zahl + " wurde aus der Menge an möglichen Zahlen für die " + " Tippgenerierung entfernt.");
				logger.log(Level.INFO, "unglückszahlenArray gespeichert.");
				sb.append(StringSammlung.erfolgreichEntfernt(zahl) + "\n");
				speichern();
			} else {
				sb.append(StringSammlung.nichtLoeschbar(zahl) + "\n"); // Sind die Bedingungen an eine der Zahlen
				// nicht erfüllt, so wird
				// auf der
				// Konsole ausgegeben das diese Zahl nicht gelöscht werden kann. Es wird
				// mit der nächsten Zahl weiter gemacht.
				logger.log(Level.INFO, zahl + " wurde nicht aus der Menge an möglichen Zahlen für die "
						+ " Tippgenerierung entfernt.");
			}
		}
		logger.log(Level.INFO, "entferneZahlen() durchgelaufen");
		return sb.toString();
	}

	/**
	 * Funktion um eine Zahl welche ausgeschlossen wurde wieder zuzulassen. Dafür
	 * muss die Zahl eine Zahl sein, in dem Array: unglueckszahlenArray vorhanden
	 * und in dem Array: sechsAusNeunundvierzig nicht vorhanden sein.
	 * 
	 * @return
	 */
	public String unglueckszahlWiederZulassen(Integer[] zahlen) {
		StringBuilder sb = new StringBuilder();
		for (int zahl : zahlen) { // durchläuft das Array an zu löschenden Zahlen
			if (unglueckszahlenArray.contains(zahl)) { // Ist die Aktuelle Zahl im unglücksArray, ...
				unglueckszahlenArray.remove((Integer) zahl); // ... so wird diese entfernt ...
				tippzahlenArray.add(zahl); // und dem tippzahlenAray hinzugefügt.
				if (zahl <= 10) {
					zweiAusZehnTippzahlenArray.add(zahl);
				}
				logger.log(Level.INFO,
						zahl + " wurde der Menge an möglichen Zahlen zur Tippgenerierung wieder hinzugefügt.");
				speichern(); // das aktualisierte Array der unglückszahlen wird abgespeichert.
				logger.log(Level.INFO, "entferneUnglueckszahl() erfolgreich durchgelaufen");
				sb.append(StringSammlung.erfolgreichWiederHinzugefuegt(zahl) + "\n");
			} else { // Ist die aktuelle Zahl nicht unter den entfernen, so wird diese ignoriert.
				logger.log(Level.INFO,
						zahl + " wurde nicht der Menge an möglichen Zahlen zur Tippgenerierung wieder hinzugefügt.");
				sb.append(StringSammlung.hinzufuegenNichtMoeglich(zahl) + "\n");
			}
		}
		return sb.toString();
	}

	/**
	 * Funktion zur Erstellung einer Liste mit zulässigen Zahlen für die Tipp
	 * Generierung. Die ausgeschlossenen Zahlen werden hierbei nicht mit
	 * aufgenommen. Die Zulässigen Zahlen werden dem tippzahlenArray hinzugefügt.
	 * Zusätzlich wird auch immer das zweiAusZehnTippzahlenArray befüllt.
	 */
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
				zweiAusZehnTippzahlenArray.add(i); // Ist die Zahl nicht im unglücksArray so wird die Zahl dem
													// zweiAusZehntippzahlenArray hinzugefügt.
			}
		}
		logger.log(Level.INFO, "zweiAusZehntippzahlenArray gefüllt.");
		logger.log(Level.INFO, "erstelleCollection durchgelaufen.");
	}

	/**
	 * Diese Methode wird in den jeweiligen Klassen Eurojackpot und 6aus49
	 * Überschrieben und dort ausgeführt. Führt dem entsprechend natürlich auch die
	 * korrekte version basierend auf dem jeweiligem Objekt (euro oder 6aus49) aus.
	 * 
	 * @return
	 */
	public String generiereTipp() {
		// generiereTipp wird in den jeweiligen Klassen Eurojackpot und 6aus49
		// ausgeführt.
		return null;
	}

	/**
	 * Hat der Nutzer den Wunsch mehrere Tipps zu generieren, so wird diese Methode
	 * verwendet, welche generiereTipp() die gewünschte anzahl an mal ausführt und
	 * somit die gewünschte anzahl an Tipps generiert werden.
	 */
	public String generiereTipps(int quicktipp) {
		StringBuilder sb = new StringBuilder();
		if (quicktipp < 1) {
			quicktipp = 1;
		}
		for (int i = 1; i <= quicktipp; i++) {
			sb.append("Tipp#" + i + System.lineSeparator()); // Für die Übergeben Anzahl an mal wird Tipp mit
																// fortlaufender Zahl
			// ausgegeben...
			sb.append(generiereTipp() + System.lineSeparator()); // ...und anschließend ein Tipp generiert.
		}
		return sb.toString();
	}

	/**
	 * Funktion zum Laden der abgespeicherten Zahlen, welche nicht in den
	 * generierten Tipps vorkommen sollen.
	 */
	public void laden() {
		unglueckszahlenArray = FileOperation.laden(); // Fügt die laden Funktion der Klasse LAden aus und speichert das
		// zurückgegebene Array ind ungklückszahlenArray ab.
		logger.log(Level.INFO, "Array mit ausgeschlossenen Zahlen wurde erfolgreich übergeben.");
	}

	/**
	 * Funktion zum Speichern der ausgeschlossenen Zahlen. Ruft die Methode
	 * speichern() der Klasse Seichern auf.
	 */
	public void speichern() {
		FileOperation.speichern(unglueckszahlenArray, "Unglueckszahlen.txt"); // speichert das aktuelle
																				// unglückszahlenArray ab.
		logger.log(Level.INFO, "Gespeichert, SLotto speichern durchgelaufen.");
	}

	/**
	 * Gibt das unglueckszahlenArray als ArrayList zurück.
	 * 
	 * @return
	 */
	public ArrayList<Integer> liste() {
		return unglueckszahlenArray;
	}

	/**
	 * Funktion überschreibt das bestehende unglückszahlenArray mit einem neuen
	 * leeren Array und speichert dieses ab. Somit werden alles gesperrten Zahlen
	 * auf einmal gelöscht.
	 */
	public void reset() {
		unglueckszahlenArray = new ArrayList<Integer>(); // erzeugt eine Neue ArrayList und speichert diese in
															// unglückszahlenArray ab.
		speichern();
	}

	/**
	 * Gibt den Aktuellen Spielmodus als String zurück.
	 */
	public String modus() {
		return currentmode;
	}
}