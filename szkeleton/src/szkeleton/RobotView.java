package szkeleton;

import java.util.HashMap;
import java.util.Map;

public class RobotView {
	private java.util.Map<Robot, Place> place = new HashMap<>();
	
	public void updateRobot(Robot r) {
		place.put(r, r.GetPlace());
	}
	public void robotDied(Robot r) {
		place.remove(r);
	}
	
	public Map<Robot, Place> getPlace() {
		return place;
	}
}
