package de.ShiningPho3nix.Lotto;
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

	/**
	 * Der Konstruktor führt den super() Konstruktor aus, inizialisiert das extra
	 * Array zweiAusZehnTippzahlenArray und füllt dieses anschließend mit den
	 * erforderlichen und erlaubten Zahlen.
	 * 
	 */
	public Eurojackpot() {
		super("Eurojackpot");
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
		logger.log(Level.INFO, tipp + " " + zweiAusZehnTipp + "wurde als Tipp generiert");
		return (StringSammlung.eurojackpotTipp(tipp, zweiAusZehnTipp));
	}
}
