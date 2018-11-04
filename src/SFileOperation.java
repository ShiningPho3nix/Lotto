import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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

}