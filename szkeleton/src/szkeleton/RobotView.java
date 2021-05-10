package szkeleton;

import java.util.HashMap;
import java.util.Map;

public class RobotView {
	/**
	 * place: az a hely, ahol a robot épp áll
	 */
	private java.util.Map<Robot, Place> place = new HashMap<>();

	/**
	 * Konstruktor
	 */
	RobotView(){}

	/**
	 * Frissíti a robot minden állapotát, ami szükséges a megjelenítéshez
	 */
	public void updateRobot(Robot r) {
		place.put(r, r.GetPlace());
	}

	/**
	 * Ha a robot meghal, eltávolítjuk a helyről
	 */
	public void robotDied(Robot r) {
		place.remove(r);
	}
	
	public Map<Robot, Place> getPlace() {
		return place;
	}
}
