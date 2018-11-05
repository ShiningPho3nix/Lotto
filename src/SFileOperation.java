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
	 * @param mode
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
		ArrayList<Integer> returnArray = new ArrayList<Integer>();
		returnArray = mehrAlsSechsWerte(doppelteZahlen(ungueltigeWerte(ungluecksZahlenArray)));
		return returnArray;
	}

	/**
	 * Pr�ft ob das Ungl�ckszahlenArray Doppelte Zahlen enth�lt, z.B. wenn die
	 * Textdatei in welcher die Zahlen gespeichert werden Manipuliert wurde.
	 * 
	 * @return
	 */
	public ArrayList<Integer> doppelteZahlen(ArrayList<Integer> ungluecksZahlenArray) {
		for (Integer integer : ungluecksZahlenArray) {
			int count = 1;
			for (Integer integer2 : ungluecksZahlenArray) {
				if (integer.equals(integer2)) {
					count++;
				}
				if (count > 1) {
					ungluecksZahlenArray.remove((Integer) integer);
				}
			}
		}
		return ungluecksZahlenArray;
	}

	/**
	 * Methode Pr�ft ob das �bergebene Array ung�ltige Werte, in diesem Fall Zahlen
	 * die kleiner als 1 oder gr��er als 50 sind enth�lt und entfernt diese.
	 * 
	 * @param ungluecksZahlenArray
	 * @return
	 */
	public ArrayList<Integer> ungueltigeWerte(ArrayList<Integer> ungluecksZahlenArray) {
		for (Integer integer : ungluecksZahlenArray) {
			if (integer < 1 || integer > 50) {
				ungluecksZahlenArray.remove((Integer) integer);
			}
		}
		return ungluecksZahlenArray;
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
		ArrayList<Integer> returnArray = new ArrayList<Integer>();
		for (Integer integer : ungluecksZahlenArray) {
			if (returnArray.size() > 6) {
				return returnArray;
			} else
				returnArray.add(integer);
		}
		return returnArray;
	}
}