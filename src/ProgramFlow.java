import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Die Klasse ProgrammFlow ist für den allgemeinen Programmablauf zuständig,
 * sowie für die Auswertung und weitergabe von eingegebenen Befehlen.
 * 
 * @author Steffen Dworsky
 *
 */
public class ProgramFlow {

	private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private Benutzereingabe benutzereingabe;
	public TippGenerator tippgenerator = null;

	/**
	 * Konstruktor erzeugt eine neue Benutzereingabe um Eingaben vom Nutzer über die
	 * Methoden von Benutzereingabe zu erhalten. Erzeugt auch Tippgenerator Objekt
	 * um einige Funktionen immer gewährleisten zu können.
	 */
	public ProgramFlow() {
		benutzereingabe = new Benutzereingabe();
		tippgenerator = new TippGenerator(new SechsAusNeunundvierzig());
		// tippgenerator wird mit SechsAusNeunundvierzig inizialisiert. Wenn keine
		// genauen Angaben zum gewünschten Speilmodus gemacht werden, so wird 6aus49
		// verwendet.
		logger.log(Level.INFO, "ProgramFlow Konstruktor durchgelaufen");
	}

	/**
	 * Begrüßt den User und führt einen übergebenen Befehl mit befehlAusfuehren()
	 * aus.
	 * 
	 */
	public void start(String args) {
		System.out.println(StringSammlung.begruessungConsole());
		System.out.println(StringSammlung.hilfeBefehl());
		befehlAusfuehren(args);
	}

	/**
	 * Funktion bekommt einen Befehl übergeben. Sorgt für die Ausführung des Befehls
	 * an dem aktuellem TippGenerator Objekt. Gibt in einigen Fällen den Befehl
	 * weiter an einzelne Funktionen
	 * 
	 * @param befehl
	 */
	public void befehlAusfuehren(String befehl) {
		if (befehl.contains("TIPPGEN")) { // Nimmt eine erste Aufteilung des eingegebenen Befehls vor und führt geg.
											// eine entsprechende Methode aus.
			befehlAusfuehrenTippgen(befehl);
		} else if (befehl.contains("DELETE")) {
			System.out.println(befehlAusfuehrenDelete(befehl));
		} else if (befehl.contains("READD")) {
			System.out.println(befehlAusfuehrenReadd(befehl));
		} else if (befehl.equals("")) {
			befehlAusfuehren(benutzereingabe.erwarteBefehl(new InputStreamReader(System.in)));
		} else {
			switch (befehl) { // Ist der eingegebene Befehl nicht einer der weiter oben bereits verarbeiteten,
								// so wird der Befehl in dieser Methode bearbeitet.
			case "RESET": // Sorgt dafür das es keine gesperrten Zahlen mehr gibt
				logger.log(Level.INFO, "Sammlung mit ausgeschlossenen Zahlen wird zurückgesetzt.");
				tippgenerator.reset();
				befehlAusfuehren("LIST"); // Gibt eine Liste mit den Entfernten Zahlen aus.
				logger.log(Level.INFO, "Befehl 'reset' ausgeführt");
				break;
			case "LIST": // Sorgt für die Ausgabe einer Liste der gesperrten/ausgeschlossenen zahlen
							// oder, falls es keine gibt, für die Ausgabe einer Nachricht.
				System.out.println(StringSammlung.list());
				if (tippgenerator.liste().isEmpty()) {
					System.out.println(StringSammlung.keineZahlenAusgeschlossen());
				} else {
					System.out.println(StringSammlung.ausgeschlosseneZahlenListe(tippgenerator));
				}
				System.out.println("");
				logger.log(Level.INFO,
						tippgenerator.liste().toString() + " als ausgeschlossene Zahlen dem Nutzer ausgegeben.");
				logger.log(Level.INFO, "Befehl 'list' ausgeführt");
				break;
			case "H": // Sorgt dafür das eine Liste mit Befehlen ausgegeben wird
				System.out.println(StringSammlung.hilfeAusgeben());
				logger.log(Level.INFO, "Befehl 'h' ausgeführt");
				break;
			case "QUIT": // Sorgt für ein korrektes beendes des Programms
				logger.log(Level.INFO, "Befehl 'quit' ausgeführt");
				Logging.quit();
				System.exit(0);
				break;
			default:
				logger.log(Level.INFO, "Ungültige Eingabe wurde als Befehl übergeben. Neuer Befehl wird angefragt.");
				System.out.println(StringSammlung.ungueltigeEingabe(befehl));
				break;
			}
		}
		befehlAusfuehren(benutzereingabe.erwarteBefehl(new InputStreamReader(System.in))); // Wenn ein befehl ausgeführt
																							// wurde wird hier der
																							// nächste Befehl angefragt.
	}

