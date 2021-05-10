package szkeleton;

import java.util.HashMap;
import java.util.Map;

public class UfoView {
	/**
	 * place: az a hely, ahhol az ufo áll
	 */
	private java.util.Map<Ufo, Place> place = new HashMap<>();

	/**
	 * Frissíti az ufo minden állapotát, ami szükséges a megjelenítéshez
	 */
	public void updateUfo(Ufo u) {
		place.put(u, u.GetPlace());
	}

	/**
	 * Ha az ufo meghal, eltávolítjuk a helyről
	 */
	public void ufoDied(Ufo u) {
		place.remove(u);
	}
	/**
	 * Konstruktor
	 */
	UfoView(){ }
	
	public Map<Ufo, Place> getPlace() {
		return place;
	}
}
