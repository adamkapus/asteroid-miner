package szkeleton;

import java.util.ArrayList;
import java.util.HashMap;

public class SettlerView {
	private java.util.Map<Settler, Place> place = new HashMap<>();
	private java.util.Map<Settler, ArrayList<Integer>> resources = new HashMap<>();
	private java.util.Map<Settler, ArrayList<TeleportGate>> gates = new HashMap<>();
	
	public void updateSettler(Settler s) {
		place.put(s, s.GetPlace());
		ArrayList<Integer> res = new ArrayList<>();
		resources.put(s, s.UpdateResourceList(res));
		gates.put(s, s.GetGates());
	}

	public void settlerDied(Settler s) {
		place.remove(s);
		resources.remove(s);
		gates.remove(s);
	}
	
	SettlerView(){
		
	}

	public ArrayList<Integer> getResource(Settler s) {return resources.get(s);}
	public ArrayList<TeleportGate> getGates(Settler s) {return gates.get(s);}
}
