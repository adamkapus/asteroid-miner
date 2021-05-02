package szkeleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AsteroidView {
	private java.util.Map<Asteroid, ArrayList<Place>> neighbours = new HashMap<>();
	private java.util.Map<Asteroid, Integer> layers = new HashMap<>();
	private java.util.Map<Asteroid, Resource> resource = new HashMap<>();
	private java.util.Map<Asteroid, Boolean> blownUp = new HashMap<>();
	
	public void updateAsteroid(Asteroid a) {
		neighbours.put(a, a.GetAllNeighbors());
		resource.put(a, a.getResource());
		layers.put(a, a.GetLayers());
		blownUp.put(a, a.GetBlownUp());
	}

	AsteroidView(){
	
	}

	public Map<Asteroid, Boolean> getBlownUp() {
		return blownUp;
	}

	public Map<Asteroid, ArrayList<Place>> getNeighbours() {
		return neighbours;
	}

	public Map<Asteroid, Integer> getLayers() {
		return layers;
	}

	public Map<Asteroid, Resource> getResource() {
		return resource;
	}
}
