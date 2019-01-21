package de.ShiningPho3nix.Lotto;

import java.util.ArrayList;

/**
 * Interface Klasse für Lotto-Objekte. Da es mehrere Arten von Lotto gibt, ist
 * deren Implementation unterschiedlich, da jedoch die selben Methoden nötig
 * sind bietet sich hier ein Interface an.
 * 
 * @author Steffen Dworsky
 *
 */
public interface ILotto {

	/**
	 * Lädt dei Unglückszahlen aus der Datei. Erzeugt eine neue, sollte keine
	 * existieren.
	 */
	void laden();

	/**
	 * Speichert die Unglückszahlen in der Datei. Erzeugt eine neue, sollte keine
	 * existieren.
	 */
	void speichern();

	/**
	 * Generiert einen Tipp im aktuell gewählten Modus, bzw. in der Klasse, von
	 * welchem TippGenerator das Objekt hält.
	 * 
	 * @return Einen formatierten String, welcher den Tipp darstellt.
	 */
	String generiereTipp();

	/**
	 * Führt generiereTipp() die mit quicktipp angegebene Anzahl an mal aus.
	 * 
	 * @param quicktipp
	 * @return Einen formatierten String, welcher alle generierten Tipps darstellt.
	 */
	String generiereTipps(int quicktipp);

	/**
	 * Erstellt mithilfe der Unglückszahlen die Tippzahlen ArrayLists für das Objekt
	 * in TippGenerator
	 */
	void erstelleCollection();

	/**
	 * Ermöglicht das ausschließen von Zahlen aus der Tippgenerierung.
	 * 
	 * @param deleteZahlen
	 * @return Einen formatierten String welcher für jede der übergebenen Zahlen
	 *         eine Benachrichtigung enthält, ob die Aktion erfolgreich war oder
	 *         nicht. Relevant vorallem für die Ausgabe dieser Informationen an den
	 *         Nutzer über die GUI.
	 */
	String neueUnglueckszahlAusschliessen(Integer[] deleteZahlen);

	/**
	 * Ermöglicht es Zahlen welche von der Tippgenerierung ausgeschlossen sind
	 * wieder zuzulassen.
	 * 
	 * @param addZahlen
	 * @return Einen formatierten String welcher für jede der übergebenen Zahlen
	 *         eine Benachrichtigung enthält, ob die Aktion erfolgreich war oder
	 *         nicht. Relevant vorallem für die Ausgabe dieser Informationen an den
	 *         Nutzer über die GUI.
	 */
	String unglueckszahlWiederZulassen(Integer[] addZahlen);

	/**
	 * Gibt den aktuellen Modus, also den Typ des Objektes welches von TippGenerator
	 * gehalten wird als String zurück.
	 * 
	 * @return 'Euro' für Eurojackpot oder '6aus49' für SechsAusNeunundvierzig.
	 */
	String modus();

	/**
	 * Löscht alle Unglückszahlen und erzeugt die Tippzahlen ArrayLists neu.
	 */
	void reset();

	/**
	 * Gibt eine Liste mit den Unglückszahlen zurück.
	 * 
	 * @return ArrayList der Unglückszahlen.
	 */
	ArrayList<Integer> liste();

}
