import java.util.ArrayList;

/**
 * Interface Klasse f�r Lotto-Objekte. Da es mehrere Arten von Lotto gibt, ist
 * deren Implementation unterschiedlich, da jedoch die selben Methoden n�tig
 * sind bietet sich hier ein Interface an.
 * 
 * @author Steffen Dworsky
 *
 */
public interface ILotto {

	void laden();

	void speichern();

	String generiereTipp();

	String generiereTipps(int quicktipp);

	void erstelleCollection();

	String neueUnglueckszahlAusschliessen(Integer[] deleteZahlen);

	String unglueckszahlWiederZulassen(Integer[] addZahlen);

	String modus();

	void reset();

	ArrayList<Integer> liste();

}
