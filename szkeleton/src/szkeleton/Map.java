package szkeleton;

import java.util.*;

public class Map implements Steppable{
    private ArrayList<Integer> allResources;
    private ArrayList<Place> places;
    Game game;
    
    
    //KA: a map konstruktoraban kene letrehozni az aszteroidakat, a place-eket nem kapja meg parameterkent
    public Map(Game game) {
        allResources = new ArrayList<>();
        places = new ArrayList<>();
        Random ran = new Random();
        for (int i = 0; i < ran.nextInt(31) + 20; i++) { // 20-50 között lesz az aszteroidák száma
        //    Asteroid newasteroid = new Asteroid(); Errort dob, mert a konstruktorban kéne Resource, de az itt nincs,
        //    places.add(newasteroid);                úgyis az aszteroidban kéne new Resource-szal új nyersanyagot csinálni
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
            if (!place.GetNeighbor(places.get(ran).placeID).equals(places.get(ran))  /*Ha még nem szomszédok, akkor...*/) {
                place.AddNeighbor(places.get(ran)); //Beálítom egymás szomszédjának a két place-t
                places.get(ran).AddNeighbor(place);
            }
        }
    }
}
