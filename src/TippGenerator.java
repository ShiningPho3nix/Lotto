import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Um Interface Polymorphie anzuwenden, verwendet diese Klasse ein Lotto-Objekt
 * um die jeweiligen Implementationen für das Interface Lotto aufzurufen.
 * 
 * @author Steffen Dworsky
 *
 */
public class TippGenerator {

	ILotto lottoart = null;
	private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * Beim erzeugen eines neuen Tippgenerator Objektes wird eine Instanz von einer
	 * Klasse, die das Interface Lotto implementiert, dem Feld lottoart zugeweisen.
	 * 
	 * @param lotto
	 */
	public TippGenerator(ILotto lotto) {
		this.lottoart = lotto;
		logger.log(Level.INFO, "TippGenerator Konstruktor durchgelaufen, mit Lottoart: " + lottoModus());
	}

	/**
	 * Führt die jeweilige Implementation von erstelleCollection() aus. Abhängig
	 * davon welche Instanz gerade im Feld lottoart zugewiesen ist.
	 */
	public void erstelleCollection() {
		lottoart.erstelleCollection();
		logger.log(Level.INFO, "Collection für " + lottoModus() + " wurde erstellt");
	}

	/**
	 * Führt die jeweilige Implementation von entferneZahlen() aus. Abhängig davon
	 * welche Instanz gerade im Feld lottoart zugewiesen ist.
	 */
	public void entferneZahlen(Integer[] deleteZahlen) {
		lottoart.entferneZahlen(deleteZahlen);
	}

	/**
	 * Führt die jeweilige Implementation von entferneUnglueckszahl() aus. Abhängig
	 * davon welche Instanz gerade im Feld lottoart zugewiesen ist.
	 * 
	 * @param addZahlen
	 */
	public void entferneUnglueckszahl(Integer[] addZahlen) {
		lottoart.entferneUnglueckszahl(addZahlen);
	}

	/**
	 * Führt die jeweilige Implementation von generiereTipp() aus. Abhängig davon
	 * welche Instanz gerade im Feld lottoart zugewiesen ist.
	 */
	public void generiereTipp() {
		lottoart.generiereTipp();
		logger.log(Level.INFO, lottoModus() + " Tipp wurde erstellt.");
	}

	/**
	 * Führt die jeweilige Implementation von generiereTipps() aus. Abhängig davon
	 * welche Instanz gerade im Feld lottoart zugewiesen ist.
	 * 
	 * @param addZahlen
	 */
	public void generiereTipps(int quicktipp) {
		lottoart.generiereTipps(quicktipp);
	}

	/**
	 * Ermöglich es dem feld lottoart eine neue Instanz zuzuweisen.
	 */
	public void setLottoart(ILotto lotto) {
		this.lottoart = lotto;
	}

	/**
	 * Führt die jeweilige Implementation von modus() aus. Abhängig davon welche
	 * Instanz gerade im Feld lottoart zugewiesen ist.
	 */
	public String lottoModus() {
		return lottoart.modus();
	}

	/**
	 * Führt die jeweilige Implementation von reset() aus. Abhängig davon welche
	 * Instanz gerade im Feld lottoart zugewiesen ist.
	 */
	public void reset() {
		lottoart.reset();
	}

	/**
	 * Führt die jeweilige Implementation von liste() aus. Abhängig davon welche
	 * Instanz gerade im Feld lottoart zugewiesen ist.
	 */
	public ArrayList<Integer> liste() {
		return lottoart.liste();
	}
}
