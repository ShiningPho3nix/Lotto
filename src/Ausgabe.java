import java.util.ArrayList;
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
		logger.log(Level.INFO, "Dem Nutzer wurde die Begrüßungsnachricht über die Konsole ausgegeben.");
	}

	/**
	 * Bekommt einen Modus übergeben und gibt eine kurze Nachricht aus, das dieser
	 * Modus gewählt wurde.
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
		System.out.println("6aus49");
		System.out.println("Euro");
		System.out.println("");
		logger.log(Level.INFO, "Dem Nutzer wurde über die Konsole die Informationen zur Modusauswahl ausgegeben.");
	}

	/**
	 * Gibt eine kurze Benachrichtigung aus, das eine ungültige Eingabe getätigt
	 * wurde.
	 */
	public void ungueltigeEingabe(String eingabe) {
		System.out.println("Ungültige Eingabe getätigt: " + eingabe);
		System.out.println("");
		hilfeAusgeben();
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
		System.out.println("Löschen von " + zahl + " nicht möglich!");
		if (zahl > 50 || zahl < 1) {
			System.out.println("Die zu löschende Zahl muss im Bereich [1,50] liegen.");
			logger.log(Level.INFO, "Der Nutzer wurde benachrichtigt das " + zahl + " nicht im gültigem Bereich liegt.");

		} else {
			System.out.println(
					"Entweder wurden bereits die Maximal 6 zulässigen Zahlen entfernt oder die gewünschte Zahl ist bereits entfernt");
			System.out.println("");
			logger.log(Level.INFO,
					"Der Nutzer wurde benachrichtigt, dass das Löschen von " + zahl + " nicht möglich ist.");
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
		System.out.println("Die möglichen Befehle sind:");
		System.out.println(
				"'tippgen <6aus49/euro> <Tippanzahl>': Erzeugt einen Tipp im angegebenen Modus, die angegebene Anzahl an Mal."
						+ " Die Eingaben werden durch ein leerzeichen getrennt.");
		System.out.println("'reset': Sammlung mit ausgeschlossenen Zahlen wird zurückgesetzt.");
		System.out.println(
				"'delete <Zahlen>': Ermöglicht das Entfernen einer oder mehrerer Zahlen aus der Menge an Zahlen zur Tippgenerierung. Die Zahlen werden durch ein leerzeichen getrennt.");
		System.out.println(
				"'readd <Zahlen>': Ermöglicht das Entfernen einer oder mehrerer Zahlen aus der Menge an ausgeschlossenen Zahlen. Die Zahlen werden durch ein leerzeichen getrennt.");
		System.out.println("'list': Gibt eine Liste mit allen aktuell ausgeschlossenen Zahlen aus.");
		System.out.println("'quit': Beendet das Programm ordnungsgemäß.");
		System.out.println("");
		logger.log(Level.INFO, "List mit Befehlen ausgegeben..");
	}

	/**
	 * Gibt aus das die übergebene Zahl erfolgreich entfernt wurde.
	 * 
	 * @param zahl
	 * @param string
	 */
	public void erfolgreichEntfernt(int zahl, String string) {
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
	public void dateiErstellt() {
		System.out.println(
				"Es existierte noch keine Datei zum abspeichern der entfernten Zahlen. Diese Datei wurde mit dem Namen 'TippGenerator.txt' erstellt");
		logger.log(Level.INFO, "Über erfolgreiches erstellen einer neuen Datei benachrichtigt.");
	}

	/**
	 * Gibt aus das die übergebene Zahl keine Zahl ist, sowie den Gültigen
	 * Zahlenbereich.
	 * 
	 * @param input
	 */
	public void istKeineZahl(String input) {
		System.out.println(input + " ist keine Zahl und wird daher ignoriert.");
		logger.log(Level.INFO, "Nachricht ausgegeben, dass " + input + " keine Zahl ist.");
	}

	/**
	 * Gibt dem Nutzer die Information das eine der Übergebenen Zahlen nicht wieder
	 * hinzugefügt werden konnte.
	 * 
	 * @param zahl
	 */
	public void hinzufuegenNichtMoeglich(int zahl) {
		System.out.println(zahl + " konnte nicht wieder hinzugefügt werden.");
		if (zahl > 50 || zahl < 1) {
			System.out.println("Die eingegebene Zahl muss im Bereich [1,50] liegen.");
			logger.log(Level.INFO, "Der Nutzer wurde benachrichtigt das " + zahl + " nicht im gültigem Bereich liegt.");

		} else {
			System.out.println("Die Zahl ist aktuell nicht unter den ausgeschlossenen Zahlen.");
			System.out.println("");
			logger.log(Level.INFO,
					"Der Nutzer wurde benachrichtigt, dass das Löschen von " + zahl + " nicht möglich ist.");
		}
		logger.log(Level.INFO, "Über Fehlschlag beim wieder hinzufügen der Zahl: " + zahl + " benachrichtigt.");
	}

	/**
	 * Gibt eine Mittteilung aus, das die folgenden Zahlen ausgeschlossene Zahlen
	 * sind.
	 */
	public void list() {
		System.out.println("Die derzeitig ausgeschlossenen Zahlen sind:");
		logger.log(Level.INFO,
				"Dem Nutzer wurde über die Konsole mitgeteilt das die folgenden Zahleh ansgeschlossene Zahlen sind.");
	}

	/**
	 * Gibt eine kurze Benachrichtigung aus, das ein ungültiger Modus eingegeben
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
				"Der Nutzer wurde über die Konsole darauf hingewiesen, dass eine ungültige Eingabe getätigt wurde."
						+ " Dem Nutzer wurde ein Hinweiß auf den Hilfebefehl gegeben.");
	}

	/**
	 * Methode zur Ausgabe einer Benachrichtigung an der Nutzer über die Konsole.
	 * Dem Nutzer wird mitgeteilt das ein übergebener Befehl ungültige Strings
	 * enthielt und das diese ignoriert wurden
	 * 
	 * @param tippgen
	 */
	public void ignorierterBefehl(ArrayList<String> tippgen) {
		System.out.println("Der Eingegebene Befehl enthielt ungültige Teile, welche ignoriert werden.");
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
