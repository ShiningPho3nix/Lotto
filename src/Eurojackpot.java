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

	private ArrayList<Integer> zweiAusZehntippzahlenArray;

	/**
	 * 
	 */
	public Eurojackpot() {
		super("Eurojackpot");
		zweiAusZehntippzahlenArray = new ArrayList<Integer>();
		erstelleCollection();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ILotto#generiereTipp()
	 */
	@Override
	public void generiereTipp() {
		int shuffle = (int) (Math.random() * 200);
		System.out.println(shuffle);
		for (int i = 0; i < shuffle; i++)
			Collections.shuffle(tippzahlenArray);
		Collections.shuffle(zweiAusZehntippzahlenArray);
		ArrayList<Integer> tipp = new ArrayList<Integer>();
		for (int i = 0; i < 6; i++) {
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
		tipp.addAll(zweiAusZehnTipp);
		tipp.forEach(System.out::println);
		logger.log(Level.INFO, "Tipp wurde dem Nutzer auf der Konsole ausgegeben.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ILotto#erstelleCollection()
	 */
	@Override
	public void erstelleCollection() {
		for (int i = 1; i < 51; i++) {
			if (unglueckszahlenArray.contains((Integer) i)) {
				logger.log(Level.INFO, Integer.toString(i)
						+ " als Zahl aus der Menge an möglichen Zahlen zur Tippgenerierung ausgeschlossen.");
				continue;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see ILotto#entferneZahlen(int)
	 */
	@Override
	public void entferneZahlen(int zahl) {
		if (tippzahlenArray.size() > 44 && tippzahlenArray.contains(zahl)) {
			tippzahlenArray.remove((Integer) zahl);
			logger.log(Level.INFO, zahl + " wurde aus der Menge an möglichen Zahlen für " + modus() + " entfernt.");
			if (zweiAusZehntippzahlenArray.size() > 4 && zweiAusZehntippzahlenArray.contains(zahl)) {
				zweiAusZehntippzahlenArray.remove(zahl);
				logger.log(Level.INFO, zahl + " wurde aus der Menge an möglichen Zahlen für " + modus() + " entfernt.");
			}
			unglueckszahlenArray.add(zahl);
			ausgabe.erfolgreichEntfernt(zahl);
			tippzahlenArray.forEach(System.out::println);
			zweiAusZehntippzahlenArray.forEach(System.out::println);
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
			if (!zweiAusZehntippzahlenArray.contains(zahl)) {
				zweiAusZehntippzahlenArray.add(zahl);
			}
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
		speicher.speichern(unglueckszahlenArray, modus());
	}
}
