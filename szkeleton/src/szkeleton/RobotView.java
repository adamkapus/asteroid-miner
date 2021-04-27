package szkeleton;

import java.util.HashMap;

public class RobotView {
	private java.util.Map<Robot, Place> place = new HashMap<>();
	
	public void updateRobot(Robot r) {
		place.put(r, r.GetPlace());
	}
	public void robotDied(Robot r) {
		place.remove(r);
	}
}
