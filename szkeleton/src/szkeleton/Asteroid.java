package szkeleton;

import java.util.List;

public class Asteroid extends Place{
    private int timeLimit;
    private int timeCurrent;
    private int layers;
    //private List<Resource> resources;

    public void ReduceRockLayer(){}
    @Override
    public void Action(Settler s){}
    @Override
    public void Action(Robot r){}
    public void HitByStorm(){}
    //public Resource MinedBy(Settler s){}
    //public InsertResource(Resource r){}
    //public RemoveResource(Resource r){}
    public void Blow(){}
    public void CheckReqResource(){}
    public void Step(){}
}
