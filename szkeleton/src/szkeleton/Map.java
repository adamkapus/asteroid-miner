package szkeleton;

import java.util.*;

public class Map implements Steppable {
    private ArrayList<Integer> allResources;
    private ArrayList<Place> places;
    Game game;
    private String name;
    //KA: a map konstruktoraban kene letrehozni az aszteroidakat, a place-eket nem kapja meg parameterkent
    public Map(String name, Game game) {
        this.name = name;
        this.game = game;
        allResources = new ArrayList<>();
        places = new ArrayList<>();
        allResources.add(10); allResources.add(11); allResources.add(12); allResources.add(13);
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".Map()");

        Szkeleton.indentDepth--;
    }

    public Map(String n, Game game, int numOfAst) {
        name = n;
        allResources = new ArrayList<>();
        allResources.add(10); allResources.add(11); allResources.add(12); allResources.add(13);
        places = new ArrayList<>();
        Random ran = new Random();
        for (int i = 0; i < numOfAst; i++) {
            Resource resource = switch (ran.nextInt(5)) {
                case 0 -> new Coal("c");
                case 1 -> new IceWater("iw");
                case 2 -> new Iron("i");
                case 3 -> new Uran("u");
                default -> null;
            };
            Asteroid newasteroid = new Asteroid("a", i, this, resource);
            places.add(newasteroid);
        }
        this.game = game;

        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".Map(int)");

        Szkeleton.indentDepth--;
    }

    public void SolarStorm() { // Az összes place-en lejátszódik az a szcenárió, amikor napvihar van
    	Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".SolarStorm()");
        for (Place place : places) {
        	Szkeleton.indentDepth++;
            place.HitByStorm();
        }
        
        Szkeleton.indentDepth--;
    }

    public ArrayList<Integer>  GetAllResources() {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".GetAllResources()");
        Szkeleton.indentDepth--;
        return allResources;
    }

    public void EnpughResources() {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".EnoughResources()");
        Szkeleton.indentDepth++;
        game.Win();
        Szkeleton.indentDepth--;
    }
    public void Step() { // Az összes place-en meghívja a Step() függvényt
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".Step()");
        for (Place place : places) {
            Szkeleton.indentDepth++;
            place.Step();
        }
        Random random = new Random();
        if(random.nextInt(100) < 5) { // 5% az esélye, hogy napvihar keletkezik
            Szkeleton.indentDepth++;
            SolarStorm();
        }
        Szkeleton.indentDepth--;
    }

    public Place GetRandomPlace() { // Visszaad egy random place-t
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".GetRandomPlace()");
        
        //EZ ITT KELL?
        Szkeleton.indentDepth++;
        Random ran = new Random();

        Szkeleton.indentDepth--;

        return places.get(ran.nextInt(places.size()));
    }

    public void Connect() {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".Connect()");

        for (int i = 0; i < places.size(); i++){
            for (int j = 0; j < places.size(); j++){
                if (i != j){
                    Szkeleton.indentDepth++;
                    places.get(i).AddNeighbor(places.get(j));
                    Szkeleton.indentDepth++;
                    places.get(j).AddNeighbor(places.get(i));
                }
            }
        }

        Szkeleton.indentDepth--;
    }
    
    public void AddPlace(Place p) {
    	Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".AddPlace()");
    	places.add(p);
    	
    	Szkeleton.indentDepth--;
    }
}
