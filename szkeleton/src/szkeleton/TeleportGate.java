package szkeleton;

public class TeleportGate extends Place {
    private boolean pairIsPlaced; // whether its pair is placed
    private TeleportGate pair; // its pair

    public TeleportGate(String name, int id, Map m){
        super(name, id, m);
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".TeleportGate()");
        Szkeleton.indentDepth--;
        pairIsPlaced = false;
        pair = null;
    }

    // teleportgate is hit by solar storm
    @Override
    public void HitByStorm() {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".HitByStorm()");
        for(Entity e : entity){
            Szkeleton.indentDepth++;
            e.Die();
        }
        Szkeleton.indentDepth--;
    }
    // do action by settler
    @Override
    public void Action(Settler s){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".Action()");
        if (pairIsPlaced) {
            Szkeleton.indentDepth++;
            s.UseTeleport();
        }
        Szkeleton.indentDepth--;
    }
    // do action by robot (nothing is done here)
    @Override
    public void Action(Robot r){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".Action()");
        Szkeleton.indentDepth--;
    }
    // returns the pair of the teleportgate
    public TeleportGate GetPair(){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".GetPair()");
        Szkeleton.indentDepth--;
        return pair;
    }

    public void SetPair(TeleportGate tg){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".SetPair()");
        Szkeleton.indentDepth--;
        pair = tg;
    }
    // notify the gate that its pair was placed
    public void SetPairIsPlaced(){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".SetPairIsPlaced()");
        Szkeleton.indentDepth--;
        pairIsPlaced = true;
    }
    // teleportgate is placed
    @Override
    public void Placed(){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".Placed()");
        Szkeleton.indentDepth++;
        pair.SetPairIsPlaced();
        Szkeleton.indentDepth--;
    }
    // make its turn
    @Override
    public void Step(){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".Step()");
        Szkeleton.indentDepth--;
    }

}
