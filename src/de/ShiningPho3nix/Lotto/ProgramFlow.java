package de.ShiningPho3nix.Lotto;

import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Die Klasse ProgrammFlow ist f�r den allgemeinen Programmablauf zust�ndig,
 * sowie f�r die Auswertung und weitergabe von eingegebenen Befehlen.
 * 
 * @author Steffen Dworsky
 *
 */
public class ProgramFlow {

	private final static Logger logger = LogManager.getLogger(ProgramFlow.class);
	private Benutzereingabe benutzereingabe;
	private TippGenerator tippgenerator = null;

	/**
	 * Konstruktor erzeugt eine neue Benutzereingabe um Eingaben vom Nutzer �ber die
	 * Methoden von Benutzereingabe zu erhalten. Erzeugt auch Tippgenerator Objekt
	 * um einige Funktionen immer gew�hrleisten zu k�nnen.
	 */
	public ProgramFlow() {
		benutzereingabe = new Benutzereingabe();
		tippgenerator = new TippGenerator(new SechsAusNeunundvierzig());
		// tippgenerator wird mit SechsAusNeunundvierzig inizialisiert. Wenn keine
		// genauen Angaben zum gew�nschten Speilmodus gemacht werden, so wird 6aus49
		// verwendet.
		logger.info("ProgramFlow Konstruktor durchgelaufen");
	}

	/**
	 * Begr��t den User und f�hrt einen �bergebenen Befehl mit befehlAusfuehren()
	 * aus.
	 * 
	 */
	public void start(String args) {
		System.out.println(StringSammlung.begruessungConsole());
		System.out.println(StringSammlung.hilfeBefehl());
		befehlAusfuehren(args);
	}

