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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * In dieser Klasse finden sich diverse Methoden um Daten abzuspeichern, zu
 * laden und auf korrektheit zu überprüfen.
 * 
 * @author Steffen Dworsky
 *
 */
public class FileOperation {

	protected static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public FileOperation() {

	}

	/**
	 * Die Funktion speichern bekommt ein zu speicherndes Objekt übergeben und
	 * speichert das Array anschließend in einer Datei ab. Vor dem Abspeichern wird
	 * gegebenenfalls checkArray ausgeführt. Ein Ordner für die Dateien wird in dem
	 * aktuellen Verzeichniss gegebenenfalls erzeugt.
	 * 
	 * @param zuSpeicherndeDaten
	 * @param file
	 */
	@SuppressWarnings("unchecked")
	public static void speichern(Object zuSpeicherndeDaten, String fileName) {
		String filePath = currentDirectory().concat("\\LottoTippGenFiles\\" + fileName);
		File file = new File(filePath);
		try {
			file.getParentFile().mkdirs();
			createFile(filePath);
			logger.log(Level.INFO, "Datei wurde erzeugt (" + filePath + ").");
		} catch (IOException e) {
			logger.log(Level.WARNING, "Die Datei konnte nicht erstellt werden (" + filePath + ").", e);
			e.printStackTrace();
		}

		try (PrintWriter out = new PrintWriter(file)) {
			if (zuSpeicherndeDaten instanceof ArrayList<?>) {
				try {
					zuSpeicherndeDaten = checkArray((ArrayList<Integer>) zuSpeicherndeDaten);
					// Handelt es sich bei dem übergebenen Object um eine ArrayList, so wird diese
					// vor dem Abspeichern nochmals geprüft.
				} catch (ClassCastException e) {
					// TODO exception behandeln
				}
			}
			out.write(zuSpeicherndeDaten.toString());
			logger.log(Level.INFO, zuSpeicherndeDaten.toString() + " wurde in die Datei geschrieben.");
			out.close();
		} catch (FileNotFoundException e) {
			logger.log(Level.WARNING, "Es konnte keine Datei gefunden werden!", e);
			e.printStackTrace();
		}
	}

	/**
	 * Die Funktion laden lädt die ausgeschlossenen Zahlen aus der Datei. Die
	 * einzelnen Zahlen werden dann in ein lokales Array gelegt und anschließend
	 * übergeben. Sollte keine Datei existieren wird eine neue Datei erzeugt und das
	 * Array leer übergeben. Befor das Array übergeben wird, wird es noch auf
	 * korrektheit geprüft mit checkArray
	 * 
	 * @return
	 */
	public static ArrayList<Integer> laden() {

		String filePath = currentDirectory().concat("\\LottoTippGenFiles\\Unglueckszahlen.txt"); // Durch
																									// currentDirectory
																									// mit angehängtem
		// Namen der Datei wird hier der Absolute
		// Path als String festgelegt.
		File file = new File(filePath); // Der oben gefundene filepath wird hier zum erzeugen eines neuen file objektes
										// genutzt.
		String ladeString = ""; // sollten keine Zahlen geladen werden, so wird der String unverändert returned,
								// daher wird dieser als leerer String inizialisiert.
		ArrayList<Integer> ladeArrayList = new ArrayList<Integer>(); // Um die geladenen Zahlen zwischen zu speichern
																		// und zu
																		// returnen

		try {
			file.getParentFile().mkdirs(); // Hiermit werden alles parent directories erzeugt.
			createFile(filePath);
			logger.log(Level.INFO, "Es wurde eine Datei für zum Speichern der Unglückszahlen erzeugt.");
		} catch (IOException e) {
			logger.log(Level.WARNING, "Die Datei konnte nicht erstellt werden (" + filePath + ")", e);
			e.printStackTrace();
		}

		try {
			BufferedReader br = new BufferedReader(new FileReader(file)); // Zum einlesen der Informationen aus der
																			// Datei wird eine BufferedReader verwendet.
			ladeString = br.readLine();
			if (ladeString == null || ladeString.equals("")) { // Sollte nichts eingelesen werden, wird die ArrayListe
																// leer zurückgegeben.
				logger.log(Level.INFO,
						"Keine ausgeschlossenen Zahlen in der Datei gefunden. Leeres Array wird übergeben.");
				br.close();
				return ladeArrayList;
			}
			ladeString = ladeString.replace("[", "").replace("]", "").replace(" ", ""); // Da die ArrayList beim
																						// Abspeichern direkt in die
																						// Datei geschrieben wird, hat
																						// sie das Format [1, 2, 3,].
																						// Die unnötigen zeichen werden
																						// daher entfernt.
			logger.log(Level.INFO, ladeString + " wurden als ausgeschlossene Zahlen aus der Datei gelesen.");
			br.close();
		} catch (IOException e) {
			logger.log(Level.WARNING, "Die Datei konnte nicht gelesen werden!", e);
			e.printStackTrace();
		}
		String[] partArray = ladeString.split(","); // Die Kommas bleiben und werden hier zum trennen der Zahlen
													// verwendet.
		for (String string : partArray) {
			if (string.equals("")) {
				continue;
			}
			try {
				ladeArrayList.add(Integer.parseInt(string)); // Sollte die Datei Manipuliert worden sein oder auf
																// irgendeinem anderen wege nicht-Zahlen in die
																// Textdatei geraten sein, so werden diese hier
																// rausgefiltert.
			} catch (NumberFormatException e) {
				logger.log(Level.WARNING, string + " ist keine Zahl und wird daher ignoriert", e);
				continue;
			}
		}
		logger.log(Level.INFO,
				"Ausgeschlossene Zahlen wurden erfolgreich aus der Datei gelesen und werden nun als Array übergeben.");
		return checkArray(ladeArrayList); // Zum abschluss werden noch einige anforderungen geprüft und die ArrayList
											// dann zurückgegeben.
	}

