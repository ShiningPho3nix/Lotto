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
	 * Der Konstruktor inizialisiert die beiden Arrays f�r die Menge an Zahlen zur
	 * Tippgenerierung, sowie f�r die entfernten Zahlen. Anschlie�end wird die
	 * Funktion laden() ausgef�hrt.
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
		logger.log(Level.INFO, "Super Konstruktor f�r " + currentmode + " durchgelaufen.");
	}

	/**
	 * Methode zum entfernen von Zahlen aus der Menge an Zahlen f�r die
	 * Tippgenerierung. L�schen einer Zahl ist nur m�glich wenn noch nicht 6 Zhalen
	 * entfernt wurden und die Zahl selbst noch nicht entfernt worden ist.
	 */
	public void entferneZahlen(int[] zahlen) {
		for (int zahl : zahlen) {
			if (unglueckszahlenArray.size() <= 6 && !unglueckszahlenArray.contains(zahl) && zahl < 51) {
				tippzahlenArray.remove((Integer) zahl);
				unglueckszahlenArray.add(zahl);
				logger.log(Level.INFO,
						zahl + " wurde aus der Menge an m�glichen Zahlen f�r die Tippgenerierung entfernt.");
				ausgabe.erfolgreichEntfernt(zahl);
				logger.log(Level.INFO, "ungl�ckszahlenArray gespeichert.");
			} else {
				ausgabe.nichtLoeschbar(zahl);
				logger.log(Level.INFO,
						zahl + " wurde nicht aus der Menge an m�glichen Zahlen f�r die Tippgenerierung entfernt.");
			}
		}
		speichern();
		logger.log(Level.INFO, "entferneZahlen() durchgelaufen");
	}

	/**
	 * Funktion um eine Zahl welche ausgeschlossen wurde wieder zuzulassen. Daf�r
	 * muss die Zahl eine Zahl sein, in dem Array: unglueckszahlenArray vorhanden
	 * und in dem Array: sechsAusNeunundvierzig nicht vorhanden sein.
	 */
	public void entferneUnglueckszahl(int[] zahlen) {
		for (int zahl : zahlen) {
			if (unglueckszahlenArray.contains(zahl)) {
				unglueckszahlenArray.remove((Integer) zahl);
				tippzahlenArray.add(zahl);
				logger.log(Level.INFO,
						zahl + " wurde der Menge an m�glichen Zahlen zur Tippgenerierung wieder hinzugef�gt.");
				ausgabe.erfolgreichWiederHinzugefuegt(zahl);
			} else {
				ausgabe.hinzufuegenNichtMoeglich(zahl);
				logger.log(Level.INFO,
						zahl + " wurde nicht der Menge an m�glichen Zahlen zur Tippgenerierung wieder hinzugef�gt.");
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
		logger.log(Level.INFO, "Array mit ausgeschlossenen Zahlen wurde erfolgreich �bergeben.");
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