package de.ShiningPho3nix.Lotto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * In dieser Klasse finden sich diverse Methoden um Daten abzuspeichern, zu
 * laden und auf korrektheit zu �berpr�fen.
 * 
 * @author Steffen Dworsky
 *
 */
public class FileOperation {

	private final static Logger logger = LogManager.getLogger(FileOperation.class);

	/**
	 * Die Funktion speichern bekommt eine zu speichernde ArrayList �bergeben und
	 * speichert diese in einer Datei ab. Vor dem Abspeichern wird checkArray
	 * ausgef�hrt. Ein Ordner f�r die Dateien wird in dem aktuellen Verzeichniss
	 * gegebenenfalls erzeugt.
	 * 
	 * @param unglueckszahlen welche abgespeichert werden sollen
	 * @param fileName        in welcher die zahlen gespeichert werden sollen.
	 */
	public static void speichern(ArrayList<Integer> unglueckszahlen, String fileName) {
		String filePath = currentDirectory().concat("\\LottoTippGenFiles\\" + fileName);
		File file = new File(filePath);
		try {
			file.getParentFile().mkdirs();
			createFile(filePath);
			logger.info("Datei wurde erzeugt (" + filePath + ").");
		} catch (IOException e) {
			logger.error("Die Datei konnte nicht erstellt werden (" + filePath + ").", e);
			e.printStackTrace();
		}
		try (PrintWriter out = new PrintWriter(file)) {
			try {
				unglueckszahlen = checkArray(unglueckszahlen);
				// ArrayList wird vor dem Abspeichern nochmals gepr�ft.
			} catch (ClassCastException e) {
				logger.error("zuSpeicherdeDaten lies sich nicht zu einem Array casten", e);
				e.printStackTrace();
			}
			out.write(unglueckszahlen.toString());
			logger.info(unglueckszahlen.toString() + " wurde in die Datei geschrieben.");
			out.close();
		} catch (FileNotFoundException e) {
			logger.warn("Es konnte keine Datei gefunden werden!", e);
			e.printStackTrace();
		}
	}

	/**
	 * Die Funktion speichern bekommt einen zu speichernden String an generierten
	 * Tipps �bergeben und speichert diese in einer Datei ab. Ein Ordner f�r die
	 * Dateien wird in dem aktuellen Verzeichniss gegebenenfalls erzeugt.
	 * 
	 * @param tipps    welche in der Datei abgespeichert werden sollen
	 * @param fileName in welcher die Tipps abgespeichert werden sollen.
	 */
	public static void speichern(String tipps, String fileName) {
		String filePath = currentDirectory().concat("\\LottoTippGenFiles\\" + fileName);
		File file = new File(filePath);
		try {
			file.getParentFile().mkdirs();
			createFile(filePath);
			logger.info("Datei wurde erzeugt (" + filePath + ").");
		} catch (IOException e) {
			logger.error("Die Datei konnte nicht erstellt werden (" + filePath + ").", e);
			e.printStackTrace();
		}
		try (PrintWriter out = new PrintWriter(file)) {
			out.write(tipps);
			logger.info("tipps wurden in die Datei geschrieben.");
			out.close();
		} catch (FileNotFoundException e) {
			logger.warn("Es konnte keine Datei gefunden werden!", e);
			e.printStackTrace();
		}
	}

