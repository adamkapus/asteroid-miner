package szkeleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TeleportView {
	/**
	 * neighbours: a teleportkapu szomszédai
	 */
	private java.util.Map<TeleportGate, ArrayList<Place>> neighbours = new HashMap<>();

	/**
	 * Frissíti a teleportkapu minden állapotát, ami szükséges a megjelenítéshez
	 */
	public void updateTeleport(TeleportGate tg) {
		neighbours.put(tg, tg.GetAllNeighbors());
	}

	/**
	 * Konstruktor
	 */
	TeleportView(){	}
	
	public Map<TeleportGate, ArrayList<Place>> getNeighbours() {
		return neighbours;
	}
}
