import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Klasse enth�lt Methoden um verschiedenes auf der Konsole auszugeben. Somit
 * werden nahezu alle System.out.println() entfernt.
 * 
 * @author Steffen Dworsky
 *
 */
public class Ausgabe {

	private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public Ausgabe() {
		logger.log(Level.INFO, "Ausgabe Konstruktor durchgelaufen.");
	}

	/**
	 * Gibt zum Start des Programms eine Wilkommensbotschaft aus.
	 */
	public void begruessung() {
		System.out.println("**************************************");
		System.out.println("*Willkommen beim Lotto Tipp Generator*");
		System.out.println("**************************************");
		System.out.println("");
		logger.log(Level.INFO, "Dem Nutzer wurde die Begr��ungsnachricht �ber die Konsole ausgegeben.");
	}

	/**
	 * Bekommt einen Modus �bergeben und gibt eine kurze Nachricht aus, das dieser
	 * Mosud gew�hlt wurde.
	 * 
	 * @param modus
	 */
	public void modusGewaehlt(String modus) {
		System.out.println(modus + " wurde als Lottoart gew�hlt.");
		System.out.println("");
		logger.log(Level.INFO,
				"Der Nutzer wurde �ber die Konsole benachrichtigt, dass " + modus + " als Modus ausgew�hlt wurde.");
	}

	/**
	 * Gibt eine Zeile aus, in der dem Nutzer gesagt wird wie er sich das Hilfemen�
	 * ausgeben lassen kann.
	 */
	public void hilfeBefehl() {
		System.out.println("'h' eingeben um eine Liste mit Befehlen ausgegeben zu bekommen.");
		logger.log(Level.INFO,
				"Dem Nutzer wurde �ber die Konsole die Information wie die Hilfe aufgerufen wird ausgegeben.");
	}

	/**
	 * Gibt die Aufforderung aus das ein Spielmodus gew�hlt wird, sowie welche
	 * Spielmodi zur auswahl stehen.
	 */
	public void waehleSpiel() {
		System.out.println("Bitte w�hlen Sie das gew�nschte Lottospiel:");
		System.out.println("1: 6aus49");
		System.out.println("2: Eurojackpot");
		System.out.println("");
		logger.log(Level.INFO, "Dem Nutzer wurde �ber die Konsole die Informationen zur Modusauswahl ausgegeben.");
	}

	/**
	 * Gibt eine kurze Benachrichtigung aus, das eine ung�ltige Eingabe get�tigt
	 * wurde.
	 */
	public void ungueltigeEingabe() {
		System.out.println("Ung�ltige Eingabe get�tigt!");
		System.out.println("");
		logger.log(Level.INFO,
				"Der Nutzer wurde �ber die Konsole darauf hingewiesen, dass eine ung�ltige Eingabe get�tigt wurde, dem Nutzer wurde ein Hinwei� auf den Hilfebefehl gegeben.");
	}

	/**
	 * Gibt aus, das die �bergebene Zahl nicht aus der Menge an Zahlen gel�scht
	 * werden kann. Unterscheidet hierbei zwischen: Auserhalb des g�ltigen
	 * Zahlenbereiches und zahl entweder bereits entfernt oder die maximal 6
	 * zul�ssigen zahlen wurden bereits entfernt.
	 * 
	 * @param zahl
	 */
	public void nichtLoeschbar(int zahl) {
		if (zahl > 49 || zahl < 1) {
			System.out.println(
					"Die zu l�schende Zahl muss beim Spielmodus 6aus49 im Bereich [1,49] liegen, beim Eurojackpot im Bereich [1,50]. Sie haben "
							+ zahl + " eingegeben");
			logger.log(Level.INFO, "Der Nutzer wurde benachrichtigt das " + zahl + " nicht im g�ltigem Bereich liegt.");

		} else {
			System.out.println("L�schen von " + zahl
					+ " nicht m�glich. Entwerder wurden bereits die Maximal 6 zul�ssigen Zahlen entfernt oder die gew�nschte Zahl ist bereits entfernt");
			System.out.println("");
			logger.log(Level.INFO,
					"Der Nutzer wurde benachrichtigt, dass das L�schen von " + zahl + " nicht m�glich ist.");
		}
	}

