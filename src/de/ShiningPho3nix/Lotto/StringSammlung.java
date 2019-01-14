package de.ShiningPho3nix.Lotto;
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
public class StringSammlung {

	private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public StringSammlung() {
		logger.setUseParentHandlers(false);
		logger.log(Level.INFO, "Ausgabe Konstruktor durchgelaufen.");
	}

	/**
	 * Eine kurze Willkommens Botschaft f�r den Nutzer.
	 */
	public static String begruessungConsole() {
		logger.log(Level.INFO, "Dem Nutzer wird die Begr��ungsnachricht �ber die Konsole ausgegeben.");
		return " ************************************** \n *Willkommen beim Lotto Tipp Generator* \n **************************************\n";
	}

	/**
	 * Da die Ausgabe auf der GUI scheinbar ein etwas anderes Format hat, musste die
	 * Begr��ung etwas angepasst werden. Gibt genau wie bei begruessungConsole eine
	 * begr��ungsnachricht als String zur�ck.
	 * 
	 * @return
	 */
	public static String begruessungGUI() {
		logger.log(Level.INFO, "Dem Nutzer wird die Begr��ungsnachricht �ber die GUI ausgegeben.");
		return " ********************************************* \n *Willkommen beim Lotto Tipp Generator* \n ********************************************* \n";
	}

	/**
	 * Bekommt einen Modus �bergeben und f�gt diesen in einen festen String ein.
	 * Gibt diesen String zur�ck.
	 * 
	 * @param modus
	 * @return
	 */
	public static String modusGewaehlt(String modus) {
		logger.log(Level.INFO, "Der Nutzer wurde benachrichtigt, dass " + modus + " als Modus ausgew�hlt wurde.");
		return (modus + " wurde als Lottoart gew�hlt. \n ");

	}

	/**
	 * Gibt einen String zur�ck, in welchem dem Nutzer gesagt wird wie er die
	 * HIlfeausgabe auf der Konsole aufrufen kann.
	 * 
	 * @return
	 */
	public static String hilfeBefehl() {
		logger.log(Level.INFO, "Dem Nutzer wurde die Information wie die Hilfe aufgerufen wird ausgegeben.");
		return "'h' eingeben um eine Liste mit Befehlen ausgegeben zu bekommen.\n";

	}

	/**
	 * Gibt die einen String zur�ck, welcher die Aufforderung ein Spielmodus zu
	 * w�hlen enth�lt, sowie welche Spielmodi zur Auswahl stehen.
	 * 
	 * @return
	 */
	public static String waehleSpiel() {
		logger.log(Level.INFO, "Dem Nutzer wurden die Informationen zur Modusauswahl ausgegeben.");
		return "Bitte w�hlen Sie das gew�nschte Lottospiel: \n 6aus49 \n Euro \n";
	}

	/**
	 * Gibt einen String zur�ck, welcher den Nutzer Informiert, das eine ung�ltige
	 * Eingabe get�tigt wurde.
	 * 
	 * @return
	 */
	public static String ungueltigeEingabe(String eingabe) {
		logger.log(Level.INFO,
				"Der Nutzer wurde darauf hingewiesen, dass eine ung�ltige Eingabe get�tigt wurde. Dem Nutzer wurde ein Hinwei� auf den Hilfebefehl gegeben.");
		return ("Ung�ltige Eingabe get�tigt: " + eingabe + "\n" + hilfeAusgeben() + "\n");

	}

	/**
	 * Gibt einen String zur�ck, welcher die Information enth�lt, das die �bergebene
	 * Zahl nicht aus der Menge an Zahlen gel�scht werden kann. Unterscheidet
	 * hierbei zwischen: Auserhalb des g�ltigen Zahlenbereiches und zahl entweder
	 * bereits entfernt oder die maximal 6 zul�ssigen zahlen wurden bereits
	 * entfernt.
	 * 
	 * @param zahl
	 */
	public static String nichtLoeschbar(int zahl) {
		if (zahl > 50 || zahl < 1) { // F�r den Fall das die Zahl au�erhalb des g�ltigen Bereichs liegt.
			logger.log(Level.INFO, "Der Nutzer wurde benachrichtigt das " + zahl + " nicht im g�ltigem Bereich liegt.");
			return ("L�schen von " + zahl + " nicht m�glich!\n "
					+ "Die zu l�schende Zahl muss im Bereich [1,50] liegen.\n");

		} else { // F�r alle anderen F�lle
			logger.log(Level.INFO,
					"Der Nutzer wurde benachrichtigt, dass das L�schen von " + zahl + " nicht m�glich ist.");
			return ("L�schen von " + zahl + " nicht m�glich!\n"
					+ "Entweder wurden bereits die Maximal 6 zul�ssigen Zahlen entfernt oder die gew�nschte Zahl ist bereits entfernt.\n");
		}
	}

