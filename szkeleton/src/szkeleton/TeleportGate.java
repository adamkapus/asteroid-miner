package szkeleton;

public class TeleportGate extends Place {
    private boolean pairIsPlaced; // whether its pair is placed
    private TeleportGate pair; // its pair

    public TeleportGate(String name, int id, Map m){
        super(name, id, m);
        pairIsPlaced = false;
        pair = null;
    }
    public TeleportGate(String name){
        super(name);
        pairIsPlaced = false;
        pair = null;
    }

    public String getName(){ return this.name; }

    // teleportgate is hit by solar storm
    @Override
    public void HitByStorm() {
        for(Entity e : entity){
            e.Die();
        }
    }
    // do action by settler
    @Override
    public void Action(Settler s){
        if (pairIsPlaced) {
            s.UseTeleport();
        }
    }
    // do action by robot (nothing is done here)
    @Override
    public void Action(Robot r){}

    @Override
    public void Action(Ufo u) {
        if (pairIsPlaced) {
            u.UseTeleport();
        }
    }

    // returns the pair of the teleportgate
    public TeleportGate GetPair(){
        return pair;
    }

    public void SetPair(TeleportGate tg){
        pair = tg;
    }
    // notify the gate that its pair was placed
    public void SetPairIsPlaced(){
        pairIsPlaced = true;
    }
    public boolean GetPairIsPlaced() {return pairIsPlaced;}
    // teleportgate is placed
    @Override
    public void Placed(){
        pair.SetPairIsPlaced();
    }
    // make its turn
    @Override
    public void Step(){}

    public void MoveRandom(){
        Asteroid a = (Asteroid) neighbors.get(0);
        a.RemoveNeighbor(this);
        Place neighbor = a.GetRandomNeighbor();
        neighbor.AddNeighbor(this);
    }
    
}