	/**
	 * Funktion um den aktuellen Verzeichnisspfad als String ausgegeben zu bekommen
	 * 
	 * @return
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
	 * @throws IOException
	 */
	private static void createFile(String name) throws IOException {
		File f = new File(name);
		f.createNewFile();
	}

	/**
	 * Methode um Arrays auf korrektheit zu prüfen. Führt die drei Methoden
	 * ungültigeWerte, doppelteZahlen und mehrAlsSechsWerte in dieser Reihenfolge
	 * aus.
	 * 
	 * @param ungluecksZahlenArray
	 * @return
	 */
	public static ArrayList<Integer> checkArray(ArrayList<Integer> ungluecksZahlenArray) {
		ArrayList<Integer> ergebnisArray = new ArrayList<Integer>();
		ergebnisArray = mehrAlsSechsWerte(doppelteZahlen(ungueltigeWerte(ungluecksZahlenArray)));
		// Überprüft mithilfe von 3 Methoden Bedingungen auf korrektheit. Zunächst
		// werden alle Werte außerhalb des gültigen Zahlenbereiches entfernt. Dannach
		// wird geprüft ob Zahlen doppelt enthalten sind. Sollten es danach mehr als 6
		// sein, werden nur die ersten 6 verwendet.
		return ergebnisArray;
	}

	/**
	 * Prüft ob das UnglückszahlenArray Doppelte Zahlen enthält, z.B. wenn die
	 * Textdatei in welcher die Zahlen gespeichert werden Manipuliert wurde.
	 * 
	 * @return
	 */
	public static ArrayList<Integer> doppelteZahlen(ArrayList<Integer> ungluecksZahlenArray) {
		ArrayList<Integer> ergebnisArray = new ArrayList<Integer>();
		for (Integer integer : ungluecksZahlenArray) {
			if (!ergebnisArray.contains((Integer) integer)) { // Ist die aktuelle Zahl noch nicht im ergebnisArray, so
																// wird die zahl hinzugefügt. Kommt die selbe zahl
																// nochmals vor, so wird die ignoriert.
				ergebnisArray.add(integer);
			}
		}
		return ergebnisArray;
	}

	/**
	 * Methode Prüft ob das übergebene Array ungültige Werte, in diesem Fall Zahlen
	 * die kleiner als 1 oder größer als 50 sind enthält und entfernt diese.
	 * 
	 * @param ungluecksZahlenArray
	 * @return
	 */
	public static ArrayList<Integer> ungueltigeWerte(ArrayList<Integer> ungluecksZahlenArray) {
		ArrayList<Integer> ergebnisArray = new ArrayList<Integer>();
		for (Integer integer : ungluecksZahlenArray) {
			if (integer > 0 && integer < 51) { // Bei 6aus49 dürfen Zahlen im Bereich 1-49 liegen, bei Eurojackpot 1-50.
												// Unglückszahlen sollen für beide gelten, somit in der gesamtbereich
												// 1-50.
				ergebnisArray.add(integer);
			}
		}
		return ergebnisArray;
	}

	/**
	 * Prüft ob das übergebene Array mehr als 6 Daten enthält und entfernt alle
	 * überschüssigen. Nach durchlauf der Methode bekommt man ein Array mit max 6
	 * einträgen zurück.
	 * 
	 * @param unglueckszahlenArray
	 * @return
	 */
	public static ArrayList<Integer> mehrAlsSechsWerte(ArrayList<Integer> ungluecksZahlenArray) {
		ArrayList<Integer> ergebnisArray = new ArrayList<Integer>();
		for (Integer integer : ungluecksZahlenArray) {
			if (ergebnisArray.size() == 6) { // Es sollen max. 6 Unglückszahlen zulässig sein, daher werden, sollten
												// sich mehr als 6 darin befinden, nur die ersten 6 Zahlen welche sich
												// im unglückszahlenArray befinden verwendet.
				return ergebnisArray;
			} else
				ergebnisArray.add(integer);
		}
		return ergebnisArray;
	}
}