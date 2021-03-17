package szkeleton;

public class TeleportGate extends Place {
    private boolean pairIsPlaced;
    private TeleportGate pair;

    @Override
    public void HitByStorm() {}
    @Override
    public void Action(Settler s){}
    @Override
    public void Action(Robot r){}
    public TeleportGate GetPair(){return null;}
    public void SetPair(TeleportGate tg){}
    public void SetPairIsPlaced(){}
    public void Placed(){}
    public void Step(){}
}
