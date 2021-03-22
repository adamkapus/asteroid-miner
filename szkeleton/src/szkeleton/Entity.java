package szkeleton;

import java.util.ArrayList;

public abstract class Entity implements Steppable {
    protected Place place;
    protected Game game;
    
    String name;
    
    public Entity(String n, Game g, Place p) {
    	this.name = n;
    	this.game = g;
    	this.place = p;
    	Szkeleton.indentDepth++;
    	p.AcceptEntity(this);
    }
    
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

    public abstract void Action();

    public void Drill(){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".Drill()");

        Asteroid a = (Asteroid)place;

        Szkeleton.indentDepth++;
        a.ReduceRockLayer();

        Szkeleton.indentDepth--;
    }

    public ArrayList<Integer> UpdateResourceList(ArrayList<Integer> l){ return l; }

    public abstract void Die();
    public abstract void BlownUp();
}
