package szkeleton;

import java.util.ArrayList;

/**
 * Entitások ősosztálya
 * A Game tárolja a listájukat, és egy aszteroidán tudnak tartózkodni
 */
public abstract class Entity implements Steppable {
    protected Place place;
    protected Game game;
    
    String name;

    /**
     * Entitás név szerinti konstruktora a tesztesetekhez.
     */
    public Entity(String n){
        this.name = n;
    }

    /**
     * Entitás konstruktora.
     */
    public Entity(String n, Game g, Place p) {
    	this.name = n;
    	this.game = g;
    	this.place = p;
    	p.AcceptEntity(this);
    }

    /**
     * Szomszédos aszteroidára áthelyezi az entity-t.
     */
    public void Move(int asteroidID) {
        Place neighbour = place.GetNeighbor(asteroidID);
        place.RemoveEntity(this);
        neighbour.AcceptEntity(this);
    }

    /**
     * Az aszteroidán elvégezhető műveleteket kezeli
     */
    public abstract void Action();

    /**
     * Aszteroidán való fúrást kezeli
     */
    public void Drill(){
        Asteroid a = (Asteroid)place;
        a.ReduceRockLayer();
    }

    /**
     *  Nyersanyagok listájának frissítése
     */
    public ArrayList<Integer> UpdateResourceList(ArrayList<Integer> l){ return l; }

    public abstract void Die();
    public abstract void BlownUp();

    public void SetPlace(Place p) {this.place = p;}
    public void SetGame(Game g) {this.game = g;}
    public Place GetPlace() {return this.place;}
    public Game GetGame() {return  this.game;}
}
