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

    public void SolarStorm() {
        for (int i = 0; i < places.length; i++) {
            places[i].HitByStorm();
        }
    }

    public int[] GetAllResources() {return allResources;}

    public void EnpughResources() {
        game.Win();
    }
    public void Step() {
        for (int i = 0; i < places.length; i++) {
            places[i].Step();
        }
    }
    public Place GetRandomPlace() {
        Random ran = new Random();
        return places[ran.nextInt(places.length)];
    }

    public void Connect() {
        for (int i= 0; i < places.length; i++) {

        }
    }
}
