package szkeleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

abstract public class Place implements Steppable {
    protected int placeID;
    protected List<Place> neighbors;
    protected Map map;
    protected List<Entity> entity;

    String name;

    public Place(String n, int id, Map m){
        name = n;
        placeID = id;
        neighbors = new ArrayList<>();
        map = m;
        entity = new ArrayList<>();
    }

    public void AcceptEntity(Entity e){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".AcceptEntity()");

        Szkeleton.indentDepth++;
        entity.add(e);
    }
    public void RemoveEntity(Entity e){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".RemoveEntity()");

        Szkeleton.indentDepth++;
        entity.remove(e);

        Szkeleton.indentDepth--;
    }
    public void AddNeighbor(Place p){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".AddNeighbor()");

        Szkeleton.indentDepth++;
        neighbors.add(p);
        p.Placed();

        Szkeleton.indentDepth--;
    }

    public Place GetNeighbor(int placeId){
        for(Place p : neighbors)
            if (p.placeID == placeId)
                return p;
        return null;
    }
    public Place GetRandomNeighbor(){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".GetRandomNeighbor()");

        Szkeleton.indentDepth++;
        Random r = new Random();

        Szkeleton.indentDepth--;

        return neighbors.get(r.nextInt(neighbors.size()));

    }

    abstract public void HitByStorm();
    abstract public void Placed();
    abstract public void Action(Settler s);
    abstract public void Action(Robot r);
}