	/**
	 * Methode zum Ausführen des Readd Befehls. Bekommt den befehl readd zusammen
	 * mit einer oder mehreren zahlen übergeben. Entfernt dann readd und packt alle
	 * zahlen im verbleibenden String in ein integer[]. Die zahlen im integer[]
	 * werden dann mithilfe der Methode entferneUnglueckszahlen von Tippgenerator
	 * aus dem unglückszahlenArray entfernt.
	 * 
	 * @param befehl
	 */
	private String befehlAusfuehrenReadd(String befehl) {
		String readd = befehl.replace("READD ", ""); // Entsorgt den substring READD, sodass bei korrekter Eingabe des
														// Befehls nur noch zahlen übrig bleiben.
		Tuple rueckgabe = Benutzereingabe.erfrageLottoZahlen(new StringReader(readd));
		Integer[] readdZahlen = rueckgabe.getIntegerArr();
		// Die Zahlen werden als StringReader an die Methode erfrageLottozahlen
		// übergeben, da diese Methode bereits alles nötige hat um einen Zahlen String
		// in ein Integer Array umzuwandeln.
		logger.log(Level.INFO, readdZahlen + " als Zahlen welche wieder hinzugefügt werden sollen übergeben.");
		logger.log(Level.INFO, "Befehl 'readd' ausgeführt");
		return tippgenerator.unglueckszahlWiederZulassen(readdZahlen);
	}

	/**
	 * Methode zum Ausführen des delete Befehls. Bekommt den befehl delete zusammen
	 * mit einer oder mehreren zahlen übergeben. Entfernt dann delete und packt alle
	 * zahlen im verbleibenden String in ein integer[]. Die zahlen im integer[]
	 * werden dann mithilfe der Methode entferneZahlen von Tippgenerator aus dem
	 * tippzahlenarray entfernt und in das unglückszahlenArray gepackt.
	 * 
	 * @param befehl
	 */
	private String befehlAusfuehrenDelete(String befehl) {
		String delete = befehl.replace("DELETE ", ""); // Entsorgt den substring DELETE, sodass bei korrekter Eingabe
														// des Befehls nur noch zahlen übrig bleiben.
		Tuple rueckgabe = Benutzereingabe.erfrageLottoZahlen(new StringReader(delete));
		Integer[] deleteZahlen = rueckgabe.getIntegerArr();
		// Die Zahlen werden als StringReader an die Methode erfrageLottozahlen
		// übergeben, da diese Methode bereits alles nötige hat um einen Zahlen String
		// in ein Integer Array umzuwandeln.
		logger.log(Level.INFO, deleteZahlen + " als auszuschließende Zahlen übergeben.");
		logger.log(Level.INFO, "Befehl 'delete' ausgeführt");
		return tippgenerator.neueUnglueckszahlAusschliessen(deleteZahlen);
	}

