package szkeleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SettlerView {
	/**
	 * place: az a hely, ahhol a telepes áll
	 * resources: a telepesnél lévő nyersanyagok
	 * gates: a telepesnél lévő teleportkapuk
	 */
	private java.util.Map<Settler, Place> place = new HashMap<>();
	private java.util.Map<Settler, ArrayList<Integer>> resources = new HashMap<>();
	private java.util.Map<Settler, ArrayList<TeleportGate>> gates = new HashMap<>();

	/**
	 * Frissíti a telepes minden állapotát, ami szükséges a megjelenítéshez
	 */
	public void updateSettler(Settler s) {
		place.put(s, s.GetPlace());
		ArrayList<Integer> res = new ArrayList<>();
		resources.put(s, s.UpdateResourceList(res));
		gates.put(s, s.GetGates());
	}

	/**
	 * Ha a telepes meghal, eltávolítjuk a helyről
	 */
	public void settlerDied(Settler s) {
		place.remove(s);
		resources.remove(s);
		gates.remove(s);
	}

	/**
	 * Konstruktor
	 */
	SettlerView(){ }
	public Place getPlace(Settler s) {return place.get(s);}
	public ArrayList<Integer> getResource(Settler s) {return resources.get(s);}
	public ArrayList<TeleportGate> getGates(Settler s) {return gates.get(s);}
	
	
	public Map<Settler, Place> getPlaceMap() {
		return place;
	}

	public Map<Settler, ArrayList<Integer>> getResourceMap() {
		return resources;
	}

	public Map<Settler, ArrayList<TeleportGate>> getGatesMap() {
		return gates;
	}

}
