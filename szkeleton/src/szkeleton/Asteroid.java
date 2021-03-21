package szkeleton;

import java.io.Console;
import java.util.*;

public class Asteroid extends Place{
    // state machine for asteroid state to the star
    private enum State {
        CLOSE, FAR
    }

    private int timeLimit; // change state when this number is reached
    private int timeCurrent; // time since last state change
    private int layers; // number of layers
    private Resource resource; // resource inside the asteroid
    private State state; // current state

    // create asteroid with name, unique id, current map, resource to be inside
    public Asteroid(String name, int id, szkeleton.Map m, Resource r){
        super(name, id, m);
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".Asteroid()");
        Random ran = new Random();
        timeLimit = ran.nextInt(50 - 5) + 5; // random int between 5 and 50
        timeCurrent = 0;
        layers = ran.nextInt(10); // random int between 0 and 10
        resource = r;
        if (ran.nextInt(2) == 0) // random state
            state = State.CLOSE;
        else
            state = State.FAR;

        Szkeleton.indentDepth--;
    }

    // reduce the number of layers
    public void ReduceRockLayer(){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".ReduceRockLayer()");
        Szkeleton.indentDepth--;
        if (layers >= 0) layers--;
    }
    // do action with settler
    @Override
    public void Action(Settler s){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".Action()");
        System.out.println("1 - Fúrás; 2 - Bányászás; 3 - Nyersanyaglerakás\n");
        Scanner in = new Scanner(System.in); // get number from the user
        String str = in.nextLine();
        try {
            int num = Integer.parseInt(str);
            if (num == 1) {
                Szkeleton.indentDepth++;
                s.Drill();
            }
            else if (num == 2) {
                Szkeleton.indentDepth++;
                s.Mine();
            }
            else if (num == 3){
                System.out.println("Hányas számú nyersanyagot szeretnéd letenni?");
                str = in.nextLine(); // get another number from user
                num = Integer.parseInt(str);
                if (num >= 0 && num < 10) {
                    Szkeleton.indentDepth++;
                    s.PlaceResource(num);
                }
            }
        }
        catch (Exception e){
            System.out.println("Nem jó számot adtál meg, buktad a körödet!");
        }
        Szkeleton.indentDepth--;
    }
    // do action with robot
    @Override
    public void Action(Robot r){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".Action()");
        Szkeleton.indentDepth++;
        r.Drill(); // robot can only drill
        Szkeleton.indentDepth--;
    }
    // asteroid is hit by storm
    @Override
    public void HitByStorm(){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".HitByStorm()");
        if (layers != 0 && resource != null){
            for(Entity e : entity) {
                Szkeleton.indentDepth++;
                e.Die();
            }
        }
        Szkeleton.indentDepth--;
    }
    // asteroid is mined by settler
    public Resource MinedBy(Settler s){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".MinedBy()");
        Resource rTemp = resource;
        resource = null;
        Szkeleton.indentDepth--;
        return rTemp;
    }
    // resource is placed inside the asteroid
    public void InsertResource(Resource r){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".InsertResource()");
        Szkeleton.indentDepth--;
        resource = r;
    }
    // remove the current resource from the asteroid
    public void RemoveResource(){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".RemoveResource()");
        Szkeleton.indentDepth--;
        resource = null;
    }
    // all entities are blown up
    public void Blow() {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".Blow()");
        for (Entity e : entity) {
            Szkeleton.indentDepth++;
            e.BlownUp();
        }
        Szkeleton.indentDepth--;
    }
    @Override
    public void Placed(){
        Szkeleton.indentDepth--;
    }
    // asteroid makes its turn
    @Override
    public void Step(){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".Step()");
        timeCurrent++;
        // change state if required
        if (timeCurrent == timeLimit)
            ChangeState();
        // sublimate the resource if conditions are met
        if (state == State.CLOSE && layers == 0 && resource != null)
            resource.Sublimation(this);
        // detonate the entities if conditions are met
        if (resource != null && resource.IsRadioactive() && layers == 0) {
            Szkeleton.indentDepth++;
            Blow();
        }

        // check the victory condition
        CheckResource();
        Szkeleton.indentDepth--;
    }

    // check the victory condition
    private void CheckResource(){
        ArrayList<Integer> currentResources = new ArrayList<>();
        for(Entity e : entity)
            currentResources = e.UpdateResourceList(currentResources);
        ArrayList<Integer> allResources = map.GetAllResources();
        java.util.Map<Integer, Integer> resMap = new HashMap<>();
        for (Integer allResource : allResources) resMap.put(allResource, 0);
        for (Integer currentResource : currentResources) resMap.put(currentResource, resMap.get(currentResource) + 1);
        boolean hasAllResources = true;
        for (Integer allResource : allResources) {
            if (resMap.get(allResource) < 3) {
                hasAllResources = false;
                break;
            }
        }
        if (hasAllResources)
            map.EnpughResources();
    }

    private void ChangeState(){
        if (state == State.CLOSE)
            state = State.FAR;
        else
            state = State.CLOSE;
        timeCurrent = 0;
    }

    public int GetLayers(){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".GetLayers()");
        Szkeleton.indentDepth--;
        return layers;
    }
    
    public void SetLayers(int newLayers) {
    	Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".SetLayers()");
        layers = newLayers;
        Szkeleton.indentDepth--;
    }
}
