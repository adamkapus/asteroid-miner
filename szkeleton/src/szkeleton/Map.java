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
    }

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

    /**
     *A numOfAst-tal meghatározhatjuk, hogy mennyi aszteroida legyen a pályán.
     *
     */
    public Map(String n, Game game, int numOfAst) {
    	name = n;
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".Map(int)");
        allResources = new ArrayList<>();
        allResources.add(10); allResources.add(11); allResources.add(12); allResources.add(13);
        places = new ArrayList<>();
        Random ran = new Random();
        
        for (int i = 0; i < numOfAst; i++) {
        	Szkeleton.indentDepth++;
            Resource resource = switch (ran.nextInt(5)) {
                case 0 -> new Coal("c");
                case 1 -> new IceWater("iw");
                case 2 -> new Iron("i");
                case 3 -> new Uran("u");
                default -> null;
            };
            Szkeleton.indentDepth++;
            Asteroid newasteroid = new Asteroid("a", i, this, resource);
            places.add(newasteroid);
        }
        this.game = game;


        Szkeleton.indentDepth--;
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
    	Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".SolarStorm()");
        for (Place place : places) {
        	Szkeleton.indentDepth++;
            place.HitByStorm();
        }
        
        Szkeleton.indentDepth--;
    }

    public void EnoughResources() {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".EnoughResources()");

        Szkeleton.indentDepth++;
        game.Win();
        Szkeleton.indentDepth--;
    }

    /**
     * Az összes place-en meghívja a Step() függvényt
     * 5% az esélye, hogy napvihar keletkezik
     */
    public void Step() {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".Step()");
        for (Place place : places) {
            Szkeleton.indentDepth++;
            place.Step();
        }
        Random random = new Random();
        if(random.nextInt(100) < 5) {
            Szkeleton.indentDepth++;
            SolarStorm();
        }
    }

    /**
     *Visszaad egy random place-t
     */
    public Place GetRandomPlace() {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".GetRandomPlace()");
        Szkeleton.indentDepth--;

        Random ran = new Random();
        return places.get(ran.nextInt(places.size()));
    }

    /**
     * Két place típusú elemet összeköt, azaz szomszédjának állít be
     */
    //itt miért van kikommentelve minden?
    public void Connect() {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".Connect()");

        Szkeleton.indentDepth++;
        /*for (Place place : places) {
            Random random = new Random();
            int ran = random.nextInt(places.size());
            if (place.GetNeighbor(places.get(ran).placeID).placeID == places.get(ran).placeID)  /*Ha még nem szomszédok, akkor... { //PlaceID alapján kéne
                place.AddNeighbor(places.get(ran)); //Beálítom egymás szomszédjának a két place-t
                places.get(ran).AddNeighbor(place);
            }
        }*/

        for (int i = 0; i < places.size(); i++){
            for (int j = i+1; j < places.size(); j++){
                Szkeleton.indentDepth++;
                places.get(i).AddNeighbor(places.get(j));
                Szkeleton.indentDepth++;
                places.get(j).AddNeighbor(places.get(i));
            }
        }

        Szkeleton.indentDepth--;
    }

    /**
     *Hozzáad egy új Place típusú elemet a Map-hez
     */
    public void AddPlace(Place p) {
    	Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".AddPlace()");
    	places.add(p);
    	
    	Szkeleton.indentDepth--;
    }

    public int GetAstNum() {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".GetAstNum()");
        Szkeleton.indentDepth--;

        return places.size();
    }

    public ArrayList<Integer> GetAllResources() {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".GetAllResources()");
        Szkeleton.indentDepth--;

        return allResources;
    }

    public String ToString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Map ");
        sb.append(name);
        sb.append("\n\tgame ");
        if (game != null) {
            sb.append(game.getName());
        } else sb.append("null");
        sb.append("\n\tplace ");
        if(places.size() != 0) {
            for (Place p : places) {
                sb.append(p.GetName());
                sb.append(' ');
            }
        } else sb.append("null");
        sb.append('\n');

        return sb.toString();
    }
}
