import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Logger;

public class SFileOperation {

	protected static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public SFileOperation() {

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
	public static void createFile() throws IOException {
		File f = new File(currentDirectory().concat("\\TippGenerator.txt"));
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