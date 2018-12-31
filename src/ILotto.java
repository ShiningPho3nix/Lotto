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

	void laden();

	void speichern();

	void generiereTipp();

	String generiereTippTest();

	void generiereTipps(int quicktipp);

	String generiereTippsTest(int anzahlTipps);

	void erstelleCollection();

	void entferneZahlen(Integer[] deleteZahlen);

	void entferneAusTippzahlen(ArrayList<Integer> unglueckszahlenArray);

	void entferneUnglueckszahl(Integer[] addZahlen);

	String modus();

	void reset();

	ArrayList<Integer> liste();

}
