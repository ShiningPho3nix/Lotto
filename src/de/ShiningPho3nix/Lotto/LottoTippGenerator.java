package de.ShiningPho3nix.Lotto;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Main Klasse zum Starten des Programms
 * 
 * @author Steffen Dworsky
 *
 */
public class LottoTippGenerator {

	private final static Logger logger = LogManager.getLogger(LottoTippGenerator.class);
	private static ProgramFlow programFlow;

	/**
	 * Main Methode bereitet das Loggen vor, erzeugt ein Objekt Ausgabe und
	 * ProgrammFlow. Wanderlt String[] args ind einen String um und ¸bergibt diesen
	 * an die start() Methode von programmflow.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		logger.info("Programm gestartet.");
		programFlow = new ProgramFlow();

		String befehl = "";
		befehl = Arrays.stream(args).collect(Collectors.joining(" ")); // Erzeugt aus dem String Array args einen
																		// String, die einzelnen Eintr‰ge des Arrays
																		// werden durch ein Leerzeichen getrennt
		if (befehl != null && befehl.length() > 0 && befehl.charAt(befehl.length() - 1) == ' ') {
			befehl = befehl.substring(0, befehl.length() - 1); // Da die Eintr‰ge des Array gejoind werden mit einem
																// Leerzeichen, hat der resultierende String am Ende
																// immer ein Leerzeichen. Dies wird durch diese Zeile
																// entfernt.
		}

		befehl = befehl.toUpperCase(); // Nahezu alle eingaben werden zu Uppercase umgewandelt, um
										// groﬂ-/kleinschreibung
										// duch den nutzer zu ignorieren.
		programFlow.start(befehl);
	}
}
