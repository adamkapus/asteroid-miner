package szkeleton;

public class TeleportGate extends Place {
    private boolean pairIsPlaced; // whether its pair is placed
    private TeleportGate pair; // its pair
    private boolean isCrazy = false;

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
        isCrazy = true;
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
    public void SetIsCrazy(){isCrazy=true;}
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

    public String ToString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Teleportgate ");
        sb.append(name);
        sb.append("\n\tentity ");
        for (Entity e : entity){
            sb.append(e.getName());
            sb.append(' ');
        }
        sb.append("\n\thitByStorm ");
        if (isCrazy)
            sb.append("true");
        else
            sb.append("false");
        sb.append("\n\tneighbors ");
        for (Place p : neighbors){
            sb.append(p.GetName());
            sb.append(' ');
        }
        sb.append("\n\tpair ");
        sb.append(pair.GetName());
        sb.append("\n\tpairIsPlaced ");
        if (pairIsPlaced)
            sb.append("true");
        else
            sb.append("false");
        sb.append("\n");

        return sb.toString();
    }
}
