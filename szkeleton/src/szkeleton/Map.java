package szkeleton;

import java.util.*;

public class Map implements Steppable {
    private ArrayList<Integer> allResources;
    private ArrayList<Place> places;
    Game game;

    //KA: a map konstruktoraban kene letrehozni az aszteroidakat, a place-eket nem kapja meg parameterkent
    public Map(Game game) {
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
            Asteroid newasteroid = new Asteroid(i, this, resource);
            places.add(newasteroid);
        }
        this.game = game;
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
        Random ran = new Random();
        return places.get(ran.nextInt(places.size()));
    }

    public void Connect() {
        for (Place place : places) {
            Random random = new Random();
            int ran = random.nextInt(places.size());
            if (!place.GetNeighbor(places.get(ran).placeID).equals(places.get(ran))  /*Ha még nem szomszédok, akkor...*/) { //PlaceID alapján kéne
                place.AddNeighbor(places.get(ran)); //Beálítom egymás szomszédjának a két place-t
                places.get(ran).AddNeighbor(place);
            }
        }
    }
}
