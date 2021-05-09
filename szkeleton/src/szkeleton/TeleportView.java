package szkeleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TeleportView {
	private java.util.Map<TeleportGate, ArrayList<Place>> neighbours = new HashMap<>();
	public void updateTeleport(TeleportGate tg) {
		neighbours.put(tg, tg.GetAllNeighbors());
	}
	TeleportView(){
		
	}
	
	public Map<TeleportGate, ArrayList<Place>> getNeighbours() {
		return neighbours;
	}
}