	/**
	 * Funktion zur ausführung des befehls tippgen. Bekommt Tippgen mit dem
	 * optionalem String 6aus49, euro oder "" übergeben, sowie einer optionalen
	 * zahl. Entfernt den substring tippgen und erzeug je nach folgestring ein
	 * tippgeneratorobjekt. Dieser Folgestring wird dann auch entfernt. Anschlißend
	 * wird starteTippgen ausgeführt und bekommt den restlichen string übergeben.
	 * 
	 * @param befehl
	 */
	private void befehlAusfuehrenTippgen(String befehl) {
		ArrayList<String> tippgen = new ArrayList<String>(Arrays.asList(befehl.split(" ")));
		tippgen.remove("TIPPGEN"); // Entsorgt den substring TIPPGEN, sodass bei korrekter Eingabe
									// des Befehls nur noch zahlen übrig bleiben.
									// Prüft ob mit dem Befehl Tippgen auch ein modus übergeben wurde. Erzeugt
									// dementsprechend Objekte
		if (tippgen.contains("6AUS49")) { // Wenn 6aus49 als gewünschter Modus übergeben wird...
			tippgen.remove("6AUS49");
			if (!tippgenerator.lottoModus().equals("6aus49")) { // ... und das Tippgenerator Objekt nicht 6aus49 ist ...
				tippgenerator = new TippGenerator(new SechsAusNeunundvierzig()); // ... wird ein neues 6aus49 Objekt
																					// erzeugt.
				logger.log(Level.INFO,
						"6AUS49 wurde als Lottomodus gewählt. Da tippgenerator kein 6aus49 Objekt enthielt, wurde ein neues erzeugt.");
			} else { // Ist das Objekt 6aus49 muss nichts weiter unternommen werden.
				logger.log(Level.INFO,
						"6AUS49 wurde als Lottomodus gewählt. Da tippgenerator ein 6aus49 Objekt enthielt, wurde kein neues erzeugt.");
			}
		} else if (tippgen.contains("EURO")) { // Genau wie bei 6aus49, nur mit Euro.
			tippgen.remove("EURO");
			if (!tippgenerator.lottoModus().equals("Euro")) {
				tippgenerator = new TippGenerator(new Eurojackpot());
				logger.log(Level.INFO,
						"EURO wurde als Lottomodus gewählt. Da tippgenerator kein Euro Objekt enthielt, wurde ein neues erzeugt.");
			} else {
				logger.log(Level.INFO,
						"EURO wurde als Lottomodus gewählt. Da tippgenerator ein Euro Objekt enthielt, wurde kein neues erzeugt.");
			}

		} else if (tippgen.isEmpty()) { // Wird kein Modus übergeben, so wird 6aus49 verwendet.
			if (!tippgenerator.lottoModus().equals("6aus49")) {
				tippgenerator = new TippGenerator(new SechsAusNeunundvierzig());
			}
			logger.log(Level.INFO,
					"Tippgenerator wurde als Befehl gewählt. Es wurde kein Modus übergeben, daher wird 6aus49 verwendet.");
		} else if (!tippgen.isEmpty() && !istNumerisch(tippgen.get(0))) { // Sollte kein Modus übergeben werden, der
																			// String aber noch nicht leer sein und der
																			// String keine Zahl sein (um mehrfach tipps
																			// zu generieren), so wird der Befehl
																			// ignoriert.
			System.out.println(StringSammlung.ignorierterBefehl(tippgen));
		} else {
			System.out.println(StringSammlung.ungueltigeEingabe(tippgen)); // Sollte eine ungültige Eingabe im Befehl
																			// enthalten sein,
			// so wird
			// dies dem Nutzer Mitgeteilt ...
			befehlAusfuehren(benutzereingabe.erwarteBefehl(new InputStreamReader(System.in))); // ... und erneut ein
																								// Befehl erfragt.
			return;
		}

		System.out.println(starteTippgenerierung(tippgen)); // Erzeugt einen Tipp mit dem aktuellen
															// tippgenerator-Objekt.
	}

	/**
	 * bekommt einen String übergeben und prüft ob dieser leer ist. falls dieser
	 * leer ist wird generiereTipp am aktuellem tippgenerator objekt ausgeführt. ist
	 * der String nicht leer so wird geprüft ob dieser eine Zahl enthält. Enthält er
	 * eine Zahl so wird generiereTipps auseführt und bekommt die Zahl als int
	 * übergeben.
	 * 
	 * @param tippgen
	 */
	public String starteTippgenerierung(ArrayList<String> tippgen) {
		if (tippgen.isEmpty()) {
			return tippgenerator.generiereTipps(1);
		} else {
			for (String string : tippgen) { // Sollte der String Tippgen nicht leer sein, so wird geprüft ob darunter
											// auch eine Zahl ist. Diese wird anschließend für die Anzahl an Tipps zur
											// tippgenerierung verwendet.
				if (istNumerisch(string)) { // Der String wird hier geprüft und entweder wird generiereTIpps mit der
											// eingegebenen Zahl ausgeführt oder generiereTipp.
					try {
						int quicktipp = Integer.parseInt(string);
						return tippgenerator.generiereTipps(quicktipp);
					} catch (NumberFormatException e) {
						logger.log(Level.WARNING,
								"Übergebene Zahl für die Anzahl an Tipps welche generiert werden sollen, ist scheinbar keine Zahl. Es wird ein einziger Tipp generiert. Übergeben wurde: "
										+ string,
								e);
					}
				}
			}
			return tippgenerator.generiereTipps(1);
		}
	}

	/**
	 * Prüft ob ein String nur aus Zahlen besteht. 'Whitespace' wird ignoriert.
	 * 
	 * @param String
	 * @return true wenn der übergebene String nur aus Zahlen besteht.
	 */
	public static boolean istNumerisch(String str) {
		for (char c : str.toCharArray()) {
			if (Character.isWhitespace(c)) { // Leerzeichen werden ignoriert
				continue;
			}
			if (!Character.isDigit(c)) // Sobald ein Char im String keine Zahl ist wird false zurückgegeben.
				return false;
		}
		return true;
	}
}
