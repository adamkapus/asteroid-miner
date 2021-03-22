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
     * Entotás konstruktora.
     */
    public Entity(String n, Game g, Place p) {
    	this.name = n;
    	this.game = g;
    	this.place = p;
    	Szkeleton.writeTabs(Szkeleton.indentDepth);
    	System.out.println(name +".Entity()");
    	Szkeleton.indentDepth++;
    	p.AcceptEntity(this);
    }

    /**
     * Szomszédos aszteroidára áthelyezi az entity-t.
     */
    public void Move(int asteroidID) {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".Move()");

        Szkeleton.indentDepth++;
        Place neighbour = place.GetNeighbor(asteroidID);
        Szkeleton.indentDepth++;
        place.RemoveEntity(this);
        Szkeleton.indentDepth++;
        neighbour.AcceptEntity(this);
        Szkeleton.indentDepth--;
    }

    /**
     * Az aszteroidán elvégezhető műveleteket kezeli
     */
    public abstract void Action();

    /**
     * Aszteroidán való fúrást kezeli
     */
    public void Drill(){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".Drill()");

        Asteroid a = (Asteroid)place;

        Szkeleton.indentDepth++;
        a.ReduceRockLayer();

        Szkeleton.indentDepth--;
    }

    /**
     *
     */
    public ArrayList<Integer> UpdateResourceList(ArrayList<Integer> l){ return l; }

    public abstract void Die();
    public abstract void BlownUp();
}
