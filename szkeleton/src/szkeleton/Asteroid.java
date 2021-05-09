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
    private AsteroidView asteroidView;	
    private boolean blownUp;

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

    public Asteroid(String name, int id, szkeleton.Map m, Resource r, AsteroidView av){
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
        asteroidView = av;
        asteroidView.updateAsteroid(this);
    }

    /**
     * setter functions
     */
    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }
    public void setTimeCurrent(int timeCurrent) {
        this.timeCurrent = timeCurrent;
    }
    public void setResource(Resource resource) {
        this.resource = resource;
    }

    /**
     * getter functions
     */
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
        map.getGame().getFrame().disableAsteroidActionButtons();
        if (layers > 0) layers--;
        asteroidView.updateAsteroid(this);
    }

    /**
     * Settler által végzett action-öket kezeli az aszteroidán
     * Ha érvénytelen számot adunk meg akkor is vége a körünknek.
     * Erről értesítést is ad egy üzenetben.
     */
    @Override
    public void Action(Settler s){
        map.getGame().getFrame().activateAsteroidActionButtons();
        /*System.out.println("1 - Fúrás; 2 - Bányászás; 3 - Nyersanyaglerakás\n");
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
        }*/
    }

    /**
     * Robot által végzett action-öket kezeli.
     */
    @Override
    public void Action(Robot r){
        r.Drill();
        asteroidView.updateAsteroid(this);
    }

    /**
     * Action for ufo
     */
    @Override
    public void Action(Ufo u) {
        u.Mine();
        asteroidView.updateAsteroid(this);
    }

    /**
     * Napvihar esetén hívódó függvény minen aszteroidára.
     */
    @Override
    public void HitByStorm(){
        if ((layers != 0) || (resource != null)){
        	ArrayList<Entity> copy = new ArrayList<Entity>();
        	for(int i =0; i < entity.size(); i++) {
        		copy.add(entity.get(i));
        	}
        	for(int i =0; i < copy.size(); i++) {
        		copy.get(i).Die();
        	}
            /*for(Entity e : entity) {
            	System.out.println("Asteroidot elerte a vihar");
            	try{
                e.Die();
            	}
            	catch(Exception exception) {
            		System.out.println("Kivetel elkapva napviharnal");
            	}
            }*/
        }
    }

    /**
     *Telepes bányászása esetn hívódó függvény.
     * @return resource inside asteroid
     */
    public Resource MinedBy(Settler s){
        map.getGame().getFrame().disableAsteroidActionButtons();
        if(resource!=null){
            Resource rTemp = resource;
            resource = null;
            asteroidView.updateAsteroid(this);
            return rTemp;}
        else {
        	 asteroidView.updateAsteroid(this);
            return null;
        }
        
    }

    /**
     * Asteroid is getting mined by ufo
     * @return resource inside asteroid
     */
    public Resource MinedBy(Ufo u){
        if(resource!=null){
            Resource rTemp = resource;
            resource = null;
            asteroidView.updateAsteroid(this);
            return rTemp;}
        else {
        	 asteroidView.updateAsteroid(this);
            return null;
        }
    }

    /**
     * Nyersanyag elhelyezése az aszteroidában
     */
    public void InsertResource(Resource r){
        map.getGame().getFrame().disableAsteroidActionButtons();
        resource = r;
        asteroidView.updateAsteroid(this);
     
    }

    /**
     * Nyersanyag eltávolítása az aszteroidából
     */
    public void RemoveResource(){
        resource = null;
        asteroidView.updateAsteroid(this);
    }

    /**
     * Minden Entitásra aki az aszteroidán volt meghívja a blownUp függvényüket.
     */
    public void Blow() {
    	ArrayList<Entity> copy = new ArrayList<Entity>();
    	for(int i =0; i < entity.size(); i++) {
    		copy.add(entity.get(i));
    	}
    	for(int i =0; i < copy.size(); i++) {
    		copy.get(i).BlownUp();
    	}
        /*for (Entity e : entity) {
        	try{
                e.BlownUp();
            	}
            	catch(Exception exception) {
            		System.out.println("Kivetel elkapva felorbbanasnak");
            	}
        }*/
    }

    /**
     * Asteroid is placed
     */
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
        if (resource != null && layers == 0) {
        	if(resource.IsRadioactive()) {
        		System.out.println("Aszteroida robban");
        		Blow();
        	}
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

    /**
     * Set state to close or far
     */
    public void SetStateToClose(){
        state = State.CLOSE;
    }
    public void SetStateToFar() {state = State.FAR;}
	
	
    /**
     * Viasszaadja, fel van-e épp robbbanva az aszteroida
     */
    public boolean GetBlownUp() {return blownUp;}

    /**
     * add a new neighbor to the place
     */
    @Override
    public void AddNeighbor(Place p){
        neighbors.add(p);
        p.Placed();
        map.getGame().getFrame().disableAsteroidActionButtons();
        asteroidView.updateAsteroid(this);
    }

    /**
     * remove a neighbor from the place
     */
    @Override
    public void RemoveNeighbor(Place p){
        neighbors.remove(p);
        asteroidView.updateAsteroid(this);
    }
}