	/**
	 * Die Funktion laden l�dt die ausgeschlossenen Zahlen aus der Datei. Die
	 * einzelnen Zahlen werden dann in ein lokales Array gelegt und anschlie�end
	 * �bergeben. Sollte keine Datei existieren wird eine neue Datei erzeugt und das
	 * Array leer �bergeben. Befor das Array �bergeben wird, wird es noch auf
	 * korrektheit gepr�ft mit checkArray
	 * 
	 * @return Eine ArrayList mit den aus der Datei geladenen ungl�ckszahlen.
	 */
	public static ArrayList<Integer> laden() {

		String filePath = currentDirectory().concat("\\LottoTippGenFiles\\Unglueckszahlen.txt"); // Durch
																									// currentDirectory
																									// mit angeh�ngtem
		// Namen der Datei wird hier der Absolute
		// Path als String festgelegt.
		File file = new File(filePath); // Der oben gefundene filepath wird hier zum erzeugen eines neuen file objektes
										// genutzt.
		String ladeString = ""; // sollten keine Zahlen geladen werden, so wird der String unver�ndert returned,
								// daher wird dieser als leerer String inizialisiert.
		ArrayList<Integer> ladeList = new ArrayList<Integer>(); // Um die geladenen Zahlen zwischen zu speichern
																// und zu
																// returnen

		try {
			file.getParentFile().mkdirs(); // Hiermit werden alles parent directories erzeugt.
			createFile(filePath);
			logger.info("Es wurde eine Datei f�r zum Speichern der Ungl�ckszahlen erzeugt.");
		} catch (IOException e) {
			logger.error("Die Datei konnte nicht erstellt werden (" + filePath + ")", e);
			e.printStackTrace();
		}

		try {
			BufferedReader br = new BufferedReader(new FileReader(file)); // Zum einlesen der Informationen aus der
																			// Datei wird eine BufferedReader verwendet.
			ladeString = br.readLine();
			if (ladeString == null || ladeString.equals("")) { // Sollte nichts eingelesen werden, wird die ArrayListe
																// leer zur�ckgegeben.
				logger.info("Keine ausgeschlossenen Zahlen in der Datei gefunden. Leeres Array wird �bergeben.");
				br.close();
				return ladeList;
			}
			ladeString = ladeString.replace("[", "").replace("]", "").replace(" ", ""); // Da die ArrayList beim
																						// Abspeichern direkt in die
																						// Datei geschrieben wird, hat
																						// sie das Format [1, 2, 3,].
																						// Die unn�tigen zeichen werden
																						// daher entfernt.
			logger.info(ladeString + " wurden als ausgeschlossene Zahlen aus der Datei gelesen.");
			br.close();
		} catch (IOException e) {
			logger.error("Die Datei konnte nicht gelesen werden!", e);
			e.printStackTrace();
		}
		String[] partArray = ladeString.split(","); // Die Kommas bleiben und werden hier zum trennen der Zahlen
													// verwendet.
		for (String string : partArray) {
			if (string.equals("")) {
				continue;
			}
			try {
				ladeList.add(Integer.parseInt(string)); // Sollte die Datei Manipuliert worden sein oder auf
														// irgendeinem anderen wege nicht-Zahlen in die
														// Textdatei geraten sein, so werden diese hier
														// rausgefiltert.
			} catch (NumberFormatException e) {
				logger.warn(string + " ist keine Zahl und wird daher ignoriert", e);
				continue;
			}
		}
		logger.info(
				"Ausgeschlossene Zahlen wurden erfolgreich aus der Datei gelesen und werden nun als Array �bergeben.");
		return checkArray(ladeList); // Zum abschluss werden noch einige anforderungen gepr�ft und die ArrayList
										// dann zur�ckgegeben.
	}

	/**
	 * Funktion um den aktuellen Verzeichnisspfad als String ausgegeben zu bekommen
	 * 
	 * @return Den aktuellen Verzeichnisspfad
	 */
	public static String currentDirectory() {
		Path currentRelativePath = Paths.get("");
		return currentRelativePath.toAbsolutePath().toString();
	}

	/**
	 * Sollte noch keine Datei existieren, kann mit dieser Methode eine neue Datei
	 * erzeugt werden. Die Methode passt den Dateinamen dabei dem aktuellen Modus
	 * an.
	 * 
	 */
	private static void createFile(String name) throws IOException {
		File f = new File(name);
		f.createNewFile();
	}

	/**
	 * Methode um Arrays auf korrektheit zu pr�fen. F�hrt die drei Methoden
	 * ung�ltigeWerte, doppelteZahlen und mehrAlsSechsWerte in dieser Reihenfolge
	 * aus.
	 * 
	 * @param ungluecksZahlenArray welches gepr�ft werden soll
	 * @return Ein �berpr�ftes und korrigiertes Array ohne ung�ltige Werte.
	 */
	public static ArrayList<Integer> checkArray(ArrayList<Integer> ungluecksZahlenArray) {
		ArrayList<Integer> ergebnisList = new ArrayList<Integer>();
		ergebnisList = mehrAlsSechsWerte(doppelteZahlen(ungueltigeWerte(ungluecksZahlenArray)));
		// �berpr�ft mithilfe von 3 Methoden Bedingungen auf korrektheit. Zun�chst
		// werden alle Werte au�erhalb des g�ltigen Zahlenbereiches entfernt. Dannach
		// wird gepr�ft ob Zahlen doppelt enthalten sind. Sollten es danach mehr als 6
		// sein, werden nur die ersten 6 verwendet.
		return ergebnisList;
	}