	/**
	 * Gibt einen String zur�ck welcher die Aufforderung zur Eingabe eines Befehls
	 * enth�lt. F�hrt in der n�chstfolgenden Zeile hilfeBefehl() aus.
	 */
	public static String erwarteBefehl() {
		logger.log(Level.INFO, "Der Nutzer wurde zur Eingabe eines Befehls aufgefordert.");
		return ("Bitte einen Befehl eingeben:\n" + hilfeBefehl());
	}

	/**
	 * Gibt einen String mit der Aufforderung zur Eingabe von Zahlen zur�ck.
	 */
	public static String zahlEingeben() {
		return "Bitte die Zahlen mit leerzeichen getrennt eingeben:\n";
	}

	/**
	 * Gibt einen fertigen String mit allen Konsolen-Befehlen zur�ck.
	 */
	public static String hilfeAusgeben() {
		logger.log(Level.INFO, "List mit Befehlen ausgegeben..");
		return ("Die m�glichen Befehle sind:\n"
				+ " 'tippgen <6aus49/euro> <Tippanzahl>': Erzeugt einen Tipp im angegebenen Modus, die angegebene Anzahl an Mal.\n"
				+ " Die Eingaben werden durch ein leerzeichen getrennt.\n"
				+ " 'reset': Sammlung mit ausgeschlossenen Zahlen wird zur�ckgesetzt.\n "
				+ "'delete <Zahlen>': Erm�glicht das Entfernen einer oder mehrerer Zahlen aus der Menge an Zahlen zur Tippgenerierung.\n"
				+ " Die Zahlen werden durch ein leerzeichen getrennt.\n "
				+ "'readd <Zahlen>': Erm�glicht das Entfernen einer oder mehrerer Zahlen aus der Menge an ausgeschlossenen Zahlen.\n"
				+ " Die Zahlen werden durch ein leerzeichen getrennt.\n"
				+ "'list': Gibt eine Liste mit allen aktuell ausgeschlossenen Zahlen aus.\n"
				+ "'quit': Beendet das Programm ordnungsgem��.\n");

	}

	/**
	 * Gibt einen String zur�ck, dass die �bergebene Zahl erfolgreich gel�scht
	 * wurde.
	 * 
	 * @param zahl
	 * @param string
	 * @return
	 */
	public static String erfolgreichEntfernt(int zahl) {
		logger.log(Level.INFO, "�ber erfolgreiches entfernen der Zahl: " + zahl + " benachrichtigt.");
		return (zahl + " erfolgreich entfernt.\n");
	}

	/**
	 * Gibt einen String zur�ck, dass die �bergebene Zahl erfolgreich wieder
	 * hinzugef�gt wurde.
	 * 
	 * @param zahl
	 */
	public static String erfolgreichWiederHinzugefuegt(int zahl) {
		logger.log(Level.INFO, "�ber erfolgreiches wieder hinzuf�gen der Zahl: " + zahl + " benachrichtigt.");
		return (zahl + " erfolgreich wieder hinzugef�gt.\n");
	}

	/**
	 * Gibt einen String zur�ck, der enth�lt, das eine Datei zum abspeichern der
	 * entfernten Zahlen erzeugt wurde.
	 */
	public static String dateiErstellt() {
		logger.log(Level.INFO, "�ber erfolgreiches erstellen einer neuen Datei benachrichtigt.");
		return "Es existierte noch keine Datei zum abspeichern der entfernten Zahlen. Diese Datei wurde mit dem Namen 'TippGenerator.txt' erstellt.\n";

	}

	/**
	 * Gibt einen String zur�ck, der dar�ber informiert, das die �bergebene Zahl
	 * keine Zahl ist. Gibt dabei auch den G�ltigen Zahlenbereich.
	 * 
	 * @param input
	 */
	public static String istKeineZahl(String input) {
		logger.log(Level.INFO, "Nachricht ausgegeben, dass " + input + " keine Zahl ist.");
		return (input + " ist keine Zahl und wird daher ignoriert.\n");
	}

