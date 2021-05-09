package szkeleton;

import java.util.*;

/**
 * Az aszteroidálbók állló pályát állítja össze.
 * Az allResources lista tárolja az összes elérhető nyersanyagot a listában
 * A Places lista tárolja az összes Place típusú elemet amiből áll a program
 */
public class Map implements Steppable {
    private ArrayList<Integer> allResources;
    private ArrayList<Place> places;
    Game game;
    private String name;

    /**
     *A Map létrehozáskor megkapja a játékot amiben benne van, és kap egy nevet
     * Itt a Szkeleton sikeressége érdekében minden nyersanyagból hozzáadunk 1-et a játékhoz
     */

    public Map(String nev) {
        this.name = nev;
        game = null;
        allResources = new ArrayList<>();
        places = new ArrayList<>();
        allResources.add(10);
        allResources.add(11);
        allResources.add(12);
        allResources.add(13);
    }

    public Map(String name, Game game) {
        this.name = name;
        this.game = game;
        allResources = new ArrayList<>();
        places = new ArrayList<>();
        allResources.add(10); allResources.add(11); allResources.add(12); allResources.add(13);
    }

    /**
     *A numOfAst-tal meghatározhatjuk, hogy mennyi aszteroida legyen a pályán.
     *
     */
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
            
            Asteroid newasteroid = new Asteroid("a", i, this, resource, game.getFrame().getView().getAsteroidView());
            places.add(newasteroid);
        }
        this.game = game;
    }

    public String getName(){
        return this.name;
    }

    public Game getGame(){
        return this.game;
    }

    public ArrayList<Integer> getResources(){
        return this.allResources;
    }

    public ArrayList<Place> getPlaces() {
        return places;
    }

    public void setAllResources(ArrayList<Integer> list){
        this.allResources = list;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlaces(ArrayList<Place> places) {
        this.places = places;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Napviharokat kezeli
     * Az összes place-en lejátszódik az a szcenárió, amikor napvihar van
     */
    public void SolarStorm() {
        Random random = new Random();
        for (Place place : places) {
            if (random.nextInt(10) == 0) {
                place.HitByStorm();
                System.out.println(place.placeID);
            }
        }
    }

    public void EnoughResources() {
        game.Win();
    }

    /**
     * Az összes place-en meghívja a Step() függvényt
     * 5% az esélye, hogy napvihar keletkezik
     */
    public void Step() {
        for (Place place : places) {
            place.Step();
        }
        Random random = new Random();
        if(random.nextInt(100) < 5) {
        	//TESZT CELJARA KIKAPCSOLVA
            SolarStorm();
            System.out.println("solar storm");
        }
    }

    /**
     *Visszaad egy random place-t
     */
    public Place GetRandomPlace() {

        Random ran = new Random();
        return places.get(ran.nextInt(places.size()));
    }

    /**
     * Két place típusú elemet összeköt, azaz szomszédjának állít be
     */
    public void Connect(int i1, int i2) {
        places.get(i1).AddNeighbor(places.get(i2));
        places.get(i2).AddNeighbor(places.get(i1));
    }

    /**
     *Hozzáad egy új Place típusú elemet a Map-hez
     */
    public void AddPlace(Place p) {
    	places.add(p);
    }

    public int GetAstNum() {

        return places.size();
    }

    public ArrayList<Integer> GetAllResources() {
        return allResources;
    }
}
