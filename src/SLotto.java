import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SLotto {

	protected ArrayList<Integer> tippzahlenArray;
	protected ArrayList<Integer> unglueckszahlenArray;
	protected Ausgabe ausgabe;
	protected Speichern speicher;
	protected Laden lader;
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
		unglueckszahlenArray = new ArrayList<Integer>();
		ausgabe = LottoTippGenerator.ausgabe;
		speicher = new Speichern();
		lader = new Laden();
		logger.log(Level.INFO, "Arrays wurden inizialisiert");
		laden();
		checkArray();
		logger.log(Level.INFO, "Super Konstruktor für " + currentmode + " durchgelaufen.");
	}

	/**
	 * Methode zum entfernen von Zahlen aus der Menge an Zahlen für die
	 * Tippgenerierung. Löschen einer Zahl ist nur möglich wenn noch nicht 6 Zhalen
	 * entfernt wurden und die Zahl selbst noch nicht entfernt worden ist.
	 */
	public void entferneZahlen(int[] zahlen) {
		for (int zahl : zahlen) {
			if (unglueckszahlenArray.size() <= 6 && !unglueckszahlenArray.contains(zahl) && zahl < 51) {
				tippzahlenArray.remove((Integer) zahl);
				unglueckszahlenArray.add(zahl);
				logger.log(Level.INFO,
						zahl + " wurde aus der Menge an möglichen Zahlen für die Tippgenerierung entfernt.");
				ausgabe.erfolgreichEntfernt(zahl);
				logger.log(Level.INFO, "unglückszahlenArray gespeichert.");
			} else {
				ausgabe.nichtLoeschbar(zahl);
				logger.log(Level.INFO,
						zahl + " wurde nicht aus der Menge an möglichen Zahlen für die Tippgenerierung entfernt.");
			}
		}
		speichern();
		logger.log(Level.INFO, "entferneZahlen() durchgelaufen");
	}

	/**
	 * Funktion um eine Zahl welche ausgeschlossen wurde wieder zuzulassen. Dafür
	 * muss die Zahl eine Zahl sein, in dem Array: unglueckszahlenArray vorhanden
	 * und in dem Array: sechsAusNeunundvierzig nicht vorhanden sein.
	 */
	public void entferneUnglueckszahl(int[] zahlen) {
		for (int zahl : zahlen) {
			if (unglueckszahlenArray.contains(zahl)) {
				unglueckszahlenArray.remove((Integer) zahl);
				tippzahlenArray.add(zahl);
				logger.log(Level.INFO,
						zahl + " wurde der Menge an möglichen Zahlen zur Tippgenerierung wieder hinzugefügt.");
				ausgabe.erfolgreichWiederHinzugefuegt(zahl);
			} else {
				ausgabe.hinzufuegenNichtMoeglich(zahl);
				logger.log(Level.INFO,
						zahl + " wurde nicht der Menge an möglichen Zahlen zur Tippgenerierung wieder hinzugefügt.");
			}
		}
		speichern();
		logger.log(Level.INFO, "entferneUnglueckszahl() durchgelaufen");
	}

	/**
	 * Funktion zum Laden der abgespeicherten Zahlen, welche nicht in den
	 * generierten Tipps vorkommen sollen.
	 */
	public void laden() {
		unglueckszahlenArray = lader.laden();
		logger.log(Level.INFO, "Array mit ausgeschlossenen Zahlen wurde erfolgreich übergeben.");
	}

	/**
	 * Funktion zum Speichern der ausgeschlossenen Zahlen.
	 */
	public void speichern() {
		speicher.speichern(unglueckszahlenArray);
		logger.log(Level.INFO, "Gespeichert, SLotto speichern durchgelaufen.");
	}

	public void reset() {
		unglueckszahlenArray = new ArrayList<Integer>();
		speichern();
	}

	public void checkArray() {
		for (Integer integer : unglueckszahlenArray) {
			int count = 0;
			for (Integer integer2 : unglueckszahlenArray) {
				if (integer.equals(integer2)) {
					count++;
				}
				if (count > 1) {
					unglueckszahlenArray.remove((Integer) integer);
				}
			}
		}
		speichern();
	}
}