	/**
	 * Gibt die Aufforderung zur Eingabe eines Befehls aus.
	 * 
	 * @param modus
	 */
	public void erwarteBefehl(String modus) {
		System.out.println("Bitte einen Befehl eingeben:");
		hilfeBefehl();
		logger.log(Level.INFO, "Der Nutzer wurde zur Eingabe eines Befehls aufgefordert.");
	}

	/**
	 * Gibt die Aufforderung zur Eingabe einer Zahl aus.
	 */
	public void zahlEingeben() {
		System.out.println("Bitte die zu l�schende Zahl eingeben:");
	}

	/**
	 * Gibt die durch 'h' oder 'help' aufgerufene Hilfe aller Befehle aus.
	 */
	public void hilfeAusgeben() {
		// TODO Befehle erg�nzen
		System.out.println("Die m�glichen Befehle sind:");
		System.out.println(
				"'tippgen': Erzeugt einen Tipp im aktuellem Modus unter Ber�cksichtigung der entfernten Zahlen.");
		System.out.println("'reset': Setzt alle gesperrten Zahlen wieder zur�ck.");
		System.out.println(
				"'delete': Erm�glicht das Entfernen einer einzigen Zahl aus der Menge an Zahlen zur Tippgenerierung.");
		logger.log(Level.INFO, "Befehl Hilfe ausgegeben.");
	}

	/**
	 * Gibt aus das die �bergebene Zahl erfolgreich entfernt wurde.
	 * 
	 * @param zahl
	 */
	public void erfolgreichEntfernt(int zahl) {
		System.out.println(zahl + " erfolgreich entfernt");
		logger.log(Level.INFO, "�ber erfolgreiches entfernen der Zahl: " + zahl + " benachrichtigt.");
	}

	/**
	 * Gibt aus das die �bergebene Zahl erfolgreich wieder hinzugef�gt wurde.
	 * 
	 * @param zahl
	 */
	public void erfolgreichWiederHinzugefuegt(int zahl) {
		System.out.println(zahl + " erfolgreich wieder hinzugef�gt");
		logger.log(Level.INFO, "�ber erfolgreiches wieder hinzuf�gen der Zahl: " + zahl + " benachrichtigt.");
	}

	/**
	 * Gibt aus das eine Datei zum abspeichern der der entfernten Zahlen erzeugt
	 * wurde.
	 */
	public void dateiErstellt(String modus) {
		System.out.println(
				"Es existierte noch keine Datei zum abspeichern der entfernten Zahlen. Diese Datei wurde mit dem Namen 'TippGenerator"
						+ modus + ".txt' erstellt");
		logger.log(Level.INFO,
				"�ber erfolgreiches erstellen einer neuen Datei f�r den Modus: " + modus + " benachrichtigt.");
	}

	/**
	 * Gibt aus das die �bergebene Zahl keine Zahl ist, sowie den G�ltigen
	 * Zahlenbereich.
	 * 
	 * @param input
	 */
	public void istKeineZahl(String input) {
		System.out.println(input + " ist keine Zahl. Bitte geben Sie eine Zahl im Bereich [1,49] ein.");
		logger.log(Level.INFO, "Nachricht ausgegeben, dass " + input + " keine Zahl ist.");
	}

	public void hinzufuegenNichtMoeglich(int zahl) {
		System.out.println(zahl + " konnte nicht wieder hinzugef�gt werden.");
		logger.log(Level.INFO, "�ber Fehlschlag beim wieder hinzuf�gen der Zahl: " + zahl + " benachrichtigt.");
	}
}
