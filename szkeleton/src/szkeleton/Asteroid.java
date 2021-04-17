package szkeleton;

import java.io.Console;
import java.util.*;

/**
 * Ezekből épül fel a játéktér.
 * A játékos ezen építhet, fúrhat, bányászhat vagy tartózkodhat.
 */
public class Asteroid extends Place{
    // state machine for asteroid state to the star
    private enum State {
        CLOSE, FAR
    }

    /**
     * timeLimit:change state when this number is reached
     * timeCurrent:time since last state change
     * layers:number of layers
     * resource:resource inside the asteroid
     * state:current state
     */
    private int timeLimit;
    private int timeCurrent;
    private int layers;
    private Resource resource;
    private State state;

    /**
     * create asteroid with name, unique id, current map, resource to be inside
      */
    public Asteroid(String name, int id, szkeleton.Map m, Resource r){
        super(name, id, m);
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
    public Asteroid(String name){
        super(name);
        // default values!
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }
    public void setTimeCurrent(int timeCurrent) {
        this.timeCurrent = timeCurrent;
    }
    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public int getTimeLimit() {
        return timeLimit;
    }
    public int getTimeCurrent() {
        return timeCurrent;
    }
    public Resource getResource() {
        return resource;
    }
    public State getState() {
        return state;
    }

    /**
     * Aszteroida kérgét csökkenti 1-el
      */
    public void ReduceRockLayer(){
        if (layers > 0) layers--;
    }

    /**
     * Settler által végzett action-öket kezeli az aszteroidán
     * Ha érvénytelen számot adunk meg akkor is vége a körünknek.
     * Erről értesítést is ad egy üzenetben.
     */
    @Override
    public void Action(Settler s){
        System.out.println("1 - Fúrás; 2 - Bányászás; 3 - Nyersanyaglerakás\n");
        Scanner in = new Scanner(System.in); // get number from the user
        String str = in.nextLine();
        try {
            int num = Integer.parseInt(str);
            if (num == 1) {
                s.Drill();
            }
            else if (num == 2) {
                s.Mine();
            }
            else if (num == 3){
                System.out.println("Hányas számú nyersanyagot szeretnéd letenni?");
                str = in.nextLine(); // get another number from user
                num = Integer.parseInt(str);
                if (num >= 0 && num < 10) {
                    s.PlaceResource(num);
                }
            }
        }
        catch (Exception e){
            System.out.println("Nem jó számot adtál meg, buktad a körödet!");
        }
    }

    /**
     * Robot által végzett action-öket kezeli.
     */
    @Override
    public void Action(Robot r){
        r.Drill(); // robot can only drill
    }

    @Override
    public void Action(Ufo u) {
        u.Mine();
    }

    /**
     * Napvihar esetén hívódó függvény minen aszteroidára.
     */
    @Override
    public void HitByStorm(){
        if ((layers != 0) || (resource != null)){
            for(Entity e : entity) {
                e.Die();
            }
        }
    }

    /**
     *Telepes bányászása esetn hívódó függvény.
     */
    public Resource MinedBy(Settler s){
        Resource rTemp = resource;
        resource = null;
        return rTemp;
    }
    public Resource MinedBy(Ufo u){
        Resource rTemp = resource;
        resource = null;
        return rTemp;
    }

    /**
     * Nyersanyag elhelyezése az aszteroidában
     */
    public void InsertResource(Resource r){
        resource = r;
    }

    /**
     * Nyersanyag eltávolítása az aszteroidából
     */
    public void RemoveResource(){
        resource = null;
    }

    /**
     * Minden Entitásra aki az aszteroidán volt meghívja a blownUp függvényüket.
     */
    public void Blow() {
        for (Entity e : entity) {
            e.BlownUp();
        }
    }
    @Override
    public void Placed(){}

    /**
     * Aszteroida lépése 1 körben
     * Itt változtatja, hogy napközelben vagy naptávolban van.
     * Ellenőrzi hogy milyen nyersanyagok vannak rajta, hogy a játékosok nyertek-e.
     * Napközelben radioaktív nyersanyaggal robban
     */
    @Override
    public void Step(){
        timeCurrent++;
        // change state if required
        if (timeCurrent == timeLimit)
            ChangeState();
        // sublimate the resource if conditions are met
        if (state == State.CLOSE && layers == 0 && resource != null) {
            resource.Sublimation(this);
        }
        // detonate the entities if conditions are met
        if (resource != null && resource.IsRadioactive() && layers == 0) {
            Blow();
        }

        // check the victory condition
        CheckResource();
    }

    /**
     * Az aszteroidán tartózkodó telepesek nyersanyag listáin végigmegy és megnézi, hogy van e elég alapanyaguk a "bázis építésére" azaz megnyerték-e a játékot
     */
    private void CheckResource(){
        ArrayList<Integer> currentResources = new ArrayList<>();
        for(Entity e : entity) {
            currentResources = e.UpdateResourceList(currentResources);
        }
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
        if (hasAllResources) {
            map.EnoughResources();
        }
    }

    /**
     * Megváltoztatna az aszteroida napközeliségét
     */
    private void ChangeState(){
        if (state == State.CLOSE)
            state = State.FAR;
        else
            state = State.CLOSE;
        timeCurrent = 0;
    }

    /**
     * A kéreg getter függvénye.
     */
    public int GetLayers(){
        return layers;
    }

    /**
     * A kéreg setter függvénye.
     */
    public void SetLayers(int newLayers) {
        layers = newLayers;
    }

    public void SetStateToClose(){
        state = State.CLOSE;
    }
    public void SetStateToFar() {state = State.FAR;}

    public String ToString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Asteroid ");
        sb.append(name);
        sb.append("\n\tentity ");
        if (entity.size() != 0) {
            for (Entity e : entity){
                sb.append(e.getName());
                sb.append(' ');
            }
        } else { sb.append("null"); }
        sb.append("\n\tlayers ");
        sb.append(layers);
        sb.append("\n\tmap ");
        if(map != null) {
            sb.append(map.getName());
        } else sb.append("null");
        sb.append("\n\tneighbors ");
        if(neighbors.size() != 0) {
            for (Place p : neighbors) {
                sb.append(p.GetName());
                sb.append(' ');
            }
        } else sb.append("null");
        sb.append("\n\tresource ");
        if(resource != null) {
            sb.append(resource.getName());
        } else sb.append("null");
        sb.append("\n\tstate ");
        if (state == State.CLOSE)
            sb.append("close");
        else
            sb.append("far");
        sb.append("\n\ttimeCurrent ");
        sb.append(timeCurrent);
        sb.append("\n\ttimeLimit ");
        sb.append(timeLimit);
        sb.append("\n");

        return sb.toString();
    }
}
