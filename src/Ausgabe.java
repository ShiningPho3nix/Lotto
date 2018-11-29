import java.util.ArrayList;
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
		logger.setUseParentHandlers(false);
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
	 * Modus gew�hlt wurde.
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
		System.out.println("6aus49");
		System.out.println("Euro");
		System.out.println("");
		logger.log(Level.INFO, "Dem Nutzer wurde �ber die Konsole die Informationen zur Modusauswahl ausgegeben.");
	}

	/**
	 * Gibt eine kurze Benachrichtigung aus, das eine ung�ltige Eingabe get�tigt
	 * wurde.
	 */
	public void ungueltigeEingabe(String eingabe) {
		System.out.println("Ung�ltige Eingabe get�tigt: " + eingabe);
		System.out.println("");
		hilfeAusgeben();
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
		System.out.println("L�schen von " + zahl + " nicht m�glich!");
		if (zahl > 50 || zahl < 1) {
			System.out.println("Die zu l�schende Zahl muss im Bereich [1,50] liegen.");
			logger.log(Level.INFO, "Der Nutzer wurde benachrichtigt das " + zahl + " nicht im g�ltigem Bereich liegt.");

		} else {
			System.out.println(
					"Entweder wurden bereits die Maximal 6 zul�ssigen Zahlen entfernt oder die gew�nschte Zahl ist bereits entfernt");
			System.out.println("");
			logger.log(Level.INFO,
					"Der Nutzer wurde benachrichtigt, dass das L�schen von " + zahl + " nicht m�glich ist.");
		}
	}

	/**
	 * Gibt die Aufforderung zur Eingabe eines Befehls aus.
	 */
	public void erwarteBefehl() {
		System.out.println("Bitte einen Befehl eingeben:");
		hilfeBefehl();
		logger.log(Level.INFO, "Der Nutzer wurde zur Eingabe eines Befehls aufgefordert.");
	}

	/**
	 * Gibt die Aufforderung zur Eingabe einer Zahl aus.
	 */
	public void zahlEingeben() {
		System.out.println("Bitte die Zahlen mit leerzeichen getrennt eingeben:");
	}

	/**
	 * Gibt die durch 'h' aufgerufene Hilfe aller Befehle aus.
	 */
	public void hilfeAusgeben() {
		System.out.println("Die m�glichen Befehle sind:");
		System.out.println(
				"'tippgen <6aus49/euro> <Tippanzahl>': Erzeugt einen Tipp im angegebenen Modus, die angegebene Anzahl an Mal."
						+ " Die Eingaben werden durch ein leerzeichen getrennt.");
		System.out.println("'reset': Sammlung mit ausgeschlossenen Zahlen wird zur�ckgesetzt.");
		System.out.println(
				"'delete <Zahlen>': Erm�glicht das Entfernen einer oder mehrerer Zahlen aus der Menge an Zahlen zur Tippgenerierung. Die Zahlen werden durch ein leerzeichen getrennt.");
		System.out.println(
				"'readd <Zahlen>': Erm�glicht das Entfernen einer oder mehrerer Zahlen aus der Menge an ausgeschlossenen Zahlen. Die Zahlen werden durch ein leerzeichen getrennt.");
		System.out.println("'list': Gibt eine Liste mit allen aktuell ausgeschlossenen Zahlen aus.");
		System.out.println("'quit': Beendet das Programm ordnungsgem��.");
		System.out.println("");
		logger.log(Level.INFO, "List mit Befehlen ausgegeben..");
	}

	/**
	 * Gibt aus das die �bergebene Zahl erfolgreich entfernt wurde.
	 * 
	 * @param zahl
	 * @param string
	 */
	public void erfolgreichEntfernt(int zahl, String string) {
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
	public void dateiErstellt() {
		System.out.println(
				"Es existierte noch keine Datei zum abspeichern der entfernten Zahlen. Diese Datei wurde mit dem Namen 'TippGenerator.txt' erstellt");
		logger.log(Level.INFO, "�ber erfolgreiches erstellen einer neuen Datei benachrichtigt.");
	}

	/**
	 * Gibt aus das die �bergebene Zahl keine Zahl ist, sowie den G�ltigen
	 * Zahlenbereich.
	 * 
	 * @param input
	 */
	public void istKeineZahl(String input) {
		System.out.println(input + " ist keine Zahl und wird daher ignoriert.");
		logger.log(Level.INFO, "Nachricht ausgegeben, dass " + input + " keine Zahl ist.");
	}

	/**
	 * Gibt dem Nutzer die Information das eine der �bergebenen Zahlen nicht wieder
	 * hinzugef�gt werden konnte.
	 * 
	 * @param zahl
	 */
	public void hinzufuegenNichtMoeglich(int zahl) {
		System.out.println(zahl + " konnte nicht wieder hinzugef�gt werden.");
		if (zahl > 50 || zahl < 1) {
			System.out.println("Die eingegebene Zahl muss im Bereich [1,50] liegen.");
			logger.log(Level.INFO, "Der Nutzer wurde benachrichtigt das " + zahl + " nicht im g�ltigem Bereich liegt.");

		} else {
			System.out.println("Die Zahl ist aktuell nicht unter den ausgeschlossenen Zahlen.");
			System.out.println("");
			logger.log(Level.INFO,
					"Der Nutzer wurde benachrichtigt, dass das L�schen von " + zahl + " nicht m�glich ist.");
		}
		logger.log(Level.INFO, "�ber Fehlschlag beim wieder hinzuf�gen der Zahl: " + zahl + " benachrichtigt.");
	}

	/**
	 * Gibt eine Mittteilung aus, das die folgenden Zahlen ausgeschlossene Zahlen
	 * sind.
	 */
	public void list() {
		System.out.println("Die derzeitig ausgeschlossenen Zahlen sind:");
		logger.log(Level.INFO,
				"Dem Nutzer wurde �ber die Konsole mitgeteilt das die folgenden Zahleh ansgeschlossene Zahlen sind.");
	}

	/**
	 * Gibt eine kurze Benachrichtigung aus, das ein ung�ltiger Modus eingegeben
	 * wurde.
	 * 
	 * @return
	 */
	public void ungueltigeModusEingabe(ArrayList<String> tippgen) {
		for (String string : tippgen) {
			if (ProgramFlow.istNumerisch(string)) {
				continue;
			} else {
				System.out.println("Unbekannten Modus eingegeben: " + string);
				System.out.println("");
			}
		}
		hilfeAusgeben();
		System.out.println("");
		logger.log(Level.INFO,
				"Der Nutzer wurde �ber die Konsole darauf hingewiesen, dass eine ung�ltige Eingabe get�tigt wurde."
						+ " Dem Nutzer wurde ein Hinwei� auf den Hilfebefehl gegeben.");
	}

	/**
	 * Methode zur Ausgabe einer Benachrichtigung an der Nutzer �ber die Konsole.
	 * Dem Nutzer wird mitgeteilt das ein �bergebener Befehl ung�ltige Strings
	 * enthielt und das diese ignoriert wurden
	 * 
	 * @param tippgen
	 */
	public void ignorierterBefehl(ArrayList<String> tippgen) {
		System.out.println("Der Eingegebene Befehl enthielt ung�ltige Teile, welche ignoriert werden.");
		tippgen.forEach(System.out::print);
	}

	/**
	 * Gibt die Information aus, dass derzeit keine Zahlen von der Tippgenerierung
	 * ausgeschlossen sind.
	 */
	public void keineZahlenAusgeschlossen() {
		System.out.println("Derzeit sind keine Zahlen von der Tippgenerierung ausgeschlossen.");

	}

	/**
	 * Methode zum ausgeben der aktuellen Liste an ausgeschlossenen Zahlen.
	 * 
	 * @param tippgenerator
	 */
	public void ausgeschlosseneZahlenListe(TippGenerator tippgenerator) {
		System.out.println(tippgenerator.liste().toString());
	}

}
