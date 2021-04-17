package szkeleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

abstract public class Place implements Steppable {
    protected int placeID; // unique id
    protected List<Place> neighbors; // neighbors
    protected Map map; // map it is on
    protected List<Entity> entity; // entities on this place
    protected String name; // name



    public Place(String name, int id, Map m){
        placeID = id;
        neighbors = new ArrayList<>();
        map = m;
        entity = new ArrayList<>();
        this.name = name;
    }
    public Place(String name){
        this.name = name;
        entity = new ArrayList<>();
        neighbors = new ArrayList<>();
    }

    public void SetPlaceID(int id) {placeID = id;}
    public void SetMap(Map m) {map = m;}

    public int GetPlaceID() {return placeID;}
    public List<Place> GetAllNeighbors() {return neighbors;}
    public Map GetMap() {return map;}
    public List<Entity> GetAllEntities() {return entity;}
    public String GetName() {return name;}

    // entity moves here
    public void AcceptEntity(Entity e){
        entity.add(e);
    }
    // entity moves away
    public void RemoveEntity(Entity e){
        entity.remove(e);
    }
    // add a new neighbor to the place
    public void AddNeighbor(Place p){
        neighbors.add(p);
        p.Placed();
    }
    public void RemoveNeighbor(Place p){
        neighbors.remove(p);
    }
    // get a certain neighbor
    public Place GetNeighbor(int placeId){
        for(Place p : neighbors)
            if (p.placeID == placeId)
                return p;
        return null;
    }
    // get a random neighbor
    public Place GetRandomNeighbor(){
        Random r = new Random();
        return neighbors.get(r.nextInt(neighbors.size()));
    }

    abstract public void HitByStorm();
    abstract public void Placed();
    abstract public void Action(Settler s);
    abstract public void Action(Robot r);
    abstract public void Action(Ufo u);
}
