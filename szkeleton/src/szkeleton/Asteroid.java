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

    public Asteroid(int id, szkeleton.Map m, Resource r){
        super(id, m);
        Random ran = new Random();
        timeLimit = ran.nextInt(50 - 5) + 5; // random int between 5 and 50
        timeCurrent = 0;
        layers = ran.nextInt(10); // random int between 0 and 10
        resource = r;
        if (ran.nextInt(2) == 0) // random state
            state = State.CLOSE;
        else
            state = State.FAR;
    }

    public void ReduceRockLayer(){
        if (layers >= 0) layers--;
    }
    @Override
    public void Action(Settler s){
        System.out.println("1 - Fúrás; 2 - Bányászás; 3 - Nyersanyaglerakás\n");
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        try {
            int num = Integer.parseInt(str);
            if (num == 1)
                s.Drill();
            else if (num == 2)
                s.Mine();
            else if (num == 3){
                str = in.nextLine();
                num = Integer.parseInt(str);
                if (num >= 0 && num < 10)
                    s.PlaceResource(num);
            }
        }
        catch (Exception e){
            System.out.println("Nem jó számot adtál meg, buktad a körödet!");
        }
    }
    @Override
    public void Action(Robot r){
        r.Drill();
    }
    @Override
    public void HitByStorm(){
        if (layers != 0 && resource != null){
            for(Entity e : entity)
                e.Die();
        }
    }
    public Resource MinedBy(Settler s){
        Resource rTemp = resource;
        resource = null;
        return rTemp;
    }
    public void InsertResource(Resource r){
        resource = r;
    }
    public void RemoveResource(Resource r){} // ez kell?
    public void Blow(){
        for(Entity e: entity)
            e.BlownUp();
    }
    public void CheckReqResource(){
        // ez mit tud?
    }
    @Override
    public void Placed(){}
    @Override
    public void Step(){
        timeCurrent++;
        if (timeCurrent == timeLimit)
            ChangeState();
        if (state == State.CLOSE && layers == 0 && resource != null){}
            //resource.Sublimate(); a resource-nak kéne szublimálás függvény!!!
        if (resource != null && resource.IsRadioactive() && layers == 0)
            Blow();

        CheckResource();
    }
    private void CheckResource(){
        int[] currentResources = new int[0]; // ezt nem lenne egyszerűbb ArrayList-el?
        for(Entity e : entity)
            currentResources = e.UpdateResourceList(currentResources);
        int[] allResources = map.GetAllResources(); // am ezt is lehetne ArrayList-el
        java.util.Map<Integer, Integer> resMap = new HashMap<>();
        for(int i = 0; i < allResources.length; i++)
            resMap.put(allResources[i], 0);
        for(int i = 0; i < currentResources.length; i++)
            resMap.put(currentResources[i], resMap.get(currentResources[i]) + 1);
        boolean hasAllResources = true;
        for (int i = 0; i < allResources.length; i++){
            if (resMap.get(allResources[i]) < 3) {
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
}
