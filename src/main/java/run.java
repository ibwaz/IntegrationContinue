import PassagersMetro.passagers;
import conducteurMetro.conducteur;
import ligneMetro.ligne;
import stationMetro.station;

public class run {

	public static void main(String[] args) {
		ligne l = new ligne(9);
		conducteur c = new conducteur(1,"khaled",l);
		passagers p = new passagers(1,50,l);
		//p.Modifier();
		c.Modifier();
		System.out.println(p.toString());
		System.out.println(c.toString());

	}

}
