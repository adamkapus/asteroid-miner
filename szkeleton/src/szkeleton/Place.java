package szkeleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

abstract public class Place implements Steppable {
    protected int placeID;
    protected List<Place> neighbors;
    protected Map map;
    protected List<Entity> entity;
    protected String name;
    /*
    public Place(int id, Map m){
    String name;

    public Place(String n, int id, Map m){
        name = n;*/
    public Place(String name, int id, Map m){
        placeID = id;
        neighbors = new ArrayList<>();
        map = m;
        entity = new ArrayList<>();
        this.name = name;
    }

    public void AcceptEntity(Entity e){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".AcceptEntity()");

        entity.add(e);
        Szkeleton.indentDepth--;
    }
    public void RemoveEntity(Entity e){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".RemoveEntity()");

        entity.remove(e);

        Szkeleton.indentDepth--;
    }
    public void AddNeighbor(Place p){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".AddNeighbor()");
        
        neighbors.add(p);
        Szkeleton.indentDepth++;
        p.Placed();

        Szkeleton.indentDepth--;
    }

    public Place GetNeighbor(int placeId){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".GetNeighbor()");
        Szkeleton.indentDepth--;
        for(Place p : neighbors)
            if (p.placeID == placeId)
                return p;
        return null;
    }

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
}
