package de.ShiningPho3nix.Lotto;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Um Interface Polymorphie anzuwenden, verwendet diese Klasse ein Lotto-Objekt
 * um die jeweiligen Implementationen f�r das Interface Lotto aufzurufen.
 * 
 * @author Steffen Dworsky
 *
 */
public class TippGenerator {

	private ILotto lottoart = null;
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
	 * F�hrt die jeweilige Implementation von erstelleCollection() aus. Abh�ngig
	 * davon welche Instanz gerade im Feld lottoart zugewiesen ist.
	 */
	public void erstelleCollection() {
		lottoart.erstelleCollection();
		logger.log(Level.INFO, "Collection f�r " + lottoModus() + " wurde erstellt");
	}

	/**
	 * F�hrt die jeweilige Implementation von entferneZahlen() aus. Abh�ngig davon
	 * welche Instanz gerade im Feld lottoart zugewiesen ist.
	 * 
	 * @return
	 */
	public String neueUnglueckszahlAusschliessen(Integer[] deleteZahlen) {
		return lottoart.neueUnglueckszahlAusschliessen(deleteZahlen);
	}

	/**
	 * F�hrt die jeweilige Implementation von entferneUnglueckszahl() aus. Abh�ngig
	 * davon welche Instanz gerade im Feld lottoart zugewiesen ist.
	 * 
	 * @param addZahlen
	 * @return
	 */
	public String unglueckszahlWiederZulassen(Integer[] addZahlen) {
		return lottoart.unglueckszahlWiederZulassen(addZahlen);
	}

	/**
	 * F�hrt die jeweilige Implementation von generiereTipps() aus. Abh�ngig davon
	 * welche Instanz gerade im Feld lottoart zugewiesen ist.
	 * 
	 * @param addZahlen
	 */
	public String generiereTipps(int quicktipp) {
		return lottoart.generiereTipps(quicktipp);
	}

	/**
	 * Erm�glich es dem feld lottoart eine neue Instanz zuzuweisen.
	 */
	public void setLottoart(ILotto lotto) {
		this.lottoart = lotto;
	}

	/**
	 * F�hrt die jeweilige Implementation von modus() aus. Abh�ngig davon welche
	 * Instanz gerade im Feld lottoart zugewiesen ist.
	 */
	public String lottoModus() {
		return lottoart.modus();
	}

	/**
	 * F�hrt die jeweilige Implementation von reset() aus. Abh�ngig davon welche
	 * Instanz gerade im Feld lottoart zugewiesen ist.
	 */
	public void reset() {
		lottoart.reset();
	}

	/**
	 * F�hrt die jeweilige Implementation von liste() aus. Abh�ngig davon welche
	 * Instanz gerade im Feld lottoart zugewiesen ist.
	 */
	public ArrayList<Integer> liste() {
		return lottoart.liste();
	}

}
