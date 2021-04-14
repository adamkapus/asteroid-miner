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
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".AcceptEntity()");

        entity.add(e);
        Szkeleton.indentDepth--;
    }
    // entity moves away
    public void RemoveEntity(Entity e){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".RemoveEntity()");

        entity.remove(e);

        Szkeleton.indentDepth--;
    }
    // add a new neighbor to the place
    public void AddNeighbor(Place p){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".AddNeighbor()");
        
        neighbors.add(p);

        Szkeleton.indentDepth++;
        p.Placed();

        Szkeleton.indentDepth--;
        
    }
    public void RemoveNeighbor(Place p){
        neighbors.remove(p);
    }
    // get a certain neighbor
    public Place GetNeighbor(int placeId){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".GetNeighbor()");
        Szkeleton.indentDepth--;
        for(Place p : neighbors)
            if (p.placeID == placeId)
                return p;
        return null;
    }
    // get a random neighbor
    public Place GetRandomNeighbor(){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".GetRandomNeighbor()");

        Random r = new Random();

        Szkeleton.indentDepth--;

        return neighbors.get(r.nextInt(neighbors.size()));

    }

    abstract public void HitByStorm();
    abstract public void Placed();
    abstract public void Action(Settler s);
    abstract public void Action(Robot r);
    abstract public void Action(Ufo u);
}
