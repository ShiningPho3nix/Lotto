import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Logger;

public class SFileOperation {

	protected Ausgabe ausgabe;
	protected static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public SFileOperation() {
		ausgabe = LottoTippGenerator.ausgabe;
	}

	/**
	 * Funktion um den aktuellen Verzeichnisspfad als String ausgegeben zu bekommen
	 * 
	 * @return
	 */
	public String currentDirectory() {
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
	public void createFile() throws IOException {
		File f = new File(currentDirectory().concat("\\TippGenerator.txt"));
		f.createNewFile();
	}

	/**
	 * Methode um Arrays auf korrektheit zu pr�fen. F�hrt die drei Methoden
	 * ung�ltigeWerte, doppelteZahlen und mehrAlsSechsWerte in dieser Reihenfolge
	 * aus.
	 * 
	 * @param ungluecksZahlenArray
	 * @return
	 */
	public ArrayList<Integer> checkArray(ArrayList<Integer> ungluecksZahlenArray) {
		ArrayList<Integer> ergebnisArray = new ArrayList<Integer>();
		ergebnisArray = mehrAlsSechsWerte(doppelteZahlen(ungueltigeWerte(ungluecksZahlenArray)));
		// �berpr�ft mithilfe von 3 Methoden Bedingungen auf korrektheit. Zun�chst
		// werden alle Werte au�erhalb des g�ltigen Zahlenbereiches entfernt. Dannach
		// wird gepr�ft ob Zahlen doppelt enthalten sind. Sollten es danach mehr als 6
		// sein, werden nur die ersten 6 verwendet.
		return ergebnisArray;
	}

	/**
	 * Pr�ft ob das Ungl�ckszahlenArray Doppelte Zahlen enth�lt, z.B. wenn die
	 * Textdatei in welcher die Zahlen gespeichert werden Manipuliert wurde.
	 * 
	 * @return
	 */
	public ArrayList<Integer> doppelteZahlen(ArrayList<Integer> ungluecksZahlenArray) {
		ArrayList<Integer> ergebnisArray = new ArrayList<Integer>();
		for (Integer integer : ungluecksZahlenArray) {
			if (!ergebnisArray.contains((Integer) integer)) { // Ist die aktuelle Zahl noch nicht im ergebnisArray, so
																// wird die zahl hinzugef�gt. Kommt die selbe zahl
																// nochmals vor, so wird die ignoriert.
				ergebnisArray.add(integer);
			}
		}
		return ergebnisArray;
	}

	/**
	 * Methode Pr�ft ob das �bergebene Array ung�ltige Werte, in diesem Fall Zahlen
	 * die kleiner als 1 oder gr��er als 50 sind enth�lt und entfernt diese.
	 * 
	 * @param ungluecksZahlenArray
	 * @return
	 */
	public ArrayList<Integer> ungueltigeWerte(ArrayList<Integer> ungluecksZahlenArray) {
		ArrayList<Integer> ergebnisArray = new ArrayList<Integer>();
		for (Integer integer : ungluecksZahlenArray) {
			if (integer > 0 && integer < 51) { // Bei 6aus49 d�rfen Zahlen im Bereich 1-49 liegen, bei Eurojackpot 1-50.
												// Ungl�ckszahlen sollen f�r beide gelten, somit in der gesamtbereich
												// 1-50.
				ergebnisArray.add(integer);
			}
		}
		return ergebnisArray;
	}

	/**
	 * Pr�ft ob das �bergebene Array mehr als 6 Daten enth�lt und entfernt alle
	 * �bersch�ssigen. Nach durchlauf der Methode bekommt man ein Array mit max 6
	 * eintr�gen zur�ck.
	 * 
	 * @param unglueckszahlenArray
	 * @return
	 */
	public ArrayList<Integer> mehrAlsSechsWerte(ArrayList<Integer> ungluecksZahlenArray) {
		ArrayList<Integer> ergebnisArray = new ArrayList<Integer>();
		for (Integer integer : ungluecksZahlenArray) {
			if (ergebnisArray.size() == 6) { // Es sollen max. 6 Ungl�ckszahlen zul�ssig sein, daher werden, sollten
												// sich mehr als 6 darin befinden, nur die ersten 6 Zahlen welche sich
												// im ungl�ckszahlenArray befinden verwendet.
				return ergebnisArray;
			} else
				ergebnisArray.add(integer);
		}
		return ergebnisArray;
	}
}