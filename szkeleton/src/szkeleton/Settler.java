package szkeleton;

import java.util.List;

public class Settler extends Entity{
    private List/*<TeleportGate>*/ gates /*= new ArrayList<>()*/;
    private List/*<Resource>*/ resources /*= new ArrayList<>()*/;

    public void Mine() {}
    public void Die() {}
    public void BlownUp() {}
    public void PlaceResource(int n) {}
    public void AddResource(/*Resource r*/) {}
    public void BuildRobot() {}
    public void UseTeleport() {}
    public void BuildTeleport() {}
    public void PlaceDownTeleport() {}
    public void RemoveResource(/*Resource r*/) {}
    public void Step() {}
}