	/**
	 * Pr�ft ob das Ungl�ckszahlenArray Doppelte Zahlen enth�lt, z.B. wenn die
	 * Textdatei in welcher die Zahlen gespeichert werden Manipuliert wurde.
	 * 
	 * @param ungluecksZahlenArray welches auf doppelte Zahlen gepr�ft werden soll.
	 * @return Ein �berpr�ftes und korrigiertes Array ohne doppelte Zahlen.
	 */
	public static ArrayList<Integer> doppelteZahlen(ArrayList<Integer> ungluecksZahlenArray) {
		ArrayList<Integer> ergebnisList = new ArrayList<Integer>();
		for (Integer integer : ungluecksZahlenArray) {
			if (!ergebnisList.contains((Integer) integer)) { // Ist die aktuelle Zahl noch nicht im ergebnisList, so
																// wird die zahl hinzugef�gt. Kommt die selbe zahl
																// nochmals vor, so wird die ignoriert.
				ergebnisList.add(integer);
			}
		}
		logger.info("Die ungl�ckszahlen wurden auf doppelte Zahlen gepr�ft.");
		return ergebnisList;
	}

	/**
	 * Methode Pr�ft ob das �bergebene Array ung�ltige Werte, in diesem Fall Zahlen
	 * die kleiner als 1 oder gr��er als 50 sind enth�lt und entfernt diese.
	 * 
	 * @param ungluecksZahlenArray welches auf ung�ltige werte z.b. buchstaben
	 *                             gepr�ft werden soll.
	 * @return Ein �berpr�ftes und korrigiertes Array ohne ung�ltige Werte wie z.b.
	 *         Buchstaben
	 */
	public static ArrayList<Integer> ungueltigeWerte(ArrayList<Integer> ungluecksZahlenArray) {
		ArrayList<Integer> ergebnisList = new ArrayList<Integer>();
		for (Integer integer : ungluecksZahlenArray) {
			if (integer > 0 && integer < 51) { // Bei 6aus49 d�rfen Zahlen im Bereich 1-49 liegen, bei Eurojackpot 1-50.
												// Ungl�ckszahlen sollen f�r beide gelten, somit in der gesamtbereich
												// 1-50.
				ergebnisList.add(integer);
			}
		}
		logger.info("Die ungl�ckszahlen wurden auf ung�ltige Werte gepr�ft.");
		return ergebnisList;
	}

	/**
	 * Pr�ft ob das �bergebene Array mehr als 6 Daten enth�lt und entfernt alle
	 * �bersch�ssigen. Nach durchlauf der Methode bekommt man ein Array mit max 6
	 * eintr�gen zur�ck.
	 * 
	 * @param unglueckszahlenArray welches gepr�ft werden soll
	 * @return Eine Liste mit den ersten 6 Eintr�gen der �bergebenen Liste.
	 */
	public static ArrayList<Integer> mehrAlsSechsWerte(ArrayList<Integer> ungluecksZahlenArray) {
		ArrayList<Integer> ergebnisList = new ArrayList<Integer>();
		for (Integer integer : ungluecksZahlenArray) {
			if (ergebnisList.size() != 6) { // Es sollen max. 6 Ungl�ckszahlen zul�ssig sein, daher werden, sollten
											// sich mehr als 6 darin befinden, nur die ersten 6 Zahlen welche sich
											// im ungl�ckszahlenArray befinden verwendet.
				ergebnisList.add(integer);
			} else
				break;
		}
		logger.info("Die ungl�ckszahlen wurden auf mehr als 6 Zahlen gepr�ft.");
		return ergebnisList;
	}
}