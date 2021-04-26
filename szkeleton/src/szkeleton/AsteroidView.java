package szkeleton;

import java.util.ArrayList;
import java.util.HashMap;

public class AsteroidView {
	private java.util.Map<Asteroid, ArrayList<Place>> neighbours = new HashMap<>();
	private java.util.Map<Asteroid, Integer> layers = new HashMap<>();
	private java.util.Map<Asteroid, Resource> resource = new HashMap<>();
	private java.util.Map<Asteroid, Boolean> blownUp = new HashMap<>();
	public void updateAsteroid(Asteroid a) {
		
	}

	AsteroidView(){
	
	}
}
