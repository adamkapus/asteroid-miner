package szkeleton;

public class TeleportGate extends Place {
    private boolean pairIsPlaced;
    private TeleportGate pair;

    public TeleportGate(int id, Map m){
        super(id, m);
        pairIsPlaced = false;
        pair = null;
    }

    @Override
    public void HitByStorm() {
        for(Entity e : entity){
            e.Die();
        }
    }
    @Override
    public void Action(Settler s){
        s.UseTeleport();
    }
    @Override
    public void Action(Robot r){}
    public TeleportGate GetPair(){return pair;}
    public void SetPair(TeleportGate tg){pair = tg;}
    public void SetPairIsPlaced(){pairIsPlaced = true;}
    @Override
    public void Placed(){
        pair.SetPairIsPlaced();
    }
    @Override
    public void Step(){}

}