	/**
	 * Funktion bekommt einen Befehl �bergeben. Sorgt f�r die Ausf�hrung des Befehls
	 * an dem aktuellem TippGenerator Objekt. Gibt in einigen F�llen den Befehl
	 * weiter an einzelne Funktionen
	 * 
	 * @param befehl
	 */
	private void befehlAusfuehren(String befehl) {
		if (befehl.contains("TIPPGEN")) { // Nimmt eine erste Aufteilung des eingegebenen Befehls vor und f�hrt geg.
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
			case "RESET": // Sorgt daf�r das es keine gesperrten Zahlen mehr gibt
				logger.info("Sammlung mit ausgeschlossenen Zahlen wird zur�ckgesetzt.");
				tippgenerator.reset();
				befehlAusfuehren("LIST"); // Gibt eine Liste mit den Entfernten Zahlen aus.
				logger.info("Befehl 'reset' ausgef�hrt");
				break;
			case "LIST": // Sorgt f�r die Ausgabe einer Liste der gesperrten/ausgeschlossenen zahlen
							// oder, falls es keine gibt, f�r die Ausgabe einer Nachricht.
				System.out.println(StringSammlung.list());
				if (tippgenerator.liste().isEmpty()) {
					System.out.println(StringSammlung.keineZahlenAusgeschlossen());
				} else {
					System.out.println(StringSammlung.ausgeschlosseneZahlenListe(tippgenerator));
				}
				System.out.println("");
				logger.info(tippgenerator.liste().toString() + " als ausgeschlossene Zahlen dem Nutzer ausgegeben.");
				logger.info("Befehl 'list' ausgef�hrt");
				break;
			case "H": // Sorgt daf�r das eine Liste mit Befehlen ausgegeben wird
				System.out.println(StringSammlung.hilfeAusgeben());
				logger.info("Befehl 'h' ausgef�hrt");
				break;
			case "QUIT": // Sorgt f�r ein korrektes beendes des Programms
				logger.info("Befehl 'quit' ausgef�hrt");
				System.exit(0);
				break;
			default:
				logger.info("Ung�ltige Eingabe wurde als Befehl �bergeben. Neuer Befehl wird angefragt.");
				System.out.println(StringSammlung.ungueltigeEingabe(befehl));
				break;
			}
		}
		befehlAusfuehren(benutzereingabe.erwarteBefehl(new InputStreamReader(System.in))); // Wenn ein befehl ausgef�hrt
																							// wurde wird hier der
																							// n�chste Befehl angefragt.
	}

	/**
	 * Methode zum Ausf�hren des Readd Befehls. Bekommt den befehl readd zusammen
	 * mit einer oder mehreren zahlen �bergeben. Entfernt dann readd und packt alle
	 * zahlen im verbleibenden String in ein integer[]. Die zahlen im integer[]
	 * werden dann mithilfe der Methode entferneUnglueckszahlen von Tippgenerator
	 * aus dem ungl�ckszahlenArray entfernt.
	 * 
	 * @param befehl
	 */
	private String befehlAusfuehrenReadd(String befehl) {
		String readd = befehl.replace("READD ", ""); // Entsorgt den substring READD, sodass bei korrekter Eingabe des
														// Befehls nur noch zahlen �brig bleiben.
		Tuple rueckgabe = Benutzereingabe.verarbeiteZahlen(new StringReader(readd));
		Integer[] readdZahlen = rueckgabe.getIntegerArr();
		// Die Zahlen werden als StringReader an die Methode erfrageLottozahlen
		// �bergeben, da diese Methode bereits alles n�tige hat um einen Zahlen String
		// in ein Integer Array umzuwandeln.
		logger.info(readdZahlen + " als Zahlen welche wieder hinzugef�gt werden sollen �bergeben.");
		logger.info("Befehl 'readd' ausgef�hrt");
		return tippgenerator.unglueckszahlWiederZulassen(readdZahlen);
	}

	/**
	 * Methode zum Ausf�hren des delete Befehls. Bekommt den befehl delete zusammen
	 * mit einer oder mehreren zahlen �bergeben. Entfernt dann delete und packt alle
	 * zahlen im verbleibenden String in ein integer[]. Die zahlen im integer[]
	 * werden dann mithilfe der Methode entferneZahlen von Tippgenerator aus dem
	 * tippzahlenarray entfernt und in das ungl�ckszahlenArray gepackt.
	 * 
	 * @param befehl
	 */
	private String befehlAusfuehrenDelete(String befehl) {
		String delete = befehl.replace("DELETE ", ""); // Entsorgt den substring DELETE, sodass bei korrekter Eingabe
														// des Befehls nur noch zahlen �brig bleiben.
		Tuple rueckgabe = Benutzereingabe.verarbeiteZahlen(new StringReader(delete));
		Integer[] deleteZahlen = rueckgabe.getIntegerArr();
		// Die Zahlen werden als StringReader an die Methode erfrageLottozahlen
		// �bergeben, da diese Methode bereits alles n�tige hat um einen Zahlen String
		// in ein Integer Array umzuwandeln.
		logger.info(deleteZahlen + " als auszuschlie�ende Zahlen �bergeben.");
		logger.info("Befehl 'delete' ausgef�hrt");
		return tippgenerator.neueUnglueckszahlAusschliessen(deleteZahlen);
	}

	/**
	 * Funktion zur ausf�hrung des befehls tippgen. Bekommt Tippgen mit dem
	 * optionalem String 6aus49, euro oder "" �bergeben, sowie einer optionalen
	 * zahl. Entfernt den substring tippgen und erzeug je nach folgestring ein
	 * tippgeneratorobjekt. Dieser Folgestring wird dann auch entfernt. Anschli�end
	 * wird starteTippgen ausgef�hrt und bekommt den restlichen string �bergeben.
	 * 
	 * @param befehl
	 */
	private void befehlAusfuehrenTippgen(String befehl) {
		ArrayList<String> tippgen = new ArrayList<String>(Arrays.asList(befehl.split(" ")));
		tippgen.remove("TIPPGEN"); // Entsorgt den substring TIPPGEN, sodass bei korrekter Eingabe
									// des Befehls nur noch zahlen �brig bleiben.
									// Pr�ft ob mit dem Befehl Tippgen auch ein modus �bergeben wurde. Erzeugt
									// dementsprechend Objekte
		if (tippgen.contains("6AUS49")) { // Wenn 6aus49 als gew�nschter Modus �bergeben wird...
			tippgen.remove("6AUS49");
			if (!tippgenerator.lottoModus().equals("6aus49")) { // ... und das Tippgenerator Objekt nicht 6aus49 ist ...
				tippgenerator = new TippGenerator(new SechsAusNeunundvierzig()); // ... wird ein neues 6aus49
																					// Objekt
				// erzeugt.
				logger.info(
						"6AUS49 wurde als Lottomodus gew�hlt. Da tippgenerator kein 6aus49 Objekt enthielt, wurde ein neues erzeugt.");
			} else { // Ist das Objekt 6aus49 muss nichts weiter unternommen werden.
				logger.info(
						"6AUS49 wurde als Lottomodus gew�hlt. Da tippgenerator ein 6aus49 Objekt enthielt, wurde kein neues erzeugt.");
			}
		} else if (tippgen.contains("EURO")) { // Genau wie bei 6aus49, nur mit Euro.
			tippgen.remove("EURO");
			if (!tippgenerator.lottoModus().equals("Euro")) {
				tippgenerator = new TippGenerator(new Eurojackpot());
				logger.info(
						"EURO wurde als Lottomodus gew�hlt. Da tippgenerator kein Euro Objekt enthielt, wurde ein neues erzeugt.");
			} else {
				logger.info(
						"EURO wurde als Lottomodus gew�hlt. Da tippgenerator ein Euro Objekt enthielt, wurde kein neues erzeugt.");
			}

		} else if (tippgen.isEmpty()) { // Wird kein Modus �bergeben, so wird 6aus49 verwendet.
			if (!tippgenerator.lottoModus().equals("6aus49")) {
				tippgenerator = new TippGenerator(new SechsAusNeunundvierzig());
			}
			logger.info(
					"Tippgenerator wurde als Befehl gew�hlt. Es wurde kein Modus �bergeben, daher wird 6aus49 verwendet.");
		} else if (!tippgen.isEmpty() && !istNumerisch(tippgen.get(0))) { // Sollte kein Modus �bergeben werden, der
																			// String aber noch nicht leer sein und der
																			// String keine Zahl sein (um mehrfach tipps
																			// zu generieren), so wird der Befehl
																			// ignoriert.
			System.out.println(StringSammlung.ignorierterBefehl(tippgen));
		} else {
			System.out.println(StringSammlung.ungueltigeEingabe(tippgen)); // Sollte eine ung�ltige Eingabe im Befehl
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
	 * bekommt einen String �bergeben und pr�ft ob dieser leer ist. falls dieser
	 * leer ist wird generiereTipp am aktuellem tippgenerator objekt ausgef�hrt. ist
	 * der String nicht leer so wird gepr�ft ob dieser eine Zahl enth�lt. Enth�lt er
	 * eine Zahl so wird generiereTipps ausef�hrt und bekommt die Zahl als int
	 * �bergeben.
	 * 
	 * @param tippgen
	 */
	private String starteTippgenerierung(ArrayList<String> tippgen) {
		if (tippgen.isEmpty()) {
			return tippgenerator.generiereTipps(1);
		} else {
			for (String string : tippgen) { // Sollte der String Tippgen nicht leer sein, so wird gepr�ft ob darunter
											// auch eine Zahl ist. Diese wird anschlie�end f�r die Anzahl an Tipps zur
											// tippgenerierung verwendet.
				if (istNumerisch(string)) { // Der String wird hier gepr�ft und entweder wird generiereTIpps mit der
											// eingegebenen Zahl ausgef�hrt oder generiereTipp.
					try {
						int quicktipp = Integer.parseInt(string);
						return tippgenerator.generiereTipps(quicktipp);
					} catch (NumberFormatException e) {
						logger.info(
								"�bergebene Zahl f�r die Anzahl an Tipps welche generiert werden sollen, ist scheinbar keine Zahl. Es wird ein einziger Tipp generiert. �bergeben wurde: "
										+ string,
								e);
					}
				}
			}
			return tippgenerator.generiereTipps(1);
		}
	}

	/**
	 * Pr�ft ob ein String nur aus Zahlen besteht. 'Whitespace' wird ignoriert.
	 * 
	 * @param String
	 * @return true wenn der �bergebene String nur aus Zahlen besteht.
	 */
	public static boolean istNumerisch(String str) {
		for (char c : str.toCharArray()) {
			if (Character.isWhitespace(c)) { // Leerzeichen werden ignoriert
				continue;
			}
			if (!Character.isDigit(c)) // Sobald ein Char im String keine Zahl ist wird false zur�ckgegeben.
				return false;
		}
		return true;
	}
}
