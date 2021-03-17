package szkeleton;

import java.util.List;

abstract public class Place /*implements Steppable*/ {
    protected int placeID;
    protected List<Place> neighbors;
    //protected Map map;
    protected List<Entity> entity;

    public void AcceptEntity(Entity e){}
    public void RemoveEntity(Entity e){}
    abstract void HitByStorm();
    public void AddNeighbor(Place p){}
    public void Action(Settler s){}
    public void Action(Robot r){}
    public Place GetNeighbor(int placeId){return null;}
    public Place GetRandomNeighbor(){return null;}
}
