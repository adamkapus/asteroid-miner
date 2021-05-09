package szkeleton;

import java.util.HashMap;
import java.util.Map;

public class UfoView {
	private java.util.Map<Ufo, Place> place = new HashMap<>();
	public void updateUfo(Ufo u) {
		place.put(u, u.GetPlace());
	}
	public void ufoDied(Ufo u) {
		place.remove(u);
	}
	
	UfoView(){
		
	}
	
	public Map<Ufo, Place> getPlace() {
		return place;
	}
}
