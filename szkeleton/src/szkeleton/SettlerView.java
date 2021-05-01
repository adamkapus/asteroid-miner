package szkeleton;

import java.util.ArrayList;
import java.util.HashMap;

public class SettlerView {
	private java.util.Map<Settler, Place> place = new HashMap<>();
	private java.util.Map<Settler, ArrayList<Resource>> resources = new HashMap<>();
	private java.util.Map<Settler, ArrayList<TeleportGate>> gates = new HashMap<>();
	
	public void updateSettler(Settler s) {
		place.put(s, s.GetPlace());
		resources.put(s, s.GetResources());
		gates.put(s, s.GetGates());
	}

	public void settlerDied(Settler s) {
		place.remove(s);
		resources.remove(s);
		gates.remove(s);
	}
	
	SettlerView(){
		
	}
}
