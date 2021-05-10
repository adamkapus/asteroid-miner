package szkeleton;

import java.util.ArrayList;
import java.util.Random;

public class Ufo extends Entity{

    private ArrayList<Resource> resources = new ArrayList<>();
    private UfoView ufoView;

    public Ufo(String name, Game g, Place p, UfoView uv){
        super(name, g, p);
        ufoView = uv;
        uv.updateUfo(this);
    }
    /**
     * Ufo műveletvégzés
     */
    public void Action() {
        this.place.Action(this);
    }

    /**
     * Meghal az ufo
      */
    public void Die() {
        game.UfoDied(this);
        ufoView.ufoDied(this);
    }

    /**
     * Az ufo felrobban
     */
    public void BlownUp() {
        this.Die();
        ufoView.updateUfo(this);
    }

    /**
     * Ufo egy lépése
     */
    public void Step() {
        Random rand1 = new Random();
        // random vagy műveletet hajt végre vagy mozog
        int rand_int = rand1.nextInt(2); // a random szám 0, vagy 1 lehet
        if (rand_int == 0)
            Action();
        else
            Move(place.GetRandomNeighbor().GetPlaceID());
    }

    public void UseTeleport() {
        // célállomás
        TeleportGate destination = ((TeleportGate) place).GetPair();
        // jelenlegiről le
        place.RemoveEntity(this);
        // újra fel
        destination.AcceptEntity(this);
        place=destination;
        game.finishedTurn();
        ufoView.updateUfo(this);
    }

    /**
     * Bányászás művelete
     */
    public void Mine() {
        Asteroid a = (Asteroid)place;
        // Csak akkor bányászhatunk, ha kevesebb mint 10 nyersanyagunk van
        if (resources.size() < 10){
            Resource r=a.MinedBy(this);
            if(r!=null) resources.add(r);
        }
    }

    /**
     * Szomszédos aszteroidára áthelyezi az entity-t.
     */
    public void Move(int asteroidID) {
        Place neighbour = place.GetNeighbor(asteroidID);
        place.RemoveEntity(this);
        neighbour.AcceptEntity(this);
        place = neighbour;
        ufoView.updateUfo(this);
    }

}
