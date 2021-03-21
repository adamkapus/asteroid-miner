package szkeleton;

import java.util.*;

public class Map implements Steppable {
    private ArrayList<Integer> allResources;
    private ArrayList<Place> places;
    Game game;
    private String name;
    //KA: a map konstruktoraban kene letrehozni az aszteroidakat, a place-eket nem kapja meg parameterkent
    public Map(String n, Game game) {
        name = n;
        allResources = new ArrayList<>();
        allResources.add(10); allResources.add(11); allResources.add(12); allResources.add(13); allResources.add(14);
        places = new ArrayList<>();
        Random ran = new Random();
        for (int i = 0; i < ran.nextInt(31) + 20; i++) { // 20-50 között lesz az aszteroidák száma
            Resource resource = switch (ran.nextInt(5)) {
                case 0 -> new Coal();
                case 1 -> new IceWater();
                case 2 -> new Iron();
                case 3 -> new Uran();
                default -> null;
            };
            
            //Asteroid newasteroid = new Asteroid("asteroid",i, this, resource);
            Asteroid newasteroid = new Asteroid("a", i, this, resource);
            places.add(newasteroid);
        }
        this.game = game;

        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".Map()");

        Szkeleton.indentDepth--;
    }

    public void SolarStorm() { // Az összes place-en lejátszódik az a szcenárió, amikor napvihar van
        for (Place place : places) {
            place.HitByStorm();
        }
    }

    public ArrayList<Integer>  GetAllResources() {return allResources;}

    public void EnpughResources() {
        game.Win();
    }
    public void Step() { // Az összes place-en meghívja a Step() függvényt
        for (Place place : places) {
            place.Step();
        }
        Random random = new Random();
        if(random.nextInt(100) < 5) { // 5% az esélye, hogy napvihar keletkezik
            SolarStorm();
        }
    }

    public Place GetRandomPlace() { // Visszaad egy random place-t
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".GetRandomPlace()");

        Szkeleton.indentDepth++;
        Random ran = new Random();

        Szkeleton.indentDepth--;

        return places.get(ran.nextInt(places.size()));
    }

    public void Connect() {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".Connect()");

        Szkeleton.indentDepth++;
        for (Place place : places) {
            Random random = new Random();
            int ran = random.nextInt(places.size());
            if (place.GetNeighbor(places.get(ran).placeID).placeID == places.get(ran).placeID)  /*Ha még nem szomszédok, akkor...*/ { //PlaceID alapján kéne
                place.AddNeighbor(places.get(ran)); //Beálítom egymás szomszédjának a két place-t
                places.get(ran).AddNeighbor(place);
            }
        }

        Szkeleton.indentDepth--;
    }
}
