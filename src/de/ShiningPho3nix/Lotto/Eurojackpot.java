package de.ShiningPho3nix.Lotto;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * 
 * @author Steffen Dworsky
 *
 */
public class Eurojackpot extends SLotto implements ILotto {

	private final static Logger logger = LogManager.getLogger(Eurojackpot.class);

	/**
	 * Der Konstruktor führt den super() Konstruktor aus, inizialisiert das extra
	 * Array zweiAusZehnTippzahlenArray und füllt dieses anschließend mit den
	 * erforderlichen und erlaubten Zahlen.
	 * 
	 */
	public Eurojackpot() {
		super();
		erstelleZweiAusZehnCollection();
		logger.info("Eurojackpot Konstruktor durchgelaufen.");
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
				logger.info(Integer.toString(i)
						+ " als Zahl aus der Menge an möglichen Zahlen zur Tippgenerierung ausgeschlossen.");
				continue; // Wenn die aktuelle Zahl unter den ausgeschlossenen Zahlen ist, wird mit der
							// nächsten Zahl weiter gemacht.
			} else {
				tippzahlenArray.add(i); // Ist die Zahl nicht im unglücksArray so wird die Zahl dem TIppArray
										// hinzugefügt.
			}
		}
		logger.info("tippzahlenArray gefüllt.");
		logger.info("erstelleCollection durchgelaufen.");
	}

	/**
	 * Diese Methode ist zur Generierung eines einzelnen Eurojackpot Tipps
	 * zuständig.
	 * 
	 * @return Ein formatierter String, welcher den generierten Tipp enthält.
	 */
	@Override
	public String generiereTipp() {

		Collections.shuffle(tippzahlenArray); // Mischt die Liste tippzahlenArray...
		Collections.shuffle(zweiAusZehnTippzahlenArray); // ... und zweiAusZehntippzahlenArray.
		ArrayList<Integer> tipp = new ArrayList<Integer>(); // ArrayList wird deklariert und inizialisiert um den Tipp
															// zwischen zu speichern.
		for (int i = 0; i < 5; i++) {
			tipp.add(tippzahlenArray.get(i)); // Die ersten 5 Zahlen aus dem tippzahlenArray werden tipp
												// hinzugefügt.
		}
		ArrayList<Integer> zweiAusZehnTipp = new ArrayList<Integer>(); // Für die 2aus10 Ziehung wird ebenfalls eine
																		// ArrayList deklariert und inizialisiert.
		for (int i = 0; i < 2; i++) {
			zweiAusZehnTipp.add(zweiAusZehnTippzahlenArray.get(i)); // Die ersten 2 Zahlen werden als 2aus10 tipp
																	// gezogen
		}
		Collections.sort(tipp, Collections.reverseOrder().reversed()); // Sortiert den tipp in aufsteigender Reihenfolge
		Collections.sort(zweiAusZehnTipp, Collections.reverseOrder().reversed()); // Die 2aus10 tipp ArrayList wird
																					// ebenfalls aufsteigend sortiert.
		return (StringSammlung.eurojackpotTipp(tipp, zweiAusZehnTipp));
	}

	/**
	 * Gibt den Aktuellen Spielmodus als String zurück.
	 */
	@Override
	public String getModus() {
		return "Euro";
	}
}
