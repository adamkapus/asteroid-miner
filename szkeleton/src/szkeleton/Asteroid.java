package szkeleton;

import java.io.Console;
import java.util.*;

public class Asteroid extends Place{
    private enum State {
        CLOSE, FAR
    }

    private int timeLimit;
    private int timeCurrent;
    private int layers;
    private Resource resource;
    private State state;

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

    public void ReduceRockLayer(){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".ReduceRockLayer()");
        Szkeleton.indentDepth--;
        if (layers >= 0) layers--;
    }
    @Override
    public void Action(Settler s){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".Action()");
        System.out.println("1 - Fúrás; 2 - Bányászás; 3 - Nyersanyaglerakás\n");
        Scanner in = new Scanner(System.in);
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
                str = in.nextLine();
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
    @Override
    public void Action(Robot r){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".Action()");
        Szkeleton.indentDepth++;
        r.Drill();
        Szkeleton.indentDepth--;
    }
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
    public Resource MinedBy(Settler s){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".MinedBy()");
        Resource rTemp = resource;
        resource = null;
        Szkeleton.indentDepth--;
        return rTemp;
    }
    public void InsertResource(Resource r){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".InsertResource()");
        Szkeleton.indentDepth--;
        resource = r;
    }
    public void RemoveResource(){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".RemoveResource()");
        Szkeleton.indentDepth--;
        resource = null;
    }
    public void Blow(){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".Blow()");
        for(Entity e: entity) {
            Szkeleton.indentDepth++;
            e.BlownUp();
        }
        Szkeleton.indentDepth--;
    }
    @Override
    public void Placed(){
        Szkeleton.indentDepth--;
    }
    @Override
    public void Step(){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".Step()");
        timeCurrent++;
        if (timeCurrent == timeLimit)
            ChangeState();
        if (state == State.CLOSE && layers == 0 && resource != null)
            resource.Sublimation(this);
        if (resource != null && resource.IsRadioactive() && layers == 0) {
            Szkeleton.indentDepth++;
            Blow();
        }

        CheckResource();
        Szkeleton.indentDepth--;
    }

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
