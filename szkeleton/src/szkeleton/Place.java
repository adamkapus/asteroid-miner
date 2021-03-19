package szkeleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

abstract public class Place implements Steppable {
    protected int placeID;
    protected List<Place> neighbors;
    protected Map map;
    protected List<Entity> entity;

    public Place(int id, Map m){
        placeID = id;
        neighbors = new ArrayList<>();
        map = m;
        entity = new ArrayList<>();
    }

    public void AcceptEntity(Entity e){
        entity.add(e);
    }
    public void RemoveEntity(Entity e){
        entity.remove(e);
    }
    public void AddNeighbor(Place p){
        neighbors.add(p);
        p.Placed();
    }
    public Place GetNeighbor(int placeId){
        for(Place p : neighbors)
            if (p.placeID == placeId)
                return p;
        return null;
    }
    public Place GetRandomNeighbor(){
        Random r = new Random();
        return neighbors.get(r.nextInt(neighbors.size()));
    }

    abstract public void HitByStorm();
    abstract public void Placed();
    abstract public void Action(Settler s);
    abstract public void Action(Robot r);
}
