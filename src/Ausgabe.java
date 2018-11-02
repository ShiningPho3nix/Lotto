import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Klasse enthält Methoden um verschiedenes auf der Konsole auszugeben. Somit
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
		logger.log(Level.INFO, "Dem Nutzer wurde die Begrüßungsnachricht über die Konsole ausgegeben.");
	}

	/**
	 * Bekommt einen Modus übergeben und gibt eine kurze Nachricht aus, das dieser
	 * Mosud gewählt wurde.
	 * 
	 * @param modus
	 */
	public void modusGewaehlt(String modus) {
		System.out.println(modus + " wurde als Lottoart gewählt.");
		System.out.println("");
		logger.log(Level.INFO,
				"Der Nutzer wurde über die Konsole benachrichtigt, dass " + modus + " als Modus ausgewählt wurde.");
	}

	/**
	 * Gibt eine Zeile aus, in der dem Nutzer gesagt wird wie er sich das Hilfemenü
	 * ausgeben lassen kann.
	 */
	public void hilfeBefehl() {
		System.out.println("'h' eingeben um eine Liste mit Befehlen ausgegeben zu bekommen.");
		logger.log(Level.INFO,
				"Dem Nutzer wurde über die Konsole die Information wie die Hilfe aufgerufen wird ausgegeben.");
	}

	/**
	 * Gibt die Aufforderung aus das ein Spielmodus gewählt wird, sowie welche
	 * Spielmodi zur auswahl stehen.
	 */
	public void waehleSpiel() {
		System.out.println("Bitte wählen Sie das gewünschte Lottospiel:");
		System.out.println("1: 6aus49");
		System.out.println("2: Eurojackpot");
		System.out.println("");
		logger.log(Level.INFO, "Dem Nutzer wurde über die Konsole die Informationen zur Modusauswahl ausgegeben.");
	}

	/**
	 * Gibt eine kurze Benachrichtigung aus, das eine ungültige Eingabe getätigt
	 * wurde.
	 */
	public void ungueltigeEingabe() {
		System.out.println("Ungültige Eingabe getätigt!");
		System.out.println("");
		logger.log(Level.INFO,
				"Der Nutzer wurde über die Konsole darauf hingewiesen, dass eine ungültige Eingabe getätigt wurde, dem Nutzer wurde ein Hinweiß auf den Hilfebefehl gegeben.");
	}

	/**
	 * Gibt aus, das die übergebene Zahl nicht aus der Menge an Zahlen gelöscht
	 * werden kann. Unterscheidet hierbei zwischen: Auserhalb des gültigen
	 * Zahlenbereiches und zahl entweder bereits entfernt oder die maximal 6
	 * zulässigen zahlen wurden bereits entfernt.
	 * 
	 * @param zahl
	 */
	public void nichtLoeschbar(int zahl) {
		if (zahl > 49 || zahl < 1) {
			System.out.println(
					"Die zu löschende Zahl muss beim Spielmodus 6aus49 im Bereich [1,49] liegen, beim Eurojackpot im Bereich [1,50]. Sie haben "
							+ zahl + " eingegeben");
			logger.log(Level.INFO, "Der Nutzer wurde benachrichtigt das " + zahl + " nicht im gültigem Bereich liegt.");

		} else {
			System.out.println("Löschen von " + zahl
					+ " nicht möglich. Entwerder wurden bereits die Maximal 6 zulässigen Zahlen entfernt oder die gewünschte Zahl ist bereits entfernt");
			System.out.println("");
			logger.log(Level.INFO,
					"Der Nutzer wurde benachrichtigt, dass das Löschen von " + zahl + " nicht möglich ist.");
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
		System.out.println("Bitte die zu löschende Zahl eingeben:");
	}

	/**
	 * Gibt die durch 'h' oder 'help' aufgerufene Hilfe aller Befehle aus.
	 */
	public void hilfeAusgeben() {
		// TODO Befehle ergänzen
		System.out.println("Die möglichen Befehle sind:");
		System.out.println(
				"'tippgen': Erzeugt einen Tipp im aktuellem Modus unter Berücksichtigung der entfernten Zahlen.");
		System.out.println("'reset': Setzt alle gesperrten Zahlen wieder zurück.");
		System.out.println(
				"'delete': Ermöglicht das Entfernen einer einzigen Zahl aus der Menge an Zahlen zur Tippgenerierung.");
		logger.log(Level.INFO, "Befehl Hilfe ausgegeben.");
	}

	/**
	 * Gibt aus das die übergebene Zahl erfolgreich entfernt wurde.
	 * 
	 * @param zahl
	 */
	public void erfolgreichEntfernt(int zahl) {
		System.out.println(zahl + " erfolgreich entfernt");
		logger.log(Level.INFO, "Über erfolgreiches entfernen der Zahl: " + zahl + " benachrichtigt.");
	}

	/**
	 * Gibt aus das die übergebene Zahl erfolgreich wieder hinzugefügt wurde.
	 * 
	 * @param zahl
	 */
	public void erfolgreichWiederHinzugefuegt(int zahl) {
		System.out.println(zahl + " erfolgreich wieder hinzugefügt");
		logger.log(Level.INFO, "Über erfolgreiches wieder hinzufügen der Zahl: " + zahl + " benachrichtigt.");
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
				"Über erfolgreiches erstellen einer neuen Datei für den Modus: " + modus + " benachrichtigt.");
	}

	/**
	 * Gibt aus das die übergebene Zahl keine Zahl ist, sowie den Gültigen
	 * Zahlenbereich.
	 * 
	 * @param input
	 */
	public void istKeineZahl(String input) {
		System.out.println(input + " ist keine Zahl. Bitte geben Sie eine Zahl im Bereich [1,49] ein.");
		logger.log(Level.INFO, "Nachricht ausgegeben, dass " + input + " keine Zahl ist.");
	}

	public void hinzufuegenNichtMoeglich(int zahl) {
		System.out.println(zahl + " konnte nicht wieder hinzugefügt werden.");
		logger.log(Level.INFO, "Über Fehlschlag beim wieder hinzufügen der Zahl: " + zahl + " benachrichtigt.");
	}
}