	/**
	 * Gibt einen String zur�ck, welcherden Nutzer informiert, dass eine der
	 * eingegebenen Zahlen nicht wieder hinzugef�gt werden konnte.
	 * 
	 * @param zahl
	 */
	public static String hinzufuegenNichtMoeglich(int zahl) {
		if (zahl > 50 || zahl < 1) {
			logger.log(Level.INFO, "Der Nutzer wurde benachrichtigt das " + zahl + " nicht im g�ltigem Bereich liegt.");
			return (zahl + " konnte nicht wieder hinzugef�gt werden.\n"
					+ "Die eingegebene Zahl muss im Bereich [1,50] liegen.\n");
		} else {
			logger.log(Level.INFO, "Der Nutzer wurde benachrichtigt, dass das L�schen von " + zahl
					+ " nicht m�glich ist. Da " + zahl + " keine der aktuell ausgeschlossenen Zahlen ist.");
			return (zahl + " konnte nicht wieder hinzugef�gt werden.\n"
					+ " Die Zahl ist aktuell nicht unter den ausgeschlossenen Zahlen.\n");
		}
	}

	/**
	 * Gibt eine Mittteilung aus, das die folgenden Zahlen ausgeschlossene Zahlen
	 * sind.
	 * 
	 * @return
	 */
	public static String list() {
		logger.log(Level.INFO, "list() ausgef�hrt.");
		return "Die derzeitig ausgeschlossenen Zahlen sind:\n";
	}

	/**
	 * Gibt einen String zur�ck, welcher den Nutzer darauf hinweist das ung�ltige
	 * Eingaben get�tig wurden. Nennt dem Nutzer auch diese ungl�ltigen Eingaben und
	 * gibt dem Nutzer die Hilfe ausgabe aus.
	 * 
	 * @return
	 */
	public static String ungueltigeEingabe(ArrayList<String> tippgen) {
		StringBuilder sb = new StringBuilder();
		for (String string : tippgen) {
			if (ProgramFlow.istNumerisch(string)) {
				continue;
			} else {
				if (sb.equals(null)) {
					sb.append("Ung�ltige Eingaben:\n" + string + "\n");
				} else {
					sb.append(string + "\n");
				}
			}
			sb.append(hilfeAusgeben());
		}
		logger.log(Level.INFO, "Der Nutzer wurde darauf hingewiesen, dass eine ung�ltige Eingabe get�tigt wurde."
				+ " Dem Nutzer wurde ein Hinwei� auf den Hilfebefehl gegeben.");
		return sb.toString();
	}

	/**
	 * Methode zur Ausgabe einer Benachrichtigung an der Nutzer das ein �bergebener
	 * Befehl ung�ltige Strings enthielt und das diese ignoriert wurden
	 * 
	 * @param tippgen
	 */
	public static String ignorierterBefehl(ArrayList<String> tippgen) {
		StringBuilder sb = new StringBuilder();
		sb.append("Der Eingegebene Befehl enthielt ung�ltige Teile, welche ignoriert werden.\n");
		for (String string : tippgen) {
			sb.append(string + " ");
		}
		return sb.toString();
	}

	/**
	 * Gibt einen String zur�ck, dass derzeit keine Zahlen von der Tippgenerierung
	 * ausgeschlossen sind.
	 * 
	 * @return
	 */
	public static String keineZahlenAusgeschlossen() {
		return "Derzeit sind keine Zahlen von der Tippgenerierung ausgeschlossen.\n";
	}

	/**
	 * Methode um einen String mit der aktuellen Liste an ausgeschlossenen Zahlen zu
	 * erzeugen.
	 * 
	 * @param tippgenerator
	 */
	public static String ausgeschlosseneZahlenListe(TippGenerator tippgenerator) {
		return tippgenerator.liste().toString();
	}

	/**
	 * Methode um einen 6aus49 Tipp als String zu erhalten.
	 * 
	 * @param tipp
	 */
	public static String sechsAusNeunundvierzigTipp(ArrayList<Integer> tipp) {
		return ("6aus49: " + tipp + System.lineSeparator());
	}

	/**
	 * Methode um einen Eurojackpot Tipp als String zu erhalten.
	 * 
	 * @param tipp
	 * @param zweiAusZehnTipp
	 */
	public static String eurojackpotTipp(ArrayList<Integer> tipp, ArrayList<Integer> zweiAusZehnTipp) {
		return ("5aus50: " + tipp + System.lineSeparator() + "2aus10: " + zweiAusZehnTipp + System.lineSeparator());
	}

	public static String nichtKorrektBeendet() {
		return ("Das Programm wurde beim letzten mal nicht korrekt beendet! \n"
				+ " Sollte das Programm nicht korrekt starten, so m�ssen 'LottoTippGeneratorLog.lck' Datei(en) manuell gel�scht werden.");
	}
}
