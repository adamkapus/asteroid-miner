package szkeleton;

import java.util.Random;

public class Map implements Steppable{
    private int[] allResources;
    private Place[] places;
    Game game;

    public Map(int[] allResources, Place[] places, Game game) {
        this.allResources = allResources;
        this.places = places;
        this.game = game;
    }

    public void SolarStorm() { // Az összes place-en lejátszódik az a szcenárió, amikor napvihar van
        for (int i = 0; i < places.length; i++) {
            places[i].HitByStorm();
        }
    }

    public int[] GetAllResources() {return allResources;}

    public void EnpughResources() {
        game.Win();
    }
    public void Step() { // Az összes place-en meghívja a Step() függvényt
        for (int i = 0; i < places.length; i++) {
            places[i].Step();
        }
        Random random = new Random();
        if(random.nextInt(100) < 5) { // 5% az esélye, hogy napvihar keletkezik
            SolarStorm();
        }
    }
    public Place GetRandomPlace() { // Visszaad egy random place-t
        Random ran = new Random();
        return places[ran.nextInt(places.length)];
    }

    public void Connect() {
        for (int i= 0; i < places.length; i++) {
            Random random = new Random();
            int ran = random.nextInt(places.length);
            if(!places[i].GetNeighbor(places[ran].placeID).equals(places[ran])  /*Ha még nem szomszédok, akkor...*/) {
                places[i].AddNeighbor(places[ran]); //Beálítom egymás szomszédjának a két place-t
                places[ran].AddNeighbor(places[i]);
            }
        }
    }
}
